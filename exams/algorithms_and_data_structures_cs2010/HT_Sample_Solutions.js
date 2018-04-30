--------------------------------------------------------------------------
** Question 1 ** - Sorting Algorithms
--------------------------------------------------------------------------

/*  a) Explain brute-force approach to algorithm design and give a well-known
 *      example of a brute-force algorithm.
 */

  /*
   * Selection Sort is an example of a brute-force sorting algorithm.
   *
   * Selection Sort iterates over the array, finding the smallest element
   * and swapping it with the element in the first index, then the second
   * smallest element with the second index and so on.
   *
   * Worst Case - O(n^2)
   */
  public static int[] selectionSort(int[] a){

    int n = a.length();
    int temp;

    //Iterate over array
    for(int i=0; i<n-1; i++){
      //Find index of min element
      int min = i;
      for(int j=i; j<n-1; j++){
        if(a[j] < a[min])
          min = j;
      }

      //Swap the found min with the a[i] element
      temp = a[i];
      a[i] = a[min];
      a[min] = temp;
    }
    return a;
  }

/*  b) List 4 algorithms you could use to sort a number of objects in
*     ascending order. Compare the approaches with respect to their best
*     case and worst case running time.
*/

   1) Insertion Sort - Small arrays, in (almost) order.
   2) Quick Sort - General purpose, when space is tight.
   3) Merge Sort - General purpose, stable.
   4) Radix Sort - When the range of elements is small.

   1) Insertion Sort - Best = O(n)
                       Worst = O(n^2)

   2) Quick Sort -     Best = O(n log n)
                       Worst = O(n^2)

   3) Merge Sort -     Best = O(n log n)
                       Worst = O(n^2)

   4) Radix Sort -     Best = O(nk)
                       Worst = O(nk)

/*  c) Briefly explain in English how each of the sorting algorithms
*     studied work.
*/

    ------------------------------------------------------------------------
    1) Merge Sort:
    ------------------------------------------------------------------------
       This is a divide and conquer method. The array divides
       itself in half and calls merge sort on recursively on the new array,
       each individual array is then sorted and merged back into one array.

       Best - O(n log n)
       Worst - O(n^2)
       Space (Worst) - O(n)

   ------------------------------------------------------------------------
   2) Quick Sort:
   ------------------------------------------------------------------------
      This is a divide and conquer method. The array picks a pivot element,
      and then repeatedly partitions the array around this pivot element.
      Each sub-section of the array a new pivot is picked. This is repeated
      until all sub-sections contain one element, which is then the ordered
      array.

      Best - O(n log n)
      Worst - O(n^2)
      Space (Worst) - O(log(n))


    ------------------------------------------------------------------------
    3) Insertion Sort:
    ------------------------------------------------------------------------
       Loop over each index of the array, each iteration fixing the next
       element into its correct place in the array of previously sorted
       elements. Repeat this until there are no elements left to be checked.

       Best - O(n)
       Worst - O(n^2)
       Space (Worst) - O(1)

     ------------------------------------------------------------------------
     4) Selection Sort:
     ------------------------------------------------------------------------
        Selection sort involves repeatedly fining the minimum element from
        an unsorted part and putting it at the beggining of the sorted array.
        The algorithm maintains two arrays, the sorted sub array and the
        unsorted sub array.

        Best - O(n^2)
        Worst - O(n^2)
        Space (Worst) - O(1)

      ------------------------------------------------------------------------
      5) Bubble Sort:
      ------------------------------------------------------------------------
         Bubble sort involves repeatedly sorting adjacent elements, two at
         a time. This is performed over multiple passes of the array. The
         algorithm is complete when a full pass is complete and no swaps
         are made.

         Best - O(n)
         Worst - O(n^2)
         Space (Worst) - O(1)

/*  d) Explain what is meant by a stable sorting algorithm. Provide an
*     example of a stable algorithm and a non-stable algorithm.
*/

  1) Stable Algorithm:

    A sorting algorithm is said to be stable if, when two elements of
    the same value appear in an array, they DO maintain their respective order
    even after sorting has occurred.

    Examples :  Insertion Sort, Merge Sort, Bubble Sort

  2) Unstable Algorithm:

     A sorting algorithm is said to be unstable if, when two elements of
     the same value appear in an array, they DO NOT maintain their respective
     order after sorting has occurred.

     Examples : Heap Sort, Quick Sort

