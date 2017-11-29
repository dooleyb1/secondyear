<!DOCTYPE html>
<head>
  <title>C++ Tutorial: Sockets - Server & Client - 2017</title>
  <meta content="C++ Tutorial: Sockets, Server & Client example, Internet Address Conversion, Network byte order, internet daemon, xinetd, inetd, network sniffing, tcpdump, wget, raw socket sniffer, libpcap, pcap.h, Asio, Asynchronous tcp/ip" name="description" />
  <meta content="C++ Tutorial, Sockets, Server & Client example, Internet Address Conversion, Network byte order, internet daemon, xinetd, inetd, network sniffing, tcpdump, wget, raw socket sniffer, libpcap, pcap.h, blocking sockets vs Non-blocking sockets, select(), error detection, parity bit, checksum, crc" name="keywords" />
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
            <h2 class="section-title">Sockets - Server & Client - 2017        <g:plusone></g:plusone></h2>
            <div class="icon-image">
                 <img src="/cplusplus/images/cplusplus_icon.png" alt="cplusplus_icon.png"/>
            </div>
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
<div class="subtitle" id="Socket">Socket</div>
<br/>
<img alt="socket_port.png" src="images/socket/socket_port.png"/>

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





<div class="subtitle_2nd" id="TypicalApps">Server/Client Applications</div>
<p>The basic mechanisms of client-server setup are:</p>
<ol>
<li>A client app send a request to a server app.</li>
<li>The server app returns a reply.</li>
<li>Some of the basic data communications between client and server are:
   <ol>
<li>File transfer - sends name and gets a file.</li>
<li>Web page - sends url and gets a page.</li>
<li>Echo - sends a message and gets it back.</li>
</ol>
</li>
</ol>
<br/>
<br/>
<div class="subtitle_2nd" id="Server_Socket">Server Socket</div>
<ol>
<li>create a <strong>socket</strong> - Get the file descriptor!</li>
<li><strong>bind</strong> to an address -What port am I on? </li>
<li><strong>listen</strong> on a port, and wait for a connection to be established.</li>
<li><strong>accept</strong> the connection from a client.</li>
<li><strong>send/recv</strong> - the same way we read and write for a file.</li>
<li><strong>shutdown</strong> to end read/write.</li>
<li><strong>close</strong> to releases data.</li>
</ol>
<br/>
<div class="subtitle_2nd" id="Client_Socket">Client Socket</div>
<ol>
<li>create a <strong>socket</strong>.</li>
<li><strong>bind*</strong> - this is probably be unnecessary because you're the client, not the server. </li>
<li><strong>connect</strong> to a server.</li>
<li><strong>send/recv</strong> - repeat until we have or receive data</li>
<li><strong>shutdown</strong> to end read/write.</li>
<li><strong>close</strong> to releases data.</li>
</ol>
<br/>
<br/>

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





<br/>
<br/>
<br/>
<div class="subtitle_2nd" id="SocketTut">Socket and network programming links</div>
<p>For socket programming with <b>Boost.Asio</b>, please visit:</p>
<ol>
<li><a href="http://www.bogotobogo.com/cplusplus/Boost/boost_AsynchIO_asio_tcpip_socket_server_client_timer_A.php" target="_blank">Boost.Asio - 1. Blocking and non-blocking wait with timers</a>
</li>
<li><a href="http://www.bogotobogo.com/cplusplus/Boost/boost_AsynchIO_asio_tcpip_socket_server_client_timer_bind_handler_member_function_B.php" target="_blank">Boost.Asio - 2. Binding arguments to a callback handler member function</a>
</li>
<li><a href="http://www.bogotobogo.com/cplusplus/Boost/boost_AsynchIO_asio_tcpip_socket_server_client_timer_bind_handler_multithreading_synchronizing_C.php" target="_blank">Boost.Asio - 3. Multithreading, synchronizing, and handler</a>
</li>
<li><a href="http://www.bogotobogo.com/cplusplus/Boost/boost_AsynchIO_asio_tcpip_socket_server_client_timer_bind_handler_multithreading_synchronizing_network_D.php" target="_blank">Boost.Asio - 4. TCP Socket Programming</a>
</li>
</ol>
<p>For socket programming with Qt, please visit <br/>
<a href="/cplusplus/sockets_server_client_QT.php" target="_blank">http://www.bogotobogo.com/cplusplus/sockets_server_client_QT.php</a>.</p>
<p>Qt 5 Tutorials:</p>
<ol>
<li><a href="/Qt/Qt5_QHttp_Downloading_Files_Network_Programming.php">Qt QHttp - Downloading Files</a> </li>
<li><a href="/Qt/Qt5_Downloading_Files_QNetworkAccessManager_QNetworkRequest.php">Qt 5  QNetworkAccessManager and QNetworkRequest - Downloading Files</a> </li>
<li><a href="/Qt/Qt5_QTcpSocket.php">Qt 5 QTcpSocket</a> </li>
<li><a href="/Qt/Qt5_QTcpSocket_Signals_Slots.php">Qt 5 QTcpSocket with Signals and Slots</a> </li>
<li><a href="/Qt/Qt5_QTcpServer_Client_Server.php">Qt 5 QTcpServer - Client and Server</a> </li>
<li><a href="/Qt/Qt5_QTcpServer_Multithreaded_Client_Server.php">Qt 5 QTcpServer - Client and Server using MultiThreading</a> </li>
<li><a href="/Qt/Qt5_QTcpServer_QThreadPool_Multithreaded_Client_Server.php">Qt 5 QTcpServer - Client and Server using QThreadPool</a> </li>
</ol>
<br/>
<br/>

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





<br/>
<br/>
<div class="subtitle_2nd" id="Socket_summary">Socket - summary</div>
<p>Here is the summary of key concepts:</p>
<ol>
<li>Socket is a way of speaking to other programs using standard <strong>file descriptors</strong>.</li>
<li>Where do we get the file descriptor for network communication? <br/>
  Well, we make a call to the <strong>socket()</strong> system routine.<br/>
  After the socket() returns the socket descriptor, we start communicate through it using the specialized <strong>send()/recv()</strong> socket API calls.</li>
<li>A TCP socket is an <strong>endpoint instance</strong></li>
<li>A TCP socket is <strong>not a connection</strong>, it is the <strong>endpoint</strong> of a specific connection.</li>
<li>A TCP <strong>connection</strong> is defined by <strong>two endpoints</strong> aka sockets.</li>
<li>The purpose of <strong>ports</strong> is to <strong>differentiate multiple endpoints</strong> on a given network address. </li>
<li>The port numbers are encoded in the transport protocol packet header, and they can be readily interpreted not only by the sending and receiving computers, but also by other components of the networking infrastructure. In particular, firewalls are commonly configured to differentiate between packets based on their source or destination <strong>port numbers</strong> as in <strong>port forwarding</strong>.</li>
<li>It is the <strong>socket pair</strong> (the <strong>4-tuple</strong> consisting of the client IP address, client port number, server IP address, and server port number) that specifies the two endpoints that uniquely identifies each <strong>TCP connection</strong> in an internet. </li>
<li>Only <strong>one process</strong> may bind to a specific <strong>IP address</strong> and <strong>port</strong> combination using the <strong>same transport protocol</strong>. Otherwise, we'll have <strong>port conflicts</strong>, where multiple programs attempt to bind to the same port numbers on the same IP address using the same protocol.</li>
</ol>
<p>To connect to another machine, we need a <strong>socket</strong> connection.<p>
<p> What's a connection? </p>
</p>A <strong>relationship</strong> between two machines, where <strong>two pieces of software know about each other</strong>. Those two pieces of software know how to communicate with each other. In other words, they know how to send <strong>bits</strong> to each other.<br/>
A socket connection means the two machines have information about each other, including <strong>network location (IP address)</strong> and <strong>TCP port</strong>. (If we can use anology, IP address is the <strong>phone number</strong> and the TCP port is the <strong>extension</strong>). <br/>
<p>A socket is an object similar to a file that allows a program to accept incoming
connections, make outgoing connections, and send and receive data. <strong>Before two
machines can communicate, both must create a socket object</strong>.</p>
<p>A socket is a <strong>resource</strong> assigned to the server process. The server creates it using the system call <strong>socket()</strong>, and it can't be shared with other processes.</p>
<br/>
<br/>

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





<br/>
<br/>
<br/>
<div class="subtitle_2nd" id="tcp_vs_udp">TCP vs UDP</div>
<p>There are several different types of socket that determine the structure of the transport layer. The most common types are <strong>stream</strong> sockets and <strong>datagram</strong> sockets.</p>
<br>
<br>
<img alt="tcp_udp_headers.jpg" src="images/socket/tcp_udp_headers.jpg"/>
<br>
<br/>
<table border="2" cellpadding="5">
<tr>
<th>TCP (Streams)</th>
<th>UDP (Datagrams)</th>
</tr>
<tr>
<td align="left">Connections</td>
<td align="left">Connectionless sockets<br/>
 We don't have to maintain an open connection as we do with stream sockets. <br/>
 We just build a packet, put an IP header on it with destination information, and send it out.<br/>
 No connection needed: datagram sockets also use IP for routing, but they don't use TCP<br/>
<br/>
*note: can be connect()'d if we really want.</td>
</tr>
<tr>
<td align="left">SOCK_STREAM</td>
<td align="left">SOCK_DGRAM</td>
</tr>
<tr>
<td align="left">If we output two items into the socket in the order "A, B", they will arrive in the order "A, B" at the opposite end. <br/>
They will also be error-free.</td>
<td align="left">If we send a datagram, it may arrive. <br/>
But it may arrive out of order.<br/>
 If it arrives, however, the data within the packet will be error-free.</td>
</tr>
<tr>
<td></td>
<td align="left">Why would we use an unreliable protocol? <br/>
Speed! We just ignore the dropped packets.</td>
</tr>
<tr>
<td align="left">Arbitrary length content</td>
<td align="left">Limited message size</td>
</tr>
<tr>
<td align="left">Flow control matches sender to receiver</td>
<td align="left">Can send regardless of receiver state</td>
</tr>
<tr>
<td align="left">Congestion control matches sender to network</td>
<td align="left">Can send regardless of network state</td>
</tr>
<tr>
<td align="left">http, telnet</td>
<td align="left">tftp (trivial file transfer protocol), dhcpcd (a DHCP client), multiplayer games, streaming audio, video conferencing<br/>
<br/>
 *note: They use complementary protocol on top of UDP to get more reliability
 </td>
</tr>
</table>
<br/>
<ol>
<li><strong>Stream Sockets</strong><br/>
	Stream sockets provide <strong>reliable two-way</strong> communication similar to when we call someone on the phone. One side initiates the connection to the other, and after the connection is established, either side can communicate to the other.<br/>
In addition, there is immediate confirmation that what we said actually reached its destination. <br/>
Stream sockets use a <strong>Transmission Control Protocol (TCP)</strong>, which exists on the transport layer of the Open Systems Interconnection (OSI) model. The data is usually transmitted in packets. TCP is designed so that the packets of data will arrive without errors and in sequence. <br/>
Webservers, mail servers, and their respective client applications all use TCP and stream socket to communicate.</li><br/>
<li><strong>Datagram Sockets</strong><br/>
	Communicating with a datagram socket is more like mailing a letter than making a phone call. The connection is <strong>one-way</strong> only and <strong>unreliable</strong>. <br/>
If we mail several letters, we can't be sure that they arrive in the same order, or even that they reached their destination at all. Datagram sockets use <strong>User Datagram Protocol (UDP)</strong>. Actually, it's not a real connection, just a basic method for sending data from one point to another.<br/>
Datagram sockets and UDP are commonly used in networked games and streaming media. <br/>
Though in this section, we mainly put focus on applications that maintain connections to their clients, using connection-oriented TCP, there are cases where the overhead of establishing and maintaining a socket connection is unnecessary.<br/>
For example, just to get the data, a process of creating a socket, making a connection, reading a single response, and closing the connection, is just too much. In this case, we use UDP. <br/>
Services provided by UDP are typically used where a client needs to make a short query of a server and expects a single short response. To access a service from UDP, we need to use the UDP specific system calls, <strong>sendto()</strong> and <strong>recvfrom()</strong> instead of <strong>read()</strong> and <strong>write()</strong> on the socket.
    <p>UDP is used by app that doesn't want reliability or bytestreams.</p>
<ol>
<li>Voice-over-ip (unreliable) such as conference call. (visit <a href="http://www.bogotobogo.com/VideoStreaming/VoIP.php" target="_blank">VoIP</a>)</li>
<li>DNS, RPC (message-oriented)</li>
<li>DHCP (bootstrapping)</li>
</ol>
</li>
</ol>
<br/>

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





