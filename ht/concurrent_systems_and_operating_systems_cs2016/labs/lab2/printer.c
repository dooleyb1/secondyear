#include <stdio.h>
#include <pthread.h>  
#include <stdlib.h> 
#include <string.h>

//https://stackoverflow.com/questions/37245312/single-producer-and-multiple-consumers

char buf[200];
char exitChar[4] = "quit";

//Main thread data struct
struct shared_state {
	int done; 		
	int value_available;
	int printing;
	int printing_thread_id;
	pthread_mutex_t mutex;
	pthread_cond_t cond;
	pthread_cond_t print_cond;
	pthread_cond_t main_cond;
} state;


//Printer thread 
void *printerThread(void *args){
	
	//While !quit
	while(!state.done){
		
		//printf("Locking mutex @ printer...\n\n");
		pthread_mutex_lock(&state.mutex);
		//printf("Mutex locked @ printer...\n\n");
		
		//While theres no input to print available, wait
		if(!state.value_available)
			pthread_cond_wait(&state.print_cond,&state.mutex);
		
		if(!state.done){		
			printf("\n%i: %s", state.printing_thread_id,buf);
			state.printing = 0;
			state.value_available = 0;
			state.printing_thread_id = 9;
		}
		
		//Signal mainline printing is finished
		pthread_cond_signal(&state.main_cond);
		//Unlock mutex
		pthread_mutex_unlock(&state.mutex);
	}
	pthread_exit(0);
}

//Consumer thread
void *consumerThread(void *args){
	
	int threadId = *((int *) args);
	//While !quit
	while(!state.done){
		pthread_mutex_lock(&state.mutex);
		
		//While theres no input available, wait
		if(!state.value_available)
			pthread_cond_wait(&state.cond,&state.mutex);
			
		//When condition signal recieved & text != quit, signal printer to print
		if(!state.done){
			state.printing_thread_id = threadId;
			pthread_cond_signal(&state.print_cond);
		}	
		pthread_mutex_unlock(&state.mutex);
	}
	free(args);
	pthread_exit(0);
}

int main( ) {
	
	pthread_t consumer_threads[3];
	pthread_t printer_thread;
	int t, returnCode, carryOn, i;
	
	//Initialize struct variables
	state.done = 0;
	state.value_available = 0;
	state.printing = 0;
	state.printing_thread_id = 9;
	state.mutex = (pthread_mutex_t)PTHREAD_MUTEX_INITIALIZER;
	state.cond = (pthread_cond_t)PTHREAD_COND_INITIALIZER;
	state.print_cond = (pthread_cond_t)PTHREAD_COND_INITIALIZER;
	state.main_cond = (pthread_cond_t)PTHREAD_COND_INITIALIZER;
	
	//Create consumer threads
	for(t=1;t<=3;t++){
		
		//printf("Creating consumer thread number %i\n\n",t); 
		int *arg = malloc(sizeof(*arg));
		*arg = t;
		//Create integration thread and pass arguments for current interval
		returnCode = pthread_create(&consumer_threads[t],NULL,consumerThread,arg); 
		if (returnCode) { 
			printf("ERROR return code from pthread_create() at consumerThread: %d\n",returnCode); 
			exit(-1); 
		} 
	}
	
	//Create printer thread
	//printf("Creating printer thread...\n\n"); 
	returnCode = pthread_create(&printer_thread,NULL,printerThread,NULL); 
	if (returnCode) { 
		printf("ERROR return code from pthread_create() at consumerThread: %d\n",returnCode); 
		exit(-1); 
	} 
	
	carryOn = 1;
    printf("Enter 'quit' to exit program...\n\n");
    printf("Enter a string: ");
    
	while(!state.done) {
	
		//Get string from user
	  	fgets(buf, 200, stdin);
	  	//printf( "\nYou entered: %s", buf);
	
	 	/* remove newline, if present */
	  	i = strlen(buf)-1;
	  	if( buf[ i ] == '\n') 
		  	buf[i] = '\0';
	   
	   	//If input != quit
    	if(strcmp(buf, exitChar) == 0){
    		pthread_mutex_lock(&state.mutex);
			carryOn = 0;
			state.done = 1;
			//This allows threads to exit
			pthread_cond_broadcast(&state.cond);
			pthread_cond_signal(&state.print_cond);
			pthread_mutex_unlock(&state.mutex);
			printf("Goodbye!\n");
		}
		
		if(!state.done) {
			//Tell any waiting consumer to consume
			pthread_mutex_lock(&state.mutex);
			
			state.value_available = 1; 
			state.printing = 1;
			pthread_cond_signal(&state.cond);
			
			//While printing wait
			if(state.printing)
				pthread_cond_wait(&state.main_cond,&state.mutex);
				printf("\n\nEnter a string: ");	
			
			pthread_mutex_unlock(&state.mutex);   
		}	
	}


	for(t=1;t<=3; t++)
        pthread_join(consumer_threads[t], NULL);

    pthread_join(printer_thread,NULL);
    printf("Successfully exited all threads!\n");

}
