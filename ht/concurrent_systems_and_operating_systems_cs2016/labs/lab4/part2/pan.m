#define rand	pan_rand
#define pthread_equal(a,b)	((a)==(b))
#if defined(HAS_CODE) && defined(VERBOSE)
	#ifdef BFS_PAR
		bfs_printf("Pr: %d Tr: %d\n", II, t->forw);
	#else
		cpu_printf("Pr: %d Tr: %d\n", II, t->forw);
	#endif
#endif
	switch (t->forw) {
	default: Uerror("bad forward move");
	case 0:	/* if without executable clauses */
		continue;
	case 1: /* generic 'goto' or 'skip' */
		IfNotBlocked
		_m = 3; goto P999;
	case 2: /* generic 'else' */
		IfNotBlocked
		if (trpt->o_pm&1) continue;
		_m = 3; goto P999;

		 /* CLAIM never_0 */
	case 3: // STATE 1 - neverclaim:4 - [((!(peating[0])&&phungry[0]))] (0:0:0 - 1)
		
#if defined(VERI) && !defined(NP)
#if NCLAIMS>1
		{	static int reported1 = 0;
			if (verbose && !reported1)
			{	int nn = (int) ((Pclaim *)pptr(0))->_n;
				printf("depth %ld: Claim %s (%d), state %d (line %d)\n",
					depth, procname[spin_c_typ[nn]], nn, (int) ((Pclaim *)pptr(0))->_p, src_claim[ (int) ((Pclaim *)pptr(0))->_p ]);
				reported1 = 1;
				fflush(stdout);
		}	}
#else
		{	static int reported1 = 0;
			if (verbose && !reported1)
			{	printf("depth %d: Claim, state %d (line %d)\n",
					(int) depth, (int) ((Pclaim *)pptr(0))->_p, src_claim[ (int) ((Pclaim *)pptr(0))->_p ]);
				reported1 = 1;
				fflush(stdout);
		}	}
#endif
#endif
		reached[2][1] = 1;
		if (!(( !(((int)now.peating[0]))&&((int)now.phungry[0]))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 8 - neverclaim:9 - [(!(peating[0]))] (0:0:0 - 1)
		
#if defined(VERI) && !defined(NP)
#if NCLAIMS>1
		{	static int reported8 = 0;
			if (verbose && !reported8)
			{	int nn = (int) ((Pclaim *)pptr(0))->_n;
				printf("depth %ld: Claim %s (%d), state %d (line %d)\n",
					depth, procname[spin_c_typ[nn]], nn, (int) ((Pclaim *)pptr(0))->_p, src_claim[ (int) ((Pclaim *)pptr(0))->_p ]);
				reported8 = 1;
				fflush(stdout);
		}	}
#else
		{	static int reported8 = 0;
			if (verbose && !reported8)
			{	printf("depth %d: Claim, state %d (line %d)\n",
					(int) depth, (int) ((Pclaim *)pptr(0))->_p, src_claim[ (int) ((Pclaim *)pptr(0))->_p ]);
				reported8 = 1;
				fflush(stdout);
		}	}
#endif
#endif
		reached[2][8] = 1;
		if (!( !(((int)now.peating[0]))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 13 - neverclaim:11 - [-end-] (0:0:0 - 1)
		
#if defined(VERI) && !defined(NP)
#if NCLAIMS>1
		{	static int reported13 = 0;
			if (verbose && !reported13)
			{	int nn = (int) ((Pclaim *)pptr(0))->_n;
				printf("depth %ld: Claim %s (%d), state %d (line %d)\n",
					depth, procname[spin_c_typ[nn]], nn, (int) ((Pclaim *)pptr(0))->_p, src_claim[ (int) ((Pclaim *)pptr(0))->_p ]);
				reported13 = 1;
				fflush(stdout);
		}	}
#else
		{	static int reported13 = 0;
			if (verbose && !reported13)
			{	printf("depth %d: Claim, state %d (line %d)\n",
					(int) depth, (int) ((Pclaim *)pptr(0))->_p, src_claim[ (int) ((Pclaim *)pptr(0))->_p ]);
				reported13 = 1;
				fflush(stdout);
		}	}
#endif
#endif
		reached[2][13] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC P */
	case 6: // STATE 1 - philo_no_deadlock.prm:30 - [peating[i] = 0] (0:6:2 - 1)
		IfNotBlocked
		reached[1][1] = 1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)now.peating[ Index(((P1 *)this)->i, 5) ]);
		now.peating[ Index(((P1 *)this)->i, 5) ] = 0;
#ifdef VAR_RANGES
		logval("peating[P:i]", ((int)now.peating[ Index(((P1 *)this)->i, 5) ]));
#endif
		;
		/* merge: pthinking[i] = 1(6, 2, 6) */
		reached[1][2] = 1;
		(trpt+1)->bup.ovals[1] = ((int)pthinking[ Index(((P1 *)this)->i, 5) ]);
		pthinking[ Index(((P1 *)this)->i, 5) ] = 1;
#ifdef VAR_RANGES
		logval("pthinking[P:i]", ((int)pthinking[ Index(((P1 *)this)->i, 5) ]));
#endif
		;
		_m = 3; goto P999; /* 1 */
	case 7: // STATE 4 - philo_no_deadlock.prm:36 - [pthinking[i] = 0] (0:21:2 - 1)
		IfNotBlocked
		reached[1][4] = 1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)pthinking[ Index(((P1 *)this)->i, 5) ]);
		pthinking[ Index(((P1 *)this)->i, 5) ] = 0;
#ifdef VAR_RANGES
		logval("pthinking[P:i]", ((int)pthinking[ Index(((P1 *)this)->i, 5) ]));
#endif
		;
		/* merge: phungry[i] = 1(21, 5, 21) */
		reached[1][5] = 1;
		(trpt+1)->bup.ovals[1] = ((int)now.phungry[ Index(((P1 *)this)->i, 5) ]);
		now.phungry[ Index(((P1 *)this)->i, 5) ] = 1;
#ifdef VAR_RANGES
		logval("phungry[P:i]", ((int)now.phungry[ Index(((P1 *)this)->i, 5) ]));
#endif
		;
		_m = 3; goto P999; /* 1 */
	case 8: // STATE 7 - philo_no_deadlock.prm:40 - [((left<right))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][7] = 1;
		if (!((((P1 *)this)->left<((P1 *)this)->right)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 8 - philo_no_deadlock.prm:41 - [((forks[left]==-(1)))] (13:0:1 - 1)
		IfNotBlocked
		reached[1][8] = 1;
		if (!((now.forks[ Index(((P1 *)this)->left, 5) ]== -(1))))
			continue;
		/* merge: forks[left] = i(0, 9, 13) */
		reached[1][9] = 1;
		(trpt+1)->bup.oval = now.forks[ Index(((P1 *)this)->left, 5) ];
		now.forks[ Index(((P1 *)this)->left, 5) ] = ((P1 *)this)->i;
#ifdef VAR_RANGES
		logval("forks[P:left]", now.forks[ Index(((P1 *)this)->left, 5) ]);
#endif
		;
		_m = 3; goto P999; /* 1 */
	case 10: // STATE 11 - philo_no_deadlock.prm:42 - [((forks[right]==-(1)))] (25:0:1 - 1)
		IfNotBlocked
		reached[1][11] = 1;
		if (!((now.forks[ Index(((P1 *)this)->right, 5) ]== -(1))))
			continue;
		/* merge: forks[right] = i(0, 12, 25) */
		reached[1][12] = 1;
		(trpt+1)->bup.oval = now.forks[ Index(((P1 *)this)->right, 5) ];
		now.forks[ Index(((P1 *)this)->right, 5) ] = ((P1 *)this)->i;
#ifdef VAR_RANGES
		logval("forks[P:right]", now.forks[ Index(((P1 *)this)->right, 5) ]);
#endif
		;
		/* merge: .(goto)(0, 22, 25) */
		reached[1][22] = 1;
		;
		_m = 3; goto P999; /* 2 */
	case 11: // STATE 14 - philo_no_deadlock.prm:43 - [((right<left))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][14] = 1;
		if (!((((P1 *)this)->right<((P1 *)this)->left)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 12: // STATE 15 - philo_no_deadlock.prm:44 - [((forks[right]==-(1)))] (20:0:1 - 1)
		IfNotBlocked
		reached[1][15] = 1;
		if (!((now.forks[ Index(((P1 *)this)->right, 5) ]== -(1))))
			continue;
		/* merge: forks[right] = i(0, 16, 20) */
		reached[1][16] = 1;
		(trpt+1)->bup.oval = now.forks[ Index(((P1 *)this)->right, 5) ];
		now.forks[ Index(((P1 *)this)->right, 5) ] = ((P1 *)this)->i;
#ifdef VAR_RANGES
		logval("forks[P:right]", now.forks[ Index(((P1 *)this)->right, 5) ]);
#endif
		;
		_m = 3; goto P999; /* 1 */
	case 13: // STATE 18 - philo_no_deadlock.prm:45 - [((forks[left]==-(1)))] (25:0:1 - 1)
		IfNotBlocked
		reached[1][18] = 1;
		if (!((now.forks[ Index(((P1 *)this)->left, 5) ]== -(1))))
			continue;
		/* merge: forks[left] = i(0, 19, 25) */
		reached[1][19] = 1;
		(trpt+1)->bup.oval = now.forks[ Index(((P1 *)this)->left, 5) ];
		now.forks[ Index(((P1 *)this)->left, 5) ] = ((P1 *)this)->i;
#ifdef VAR_RANGES
		logval("forks[P:left]", now.forks[ Index(((P1 *)this)->left, 5) ]);
#endif
		;
		/* merge: .(goto)(0, 22, 25) */
		reached[1][22] = 1;
		;
		_m = 3; goto P999; /* 2 */
	case 14: // STATE 23 - philo_no_deadlock.prm:50 - [phungry[i] = 0] (0:26:2 - 1)
		IfNotBlocked
		reached[1][23] = 1;
		(trpt+1)->bup.ovals = grab_ints(2);
		(trpt+1)->bup.ovals[0] = ((int)now.phungry[ Index(((P1 *)this)->i, 5) ]);
		now.phungry[ Index(((P1 *)this)->i, 5) ] = 0;
#ifdef VAR_RANGES
		logval("phungry[P:i]", ((int)now.phungry[ Index(((P1 *)this)->i, 5) ]));
#endif
		;
		/* merge: peating[i] = 1(26, 24, 26) */
		reached[1][24] = 1;
		(trpt+1)->bup.ovals[1] = ((int)now.peating[ Index(((P1 *)this)->i, 5) ]);
		now.peating[ Index(((P1 *)this)->i, 5) ] = 1;
#ifdef VAR_RANGES
		logval("peating[P:i]", ((int)now.peating[ Index(((P1 *)this)->i, 5) ]));
#endif
		;
		_m = 3; goto P999; /* 1 */
	case 15: // STATE 26 - philo_no_deadlock.prm:55 - [forks[right] = -(1)] (0:0:1 - 1)
		IfNotBlocked
		reached[1][26] = 1;
		(trpt+1)->bup.oval = now.forks[ Index(((P1 *)this)->right, 5) ];
		now.forks[ Index(((P1 *)this)->right, 5) ] =  -(1);
#ifdef VAR_RANGES
		logval("forks[P:right]", now.forks[ Index(((P1 *)this)->right, 5) ]);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 16: // STATE 27 - philo_no_deadlock.prm:56 - [forks[left] = -(1)] (0:0:1 - 1)
		IfNotBlocked
		reached[1][27] = 1;
		(trpt+1)->bup.oval = now.forks[ Index(((P1 *)this)->left, 5) ];
		now.forks[ Index(((P1 *)this)->left, 5) ] =  -(1);
#ifdef VAR_RANGES
		logval("forks[P:left]", now.forks[ Index(((P1 *)this)->left, 5) ]);
#endif
		;
		_m = 3; goto P999; /* 0 */

		 /* PROC :init: */
	case 17: // STATE 1 - philo_no_deadlock.prm:12 - [i = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[0][1] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->_1_1_i;
		((P0 *)this)->_1_1_i = 0;
#ifdef VAR_RANGES
		logval(":init::i", ((P0 *)this)->_1_1_i);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 18: // STATE 2 - philo_no_deadlock.prm:13 - [((i<5))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][2] = 1;
		if (!((((P0 *)this)->_1_1_i<5)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 19: // STATE 3 - philo_no_deadlock.prm:14 - [(run P(i))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][3] = 1;
		if (!(addproc(II, 1, 1, ((P0 *)this)->_1_1_i)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 20: // STATE 4 - philo_no_deadlock.prm:15 - [i = (i+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[0][4] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->_1_1_i;
		((P0 *)this)->_1_1_i = (((P0 *)this)->_1_1_i+1);
#ifdef VAR_RANGES
		logval(":init::i", ((P0 *)this)->_1_1_i);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 21: // STATE 11 - philo_no_deadlock.prm:21 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[0][11] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */
	case  _T5:	/* np_ */
		if (!((!(trpt->o_pm&4) && !(trpt->tau&128))))
			continue;
		/* else fall through */
	case  _T2:	/* true */
		_m = 3; goto P999;
#undef rand
	}

