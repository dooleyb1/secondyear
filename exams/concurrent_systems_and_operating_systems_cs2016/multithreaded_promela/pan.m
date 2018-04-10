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

		 /* PROC :init: */
	case 3: // STATE 1 - part1.prm:28 - [(run P())] (0:0:0 - 1)
		IfNotBlocked
		reached[2][1] = 1;
		if (!(addproc(II, 1, 0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 2 - part1.prm:29 - [(run Q())] (0:0:0 - 1)
		IfNotBlocked
		reached[2][2] = 1;
		if (!(addproc(II, 1, 1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 3 - part1.prm:30 - [((p_complete==2))] (0:0:0 - 1)
		IfNotBlocked
		reached[2][3] = 1;
		if (!((((int)now.p_complete)==2)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 6: // STATE 4 - part1.prm:31 - [assert((n==4))] (0:0:0 - 1)
		IfNotBlocked
		reached[2][4] = 1;
		spin_assert((((int)now.n)==4), "(n==4)", II, tt, t);
		_m = 3; goto P999; /* 0 */
	case 7: // STATE 5 - part1.prm:32 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[2][5] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC Q */
	case 8: // STATE 1 - part1.prm:19 - [((q_count==2))] (0:0:1 - 1)
		IfNotBlocked
		reached[1][1] = 1;
		if (!((((int)((P1 *)this)->q_count)==2)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: q_count */  (trpt+1)->bup.oval = ((P1 *)this)->q_count;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P1 *)this)->q_count = 0;
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 4 - part1.prm:21 - [n = (n+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[1][4] = 1;
		(trpt+1)->bup.oval = ((int)now.n);
		now.n = (((int)now.n)+1);
#ifdef VAR_RANGES
		logval("n", ((int)now.n));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 10: // STATE 5 - part1.prm:22 - [q_count = (q_count+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[1][5] = 1;
		(trpt+1)->bup.oval = ((int)((P1 *)this)->q_count);
		((P1 *)this)->q_count = (((int)((P1 *)this)->q_count)+1);
#ifdef VAR_RANGES
		logval("Q:q_count", ((int)((P1 *)this)->q_count));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 11: // STATE 9 - part1.prm:24 - [p_complete = (p_complete+1)] (0:0:1 - 3)
		IfNotBlocked
		reached[1][9] = 1;
		(trpt+1)->bup.oval = ((int)now.p_complete);
		now.p_complete = (((int)now.p_complete)+1);
#ifdef VAR_RANGES
		logval("p_complete", ((int)now.p_complete));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 12: // STATE 10 - part1.prm:25 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[1][10] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC P */
	case 13: // STATE 1 - part1.prm:7 - [((p_count==2))] (0:0:1 - 1)
		IfNotBlocked
		reached[0][1] = 1;
		if (!((((int)((P0 *)this)->p_count)==2)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: p_count */  (trpt+1)->bup.oval = ((P0 *)this)->p_count;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P0 *)this)->p_count = 0;
		_m = 3; goto P999; /* 0 */
	case 14: // STATE 4 - part1.prm:9 - [temp = n] (0:0:1 - 1)
		IfNotBlocked
		reached[0][4] = 1;
		(trpt+1)->bup.oval = ((int)((P0 *)this)->temp);
		((P0 *)this)->temp = ((int)now.n);
#ifdef VAR_RANGES
		logval("P:temp", ((int)((P0 *)this)->temp));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 15: // STATE 5 - part1.prm:10 - [temp = (temp+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[0][5] = 1;
		(trpt+1)->bup.oval = ((int)((P0 *)this)->temp);
		((P0 *)this)->temp = (((int)((P0 *)this)->temp)+1);
#ifdef VAR_RANGES
		logval("P:temp", ((int)((P0 *)this)->temp));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 16: // STATE 6 - part1.prm:11 - [n = temp] (0:0:1 - 1)
		IfNotBlocked
		reached[0][6] = 1;
		(trpt+1)->bup.oval = ((int)now.n);
		now.n = ((int)((P0 *)this)->temp);
#ifdef VAR_RANGES
		logval("n", ((int)now.n));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 17: // STATE 7 - part1.prm:12 - [p_count = (p_count+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[0][7] = 1;
		(trpt+1)->bup.oval = ((int)((P0 *)this)->p_count);
		((P0 *)this)->p_count = (((int)((P0 *)this)->p_count)+1);
#ifdef VAR_RANGES
		logval("P:p_count", ((int)((P0 *)this)->p_count));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 18: // STATE 11 - part1.prm:14 - [p_complete = (p_complete+1)] (0:0:1 - 3)
		IfNotBlocked
		reached[0][11] = 1;
		(trpt+1)->bup.oval = ((int)now.p_complete);
		now.p_complete = (((int)now.p_complete)+1);
#ifdef VAR_RANGES
		logval("p_complete", ((int)now.p_complete));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 19: // STATE 12 - part1.prm:15 - [-end-] (0:0:0 - 1)
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

