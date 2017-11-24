import java.util.ArrayList;
import java.util.TreeMap;

public class AVLTree {
    
	//**************************************************
	//* NESTED NODE CLASS                              *
	//**************************************************
	private class Node {
		Node left, right; // holds each child node individually
		int key, height;  // holds the key value and node height respectively
		String data;      // Holds the word to the song
		
		/**
		 * A no-argument constructor. Creates an empty root
		 */
		public Node() {
			left = right =  null;
			data = null;
			height = key = 0;
		}
		/**
		 * This constructor takes the data to be inserted
		 * into the AVL tree.
		 * @param string, the data for the node
		 */
		public Node(int key, String string) {
			left = right = null;
			data = string;
			this.key = key;
			height = 0;
		}
	}
	
	//**************************************************
	//* MAIN AVL TREE CLASS                            *
	//**************************************************
	private Node root;    // the eldest ancestor in the tree.
	private ArrayList<Node> innodes, prenodes, postnodes; // used to return traversals
	
	/**
	 * No-argument constructor for the AVL tree
	 */
	public AVLTree() {
		root = null;
		innodes = prenodes = postnodes = new ArrayList<Node>();
		
	}
	
	/**
	 * Boolean method to determine if there are any nodes
	 * in the tree.
	 * @return boolean, whether or not there are nodes in the tree
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Logically resets the tree to void because every method
	 * relies on passing root in as the "node"
	 */
	public void destroy() {
		root = null;
	}
	
	/**
	 * returns the height of the given node in the tree
	 * @param node, the node in question
	 * @return int, either the height, or -1 for invalid
	 */
	private int height( Node node ) {
		// returns either the height of the node
		// or a -1 if the node is null
		return node == null ? -1: node.height;
	}
	
	/**
	 * Test method
	 * @param key
	 * @return
	 */
	public int getHeight( int key ) {
		Node node = getNode( key );
		return node == null ? -1: node.height;
	}
	
	/**
	 * The max function is used to determine which side of the tree
	 * is the tallest/shortest. This is used to handle rotations to
	 * balance the tree.
	 * @param leftHeight, the height of left branch
	 * @param rightHeight, the height of right branch
	 * @return an integer for the height
	 */
	private int max( int leftHeight, int rightHeight ) {
		// return the tallest side
		return leftHeight < rightHeight ? leftHeight : rightHeight;
	}
	
	/**
	 * The public helper function used to insert nodes into the
	 * tree.
	 * @param string, the data held in the node
	 * @param key, the numerical index of the node
	 */
	public void insert( int key, String string ) {
		root = insert( key, string, root );
	}
	
	/**
	 * The private insert method does all of the work of the insertion.
	 * you pass it the key to be added in, the string to be held, and
	 * a node.
	 * @param key, numerical index of the node
	 * @param string, the data held in the node
	 * @param node, the root node
	 * @return node, after the insertion
	 */
	private Node insert( int key, String string, Node node ) {
		
		// if the node is null, make a new node (at the leaf)
	    if( node == null ) {
            
	    	return new Node( key, string );
	    }
	    // Compare keys and call insert again
        if( key < node.key ) {
            node.left = insert( key, string, node.left );
        }
        else if( key > node.key ) {
            node.right = insert( key, string, node.right );
        }
        else {
            ;  // Duplicate; do nothing
        }
    	// check balance and react accordingly
        return balance( node );
	}
	
	/**
	 * Causes a rotation with the left child of the node that is
	 * passed into the function. The top node is the one that rotates
	 * to the left in this method
	 * @param node, the parent node
	 * @return leftNode, the left child of the original node
	 */
	private Node rotateLeft( Node node ) {
		Node leftNode = node.left;
		node.left = leftNode.right;
		leftNode.right = node;
		
		// Adjust height values held in the nodes
		node.height = Math.max( height( node.left ), height( node.right ) ) + 1;
		leftNode.height = Math.max( height( leftNode.left ), node.height ) + 1;
		
		return leftNode;
	}
	
