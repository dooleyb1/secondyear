	switch (t->back) {
	default: Uerror("bad return move");
	case  0: goto R999; /* nothing to undo */

		 /* CLAIM never_0 */

	case 3: // STATE 1
		;
		finish = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 5: // STATE 3
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC Q */

	case 6: // STATE 1
		;
	/* 0 */	((P1 *)this)->count = trpt->bup.oval;
		;
		;
		goto R999;

	case 7: // STATE 4
		;
		now.n = trpt->bup.oval;
		;
		goto R999;

	case 8: // STATE 5
		;
		((P1 *)this)->count = trpt->bup.oval;
		;
		goto R999;

	case 9: // STATE 9
		;
		finish = trpt->bup.oval;
		;
		goto R999;

	case 10: // STATE 10
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC P */

	case 11: // STATE 1
		;
	/* 0 */	((P0 *)this)->counter = trpt->bup.oval;
		;
		;
		goto R999;

	case 12: // STATE 4
		;
		((P0 *)this)->temp = trpt->bup.oval;
		;
		goto R999;

	case 13: // STATE 5
		;
		((P0 *)this)->temp = trpt->bup.oval;
		;
		goto R999;

	case 14: // STATE 6
		;
		now.n = trpt->bup.oval;
		;
		goto R999;

	case 15: // STATE 7
		;
		((P0 *)this)->counter = trpt->bup.oval;
		;
		goto R999;

	case 16: // STATE 11
		;
		finish = trpt->bup.oval;
		;
		goto R999;

	case 17: // STATE 12
		;
		p_restor(II);
		;
		;
		goto R999;
	}

