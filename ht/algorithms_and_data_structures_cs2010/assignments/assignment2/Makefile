JFLAGS = -cp
JC = javac
JVM = java
TEST = *:. 
BUILD = *:. 

.SUFFIXES: .java .class .jar

default:
	$(JC) $(JFLAGS) $(BUILD) KMPSearch.java KMPSearchTest.java

kmp:
	$(JVM) $(JFLAGS) $(TEST) KMPSearch

kmptest:
	$(JVM) $(JFLAGS) $(TEST) KMPSearchTest
		
tst:
	$(JVM) $(JFLAGS) $(TEST) TST

tsttest:
	$(JVM) $(JFLAGS) $(TEST) TSTTest

clean: 
	$(RM) *.class

##Need To compile project with the juinity jar
##To run need to run the Test file with the hamcrest and Junit

