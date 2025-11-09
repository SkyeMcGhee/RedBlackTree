package BinarySearchTree;

/**
 * This is a "generic" Binary Search Tree - know the definition of what a BST is!
 * 
 * NOTE: To allow for our objects to be inserted (and found) properly they have to be COMPARED
 * to the objects in the tree. This is why we have <T extends Comparable<T>> instead of 
 * just <T> : We are effectively saying that the objects which can be stored MUST implement
 * the Comparable interface.
 * 
 * NOTE: Our Node class is an inner class in an inner class at the bottom of the code.
 * 
 * @author dermot.hegarty
 *
 * @param <T>
 */
public class RedBlackTree<T extends Comparable<T>> {
	/**
	 * Reference to the root of the tree
	 */
	public Node root;
	

	/**
	 * This is the public insert method, i.e. the one that the outside world will invoke.
	 * It then kicks off a recursive method to "walk" through down through the tree - this is 
	 * possible because each sub-tree is itself a tree.
	 * @param value Object to insert into the tree
	 */
	public void insert(T value){
		Node node = new Node(value); // Create the Node to add
		node.isRed = true;
		//Special case that cannot be handled recursively
		if ( root == null ) {
			root = node;
			
			//part 3 update
			
			node.parent = null;
			System.out.println("Root set, val = " + value);
			return;
		}
		
		//Initially we start at the root. Each subsequent recursive call will be to a 
		//left or right subtree.
		insertRec(root, node);
		
		//part 3, call method to resolve colour
		handleRedBlack(node);
	}

	/**
	 * 
	 * @param subTreeRoot The SubTree to insert into
	 * @param node The Node that we wish to insert
	 */
	protected void insertRec(Node subTreeRoot, Node node)
	{

		//Note the call to the compareTo() method. This is only possible if our objects implement
		//the Comparable interface.
		if ( node.value.compareTo(subTreeRoot.value) < 0)
		{
			System.out.println("Lesser val found");
			//This is our terminal case for recursion. We should be going left but there is 
			//no leaf node there so that is obviously where we must insert
			if ( subTreeRoot.left == null )
			{
				subTreeRoot.left = node;
				//part 3, assigning parent
				node.parent = subTreeRoot;
				System.out.println("left is null");
				handleRedBlack(node);
				return; //return here is unnecessary
			}
			else
			{ // Note that this allows duplicates!
				
				//Now our new "root" is the left subTree
				insertRec(subTreeRoot.left, node);
			}
		}
		//Same logic for the right subtree
		else
		{
			System.out.println("greater val found");
			if (subTreeRoot.right == null)
			{
				System.out.println("Set right");
				subTreeRoot.right = node;
				//part 3, assigning parent
				node.parent = subTreeRoot;
				return;
			}
			else
			{
				insertRec(subTreeRoot.right, node);
			}
		}
		
	}
	
	
	/**
	 * Should traverse the tree "in-order." See the notes
	 */
	public void inOrderTraversal()
	{
		//start at the root and recurse
		recInOrderTraversal(root);
	}
	
	public void preOrderTraversal()
	{
		//start at the root and recurse
		recPreOrderTraversal(root);
	}
	
	public void postOrderTraversal()
	{
		//start at the root and recurse
		recPostOrderTraversal(root);
	}
	
	/**
	 * This allows us to recursively process the tree "in-order". Note that it is private
	 * @param subTreeRoot
	 */
	private void recInOrderTraversal(Node subTreeRoot)
	{
		if(subTreeRoot == null) return;
		
		recInOrderTraversal(subTreeRoot.left);
		processNode(subTreeRoot);
		recInOrderTraversal(subTreeRoot.right);
	}
	
	private void recPreOrderTraversal (Node subTreeRoot)
	{
		if(subTreeRoot == null) return;
		
		processNode(subTreeRoot);
		recPreOrderTraversal(subTreeRoot.left);
		recPreOrderTraversal(subTreeRoot.right);
	}
	
	private void recPostOrderTraversal (Node subTreeRoot)
	{
		if(subTreeRoot == null) return;
		
		recPostOrderTraversal(subTreeRoot.left);
		recPostOrderTraversal(subTreeRoot.right);
		processNode(subTreeRoot);
	}
	
	/** 
	 * Do some "work" on the node - here we just print it out 
	 * @param currNode
	 */
	private void processNode(Node currNode)
	{
		System.out.println(currNode.toString());
	}
	
