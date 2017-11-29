<!DOCTYPE html>
<head>
  <title>Boost.Asio - 4. TCP Socket Programming- 2017</title>
  <meta content="Boost.Asio - 4. TCP Socket Programming" name="description" />
  <meta content="Boost.Asio - 4. TCP Socket Programming" name="keywords" />
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.no-icons.min.css" rel="stylesheet">
  <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
  <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Alice|Open+Sans:400,300,700">
  <link rel="stylesheet" href="/public/css/app.css">
  <link rel="stylesheet" href="/public/css/styles.css">
  <link rel="stylesheet" href="/public/css/bogostyleWidePreNew.css">
</head>

<body class="home">
    <nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">      
      <div class="navbar-header">
        <!--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> -->
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse" aria-expanded="false" aria-controls="navbar"> 
          <span class="sr-only">Toggle navigation</span> 
          <span class="icon-bar"></span> 
          <span class="icon-bar"></span> 
          <span class="icon-bar"></span> 
        </button>
        <a class="navbar-brand" href="/index.php">BogoToBogo</a>
      </div>
      
      <div class="navbar-collapse collapse">
        
        <ul class="nav navbar-nav">
          <li class="active"><a href="/index.php">Home</a></li>
          <li><a href="/about_us.php">About</a></li>
          <li><a href="/Hadoop/BigData_hadoop_Install_on_ubuntu_single_node_cluster.php">Big Data</a></li>
          <li><a href="/python/scikit-learn/Artificial-Neural-Network-ANN-1-Introduction.php">Machine Learning</a></li>
          <li><a href="/AngularJS/AngularJS_Introduction.php">AngularJS</a></li> 
          <li><a href="/python/pytut.php">Python</a></li>
          <li><a href="/cplusplus/cpptut.php">C++</a></li>
          <li><a href="/DevOps/DevOps_Jenkins_Chef_Puppet_Graphite_Logstash.php">DevOps </a></li>
          <li><a href="/Algorithms/algorithms.php">Algorithms</a></li> 
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">More...<b class="caret"></b></a>
            <ul class="dropdown-menu">
           
              <li><a href="/Qt/Qt5_Creating_QtQuick2_QML_Application_Animation_A.php">Qt 5</a></li>                           
              <li><a href="/Android/android.php">Android</a></li>
              
              <li><a href="/Linux/linux_tips1.php">Linux</a></li>
              <li><a href="/Java/tutorials/on_java.php">Java</a></li>
              <li><a href="/CSharp/.netframework.php">CSharp</a></li>
              <li><a href="/VideoStreaming/videostreaming_etc.php">Video Streaming</a></li>
              <li><a href="/FFMpeg/ffmpeg_fade_in_fade_out_transitions_effects_filters_slideshow_concat.php">FFmpeg</a></li>
              <li><a href="/Matlab/Matlab_Tutorial_Manipulating_Audio_I_Reverse_Delay_Tone_Control_Changing_Speed_Removing_Vocals.php">Matlab</a></li>
              <li><a href="/python/Django/Python_Django_Forums_Shared_Host.php">Django 1.8</a></li>
              <li><a href="/Laravel5/Laravel5_ToDo_List_Sample.php">Laravel 5.2</a></li>
              <li><a href="/RubyOnRails/RubyOnRails.php">Ruby On Rails</a></li>
              <li><a href="/HTML5/HTML5_Tutorial.php">HTML5 & CSS</a></li>
              <li><a href="/AmazingPlaces/index.php" target="_blank">
<img src="/Menus/MenuIcons/Earth_8px_transparent_background.png"
width="24" height="24"/>Earth</a> </li>
            </ul>
          </li>
        </ul>      
      </div>
    </div>  
  </nav>  

  <div id="main">
    <div class="container">
      <div class="row section featured topspace">
        <div class="row">
          <div class="col-sm-9 col-md-9 col-xs-9">
            <h2 class="section-title">Boost.Asio - 4. TCP Socket Programming - 2017        <g:plusone></g:plusone></h2>
            <div class="SocialLinks">
  <span class='st__large' displayText=''></span>
  <br><br>
  <div align="center">
  <span class='st_facebook_large' displayText='Facebook'></span>
  <span class='st_twitter_large' displayText='Tweet'></span>
  <span class='st_linkedin_large' displayText='LinkedIn'></span>
  </div>
  <br><br>
  <script type="text/javascript" src="http://w.sharethis.com/button/buttons.js"></script>
  <script type="text/javascript">stLight.options({publisher: "b9569c43-5f56-4501-92f0-4bf4aa8fceb0", doNotHash: false, doNotCopy: true, hashAddressBar: false});</script>
</div>

<div id="bookmarkshare">
  <script type="text/javascript">var addthis_config = {"data_track_clickback":true};</script>
  <a class="addthis_button" href="http://www.addthis.com/bookmark.php?v=250&amp;username=khhong7"><img src="http://s7.addthis.com/static/btn/v2/lg-share-en.gif" width="125" height="16" alt="Bookmark and Share" style="border:0"/></a>
  <script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#username=khhong7"></script>
</div>

<br>
<hr>
<br>


<!-- Google bogo1 ad -->
<!-- Google search box -->



<!-- bogo1 -->
<div>
  <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
  <!-- bogo1 -->
  <ins class="adsbygoogle"
     style="display:inline-block;width:728px;height:90px"
     data-ad-client="ca-pub-4716428189734495"
     data-ad-slot="6542308167"></ins>
  <script>
   (adsbygoogle = window.adsbygoogle || []).push({});
  </script>
</div>


<!-- Google search box -->
<div class="AdSenseSearch">
  bogotobogo.com site search:
  <form action="http://www.google.com" id="cse-search-box" target="_blank">
    <div>
      <input type="hidden" name="cx" value="partner-pub-4716428189734495:1794050961" />
      <input type="hidden" name="ie" value="UTF-8" />
      <!--<input type="text" name="q" size="55" />-->
      <input type="text" name="q" size="" width="90%"/>
      <input type="submit" name="sa" value="Search" />
    </div>
  </form>
  <script type="text/javascript" src="http://www.google.com/coop/cse/brand?form=cse-search-box&amp;lang=en"></script>
</div>




