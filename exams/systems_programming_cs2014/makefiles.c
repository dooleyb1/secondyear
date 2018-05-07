--------------------------------------------------
** Makefiles & Complining **
--------------------------------------------------

target: dependencies
    action

When a program is compiled source files ( main.c )
are compiled into object files ( main.o )
which are then further compiled into executables ( output ).

When using multiple source files, each individual
source file is compiled into individiual object files
( main.o , partb.o, partc.o ) these are then linked
together with any C libraries used and combined
together into one executable.


    ---------------------------------------------
    [Makefile 1]
    ---------------------------------------------

    hellomake: hellomake.c hellofunc.c
       gcc -o hellomake hellomake.c hellofunc.c -I.

    //This makefile is dependant on hellomake.c and hellofunc.c

    - gcc                     || GCC C Compiler
    - o hellomake             || Output to hellomake executable
    - hellomake.c hellofunc.c || C files for compiler
    - I.                      || Include local directory


    ---------------------------------------------
    [Makefile 2]
    ---------------------------------------------

    CC=gcc
    CFLAGS=-I.

    hellomake: hellomake.o hellofunc.o
         $(CC) -o hellomake hellomake.o hellofunc.o -I.

    //This makefile is now dependant on hellomake.o and hellofunc.o
    //Make knows it must first compile the c files for that dependancy

    - CC=gcc                  || C Compiler to use = gcc
    - CFLAGS = -I.            || Include local directory
    - o hellomake             || Output to hellomake executable
    - hellomake.o hellofunc.o || Object files for compiler
    - I.                      || Include local directory

    ---------------------------------------------
    [Makefile 3]
    ---------------------------------------------

    CC=gcc
    CFLAGS=-I.
    DEPS = hellomake.h

    %.o: %.c $(DEPS)
    	$(CC) -c -o $@ $< $(CFLAGS)

    hellomake: hellomake.o hellofunc.o
    	gcc -o hellomake hellomake.o hellofunc.o -I.

    //DEPS is the set of .h files upon which the .c files depend on

    //%.o: All files which end in .o
    //This says that all .o files depend on the .c of that same file
    //and also the dependancies listed in DEPS

    - DEPS = hellomake.h      || Dependencies
    - %.o                     || All files ending in .o
    - %.c                     || All files ending in .c
    - $(CC) -c                || Generate object file
    - -o $@                   || Output to executable named after x.o
    - $<                      || First item in depencencies list
    - $(CFLAGS)               || -I.


    ---------------------------------------------
    [Makefile 4]
    ---------------------------------------------

    CC=gcc
    CFLAGS=-I.
    DEPS = hellomake.h
    OBJ = hellomake.o hellofunc.o

    %.o: %.c $(DEPS)
    	$(CC) -c -o $@ $< $(CFLAGS)

    hellomake: $(OBJ)
    	gcc -o $@ $^ $(CFLAGS)

    //OBJS is the set of .o files (object files)

    - OBJ = hellomake.o hellofunc.o  || Object files
    - $@                             || Left side of :
    - $<                             || First item in depencencies list
    - $^                             || Right side of :


    ---------------------------------------------
    [Makefile 5]
    ---------------------------------------------

    IDIR =../include
    CC=gcc
    CFLAGS=-I$(IDIR)

    ODIR=obj
    LDIR =../lib

    LIBS=-lm

    _DEPS = hellomake.h
    DEPS = $(patsubst %,$(IDIR)/%,$(_DEPS))

    _OBJ = hellomake.o hellofunc.o
    OBJ = $(patsubst %,$(ODIR)/%,$(_OBJ))


    $(ODIR)/%.o: %.c $(DEPS)
    	$(CC) -c -o $@ $< $(CFLAGS)

    hellomake: $(OBJ)
    	gcc -o $@ $^ $(CFLAGS) $(LIBS)

    .PHONY: clean

    clean:
    	rm -f $(ODIR)/*.o *~ core $(INCDIR)/*~

    //This Makefile introduces make clean & directories*/

    - IDIR =../include  || includeDirectory
    - CFLAGS=-I$(IDIR)  || Include includeDirectory
    - ODIR = obj        || objects directory
    - LDIR = ../lib     || library directory
    - LIBS = -lm        || Math library
    -
