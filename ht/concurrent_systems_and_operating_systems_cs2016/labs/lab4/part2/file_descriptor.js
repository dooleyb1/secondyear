--------------------------------------------------------------------------------
1 - One Philosopher - No Deadlock, Starvation or Livelock
--------------------------------------------------------------------------------

Presuming there are two forks provided, both forks will 
always be available to the philosopher since there are
no other philosophers attempting to access these resources.
Therefore he will always be able to eat when he becomes 
hungry and no deadlock, starvation or livelock will occur.


--------------------------------------------------------------------------------
2 - Two Philosophers - Not Seated Beside  - No Deadlock, Starvation or Livelock
--------------------------------------------------------------------------------

Presuming Mike means that there are more than 2/3 forks on 
the table considering the philosophers are not seated 
together thus not sharing resources. This means that whenever
either one becomes hungry the other philosopher will not 
have/be attempting to access his forks. So he will always
be able to eat whenever he becomes hungry thus no deadlock, 
livelock or starvation.


--------------------------------------------------------------------------------
3 - Two Philosophers - Seated Beside  - Deadlock
--------------------------------------------------------------------------------

Presuming Mike means that there are only two forks on the 
table. Both of the philosophers seated at the table will be 
sharing the same resources i.e forks. Therefore there
could exist a state where both philosophers are hungry and
have instantaniously grabbed a fork, leaving no more forks
on the table. This means that both are caught in deadlock 
waiting on a fork to be released from the other.

--------------------------------------------------------------------------------
4 - Four Philosophers - Deadlock
--------------------------------------------------------------------------------

Presuming Mike means that there are only four forks on the
table this means that all philosophers are sharing a fork
with one other philosophers. There can occur a state when 
all philosophers become hungry, no philosophers are eating 
and all philosophers instantaniously grab a fork leaving no
free forks.

	Phil 1 - Fork 1
	Phil 2 - Fork 2 
	Phil 3 - Fork 3
	Phil 4 - Fork 4
	Phil 5 - Fork 5

This means that all philosophers are stuck in limbo waiting 
for a fork to be released, thus deadlock occurs.


--------------------------------------------------------------------------------
*Livelock
--------------------------------------------------------------------------------

Livelock occurs when for example all Philosophers decide if 
you don't manage to pick up a second fork within 10 minutes
you must put down your current fork and wait 10 minutes before
trying again. However, if all Philosophers somehow get to the 
stage where the 10 minute intervals are the exact same, Livelock
occurs.



--------------------------------------------------------------------------------
5 - N Philosophers - Solving Deadlock
--------------------------------------------------------------------------------

To solve deadlock occuring in all of the above the order
of selection of forks must change from non-deterministic 
to deterministic. Instead of randomly checking left/right
first Philosophers must check the left/right forks in a 
specific order.

While this solves deadlock starvation and livelock can 
still occur. We must check that when a Philosopher becomes
hungry he must eventually eat at some stage. From the 
testing below we can see that it can occur that:

	Philosoper X becomes hungry and tries to pick up fork F.
	At the same time Philosopher Y picks up fork F.

	Philosopher Y continues eating, finishes, and starts thinking.
	Philosopher Y immediately becomes hungry again.
	Philosopher Y grabs fork F before Philosopher X manages to.

	This happens continuously starving Philopher X to death.



---------------------------------------------------------------------------------