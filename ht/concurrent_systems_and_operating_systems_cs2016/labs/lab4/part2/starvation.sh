#!/bin/bash
spin -a -N neverclaim philo_no_deadlock.prm
gcc -O2 -g -o pan pan.c
./pan -a -n -m1000000
