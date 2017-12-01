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

	char buffer[BUFSIZ];
   	enum CONSTEXPR { MAX_REQUEST_LEN = 1024};
    	char request[MAX_REQUEST_LEN];
    	char request_template[] = "GET / HTTP/1.1\r\nHost: %s\r\n\r\n";
    	struct protoent *protoent;
    	char *host;
    	in_addr_t in_addr;
    	int request_len;
    	int socket_file_descriptor;
    	ssize_t nbytes_total, nbytes_last;
    	struct hostent *hostent;
    	struct sockaddr_in sockaddr_in;
    	unsigned short port;


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
			  port = strtoul(argv[4], NULL, 10);
			  cout << "portflag set to true" << "\n";
			  cout << "portvalue entered = " << port << "\n";
			  break;
			case 'H':
			  helpflag = true;
			  cout << "helpflag set to true" << "\n";
			  break;
			case 'w':
			  webflag = true;
			  cout << "webflag set to true" << "\n";
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
			  port = strtoul(argv[4], NULL, 10);
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
			  cout << "webflag set to true" << "\n";
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
		// check request length
		request_len = snprintf(request, MAX_REQUEST_LEN, request_template, host);
    		if (request_len >= MAX_REQUEST_LEN) {
      			fprintf(stderr, "request length large: %d\n", request_len);
        		exit(1);
    		}	
		

		// build the socket
   		protoent = getprotobyname("tcp");
    		if (protoent == NULL) {
        		perror("getprotobyname");
        		exit(1);
    		}

    		socket_file_descriptor = socket(AF_INET, SOCK_STREAM, protoent->p_proto);
    		if (socket_file_descriptor == -1) {
        		perror("socket");
        		exit(1);
    		}

		// build the address using DNS
    		hostent = gethostbyname(host);
    		if (hostent == NULL) {
        		fprintf(stderr, "error: gethostbyname(\"%s\")\n", host);
        		exit(1);
   		}
		else
			cout << "Successfly found hostname..." << "\n";	
		
		// setup address structure
		in_addr = inet_addr(inet_ntoa(*(struct in_addr*)*(hostent->h_addr_list)));
   		if (in_addr == (in_addr_t)-1) {
        		fprintf(stderr, "error: inet_addr(\"%s\")\n", *(hostent->h_addr_list));
        		exit(1);
    		}
		sockaddr_in.sin_addr.s_addr = in_addr;
   		sockaddr_in.sin_family = AF_INET;
    		sockaddr_in.sin_port = htons(port);

		// actually connect to the socket
    		if (connect(socket_file_descriptor, (struct sockaddr*)&sockaddr_in, sizeof(sockaddr_in)) == -1) {
      		  	perror("connect");
      		  	exit(1);
   		}
		else
			cout << "Successfly connected to socket..." << "\n";	

      		// send HTTP request
    		nbytes_total = 0;
    		while (nbytes_total < request_len) {
	       		nbytes_last = write(socket_file_descriptor, request + nbytes_total, request_len - nbytes_total);
			if (nbytes_last == -1) {
		    		perror("write");
		    		exit(1);
			}
        		nbytes_total += nbytes_last;
    		}
		cout << "Successfly sent request..." << "\n";


	    fprintf(stderr, "debug: before first read\n");
    	while ((nbytes_total = read(socket_file_descriptor, buffer, BUFSIZ)) > 0) {
        	fprintf(stderr, "debug: after a read\n");
        	write(STDOUT_FILENO, buffer, nbytes_total);
    	}
    	fprintf(stderr, "debug: after last read\n");
    	if (nbytes_total == -1) {
       		perror("read");
        	exit(1);
    	}

    	close(socket_file_descriptor);
    	exit(1);
	}
}
