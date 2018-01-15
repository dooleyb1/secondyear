
#First things to do in [Ubuntu](https://www.ubuntu.com/)

This is an example from my course on systems 
programming (<a href="https://down.dsg.cs.tcd.ie/cs2014">CS2014</a>),
the canonical URL for this is 
<a href="https://down.dsg.cs.tcd.ie/cs2014/examples/shell/README.html">here</a>.

If you're installing Ubuntu on a machine at home (a good thing to do) then
you'll be after things like [HOWTO make an install USB
stick](https://askubuntu.com/questions/307802/how-to-install-ubuntu-on-a-usb-stick),
and [things to do after installing
Ubuntu](http://www.omgubuntu.co.uk/2016/04/10-things-to-do-after-installing-ubuntu-16-04-lts).
Those are fine things but here we'll assume you've installed Ubuntu 16.04 or
better, i.e. we assume the machines in LG12.


##Files in this example:

- [README.md](README.md) - this file in markdown format
- [README.html](README.html) - this file, in HTML format (do ```make html``` to update that from .md)
- [Makefile](Makefile) - to build the example and HTML (there's a clean target too)
- [filecount.sh](filecount.sh) - a shell script to count files (see below)
- [chkpwned.sh](chkpwnd.sh) - a shell script to check if a password is on a (BIG) list of hashes
- [websites.sh](websites.sh) - a shell script to check if some websites are "up"
- [websites.lst](websites.lst) - the list of (hostnames of) websites to check
- [emails.lst](emails.lst) - the set of emails to which to send a report when a website is not up

After running ```'make'``` then this file will be produced (if all
goes well):

##Log in to the system

You should have your username and password, let's say those are
```student100``` and whatever you've been given for a password.
Logging in to the GUI is pretty obvious (I hope:-).

Once logged in, you want to get a command line/shell. There are
GUI based ways to fire up a shell, but ```ctrl-alt-t``` also
works and is useful in case there's no obvious icon for a command
line/shell. (There are loads of online guides to getting 
started with the shell, [here's](http://www.makeuseof.com/tag/a-quick-guide-to-get-started-with-the-linux-command-line/) one.

Once you have a shell, you'll see a prompt, something like:

		student100:~$ _ 

From there you can type commands to the shell.

The shell is (mostly) just another program that  interprets
your commands and runs the relevant programs, so e.g. if
you want to know where your "home" directory is on the
disk you can type:

		student100:~$ /bin/pwd
		/home/student100

And you see that the directory ```/home/student100``` is,
as you might expect, your home directory. (Your home
directory is also stored in an [environment variable](https://www.digitalocean.com/community/tutorials/how-to-read-and-set-environmental-and-shell-variables-on-a-linux-vps), so 
this also works:

		student100:~$ echo $HOME
		/home/student100


You can now use the ```man``` command to get some
help, or search online for loads of explanations as to
how to use a shell.

------------------------------------------------------

##Careful now...

You'll also need to be non-dumb of course, as there
are commands that can delete or break things. (Hopefully
not too badly in the lab!) The ```rm``` command in
particular is one to be careful of, especially if you
ever tell it to recursively delete an entire directory tree via
the ```-rf``` [command line option](https://blog.mafr.de/2007/08/05/cmdline-options-in-shell-scripts/)!
So: BE CAREFUL!

There are a variety of different shells available, with
minor variations in syntax when you start to use more
complicated commands and scripting. I use the ```bash```
shell myself and am happy with that, you may choose
another one. There's a ```chsh``` command that you
can use to change the shell you're using, check out
it's man page if you want to switch.

You can also personalise the shell (e.g. to set the
kind of prompts I show here), usually via editing
a "hidden" file in your home directory. In the case
of the bash shell, that file is ```$HOME/.bashrc```.
("Hidden" file and directory names start with a dot and are 
not displayed by the ```ls``` command unless you
ask to see 'em via the ```-a``` command line option.
They useful so as to not clutter things up, but
they are just normal files and directories in the filesystem otherwise.)

As a last preliminary, you can change your password
via the ```passwd``` command. If you've been given
a crappy easily guessed password, then changing
that is probably a good plan. But if you forget
your new password, expect me, the TA and demonstrators
to be unhappy with you! 

You should also expect
that someone else is likely to try guess your password,
especially if a system is accessible to the 
Internet via SSH and allows password logins. In
that case, the chances are that brute-force password attacks
will be made against the system every few minutes.
(As correctly stated [here:](https://serverfault.com/questions/801546/someone-is-trying-to-brute-force-ssh-access-to-my-server)
"Unfortuntately, this is absolutely normal and something every SSH server experiences. Welcome to the internet.";-)
 
Most systems should have tooling to avoid that problem, but
not all do, and you as a user don't know when a
system is well protected or not - so ONLY EVER USE
STRONG PASSWORDS! Note that "STRONG" here really means
"in practice, not guessable via the network" so "123456"
is not strong, but "asoduasdhslkjadfadjksl" is even
though it hasn't much real entropy.

The next thing we want to do is clone the github repository
for the course and then run the [broken malloc](../bm/README.html)
example.


------------------------------------------------------

##Clone [this repo](https://github.com/sftcd/cs2014)...

I usually keep repositories like this in a ```code```
directory below my home directory so...

		student100:~$ mkdir code	
		student100:~$ cd code	

Another abbreviation for $HOME is the tilda character (```~```) which 
is why that may be part of the prompt depending on
your preferences. So, if you're logged in as student100, the following names for that
```code``` directory are equivalent:

		/home/student100/code/
		~/code/
		$HOME/code/

Of course for ```student666```, ```$HOME/code```
and ```~/code``` will refer to ```/home/student666/code/``` instead.
(Note that your ```$HOME``` might not be ```/home/$USER``` - that's
up to the sysadmins to decide when they create accounts.)

Now you want to get a copy of this repository:

		student100:~/code$ git clone https://github.com/sftcd/cs2014.git
		Cloning into 'cs2014'...
		remote: Counting objects: 139, done.
		remote: Compressing objects: 100% (89/89), done.
		remote: Total 139 (delta 73), reused 111 (delta 48), pack-reused 0
		Receiving objects: 100% (139/139), 714.98 KiB | 587.00 KiB/s, done.
		Resolving deltas: 100% (73/73), done.

The output you see will differ from the above as I'll have modified
the repo by then.

And now you can go build and run the first ```broken-malloc``
example:

		student100:~/code$ cd cs2014/examples/bm
		student100:~/code/cs2014/examples/bm$ make
		gcc     broken-malloc.c broken-malloc.h   -o broken-malloc
		student100:~/code/cs2014/examples/bm$ ./broken-malloc 
		Malloc 1 succeeded!
		Malloc 2 failed!
		Malloc 3 succeeded!
		Malloc 4 succeeded!
		Malloc 5 failed!
		Malloc 6 succeeded!
		Malloc 7 succeeded!
		Malloc 8 succeeded!
		Malloc 9 succeeded!
		Malloc 10 succeeded!
		Tests: 10, fails: 2
		student100:~/code/cs2014/examples/bm$ 

Yay! Success!

From now on, for clarity, I'll just show prompts as ```$``` and
omit the rest of the verbiage. (That stuff is useful though if
you have a number of windows with a shell open, so's you don't
type a command into the wrong place.)

Once you've gotten this far, I'd suggest you play about with the
```broken-malloc``` example, and see what you can change. Trying to
implement and test the ```realloc()``` function is a fine
plan.
If you manage to make it better (or interestingly different),
and you have a github.com account,
then feel free to submit a [pull-request](https://help.github.com/articles/about-pull-requests/) (PR) and if it's good I'll
accept that and maybe use it another year. Put your name
somewhere in the PR I can see it, so's I know who's submitted
what. (I'll even take typo-fixes, as I'm pretty bad at getting
rid of all the typos in my text/code;-)

------------------------------------------------------

##Editing files... probably using ```vi```

In order to play about with the example, you need a way to
edit files. My preferred tool for that is ```vi``` which is
a venerable editor that is still excellent today. 
There are loads of [vi cheatsheets](https://www.smashingmagazine.com/2010/05/vi-editor-linux-terminal-cheat-sheet-pdf/)
available online and spending a bit of time getting 
familiar with ```vi``` is well worthwhile - you'll be
using it for many years to come, so getting speedy at
editing with it is really a MUST. You don't need to
do all that at first though, it's fine to learn as you
go and get faster later.

Anyway, if
you wanted to edit the ```broken-malloc.h``` file
then you simply say ```vi broken-malloc.h``` while in
the right directory and off you go. If you've not
checked out a cheatsheet, you'll likely get stuck
there and never figure out that to exit the editor
without saving changes you need to type ```:q!```,
and to exit saving changes then use ```:wq```
(The ```:``` character takes you out of "visual"
mode, to a vi-internal command line, but go read
a cheatsheet for more.)

If you've not used ```vi``` before this is a really
good thing to ask about in the first lab.

There are plenty of other editors available on many
linux systems, from the most basic ```ed``` to 
```nano``` to
```emacs``` which I think is a bit of a monster. Many
people though just love and swear by ```emacs```  (for
too much information about emacs, go [here](https://www.gnu.org/software/emacs/).)
In any case, 
try out various editors if you like and see which you find most comfortable.

BTW, ```ed``` is something I've not used in 
many years, and you'll only likely need to use it
if editing a file over a gigantically crappy network
that's dropping almost all packets or that has
multiple satellite hops on the path. But the
fact that tool is still there shows up a strength
of UNIX and GNU/Linux - pretty much no matter what
scenario you hit, there's a tool that works as well
as can be expected for that scenario.

If you can't remember the name of a shell command,
that's pretty normal, you can use ```man -k <keyword>```
to try get help, or in many cases your shell will
be setup with ["tab completion"](https://stackoverflow.com/questions/5570795/how-does-bash-tab-completion-work)
which also helps when you kinda-remember.

------------------------------------------------------

##Piping hot shell commands...

There are loads of commands available to you
in a typical shell and you might want to use the
output of one command as the input of another.
Not surprisingly, that works just fine.

Let's say you want to count how many files
exist below your home directory, including
all sub-directories (like the ```code```
directory we created a minute ago). Then
you can use the ```find``` and ```wc```
commands to do that by feeding the output
of ```find``` into ```wc``` like this:

		$ find $HOME -type f  | wc | awk '{print $1}'

The pipe character (```|```) basically feeds the
output from the command on the left into the
subsequent command. (Yes, that uses the 
```stdout``` and ```stdin``` special files
the same as C programs do.)

In the example above, we also tidied up the 
output using ```awk``` which is a bit of a swiss-army
knife for the shell - it can do nearly anything
you want.

Check out the man pages for ```find```, ```wc```,
and ```awk``` for more details, and [here's](https://bash.cyberciti.biz/guide/Pipes)
an introduction to pipes in the shell.

Another handy thing is to be able to input file
contents to a shell command or to send the output 
of a shell command to a file, so if you wanted to
for some reason record how many files you have
each time you logged in you could do this:

		$ find $HOME -type f  | wc | awk '{print $1}' >"howmanyfiles.`date --rfc-3339="date"`"

The greater-than character (```>```) causes the
output of the command we ran earlier to be sent
to a new file.

Today (August 3rd 2017) that'll create a file called 
```howmanyfiles.2017-08-03``` that just contains
the number of files found. (We're sneakily
using the backquote character (\`) there
to cause the ```date``` command to be 
executed as the file is being created:-)

It seems a little untidy to create a new file each time
though, so we can also get the command ouput appended
to an existing file, and include the number of 
files and the date on one line of the file. That'd
look like:

		$ find $HOME -type f  | wc | awk '{print $1 " " strftime("%Y-%m-%d") }' >>howmanyfiles.txt

That uses ```awk```'s built-in strftime function, and the
content of the file will end up looking like:

		$ cat howmanyfiles.txt
		197 2017-08-03
		199 2017-08-04
		202 2017-08-08

(Note that we didn't run the command over the August
bank-holiday weekend - it is possible to do too much work!)

To show reading that file as input we can again use ```awk```
magic to calculate the average number of files as follows:

		$ cat howmanyfiles.txt | awk '{ sum += $1; n++ } END { if (n > 0) print sum / n; }'
		199.333

And another way to do that is to use the ```<``` character to
provide the input to the ```awk``` command as follows:

		$ awk '{ sum += $1; n++ } END { if (n > 0) print sum / n; }' < howmanyfiles.txt
		199.333


------------------------------------------------------

##A little shell scripting...

Now that you have a shell and can enter commands, you'll
quickly tire of typing the same sets of commands
over and over. So you'll want to script up sets of
useful commands into shell scripts that you can run
in a single command. You'll also of course tend to
forget the exact syntax for things like that last
awk command and will type things wrong all the time,
so putting more complicated shell commands into a
script is also much more reliable. 

In fact, you
can do pretty much any programming using shell scripts
if you like, it's just a good bit slower to use
the shell (which forks processes a lot and is
interpreted and hence slower) than a compiled
C program. OTOH, shell scripts can be quicker to
create and small ones can be very handy.

[Here's](https://www.st-andrews.ac.uk/ITS/training/unix/unix7.html) a 
reasonable description of how commands map to processes in
Linux. The basic idea is that each time you run a program
(e.g. ```rndbytes``` or ```awk```) the operating system
starts a process that runs until it's done. It gets
more complicated that that (e.g. with [threads](http://www.thegeekstuff.com/2012/03/linux-threads-intro/))
but we won't go there yet.

Let's imagine you do want to be anal about the 
number of files below your home directory and how
the average changes. We can put the commands
above into a file and then run that each time.

Typically people put scripts like that in a ```bin```
directory just below their home directory...

		$ mkdir $HOME/bin
		$ cd $HOME/bin
		$ vi filecount.sh
		... edits done here ...
		$ chmod u+x filecount.sh
		$ cd $HOME
		$ $HOME/bin/filecount.sh

The ```chmod``` command sets the file permissions to that the
owner of the file can execute it.

And let's see what we might put in ```filecount.sh```...

		#!/bin/bash
		
		# This counts and records the number of files below $HOME
		# and reports the current count and average over time.
		
		# Where to start counting from...
		TOP=$HOME
		
		# where to keep the records
		RECORDFILE=howmanyfiles.txt
		
		# count the files and record the count and date in a file
		find $TOP -type f  | wc | awk '{print $1 " " strftime("%Y-%m-%d") }' >>$RECORDFILE
		
		# take today's count from last line...
		lastcount=`tail -1 $RECORDFILE | awk '{print $1}'`
		# take dates from first and last line...
		lastdate=`tail -1 $RECORDFILE | awk '{print $2}'`
		firstdate=`head -1 $RECORDFILE | awk '{print $2}'`
		
		# figure out the average
		average=`cat howmanyfiles.txt | awk '{ sum += $1; n++ } END { if (n > 0) print sum / n; }'`
		
		# say what we found
		echo "Between $firstdate and $lastdate, there were on average $average files below $TOP" 
		echo "Last I looked (using $0) the total was $lastcount"
		
		exit 0

If you run that with the content of ```howmanyfiles.txt``` as per the above
then you should see...

		$ $HOME/bin/filecount.sh 
		Between 2017-08-03 and 2017-08-08, there were on average 199.333 files below /home/student100
		Last I looked (using /home/student100/bin/filecount.sh) the total was 202

Of course, that script is pretty broken, so it's only to illustrate
how the commands we used earlier can be put into a script and isn't
meant to be used in real life.

Minor notes:

- ```$HOME/bin``` is a traditional place to put useful scripts, and adding
```$HOME/bin``` to your ```$PATH``` inside your ```$HOME/.bashrc``` is
a commonly good thing to do
- The first line of the shell script above (```#!/bin/bash```) is a 
special one (called "She-Bang") that tells the shell which 
command intepreter to use when running the script (after you've
done the ```chmod u+x``` thing). A python script would likely start
with ```#!/usr/bin/python``` for example.
- You've probably guessed that ```#``` is the comment character
for bash commands:-)

------------------------------------------------------

##The path to righteousness (or to binaries)...

When we run a command from the shell, the shell and operating
system search through the set of directories in the ```$PATH```
environment variable, to try find the command to run. So if
you want to run ```find``` for example, then program that
will usually be run is found in a file called ```/usr/bin/find```
but we don't have to type the ```/usr/bin/``` part of that
because the ```$PATH``` environment variable contains ```/usr/bin```.
You can look at the ```$PATH``` environment variable just you can
look at the ```$HOME``` environment variable, and you can
also add to it, so that scripts in ```$HOME/bin``` can be
run without having to provide the full pathname of the file.
So, you might see/do this...

		$ echo $PATH
		/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/snap/bin
		$ export PATH="/home/student100/bin:$PATH"
		$ echo $PATH
		/home/student100/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/snap/bin
		$ cd $HOME/code
		$ filecount.sh
		Between 2017-08-08 and 2017-08-08, there were on average 202 files below /home/student100
		Last I looked (using filecount.sh) the total was 202

Note that there are some shell "built-ins" (e.g. the ```cd``` command) 
that don't live as files anywhere in the filesystem - see ```man builtins``` for 
details of those. And if you're using a smaller embedded 
system based on the popular [busybox](https://www.busybox.net/)
tool then there may be lots more commands built in to the
shell itself, compared to in a typical GNU/Linux distribution.

All that kind of thing is fairly distribution-specific and will
vary from system to system.

Anyway the last run above shows up a fairly obvious bug in that script that I'd like you
to tell me about.... What's the bug and the fix?


------------------------------------------------------

##Going a bit further...

As further exercises for you: 

- you can change what the script counts, and,

- if you're
keen try figure out how to run the script every day, say at midnight,
using ``cron``, or,  

- since I'm not sure if you have permission to use ```cron```
on the LG12 boxes, you could figure out how to run it
each time you login, but then you'll need to remove duplicate counts
one way or another if you log in a bunch of times in one day, and lastly,

- you could also usefully extend this to do error checking (the
bash variable ```$?``` records the return value of the commands
run, so you could exit without damaging the ```$RECORDFILE``` if
that's not zero for example), and the whole thing could be a lot
more robust against corruption of the ```$RECORDFILE```.


------------------------------------------------------

##A password hashing example

This example isn't that interesting but is timely, as I knocked up
this script while preparing the course...

On 20170803 Troy Hunt [published](https://haveibeenpwned.com/Passwords) the 
sha-1 hashes of a set of 320 million
unique passwords he's been collecting from hacked web site leaks over
the last few years. While I'd previously never used his online checking
service (sending a password hash over the network to who knows where? Yuk!),
I was happy to download the 6GB of compressed hashes (expands to 12GB on
disk) so I could check for what passwords might be in the leaked set.

Anyway here's the script I knocked up for that:


		#!/bin/bash

		# A v. basic script to check if (the hash of) a password is present
		# in the list of passwords previously leaked.
		# The list of 320 million hashes has been released (20170804) from
		# https://haveibeenpwned.com/Passwords for research purposes.


		# initial list has 306 million entries
		LIST=$HOME/data/pwned-passwords-1.0.txt
		# update has another 14M, apparently with mixed-case variations
		# that got missed when assembling the 306M list
		UPDATE1=$HOME/data/pwned-passwords-update-1.txt

		if [[ ! -f $LIST || ! -f $UPDATE1 ]]
		then
			echo "Sorry, you need the lists, nothing to check for now"
			exit 1
		fi


		# If you do put a pwd on the command line, it'll be visible to
		# ps, for about 30 seconds, so that's a bad plan:-)
		if [[ $1 != "" ]]
		then
			PWD=$1
		else
			# Read Password without it being visible to system for a long time
			echo -n Password: 
			read -s PWD
			echo
			#PWD="password"
			#echo "checking $PWD"
		fi

		# note that the password is visible here briefly, FIXME
		hash=`echo -n $PWD  | openssl sha1 | awk '{print $2}' | awk 'BEGIN { getline; print toupper($0) }'`
		# maybe don't display this?
		echo "Hash is $hash"

		# There has to be a quicker way, but 30s isn't that bad
		count1=`grep -c $hash $LIST`
		count2=`grep -c $hash $UPDATE1`

		count=$((count1+count2))

		echo "Found $count occurrences in $LIST and $UPDATE1"

		exit 0

That takes around 30 seconds to run on my laptop. The files with
the lists are just simply one upper-case sha-1 hash per line, so...


		$ head ~/data/pwned-passwords-1.0.txt
		000000005AD76BD555C1D6D771DE417A4B87E4B4
		00000000A8DAE4228F821FB418F59826079BF368
		00000000DD7F2A1C68A35673713783CA390C9E93
		00000001E225B908BAC31C56DB04D892E47536E0
		00000008CD1806EB7B9B46A8F87690B2AC16F617
		0000000A0E3B9F25FF41DE4B5AC238C2D545C7A8
		0000000A1D4B746FAA3FD526FF6D5BC8052FDB38
		0000000CAEF405439D57847A8657218C618160B2
		0000000FC1C08E6454BED24F463EA2129E254D43
		00000010F4B38525354491E099EB1796278544B1
		... 320 million more...


Noteworthy:

- We're setting (environment variable) like ```LIST``` and refer to
those as ```$LIST``` to access the value (those aren't typed btw,
and don't have to be upper-case)
- We've used ```if``` statements, there are also loops in bash
- We've used ```$1``` for the first command line argument 
(as you no doubt by now expect ```$0``` is the name of the
script)
- The ```read``` built-in let's us get (non-argument) input from the command line
- We even did a bit of arithmetic with ```count=$((count1+count2))```
- All the bracket stuff is hard to remember (for me), and there
are loads of ways to do things, just pick one and use that while
you remember it.
- Explain the meat of that script, which is:

			    hash=`echo -n $PWD  | openssl sha1 | awk '{print $2}' | awk 'BEGIN { getline; print toupper($0) }'`


Possible improvements:

- Ditch the ```$1``` stuff, dangerous!
- Don't expose the ```$PWD``` on the command line (from ```echo```)
  even if only monentarily
- Make it faster! Lots of scope for playing there.

As I said, that script isn't that interesting but happened to be
timely, so I added it here. One question for you though - any idea
why I chose the ```-c``` argument to grep? Answers on a postcard
please:-)

------------------------------------------------------

## A script to check websites are "up"

The [websites.sh](websites.sh) example shows a common pattern in systems admin: I need 
to do a quick check on a thing, and maybe automate that
check being done periodically.

Note: There are *much* better answers for monitoring
systems, from simple things such as monit to ever more
complex commercial monitoring systems. So you shouldn't
do this at all (but you will do stuff like this from
time to time regardless:-)

Our task here is to check if some websites are "up"
and to send mail to some recipients if not.

The pattern I followed here is:

- Search the web for a script someone else had done
- Look at and discard a few options
- One seems ok-ish, try run that
- See that script is broken
- Fix/improve script
- Run script
- *NOT DONE* Set script to run automatically via cron 

Here's the code:

		#!/bin/bash
		
		#set -x
		
		# modified from https://serverfault.com/questions/282215/script-to-automatically-test-if-a-web-site-is-available downloaded from there on 20170922
		# no idea of copyright
		
		# SF changes
		# - renamed ".lst" files to take out abs path, need to be put back for cron
		# - added check of mailx being installed and cfg files being readable
		# - added use of mktemp
		# - 200 is not the only good response, 30x's tend to be too:-) Added -L to curl
		# - added "https://" before the thing to check:-)
		
		# check if the mail client we need is installed, and give
		# an error if not
		MAILX=`which mailx`
		if [[ $? != 0 || $MAILX == "" ]]
		then
			echo "$0: You need mailx - to get that 'sudo apt install mailutils'"
			exit 1
		fi
		
		# list of websites. each website in new line. leave an empty line in the end.
		LISTFILE=websites.lst
		# Send mail in case of failure to. leave an empty line in the end.
		EMAILLISTFILE=emails.lst
		
		if [[ ! -r $LISTFILE ]]
		then
			echo "$0: Can't read $LISTFILE, please fix"
			exit 1
		fi
		if [[ ! -r $EMAILLISTFILE ]]
		then
			echo "$0: Can't read $EMAILLISTFILE, please fix"
			exit 1
		fi
		
		WORKDIR=`mktemp -d /tmp/websitesXXXX`
		if [[ ! -d $WORKDIR ]]
		then
			echo "$0: mktemp failed to make $WORKDIR - odd that, exiting"
			exit 1
		fi
		
		
		# `Quiet` is true when in crontab; show output when it's run manually from shell.
		# Set THIS_IS_CRON=1 in the beginning of your crontab -e.
		# else you will get the output to your email every time
		if [ -n "$THIS_IS_CRON" ]; then QUIET=true; else QUIET=false; fi
		
		function test {
		  response=$(curl -L --write-out %{http_code} --silent --output /dev/null https://$1)
		  filename=$( echo $1 | cut -f1 -d"/" )
		  if [ "$QUIET" = false ] ; then echo -n "$p "; fi
		
		  if [ $response -eq 200 ] ; then
		    # website working
		    if [ "$QUIET" = false ] ; then
		      echo -n "$response "; echo -e "\e[32m[ok]\e[0m"
		    fi
		    # remove .temp file if exist.
		    if [ -f $WORKDIR/$filename ]; then rm -f $WORKDIR/$filename; fi
		  else
		    # website down
		    if [ "$QUIET" = false ] ; then echo -n "$response "; echo -e "\e[31m[DOWN]\e[0m"; fi
		    if [ ! -f $WORKDIR/$filename ]; then
		        while read e; do
		            # using mailx command
		            echo "$p WEBSITE DOWN" | $MAILX -s "$1 WEBSITE DOWN" $e
		            # using mail command
		            #mail -s "$p WEBSITE DOWN" "$EMAIL"
		        done < $EMAILLISTFILE
		        echo > $WORKDIR/$filename
		    fi
		    # remove .temp file if exist.
		    if [ -f $WORKDIR/$filename ]; then rm -f $WORKDIR/$filename; fi
		  fi
		}
		
		# main loop
		while read p; do
		  test $p
		done < $LISTFILE
		
		# clean up workdir, but a little carefully - don't
		# want to delete the working directory if that 
		# env var got unset!
		if [[ "$WORKDIR" != "" ]]
		then
			rm -rf $WORKDIR
		fi
		
		
Noteworthy:

- The process (see early comments)
- ```set -x``` for debugging bash scripts (show that)
- Checking existence of commands (only half-done here really)
- Checking expected inputs - those go wrong in loads of ways in real life
- Creating temporary files/directories via ```mktemp``` and (carefully) cleaning those up on exit (not always here)
- The bash function ```test``` that also uses a new ```$1``` (not command line) argument
- Using ```$()``` instead of backquotes
- if/then/else/fi conditionals
- while loop