<br/>
<br/>
<div class="subtitle" id="client_server">Client/Server</div>
<p>The <strong>client-server model</strong> distinguishes between applications as well as devices. <strong>Network clients make requests to a server by sending messages</strong>, and <strong>servers respond to their clients by acting on each request and returning results</strong>.<p>
<p>For example, let's talk about <strong>telnet</strong>. <br/>
When we connect to a remote host on port 23 with telnet (the client), a program on that host (called <strong>telnetd</strong>, the server) springs to life. It handles the incoming telnet connection, sets us up with a login prompt, etc.</p>
<p>One server generally supports numerous clients, and multiple servers can be networked together in a pool to handle the increased processing load as the number of clients grows.</p>
<p>
Some of the most popular applications on the Internet follow the client-server model including email, FTP and Web services. Each of these clients features a user interface and a client application that allows the user to connect to servers. In the case of email and FTP, users enter a computer name (or an IP address) into the interface to set up connections to the server.</p>
<p>The steps to establish a socket on the <strong>server</strong> side are:
<ol>
<li>Create a socket with the <strong>socket()</strong> system call. </li>
<li>The server process gives the socket a name. In linux file system, local sockets are given a filename, under /tmp or /usr/tmp directory. For network sockets, the filename will be a service identifier, port number, to which the clients can make connection. This identifier allows to route incoming connections (which has that the port number) to connect server process. A socket is named using <strong>bind()</strong> system call. </li>
<li>The server process then waits for a client to connect to the named socket, which is basically listening for connections with the <strong>listen()</strong> system call. If there are more than one client are trying to make connections, the <strong>listen()</strong> system call make a queue.<br/>
The machine receiving the connection (the server) must bind its socket object to a
known port number. A port is a 16-bit number in the range 0-65535 that's managed by
the operating system and used by clients to uniquely identify servers. Ports 0-1023 are
reserved by the system and used by common network protocols.</li>
<li>
Accept a connection with the <strong>accept()</strong> system call. At <strong>accept()</strong>, a new socket is created that is distinct from the named socket. This new socket is used solely for communication with this particular client.<br/>
For TCP servers, the socket object used to receive connections is not the same socket
used to perform subsequent communication with the client. In particular, the <strong>accept()</strong>
system call returns a new socket object that's actually used for the connection.
This allows a server to manage connections from a large number of clients simultaneously.
 </li>
<li>Send and receive data.
</li>
<li>The named socket remains for further connections from other clients. A typical web server can take advantage of multiple connections. In other words, it can serve pages to many clients at once. But for a simple server, further clients wait on the listen queue until the server is ready again.</li>
</ol>
<p>The steps to establish a socket on the <strong>client</strong> side are:
<ol>
<li>Create a socket with the <strong>socket()</strong> system call. </li>
<li>Connect the socket to the address of the server using the <strong>connect()</strong> system call.</li>
<li>Send and receive data. There are a number of ways to do this, but the simplest is to use the <strong>read()</strong> and <strong>write()</strong> system calls.</li>
</ol>
</p>
<br/>
<br/>
<img alt="Socket_API.png" src="images/socket/Socket_API.png"/>
<br/>
<br/>
<br/>
<p><strong>TCP communication</strong></p>
<img alt="TCP_IP_socket_diagram.png" src="images/socket/TCP_IP_socket_diagram.png"/>
<br/>
<br/>
<br/>
<p><strong>UDP communication</strong> - clients and
servers don't establish a <strong>connection</strong> with each other</p>
<img alt="UDP_socket_diagram.png" src="images/socket/UDP_socket_diagram.png"/>
<br/>
<p>*call block, go to <a href="#block_vs_non_blocking" target="_blank">Blocking socket vs non-blocking socket </a>.</p>
<br/>
<br/>

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





<br/>
<br/>
<br/>
<br/>
<br/>
<div class="subtitle_2nd" id="socket_functions">Socket Functions</div>
<p><strong>Sockets</strong>, in C, behaves like files because they use file descriptors to identify themselves. Sockets behave so much like files that we can use the <strong>read()</strong> and <strong>write()</strong> to receive and send data using <strong>socket file descriptors</strong>.</p> There are several functions, however, specifically designed to handle sockets. These functions have their prototypes defined in <strong>/usr/include/sys/sockets.h</strong>.</p>
<ol>
<li><p><strong>int socket(int domain, int type, int protocol)</strong></p>
<p>Used to create a new socket, returns a file descriptor for the socket or -1 on error.<br/>
 It takes three parameters: <br/>
<ol>
<li>domain: the protocol family of socket being requested</li>
<li>type: the type of socket within that family</li>
<li>and the protocol.</li>
</ol>
<br/>
 The parameters allow us to say what kind of socket we want (IPv4/IPv6,
 stream/datagram(TCP/UDP)).<br/>
<ol>
<li>The protocol family should be <strong>AF_INET</strong> or <strong>AF_INET6</strong></li>
<li>and the protocol type for these two families is <br/>
  either <strong>SOCK_STREAM</strong> for TCP/IP or <strong>SOCK_DGRAM</strong> for UDP/IP.</li>
<li>The protocol should usually be set to zero to indicate that the default protocol should be used.</li>
</ol>
<br/>
</p>
</li>
<li><p><strong>int bind(int fd, struct sockaddr *local_addr, socklen_t addr_length) </strong></p>
<p>Once we have a socket, we might have to associate that socket with a port on our local machine.<br/>
The port number is used by the kernel to match an incoming packet to a certain process's socket descriptor.<br/>
A server will call <strong>bind()</strong> with the address of the local host and the port on which it will listen for connections.<br/>
It takes file descriptor (previously established socket), a pointer to (the address of) a structure containing the details of the address to bind to, the value <strong>INADDR_ANY</strong> is typically used for this, and the length of the address structure.<br/>
The particular structure that needs to be used will depend on the protocol, which is why it is passed by the pointer.<br/>
So, this <strong>bind()</strong> call will bind the socket to the current IP address on port, portno<br/>
 Returns 0 on success and -1 on error.</p>
</li>
<li><p><strong>int listen(int fd, int backlog_queue_size) </strong></p>
<p>Once a server has been bound to an address, the server can then call <strong>listen()</strong> on the socket.<br/>
The parameters to this call are the socket (fd) and the maximum number of queued connections requests up to <strong>backlog_queue_size</strong>.<br/>
 Returns 0 on success and -1 on error.</p>
</li>
<li><p><strong>int accept(int fd, struct sockaddr *remote_host, socklen_t addr_length) </strong></p>
<p>Accepts an incoming connection on a bound socket. The address information from the remote host is written into the <strong>remote_host</strong> structure and the actual size of the address structure is written into <strong>*addr_length</strong>.<br/>
In other words, this <strong>accept()</strong> function will write the connecting client's address info into the address structure.<br/>
Then, returns a new socket file descriptor for the accepted connection.<br/>
So, the original socket file descriptor can continue to be used for accepting new connections while the new socket file descriptor is used for communicating with the connected client.<br/>
 This function returns a new socket file descriptor to identify the connected socket or -1 on error.</p>
<p>Here is the description from the man page:<br/>
"It extracts the first connection request on the queue of pending connections for the listening socket, <b>sockfd</b>, creates a new connected socket, and returns a new file descriptor referring to that socket. The newly created socket is not in the listening state. The original socket sockfd is unaffected by this call".</p>
<p>If no pending connections are present on the queue, and the socket is not marked as nonblocking, <font color="blue">accept() blocks the caller until a connection is present</font>.</p>
</li>
<li><p><strong>int connect(int fd, struct sockaddr *remote_host, socklen_t addr_length) </strong></p>
<p>Connects a socket (described by file descriptor <strong>fd</strong>) to a remote host. <br/>
Returns 0 on success and -1 on error.</p>
<p>This is a <font color="blue">blocking call</font>. That's because when we issue a call to connect(), our program doesn't regain control until either the connection is made, or an error occurs. For example, let's say that we're writing a web browser. We try to connect to a web server, but the server isn't responding. So, we now want the connect() API to stop trying to connect by clicking a stop button. But that can't be done. It waits for a return which could be 0 on success or -1 on error.</p>
</li>
<li><p><strong>int send(int fd, void *buffer, size_t n, int flags) </strong></p>
<p>Sends n bytes from <strong>*buffer</strong> to <strong>socket fd</strong>.<br/>
 Returns the number of bytes sent or -1 on error.</p>
</li>
<li><p><strong>int receive(int fd, void *buffer, size_t n, int flags) </strong></p>
<p>Reveives n bytes from <strong>socket fd</strong> into <strong>*buffer</strong>.<br/>
 Returns the number of bytes received or -1 on error.</p>
<p>This is another <font color="blue">blocking call</font>. In other words, when we call <b>recv()</b> to read from a stream, control isn't returned to our program until at least one byte of data is read from the remote site. This process of waiting for data to appear is referred to as <b>blocking</b>. The same is true for the write() and the connect() APIs, etc. When we run those blocking APIs, the connection "blocks" until the operation is complete.</p>
</li>
</ol>
<br/>
<br/>
<br/>
<p>The following server code listens for TCP connections on port 20001. When a client connects, it sends the message "Hello world!", and then it receives data from client.</p>
<br/>
<p><strong>server.c</strong></p>
<pre>
/* The port number is passed as an argument */
#include &lt;stdio.h&gt;
#include &lt;stdlib.h&gt;
#include &lt;string.h&gt;
#include &lt;unistd.h&gt;
#include &lt;sys/types.h&gt; 
#include &lt;sys/socket.h&gt;
#include &lt;netinet/in.h&gt;

void error(const char *msg)
{
    perror(msg);
    exit(1);
}

int main(int argc, char *argv[])
{
     int sockfd, newsockfd, portno;
     socklen_t clilen;
     char buffer[256];
     struct <font color="blue">sockaddr_in</font> serv_addr, cli_addr;
     int n;
     if (argc &lt; 2) {
         fprintf(stderr,"ERROR, no port provided\n");
         exit(1);
     }
     // create a socket
     // socket(int <font color="blue">domain</font>, int <font color="blue">type</font>, int <font color="blue">protocol</font>)
     <font color="orange">sockfd</font> = <font color="red"> socket</font>(AF_INET, SOCK_STREAM, 0);
     if (sockfd &lt; 0) 
        error("ERROR opening socket");

     // clear address structure
     bzero((char *) &amp;serv;_addr, sizeof(serv_addr));

     portno = atoi(argv[1]);

     /* setup the host_addr structure for use in bind call */
     // server byte order
     serv_addr.sin_family = AF_INET;  

     // automatically be filled with current host's IP address
     serv_addr.sin_addr.s_addr = INADDR_ANY;  

     // convert short integer value for port must be converted into network byte order
     serv_addr.sin_port = htons(portno);

     // bind(int fd, struct sockaddr *local_addr, socklen_t addr_length)
     // bind() passes file descriptor, the address structure, 
     // and the length of the address structure
     // This bind() call will bind  the socket to the current IP address on port, portno
     if (<font color="red">bind</font>(<font color="orange">sockfd</font>, (struct sockaddr *) &amp;serv;_addr,
              sizeof(serv_addr)) &lt; 0) 
              error("ERROR on binding");

     // This listen() call tells the socket to listen to the incoming connections.
     // The listen() function places all incoming connection into a backlog queue
     // until accept() call accepts the connection.
     // Here, we set the maximum size for the backlog queue to 5.
     <font color="red">listen</font>(<font color="orange">sockfd</font>,5);

     // The accept() call actually accepts an incoming connection
     clilen = sizeof(cli_addr);

     // This accept() function will write the connecting client's address info 
     // into the the address structure and the size of that structure is clilen.
     // The accept() returns a new socket file descriptor for the accepted connection.
     // So, the original socket file descriptor can continue to be used 
     // for accepting new connections while the new socker file descriptor is used for
     // communicating with the connected client.
     <font color="maroon">newsockfd</font> = <font color="red">accept</font>(<font color="orange">sockfd</font>, 
                 (struct sockaddr *) &amp;cli;_addr, &amp;clilen;);
     if (newsockfd &lt; 0) 
          error("ERROR on accept");

     printf("server: got connection from %s port %d\n",
            inet_ntoa(cli_addr.sin_addr), ntohs(cli_addr.sin_port));


     // This send() function sends the 13 bytes of the string to the new socket
     <font color="red">send</font>(<font color="maroon">newsockfd</font>, "Hello, world!\n", 13, 0);

     bzero(buffer,256);

     n = <font color="red">read</font>(<font color="maroon">newsockfd</font>,buffer,255);
     if (n &lt; 0) error("ERROR reading from socket");
     printf("Here is the message: %s\n",buffer);

     close(<font color="maroon">newsockfd</font>);
     close(<font color="orange">sockfd</font>);
     return 0; 
}
</pre>
<br/>
<p>When a socket is created with the <strong>socket()</strong> function, the <strong>domain</strong>, <strong>type</strong>, and <strong>protocol</strong> of the socket must be specified. The domain refers to the protocol family of the socket.</p>
<p><pre>
/* Address families.  */
#define AF_UNSPEC       PF_UNSPEC
#define AF_LOCAL        PF_LOCAL
#define AF_UNIX         PF_UNIX
#define AF_FILE         PF_FILE
#define <font color="red">AF_INET</font>         PF_INET
#define AF_AX25         PF_AX25
#define AF_IPX          PF_IPX
#define AF_APPLETALK    PF_APPLETALK
#define AF_NETROM       PF_NETROM
#define AF_BRIDGE       PF_BRIDGE
#define AF_ATMPVC       PF_ATMPVC
#define AF_X25          PF_X25
#define AF_INET6        PF_INET6
#define AF_ROSE         PF_ROSE
#define AF_DECnet       PF_DECnet
#define AF_NETBEUI      PF_NETBEUI
#define AF_SECURITY     PF_SECURITY
#define AF_KEY          PF_KEY
#define AF_NETLINK      PF_NETLINK
#define AF_ROUTE        PF_ROUTE
#define AF_PACKET       PF_PACKET
#define AF_ASH          PF_ASH
#define AF_ECONET       PF_ECONET
#define AF_ATMSVC       PF_ATMSVC
#define AF_SNA          PF_SNA
#define AF_IRDA         PF_IRDA
#define AF_PPPOX        PF_PPPOX
#define AF_WANPIPE      PF_WANPIPE
#define AF_BLUETOOTH    PF_BLUETOOTH
#define AF_MAX          PF_MAX
</pre>
</p>
<p>A socket can be used to communicate using a variety of protocols, from the standard Internet protocol used when we browse the Web. These families are defined in <strong>bits/socket.h</strong>, which is automatically included from <strong>sys/socket.h</strong>.</p>
<p>There are several types of sockets: stream sockets and datagram sockets are the most commonly used. The types of sockets are also defined in <strong>/usr/include/bits/socket.h</strong>
<pre>
/* Types of sockets.  */
enum __socket_type
{
  SOCK_STREAM = 1,              /* Sequenced, reliable, connection-based
                                   byte streams.  */
#define SOCK_STREAM SOCK_STREAM
  SOCK_DGRAM = 2,               /* Connectionless, unreliable datagrams
                                   of fixed maximum length.  */
#define SOCK_DGRAM SOCK_DGRAM
  SOCK_RAW = 3,                 /* Raw protocol interface.  */
#define SOCK_RAW SOCK_RAW
  SOCK_RDM = 4,                 /* Reliably-delivered messages.  */
#define SOCK_RDM SOCK_RDM
  SOCK_SEQPACKET = 5,           /* Sequenced, reliable, connection-based,
                                   datagrams of fixed maximum length.  */
#define SOCK_SEQPACKET SOCK_SEQPACKET
  SOCK_PACKET = 10              /* Linux specific way of getting packets
                                   at the dev level.  For writing rarp and
                                   other similar things on the user level. */
#define SOCK_PACKET SOCK_PACKET
};
</pre>
</p>
<p>The 3rd argument for the <strong>socket()</strong> function is the protocol, which should always be 0. The specification allows for multiple protocols within a protocol family, so this argument is used to select on of the protocols from the family. 

