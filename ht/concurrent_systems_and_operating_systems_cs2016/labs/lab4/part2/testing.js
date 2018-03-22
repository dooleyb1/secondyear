
1. philo_nice_deadlock.prm

5 Philosophers, non-deterministic selection of forks
which can lead to deadlock. Occurs when all philosophers 
are hungry, none are eating and each philosopher has 
one fork.

----------------------------------------------------------

2. philo_nice_ordered_selection.prm

5 Philosophers, now includes deterministic selection of
forks. Philosophers will first check if the left fork is 
available and then check the right fork.





 ** Check for deadlock **
 $ spin -a philo_no_deadlock.prm
 $ gcc -O2 -g -o pan pan.c -DSAFETY
 $ ./pan -m10000000
 .....
 ....
 no mention of a trail file i.e no deadlock





 ** Check for starvation (use neverclaim)**
 $ spin -a -N neverclaim philo_no_deadlock.prm
 $ gcc -O2 -g -o pan pan.c
 $ ./pan -a -n -m1000000
 ...........
 ...........
 pan:1: acceptance cycle (at depth 3218)
 pan: wrote philo_nice_ordered_selection.prm.trail
 -Acceptance cycle therefore, never claim does not hold
 -Now check trail

$ spin -p -t philo_nice_ordered_selection.prm


 3218:	proc  1 (P:1) philo_nice_ordered_selection.prm:43 (state 14)	[((right<left))]
  <<<<<START OF CYCLE>>>>>
3220:	proc  5 (P:1) philo_nice_ordered_selection.prm:41 (state 8)	[((forks[left]==-(1)))]
3220:	proc  5 (P:1) philo_nice_ordered_selection.prm:41 (state 9)	[forks[left] = i]
3222:	proc  5 (P:1) philo_nice_ordered_selection.prm:42 (state 11)	[((forks[right]==-(1)))]
3222:	proc  5 (P:1) philo_nice_ordered_selection.prm:42 (state 12)	[forks[right] = i]
3224:	proc  5 (P:1) philo_nice_ordered_selection.prm:50 (state 23)	[phungry[i] = 0]
3224:	proc  5 (P:1) philo_nice_ordered_selection.prm:51 (state 24)	[peating[i] = 1]
3226:	proc  5 (P:1) philo_nice_ordered_selection.prm:55 (state 26)	[forks[right] = -(1)]
3228:	proc  5 (P:1) philo_nice_ordered_selection.prm:56 (state 27)	[forks[left] = -(1)]
3230:	proc  5 (P:1) philo_nice_ordered_selection.prm:30 (state 1)	[peating[i] = 0]
3230:	proc  5 (P:1) philo_nice_ordered_selection.prm:31 (state 2)	[pthinking[i] = 1]
3232:	proc  5 (P:1) philo_nice_ordered_selection.prm:36 (state 4)	[pthinking[i] = 0]
3232:	proc  5 (P:1) philo_nice_ordered_selection.prm:37 (state 5)	[phungry[i] = 1]
3234:	proc  5 (P:1) philo_nice_ordered_selection.prm:40 (state 7)	[((left<right))]
spin: trail ends after 3234 steps


Philosopher 1 is hungry, and tries to pick up fork. However at the same time Philosopher 5
 tries to pick up the same shared fork which he manages to do. He continues eating, finishes,
 starts thinking again and immediately becomes hungry again and picks up the fork before Philosopher 1 can i.e starving him to death.

------------------------------------------------------------------------------------------