--------------------------------------------------------------------------
** Question 2 ** - String Algorithms
--------------------------------------------------------------------------

/*  a) Explain in English or write code how key-index counting algorithm works
*/

 Key-Index counting is based on comparing the keys between a specific range.
 It works by counting the number of objects that have the same key value.
 It then uses this count array ti perform some arithmetic to work out the
 correct position for the elements.

 For simplicity, consider the data in the range 0 to 9.
 Input data: 1, 4, 1, 2, 7, 5, 2

1) Take a count array to store the count of each unique object.

  Index:     0  1  2  3  4  5  6  7  8  9
  Count:     0  2  2  0   1  1  0  1  0  0

2) Modify the count array such that each element at each index stores the
   sum of previous counts.

  Index:     0  1  2  3  4  5  6  7  8  9
  Count:     0  2  4  4  5  6  6  7  7  7

  The modified count array indicates the position of each object in
  the output sequence.

3) Output each object from the input sequence followed by decreasing
   its count by 1.

 Process the input data: 1, 4, 1, 2, 7, 5, 2.
 Position of 1 is 2.
 Put data 1 at index 2 in output.
 Decrease count by 1 to place next data 1 at an index 1 smaller than this index.

/*  b) What is meant by radix sort, give an example of an algorithm that
*     uses Radix Sort.
*/

  The idea of Radix Sort is to do digit by digit sort starting from least
  significant digit to most significant digit. Radix sort uses counting sort
  as a subroutine to sort.

  Original, unsorted list:
  170, 45, 75, 90, 802, 24, 2, 66

  Sorting by least significant digit (1s place) gives:
  //[*Notice that we keep 802 before 2, because 802 occurred before 2 in
  // the original list, and similarly for pairs 170 & 90 and 45 & 75.]
    ↓   ↓    ↓  ↓   ↓   ↓   ↓   ↓
  170, 90, 802, 2, 24, 45, 75, 66

  Sorting by next digit (10s place) gives:
  //[*Notice that 802 again comes before 2 as 802 comes before 2 in
  // the previous list.]
   ↓  ↓   ↓   ↓   ↓    ↓   ↓   ↓
  802, 2, 24, 45, 66, 170, 75, 90

  Sorting by most significant digit (100s place) gives:
 ↓  ↓   ↓   ↓   ↓   ↓    ↓    ↓
  2, 24, 45, 66, 75, 90, 170, 802

  Examples: Most-Significant-Digit (MSD) Radix Sort

/*  c) What are LSD and MSD, and what is the difference between them? When
*    would you use each?.
*/

   1) Least Significant Digit (LSD) Radix Sort:

   This is a fast STABLE sorting algorithm which can be used to sort keys in
   integer representation order. Keys may be a string of characters, or numerical
   digits within a given radix. The processing of keys begins at the least
   significant digit and proceeds to the most significant digit.

   You would use this sort when you want elements to be returned within a
   numerical order e.g 1,2,3,4,5,6

   2) Most Significant Digit (MSD) Radix Sort:

   This is a fast UN-STABLE sorting algorithm which can be used to sort keys in
   lexicographic order (e.g 1,10,12,2,20,30). Unlike a LSD radix sort, MSD
   DOES NOT preserve the original order of duplicate keys. An MSD sort starts
   processing keys from the most significant digit and proceeds to the least
   significant digit.

   You would use this sort when you want elements to be returned within a specific
   numerical order e.g 1,10,12,2,20,30

/*  d) Discuss the performance of LSD compared to the performance of Insert
*     or merge or quick sort.
*/

    LSD radix sort serves as an alternative high-performance sorting algorithm
    to the likes of heapsort and mergesort that require O(n log n) comparisons.

    Comparison sorts can do no better than O(n log n) execution time but offer
    the flexibility of being able to sort with respect to more complicated
    orderings than a lexicographic one.

    An LSD radix sort operates in O(nw) time, where n is the number of words
    and w is the average key length.


