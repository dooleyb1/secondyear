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

#include <fstream>
#include <iostream>

using namespace std;


/* Globals */
bool hostflag, portflag, helpflag, webflag, fileflag;

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

	int port;
	string host;
	char getrequest[1000], message[1000], server_reply[2000];

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
			  host = argv[2];
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
 			  memcpy(getrequest, argv[6], strlen(argv[6])+1);
			  cout << "webflag set to true" << "\n";
 			  cout << "HTTP get request is : " << getrequest << "\n";
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
			  host = argv[2];
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
			  cout << "webflag set to true" << "\n";
			  memcpy(getrequest, argv[6], strlen(argv[6])+1);
			  cout << "webflag set to true" << "\n";
 			  cout << "HTTP get request is : " << getrequest << "\n";
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
		// use DNS to get IP address
    		struct hostent *hostEntry;
    		hostEntry = gethostbyname(host.c_str());

    		if (!hostEntry) {
        		cout << "No such host name: " << host << endl;
        		exit(-1);
		}
		else
			cout << "Successfly found hostname..." << "\n";	
		
		// setup socket address structure
    		struct sockaddr_in server_addr;
   		memset(&server_addr,0,sizeof(server_addr));
    		server_addr.sin_family = AF_INET;
    		server_addr.sin_port = htons(port);
    		memcpy(&server_addr.sin_addr, hostEntry->h_addr_list[0], hostEntry->h_length);

      		// create socket
    		int server = socket(PF_INET,SOCK_STREAM,0);
    		if (server < 0) {
        		perror("socket");
        		exit(-1);
    		}
		else
			cout << "Socket successfully created..." << "\n";

      		// connect to server
    		if (connect(server,(const struct sockaddr *)&server_addr,sizeof(server_addr)) < 0) {
       			perror("connect");
        		exit(-1);
    		}
		else
			cout << "Successfly connected to server..." << "\n";	

		//keep communicating with server
	      	while(1)
	   	{
			printf("Enter message : ");
			scanf("%s" , message);
		 
			//Send some data
			if( send(server , message , strlen(message) , 0) < 0)
			{
		    		puts("Send failed");
		    		return 1;
			}
		 
			//Receive a reply from the server
			if( recv(server , server_reply , 2000 , 0) < 0)
			{
		    		puts("recv failed");
		    		break;
			}
		 
			puts("Server reply :");
			puts(server_reply);
	    	}
	     
	   	close(server);
	    	return 0;  			
	}
}
