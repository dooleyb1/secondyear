JFLAGS = -cp
JC = javac
JVM = java
TEST = hamcrest-core-1.3.jar:junit-4.11.jar:. 
BUILD = junit-4.11.jar:. 

.SUFFIXES: .java .class .jar

default:
	$(JC) $(JFLAGS) $(BUILD) *.java

run:
	$(JVM) $(JFLAGS) $(TEST) SortComparison

time:
	$(JVM) $(JFLAGS) $(TEST) SortComparisonTest

clean:
	$(RM) *.class

##Need To compile project with the juinity jar
##To run need to run the Test file with the hamcrest and Junit

