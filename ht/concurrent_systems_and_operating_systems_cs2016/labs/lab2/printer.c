#include <stdio.h>
#include <pthread.h>  
#include <stdlib.h> 

char str[200];
char exitChar[4] = "quit";
pthread_t printer_thread;

//Printer thread struct
struct printer_struct {
	int id;
	int status;
	char stringToPrint[200];
};

//Consumer thread struct
struct consumer_struct {
	int id;
	int status;
	char inputString[200];
};

//Method to get user input returns 1 if successful, 0 if unsuccesful
int getChar() {

	printf("Enter a string: ");
  	fgets(str, 200, stdin);
	int i;
	
 	/* remove newline, if present */
  	i = strlen(str)-1;
  	if( str[ i ] == '\n') 
      	str[i] = '\0';
    
    //If input != quit
    if(strcmp(str, exitChar) != 0)
    	return 1;
    	
    else
    	return 0;
	
}

//Integration by parts (threaded)
void *consumerThread(void *args){
	
	struct consumer_struct* actual_args = args;
	int id = actual_args->id;
	int status = actual_args->status;
	
	//While status = 0, do nothing and wait until status is 1
	while(status!=1){
		if(status==1)
			
			
	}
	
	free(actual_args);
	return 0;
}

int main( ) {

	int t, returnCode;
	
	pthread_t consumer_threads[3];
	pthread_t printer_thread;
	
	//Create consumer threads
	for(t=0;t<=3;t++){
		//Create struct to hold arguments for thread
		struct consumer_struct *args = malloc(sizeof *args);
		
		//Pass arguments to struct
        args->id = t;
        args->status = 0;
		
		printf("Creating thread number %i\n\n",t); 
		
		//Create integration thread and pass arguments for current interval
		returnCode = pthread_create(&consumer_threads[t],NULL,consumerThread,args); 
		if (returnCode) { 
			printf("ERROR return code from pthread_create(): %d\n",returnCode); 
			exit(-1); 
		} 
	}
	
	for(t=0;t<8000000;t++){
	 int i = 1+1;
	}
	/*
	int returnVal = 1;
    returnVal = getChar();
    printf("Enter 'quit' to exit program...\n\n");
    
	while(returnVal != 0) {
		printf( "\nYou entered: %s\nSending input to printer...\n\n", str);
		//sendCharToPrinter();
		returnVal = getChar();
	}
		
   	printf("Goodbye!\n");
   	return 0;
   	*/

}
