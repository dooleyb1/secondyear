CC=gcc

# the name of our executable result
program_NAME := printer
# the names of our 'C' source files - in this case all .c files in this dir
program_C_SRCS := $(wildcard *.c)
# the names of our object files - in this case all .o files here, derived from .c files
program_C_OBJS := ${program_C_SRCS:.c=.o}
program_OBJS := $(program_C_OBJS) 

# our default target
all: $(program_NAME)

# our default dependecies - build rule is derived
$(program_NAME): $(program_OBJS)
	$(CC) -pthread $(program_OBJS) -lm -lcurses -o $(program_NAME)

clean:
	@- $(RM) $(program_NAME)
	@- $(RM) $(program_OBJS)