<hr>
<br><br><br>
<div class="subtitle" id="SocketProgramming">TCP Socket Programming</div>
<p>The <b>Boost.Asio</b> provides many I/O objects to develop network applications. Though <b>Boost.Asio</b> is a library that can process any kind of data asynchronously, it is mainly being used for network programming.</p>
<p>As we can see from the subsequent samples, it provides developers with a consistent asynchronous I/O model using a modern C++ approach.</p>
<p>The materials used in this section are based on the <a href="http://www.ietf.org/rfc/rfc867.txt" target="_blank">daytime</a> protocol and mostly borrowed from <a href="http://www.boost.org/doc/libs/1_55_0/doc/html/boost_asio/tutorial.html" target="_blank">Introduction to Sockets</a>.</p>
<blockquote>"<b>The Daytime Protocol</b> is a service in the Internet Protocol Suite, defined in 1983 in RFC 867. It is intended for testing and measurement purposes in computer networks. A host may connect to a server that supports the Daytime Protocol on either Transmission Control Protocol (TCP) or User Datagram Protocol (UDP) port 13". </blockquote>
<p>From <a href="http://en.wikipedia.org/wiki/Daytime_Protocol" target="_blank">http://en.wikipedia.org/wiki/Daytime_Protocol</a>:</p>
<p>On UNIX-like operating systems a daytime server is usually built into the <b>inetd</b> (or <b>xinetd</b>) daemon. The service is usually not enabled by default. It may be enabled by adding the following lines to the file <b>/etc/inetd.conf</b> and telling <b>inetd</b> to reload its configuration:</p>
<pre>
daytime   stream  tcp     nowait  root    internal
daytime   dgram   udp     wait    root    internal
</pre>
<p>
An example output may be:</p>
<pre>
Tuesday, February 22, 1982 18:45:59-PST
</pre>

<!-- Google bogo_square_ad -->
<div>
  <script type="text/javascript">
    google_ad_client = "ca-pub-4716428189734495";
    /* bogo_LargeRectangle_336_280 */
    google_ad_slot = "2712696561";
    google_ad_width = 336;
    google_ad_height = 280;
  </script>
  <script type="text/javascript"
    src="//pagead2.googlesyndication.com/pagead/show_ads.js">
  </script>
</div>
<br>





<br/><br/>
<div class="subtitle_2nd" id="A_synchronousTCP server">Synchronous TCP server</div>
<p>This sample code shows how to use <b>asio</b> to implement a server application with TCP.</p>
<pre>
#include &lt;ctime&gt;
#include &lt;iostream&gt;
#include &lt;string&gt;
#include &lt;boost/asio.hpp&gt;

using boost::asio::ip::tcp;

std::string make_daytime_string()
{
  std::time_t now = std::time(0);
  return std::ctime(&amp;now;);
}

int main()
{
  try
  {
    // Any program that uses asio need to have at least one io_service object
    boost::asio::io_service io_service;

    // acceptor object needs to be created to listen for new connections
    tcp::acceptor acceptor(io_service, tcp::endpoint(tcp::v4(), 13));

    for (;;)
    {
      // creates a socket
      tcp::socket socket(io_service);

      // wait and listen
      acceptor.accept(socket);

      // prepare message to send back to client
      std::string message = make_daytime_string();

      boost::system::error_code ignored_error;

      // writing the message for current time
      boost::asio::write(socket, boost::asio::buffer(message), ignored_error);
    }
  }
  catch (std::exception&amp; e)
  {
    std::cerr &lt;&lt; e.what() &lt;&lt; std::endl;
  }

  return 0;
}
</pre>
<p>Here is the code: <a href="http://www.bogotobogo.com/cplusplus/Boost/files/asioD/server.cpp" target="_blank">server.cpp</a>.</p>
<br/>
<p>First, we defined the function <b>make_daytime_string()</b> to create the string to be sent back to the client.</p>
<p>Any program that uses <b>asio</b> need to have at least one <b>io_service</b> object:</p>
<pre>
boost::asio::io_service io_service;
</pre>
<p>
A <b>ip::tcp::acceptor</b> object needs to be created to <font color="blue">listen</font> for new connections. It is initialised to listen on TCP <b>port 13</b>, for IP version 4:</p>
<pre>
tcp::acceptor acceptor(io_service, tcp::endpoint(tcp::v4(), 13));
</pre>
<p>This is an iterative server that it handles one connection at a time. We <font color="blue">creates a socket</font> that represents the connection to the client, and then <font color="blue">waits</font> for a connection:</p>
<pre>
tcp::socket socket(io_service);  
acceptor.accept(socket);
</pre>
<p>When a client is accessing our service. This server should determine the current time and transfer this information to the client:</p>
<pre>
std::string message = make_daytime_string();

boost::system::error_code ignored_error;
boost::asio::write(socket, boost::asio::buffer(message), ignored_error);
</pre>
<p>Let's run the server:</p>
<pre>
$ ./server
</pre>
<br/><br/><br/><br/>
<div class="subtitle_2nd" id="A_synchronousTCP client">Synchronous TCP client</div>
<pre>
#include &lt;iostream&gt;
#include &lt;boost/array.hpp&gt;
#include &lt;boost/asio.hpp&gt;

using boost::asio::ip::tcp;

