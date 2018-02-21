JFLAGS = -cp
JC = javac
JVM = java
TEST = hamcrest-core-1.3.jar:junit-4.11.jar:json-simple-1.1(1).jar. 
BUILD = junit-4.11.jar:json-simple-1.1(1).jar. 

.SUFFIXES: .java .class .jar

default:
	$(JC) $(JFLAGS) $(BUILD) *.java

kmp:
	$(JVM) $(JFLAGS) $(TEST) KMPSearch

kmptest:
	$(JVM) $(JFLAGS) $(TEST) KMPSearchTest
		
tst:
	$(JVM) $(JFLAGS) $(TEST) TST

tst:
	$(JVM) $(JFLAGS) $(TEST) TSTTest

clean:
	$(RM) *.class

##Need To compile project with the juinity jar
##To run need to run the Test file with the hamcrest and Junit

