#!/bin/bash
spin -a philo_deadlock.prm 
gcc -O2 -g -o pan pan.c -DSAFETY
./pan -m1000000
spin -p -t philo_deadlock.prm
