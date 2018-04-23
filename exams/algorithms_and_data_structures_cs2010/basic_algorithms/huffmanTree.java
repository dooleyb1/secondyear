// ----------------------------------------------------------
/**
 * Huffman encoding of a bitstring into a tree involves Recursively
 * creating subtries of the minimum element of a priority queue
 * until there are no further elements remaining.
 *
 * @param freq: Array representing the frequency of ASCII letters.
 * @return Huffman encoded trie.
 *
 * ----------------------------------------------------------
 *
 *
 */
private static Node buildTree(int[] freq){

  MinPQ<Node> pq = new MinPQ<Node>();

  //Iterate over radix, if freq[letter] > 0 create node and add to PQ
  for(char i = 0; i < R; i++)
    if(freq[i] > 0)
      pq.insert(new Node(i, freq[i], null, null));

  //Recursively create subtrees from PQ
  while(pq.size() > 1){

    //Take two minimum elements from PQ
    Node x = pq.delMin();
    Node y = pq.delMin();

    //Start letter '\0' is unused root letter
    Node parent = new Node('\0', (x.freq + y.freq), x, y);
    pq.insert(parent);
  }

  return pq.delMin();
}

// ----------------------------------------------------------
/**
 * Class to represent the Nodes of a Huffman Trie
 *
 * ------------------------------------------------------------
 */
private static class Node implements Comparable<Node>{

    private final char ch;
    private final int freq;
    private final Node left, right;

    public Node(char ch, int freq, Node left, Node right){
      this.ch = ch;
      this.freq = freq;
      this.left = left;
      this.right = right;
    }

    public boolean isLeaf(){
      return left == null && right == null;
    }

    public int compareTo(Node that){
      return this.freq - that.freq;
    }
}
