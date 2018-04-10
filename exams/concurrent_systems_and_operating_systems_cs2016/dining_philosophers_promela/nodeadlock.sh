#!/bin/bash
spin -a philo_no_deadlock.prm
gcc -O2 -g -o pan pan.c -DSAFETY
./pan -m10000000