/*  e) List 4 algorithms you could use to search for a substring in a larger
*     string. Compare performance in time and space of these algorithms.
*     Discuss situations where one approach is preffered over another.
*/

     ------------------------------------------------------------------------
     1) Brute Force:
     ------------------------------------------------------------------------
        This method involves iterating over the entire string that is to be
        searched, continuouslly trying to find the needle in the haystack.
        Effecient for small haystacks with small needles. This method involves
        keeping a backup buffer of previously matched characters.

        Worst - O(NEEDLE * HAYSTACK)
        Space (Worst) - O(NEEDLE)

      ------------------------------------------------------------------------
      2) Knuth-Morris-Pratt (KMP):
      ------------------------------------------------------------------------
         This method is some-what a brute force method however, it prevents
         the need for a backup buffer by using a Deterministic Final State
         Automaton (DFA). This keeps track of states, and transitions between
         states.

         Worst - O(NEEDLE + HAYSTACK)
         Space (Worst) - O(RADIX * NEEDLE) *for DFA

       ------------------------------------------------------------------------
       3) Boyer-Moore:
       ------------------------------------------------------------------------
          When find a character not in pattern, can skip up to M characters,
          this prevents the need to loop through all N characters. This method
          doesnt look through characters in order, instead it starts from the
          back and looks at the last character in the pattern first and see if
          its a match. Uses a backup buffer.

          Worst - O(NEEDLE * HAYSTACK)
          Space (Worst) - O(NEEDLE)

        ------------------------------------------------------------------------
        4) Rabin-Karp:
        ------------------------------------------------------------------------
           Compute a hash of the individual characters of the pattern. Compute
           a hash of every letter within the haystack. If pattern hash = text
           substring hash, check for match.

           Worst - O(7 * N))
           Space (Worst) - O(M)

/*  f) Explain what a DFA is and how it is used in KMP substring search
*      algorithm.
*/

    A DFA is a Deterministic Finite State Automaton, which is an array
    representation of a finite state machine. The elements of the array
    represent the transitions of the machine and the indices represent the
    states.

    A DFA is used in Knuth-Morris-Pratt substring search algorithm to prevent
    the need for a backup buffer. It allows the algorithm to keep track of
    current states and how to transition from one state to another.

/*  g) Build a DFA for the state transitions to be used in the KMP search
*    when searching for a substring "BANANA" in an alphabet consisting
*    only of the letters A, B, C, N, G
*/

    ------------------------------------------
    How to construct a DFA:
    -----------------------------------------
    1)Include one state for each character in pattern
    2)Advance to next state if c == pat.charAt(j)
    3)Back up if c != pat.charAt(j)


                j |  0  |  1  |  2  |  3  |  4  |  5  |  6
                --------------------------------------------
    pat.charAt(j) |  B  |  A  |  N  |  A  |  N  |  A  |
                --------------------------------------------
                A |  0  |  2  |  0  |  4  |  0  |  6  |
       dfa[][j] B |  1  |  1  |  1  |  1  |  1  |  1  |
                C |  0  |  0  |  0  |  0  |  0  |  0  |
                N |  0  |  0  |  3  |  0  |  5  |  0  |
                G |  0  |  0  |  0  |  0  |  0  |  0  |

/*  h) Using the above DFA provide a trace of state transitions that KMP
*     algorithm would construct when searching for a substring "BANANA"
*     in a string "ABCNGBANCG"
*/

        states |  0  |  1  |  2  |  3  |  4  |  5  |  6
   -------------------------------------------------------
   stringLetter|  A  |  B  |  C  |  N  |  G  |  B  |  A  |  N  |  C  |  G
   currentState|  0  |  1  |  0  |  0  |  0  |  1  |  2  |  3  |  0  |  0

