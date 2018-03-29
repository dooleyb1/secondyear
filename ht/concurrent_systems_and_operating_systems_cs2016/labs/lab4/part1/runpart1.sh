#!/bin/bash
spin -a -DSTRATEGY=0 part1.prm
gcc -O2 -g -o pan pan.c -DSAFETY
./pan -m10000000
spin -t part1.prm