	/**
	 * The same as the rotate left function, but with the right child.
	 * @param node, parent node
	 * @return rightNode, the right child of the original parent
	 */
	private Node rotateRight( Node node ) {
		Node rightNode = node.right;
		node.right = rightNode.left;
		rightNode.left = node;
		node.height = Math.max( height( node.left ), height( node.right ) ) + 1;
		rightNode.height = Math.max( height( rightNode.right ), node.height ) + 1;
		
		return rightNode;
	}
	
	/**
	 * The double rotate method makes use of the fact that both of
	 * the existing single rotate methods return a node in the proper
	 * state after the rotations.
	 * @param node, original parent node
	 * @return node, the node now in the parent position
	 */
	private Node doubleRotateLeft( Node node ) {
		node.left = rotateRight( node.left );
		return rotateLeft( node );
	}
	
	/**
	 * Opposite as double rotate left
	 * @param node, original parent
	 * @return node, new parent
	 */
	private Node doubleRotateRight( Node node ) {
		node.right = rotateLeft( node.right );
		return rotateRight( node );
	}
	
	/**
	 * Public interface of the counting function
	 * @return the number of nodes
	 */
	public int numberOfNodes() {
		return numberOfNodes( root );
	}
	
	/**
	 * The worker method that recursively counts the nodes by
	 * calling this method on each of the child nodes.
	 * @param node
	 * @return the number of nodes
	 */
	private int numberOfNodes( Node node ) {
		if( node == null ) {
			return 0;
		} else {
			int count = 1;
			count += numberOfNodes( node.left );
			count += numberOfNodes( node.right );
			
			return count;
		}
	}
	
	/**
	 * The public search interface that only receives the key
	 * that the user can use to verify that a particular key
	 * has an associated value in the tree.
	 * @param key
	 * @return
	 */
	public boolean containsKey( int key ) {
		return containsKey( key, root );
	}
	
	/**
	 * This does the work for the search method. It returns a boolean
	 * indicating whether or not a value is located within the tree.
	 * @param key
	 * @param node
	 * @return
	 */
	private boolean containsKey( int key, Node node ) {
		boolean found = false;
		while( ( node != null ) && !found ) {
			int nodeKey = node.key;
			if( key < nodeKey ) {
				node = node.left;
			} else if( key > nodeKey ) {
				node = node.right;
			} else {
				found = true;
				break;
			}
			
			found = containsKey( key, node );
		}
		return found;
	}
	
	/**
	 * Giving the key to this public interface passes it to
	 * a private method that handles the search logic.
	 * @param key
	 * @return String associated with key
	 */
	public String getValue( int key ) {
		return getValue( key, root );
	}
	
	/**
	 * Recursively searches for the node that contains the value
	 * associated with the given key.
	 * 
	 * @param key
	 * @param node
	 * @return
	 */
	private String getValue( int key, Node node ) {
		boolean found = false;
		while( ( node != null ) && !found ) {
			int nodeKey = node.key;
			if( key < nodeKey ) {
				node = node.left;
			} else if( key > nodeKey ) {
				node = node.right;
			} else {
				found = true;
				break;
			}
			
			found = containsKey( key, node );
		}
		return node.data;
	}
	
	/**
	 * public interface that returns desired node given a key
	 * @param key, index for the desired node
	 * @return node, the desired node
	 */
	public Node getNode( int key ) {
		return getNode( key, root );
	}
	
	/**
	 * The private helper function that executes the public
	 * call
	 * @param key, index for desired node
	 * @param node, the root node
	 * @return node, the desired node
	 */
	private Node getNode( int key, Node node ) {
		boolean found = false;
		while( ( node != null ) && !found ) {
			int nodeKey = node.key;
			if( key < nodeKey ) {
				node = node.left;
			} else if( key > nodeKey ) {
				node = node.right;
			} else {
				found = true;
				break;
			}
			
			found = containsKey( key, node );
		}
		return node;
	}
	
	/**
     * Public interface for removal from the tree.
     * @param key for the item to remove.
     */
    public void delete( int key )
    {
        root = delete( key, root );
    }

       
    /**
     * Private worker method to remove from a tree.
     * @param key, for the item to remove.
     * @param node, the root of the tree/subtree
     * @return node, the new root after the deletion.
     */
    private Node delete( int key, Node node )
    {
    	// Handle all null instances
        if( node == null ) {
            return node;
        }
                        
        if( key < node.key ) {
            node.left = delete( key, node.left );
        }
        else if( key > node.key ) {
            node.right = delete( key, node.right );
        }
        
        // if there are two children, then we have shenanigans
        // currently deletes the highest key in the whole tree
        // and copies the lowest value in the node's subtree
        else if( node.left != null && node.right != null ) {
            node.data = findMin( node.right ).data;
            node.right = delete( node.right.key, node.right );
        }
        else {
            node = ( node.left != null ) ? node.left : node.right;
        }
        
        return balance( node );
    }
    
