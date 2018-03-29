	switch (t->back) {
	default: Uerror("bad return move");
	case  0: goto R999; /* nothing to undo */

		 /* PROC :init: */

	case 3: // STATE 1
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 4: // STATE 2
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;
;
		;
		;
		;
		
	case 7: // STATE 5
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC Q */

	case 8: // STATE 1
		;
	/* 0 */	((P1 *)this)->q_count = trpt->bup.oval;
		;
		;
		goto R999;

	case 9: // STATE 4
		;
		now.n = trpt->bup.oval;
		;
		goto R999;

	case 10: // STATE 5
		;
		((P1 *)this)->q_count = trpt->bup.oval;
		;
		goto R999;

	case 11: // STATE 9
		;
		now.p_complete = trpt->bup.oval;
		;
		goto R999;

	case 12: // STATE 10
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC P */

	case 13: // STATE 1
		;
	/* 0 */	((P0 *)this)->p_count = trpt->bup.oval;
		;
		;
		goto R999;

	case 14: // STATE 4
		;
		((P0 *)this)->temp = trpt->bup.oval;
		;
		goto R999;

	case 15: // STATE 5
		;
		((P0 *)this)->temp = trpt->bup.oval;
		;
		goto R999;

	case 16: // STATE 6
		;
		now.n = trpt->bup.oval;
		;
		goto R999;

	case 17: // STATE 7
		;
		((P0 *)this)->p_count = trpt->bup.oval;
		;
		goto R999;

	case 18: // STATE 11
		;
		now.p_complete = trpt->bup.oval;
		;
		goto R999;

	case 19: // STATE 12
		;
		p_restor(II);
		;
		;
		goto R999;
	}

