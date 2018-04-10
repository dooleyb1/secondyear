#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#define NUM_THREADS 6

//Threads method, takes in parameter of threadid
void *PrintHello(void *threadid) {

	printf("\n%d: Hello World!\n", threadid);
	pthread_exit(NULL);
}

int main (int argc, const char * argv[]) {
	
	//Array of pthreads
	pthread_t threads[NUM_THREADS];
	int rc,t;
	
	//Create pthreads
	for (t=0;t<NUM_THREADS;t++) {
		
		printf("Creating thread %d\n",t);

		//Create respective thread
		rc = pthread_create(&threads[t], NULL, PrintHello, (void *)t);
		
		if (rc) {
			printf("ERROR return code from pthread_create(): %d\n",rc);
			exit(-1);
		}
	}
	
	//Wait for threads to exit
	for(t=0;t<NUM_THREADS;t++) {
		pthread_join( threads[t], NULL);
	}

	return(0);
}