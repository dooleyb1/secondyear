#include <stdio.h>
#include <pthread.h>  
#include <stdlib.h> 
#include <string.h>

//https://stackoverflow.com/questions/37245312/single-producer-and-multiple-consumers

char buf[200];
char exitChar[4] = "quit";
pthread_mutex_t mutex;
pthread_cond_t prod, cons, print;

//Main thread data struct
struct shared_state {
	int done; 		
	int value_available;
	int printing;
} state;


//Printer thread 
void *printerThread(void *args){
	
	//While !quit
	while(!state.done){
		
		pthread_mutex_lock(&mutex);
		
		//While theres no input to print available, wait
		while(!state.printing)
			pthread_cond_wait(&print,&mutex);
				
		//Start printing 
		state.printing = 1;
		printf("\nYou entered: %s", buf);
		state.printing = 0;
		state.value_available = 0;
		
		//Unlock mutex and tell producer to produce 
		pthread_cond_broadcast(&prod);
		pthread_mutex_unlock(&mutex);
	}
}

//Consumer thread
void *consumerThread(void *threadId){
	
	printf("Consumer %d successfully created...\n\n", threadId);
	//While !quit
	while(!state.done){
		
		printf("Locking mutex @ consumer %d...\n\n", threadId);
		pthread_mutex_lock(&mutex);
		printf("Mutex locked @ consumer %d...\n\n", threadId);
		
		//While theres no input available, wait
		while(!state.value_available)
			pthread_cond_wait(&cons,&mutex);
			
		//If printer is printing, wait
		while(state.printing)
			pthread_cond_wait(&print,&mutex);
			
		//Tell printer to print
		pthread_cond_broadcast(&print);
		pthread_mutex_unlock(&mutex);
	}
}

//Main thread (producer)
void *producerThread(void *args){
	
	pthread_t consumer_threads[3];
	pthread_t printer_thread;
	int t, returnCode, carryOn, i;;
	
	//Initialize shared variables
	mutex = (pthread_mutex_t)PTHREAD_MUTEX_INITIALIZER;
	prod = (pthread_cond_t)PTHREAD_COND_INITIALIZER;
	cons = (pthread_cond_t)PTHREAD_COND_INITIALIZER;
	print = (pthread_cond_t)PTHREAD_COND_INITIALIZER;
	
	//Initialize struct variables
	state.done = 0;
	state.value_available = 0;
	state.printing = 0;
	
	//Create consumer threads
	for(t=1;t<=3;t++){
		
		printf("Creating consumer thread number %i\n\n",t); 
		
		//Create integration thread and pass arguments for current interval
		returnCode = pthread_create(&consumer_threads[t],NULL,consumerThread,(void *) t); 
		if (returnCode) { 
			printf("ERROR return code from pthread_create() at consumerThread: %d\n",returnCode); 
			exit(-1); 
		} 
	}
	
	//Create printer thread
	printf("Creating printer thread\n\n"); 
	returnCode = pthread_create(&printer_thread,NULL,printerThread,args); 
	if (returnCode) { 
		printf("ERROR return code from pthread_create() at consumerThread: %d\n",returnCode); 
		exit(-1); 
	} 
	
	carryOn = 1;
    printf("Enter 'quit' to exit program...\n\n");
    
	while(!state.done) {
		//Lock producer mutex
		pthread_mutex_lock(&mutex);
	
		//If a value is waiting to be printed, wait
		while(state.value_available)
			pthread_cond_wait(&prod, &mutex);
			
		//Get string from user
		printf("Enter a string: ");
	  	fgets(buf, 200, stdin);
	  	printf( "\nYou entered: %s\nChecking string...\n\n", buf);
	  	state.value_available = 1;
	
	 	/* remove newline, if present */
	  	i = strlen(buf)-1;
	  	if( buf[ i ] == '\n') 
		  	buf[i] = '\0';
	   
	   	//If input != quit
    	if(strcmp(buf, exitChar) == 0){
			carryOn = 0;
			state.done = 1;
			printf("Goodbye!\n");
			return 0;
		}
		
		printf("Sending input to consumer...\n\n");
		
		//Tell any waiting consumer to consume
		pthread_cond_broadcast(&cons);
		pthread_mutex_unlock(&mutex);   	
	}
}



int main( ) {
	int t, returnCode;
	
	//Declare and create main thread (producer)
	pthread_t producer_thread;
	
	printf("Creating producer thread (main thread) \n\n"); 
		
	returnCode = pthread_create(&producer_thread,NULL,producerThread, (void *) t); 
	if (returnCode) { 
		printf("ERROR return code from pthread_create() at producer_thread: %d\n",returnCode); 
		exit(-1); 
	} 
	
	while(!state.done){
	
	}
}
