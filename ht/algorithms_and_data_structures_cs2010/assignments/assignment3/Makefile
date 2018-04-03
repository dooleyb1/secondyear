JFLAGS = -cp
JC = javac
JVM = java

ZIP = AdjMatrixEdgeWeightedDigraph.java Bag.java CompetitionDijkstra.java CompetitionFloydWarshall.java CompetitionTests.java DijkstraSP.java DirectedEdge.java EdgeWeightedDiGraph.java EdgeWeightedDirectedCycle.java FloydWarshall.java IndexMinPQ.java 1000EWD.txt dualNodeEWD.txt emptyEWD.txt invalidEWD.txt singleNodeEWD.txt tinyEWD.txt negativeCycleEWD.txt

TEST = .:hamcrest-core-1.3.jar:junit-4.11.jar org.junit.runner.JUnitCore 
RUN = .:jars:hamcrest-core-1.3.jar:json-simple-1.1.jar
BUILD = *:. 


.SUFFIXES: .java .class .jar

default:
	$(JC) $(JFLAGS) $(BUILD) *.java

dijkstra:
	$(JVM) $(JFLAGS) $(RUN) CompetitionDijkstra

test:
	$(JVM) $(JFLAGS) $(TEST) CompetitionTests
		
floyd:
	$(JVM) $(JFLAGS) $(RUN) CompetitionFloydWarshall

clean: 
	$(RM) *.class

zip:
	zip submission.zip $(ZIP)

##Need To compile project with the juinity jar
##To run need to run the Test file with the hamcrest and Junit

