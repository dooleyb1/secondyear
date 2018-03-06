SOURCES=*.java
ORIGINAL=original/*.java
PREQUEUE=pre_priority/*.java
NONPRE=non_pre_fifo/*.java
CLASSES=osp/*/*.class
OPTS=

all: build

build: $(CLASSES)
	@(test -d osp/tmp || \
		(mkdir -p osp/tmp; make) \
		|| (echo "Something wrong: Can't create the directory for the *.class files"; exit 1))

$(CLASSES): $(SOURCES) 
	javac -g -classpath .:$(CLASSPATH):OSP.jar -d . $(SOURCES)

run: 	build
	java -classpath .:$(CLASSPATH):OSP.jar osp.OSP -noGUI $(OPTS)

gui: 	build
	java -classpath .:$(CLASSPATH):OSP.jar osp.OSP $(OPTS)

demo:
	java -classpath .:$(CLASSPATH):Demo.jar osp.OSP $(OPTS)

debug: 	build
	jdb -classpath .:$(CLASSPATH):OSP.jar osp.OSP $(OPTS)

clean:
	/bin/rm -rf osp temp *.log saved

pre: cleanThread
	cp $(PREQUEUE) .
	make -s run

nonpre: cleanThread
	cp $(NONPRE) .
	make -s run

org: cleanThread
	cp $(ORIGINAL) .
	make -s run

cleanThread:
	rm *.java