<p><strong>/usr/include/bits/socket.h</strong>
<pre>
/* Protocol families.  */
#define PF_UNSPEC       0       /* Unspecified.  */
#define PF_LOCAL        1       /* Local to host (pipes and file-domain).  */
#define PF_UNIX         PF_LOCAL /* Old BSD name for PF_LOCAL.  */
#define PF_FILE         PF_LOCAL /* Another non-standard name for PF_LOCAL.  */
#define PF_INET         2       /* IP protocol family.  */
#define PF_AX25         3       /* Amateur Radio AX.25.  */
#define PF_IPX          4       /* Novell Internet Protocol.  */
#define PF_APPLETALK    5       /* Appletalk DDP.  */
#define PF_NETROM       6       /* Amateur radio NetROM.  */
#define PF_BRIDGE       7       /* Multiprotocol bridge.  */
#define PF_ATMPVC       8       /* ATM PVCs.  */
#define PF_X25          9       /* Reserved for X.25 project.  */
#define PF_INET6        10      /* IP version 6.  */
#define PF_ROSE         11      /* Amateur Radio X.25 PLP.  */
#define PF_DECnet       12      /* Reserved for DECnet project.  */
#define PF_NETBEUI      13      /* Reserved for 802.2LLC project.  */
#define PF_SECURITY     14      /* Security callback pseudo AF.  */
#define PF_KEY          15      /* PF_KEY key management API.  */
#define PF_NETLINK      16
#define PF_ROUTE        PF_NETLINK /* Alias to emulate 4.4BSD.  */
#define PF_PACKET       17      /* Packet family.  */
#define PF_ASH          18      /* Ash.  */
#define PF_ECONET       19      /* Acorn Econet.  */
#define PF_ATMSVC       20      /* ATM SVCs.  */
#define PF_SNA          22      /* Linux SNA Project */
#define PF_IRDA         23      /* IRDA sockets.  */
#define PF_PPPOX        24      /* PPPoX sockets.  */
#define PF_WANPIPE      25      /* Wanpipe API sockets.  */
</pre>
</p>
However, in practice, most protocol families only have one protocol, which means this should usually be set for 0; the first and only protocol in the enumeration of the family. </p>
<pre>
serv_addr.sin_family = AF_INET;
serv_addr.sin_addr.s_addr = INADDR_ANY;
serv_addr.sin_port = htons(portno);
</pre>
<ol>
<li><strong>sin_family</strong> = specifies the address family, usually the constant <strong>AF_INET</strong></li>
<li><strong>sin_addr</strong> = holds the IP address returned by <strong>inet_addr()</strong> to be used in the socket connection.</li>
<li><strong>sin_port</strong> = specifies the port number and must be used with <strong>htons()</strong> function that converts the <strong>host byte order</strong> to <strong>network byte order</strong> so that  it can be transmitted and routed properly when opening the socket connection. The reason for this is that computers and network protocols order their bytes in a non-compatible fashion.</li>
</ol>
<br/>
<p>The lines above set up the <strong>serv_addr</strong> structure for use in the <strong>bind</strong> call.
<pre>
/* Structure describing a generic socket address. */
struct <font color="red">sockaddr</font>
{
    __SOCKADDR_COMMON (sa_);    /* Common data: address family and length. */
    char sa_data[14];           /* Address data. */
};
</pre>
 The address family is <strong>AF_INET</strong>, since we are using <strong>IPv4</strong> and the <strong>sockaddr_in</strong> structure. The short integer value for port must be converted into network byte order, so the <strong>htons()</strong> (Host-to-Network Short) function is used.</p>
<p>The <strong>bind()</strong> call passes the socket file descriptor, the address structure, and the length of the address structure. This call will bind the socket to the current IP address on port 20001.</p>
<pre>
if (<font color="red">bind</font>(sockfd, (struct sockaddr *) &amp;serv;_addr,
           sizeof(serv_addr)) &lt; 0) error("ERROR on binding");
</pre>
<p>The <strong>listen()</strong> call tells the socket to listen for incoming connections, and a subsequent <strong>accept()</strong> call actually accepts an incoming connection. The <strong>listen()</strong> function places all incoming connections into a backlog queue until an <strong>accept()</strong> call accepts the connections. The last argument to the <strong>listen()</strong> call sets the maximum size for the backlog queue.</p>
<pre>
<font color="red">listen</font>(sockfd,5);
clilen = sizeof(cli_addr);
newsockfd = <font color="red">accept</font>(sockfd, (struct sockaddr *) &amp;cli;_addr, &amp;clilen;);
if (newsockfd &lt; 0) error("ERROR on accept");
</pre>
<p>The final argument of the <strong>accept()</strong> is a pointer to the size of the address structure. This is because the <strong>accept()</strong> function will write the connecting client's address information into the address structure and the size of that structure is <strong>clilen</strong>. The <strong>accept()</strong> function returns a new socket file descriptor for the accepted connection:</p>
<pre>
<font color="blue">newsockfd</font> = <font color="red">accept</font>(sockfd, 
             (struct sockaddr *) &amp;cli;_addr,&amp;clilen;);
</pre>
<p>This way, the original socket file descriptor can continue to be used for accepting new connections, while the new socket file descriptor is used for communicating with the connected client.</p>
<p>The <strong>send()</strong> function sends the 13 bytes of the string <strong>Hello, world\n"</strong> to the new socket that describes the new connection.</p>
<pre>
send(newsockfd, "Hello, world!\n", 13,0);
</pre>
<br/>
<p>To compile, the server.c: </p>
<pre>
gcc -o server server.c</pre>
<p>and to run</p>
<pre>
./server port# 
</pre>
<br/><br/>
<p><strong>client.c</strong></p>
<pre>
#include &lt;stdio.h&gt;
#include &lt;stdlib.h&gt;
#include &lt;unistd.h&gt;
#include &lt;string.h&gt;
#include &lt;sys/types.h&gt;
#include &lt;sys/socket.h&gt;
#include &lt;netinet/in.h&gt;
#include &lt;netdb.h&gt;

void error(const char *msg)
{
    perror(msg);
    exit(0);
}

