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

--------------------------------------------------------------------------
** Question 3 ** - Shortest Paths
--------------------------------------------------------------------------

/*  a) Describe what is the postorder and the topological sort of the
 *    vertices of a Directed Acyclic Graph
 */

  **Postorder** - A tree traversal where the left and right subtrees are
                  recursively traversed before the root is finally
                  traversed.

  **Topological Sort** - Topological sort is when all of the vertices of
                          a DAG point upwards. This is achieved by returning
                          vertices in reverse postorder.

/*  b) Explain in English the algorithm that finds the topological sort of
 *     a DAG. You can presume DFS and BFS are known.
 */

   1) Run DFS on a graph and return all vertices in postorder.
   2) Reverse all vertices to reversed postorder.

/*  c) Give the postoder and topological sort of the vertices of the following
 *      graph.
*/

  1) Postoder:

      G F D E B C A

  2) Topological Sort:

      A C B E D F G

/*  d) Give the postoder and topological sort of the vertices of the following
 *      graph.
*/

    1) Dijkstras algorithm:

      - Runtime = O(V^2) OR O(E + V log V) with a Fibonacci Heap
      - Limitations = Non-Negative Weights, No Cycles
      - Suited Graphs = Graphs with non-negative edgeweights

    2) Topological Sort algorithm:

      - Runtime = O(E + V)
      - Limitations = No Directed Cycles
      - Suited Graphs = Acyclic Edge-Weighted Digraphs

    3) Bellman-Ford algorithm:

      - Runtime = O(E * V)
      - Limitations = No Negative Cycles
      - Suited Graphs = Graphs with No Negative Cycles

/*  e) Give the SPT, rooted at vertex A, in the above graph G. Which Algorithm
 *      is best suited for finding the shortest paths in this graph
*/

    Topological Sort:

        A C B E D F G


    v    distTo[]    edgeTo[]
    ---------------------------
    A       0           -
    B       10         A->B
    C       10         A->C
    D       20         B->D
    E        5         B->E
    F        0         E->F
    G        5         F->G

    -Best method suited for finding shortest path in this graph would be the
      Topological Sort Algorithm as it is suited to Acyclic Edge-Weighted
      Digraphs and allows for negative edge weightings.

------------------------------------------------------------------------------
** Question 3 ** - Shortest Paths
------------------------------------------------------------------------------

/*  a) Give the SPT, rooted at vertex A, in the above graph G. Which Algorithm
 *      is best suited for finding the shortest paths in this graph
*/

public static int binarySearch(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;

            if(key < a[mid])
              hi = mid - 1;

            else if(key > a[mid])
              lo = mid + 1;

            else
              return mid;
        }
        return -1;
    }

/*  a) Consider the Basic_Matrix class on the next page. Implement from the
*     Basic Matrix class the following Java method.
*/

    int min_index(int[] arr){

      int minElement = arr[0];
      int minIndex = 0;

      //Since no sorting, have to iterate through array
      for(int i=0 < i<arr.length(); i++){
        if(arr[i] < minElement){
          minElement = arr[i];
          minIndex = i;
        }
      }
    }

/*  b) A Matrix M has a Saddle Point iff for some position (i,j), M(i,j) is the
 *    minimum of row i and maximum of column j.
*/

    void one_saddle(Basic_Matrix m){

      int rowIndex = 0;
      int minRowVal = m[0][0];

      int columnIndex = 0;
      int maxColumnVal = m[0][0];

      //Iterate over all rows
      for(int i=0 < i<m.length(); i++){

        minRowVal = m[i][0];
        columnIndex = 0;

        //Find the minimum element for each row
        for(int j=1; j<m.length(); i++){
          if(minRowVal > m[i][j]){
            minRowVal = m[i][j];
            minRowIndex = i;
            columnIndex = j;
          }
        }

        //Check if this mininmum element is also the max for its column;
        int k;
        for(k=0; k<n; k++){
          if(minRowVal < mat[k][columnIndex])
            break;
        }

        if(k == m.length()){
          System.out.println("Value of saddle point is "
                    + m[minRowIndex][minColumnIndex]);
          return 0;
        }
      }
      return 0;
    }
