#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <string>
#include <arpa/inet.h>
#include <errno.h>
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <netinet/in.h>
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include<fcntl.h> 		
#include <fstream>
#include <sys/time.h>
#include <iostream>

using namespace std;
#define BUFSIZE 4096


/* Globals */
bool hostflag, portflag, helpflag, webflag, fileflag;
//Receiving function
static int readSocket(int socket, char *buffer);

void usage(int n)
{
  string usageString("usage: ./knock -h host -p port [-H] [-w] [-f file]");

  cout << usageString << "\n";
  exit(0);
}

int main(int argc, char **argv)
{
	//Initialise flags to false
	hostflag = portflag = helpflag = webflag = fileflag = false;

    char host[500];
    char resource_path[500];
    char request[1000];
    char* input_buffer;
    input_buffer = (char*) malloc(20000);
    struct hostent *server;
    struct sockaddr_in serveraddr;
    int port;
    int result;

	int n,i,j;
	//Process command line args
	for(n=i=1;i<argc;i=n) {
		n++;
		//If arg starts with '-' do this
		if (argv[i][0] == '-' && argv[i][1]) {
		  for(j=1;argv[i][j];j++) {
			switch(argv[i][j]) {
			case 'h':
			  hostflag = true;
			  memcpy(host, argv[2], strlen(argv[2])+1);
 			  cout << "hostflag set to true" << "\n";
			  cout << "hostvalue entered = " << host << "\n";
			  break;
			case 'p':
			  portflag = true;
			  port = atoi(argv[4]);
			  cout << "portflag set to true" << "\n";
			  cout << "portvalue entered = " << port << "\n";
			  break;
			case 'H':
			  helpflag = true;
			  cout << "helpflag set to true" << "\n";
			  break;
			case 'w':
			  webflag = true;
			  memcpy(resource_path, argv[6], strlen(argv[6])+1);
			  cout << "webflag set to true" << "\n";
			  cout << "HTTP get request is : " << resource_path << "\n";	
			  break;
			case 'f':
			  fileflag = true;
			  cout << "fileflag set to true" << "\n";
			  break;
			case '?':
			  helpflag = true;
			  cout << "helpflag set to true" << "\n";
			  break;
			default:
			  //If no flag has been set, then invalid input 
			  if(!hostflag && !portflag && !helpflag && !webflag && !fileflag) {
				  fprintf(stderr,"knock: Invalid argument -`%c'.\n",argv[i][j]);
				  usage(1);
				  exit(1);
				  break;
			  }
			}
		  }
		}
		//If arg doesn't start with '-' do this
		else if(argv[i][0] != '-') {
			for(j=0;argv[i][j];j++) {
	    		if (!strcmp("host", argv[i])) {
	     		  hostflag = true;
			  memcpy(host, argv[2], strlen(argv[2])+1);
 			  cout << "hostflag set to true" << "\n";
			  cout << "hostvalue entered = " << host << "\n";
	     		  break;
	    		}
	    		if (!strcmp("port",argv[i])) {
	    		  portflag = true;
			  port = atoi(argv[4]);
			  cout << "portflag set to true" << "\n";
			  cout << "portvalue entered = " << port << "\n";
	     		  break;
	    		}
				if (!strcmp("help",argv[i])) {
	    		  helpflag = true;
			  cout << "helpflag set to true" << "\n";
	     		  break;
	    		}
				if (!strcmp("web",argv[i])) {
	    		 	 webflag = true;
			 		 memcpy(resource_path, argv[6], strlen(argv[6])+1);
			  		 cout << "webflag set to true" << "\n";
			  		 cout << "HTTP get request is : " << resource_path << "\n";
	     		     break;
	    		}
				if (!strcmp("file",argv[i])) {
	    		  fileflag = true;
    			  cout << "fileflag set to true" << "\n";
	     		  break;
	    		}
				//If no flag has been set, then invalid input 
			  	else if(!hostflag && !portflag && !helpflag && !webflag && !fileflag) {
				  fprintf(stderr,"knock: Invalid argument -`%c'.\n",argv[i][j]);
				  usage(1);
				  exit(1);
				  break;
			  	}
			}
		}
	}

	if(helpflag){
		usage(1);
	}
	
	else{
		int tcpSocket = socket(AF_INET, SOCK_STREAM, 0);
     
    	if (tcpSocket < 0)
        	printf("\nError opening socket");
    	else
        	printf("\nSuccessfully opened socket");
     
    	server = gethostbyname(host);
     
    	if (server == NULL)
    	{
        	printf("gethostbyname() failed\n");
    	}
    	else
    	{
        	printf("\n%s = ", server->h_name);
        	unsigned int j = 0;
        	while (server -> h_addr_list[j] != NULL)
        	{
           		printf("%s", inet_ntoa(*(struct in_addr*)(server -> h_addr_list[j])));
            		j++;
        	}
	}
	printf("\n");
 
    	bzero((char *) &serveraddr, sizeof(serveraddr));
    	serveraddr.sin_family = AF_INET;

	bcopy((char *)server->h_addr, (char *)&serveraddr.sin_addr.s_addr, server->h_length);
     
    	serveraddr.sin_port = htons(port);
	
	if (connect(tcpSocket, (struct sockaddr *) &serveraddr, sizeof(serveraddr)) < 0)
        	printf("\nError Connecting");
    	else
        	printf("\nSuccessfully Connected\n");
 
    	sprintf(request, "GET /%s HTTP/1.1\r\nHost: %s\r\nContent-Type: text/plain\r\n\r\n", resource_path, host);
  	printf("\nCreated Get Request is below:\n\n\n");
 
	printf("%s", request);
	
	if( send(tcpSocket , request , strlen(request) , 0) < 0)
    	{
        	printf("Send failed");
        	return 1;
    	}
    	printf("Data Sent\n");

	//Now receive full data
    	readSocket(tcpSocket, input_buffer);
    	return 0;
  }
}

static int readSocket(int socket, char *buffer)
{
    ssize_t bytes_read = 0;
	
    //testing stuff, BUFSIZE = 4096
    printf("Received Socket # %d\nBuffer Size = %d\n", socket, BUFSIZE);
	
    bytes_read = recv(socket, buffer, BUFSIZE - 1, 0);
    while (bytes_read > 0)
    {
        buffer[bytes_read] = 0; // Null-terminate the buffer
        printf("Buffer: %s\n", buffer);	
        bytes_read = recv(socket, buffer, BUFSIZE - 1, 0);		
    }
    if (bytes_read == -1)
    {
        // Requires inclusion of <errno.h>
        printf("Socket recv failed");
        return -1;
    }
    return 0;
}