int main(int argc, char* argv[])
{
  try
  {
    // the user should specify the server - the 2nd argument
    if (argc != 2)
    {
      std::cerr &lt;&lt; "Usage: client <host>" &lt;&lt; std::endl;
      return 1;
    }

    // Any program that uses asio need to have at least one io_service object
    boost::asio::io_service io_service;

    // Convert the server name that was specified as a parameter to the application, to a TCP endpoint. 
    // To do this, we use an ip::tcp::resolver object.
    tcp::resolver resolver(io_service);

    // A resolver takes a query object and turns it into a list of endpoints. 
    // We construct a query using the name of the server, specified in argv[1], 
    // and the name of the service, in this case "daytime".
    tcp::resolver::query query(argv[1], "daytime");

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
  catch (std::exception&amp; e)
  {
    std::cerr &lt;&lt; e.what() &lt;&lt; std::endl;
  }

  return 0;
}
</char,></host></pre>
<p>Here is the code: <a href="http://www.bogotobogo.com/cplusplus/Boost/files/asioD/client.cpp" target="_blank">client.cpp</a>.</p>
<p>If the client request connection, it gets the current time:</p>
<pre>
$ ./client 127.0.0.1
Sat Feb 15 12:27:13 2014
</pre>
<br/><br/>
<br/><br/>
<div class="subtitle_2nd" id="AsynchronousTCP server">Asynchronous TCP server</div>
<pre>
#include &lt;ctime&gt;
#include &lt;iostream&gt;
#include &lt;string&gt;
#include &lt;boost/bind.hpp&gt;
#include &lt;boost/shared_ptr.hpp&gt;
#include &lt;boost/enable_shared_from_this.hpp&gt;
#include &lt;boost/asio.hpp&gt;

using boost::asio::ip::tcp;

std::string make_daytime_string()
{
  std:: time_t now = std::time(0);
  return std::ctime(&amp;now;);
}

class tcp_connection
  <font color="blue">// Using shared_ptr and enable_shared_from_this 
  // because we want to keep the tcp_connection object alive 
  // as long as there is an operation that refers to it.</font>
  : public boost::enable_shared_from_this&lt;tcp_connection&gt;
{
public:
  typedef boost::shared_ptr&lt;tcp_connection&gt; pointer;

  static pointer create(boost::asio::io_service&amp; io_service)
  {
    return pointer(new tcp_connection(io_service));
  }

  tcp::socket&amp; socket()
  {
    return socket_;
  }

  <font color="blue">// Call boost::asio::async_write() to serve the data to the client. 
  // We are using boost::asio::async_write(), 
  // rather than ip::tcp::socket::async_write_some(), 
  // to ensure that the entire block of data is sent.</font>

  void start()
  {
    <font color="blue">// The data to be sent is stored in the class member m_message 
    // as we need to keep the data valid 
    // until the asynchronous operation is complete.
    m_message = make_daytime_string();</font>

    <font color="blue">// When initiating the asynchronous operation, 
    // and if using boost::bind(), 
    // we must specify only the arguments 
    // that match the handler's parameter list. 
    // In this code, both of the argument placeholders 
    // (boost::asio::placeholders::error 
    // and boost::asio::placeholders::bytes_transferred) 
    // could potentially have been removed, 
    // since they are not being used in handle_write().</font>

    boost::asio::async_write(socket_, boost::asio::buffer(m_message),
        boost::bind(&amp;tcp;_connection::handle_write, shared_from_this(),
          boost::asio::placeholders::error,
          boost::asio::placeholders::bytes_transferred));
  }

private:
  tcp_connection(boost::asio::io_service&amp; io_service)
    : socket_(io_service)
  {
  }
  <font color="blue">// handle_write() is responsible for any further actions 
  // for this client connection.</font>
  void handle_write(const boost::system::error_code&amp; /*error*/,
      size_t /*bytes_transferred*/)
  {
  }

  tcp::socket socket_;
  std::string m_message;
};

class tcp_server
{
public:
  <font color="blue">// Constructor: initialises an acceptor to listen on TCP port 13.</font>
  tcp_server(boost::asio::io_service&amp; io_service)
    : acceptor_(io_service, tcp::endpoint(tcp::v4(), 13))
  {
    <font color="blue">// start_accept() creates a socket and 
    // initiates an asynchronous accept operation 
    // to wait for a new connection.</font>
    start_accept();
  }

private:
  void start_accept()
  {
    <font color="blue">// creates a socket</font>
    tcp_connection::pointer new_connection =
      tcp_connection::create(acceptor_.get_io_service());

    <font color="blue">// initiates an asynchronous accept operation 
    // to wait for a new connection. </font>
    acceptor_.async_accept(new_connection-&gt;socket(),
        boost::bind(&amp;tcp;_server::handle_accept, this, new_connection,
          boost::asio::placeholders::error));
  }

  <font color="blue">// handle_accept() is called when the asynchronous accept operation 
  // initiated by start_accept() finishes. It services the client request</font>
  void handle_accept(tcp_connection::pointer new_connection,
      const boost::system::error_code&amp; error)
  {
    if (!error)
    {
      new_connection-&gt;start();
    }

    <font color="blue">// Call start_accept() to initiate the next accept operation.</font>
    start_accept();
  }

  tcp::acceptor acceptor_;
};

int main()
{
  try
  {
    <font color="blue">// We need to create a server object to accept incoming client connections.</font>
    boost::asio::io_service io_service;

    <font color="blue">// The io_service object provides I/O services, such as sockets, 
    // that the server object will use.</font>
    tcp_server server(io_service);

    <font color="blue">// Run the io_service object to perform asynchronous operations.</font>
    io_service.run();
  }
  catch (std::exception&amp; e)
  {
    std::cerr &lt;&lt; e.what() &lt;&lt; std::endl;
  }

  return 0;
}
</pre>
<p>Here is the code: <a href="http://www.bogotobogo.com/cplusplus/Boost/files/asioD/async_server.cpp" target="_blank">async_server.cpp</a>.</p>
<p>Let's run the server and then client:</p>
<pre>
$ ./async_server

$ ./client 127.0.0.1
Sat Feb 15 12:50:53 2014
</pre>
<br/>
<p>References used in this chapter:</p>
<ol>
<li><a href="http://www.boost.org/doc/libs/1_55_0/doc/html/boost_asio/tutorial.html" target="_blank">http://www.boost.org/doc/libs/1_55_0/doc/html/boost_asio/tutorial.html</a>.
 </li>
<li><a href="http://en.highscore.de/cpp/boost/asio.html" target="_blank">http://en.highscore.de/cpp/boost/asio.html</a>.
 </li>
</ol>
<br/><br/>
<br/>
<br/><br/>
<br /><br />

<div class="skyscraper">
  <br>


<div class="skyscraper">

  <div class="bogo-paypal">
    <!-- Paypal Donate button -->
    <p><i>Sponsor Open Source development activities and free contents for everyone.</i></p>

    <form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
    <input type="hidden" name="cmd" value="_s-xclick">
    <input type="hidden" name="encrypted" value="-----BEGIN PKCS7-----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-----END PKCS7-----
    ">
    <input type="image" src="https://www.paypalobjects.com/webstatic/en_US/btn/btn_donate_pp_142x27.png" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
    <img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
    </form>
    <p><i>Thank you.</i></p>
    <p>- <a href="http://bogotobogo.com/about_us.php" target="_blank">K Hong</a></p>
    <!-- End of Paypal Donate button   -->
  </div>


  <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
  <!-- bogo_skyscraper -->
  <ins class="adsbygoogle"
       style="display:inline-block;width:160px;height:600px"
       data-ad-client="ca-pub-4716428189734495"
       data-ad-slot="5321096966"></ins>
  <script>
  (adsbygoogle = window.adsbygoogle || []).push({});
  </script>


  <br><br>





</div></div>

<!-- cplusplus_list_INNER.php -->
<br><br><br>
<h1>C++ Tutorials</h1>
   <a href="/cplusplus/cpptut.php">C++ Home</a> 
   <br><br>
   <a href="/Algorithms/algorithms.php">Algorithms & Data Structures in C++ ...</a>
   <br><br>
   <a href="/cplusplus/application_visual_studio_2013.php">Application (UI) - using Windows Forms (Visual Studio 2013/2012)</a>
   <br><br>
   <a href="/cplusplus/autoptr.php">auto_ptr</a>
   <br><br>
   <a href="/cplusplus/binarytree.php">Binary Tree Example Code</a>
   <br><br>
   <a href="/cplusplus/blackjackQT.php">Blackjack with Qt</a>
   <br><br>
   <a href="/cplusplus/boost.php">Boost - shared_ptr, weak_ptr, mpl, lambda, etc.</a>
   <br><br>
   <a href="/cplusplus/Boost/boost_AsynchIO_asio_tcpip_socket_server_client_timer_A.php">Boost.Asio (Socket Programming - Asynchronous TCP/IP)...</a>
   <br><br>
   <a href="/cplusplus/class.php">Classes and Structs</a> 
   <br><br>
   <a href="/cplusplus/constructor.php">Constructor</a> 
   <br><br>
   <a href="/cplusplus/cplusplus11.php">C++11(C++0x): rvalue references, move constructor, and lambda, etc.</a> 
   <br><br>
   <a href="/cplusplus/cpptesting.php">C++ API Testing</a>
   <br><br>
   <a href="/cplusplus/cplusplus_keywords.php">C++ Keywords - const, volatile, etc.</a>
   <br><br>
   <a href="/cplusplus/CppCrashDebuggingMemoryLeak.php">Debugging Crash & Memory Leak</a>
   <br><br>
   <a href="/DesignPatterns/introduction.php">Design Patterns in C++ ...</a>
   <br><br>
   <a href="/cplusplus/dynamic_cast.php">Dynamic Cast Operator</a>
   <br><br>
   <a href="/cplusplus/eclipse_CDT_JNI_MinGW_64bit.php">Eclipse CDT / JNI (Java Native Interface) / MinGW</a>
   <br><br>
   <a href="/cplusplus/embeddedSystemsProgramming.php">Embedded Systems Programming I - Introduction</a>
   <br><br>
   <a href="/cplusplus/embeddedSystemsProgramming_gnu_toolchain_ARM_cross_compiler.php">Embedded Systems Programming II - gcc ARM Toolchain and Simple Code on Ubuntu and Fedora</a>
   <br><br>
   <a href="/cplusplus/embeddedSystemsProgramming_GNU_ARM_ToolChain_Eclipse_CDT_plugin.php">Embedded Systems Programming III - Eclipse CDT Plugin for gcc ARM Toolchain </a>
   <br><br>
   <a href="/cplusplus/exceptions.php">Exceptions</a> 
   <br><br>
   <a href="/cplusplus/friendclass.php">Friend Functions and Friend Classes</a>
   <br><br>
   <a href="/cplusplus/fstream_input_output.php">fstream: input & output</a>
   <br><br>
   <a href="/cplusplus/function_overloading.php">Function Overloading</a>
   <br><br>
   <a href="/cplusplus/functor_function_object_stl_intro.php">Functors (Function Objects) I - Introduction</a>
   <br><br>
   <a href="/cplusplus/functor_function_object_stl_2.php">Functors (Function Objects) II - Converting function to functor</a>
   <br><br>
   <a href="/cplusplus/functors.php">Functors (Function Objects) - General</a>
   <br><br>
   <br><br>
   <a href="/cplusplus/Git/Git_GitHub_Express.php">Git and GitHub Express...</a>
   <br><br>
   <a href="/cplusplus/google_unit_test_gtest.php">GTest (Google Unit Test) with Visual Studio 2012</a>
   <br><br>
   <a href="/cplusplus/multipleinheritance.php">Inheritance & Virtual Inheritance (multiple inheritance) </a>
   <br><br>
   <a href="/cplusplus/libraries.php">Libraries - Static, Shared (Dynamic)</a>
   <br><br>
   <a href="/cplusplus/linked_list_basics.php">Linked List Basics</a>
   <br><br>
   <a href="/cplusplus/linkedlist.php">Linked List Examples</a>
   <br><br>
   <a href="/cplusplus/make.php">make & CMake</a>
   <br><br>
   <a href="/cplusplus/gnumake.php">make (gnu)</a>
   <br><br>
   <a href="/cplusplus/memoryallocation.php">Memory Allocation</a>
   <br><br>
   <a href="/cplusplus/multithreaded.php">Multi-Threaded Programming - Terminology - Semaphore, Mutex, Priority Inversion etc.</a>
   <br><br>
   <a href="/cplusplus/multithreading_win32A.php">Multi-Threaded Programming II -  Native Thread for Win32 (A) </a>
   <br><br>
   <a href="/cplusplus/multithreading_win32B.php">Multi-Threaded Programming II -  Native Thread for Win32 (B) </a>
   <br><br>
   <a href="/cplusplus/multithreading_win32C.php">Multi-Threaded Programming II -  Native Thread for Win32 (C) </a>
   <br><br>
   <a href="/cplusplus/multithreading_win32.php">Multi-Threaded Programming II - C++ Thread for Win32</a>
   <br><br>
   <a href="/cplusplus/multithreading_pthread.php">Multi-Threaded Programming III - C/C++ Class Thread for Pthreads</a>
   <br><br>
   <a href="/cplusplus/multithreading_ipc.php">MultiThreading/Parallel Programming - IPC</a>
   <br><br>
   <a href="/cplusplus/multithreaded4_cplusplus11.php">Multi-Threaded Programming with C++11 Part A (start, join(), detach(), and ownership)</a>
   <br><br>
   <a href="/cplusplus/multithreaded4_cplusplus11B.php">Multi-Threaded Programming with C++11 Part B (Sharing Data - mutex, and race conditions, and deadlock)</a>
   <br><br>
   <a href="/cplusplus/multithreadedDebugging.php">Multithread Debugging</a>
   <br><br>
   <a href="/cplusplus/object_returning.php">Object Returning</a>
   <br><br>
   <a href="/cplusplus/slicing.php">Object Slicing and Virtual Table</a>
   <br><br>
   <a href="/cplusplus/opencv.php">OpenCV with C++</a> 
   <br><br>
   <a href="/cplusplus/operatoroverloading.php">Operator Overloading I</a> 
   <br><br>
   <a href="/cplusplus/operator_oveloading_self_assignment.php">Operator Overloading II - self assignment</a> 
   <br><br>
   <a href="/cplusplus/valuevsreference.php">Pass by Value vs. Pass by Reference</a>
   <br><br>
   <a href="/cplusplus/pointers.php">Pointers</a>
   <br><br>
   <a href="/cplusplus/pointers2_voidpointers_arrays.php">Pointers II - void pointers & arrays</a>
   <br><br>
   <a href="/cplusplus/pointers3_function_multidimensional_arrays.php">Pointers III - pointer to function & multi-dimensional arrays</a>
   <br><br>
   <a href="/cplusplus/preprocessor_macro.php">Preprocessor - Macro</a>
   <br><br>
   <a href="/cplusplus/private_inheritance.php">Private Inheritance</a>
   <br><br>
   <a href="/python/python_cpp_sip.php">Python & C++ with SIP</a>
   <br><br>
   <a href="/cplusplus/RandomNumbers.php">(Pseudo)-random numbers in C++</a>
   <br><br>
   <a href="/cplusplus/references.php">References for Built-in Types</a>
   <br><br>
   <a href="/cplusplus/sockets_server_client.php">Socket - Server & Client</a>
   <br><br>
    <a href="/cplusplus/sockets_server_client_QT.php">Socket - Server & Client with Qt (Asynchronous / Multithreading / ThreadPool etc.)</a>
   <br><br>
   <a href="/cplusplus/stackunwinding.php">Stack Unwinding</a>
   <br><br>
   <a href="/cplusplus/stl_vector_list.php">Standard Template Library (STL) I - Vector & List</a>
   <br><br>
   <a href="/cplusplus/stl2_map.php">Standard Template Library (STL) II - Maps</a>
   <br><br>
   <a href="/cplusplus/stl2_unorderd_map_cpp11_hash_table_hash_function.php">Standard Template Library (STL) II - unordered_map</a>
   <br><br>
   <a href="/cplusplus/stl2B_set.php">Standard Template Library (STL) II - Sets</a>
   <br><br>
   <a href="/cplusplus/stl3_iterators.php">Standard Template Library (STL) III - Iterators</a>
   <br><br>
   <a href="/cplusplus/stl4_algorithms.php">Standard Template Library (STL) IV - Algorithms</a>
   <br><br>
   <a href="/cplusplus/stl5_function_objects.php">Standard Template Library (STL) V - Function Objects</a>
   <br><br>
   <a href="/cplusplus/statics.php">Static Variables and Static Class Members</a>
   <br><br>
   <a href="/cplusplus/string.php">String</a> 
   <br><br>
   <a href="/cplusplus/string2.php">String II - sstream etc.</a> 
   <br><br>
   <a href="/cplusplus/assembly.php">Taste of Assembly</a>
   <br><br>
   <a href="/cplusplus/templates.php">Templates</a>
   <br><br>
   <a href="/cplusplus/template_specialization_function_class.php">Template Specialization</a>
   <br><br>
   <a href="/cplusplus/template_specialization_traits.php">Template Specialization - Traits</a>
   <br><br>
   <a href="/cplusplus/template_declaration_definition_header_implementation_file.php">Template Implementation & Compiler (.h or .cpp?)</a>
   <br><br>
   <a href="/cplusplus/this_pointer.php">The this Pointer</a>
   <br><br>
   <a href="/cplusplus/typecast.php">Type Cast Operators</a>
   <br><br>
   <a href="/cplusplus/upcasting_downcasting.php">Upcasting and Downcasting</a>
   <br><br>
   <a href="/cplusplus/virtual_destructors_shared_ptr.php">Virtual Destructor & boost::shared_ptr</a>
   <br><br>
   <a href="/cplusplus/virtualfunctions.php">Virtual Functions</a>
   <br><br>
   <br><br>
   <i>Programming Questions and Solutions &darr;</i>
      <br><br>
       <a href="/cplusplus/quiz_strings_arrays.php">Strings and Arrays</a>
       <br><br>
       <a href="/cplusplus/quiz_linkedlist.php">Linked List</a>
       <br><br>
       <a href="/cplusplus/quiz_recursion.php">Recursion</a>
       <br><br>
       <a href="/cplusplus/quiz_bit_manipulation.php">Bit Manipulation</a> 
       <br><br>
       <a href="/cplusplus/smallprograms.php">Small Programs (string, memory functions etc.)</a>
       <br><br>
       <a href="/cplusplus/quiz_math_probability.php">Math & Probability</a>
       <br><br>
       <a href="/cplusplus/quiz_multithreading.php">Multithreading</a>
       <br><br>
       <a href="/cplusplus/google_interview_questions.php">140 Questions by Google</a> 
       <br><br>
       <br><br>
   <a href="/Qt/Qt5_Creating_QtQuick2_QML_Application_Animation_A.php">Qt 5 EXPRESS...</a>
   <br><br>
   <a href="/Win32API/Win32API_DLL.php">Win32 DLL ...</a>
   <br><br>
   <a href="/cplusplus/cppNews.php">Articles On C++</a> 
   <br><br>
   <a href="/cplusplus/C11/C11_initializer_list.php">What's new in C++11...</a> 
   <br><br>
   <a href="/cplusplus/C11/1_C11_creating_thread.php">C++11 Threads EXPRESS...</a> 
   <br><br>
   <a href="/OpenCV/opencv_3_tutorial_imgproc_gausian_median_blur_bilateral_filter_image_smoothing.php">OpenCV...</a> 
<br />

<br/><br/>
<br/>
<br/><br/>
<br/>

<div>
    
<div class="custom-disqus">
<!-- Disqus -->
<div id="disqus_thread"></div>
<script>
    /**
     *  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
     *  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables
     */
     
/* Disabling Disqus 4/26/2017
    var disqus_config = function () {
        this.page.url = window.location.href;
        this.page.identifier = document.title;
    };
    
    (function() {  // REQUIRED CONFIGURATION VARIABLE: EDIT THE SHORTNAME BELOW
        var d = document, s = d.createElement('script');
        
        s.src = '//bogotobogocom.disqus.com/embed.js';  // IMPORTANT: Replace EXAMPLE with your forum shortname!
        
        s.setAttribute('data-timestamp', +new Date());
        (d.head || d.body).appendChild(s);
    })();
 Disabling Disqus */    
</script>
<noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript" rel="nofollow">comments powered by Disqus.</a></noscript>

<!-- Disqus ends here -->
</div>

<br>
<br>
<br>
<br></div>
          </div>
          <div class="col-sm-3 col-md-3 col-xs-3">
  	    <div class="g-person" data-width="1" data-href="//plus.google.com/111664369941456137911" data-rel="author">
  	    </div>
            <div class="resume">
              <p>Ph.D. / Golden Gate Ave, San Francisco / Seoul National Univ / Carnegie Mellon / UC Berkeley / DevOps / Deep Learning / Visualization</p>
            </div>
  	    <div>
                
<div class="skyscraper">
  <br>


<div class="skyscraper">

  <div class="bogo-paypal">
    <!-- Paypal Donate button -->
    <p><i>Sponsor Open Source development activities and free contents for everyone.</i></p>

    <form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
    <input type="hidden" name="cmd" value="_s-xclick">
    <input type="hidden" name="encrypted" value="-----BEGIN PKCS7-----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-----END PKCS7-----
    ">
    <input type="image" src="https://www.paypalobjects.com/webstatic/en_US/btn/btn_donate_pp_142x27.png" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
    <img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
    </form>
    <p><i>Thank you.</i></p>
    <p>- <a href="http://bogotobogo.com/about_us.php" target="_blank">K Hong</a></p>
    <!-- End of Paypal Donate button   -->
  </div>


  <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
  <!-- bogo_skyscraper -->
  <ins class="adsbygoogle"
       style="display:inline-block;width:160px;height:600px"
       data-ad-client="ca-pub-4716428189734495"
       data-ad-slot="5321096966"></ins>
  <script>
  (adsbygoogle = window.adsbygoogle || []).push({});
  </script>


  <br><br>





</div></div>




<!-- Place this tag after the last widget tag. -->
<script type="text/javascript">
        (function() {
          var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
          po.src = 'https://apis.google.com/js/platform.js';
          var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
        })();
</script>
  	    </div>
  	    <div class="side_menu">
                <br /><br />

<div class="skyscraper">
  <br>


<div class="skyscraper">

  <div class="bogo-paypal">
    <!-- Paypal Donate button -->
    <p><i>Sponsor Open Source development activities and free contents for everyone.</i></p>

    <form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
    <input type="hidden" name="cmd" value="_s-xclick">
    <input type="hidden" name="encrypted" value="-----BEGIN PKCS7-----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-----END PKCS7-----
    ">
    <input type="image" src="https://www.paypalobjects.com/webstatic/en_US/btn/btn_donate_pp_142x27.png" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
    <img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
    </form>
    <p><i>Thank you.</i></p>
    <p>- <a href="http://bogotobogo.com/about_us.php" target="_blank">K Hong</a></p>
    <!-- End of Paypal Donate button   -->
  </div>


  <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
  <!-- bogo_skyscraper -->
  <ins class="adsbygoogle"
       style="display:inline-block;width:160px;height:600px"
       data-ad-client="ca-pub-4716428189734495"
       data-ad-slot="5321096966"></ins>
  <script>
  (adsbygoogle = window.adsbygoogle || []).push({});
  </script>


  <br><br>





</div></div>

<!-- cplusplus_list_INNER.php -->
<br><br><br>
<h1>C++ Tutorials</h1>
   <a href="/cplusplus/cpptut.php">C++ Home</a> 
   <br><br>
   <a href="/Algorithms/algorithms.php">Algorithms & Data Structures in C++ ...</a>
   <br><br>
   <a href="/cplusplus/application_visual_studio_2013.php">Application (UI) - using Windows Forms (Visual Studio 2013/2012)</a>
   <br><br>
   <a href="/cplusplus/autoptr.php">auto_ptr</a>
   <br><br>
   <a href="/cplusplus/binarytree.php">Binary Tree Example Code</a>
   <br><br>
   <a href="/cplusplus/blackjackQT.php">Blackjack with Qt</a>
   <br><br>
   <a href="/cplusplus/boost.php">Boost - shared_ptr, weak_ptr, mpl, lambda, etc.</a>
   <br><br>
   <a href="/cplusplus/Boost/boost_AsynchIO_asio_tcpip_socket_server_client_timer_A.php">Boost.Asio (Socket Programming - Asynchronous TCP/IP)...</a>
   <br><br>
   <a href="/cplusplus/class.php">Classes and Structs</a> 
   <br><br>
   <a href="/cplusplus/constructor.php">Constructor</a> 
   <br><br>
   <a href="/cplusplus/cplusplus11.php">C++11(C++0x): rvalue references, move constructor, and lambda, etc.</a> 
   <br><br>
   <a href="/cplusplus/cpptesting.php">C++ API Testing</a>
   <br><br>
   <a href="/cplusplus/cplusplus_keywords.php">C++ Keywords - const, volatile, etc.</a>
   <br><br>
   <a href="/cplusplus/CppCrashDebuggingMemoryLeak.php">Debugging Crash & Memory Leak</a>
   <br><br>
   <a href="/DesignPatterns/introduction.php">Design Patterns in C++ ...</a>
   <br><br>
   <a href="/cplusplus/dynamic_cast.php">Dynamic Cast Operator</a>
   <br><br>
   <a href="/cplusplus/eclipse_CDT_JNI_MinGW_64bit.php">Eclipse CDT / JNI (Java Native Interface) / MinGW</a>
   <br><br>
   <a href="/cplusplus/embeddedSystemsProgramming.php">Embedded Systems Programming I - Introduction</a>
   <br><br>
   <a href="/cplusplus/embeddedSystemsProgramming_gnu_toolchain_ARM_cross_compiler.php">Embedded Systems Programming II - gcc ARM Toolchain and Simple Code on Ubuntu and Fedora</a>
   <br><br>
   <a href="/cplusplus/embeddedSystemsProgramming_GNU_ARM_ToolChain_Eclipse_CDT_plugin.php">Embedded Systems Programming III - Eclipse CDT Plugin for gcc ARM Toolchain </a>
   <br><br>
   <a href="/cplusplus/exceptions.php">Exceptions</a> 
   <br><br>
   <a href="/cplusplus/friendclass.php">Friend Functions and Friend Classes</a>
   <br><br>
   <a href="/cplusplus/fstream_input_output.php">fstream: input & output</a>
   <br><br>
   <a href="/cplusplus/function_overloading.php">Function Overloading</a>
   <br><br>
   <a href="/cplusplus/functor_function_object_stl_intro.php">Functors (Function Objects) I - Introduction</a>
   <br><br>
   <a href="/cplusplus/functor_function_object_stl_2.php">Functors (Function Objects) II - Converting function to functor</a>
   <br><br>
   <a href="/cplusplus/functors.php">Functors (Function Objects) - General</a>
   <br><br>
   <br><br>
   <a href="/cplusplus/Git/Git_GitHub_Express.php">Git and GitHub Express...</a>
   <br><br>
   <a href="/cplusplus/google_unit_test_gtest.php">GTest (Google Unit Test) with Visual Studio 2012</a>
   <br><br>
   <a href="/cplusplus/multipleinheritance.php">Inheritance & Virtual Inheritance (multiple inheritance) </a>
   <br><br>
   <a href="/cplusplus/libraries.php">Libraries - Static, Shared (Dynamic)</a>
   <br><br>
   <a href="/cplusplus/linked_list_basics.php">Linked List Basics</a>
   <br><br>
   <a href="/cplusplus/linkedlist.php">Linked List Examples</a>
   <br><br>
   <a href="/cplusplus/make.php">make & CMake</a>
   <br><br>
   <a href="/cplusplus/gnumake.php">make (gnu)</a>
   <br><br>
   <a href="/cplusplus/memoryallocation.php">Memory Allocation</a>
   <br><br>
   <a href="/cplusplus/multithreaded.php">Multi-Threaded Programming - Terminology - Semaphore, Mutex, Priority Inversion etc.</a>
   <br><br>
   <a href="/cplusplus/multithreading_win32A.php">Multi-Threaded Programming II -  Native Thread for Win32 (A) </a>
   <br><br>
   <a href="/cplusplus/multithreading_win32B.php">Multi-Threaded Programming II -  Native Thread for Win32 (B) </a>
   <br><br>
   <a href="/cplusplus/multithreading_win32C.php">Multi-Threaded Programming II -  Native Thread for Win32 (C) </a>
   <br><br>
   <a href="/cplusplus/multithreading_win32.php">Multi-Threaded Programming II - C++ Thread for Win32</a>
   <br><br>
   <a href="/cplusplus/multithreading_pthread.php">Multi-Threaded Programming III - C/C++ Class Thread for Pthreads</a>
   <br><br>
   <a href="/cplusplus/multithreading_ipc.php">MultiThreading/Parallel Programming - IPC</a>
   <br><br>
   <a href="/cplusplus/multithreaded4_cplusplus11.php">Multi-Threaded Programming with C++11 Part A (start, join(), detach(), and ownership)</a>
   <br><br>
   <a href="/cplusplus/multithreaded4_cplusplus11B.php">Multi-Threaded Programming with C++11 Part B (Sharing Data - mutex, and race conditions, and deadlock)</a>
   <br><br>
   <a href="/cplusplus/multithreadedDebugging.php">Multithread Debugging</a>
   <br><br>
   <a href="/cplusplus/object_returning.php">Object Returning</a>
   <br><br>
   <a href="/cplusplus/slicing.php">Object Slicing and Virtual Table</a>
   <br><br>
   <a href="/cplusplus/opencv.php">OpenCV with C++</a> 
   <br><br>
   <a href="/cplusplus/operatoroverloading.php">Operator Overloading I</a> 
   <br><br>
   <a href="/cplusplus/operator_oveloading_self_assignment.php">Operator Overloading II - self assignment</a> 
   <br><br>
   <a href="/cplusplus/valuevsreference.php">Pass by Value vs. Pass by Reference</a>
   <br><br>
   <a href="/cplusplus/pointers.php">Pointers</a>
   <br><br>
   <a href="/cplusplus/pointers2_voidpointers_arrays.php">Pointers II - void pointers & arrays</a>
   <br><br>
   <a href="/cplusplus/pointers3_function_multidimensional_arrays.php">Pointers III - pointer to function & multi-dimensional arrays</a>
   <br><br>
   <a href="/cplusplus/preprocessor_macro.php">Preprocessor - Macro</a>
   <br><br>
   <a href="/cplusplus/private_inheritance.php">Private Inheritance</a>
   <br><br>
   <a href="/python/python_cpp_sip.php">Python & C++ with SIP</a>
   <br><br>
   <a href="/cplusplus/RandomNumbers.php">(Pseudo)-random numbers in C++</a>
   <br><br>
   <a href="/cplusplus/references.php">References for Built-in Types</a>
   <br><br>
   <a href="/cplusplus/sockets_server_client.php">Socket - Server & Client</a>
   <br><br>
    <a href="/cplusplus/sockets_server_client_QT.php">Socket - Server & Client with Qt (Asynchronous / Multithreading / ThreadPool etc.)</a>
   <br><br>
   <a href="/cplusplus/stackunwinding.php">Stack Unwinding</a>
   <br><br>
   <a href="/cplusplus/stl_vector_list.php">Standard Template Library (STL) I - Vector & List</a>
   <br><br>
   <a href="/cplusplus/stl2_map.php">Standard Template Library (STL) II - Maps</a>
   <br><br>
   <a href="/cplusplus/stl2_unorderd_map_cpp11_hash_table_hash_function.php">Standard Template Library (STL) II - unordered_map</a>
   <br><br>
   <a href="/cplusplus/stl2B_set.php">Standard Template Library (STL) II - Sets</a>
   <br><br>
   <a href="/cplusplus/stl3_iterators.php">Standard Template Library (STL) III - Iterators</a>
   <br><br>
   <a href="/cplusplus/stl4_algorithms.php">Standard Template Library (STL) IV - Algorithms</a>
   <br><br>
   <a href="/cplusplus/stl5_function_objects.php">Standard Template Library (STL) V - Function Objects</a>
   <br><br>
   <a href="/cplusplus/statics.php">Static Variables and Static Class Members</a>
   <br><br>
   <a href="/cplusplus/string.php">String</a> 
   <br><br>
   <a href="/cplusplus/string2.php">String II - sstream etc.</a> 
   <br><br>
   <a href="/cplusplus/assembly.php">Taste of Assembly</a>
   <br><br>
   <a href="/cplusplus/templates.php">Templates</a>
   <br><br>
   <a href="/cplusplus/template_specialization_function_class.php">Template Specialization</a>
   <br><br>
   <a href="/cplusplus/template_specialization_traits.php">Template Specialization - Traits</a>
   <br><br>
   <a href="/cplusplus/template_declaration_definition_header_implementation_file.php">Template Implementation & Compiler (.h or .cpp?)</a>
   <br><br>
   <a href="/cplusplus/this_pointer.php">The this Pointer</a>
   <br><br>
   <a href="/cplusplus/typecast.php">Type Cast Operators</a>
   <br><br>
   <a href="/cplusplus/upcasting_downcasting.php">Upcasting and Downcasting</a>
   <br><br>
   <a href="/cplusplus/virtual_destructors_shared_ptr.php">Virtual Destructor & boost::shared_ptr</a>
   <br><br>
   <a href="/cplusplus/virtualfunctions.php">Virtual Functions</a>
   <br><br>
   <br><br>
   <i>Programming Questions and Solutions &darr;</i>
      <br><br>
       <a href="/cplusplus/quiz_strings_arrays.php">Strings and Arrays</a>
       <br><br>
       <a href="/cplusplus/quiz_linkedlist.php">Linked List</a>
       <br><br>
       <a href="/cplusplus/quiz_recursion.php">Recursion</a>
       <br><br>
       <a href="/cplusplus/quiz_bit_manipulation.php">Bit Manipulation</a> 
       <br><br>
       <a href="/cplusplus/smallprograms.php">Small Programs (string, memory functions etc.)</a>
       <br><br>
       <a href="/cplusplus/quiz_math_probability.php">Math & Probability</a>
       <br><br>
       <a href="/cplusplus/quiz_multithreading.php">Multithreading</a>
       <br><br>
       <a href="/cplusplus/google_interview_questions.php">140 Questions by Google</a> 
       <br><br>
       <br><br>
   <a href="/Qt/Qt5_Creating_QtQuick2_QML_Application_Animation_A.php">Qt 5 EXPRESS...</a>
   <br><br>
   <a href="/Win32API/Win32API_DLL.php">Win32 DLL ...</a>
   <br><br>
   <a href="/cplusplus/cppNews.php">Articles On C++</a> 
   <br><br>
   <a href="/cplusplus/C11/C11_initializer_list.php">What's new in C++11...</a> 
   <br><br>
   <a href="/cplusplus/C11/1_C11_creating_thread.php">C++11 Threads EXPRESS...</a> 
   <br><br>
   <a href="/OpenCV/opencv_3_tutorial_imgproc_gausian_median_blur_bilateral_filter_image_smoothing.php">OpenCV...</a> 
<br />

  		
  	    </div>
          </div>
        </div>
      </div> <!-- / section -->
    </div>
  </div>

  <br>
<br>
<br>
<br>

<div class="custom-disqus">
<!-- Disqus -->
<!-- Disqus disabled Oct 17, 2016 
<div id="disqus_thread"></div>
<script>
    /**
     *  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
     *  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables
     */
     
    var disqus_config = function () {
        this.page.url = window.location.href;
        this.page.identifier = document.title;
    };
    
    (function() {  // REQUIRED CONFIGURATION VARIABLE: EDIT THE SHORTNAME BELOW
        var d = document, s = d.createElement('script');
        
        s.src = '//bogotobogocom.disqus.com/embed.js';  // IMPORTANT: Replace EXAMPLE with your forum shortname!
        
        s.setAttribute('data-timestamp', +new Date());
        (d.head || d.body).appendChild(s);
    })();
     
</script>
<noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript" rel="nofollow">comments powered by Disqus.</a></noscript>
---- Disable -->
<!-- Disqus ends here -->
</div>

<br>
<br>
<br>
<br>


<!-- footer starts here -->
<footer id="footer">
  <div class="container">
    <div class="row">
      <div class="col-md-4 widget">
        <h3 class="widget-title">Contact</h3>
        <div class="widget-body">
          <p>BogoToBogo<br>
            <a href="mailto:#">contactus@bogotobogo.com</a><br>
          </p>  
        </div>
      </div>

      <div class="col-md-4 widget">
        <h3 class="widget-title">Follow Bogotobogo</h3>
        <div class="widget-body">

          <!--
          <ul>
            <li><a href="https://www.facebook.com/KHongSanFrancisco" target="_unknown"><i class="fa fa-facebook"></i></a></li>
            <li><a href="https://twitter.com/KHongTwit" target="_unknown"><i class="fa fa-twitter"></i></a></li>
            <li><a href="https://plus.google.com/u/0/+KHongSanFrancisco/posts" target="_unknown"><i class="fa fa-google-plus"></i> </a></li>
          </ul>
          -->
             <h3>
             <a href="https://www.facebook.com/KHongSanFrancisco" target="_unknown"><i class="fa fa-facebook"></i></a> 
             <a href="https://twitter.com/KHongTwit" target="_unknown"><i class="fa fa-twitter"></i></a> 
             <a href="https://plus.google.com/u/0/+KHongSanFrancisco/posts" target="_unknown"><i class="fa fa-google-plus"></i> </a> 
             </h3>

        </div>
      </div>

      <div class="col-md-4 widget">
        <h3 class="widget-title"><a href="/about_us.php">About Us</a></h3>
        <div class="widget-body">
            <a href="mailto:#">contactus@bogotobogo.com</a><br>
            <br>
            Golden Gate Ave, San Francisco, CA 94115
          </p>  
        </div>
      </div>

    </div> 
  </div>
</footer>

<footer id="underfooter">
  <div class="container">
    <div class="row">
      
      <div class="col-md-6 widget">
        <div class="widget-body">
          <p>Golden Gate Ave, San Francisco, CA 94115 </p>
        </div>
      </div>

      <div class="col-md-6 widget">
        <div class="widget-body">
          <p class="text-right">
            Copyright &copy; 2016, bogotobogo<br> 
            Design: <a href="http://www.bogotobogo.com" rel="designer">Web Master</a> </p>
        </div>
      </div>

    </div>
  </div>
</footer>

  <!-- JavaScript libs are placed at the end of the document so the pages load faster -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
     src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
</script>
<!-- D3.js -->
<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>

<!-- Google Analytics -->
<script type="text/javascript">
    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-12016988-2']);
    _gaq.push(['_trackPageview']);
    
    (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
    })();
</script>

<!-- Google Analytics --> 
<!-- We need this for Analytics -->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-43017326-1', 'bogotobogo.com');
  ga('send', 'pageview');

</script>
  
<!-- Google+ -->
<script type="text/javascript" src="http://apis.google.com/js/plusone.js"></script>

</body>
</html>