	/**
	 * 
	 * @return The number of nodes in the tree
	 */
	public int countNodes()
	{
		return recCountNodes(root);
	}
	
	
	/**
	 * Note: This is a practical example of a simple usage of pre-order traversal
	 * @param subTreeRoot
	 * @return
	 */
	private int recCountNodes(Node subTreeRoot)
	{
		if (subTreeRoot == null) return 0;
		
		//Look at the pre-order. "Count this node and THEN count the left and right 
		//subtrees recursively
		return 1 + recCountNodes(subTreeRoot.left) + recCountNodes(subTreeRoot.right);
	}
	
	
	//practical 1, find min val in the tree
	public T findMinimum()
	{
		return recFindMinimum(root);
	}
	
	private T recFindMinimum (Node subTreeRoot)
	{
		if ( subTreeRoot.left == null ){
			return subTreeRoot.value; 
		}
		else
		{ 
			return recFindMinimum(subTreeRoot.left);
		}
      
	}
	
	//practical 1, find a val in the tree
	
	public T find(T searchVal)
	{
		//start at the root and recurse
		return recFind(root, searchVal);
	}
	
	private T recFind(Node subTreeRoot, T searchVal)
	{
		if(subTreeRoot == null) 
			return null;
         
		if(subTreeRoot.value.equals(searchVal))
		{
			return subTreeRoot.value;
		}
		else if(subTreeRoot.value.compareTo(searchVal) >0 )
		{
         return recFind(subTreeRoot.left, searchVal);
		}
      else
      {
		   return recFind(subTreeRoot.right, searchVal);
      }
		
	}
	
	//practical 2, rotate left
	
	public void rotateTreeLeft() 
	{
		root.right = rotateSubTreeLeft(root.right);
	}
	public void rotateTreeLeft(Node target)
	{
		target.right = rotateSubTreeLeft(target.right);
	}
	
	/// <summary>
	/// Rotate the subtree such that the current root becomes a left decendent and the current root.right becomes the root
	/// </summary>
	private Node rotateSubTreeLeft(Node subTreeRoot) 
	{
		Node parent = subTreeRoot.right;
		Node grandparent = subTreeRoot;
		Node greatGrandparent = grandparent.parent;
		
		// Orphan grandparent
		if(greatGrandparent != null)
			greatGrandparent.right = null;
		grandparent.parent = null;
		grandparent.right = null;
		
		insertRec(parent, grandparent);
		
		return parent;
	}
	
	//practical 2, rotate right
	
	public void rotateTreeRight() 
	{
		root = rotateSubTreeRight(root);
	}
	public void rotateTreeRight(Node target)
	{
		
		target.left = rotateSubTreeLeft(target.left);
	}
	
	private Node rotateSubTreeRight(Node subTreeRoot) 
	{
		
		Node parent = subTreeRoot.left;
		Node grandparent = subTreeRoot;
		Node greatGrandparent = grandparent.parent;
		
		// Orphan grandparent
		if(greatGrandparent != null)
			greatGrandparent.left = null;
		grandparent.parent = null;
		grandparent.left = null;
		
		insertRec(parent, grandparent);
		
		return parent;
	}
	
	//part 3, handling colour
	
	public void handleRedBlack (Node newNode) 
	{
		System.out.println("Entered handle redblack, val = " + newNode.value);
		
		//make sure the new node is red
		newNode.isRed = true;
		
		//if the node is root, set black and exit
		if(newNode == root)
		{
			newNode.isRed = false;
			return;
		}
		
		Node uncle = getFirstUncle(newNode);
		Node parentNode = newNode.parent;
		Node grandParent = parentNode.parent;
		
		//debugging block
		if(uncle == null)
			System.out.println("Uncle is Null");
		if (uncle != null)
			System.out.println("Uncle is " + uncle.value);
		
		if(parentNode == null)
			System.out.println("Parent is Null");
		if (parentNode != null)
			System.out.println("Parent is " + parentNode.value);
		
		if(grandParent == null)
			System.out.println("Grandparent is Null");
		if (grandParent != null)
			System.out.println("Grandparent is " + grandParent.value);
		
		//checking for and handling red uncle violations
		if(parentNode.isRed && grandParent != null) 
		{
			System.out.println("Handle red black, not root and not first born");
			
			if(uncle != null && uncle.isRed)
			{
				uncle.isRed = false;
				parentNode.isRed = false;
				grandParent.isRed = true;
				handleRedBlack(grandParent);
				return;
			}
			
		}
		
		//practical 4
		
		//handling black or null uncles
		if(uncle == null || uncle.isRed == false)
		{
			//if there are enough nodes to adjust
			if(parentNode != root && grandParent != null)
			{
				//Test for each type of violation
				if( (uncleOnRight(newNode) == false || uncle == null) && newNode == parentNode.right && grandParent.right == parentNode)
				{
					System.out.println("Right Right case detected");
					applyRightRightCase(newNode);
				}
				else if((uncleOnRight(newNode) || uncle == null) && newNode == parentNode.left && grandParent.left == parentNode)
				{
					System.out.println("Left Left case detected");
					applyLeftLeftCase(newNode);
				}
				else if(uncleOnRight(newNode) && newNode == parentNode.right && parentNode == parentNode.parent.left)
				{
					System.out.println("Left Right case detected");
					applyLeftRightCase(newNode);
				}
				else if(uncleOnRight(newNode) == false && newNode == parentNode.left && parentNode == parentNode.parent.right)
				{
					System.out.println("Right Left case detected");
					applyRightLeftCase(newNode);
				}
			}
		
		}
		
		//set the root to black
		root.isRed = false;
	}
	
