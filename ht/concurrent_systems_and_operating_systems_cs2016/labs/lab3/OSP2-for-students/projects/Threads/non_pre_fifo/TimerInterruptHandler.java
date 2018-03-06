package osp.Threads;

import osp.IFLModules.*;
import osp.Utilities.*;
import osp.Hardware.*;

public class TimerInterruptHandler extends IflTimerInterruptHandler
{

    public void do_handleInterrupt()
    {
       ThreadCB.dispatch();
    }
}