/*  i) You are given a string searchIn = "teststringjoananomatch" and
*     a string searchFor = "ivana". How many character comparisons in
*     total will Boyer-Moore make searching. List the trace of skip
*     variable for thie search?
*/

    Boyer-Moore search algorithm uses the following structure:

    public static int indexOf(String txt, String pat){
      //Iterate over txt, incrementing by skip
      for(int i = 0; i < (txt.length()-pat.length()); i += skip){
        skip = 0;

        //Iterate backwards over pattern, searching for last letter first
        for(int j = pat.length()-1; j >= 0; j--){
          //If letters do not match, update skip
          if(pat.charAt(j) != txt.charAt(i+j)){
            skip = Math.max(1, j - right[txt.charAt(i+j)]);
            break;
          }
        }

        //Match
        if(skip == 0)
          return i;
      }
    }

    So, when searching for "ivana" in "teststringjoananomatch", the following
    steps occur:

    1) First Comparison will be as follows, ("a") from pat will be compared
       with ("s") from txt.

         →  →  →  →  X
        [t][e][s][t][s][t][r][i][n][g][j][o][a][n][a][n][o][m][a][t][c][h]
        [i][v][a][n][a]


    2) No match, skip now = righmost occurance of character ("s") in pattern.
       Since ("s") does not exist in pattern, skip is now = 4 - (-1) = 5..

    3) Second comparison will be as follows, ("a") from pat will be compared
       with ("g") from txt.

        skip = 5;

         →  →  →  →  →  →  →  →  →  X
        [t][e][s][t][s][t][r][i][n][g][j][o][a][n][a][n][o][m][a][t][c][h]
                       [i][v][a][n][a]

        skip = 0;

    4) No match, skip now = rightmost occurance of character ("g") in pattern.
       Since ("g") does not exist in pattern, skip is now = 4 - (-1) = 5.

    5) Third comparison will be as follows, ("a") from pat will be compared
       with ("a") from txt.

         skip = 5;
          →  →  →  →  →  →  →  →  →  →  →  →  →  →  X
         [t][e][s][t][s][t][r][i][n][g][j][o][a][n][a][n][o][m][a][t][c][h]
                                       [i][v][a][n][a]

         skip = 0;

    6) Match.
    7) Fourth comparison, ("n") from pat will be compared with ("n") from txt.
    8) Match.
    9) Fifth comparison, ("a") from pat will be compared with ("a") from txt.
    10) Match.
    11) Sixth comparison, ("v") from pat will be compared with ("o") from txt.

    12) No match, skip now = rightmost occurance of character ("o") in pattern.
       Since ("o") does not exist in pattern, skip is now = 1 - (-1) = 2.

    13) Seventh comparison will be as follows, ("a") from pat will be compared
       with ("o") from txt.

        skip = 2;

         →  →  →  →  →  →  →  →  →  →  →  →  →  →  →  →  X
        [t][e][s][t][s][t][r][i][n][g][j][o][a][n][a][n][o][m][a][t][c][h]
                                            [i][v][a][n][a]

        skip = 0;

    14) No match, skip now = rightmost occurance of character ("o") in pattern.
       Since ("o") does not exist in pattern, skip is now = 4 - (-1) = 5.

    13) Eight comparison will be as follows, ("a") from pat will be compared
        with ("h") from txt.

       skip = 5;

        →  →  →  →  →  →  →  →  →  →  →  →  →  →  →  →  →  →  →  →  →  X
       [t][e][s][t][s][t][r][i][n][g][j][o][a][n][a][n][o][m][a][t][c][h]
                                                          [i][v][a][n][a]

       skip = 0;

     14) No match, skip now = rightmost occurance of character ("h") in pattern.
        Since ("h") does not exist in pattern, skip is now = 4 - (-1) = 5.

     15) Pattern not found in txt.
         8 Comparisons in total.

/*  j) What are Tries and when is it suitable to use them?
*/

    Tries are a Data Structure used for searching with string keys.

    A Trie stores characters in nodes (not keys).
    Each Node has R children, one for each possible character.
    Each Node has a value - Null if not end of word.

/*  k) What is the difference between a Trie and a TST?
*/

    In a Trie, a node can have any R children, one for each possible
    character as defined by the user.

    However, in a Terneray (3) Search Trie each node has 3 children:

      1) Smaller (Left)
      2) Equal (Middle)
      3) Larger (Right)

    For example, if the character of the root is S, then the nodes:

      1) Left - Keys that start with a letter before S
      2) Middle - Keys that start with the letter S
      3) Right - Keys that start with a letter after S

--------------------------------------------------------------------------
** Question 3 ** - Graph Algorithm Questions
--------------------------------------------------------------------------