    /**
     * A simple method that finds the smallest value in the tree.
     * It does this by repeatedly asking for the left child of each node it
     * finds until it receives a null. This is the leftmost leaf.
     * @param node, a starting node position
     * @return node, the smallest child in the tree
     */
    private Node findMin( Node node ) {
    	if( node == null ) {
    		return node;
    	}
    	while( node.left != null ) {
    		node = node.left;
    	}
    	return node;
    }
    
    /**
     * A balancing method derived from research on the web. It's a late comer in the
     * development, so it should really be placed in a few other places as well. (insert
     * being a prime example).
     * 
     * @param node, the node to balance
     * @return node, the balanced node
     */
    private Node balance( Node node ) {
    	// check to see if node is null. Quit if that's the case
    	if( node == null ) {
    		return node;
    	}
    	// test to see if the left side is more than 1 taller than the right
    	if( height( node.left ) - height( node.right ) > 1 ) {
    		// if it is, check to see if we have a double left of a left, right
    		// imbalance and respond accordingly
    		if( height( node.left.left ) >= height( node.left.right ) ) {
    			node = rotateLeft( node );
    		} else {
    			node = doubleRotateLeft( node );
    		}
    	} else {
    		// do the same thing for the right side
    		if( height( node.right )  - height( node.left ) > 1 ) {
    			if( height( node.right.right ) >= height( node.right.left ) ) {
    				node = rotateRight( node );
    			} else {
    				node = doubleRotateRight( node );
    			}
    		}
    	}
    	
    	// reset the node's height value by taking the tallest value from its
    	// children and incrementing it by one.
    	node.height = Math.max( height( node.left ), height( node.right ) ) + 1;
    	
    	return node;
    }
    
  	
	/**
	 * public interface used to traverse the tree inorder
	 */
	public ArrayList<Node> inorder() {
		return inorder( root );
	}
	
	/**
	 * Recursively prints out the nodes of the tree in order. This means
	 * that the left child is recursively called, then there is a
	 * print statement for the noce, then the right node comes last.
	 * @param node
	 */
	public ArrayList<Node> inorder( Node node ) {
		if( node != null ) {
			
			// inorder does the visit action between the recursive calls
			inorder( node.left );
			
			System.out.print( node.key + ": " + node.data + " " );
			innodes.add(node);
			
			inorder( node.right );
			
			return innodes;
		} else {
			return null;
		}
	}
	
	/**
	 * Public interface for preorder traversal
	 */
	public ArrayList<Node> preorder() {
		return preorder( root );
	}
	
	/**
	 * Work for the preorder traversal. The preorder traversal
	 * prints out the node, then the left child and the right child
	 * in that order.
	 * @param node
	 */
	private ArrayList<Node> preorder( Node node ) {
		if( node != null ) {
			
			// preorder does the visit action before the recursive calls
			System.out.print( node.key + ": " + node.data + " " );
			prenodes.add(node);
			
			preorder( node.left );
			preorder( node.right );
			return prenodes;
		} else {
			return null;
		}
	}
	
	/**
	 * The postorder traversal public interface. Note, all of these
	 * public interfaces cause the entire tree to be traversed from
	 * whatever node is presently assigned as the root.
	 */
	public ArrayList<Node> postorder() {
		return postorder( root );
	}
	
	/**
	 * The postorder working method recursively calls the right
	 * and the left nodes, then it prints last.
	 * @param node
	 */
	private ArrayList<Node> postorder( Node node ) {
		if( node != null ) {
			
			// postorder does the visit action after the recursive calls
			postorder( node.left );
			postorder( node.right );
			
			System.out.print( node.key + ": " + node.data + " " );
			postnodes.add( node );
			return postnodes;
		} else {
			return null;
		}
	}
}
