package osp.FileSys;

import java.util.*;
import osp.IFLModules.*;
import osp.Hardware.*;
import osp.Utilities.*;
import osp.Devices.*;


/**
   This class implements a mount table -- an array of directory names
   associated with logical devices.

   Note that all methods in this class are static, so the mount table
   has to be implemented as a static data structure.
   
   @OSPProject FileSys
*/

public class MountTable extends IflMountTable
{
    /**
       Returns true, if dirname is a mount point; false otherwise.
       @param dirname

       @OSPProject FileSys
    */
    public static boolean do_isMountPoint(String dirname)
    {
        // your code goes here

    }

    /**
       Returns the Id of the device where <code>pathname</code> resides.

       @param pathname a file or directory name to find the deviceID for
       @return deviceID of the device, where this file or dir resides, 
       NONE if the file device is not found. Since there is a root directory 
       with mount poit that consists just of a DirSeparator, this 
       should only happen if the name does not start with the directory 
       separator symbol.

       @OSPProject FileSys
    */
    public static int do_getDeviceID(String pathname)
    {
        // your code goes here

    }


    /*
       Feel free to add methods/fields to improve the readability of your code
    */

}

/*
      Feel free to add local classes to improve the readability of your code
*/