	//part 3, finding uncles
	
	public boolean uncleOnRight(Node newNode) 
	{
		
		if(newNode != null
				&& newNode.parent != null
				&& newNode.parent.parent != null
				&& newNode.parent.parent.right != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	public Node getFirstUncle(Node newNode)
	{
		if(uncleOnRight(newNode))
			return getRightUncle(newNode);
		else
			return getLeftUncle(newNode);
	}
	
	public Node getRightUncle(Node newNode)
	{
		if(newNode != null
				&& newNode.parent != null
				&& newNode.parent.parent != null)
		{
			// Check if current node is Right decendant
			if(newNode.parent == newNode.parent.parent.right)
				return null;
			else
				return newNode.parent.parent.right;
		}
			else
				return null;
	}
	
	public Node getLeftUncle(Node newNode)
	{
		if(newNode != null
			&& newNode.parent != null
			&& newNode.parent.parent != null)
		{
			
			// are we the Right decedent
			if(newNode.parent == newNode.parent.parent.left)
				return null;
			else
				return newNode.parent.parent.left;
		}
		else
			return null;
	}
	
	//part 4, adjusting left left/ right right cases for black Uncles
	
	public void applyLeftLeftCase(Node target)
	{
		Node parent = target.parent;
		Node grandparent = parent.parent;
		Node greatGrandparent = grandparent.parent;
		
		if(greatGrandparent == null)
		{
			//Is a root swap
			parent.parent = null;
			root = rotateSubTreeRight(grandparent);
		}
		else
		{
			//Subtree rotation
			greatGrandparent.left = rotateSubTreeRight(grandparent);
		}
		
		//call handle red black to adjust colours
		handleRedBlack(grandparent);
	}
	
	public void applyRightRightCase(Node target)
	{
		Node parent = target.parent;
		Node grandparent = parent.parent;
		Node greatGrandparent = grandparent.parent;
		
		if(greatGrandparent == null)
		{
			//Is a root swap
			parent.parent = null;
			root = rotateSubTreeLeft(grandparent);
		}
		else
		{
			//Subtree rotation
			greatGrandparent.right = rotateSubTreeLeft(grandparent);
		}
		
		//call handle red black to adjust colours
		handleRedBlack(grandparent);
	}
	
	//part 5
	public void applyRightLeftCase(Node target)
	{
		Node pNode = target.parent;
		Node temp = pNode;
		//rotate
		rotateSubTreeRight(pNode.parent);
		
		//call right right case
		applyRightRightCase(temp);
	}
	
	public void applyLeftRightCase(Node target)
	{
		Node pNode = target.parent;
		Node temp = pNode;
		//rotate
		rotateSubTreeLeft(pNode.parent);
		
		//call right right case
		applyLeftLeftCase(temp);
	}
	/////////////////////////////////////////////////////////////////
	/**
	 * Our Node contains a value and a reference to the left and right subtrees (initially null)
	 * @author dermot.hegarty
	 *
	 */
	protected class Node {
		public T value; //value is the actual object that we are storing
		public Node left;
		public Node right;
		public Node parent;
		public boolean isRed;
		String colorText;
		
		public Node(T inValue) 
		{
			value = inValue;
			right = null;
			left = null;
		}

		@Override
		public String toString() 
		{
			if(isRed == true)
			{
				colorText = "Red";
			}
			else
			{
				colorText = "Black";
			}
			return "Node [value=" + value + " colour=" + colorText + "]";
		}
		
		

	}
}
