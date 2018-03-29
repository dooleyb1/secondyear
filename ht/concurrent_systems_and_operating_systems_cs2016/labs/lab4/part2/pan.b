	switch (t->back) {
	default: Uerror("bad return move");
	case  0: goto R999; /* nothing to undo */

		 /* CLAIM never_0 */
;
		;
		;
		;
		
	case 5: // STATE 13
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC P */

	case 6: // STATE 2
		;
		pthinking[ Index(((P1 *)this)->i, 5) ] = trpt->bup.ovals[1];
		now.peating[ Index(((P1 *)this)->i, 5) ] = trpt->bup.ovals[0];
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 7: // STATE 5
		;
		now.phungry[ Index(((P1 *)this)->i, 5) ] = trpt->bup.ovals[1];
		pthinking[ Index(((P1 *)this)->i, 5) ] = trpt->bup.ovals[0];
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;
;
		;
		
	case 9: // STATE 9
		;
		now.forks[ Index(((P1 *)this)->left, 5) ] = trpt->bup.oval;
		;
		goto R999;

	case 10: // STATE 12
		;
		now.forks[ Index(((P1 *)this)->right, 5) ] = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 12: // STATE 16
		;
		now.forks[ Index(((P1 *)this)->right, 5) ] = trpt->bup.oval;
		;
		goto R999;

	case 13: // STATE 19
		;
		now.forks[ Index(((P1 *)this)->left, 5) ] = trpt->bup.oval;
		;
		goto R999;

	case 14: // STATE 24
		;
		now.peating[ Index(((P1 *)this)->i, 5) ] = trpt->bup.ovals[1];
		now.phungry[ Index(((P1 *)this)->i, 5) ] = trpt->bup.ovals[0];
		;
		ungrab_ints(trpt->bup.ovals, 2);
		goto R999;

	case 15: // STATE 26
		;
		now.forks[ Index(((P1 *)this)->right, 5) ] = trpt->bup.oval;
		;
		goto R999;

	case 16: // STATE 27
		;
		now.forks[ Index(((P1 *)this)->left, 5) ] = trpt->bup.oval;
		;
		goto R999;

		 /* PROC :init: */

	case 17: // STATE 1
		;
		((P0 *)this)->_1_1_i = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 19: // STATE 3
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 20: // STATE 4
		;
		((P0 *)this)->_1_1_i = trpt->bup.oval;
		;
		goto R999;

	case 21: // STATE 11
		;
		p_restor(II);
		;
		;
		goto R999;
	}

