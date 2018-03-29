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
	case 3: // STATE 1 - part1.prm:34 - [finish = 4] (0:0:1 - 1)
		
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
		(trpt+1)->bup.oval = ((int)finish);
		finish = 4;
#ifdef VAR_RANGES
		logval("finish", ((int)finish));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 2 - part1.prm:35 - [((n<4))] (0:0:0 - 1)
		
#if defined(VERI) && !defined(NP)
#if NCLAIMS>1
		{	static int reported2 = 0;
			if (verbose && !reported2)
			{	int nn = (int) ((Pclaim *)pptr(0))->_n;
				printf("depth %ld: Claim %s (%d), state %d (line %d)\n",
					depth, procname[spin_c_typ[nn]], nn, (int) ((Pclaim *)pptr(0))->_p, src_claim[ (int) ((Pclaim *)pptr(0))->_p ]);
				reported2 = 1;
				fflush(stdout);
		}	}
#else
		{	static int reported2 = 0;
			if (verbose && !reported2)
			{	printf("depth %d: Claim, state %d (line %d)\n",
					(int) depth, (int) ((Pclaim *)pptr(0))->_p, src_claim[ (int) ((Pclaim *)pptr(0))->_p ]);
				reported2 = 1;
				fflush(stdout);
		}	}
#endif
#endif
		reached[2][2] = 1;
		if (!((((int)now.n)<4)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 3 - part1.prm:37 - [-end-] (0:0:0 - 1)
		
#if defined(VERI) && !defined(NP)
#if NCLAIMS>1
		{	static int reported3 = 0;
			if (verbose && !reported3)
			{	int nn = (int) ((Pclaim *)pptr(0))->_n;
				printf("depth %ld: Claim %s (%d), state %d (line %d)\n",
					depth, procname[spin_c_typ[nn]], nn, (int) ((Pclaim *)pptr(0))->_p, src_claim[ (int) ((Pclaim *)pptr(0))->_p ]);
				reported3 = 1;
				fflush(stdout);
		}	}
#else
		{	static int reported3 = 0;
			if (verbose && !reported3)
			{	printf("depth %d: Claim, state %d (line %d)\n",
					(int) depth, (int) ((Pclaim *)pptr(0))->_p, src_claim[ (int) ((Pclaim *)pptr(0))->_p ]);
				reported3 = 1;
				fflush(stdout);
		}	}
#endif
#endif
		reached[2][3] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC Q */
	case 6: // STATE 1 - part1.prm:24 - [((count==2))] (0:0:1 - 1)
		IfNotBlocked
		reached[1][1] = 1;
		if (!((((int)((P1 *)this)->count)==2)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: count */  (trpt+1)->bup.oval = ((P1 *)this)->count;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P1 *)this)->count = 0;
		_m = 3; goto P999; /* 0 */
	case 7: // STATE 4 - part1.prm:26 - [n = (n+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[1][4] = 1;
		(trpt+1)->bup.oval = ((int)now.n);
		now.n = (((int)now.n)+1);
#ifdef VAR_RANGES
		logval("n", ((int)now.n));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 8: // STATE 5 - part1.prm:27 - [count = (count+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[1][5] = 1;
		(trpt+1)->bup.oval = ((int)((P1 *)this)->count);
		((P1 *)this)->count = (((int)((P1 *)this)->count)+1);
#ifdef VAR_RANGES
		logval("Q:count", ((int)((P1 *)this)->count));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 9 - part1.prm:29 - [finish = (finish+1)] (0:0:1 - 3)
		IfNotBlocked
		reached[1][9] = 1;
		(trpt+1)->bup.oval = ((int)finish);
		finish = (((int)finish)+1);
#ifdef VAR_RANGES
		logval("finish", ((int)finish));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 10: // STATE 10 - part1.prm:30 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[1][10] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC P */
	case 11: // STATE 1 - part1.prm:9 - [((counter==2))] (0:0:1 - 1)
		IfNotBlocked
		reached[0][1] = 1;
		if (!((((int)((P0 *)this)->counter)==2)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: counter */  (trpt+1)->bup.oval = ((P0 *)this)->counter;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P0 *)this)->counter = 0;
		_m = 3; goto P999; /* 0 */
	case 12: // STATE 4 - part1.prm:11 - [temp = n] (0:0:1 - 1)
		IfNotBlocked
		reached[0][4] = 1;
		(trpt+1)->bup.oval = ((int)((P0 *)this)->temp);
		((P0 *)this)->temp = ((int)now.n);
#ifdef VAR_RANGES
		logval("P:temp", ((int)((P0 *)this)->temp));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 13: // STATE 5 - part1.prm:12 - [temp = (temp+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[0][5] = 1;
		(trpt+1)->bup.oval = ((int)((P0 *)this)->temp);
		((P0 *)this)->temp = (((int)((P0 *)this)->temp)+1);
#ifdef VAR_RANGES
		logval("P:temp", ((int)((P0 *)this)->temp));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 14: // STATE 6 - part1.prm:13 - [n = temp] (0:0:1 - 1)
		IfNotBlocked
		reached[0][6] = 1;
		(trpt+1)->bup.oval = ((int)now.n);
		now.n = ((int)((P0 *)this)->temp);
#ifdef VAR_RANGES
		logval("n", ((int)now.n));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 15: // STATE 7 - part1.prm:14 - [counter = (counter+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[0][7] = 1;
		(trpt+1)->bup.oval = ((int)((P0 *)this)->counter);
		((P0 *)this)->counter = (((int)((P0 *)this)->counter)+1);
#ifdef VAR_RANGES
		logval("P:counter", ((int)((P0 *)this)->counter));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 16: // STATE 11 - part1.prm:16 - [finish = (finish+1)] (0:0:1 - 3)
		IfNotBlocked
		reached[0][11] = 1;
		(trpt+1)->bup.oval = ((int)finish);
		finish = (((int)finish)+1);
#ifdef VAR_RANGES
		logval("finish", ((int)finish));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 17: // STATE 12 - part1.prm:17 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[0][12] = 1;
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