/*  a) Explain, in psuedo code, how the following graph algorithms work;
 *     DFS, BFS, Primms, Kruskals, Dijkstras, Bellman-Ford, Floyd-Warshall
*/
     ------------------------------------------------------------------------
     1) Depth-First-Search (DFS):
     ------------------------------------------------------------------------

        1) Mark vertex V as visited.
        2) Recursively visit all unmarked vertices adjacent to V.

        marked[v] - Whether or not there exists a path from source to V
        edgeTo[v] - What edge to take from S to get to V

      ------------------------------------------------------------------------
      2) Breadth-First-Search (BFS):
      ------------------------------------------------------------------------

         1) Initialize FIFO queue with source vertex V and mark it.
         2) Remove the least recently visited vertex V from the queue.
         3) Add to queue all unmarked vertices adjacent to V and mark them.

         Vertices visited in order of increasing distance/number of edges
         from source -> computes the shortest path from source to all other
         vertices.

       ------------------------------------------------------------------------
       3) Primms:
       ------------------------------------------------------------------------

          1) Choose any V from Graph as source
          2) Check edges adjacent to V, add edge of least cost to tree (e.g B)
          3) Check edges adjacent to both V and B, choose least cost
          4) Repeat until all edges are added

          Prims algorithm is used to get the minimum spanning tree of a
          connected graph.

        ------------------------------------------------------------------------
        4) Kruskals:
        ------------------------------------------------------------------------

          1) Arrange edges in order of increasing weight
          2) Add edge with lowest weight to tree
          3) Ensure that this addition DOES NOT create a circuit
          4) If it does skip, else continue

          Kruskals algorithm is used to get the minimum spanning tree of a
          connected graph.

        ------------------------------------------------------------------------
        5) Dijkstras:
        ------------------------------------------------------------------------

          1) Mark all nodes as unvisited
          2) Assign to every node a tentative dist value (0 for source, inf otherwise)
          3) Set initial node as currentNode
          4) For currentNode, consider all  unvisited neighbours
          5) Calculate tentativeDist through all neighbour nodes
          6) Compare the new tentativeDist to the current dist, re-assign if smaller
          8) When all neighbours are considered, mark as visited and remove from unvisited
          9) Move to next unvisited node with the SMALLEST tentative distance
          10) Repeat the above steps until all nodes visited

          Dijkstras algorithm is used for determining the shortest path within a
          graph from a source node to other nodes within a graph.

          -CANT handle negative edge weights
          -CANT handle negative cycles


        ------------------------------------------------------------------------
        6) Bellman-Ford:
        ------------------------------------------------------------------------

          -Exactly like Dijkstras this algorithm uses relaxation, however the
           set of edges is relaxed exactly V-1 times to ensure that it doesnt
           continue forever(which Dijkstas would)

           public static void bellmanFord(){

             for v in V:
                v.distance = infinity
                v.predecessor = None

             source.distance = 0

             for i from 1 to |V| - 1:
                for (u, v) in E:
                relax(u, v)

             relax(u, v):
                if v.distance > u.distance + weight(u, v):
                v.distance = u.distance + weight(u, v)
                v.predecessor = u
           }


          Bellman-Ford can be used for determining the shortest path witihin
          a graph whilst also ensuring that there are no negative cycles.

          -CAN detect negative cycles

        ------------------------------------------------------------------------
        7) Floyd-Warshall:
        ------------------------------------------------------------------------

          -Floyd-Warshall is an algorithm used for finding the shortest paths in
           a weighted graph with positive or negative edge weights (but with no
           negative cycles). The algorithm gives the shortest path between ALL
           pairs of vertices.


          public static void floydWarshall(){

            let dist be a |V| × |V| array of minimum distances initialized to ∞ (infinity)

            for each edge (u,v)
              dist[u][v] ← w(u,v)  // the weight of the edge (u,v)
            for each vertex v
              dist[v][v] ← 0

            for k from 1 to |V|
              for i from 1 to |V|
                for j from 1 to |V|

                  if dist[i][j] > dist[i][k] + dist[k][j]
                   dist[i][j] ← dist[i][k] + dist[k][j]
          }

          -CAN handle positive edges
          -CAN handle negative edge
          -CANT handle negative cycles

