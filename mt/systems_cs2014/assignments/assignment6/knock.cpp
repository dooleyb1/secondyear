#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>
#include <iostream>
#include <string>
#include <boost/array.hpp>
#include <boost/asio.hpp>
using namespace std;
using boost::asio::ip::tcp;


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
 			  cout << "hostflag set to true" << "\n";
			  break;
			case 'p':
			  portflag = true;
			  cout << "portflag set to true" << "\n";
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
			      cout << "hostflag set to true" << "\n";
	     		  break;
	    		}
	    		if (!strcmp("port",argv[i])) {
	    		  portflag = true;
				  cout << "portflag set to true" << "\n";
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

 		try
  		{
			// Any program that uses asio need to have at least one io_service object
			boost::asio::io_service io_service;

			// Convert the server name that was specified as a parameter to the application, to a TCP endpoint. 
			// To do this, we use an ip::tcp::resolver object.
			tcp::resolver resolver(io_service);

			// A resolver takes a query object and turns it into a list of endpoints. 
			// We construct a query using the name of the server, specified in argv[1], 
			// and the name of the service, in this case "daytime".
			tcp::resolver::query query(argv[2], "daytime");

			// The list of endpoints is returned using an iterator of type ip::tcp::resolver::iterator. 
			// A default constructed ip::tcp::resolver::iterator object can be used as an end iterator.
			tcp::resolver::iterator endpoint_iterator = resolver.resolve(query);

			// Now we create and connect the socket.
			// The list of endpoints obtained above may contain both IPv4 and IPv6 endpoints, 
			// so we need to try each of them until we find one that works. 
			// This keeps the client program independent of a specific IP version. 
			// The boost::asio::connect() function does this for us automatically.
			tcp::socket socket(io_service);
			boost::asio::connect(socket, endpoint_iterator);

			// The connection is open. All we need to do now is read the response from the daytime service.
			for (;;)
			{
			  // We use a boost::array to hold the received data. 
			  boost::array<char, 128> buf;
			  boost::system::error_code error;

			  // The boost::asio::buffer() function automatically determines 
			  // the size of the array to help prevent buffer overruns.
			  size_t len = socket.read_some(boost::asio::buffer(buf), error);

			  // When the server closes the connection, 
			  // the ip::tcp::socket::read_some() function will exit with the boost::asio::error::eof error, 
			  // which is how we know to exit the loop.
			  if (error == boost::asio::error::eof)
				break; // Connection closed cleanly by peer.
			  else if (error)
				throw boost::system::system_error(error); // Some other error.

			  std::cout.write(buf.data(), len);
			}
		  }
		  // handle any exceptions that may have been thrown.
		  catch (std::exception& e)
		  {
			std::cerr << e.what() << std::endl;
		  }

		  return 0;
	}
}