int main(int argc, char *argv[])
{
    int sockfd, portno, n;
    struct sockaddr_in serv_addr;
    struct hostent *server;

    char buffer[256];
    if (argc &lt; 3) {
       fprintf(stderr,"usage %s hostname port\n", argv[0]);
       exit(0);
    }
    portno = atoi(argv[2]);
    <font color="orange">sockfd</font> = <font color="red">socket</font>(AF_INET, SOCK_STREAM, 0);
    if (sockfd &lt; 0) 
        error("ERROR opening socket");
    server = gethostbyname(argv[1]);
    if (server == NULL) {
        fprintf(stderr,"ERROR, no such host\n");
        exit(0);
    }
    bzero((char *) &amp;serv;_addr, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    bcopy((char *)server-&gt;h_addr, 
         (char *)&amp;serv;_addr.sin_addr.s_addr,
         server-&gt;h_length);
    serv_addr.sin_port = htons(portno);
    if (<font color="red">connect</font>(<font color="orange">sockfd</font>, (struct sockaddr *) &amp;serv;_addr, sizeof(serv_addr)) &lt; 0) 
        error("ERROR connecting");
    printf("Please enter the message: ");
    bzero(buffer,256);
    fgets(buffer,255,stdin);
    n = <font color="red">write</font>(<font color="orange">sockfd</font>, buffer, strlen(buffer));
    if (n &lt; 0) 
         error("ERROR writing to socket");
    bzero(buffer,256);
    n = <font color="red">read</font>(<font color="orange">sockfd</font>, buffer, 255);
    if (n &lt; 0) 
         error("ERROR reading from socket");
    printf("%s\n", buffer);
    close(<font color="orange">sockfd</font>);
    return 0;
}
</pre>
<p>To compile</p>
<pre>
gcc -o client client.c</pre>
<p>and to run</p>
<pre>
./client hostname port# 
</pre>
<br/>
<br/>
<p>First, we run <strong>server.c</strong> as in</p>
<pre>
$ ./server 20001
</pre>
<p>Then, on <strong>client side</strong></p>
<pre>
$ ./client myhostname 20001
Please enter the message: 
</pre>
<p>Then, <strong>server side</strong> has the following message when connected successfully:</p>
<pre>
$ ./server 20001
server: got connection from 127.0.0.1 port 47173
</pre>
<p>Then, on <strong>client side</strong></p>
<pre>
$ ./client myhostname 20001
Please enter the message: Hello from client
</pre>
<p>Then, <strong>server side</strong> has the following message:</p>
<pre>
$ ./server 20001
server: got connection from 127.0.0.1 port 47173
Here is the message: Hello from Client
</pre>
<p><strong>Clent side</strong> gets message (Hello, world!) from the server:</p>
<pre>
$ ./client myhostname 20001
Please enter the message: Hello from Client
Hello, world!
</pre>
<br/>
<p>To run this codes, we don't need two machines. One is enough!</p>
<br/><br/>
<br/><br/>
<div class="subtitle_2nd" id="share_sockets">Sharing between processes - Sockets</div>
<p><a href="http://www.bogotobogo.com/cplusplus/multithreading_ipc.php" target="_blank">
Sharing between processes - Sockets using PThreads</a>
<br/><br/>
<br/>

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





<br/>
<br/><br/>
<div class="subtitle_2nd" id="byte_order">Network Byte Order</div>
<p>The port number and IP address used in the <strong>AF_INET</strong> socket address structure are expected to follow the network byte ordering (big-endian). This is the opposite of x86's little-endian byte ordering, so these values must be converted. There are specialized functions for the conversions, and they are defined in <strong>netinet.h</strong> and <strong>arpa/inet.h</strong>.</p>
<p>"Basically, we want to convert the numbers to Network Byte Order before they go out on the wire, and
convert them to Host Byte Order as they come in off the wire."</p>
<ol>
<li><p><strong>htonl (long value) Host-to-Network Long</strong><br/>
Converts a 32-bit integer from the host's byte order to network byte order.</p>
<li><p><strong>htons (short value) Host-to-Network Short</strong><br/>
Converts a 16-bit integer from the host's byte order to network byte order.</p>
<li><p><strong>ntohl (long value) Network-to-Host Long</strong><br/>
Converts a 32-bit integer from network byte order to the host's byte order.</p>
<li><p><strong>ntohs (short value) Network-to-Host Short</strong><br/>
Converts a 16-bit integer from network byte order to the host's byte order.</p>
<li>For C code for the conversions, please visit<br/><br/>
<a href="http://www.bogotobogo.com/Embedded/Little_endian_big_endian_htons_htonl.php">
<img alt="Little_Big_Endians.png" src="/cplusplus/images/smallprograms/Little_Big_Endians.png" width="100%"><br/>
<img alt="htons_htonl.png" src="/Embedded/images/endian/htons_htonl.png" width="100%">
<br/>
Little Endian/Big Endian &amp; TCP Sockets</img></img></a>.</li>
</li></li></li></li></ol>
<br/><br/>

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





<br/>
<br/>
<br/>
<div class="subtitle_2nd" id="Internet_Address_Conversion">Internet Address Conversion</div>
<ol>
<li><p><strong>inet_aton (char *ascii_addr, struct in_addr *network_addr) ASCII-to-Network</strong><br/>
Converts an ASCII string containing IP address in dotted-number format into an <strong>in_addr</strong>, which only contains a 32-bit integer representing the IP address in network byte order.</p>
<li><p><strong>inet_ntoa (struct in_addr *network_addr) Network to ASCII</strong><br/>
It is passed a pointer to an <strong>in_addr</strong> structure containing an IP address, and the function returns a character pointer to an ASCII string containing the IP address in dotted-number format. This string is held in a statically allocated memory buffer in the function, so it can be accessed until the next call to <strong>inet_ntoa()</strong>, and the string will be overwritten.</p>
</li></li></ol>
<br/>
<br/>
<br/>
<div class="subtitle" id="three_way_handshake">TCP Connection establishment: Three-way-handshake</div>
<p>
To establish a connection, TCP uses a <strong>three-way handshake</strong>. Before a client attempts to connect with a server, the server must first bind to and listen at a port to open it up for connections: this is called a <strong>passive open</strong>. Once the passive open is established, a client may initiate an <strong>active open</strong>. </p>
<p>In other words, before communication begins, TCP establishes a new connection using a three-way handshake. This is because </p>
<ol>
<li>Both sender and receiver must be ready before data transport starts, and they need to agree on set of parameters such as the Maximum Segment Size (MSS).</li>
<li>TCP end points maintain state about communications in both directions, and the handshake allows the state to be created and initialized.</li>
<li>TCP establishes a stream of bytes in both directions, and the three-way handshake allows both streams to be established and acknowledged.</li>
</ol>
<p>To establish a connection, the <strong>three-way handshake</strong> occurs:<br/>
<ol>
<li><strong>SYN</strong>: The active open is performed by the client sending a SYN to the server. </li>
<li><strong>SYN-ACK</strong>: In response, the server replies with a SYN-ACK. </li>
<li><strong>ACK</strong>: Finally, the client sends an ACK back to the server.</li>
</ol>
<img alt="three_way_handshake.png" src="images/socket/three_way_handshake.png" width="100%">
<p>The picture above: from <a href="http://lwn.net/Articles/508865/" target="_blank">TCP Fast Open: expediting web services</a></p>
<p>Here is the sample using Wireshark when I requested a page from apple.com, and then closed it:</p>
<img alt="wireshark_syn_ack_fin.png" src="images/socket/wireshark_syn_ack_fin.png" width="100%">
<br/>
<br/>
<p>It is also possible to terminate the connection by a 3-way handshake, more strictly it's a 2 (FIN/ACK) x 2 (FIN/ACK) handshake:</p>
<ol>
<li>host A sends a <strong>FIN</strong> </li>
<li>host B replies with a <strong>ACK (with data) + FIN</strong> </li>
<li>host A replies with an <strong>ACK</strong></li>
</ol>
<img alt="TCP_DISCONNECT_TEAR_DOWN.png" src="images/socket/TCP_DISCONNECT_TEAR_DOWN.png"/>
<p>picture from <a href="http://en.wikipedia.org/wiki/Transmission_Control_Protocol" target="_blank">wiki</a></p>
<br/>
<br/>

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





<br/>
<br/>
<br/>
<div class="subtitle" id="network_command">Basic Network command</div>
<ol>
<li><strong>ipconfig (internet protocol configuration)</strong> <br/>
A console application that displays all current TCP/IP network configuration values. <br/>
In other words, it is commonly used to identify the addresses information of a computer on a network. <br/>It can show the physical address as well as the IP address.

<br/>It can modify Dynamic Host Configuration Protocol DHCP and Domain Name System DNS settings.<br/><br/>
<img alt="ipconfig.png" src="images/socket/ipconfig.png" width="100%">
</img></li><br/><br/>
<li><strong>nslookup </strong> <br/>
A network administration command-line tool available for many computer operating systems for <br/>querying the Domain Name System (DNS) to obtain domain name or IP address mapping or for any other specific DNS record.<br/><br/>
<img alt="nslookup.png" src="images/socket/nslookup.png" width="100%">
</img></li><br/><br/>
<li><strong>netstat(network statistics) </strong> <br/>
 A command-line tool that displays network connections (both incoming and outgoing), routing tables, and a number of network interface (network interface controller or software-defined network interface) and network protocol statistics.<br/>
Simply put, tt provides useful information about the current TCP/IP settings of a connection.
<br/><br/>
<img alt="netstat_n.png" src="images/socket/netstat_n.png" width="100%">
</img></li><br/><br/>
<li><strong>traceroute</strong> <br/>
 Traceroute is a computer network diagnostic tool for displaying the route (path) and measuring transit delays of packets across an Internet Protocol (IP) network. <strong>Tracert</strong> is a Windows utility program that can used to trace the route taken by data from the router to the destination network. It also shows the number of hops taken during the entire transmission route.
 <br/><br/>
<img alt="traceroute.png" src="images/socket/traceroute.png" width="100%">
<br/><br/>
 Here's how traceroute works. <br/>
 Traceroute <strong>probes successive hops</strong> in order to find the network path between a 
host that's doing the probing and a destination host which might be some remote web server. And, we want to find the path, the 
network path between our computer and the remote host server. <br/>
What traceroute does is it sends a packet 
towards that remote host, only a single 
hop onto the network. And then, causes the network to send a 
message back, or a reply back. 
Then, it sends a packet two hops into the network. 
It elicits a response from there, and so on. 
And eventually, the packet 
will reach the remote host, which will 
then send a response back.<br/>
This gives us information about what 
 routers are between our computer and the host, the number of them, 
and the sequence in which they're organized. 

 </img></li><br/>
</ol>
<br/>
<br/>

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





<br/>
<br/>
<br/>
<div class="subtitle" id="inetd">xinetd/inetd</div>
<p>The <strong>internet daemon (Disk And Execution MONitor) [xinetd/inetd]</strong> listens for connections on many ports at once. When a client connects to a service, the daemon program runs the appropriate server. So, this boils down to the need for servers to be running all the time. </p>
<p><strong>xinetd</strong> can be configured by modifying its configuration file. They are typically in <strong>/etc/xinetd.conf</strong> and files in <strong>/etc/xinetd.d</strong> directory.</p>
<p>Here is the list all of the services that are enabled extracted by using <strong>grep -v "^#" /etc/inetd.conf</strong></p>
<pre>
defaults
{
        log_type        = SYSLOG daemon info
        log_on_failure  = HOST
        log_on_success  = PID HOST DURATION EXIT

        cps             = 50 10
        instances       = 50
        per_source      = 10

        v6only          = no

        groups          = yes
        umask           = 002
}
includedir /etc/xinetd.d
</pre>
<p>As an example of <strong>xinetd</strong> configuration file, here is the <strong>daytime</strong> service:</p>
<pre>
# This is the configuration for the tcp/stream daytime service.

service daytime
{
# This is for quick on or off of the service
        disable         = yes

# The next attributes are mandatory for all services
        id              = daytime-stream
        type            = INTERNAL
        wait            = no
        socket_type     = stream
#       protocol        =  socket type is usually enough
.....
}
</pre>
<p>The <strong>daytime</strong> service that the <strong>getdate</strong> program connects to is actually handled by <strong>xinetd</strong> itself, and it can be made available using both <strong>SOCK_STREAM (tcp)</strong> and <strong>SOCK_DGRAM (udp)</strong> sockets.</p>
<p>Another example for <strong>file transfer</strong> service:</p>
<pre>
# default: off
# description: The kerberized FTP server accepts FTP connections \
#              that can be authenticated with Kerberos 5.
service ftp
{
        flags           = REUSE
        socket_type     = stream
        wait            = no
        user            = root
        server          = /usr/kerberos/sbin/ftpd
        server_args     = -l -a
        log_on_failure  += USERID
        disable         = yes
}
</pre>
<p>The <strong>ftp</strong> file transfer service is aailable only via <strong>SOCK_STREAM</strong> sockets and is provided by external program, in this case <strong>gssftp</strong>. The daemon will start these external program when a client connects to the <strong>ftp</strong> port.</p>
<p>To activate service configuration, we can edit the <strong>xinetd</strong> configuration and send a hang-up signal to the daemon process. If a daemon process has a configuration file which is modified after the process has been started, there should be a way to tell that process to re-read its configuration file, without stopping the process. Many daemons provide this mechanism using the <strong>SIGHUP</strong> signal handler. When we want to tell the daemon to re-read the file we simply send it the <strong>SIGHUP</strong> signal.
<pre>
killall -HUP xinetd
</pre>
</p>
<br/>
<br/>

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





<br/>
<br/>
<br/>
<div class="subtitle" id="network_sniffing">Network Sniffing</div>
<p>On a <strong>switched network</strong>, each packet moves between a computer and a port on a switch, 
or between two switches. It's the job of the switch to transmit a packet only when the line is clear, and only to the necessary ports.  This way, it's like each computer is on it's own private network. We never have packet collisions and we leave as much of our network clear as possible. This means we get the most throughput possible out of our network.</p>
<p>On an <strong>unswitched network</strong>, all packets go to all ports and visible to all computers.  If two computers want to send a packet at the same time, we have a collision. Both computers must resend, and that particular bit of bandwidth used for the broken transmission is completely wasted.  This means that actual network throughput is much lower than the theoretical potential.</p>
<p>On an <strong>unswitched network</strong>, where Ethernet packets pass through every device on the network, expecting each system device to only look at the packets sent to its destination address. But it is quite trivial to set a device to <strong>promiscuous mode</strong>, which causes it to look at all packets, regardless of the destination address. Most packet-capturing codes, such as <strong>tcpdump</strong>, drop the device they are listening to into promiscuous mode by default. This promiscuous more can be set using <strong>ifconfig</strong>:</p>
<pre>
$ ifconfig eth0
eth0      Link encap:Ethernet  HWaddr 00:1D:09:67:11:69
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:4186029513 errors:0 dropped:0 overruns:0 frame:0
          TX packets:3343760394 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000
          RX bytes:1289597250 (1.2 GiB)  TX bytes:332184455 (316.7 MiB)
          Interrupt:169 Memory:f8000000-f8012800

</pre>
<pre>
$ sudo ifconfig eth0 promisc
$ ifconfig eth0
eth0      Link encap:Ethernet  HWaddr 00:1D:09:67:11:69
          UP BROADCAST RUNNING PROMISC MULTICAST  MTU:1500  Metric:1
          RX packets:4186031551 errors:0 dropped:0 overruns:0 frame:0
          TX packets:3343761162 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000
          RX bytes:1290474745 (1.2 GiB)  TX bytes:332296284 (316.9 MiB)
          Interrupt:169 Memory:f8000000-f8012800
</pre>
<p>To go back to the state before:</p>
<pre>
$ sudo ifconfig eth1 -promisc
</pre>
<p><strong>tcpdump</strong> allows us to save the packets that are captured, so that we can use it for future analysis. The saved file can be viewed by the same tcpdump command.</p>
<br/>
<img alt="tcpdump" src="images/socket/tcpdump.png"/>
<br/>
<p> We can also use open source software like <strong>wireshark</strong> to read the <strong>tcpdump pcap (Packet CAPture)</strong> files (Unix-like systems implement pcap in the <strong>libpcap</strong> library). </p>
<p>Capturing packets sometimes called <strong>sniffing</strong> when it's not necessarily meant for public viewing. Sniffing packets in promiscuous more on an unswitched network can turn up lots of information.</p>
<p>However, our network is only unswitched within individual access points, and there is some provision in wifi to allow multiple access points in the same space.</p>
<br/><br/>

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





<br/>
<br/><br/>
<div class="subtitle_2nd" id="raw_socket">Raw_Sock Sniffing</div>
<p>In this section, we are accessing the network at lower level than transport/session layer. By using <strong>raw sockets</strong>, all the details are exposed and must be handled explicitly. Note that we've been using <strong>stream sockets</strong>, where the data is neatly wrapped in a TCP/IP connection. Accessing session layer, the OS takes care of all of the lower-level details of transmission, correction, and routing.</p>
<p>By specifying <strong>SOCK_RAW</strong>, we can use <strong>raw sockets</strong>. The protocol matters because there are multiple options. The protocol can be <strong>IPPROTO_TCP</strong> or <strong>IPPROTO_UDP</strong>. Here we are sniffing packet of TCP.</p>
<pre>
// sn.c

#include &lt;stdio.h&gt;
#include &lt;stdlib.h&gt;
#include &lt;string.h&gt;
#include &lt;sys/socket.h&gt;
#include &lt;netinet/in.h&gt;
#include &lt;arpa/inet.h&gt;

void packet_dump(const unsigned char *buf, const unsigned int len)
{
        unsigned char c;
        int i,j;
        for(i = 0; i &lt; len; i++) {
                printf("%02x ", buf[i]);
                if((i % 16) == 15 || (i == len-1)) {
                        for(j = 0; j &lt; 15 - (i % 16); j++) printf("   ");
                        printf("| ");
                        for(j = (i - (i % 16)); j &lt;= i; j++) {
                                c = buf[j];
                                if((c &gt; 31) &amp;&amp; (c &lt; 127))
                                        printf("%c", c);
                                else
                                        printf(".");
                        }
                        printf("\n");
                }
        }
}

int main()
{
        int received_length, sock_fd;
        int i;
        u_char buf[5000];

        if((sock_fd = socket(PF_INET, <font color="red">SOCK_RAW</font>, <font color="red">IPPROTO_TCP</font>)) == -1) {
                printf("errro: socket");
                exit(1);
        }

        for(i = 0; i &lt; 5; i++) {
                received_length = recv(sock_fd, buf, 4000, 0);
                printf("%d byte packet\n", received_length);
                packet_dump(buf, received_length);
        }
}
</pre>
<p>We opened a raw TCP socket and listens for five packets, print out the raw data of each one with the <b>packet_dump()</b>. The <b>recv()</b> function receives a message from a socket. The <b>recv()</b> call can be used on a connection mode socket or a bound, connectionless socket. If no messages are available at the socket, the <b>recv()</b> call <font color="blue">waits</font> for a message to arrive unless the socket is nonblocking. If a socket is nonblocking, -1 is returned and the external variable errno is set to <b>EWOULDBLOCK</b>. </p>
<p>Here the <b>buf</b> is declared as a <b>u_char</b> type, and it's for our convenience since it's been repeatedly used in network programming. The <b>u</b> in <strong>u_char</strong> stands for <b>unsigned</b>.
Actually, the <b>char</b> data-type isn't always used to store characters. Since char is the only data type whose size is always <b>1 byte</b> on any platform, it is used often to store 1 byte data.
1 byte can hold 255 values but the regular char datatype is a signed type and hence stores values from -127 to 127 i.e. After 127, the number is represented in 2's complement notation and hence the numbers are represented as negative.
To use only the values 0 to 255, the unsigned type is used. In this case, everything is considered as a positive number and 2's complement is not taken.</p>
<p>To run the compiled code, we need to have root privilege since the use of raw sockets requires it.</p>
<pre>
$ gcc -o sn sn.c
$ sudo ./sn
40 byte packet
45 00 00 28 87 ee 40 00 74 06 dd 0c 63 43 d6 dd | E..(..@.t...cC..
d3 2b 94 88 07 ef 00 16 c5 bc d9 8c ab c6 ca 28 | .+.............(
50 10 fa 63 f6 5d 00 00                         | P..c.]..
40 byte packet
45 00 00 28 87 f1 40 00 74 06 dd 09 63 43 d6 dd | E..(..@.t...cC..
d3 2b 94 88 07 ef 00 16 c5 bc d9 8c ab c6 cb 2c | .+.............,
50 10 ff ff ef bd 00 00                         | P.......
40 byte packet
45 00 00 28 87 f3 40 00 74 06 dd 07 63 43 d6 dd | E..(..@.t...cC..
d3 2b 94 88 07 ef 00 16 c5 bc d9 8c ab c6 cc 30 | .+.............0
50 10 fe fb ef bd 00 00                         | P.......
40 byte packet
45 00 00 28 87 f4 40 00 74 06 dd 06 63 43 d6 dd | E..(..@.t...cC..
d3 2b 94 88 07 ef 00 16 c5 bc d9 8c ab c6 cd 34 | .+.............4
50 10 fd f7 ef bd 00 00                         | P.......
40 byte packet
45 00 00 28 87 f5 40 00 74 06 dd 05 63 43 d6 dd | E..(..@.t...cC..
d3 2b 94 88 07 ef 00 16 c5 bc d9 8c ab c6 ce 38 | .+.............8
50 10 fc f3 ef bd 00 00                         | P.......
</pre>
<p>We captured packets, but it is not reliable and will miss some of the packets in case when there is a lot of traffic. And we only captured TCP packets. To capture other packets such as UDP, we need to open additional raw sockets.
The biggest issue of capturing raw socket packets is the dependency on OS.</p>
<br/>
<p>For more tcpdump, check <a href="http://www.bogotobogo.com/Linux/tcpdump.php" target="_blank">here</a>.</p>
<br/>
<br/>
<br/>

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





<br/>
<br/>
<div class="subtitle_2nd" id="libpcap_sniffing">Sniffing using libpcap</div>
<p><strong>libpcap</strong> was originally developed by the <strong>tcpdump</strong> developers in the Network Research Group at Lawrence Berkeley Laboratory. The low-level packet capture, capture file reading, and capture file writing code of tcpdump was extracted and made into a library, with which tcpdump was linked. It is now developed by the same <a href="http://www.tcpdump.org/" target="_blank">tcpdump.org group</a> that develops tcpdump.</p>
<p>The <strong>libpcap</strong> provides a portable framework for low-level network monitoring. It can provide network statistics collection, security monitoring and network debugging. Since almost every system vendor provides a different interface for packet capture, the libpcap authors created this system-independent API to ease in porting and to alleviate the need for several system-dependent packet capture modules in each application.</p>
<p>We can use the <strong>libpcap</strong> to smooth out the inconsistencies of raw sockets. While the library is still using raw sockets, it knows how to correctly work on with raw sockets on multiple platforms/architectures.</p>
<pre>
// pcap-sn.c

#include &lt;stdio.h&gt;
#include &lt;stdlib.h&gt;
#include <font color="red">&lt;pcap.h&gt;</font>;

void packet_dump(const unsigned char *buf, const unsigned int len)
{
        unsigned char c;
        int i,j;
        for(i = 0; i &lt; len; i++) {
                printf("%02x ", buf[i]);
                if((i % 16) == 15 || (i == len-1)) {
                        for(j = 0; j &lt; 15 - (i % 16); j++) printf("   ");
                        printf("| ");
                        for(j = (i - (i % 16)); j &lt;= i; j++) {
                                c = buf[j];
                                if((c &gt; 31) &amp;&amp; (c &lt; 127))
                                        printf("%c", c);
                                else
                                        printf(".");
                        }
                        printf("\n");
                }
        }
}
int main()
{
        struct pcap_pkthdr header;
        const u_char *packet;
        char err_buf[PCAP_ERRBUF_SIZE];
        char *device;
        pcap_t *pcap_handle;
        int i;

        device = <font color="red">pcap_lookupdev</font>(err_buf);
        if(!device) {
                printf("Error in %s: %s\n","pcap_lookupdev",err_buf);
                exit(1);
        }

        printf("Sniffing on device %s\n", device);

        pcap_handle = <font color="red">pcap_open_live</font>(device, 4096, 1, 0, err_buf);
        if(!pcap_handle) {
                printf("Error in %s: %s\n","pcap_open_live",err_buf);
                exit(1);
        }

        for(i = 0; i &lt; 5; i++) {
                packet = <font color="red">pcap_next</font>(pcap_handle, &amp;header;);
                printf("%d byte packet\n", header.len);
                packet_dump(packet, header.len);
        }
        <font color="red">pcap_close</font>(pcap_handle);
}
</pre>
<p>The <strong>pcap</strong> functions use a error buffer to return error and status messages, so we used this function to display this buffer:</p>
<pre>
        if(!pcap_handle) {
               printf("Error in %s: %s\n","pcap_open_live",err_buf);
               exit(1);
        }
</pre>
<br/>
<br/>
<p>The <strong>err_buf</strong> is the error buffer, its size coming from a define in <strong>pcap.h</strong> set to <strong>256</strong>. The header variable is a <strong>pcap_pkthdr</strong> structure containing extra capture information about the packet, the time it captured and its length. The <strong>pcap_handle</strong> pointer is similar to a file descriptor but it is used to reference a packet-capturing object:</p>
<pre>
        struct pcap_pkthdr header;
        const u_char *packet;
        char err_buf[PCAP_ERRBUF_SIZE];
        char *device;
        pcap_t *pcap_handle;
</pre>
<p>The <strong>pcap_lookupdev()</strong> looks for a suitable device to sniff on. This device is returned as a string pointer referencing static function memory. In our case, it is <strong>/dev/eth0</strong>. It will return NULL if it can't find a suitable interface:</p>
<pre>
        device = <font color="blue">pcap_lookupdev</font>(err_buf);
        if(!device) {
                printf("Error in %s: %s\n","pcap_lookupdev",err_buf);
                exit(1);
        }

        printf("Sniffing on device %s\n", device);
</pre>
<p>The <strong>pcap_open_live()</strong> opens a packet-capturing device, and then returns a handle to it. The arguments are the device to sniff, the maximum packet size, a promiscuous flag, a timeout value, and a pointer to the error buffer. Because we want to capture in promiscuous mode, the flag is set to 1:</p>
<pre>
        pcap_handle = <font color="blue">pcap_open_live</font>(device, 4096, 1, 0, err_buf);
        if(!pcap_handle) {
                printf("Error in %s: %s\n","pcap_open_live",err_buf);
                exit(1);
        }
</pre>
<p>The <strong>pcap_next()</strong> is used in the packet capture loop to get the next packet. Tis function is passed the <strong>pcap_handle</strong> and a pointer to a <strong>pcap_pkthdr</strong> structure so it can fill it with details of the capture. The function returns a pointer to the packet and then prints the packet. The <strong>pcal_close()</strong> closes the capture interface:</p>
<pre>
        for(i = 0; i &lt; 5; i++) {
                packet = <font color="blue">pcap_next</font>(pcap_handle, &amp;header;);
                printf("%d byte packet\n", header.len);
                packet_dump(packet, header.len);
        }
        <font color="blue">pcap_close</font>(pcap_handle);
</pre>
<br/>
<p>Output should look like this:</p>
<pre>
$ gcc -o pcap_sn pcap_sn.c -l pcap
$ sudo ./pcap_sn
Sniffing on device eth0
1134 byte packet
01 00 5e 04 50 01 00 22 19 55 11 27 08 00 45 00 | ..^.P..".U.'..E.
04 60 71 6b 00 00 10 11 8e 65 d3 2b 94 8b ef 04 | .`qk.....e.+....
50 01 07 c4 2c a0 04 4c 50 bf 80 61 2d 22 a2 f3 | P...,..LP..a-"..
e8 a4 00 00 2f 6d 00 00 01 b6 16 58 24 60 5f db | ..../m.....X$`_.
7f 1b 6d fc 6d b7 f1 b6 df c6 db 7f 1b 6d fc 6d | ..m.m........m.m
b7 f1 b6 df c6 db 7f 1b 6d fc 6d b7 ed 83 79 90 | ........m.m...y.
.....
62 3e f2 b0 5e c6 07 fe 07 02 99 5e 60 71 75 4f | b&gt;..^......^`quO
aa c8 7d f4 40 c2 92 c5 86 b6 b7 5e d9 84 e1 0d | ..}.@......^....
30 f9 b4 cd 17 dd c6 f7 3c 38 9e 53 71 4a       | 0.......&lt;8.SqJ
1134 byte packet
01 00 5e 04 50 01 00 22 19 55 11 27 08 00 45 00 | ..^.P..".U.'..E.
04 60 71 6c 00 00 10 11 8e 64 d3 2b 94 8b ef 04 | .`ql.....d.+....
50 01 07 c4 2c a0 04 4c 6f 18 80 61 2d 23 a2 f3 | P...,..Lo..a-#..
e8 a4 00 00 2f 6d 25 a2 84 74 f9 51 05 26 e4 66 | ..../m%..t.Q.&amp;.f
51 01 88 de e7 6f 07 0a 71 75 b7 bc 51 fe ac 4a | Q....o..qu..Q..J
b0 a8 7a 0c df d2 b3 f6 29 71 6f 9a e5 9f aa e0 | ..z.....)qo.....
.....
0b a4 b5 a1 d1 77 f5 ba 59 ac 15 28 2d df 5e 4f | .....w..Y..(-.^O
97 b3 be a1 de 79 e2 1b 8a 84 85 33 bf b3 54 c0 | .....y.....3..T.
ff f9 5a 03 0c 07 97 f9 73 19 8d 77 ed fb f7 24 | ..Z.....s..w...$
80 cb ec cc fc 2d 0b 41 92 0c 90 0e a4 06       | .....-.A......
</pre>
<br/><br/>

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





<br/>
<br/>
<br/>
<div class="subtitle_2nd" id="wget_sniffing">Packet Sniffing using wget and tcpdump</div>
<p>In this section, we will check the response to the http url hitting- <br/>http://ad.doubleclick.net/ad/DY212/;order=sny0003;sz=1x1;ord=1322627560,<br/>
to see we are getting http 200 response.</p>
<p>We're going to do packet dump tcpdump (we may use wireshark) when our
server fires the tracking pixel? I am using linux commend:</p>
<pre>
sudo tcpdump port 80 -XX -s 1024
</pre>
<p>and use another linux window to run:
<pre>
wget http://ad.doubleclick.net/ads/813/;order=smsn0009;sz=1x1;o=1922687960
</pre>
<p>Then we get the output something like this:</p>
<pre>
23:50:12.616992 IP hx-in-f149.1e100.net.http &gt; 201.13.198.196.54564: 
P 475:859(384) ack 268 win 123 <nop,nop,timestamp 2103493515 2774405222>
 0x0000:  001d 0967 1169 2c6b f562 1105 0800 4500  ...g.i,k.b....E.
 0x0010:  01b4 f2f9 0000 2d06 9f7a 4a7d 4795 d32b  ......-..zJ}G..+
 0x0020:  9492 0050 d524 dca0 9756 c704 1fe5 8018  ...P.$...V......
 0x0030:  007b a053 0000 0101 080a 7d60 c38b a55e  .{.S......}`...^
 0x0040:  1066 4854 5450 2f31 2e30 2032 3030 204f  .f<font color="red">HTTP/1.0.200.O</font>
 0x0050:  4b0d 0a43 6f6e 7465 6e74 2d54 7970 653a  <font color="red">.K.</font>.Content-Type:
 0x0060:  2069 6d61 6765 2f67 6966 0d0a 4c61 7374  .image/gif..Last
 0x0070:  2d4d 6f64 6966 6965 643a 2053 756e 2c20  -Modified:.Sun,.
 0x0080:  3031 2046 6562 2032 3030 3920 3038 3a30  01.Feb.2009.08:0
 0x0090:  303a 3030 2047 4d54 0d0a 4461 7465 3a20  0:00.GMT..Date:.
 0x00a0:  5765 642c 2033 3020 4e6f 7620 3230 3131  Wed,.30.Nov.2011
 0x00b0:  2032 313a 3433 3a31 3420 474d 540d 0a45  .21:43:14.GMT..E
 0x00c0:  7870 6972 6573 3a20 5468 752c 2030 3120  xpires:.Thu,.01.
 0x00d0:  4465 6320 3230 3131 2032 313a 3433 3a31  Dec.2011.21:43:1
 0x00e0:  3420 474d 540d 0a58 2d43 6f6e 7465 6e74  4.GMT..X-Content
 0x00f0:  2d54 7970 652d 4f70 7469 6f6e 733a 206e  -Type-Options:.n
 0x0100:  6f73 6e69 6666 0d0a 5365 7276 6572 3a20  osniff..Server:.
 0x0110:  7366 6665 0d0a 436f 6e74 656e 742d 4c65  sffe..Content-Le
 0x0120:  6e67 7468 3a20 3433 0d0a 582d 5853 532d  ngth:.43..X-XSS-
</nop,nop,timestamp></pre>
<p>
The 200 OK means doubleClick is returning the response properly. In other words,
 the pixel is fired properly, then we can assure it's been registered a hit.</p>
<p>Also see <a href="../php/php13B_curl.php" target="_blank">php cURL</a></p>
<br/><br/>

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





<br/>
<br/>
<br/>
<div class="subtitle" id="block_vs_non_blocking">Blocking socket vs non-blocking socket</div>
<p>By default, TCP <b>socket</b> is set as <b>blocking (sleeping)</b> mode.</p>
<p>What does it mean blocking? Well, we're doing communication. We do something, and wait for the response. So, until we get the response, we cannot do anything. Just wait... and wait. What's the response? It could be either normal message or error.</p>
<p>For example, client did call <b>receive()</b> to remote server to read from a stream, control isn't returned to our program until at least one byte of data is read from the remote site. We can call this waiting process as <b>blocking</b>. If we use <b>recv()</b> in <b>non-blocking</b> mode by setting a descriptor as such, it will return any data that the system has in it's read buffer for that socket. But, it won't wait for that data. </p>
<p>Among the socket APIs we used, <b>accept()</b>, <b>connect()</b>, <b>recv()</b>, and <b>recvfrom()</b> are blocking functions.</p>
<p>When we first create the socket descriptor with <b>socket()</b>, the kernel sets it to blocking. If we do not want a socket to be blocking, we have to make a call to <b>fcntl()</b>:
<pre>
#include &lt;unistd.h&gt;
#include &lt;fcntl.h&gt;

sockfd = socket(PF_INET, SOCK_STREAM, 0);
<font color="red">fcntl(sockfd, F_SETFL, O_NONBLOCK);</font>
</pre>
By setting a socket to non-blocking, we can poll the socket for information. If we try to read from a non-blocking socket and there's no data there, it's not allowed to block-it will return -1 and errno will be set to EWOULDBLOCK. The <strong>non-blocking</strong> mode is set by changing one of the socket's flags. <br/>
The flags are a series of bits, each one representing a different capability of the socket. </p>
<p>Actually, it's a three-step process:<br/>
<ol>
<li>use F_GETFL to get the current flags</li>
<li>set or clear the O_NONBLOCK flag</li>
<li>then use F_SETFL to set the flags.</li>
</ol>
<p>For more info, <a href="http://www.scottklement.com/rpg/socktut/nonblocking.html" target="_blank">Blocking vs. non-blocking sockets</a>.</p>
<p>However, in general, the polling is not a good idea because it makes our program in a busy-wait looking for data on the socket, and it will consume the precious CPU time.</p>
<p>We have another way of checking to see if there's data waiting to be read: <strong>select()</strong>!</p>
<br/><br/>

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





<br/>
<br/>
<br/>
<div class="subtitle" id="select_non_blocking">What are the pros/cons of select(), non-blocking I/O and SIGIO?</div>
<p>Using <strong>non-blocking I/O</strong> means that we have to poll sockets to see if there is data to be read from them. Polling should usually be avoided since it uses more CPU time than other techniques. </p>
<p>Using SIGIO allows our application to do what it does and have the operating system tell it (with a signal) that there is data waiting for it on a socket. The only drawback to this solution is that it can be confusing, and if we're dealing with multiple sockets we will have to do a <strong>select()</strong> anyway to find out which one(s) is ready to be read. </p>
<pre>
#include &lt;sys/time.h&gt;
#include &lt;sys/types.h&gt;
#include &lt;unistd.h&gt;

int select(int numfds, fd_set *readfds, fd_set *writefds,
           fd_set *exceptfds, struct timeval *timeout); 
</pre>
<p>The function monitors sets of file descriptors; in particular <strong>readfds</strong>, <strong>writefds</strong>, and <strong>exceptfds</strong>. If we want to see if we can read from standard input and some socket descriptor, <strong>sockfd</strong>, just add the file descriptors <strong>0</strong> and <strong>sockfd</strong> to the set <strong>readfds</strong>. The parameter <strong>numfds</strong> should be set to the values of the highest file descriptor plus one. In this example, it should be set to <strong>sockfd+1</strong>, since it is assuredly higher than standard input (0).</p>
<p>
When <strong>select()</strong> returns, <strong>readfds</strong> will be modified to reflect which of the file descriptors you selected which is ready for reading.</p>
<p>
Using <strong>select()</strong> is great if our application has to accept data from more than one socket at a time since it will block until any one of a number of sockets is ready with data. In other words, <strong>select()</strong> gives us the power to monitor several sockets at the same time. It'll tell us which ones are ready for reading, which are ready for writing, and which sockets have raised exceptions, if we really want to know that.
</p>
<p>One other advantage to <strong>select()</strong> is that we can set a time-out value after which control will be returned to us whether any of the sockets have data for us  or not.</p>
<p>The <strong>select()</strong>, though very portable, is one of the slowest methods for monitoring sockets. One possible alternative is <strong>libevent</strong> that encapsulates all the system-dependent stuff involved with getting socket notifications.</p>
<br/><br/>
<br/>

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





<br/>
<br/>
<br/>
<div class="subtitle" id="passive_active_ftp">Active FTP vs Passive FTP</div>
<ol>
<li> <strong>Active FTP</strong><br/>
<br/>
<ol>
<li>FTP server's port 21 from anywhere (Client initiates connection)</li>
<li>FTP server's port 21 to ports &gt; 1023 (Server responds to client's control port)</li>
<li>FTP server's port 20 to ports &gt; 1023 (<font color="red">Server initiates data connection</font> to client's data port)</li>
<li>FTP server's port 20 from ports &gt; 1023 (Client sends ACKs to server's data port)</li>
</ol>
<p>(*)The main problem with active mode FTP actually falls on the client side. The FTP client doesn't make the actual connection to the data port of the server--it simply tells the server what port it is listening on and the server connects back to the specified port on the client. From the client side firewall this appears to be an outside system initiating a connection to an internal client--something that is usually blocked.</p>
</li>
<br/>
<li> <strong>Passive FTP</strong><br/>
<br/>
<ol>
<li>FTP server's port 21 from anywhere (Client initiates connection)</li>
<li>FTP server's port 21 to ports &gt; 1023 (Server responds to client's control port)</li>
<li>FTP server's ports &gt; 1023 from anywhere (<font color="red">Client initiates data connection</font> to random port specified by server)</li>
<li>FTP server's ports &gt; 1023 to remote ports &gt; 1023 (Server sends ACKs (and data) to client's data port)
</li>
</ol>
</li>
</ol>
<br/>
<br/>
<div class="subtitle" id="vpn">Client-Server : VPN</div>
<p>Visit <a href="http://www.bogotobogo.com/VideoStreaming/VPN.php" target="_blank">VPN</a>.</p>
<br/>
<br/>

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





<br/>
<br/>
<br/>
<div class="subtitle" id="switch_vs_router">Switch vs Router</div>
<p>
<ol>
<li><strong>Hub</strong> can't identify the source or intended destination of the information it receives, so it sends the information to all of the computers connected to it, including the one that sent it. A hub can send or receive information, but it can't do both at the same time. This makes hubs slower than switches. Hubs are the least complex and the least expensive of these devices.</li>
<li><strong>Switches</strong> work the same way as hubs, but they can identify the intended destination of the information that they receive, so they send that information to only the computers that are supposed to receive it. Switches can send and receive information at the same time, so they can send information faster than hubs can.</li>
<li><strong>Routers</strong> enable computers to communicate and they can pass information between two networks
 <li><strong>Switches</strong> usually work at <strong>Layer 2 (Data or Datalink)</strong> of the OSI Reference Model, using <strong>MAC addresses</strong></li>
<li><strong>Routers</strong> work at <strong>Layer 3 (Network)</strong> with Layer 3 addresses (IP).</li>
<li> The algorithm that switches use to decide how to forward packets is different from the algorithms used by routers to forward packets.</li>
<li>One of these differences in the algorithms between switches and routers is how <strong>broadcasts</strong> are handled. </li>
<li>On any network, the concept of a broadcast packet is vital to the operability of a network. Whenever a device needs to send out information but doesn't know who it should send it to, it sends out a broadcast. Broadcasts are used any time a device needs to make an announcement to the rest of the network or is unsure of who the recipient of the information should be.</li>
<li>A <strong>hub</strong> or a <strong>switch</strong> will pass along any broadcast packets they receive to all the other segments in the broadcast domain.</li>
<li>But a <strong>router will not</strong>. Without the specific address of another device, it will not let the data packet through. This is a good thing for keeping networks separate from each other, but not so good when we want to talk between different parts of the same network. This is where switches come in.</li>
<li><strong>Switches</strong> don't scale to large networks: table for all destinations may blow up and it may broadcast new destinations to the whole world.</li>
<li>While there are several technologies such as Ethernet, 4G, and wireless, <strong>switches</strong> don't work across more than one link layer technology.</li>
<li><strong>Switches</strong> do not provide much for traffic control.</li>
</li></ol>
<br/><br/>
<br/>
<br/>
<div class="subtitle" id="socket_qt">Socket programming with Qt</div>
<br/>
<p><a href="http://www.bogotobogo.com/cplusplus/sockets_server_client_QT.php" target="_blank">TCP sockets using Qt</a>.</p>
<br/>
<br/>

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





<br/>
<br/>
<br/>
<div class="subtitle" id="network">Networking</div>
<br/>
<br/>
<div class="subtitle_2nd" id="4_layer_model">4-Layer Model</div>
<br/>
<img alt="4_Layer_Model.png" src="images/socket/4_Layer_Model.png"/>
<p>Picture from <a href="http://en.wikipedia.org/wiki/File:IP_stack_connections.svg" target="_blank">wiki</a></p>
<p><strong>Protocols</strong> and <strong>layering</strong> is the primary 
structuring method used to divide up network functionality. 
Each protocol instance talks virtually to its peer using the protocol.
 Also, each instance of a protocol uses only the services of the lower layer.</p>
<p>This is about modularization of a
complex system. As we already know the protocol refers to a
sequence of communication and computation
to control the system. So a modularize the protocol is what
people call the layer <strong>protocol stack</strong>.</p>
<p>This is not for efficiency but for
<strong>evolvability</strong>. In other words, 
 this allows specialization of
business sectors, and a common interface
among them but also for technology reasons.
We have so many unforeseen and
unforeseeable needs in the future for our
technology that we would rather keep a stack where we can pull out 
one part of the whole system without having to redesign the entire system.
</p>
<p><strong>Encapsulation</strong> is the mechanism used for protocol 
layering. So, the lower layer wraps higher layer content, adding its own control information (header/trailer), compression/encryption, segmentation/disassemble, etc. to make a new message 
for delivery.</p>
<p>Advantages of the network layers abstraction (encapsulation):</p>
<ol>
<li>Break a complex task of communication into smaller pieces.</li>
<li>Lower layers can change implementation without affecting upper layers as long as the interface between layers remains the same. For example, the difference in the underlying connection systems (between wire and wireless) does not affect the upper layer communications as shown in the picture below:
   <br/>
<img alt="AdvantagesOfLayering.png" src="images/socket/AdvantagesOfLayering.png"/>
<br/>
</li>
<li>Lower layers hide the implementation details from higher layers.</li>
</ol>
<br/>
<br/>
<br/>
<div class="subtitle_2nd" id="units_of_data">Unit of Data for each layer of Reference Model</div>
<br/><br/>
<table border="2" cellpadding="5">
<tr>
<th>Layer</th>
<th>Unit of Data</th>
</tr>
<tr>
<td>Application</td>
<td>Message</td>
</tr>
<tr>
<td>Transport</td>
<td>Segment</td>
</tr>
<tr>
<td>Network</td>
<td>Packet</td>
</tr>
<tr>
<td>Link</td>
<td>Frame</td>
</tr>
<tr>
<td>Physical</td>
<td>Bit</td>
</tr>
</table>
<br/><br/>

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





<br/>
<br/>
<br/>
<div class="subtitle_2nd" id="ip_layer">Network Layer (IP)</div>
<br/>
<p>Summary of IP Network Layer</p>
<ol>
<li>The Internet protocol (IP) is an example of a network layer, and is required for all communications in the Internet.</li>
<li>There are currently two main versions of the IP protocol used in the Internet: IP Version 4, and IP Version 6.</li>
<li>The Internet protocol is responsible for delivering self-contained datagrams from a source host to the specified destination.</li>
<li>It makes no promise to deliver packets in order, or at all.</li>
<li>It has a feature to prevent packets looping forever (TTL).</li>
<li>It will fragment packets if they are too long.</li>
<li>It uses a checksum to reduce chances of delivering to wrong address.</li>
</ol>
<br/><br/>
<table border="2" cellpadding="5">
<tr>
<th>Property</th>
<th>Behavior</th>
</tr>
<tr>
<td>Data</td>
<td>individually routed packets. <br/>
Hop-by-hop routing.</td>
</tr>
<tr>
<td>Unreliable</td>
<td>Packet might be dropped</td>
</tr>
<tr>
<td>Best effort</td>
<td>if necessary</td>
</tr>
<tr>
<td>Connectionless</td>
<td>No per-flow state<br/>
Packets may not be in order</td>
</tr>
</table>
<br/>
<p>(*note) An Internet router is allowed to drop packets when it has insufficient resources(best effort service). There can also be cases when resources are available (e.g., link capacity) but the router drops the packet anyways. The following are examples of scenarios where a router drops a packet even when it has sufficient resources:</p>
<ol>
<li>A router configured as a firewall, that dictates which packets should be denied.</li>
<li>An ISP that limits bandwidth consumed by customers, even though there is available capacity.</li>
</ol>
<br/>
<br/>

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





<br/>
<br/>
<br/>
<div class="subtitle_2nd" id="tcp_layer">Transport Layer (TCP)</div>
<ol>
<li>TCP is responsible for providing reliable, in-sequence end-to-end delivery of data between applications. In other words, 
TCP delivers a stream of bytes from one end to the other, reliably and in-sequence, on behalf of an application.</li>
<li>When a TCP packet arrives at the destination, the data portion is delivered to the service (or application) identified by the destination port number.</li>
<li>TCP will retransmit missing data even if the application can not use it - for example, in Internet telephony a late arriving retransmission may arrive too late to be useful.</li>
<li>TCP saves an application from having to implement its own mechanisms to retransmit missing data, or resequence arriving data.</li>
</ol>
<br/><br/>
<table border="2" cellpadding="5">
<tr>
<th>Property</th>
<th>Behavior</th>
</tr>
<tr>
<td>Connection oriented</td>
<td><a href="http://www.bogotobogo.com/cplusplus/sockets_server_client.php#three_way_handshake" target="_blank">Three-way handshake</a> for connection setup.</td>
</tr>
<tr>
<td>Reliable</td>
<td>Acknowledgments indicate delivery.<br/>
Checksums detect corrupted data.<br/>
Sequence numbers detec missing data.<br/>
Flow-control prevents overrunning receiver.</td>
</tr>
<tr>
<td>In-sequence</td>
<td>Data delivered to application in sequence transmitted.</td>
</tr>
<tr>
<td>Congestion Control</td>
<td>It controls network congestion</td>
</tr>
</table>
<br/>
<br/>
<br/>
<br/>
<div class="subtitle_2nd" id="delivery">Packet Delivery Models</div>
<br/>
<img alt="Delivery_Multicast_Unicast.png" src="images/socket/Delivery_Multicast_Unicast.png" width="100%">
<br/>
<br/>

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





<br/>
<br/>
<div class="subtitle_2nd" id="icmp">Internet Control Message Protocol (ICMP)</div>
<p>Making Network layer work.</p>
<ol>
<li>Internet Protocol (IP)
    <ol>
<li>creat IP datagrams</li>
<li>deliver datagrams from end to end hop-by-hop</li>
</ol>
</li>
<li>Routing Tables - algorithms to populate router forwarding tables</li>
<li>ICMP
    <ol>
<li>Examples: ping, tracerouter</li>
<li>communicates network layer information between end hosts and routers</li>
<li>reports error conditions</li>
<li>helps to diagnose problems</li>
</ol>
</li>
</ol>
<br/><br/>
<table border="2" cellpadding="5">
<tr>
<th>Property</th>
<th>Behavior</th>
</tr>
<tr>
<td>Reporting Message</td>
<td>Self-contained message reporting error
</td></tr>
<tr>
<td>Unreliable</td>
<td>Simple datagram service - no retries</td>
</tr>
</table>
<br/>
<br/>
<img alt="icmp.png" src="images/socket/icmp.png"/>
<p>Picture from <a href="http://www.erg.abdn.ac.uk/~gorry/eg3567/inet-pages/icmp.html" target="_blank">Internet Control Message Protocol (ICMP) </a></p>
<br/>
<p><strong>ping</strong></p>
<ol>
<li>ping can be used to measure end-to-end delay.</li>
<li>ping can be used to test if a machine is alive.</li>
<li>ping can be maliciously used as a way to attack a machine by flooding it with ping requests.</li>
<li>ping sends out <strong>ICMP ECHO_REQUEST</strong> message to the destination.</li>
</ol>
<br/>
<p><strong>traceroute</strong></p>
<p>It contains a client interface to ICMP. Like the <strong>ping</strong>, it may be used by a user to verify an end-to-end Internet Path is operational, but also provides information on each of the Intermediate Systems (i.e. IP routers) to be found along the IP Path from the sender to the receiver. <strong>traceroute</strong> uses ICMP echo messages. These are addressed to the target IP address. The sender manipulates the <strong>TTL (hop count)</strong> value at the IP layer to force each hop in turn to return an error message.</p>
<br/><br/>

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





<br/>
<br/>
<br/>
<div class="subtitle_2nd" id="arp">Address Resolution Protocol (ARP)</div>
<p>We can retrieve MAC address (Ethernet address) via the <b>Address Resolution Protocol (ARP)</b>.</p>
<ol>
<li>A network device (e.g. laptop) sends an ARP request to the switch ("I want the MAC address of the device with IP address 192.168.102.3").</li>
<li>The switch broadcasts the ARP request to all devices.</li>
<li>The device with the appropriate IP address makes an ARP response back to the switch.</li>
<li>The switch relays the ARP response back to the network device.</li>
</ol>
<p>This is in a sense reverse of the DHCP where obtaining IP by giving device info. ARP provides IP info to get device info (MAC address).</p>
<img alt="arp_pic.png" src="images/socket/arp_pic.png"/>
<br/>
<br/>
<br/>
<br/>
<div class="subtitle" id="error_detection">Error Detection</div>
<p>Sending bits via network is not perfect, and some bits may be received in error whether due to loss or due to a noise in the signal. How do we detect the error in bits?</p>
<p>Here we will discuss three ways of detecting it:</p>
<ol>
<li>Parity</li>
<li>Checksums</li>
<li>CRC (Cyclic Redundancy Check)s</li>
</ol>
<p>Note that those are limited to error detection but not the correction as done in <a href="http://en.wikipedia.org/wiki/Hamming_code" target="_blank">Hamming code</a> etc.</p>
<br/>
<br/>
<div class="subtitle_2nd" id="parity">1. Parity</div>
<p>This is the simplest.<br/>
<p>We take <strong>n</strong> data bits, add 1 check bit that is modulo 2 for the sum of the D bits.<br/>
For example, let's take 7 bit data: 1001100.<br/>
The sum of the bit is 3, then if we do modulo, 3 % 2 = 1<br/>
So, the parity bit becomes <strong>1</strong>.<br/>
The bits we're sending is now 1001100<font color="red">1</font> <br/>
<p>Note that we used one of the two variants: <strong>even</strong> parity bit.</p>
<p>We could have used the <strong>odd</strong> parity bit as shown in the table below.</p>
<table border="1" cellpadding="5">
<tr>
<th>7 bit data</th>
<th># of 1 bits</th>
<th>Even parity</th>
<th>Odd parity</th>
</tr>
<tr>
<td>0000000</td>
<td>0</td>
<td>0000000<font color="red">0</font></td>
<td>0000000<font color="red">1</font></td>
</tr>
<tr>
<td>10100010</td>
<td>3</td>
<td>1010001<font color="red">1</font></td>
<td>1010001<font color="red">0</font></td>
</tr>
<tr>
<td>1101001</td>
<td>4</td>
<td>1101001<font color="red">0</font></td>
<td>1101001<font color="red">1</font></td>
</tr>
<tr>
<td>1111111</td>
<td>7</td>
<td>1111111<font color="red">1</font></td>
<td>1111111<font color="red">0</font></td>
</tr>
</table>
<p>Here is scenario for the successful transmission for even parity assuming we are sending a simple 7-bit value 1001100 with the parity bit (8th bit) following on the right, and with ^ denoting an XOR gate:</p>
<ol>
<li>A wants to transmit: 1001100</li>
<li>A computes parity bit value: 1^0^0^1^1^0^0 = 1</li>
<li>A adds parity bit and sends: 10011001</li>
<li>B receives: 10011001</li>
<li>B computes parity: 1^0^0^1^1^0^0^1 = 0</li>
<li>B reports correct transmission after observing expected even result.</li>
</ol>
<p>Summary:</p>
<ol>
<li>If an odd number of bits (including the parity bit) are transmitted incorrectly, the parity bit will be incorrect, thus indicating that a parity error occurred in the transmission. 
  </li>
<li>The parity bit is only suitable for detecting errors; it cannot correct any errors, as there is no way to determine which particular bit is corrupted. The data must be discarded entirely, and re-transmitted from scratch. </li>
<li>On a noisy transmission medium, successful transmission can therefore take a long time, or even never occur. However, parity has the advantage that it uses only a single bit and requires only a number of XOR gates to generate. </li>
<li>
Parity bit checking is used occasionally for transmitting ASCII characters, which have 7 bits, leaving the 8th bit as a parity bit.</li>
</ol>
<br/><br/>

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





<br/>
<br/><br/>
<div class="subtitle_2nd" id="checksums">2. Checksums</div>
<p>Checksums are widely used in TCP/IP/UDP for error detection and provids stronger protection than parity.</p>
<br/>
<br/>
<img alt="tcp_datagram.png" src="images/socket/tcp_datagram.png" width="100%">
<p>Picture from <a href="http://www.cisco.com/web/about/ac123/ac147/ac174/ac196/about_cisco_ipj_archive_article09186a00800c8417.html" target="_blank">Cisco: TCP Performance</a></p>
<br/><br/>
<p>Here is the description of <strong>checksum</strong> in <a herf="http://tools.ietf.org/html/rfc793" target="_blank">RFC793</a>:</p>
<blockquote>
The checksum field is the 16 bit one's complement of the one's complement sum of all 16-bit words...
</blockquote>
<p>Sending can be divided into 4 steps:</p>
<ol>
<li>Arrange data in 16-bit words</li>
<li>Put zero in checksum position</li>
<li>Add any carryover back to get 16 bits</li>
<li>Complement to get sum</li>
</ol>
<br/>
<img alt="sendingChecksum.png" src="images/socket/sendingChecksum.png" width="100%">
<br/>
<p>Receiving can also be divided into 4 steps:</p>
<ol>
<li>Arrange data in 16-bit words</li>
<li>Add checksum to the 16-bit words</li>
<li>Add any carryover back to get 16 bits</li>
<li>Complement the result and check if it is 0</li>
</ol>
<br/>
<img alt="receivingChecksum.png" src="images/socket/receivingChecksum.png" width="100%">
<br/><br/>
<div class="subtitle_2nd" id="crc">3. CRC (Cyclic Redundancy Check)</div>
<blockquote>
CRCs are so called because the check (data verification) value is a redundancy (it expands the message without adding information) and the algorithm is based on cyclic codes. - <a href="http://en.wikipedia.org/wiki/Cyclic_redundancy_check" target="_blank">wiki</a>
</blockquote>
<p>Given <strong>n</strong> data bits, generate <strong>k</strong> check bits such that the <strong>n+k</strong> bits are evenly divisible by a divisor <strong>D</strong>.</p>
<p>For example, n=301, k=1, and D=3:<br/>
the bits to send would be 4 bits: 301<font color="red">?</font>. But we can start with 301<font color="red">0</font>. 3010 % 3 = 1, so to make it divisible by D=3, it should be 3012.</p>
<p>Sending procedure should be like this:</p>
<ol>
<li>Extend the <strong>n</strong> data bits with <strong>k</strong> zeros.</li>
<li>Divide by the divisor <strong>D</strong>.</li>
<li>Keep remainder, and throw away quotient.</li>
<li>Adjust <strong>k</strong> check bits by remainder.</li>
</ol>
<p>This picture below is for the case when </p>
<ol>
<li>Data bits: 1101011111</li>
<li>Check bits, <strong>k</strong> = 4 </li>
<li>Divisior, <strong>D</strong> = 10011 </li>
</ol>
<br/>
<p>Receiving procedure is the same, and need to check if the remainder is zero.</p>
<br/>
<img alt="CRC_Sender.png" src="images/socket/CRC_Sender.png" width="100%">
<br/><br/>
<br/>
<br/>
<br/>
<div class="subtitle" id="network_application">Network Applications</div>
<p>Dominant model for network applications is <strong>TCP Byte Stream</strong> model where one side writes and the other side reads. It's the building block of most applications today though ther models are there such as datagrams, real-time streams.</p>
<ol>
<li>web server http</li>
<li>skype client<br/>
   Rendezvous service that allows users not behind a NAT to call users behind a NAT.<br/>
<a href="http://www.bogotobogo.com/cplusplus/files/socket/Skype_peer_to_peer.pdf" target="_blank">An Analysis of the Skype Peer-to-Peer Internet Telephony 
Protocol by 
 Salman A. Baset and Henning Schulzrinne(pdf)</a>
</li>
<li>Bit Torrents<br/>
   Tit For Tat algorithm - gives download preference to peers that give data to you.<br/>
   Visit <a href="http://www.bogotobogo.com/cplusplus/files/socket" target="_blank">P2P</a><br/>
<a href="https://wiki.theory.org/BitTorrentSpecification" target="_blank">https://wiki.theory.org/BitTorrentSpecification</a>
</li>
</ol>
<br/>
<br/>
<br/>
<div class="subtitle_2nd" id="references">References</div>
<p><a href="http://www.bogotobogo.com/cplusplus/files/socket/bgnet_USLetter.pdf" target="_blank">Beej's Guide to Network Programming
Using Internet Sockets</a><br/>
 or get it from <a href="http://beej.us/guide/bgnet/output/print/bgnet_USLetter.pdf" target="_blank">http://beej.us/guide/bgnet/output/print/bgnet_USLetter.pdf</a></p>.
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
    <input type="hidden" name="encrypted" value="-----BEGIN PKCS7-----MIIHRwYJKoZIhvcNAQcEoIIHODCCBzQCAQExggEwMIIBLAIBADCBlDCBjjELMAkGA1UEBhMCVVMxCzAJBgNVBAgTAkNBMRYwFAYDVQQHEw1Nb3VudGFpbiBWaWV3MRQwEgYDVQQKEwtQYXlQYWwgSW5jLjETMBEGA1UECxQKbGl2ZV9jZXJ0czERMA8GA1UEAxQIbGl2ZV9hcGkxHDAaBgkqhkiG9w0BCQEWDXJlQHBheXBhbC5jb20CAQAwDQYJKoZIhvcNAQEBBQAEgYC0In+maN+zseQtRj6SJqP9kj2LLvKf0yFklTm01uHY7UwgB3YJ0MZwvi6iERXfh4x2/KVYyMzY6elATG68c3gd6gb0Pqca380dXCg2Xua8jlW0pTZ3UabUNkpYi0iIwMSUsvWKbIw9eX8cBljOrYU1CXNuk46c0Yz2J3lGG+xCZTELMAkGBSsOAwIaBQAwgcQGCSqGSIb3DQEHATAUBggqhkiG9w0DBwQI23eIgGIDbFqAgaDMolOA+os0Y06D0j9NgHZJahDCSSl3deolhu6gz8hNd0SKwNAMBDPd5LBjJ7v6QgReCprB9L2E6CVpXZwgyLnzPC/wHbQG0Qd9sc/CqbiFy2FaJodDtPbRS8mOh+aHph0pNXgZ2kRA8uqVGIRF5gc0d6wqx7+NrPK5FehCMWoGGTmfTTMlykPVQhwDAY8+QFNSbCnqih5GXX62XpkmMJWFoIIDhzCCA4MwggLsoAMCAQICAQAwDQYJKoZIhvcNAQEFBQAwgY4xCzAJBgNVBAYTAlVTMQswCQYDVQQIEwJDQTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEUMBIGA1UEChMLUGF5UGFsIEluYy4xEzARBgNVBAsUCmxpdmVfY2VydHMxETAPBgNVBAMUCGxpdmVfYXBpMRwwGgYJKoZIhvcNAQkBFg1yZUBwYXlwYWwuY29tMB4XDTA0MDIxMzEwMTMxNVoXDTM1MDIxMzEwMTMxNVowgY4xCzAJBgNVBAYTAlVTMQswCQYDVQQIEwJDQTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEUMBIGA1UEChMLUGF5UGFsIEluYy4xEzARBgNVBAsUCmxpdmVfY2VydHMxETAPBgNVBAMUCGxpdmVfYXBpMRwwGgYJKoZIhvcNAQkBFg1yZUBwYXlwYWwuY29tMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBR07d/ETMS1ycjtkpkvjXZe9k+6CieLuLsPumsJ7QC1odNz3sJiCbs2wC0nLE0uLGaEtXynIgRqIddYCHx88pb5HTXv4SZeuv0Rqq4+axW9PLAAATU8w04qqjaSXgbGLP3NmohqM6bV9kZZwZLR/klDaQGo1u9uDb9lr4Yn+rBQIDAQABo4HuMIHrMB0GA1UdDgQWBBSWn3y7xm8XvVk/UtcKG+wQ1mSUazCBuwYDVR0jBIGzMIGwgBSWn3y7xm8XvVk/UtcKG+wQ1mSUa6GBlKSBkTCBjjELMAkGA1UEBhMCVVMxCzAJBgNVBAgTAkNBMRYwFAYDVQQHEw1Nb3VudGFpbiBWaWV3MRQwEgYDVQQKEwtQYXlQYWwgSW5jLjETMBEGA1UECxQKbGl2ZV9jZXJ0czERMA8GA1UEAxQIbGl2ZV9hcGkxHDAaBgkqhkiG9w0BCQEWDXJlQHBheXBhbC5jb22CAQAwDAYDVR0TBAUwAwEB/zANBgkqhkiG9w0BAQUFAAOBgQCBXzpWmoBa5e9fo6ujionW1hUhPkOBakTr3YCDjbYfvJEiv/2P+IobhOGJr85+XHhN0v4gUkEDI8r2/rNk1m0GA8HKddvTjyGw/XqXa+LSTlDYkqI8OwR8GEYj4efEtcRpRYBxV8KxAW93YDWzFGvruKnnLbDAF6VR5w/cCMn5hzGCAZowggGWAgEBMIGUMIGOMQswCQYDVQQGEwJVUzELMAkGA1UECBMCQ0ExFjAUBgNVBAcTDU1vdW50YWluIFZpZXcxFDASBgNVBAoTC1BheVBhbCBJbmMuMRMwEQYDVQQLFApsaXZlX2NlcnRzMREwDwYDVQQDFAhsaXZlX2FwaTEcMBoGCSqGSIb3DQEJARYNcmVAcGF5cGFsLmNvbQIBADAJBgUrDgMCGgUAoF0wGAYJKoZIhvcNAQkDMQsGCSqGSIb3DQEHATAcBgkqhkiG9w0BCQUxDxcNMTUwOTA2MTYwNDAxWjAjBgkqhkiG9w0BCQQxFgQUuyx70nay4O6eJQs3x4WiAm4/7DkwDQYJKoZIhvcNAQEBBQAEgYAN7yS/34G8dBK6CfFf5g4rQk/H8s7D/aUmIzppGWOoXR7nZuXQo99wSBlQsPdeFtB+a+NNapf6lC4ibUTjgSpbu1gscGHH4Y+QtXl03bt5qgaSoFhZsCJKubwRHPHGHDGVx+tQmQ2DHk09lXjjL61FpB6iqkiFFvw4vfixsoeI6g==-----END PKCS7-----
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
    <input type="hidden" name="encrypted" value="-----BEGIN PKCS7-----MIIHRwYJKoZIhvcNAQcEoIIHODCCBzQCAQExggEwMIIBLAIBADCBlDCBjjELMAkGA1UEBhMCVVMxCzAJBgNVBAgTAkNBMRYwFAYDVQQHEw1Nb3VudGFpbiBWaWV3MRQwEgYDVQQKEwtQYXlQYWwgSW5jLjETMBEGA1UECxQKbGl2ZV9jZXJ0czERMA8GA1UEAxQIbGl2ZV9hcGkxHDAaBgkqhkiG9w0BCQEWDXJlQHBheXBhbC5jb20CAQAwDQYJKoZIhvcNAQEBBQAEgYC0In+maN+zseQtRj6SJqP9kj2LLvKf0yFklTm01uHY7UwgB3YJ0MZwvi6iERXfh4x2/KVYyMzY6elATG68c3gd6gb0Pqca380dXCg2Xua8jlW0pTZ3UabUNkpYi0iIwMSUsvWKbIw9eX8cBljOrYU1CXNuk46c0Yz2J3lGG+xCZTELMAkGBSsOAwIaBQAwgcQGCSqGSIb3DQEHATAUBggqhkiG9w0DBwQI23eIgGIDbFqAgaDMolOA+os0Y06D0j9NgHZJahDCSSl3deolhu6gz8hNd0SKwNAMBDPd5LBjJ7v6QgReCprB9L2E6CVpXZwgyLnzPC/wHbQG0Qd9sc/CqbiFy2FaJodDtPbRS8mOh+aHph0pNXgZ2kRA8uqVGIRF5gc0d6wqx7+NrPK5FehCMWoGGTmfTTMlykPVQhwDAY8+QFNSbCnqih5GXX62XpkmMJWFoIIDhzCCA4MwggLsoAMCAQICAQAwDQYJKoZIhvcNAQEFBQAwgY4xCzAJBgNVBAYTAlVTMQswCQYDVQQIEwJDQTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEUMBIGA1UEChMLUGF5UGFsIEluYy4xEzARBgNVBAsUCmxpdmVfY2VydHMxETAPBgNVBAMUCGxpdmVfYXBpMRwwGgYJKoZIhvcNAQkBFg1yZUBwYXlwYWwuY29tMB4XDTA0MDIxMzEwMTMxNVoXDTM1MDIxMzEwMTMxNVowgY4xCzAJBgNVBAYTAlVTMQswCQYDVQQIEwJDQTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEUMBIGA1UEChMLUGF5UGFsIEluYy4xEzARBgNVBAsUCmxpdmVfY2VydHMxETAPBgNVBAMUCGxpdmVfYXBpMRwwGgYJKoZIhvcNAQkBFg1yZUBwYXlwYWwuY29tMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBR07d/ETMS1ycjtkpkvjXZe9k+6CieLuLsPumsJ7QC1odNz3sJiCbs2wC0nLE0uLGaEtXynIgRqIddYCHx88pb5HTXv4SZeuv0Rqq4+axW9PLAAATU8w04qqjaSXgbGLP3NmohqM6bV9kZZwZLR/klDaQGo1u9uDb9lr4Yn+rBQIDAQABo4HuMIHrMB0GA1UdDgQWBBSWn3y7xm8XvVk/UtcKG+wQ1mSUazCBuwYDVR0jBIGzMIGwgBSWn3y7xm8XvVk/UtcKG+wQ1mSUa6GBlKSBkTCBjjELMAkGA1UEBhMCVVMxCzAJBgNVBAgTAkNBMRYwFAYDVQQHEw1Nb3VudGFpbiBWaWV3MRQwEgYDVQQKEwtQYXlQYWwgSW5jLjETMBEGA1UECxQKbGl2ZV9jZXJ0czERMA8GA1UEAxQIbGl2ZV9hcGkxHDAaBgkqhkiG9w0BCQEWDXJlQHBheXBhbC5jb22CAQAwDAYDVR0TBAUwAwEB/zANBgkqhkiG9w0BAQUFAAOBgQCBXzpWmoBa5e9fo6ujionW1hUhPkOBakTr3YCDjbYfvJEiv/2P+IobhOGJr85+XHhN0v4gUkEDI8r2/rNk1m0GA8HKddvTjyGw/XqXa+LSTlDYkqI8OwR8GEYj4efEtcRpRYBxV8KxAW93YDWzFGvruKnnLbDAF6VR5w/cCMn5hzGCAZowggGWAgEBMIGUMIGOMQswCQYDVQQGEwJVUzELMAkGA1UECBMCQ0ExFjAUBgNVBAcTDU1vdW50YWluIFZpZXcxFDASBgNVBAoTC1BheVBhbCBJbmMuMRMwEQYDVQQLFApsaXZlX2NlcnRzMREwDwYDVQQDFAhsaXZlX2FwaTEcMBoGCSqGSIb3DQEJARYNcmVAcGF5cGFsLmNvbQIBADAJBgUrDgMCGgUAoF0wGAYJKoZIhvcNAQkDMQsGCSqGSIb3DQEHATAcBgkqhkiG9w0BCQUxDxcNMTUwOTA2MTYwNDAxWjAjBgkqhkiG9w0BCQQxFgQUuyx70nay4O6eJQs3x4WiAm4/7DkwDQYJKoZIhvcNAQEBBQAEgYAN7yS/34G8dBK6CfFf5g4rQk/H8s7D/aUmIzppGWOoXR7nZuXQo99wSBlQsPdeFtB+a+NNapf6lC4ibUTjgSpbu1gscGHH4Y+QtXl03bt5qgaSoFhZsCJKubwRHPHGHDGVx+tQmQ2DHk09lXjjL61FpB6iqkiFFvw4vfixsoeI6g==-----END PKCS7-----
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