/*  b) What is a minimum spanning tree? List 2 algorithms you can use to find
 *     them, explain in English how they work and what is the difference between
 *     them in terms of performance and space requirements.
 */

      -The minimum spanning tree is a subset of the edges of a connected graph
      that connects all vertices without any cycles and with the minimum
      possible total edge weight.

      1) Prims Algorithm

            -Choose V
            -Add adjacent edge of least cost
            -Repeat

      2) Kruskals Algorithm

            -Arrange edges in order of weight
            -Add edge of least cost
            -Ensure addition didnt create cycle
            -Repeat

      Prims - O(E + V log V) * with a Fibonacci heap
      Kruskals - O(E log V)

      Sparse Graph = Kruskals (Simpler data structures)
      Dense Graph = Prims (Large number of edges saves space)


/*
 *  c) Explain why Prim (Kruskal) is said to be a greedy algorithm.
 */

      Prims & Kruskals are said to be greedy algorithms as they choose the
      minimum edge at every step which makes them greedy techniques. They
      always select the optimal solution, each iteration.

/*
 *  d) List 4 algorithms that can be used to find a shortest path between
 *     2 vertices. Explain the differences between them in terms of best/worse
 *     case running time, space requirements, and their applicability depending on graph characteristics
 */
      Algorithms:
      ---------------------------------------------------------------
      1) Topological Sort
      2) Dijkstras
      3) Bellman-Ford
      4) Floyd-Warshall

      Runtime (Worst Case):
      ----------------------------------------------------------------
      1) Topological Sort = O(V + E)
      2) Dijkstas = O(E + V log V)     *O(V^2) without Fibonacci Heap
      3) Bellman-Ford = O(V * E)
      4) Floyd-Warshall = O(V^3)

      Space Requirements:
      ----------------------------------------------------------------
      1) Topological Sort = O(V + E)
      2) Dijkstas = O(V)
      3) Bellman-Ford = O(V)
      4) Floyd-Warshall = O(V^2)

      Use Cases:
      ----------------------------------------------------------------
      1) Topological Sort =

          -Linear time
          -Graphs WITHOUT Directed Cycles
          -Only applicable when can find topological sorting
          -Can be used to find longest path

      2) Dijkstas =

          -Graphs WITHOUT negative edge weights
          -When you want to find shortest distance from ONE source
          -Graphs WITHOUT negative cycles
          -Sparse graphs

      3) Bellman-Ford =

          -Graphs WITH negative edge weights
          -When you want to find shortest distance from ONE source
          -Graphs WITH negative cycles (notifies their presence)
          -Sparse graphs (doesnt scale well)

      4) Floyd-Warshall =

          -Graphs WITH negative edge weights
          -When you want to find shortest distance from ALL nodes
          -Graphs WITHOUT negative cycles
          -Dense graphs


/*
 *   e) List 2 algorithms that can be used to detect if a graph contains
 *      negative cycles. Explain in English how is each used to do the detection
 */


        1) Bellman-Ford:

            -Iterate V-1 times to get the SP graph
            -On Vth iteration, check for negative cycle
            -Keep knowledge of dist[] from source and predecessor[] arrays
            -On Vth iteration, if dist[] array changes, negative cycle present

        2) Floyd-Warshall:

            -Initially, the length of the path (i,i) is 0
            -This can only be improved if dist[i][i] is less than 0
            -If dist[i][i] is less than 0, negative cycle present


/*
 *   f) Are Bellman-Ford and Floyd-Warshall examples of Dynamic Programming?
 *      If so, how?
 */
       Dynamic programming is a method for solving a complex problem by breaking
       it down into a collection of simpler subproblems, solving each of those
       subproblems just once, and storing their solutions.

       1) Bellman-Ford - YES, Dynamic Programming

          -Calculates the SP in bottom-up manner
          -Intermediate values are stored for next pass
          -It first calculates the shortest distances for the shortest paths
           which have at-most one edge in the path. Stores it.
          -Then, it calculates shortest paths with at-most 2 edges, and so on.
          -After the ith iteration of outer loop, the shortest paths with at
           most i edges are calculated.

      2) Floyd-Warshall - YES, Dynamic Programming

          -The idea is to one by one pick all vertices
          -Update all shortest paths which include the picked vertex
           as an intermediate vertex in the shortest path
          -We pick a vertex V
          -For every pair (i, j), there are two possible cases:

              a)  k is not an intermediate vertex in shortest path from i to j.
                  -> We keep the value of dist[i][j] as it is.

              b)  k is an intermediate vertex in shortest path from i to j.
                  -> We update the value of dist[i][j] as dist[i][k] + dist[k][j].
