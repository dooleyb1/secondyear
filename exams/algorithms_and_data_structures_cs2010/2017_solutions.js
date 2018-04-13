--------------------------------------------------------------------------
** Question 1 ** - Abstract Data Types
--------------------------------------------------------------------------

//  a)
  /*  i. Write the main operations in the API of the Symbol Table Abstract
        Data Type (ADT) and briefly describe what they do.
  */

    public class ST<Key extends Comparable<Key>, Value>
    --------------------------------------------------------------------
                    ST()                        //Create an empty symbol table
    void            put(Key key, Value val)     //Associate val with key
    Value           get(Key key)                //Get val associated with Key
    void            remove(Key key)             //Remove key (and associated val)
    boolean         contains(Key key)           //Is there a value associated with key?
    int             size()                      //Number of key-val pairs
    Iterable<Key>   put(Key key, Value val)     //All keys in the symbol table

    /*  ii. Using aymptotic notation, give the worst-case running time of each
          of the operations when implemented using a red-black BST.
    */

    void put() => O(2 lg N)
    Value get() => O(2 lg N)
    void remove() => O(2 lg N)
    boolean contains() => O(2 lg N)
    int size() => O(1)
    Iterable<Key> => O(N)

    /*  iii. Discuss the benefits of implementing a ST using a Red-Black tree
          instead of a hashtable.
    */

    1. There are kinds of data which can not be hashed (or are expnsive to hash)
        and therefore can not be held in hashtables.

    2. Trees keep the data in the order you need (sorted). You cant effectively
        do this with a hashtable.

    3. Trees have better worst-case performance.

/*  b) The following is the class of the nodes of a linked list
 *
 *        class LNode {
 *            public int data;
 *            public LNode next;
 *
 *            public LNode(int d, LNode nxt){
 *              data = d; next = next
 *            }
 *         }
 */

/*    Implement the method
 *
 *        LNode deleteAt(Lnode head, int pos)
 *
 */

    LNode deleteAt(LNode head, int pos){

      //If list is empty return null
      if(head.next == null && head.data == null)
        return null;

      //If pos == 0 -> deleteHead()
      else if(pos == 0){
        head.next = null;
        head.data = null;
        return head;
      }

      else {
        LNode current = head;
        LNode prev = null;

        //Iterate through LList until you get node
        for(int i=1; i<pos; i++){
          prev = current;
          current = current.next();
        }

        //Delete middle node
        prev.next = current.next();
        return prev;
      }
    }

--------------------------------------------------------------------------
** Question 2 ** - Binary Trees
--------------------------------------------------------------------------

//  a)
  /*  i. You are given an algorithm A which runs in O(n^2 log2(n)). Providing
        justification indicate which of the following statements are correct.

        i.    Algorithm A runs in O(n^3),
        ii.   Algorithm A runs in O(n^2),
        iii.  Algorithm A runs in O-(n^3),
        iv.   Algorithm A runs in O-(n^2 log2(n)),
        v.    Algorithm A runs in O(n^2 log10(n)),
  */

  i = False, since O(n^3) is n times larger or worse than O (n^2) and thus
      larger than O(n^2 log2(n).

  ii = False, since O(n^2 log2(n)) is smaller than O(n^2) since log2n is
      increasing.

  iii = False, since if the runtime is less than O(n^3) the order of growth
      cannot be this O(n^3).

  iv = True, since the orders of growth matches the runtime.

  v = False, since log10(n) is worse/higher than log2(n)


  /*  b) A binary tree has nodes which are objects of the following class
   *
   *        class TreeNode {
   *            int key;
   *            TreeNode left, right;
   *         }
   */

   /*  i.Implement the method
    *
    *        boolean checkBounds(TreeNode rt, int minval, int maxval)
    *
    */

          boolean checkBounds(TreeNode rt, int minval, int maxval){

            boolean withinBounds = false;

            //Traverse all right leaning sub-trees checking within bounds
            withinBounds = checkRight(rt, minval, maxval);
            //Traverse all left leaning sub-trees checking within bounds
            withinBounds = checkLeft(rt, minval, maxval);

            return withinBounds;
          }

          boolean checkRight(Treenode rt, int minval, maxval){

            boolean safeRight = false;

            //If not furthest right, go righter
            if(rt.right != null)
              safeLeft = checkLeft(rt.right, minval);

            //Else if last node right, check that minval is safe
            else if(rt.right == null)
              if(rt.key <= maxval && rt.key >= minval)
                safeRight = true;

            return safeRight;
          }

          boolean checkLeft(Treenode rt, int minval, int maxval){

            boolean safeLeft = false;

            //If not furthest left, go lefter
            if(rt.left != null)
              safeLeft = checkLeft(rt.left, minval, maxval);

            //Else if last node left, check that minval is safe
            else if(rt.left == null)
              if(rt.key <= maxval && rt.key >= minval)
                safeLeft = true;

            return safeLeft;
          }



     /*  ii. Change your implementation so that it only checks if all keys
            are greater than or less than minval and maxval.
      *
      *        boolean checkBounds(TreeNode rt, int minval, int maxval)
      *
      */

      boolean checkBounds(TreeNode rt, int minval, int maxval){

        boolean withinBounds = false;

        //Check left for min
        withinBounds = checkMin(rt, minval);
        //Check right for max
        withinBounds = checkMax(rt, maxval);

        return withinBounds;
      }

      boolean checkMin(TreeNode rt, int minval){

        //First check minval on left
        if(rt.left.key < minval)
          if(checkMin(rt.left, minval));
            return true;

      }

      boolean checkMax(TreeNode rt, int maxval){

        //First check minval on left
        if(rt.right.key > maxval)
          if(checkMax(rt.right, maxval))
            return true;;

      }
