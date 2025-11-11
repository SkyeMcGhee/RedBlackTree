package BinarySearchTree;

public class BinaryTreeTester1 {

	public static void main(String[] args) {
		RedBlackTree<Integer> myTree = new RedBlackTree<Integer>();
		
		/*myTree.insert(40);//Note the use of primitive ints - they are auto-boxed into Integer objects.
		myTree.insert(32);
		myTree.insert(37);
		myTree.insert(34);
		myTree.insert(26);
		myTree.insert(29);
		myTree.insert(18);
		myTree.insert(20);
		myTree.insert(10);
		myTree.insert(49);
		myTree.insert(60);
		myTree.insert(70);
		myTree.insert(80);
		myTree.insert(75);
		myTree.insert(55);*/
		
		/*RedBlackTree<String> myTreeString = new RedBlackTree<String>();
		myTreeString.insert("adc");
		myTreeString.insert("qwe");
		myTreeString.insert("wer");
		myTreeString.insert("ert");
		myTreeString.insert("rty");
		myTreeString.insert("tyu");
		myTreeString.insert("yui");
		myTreeString.insert("uio");
		myTreeString.insert("iop");
		
		
		RedBlackTree<Person> myPersonTree = new RedBlackTree<Person>();
		myPersonTree.insert(new Person("Amelia", "Quinn", 20));
		myPersonTree.insert(new Person("Olivia", "McLoone", 12));
		myPersonTree.insert(new Person("Emily", "Thompson", 26));
		myPersonTree.insert(new Person("Isla", "Wright", 30));
		myPersonTree.insert(new Person("Ava", "Jackson", 60));
		myPersonTree.insert(new Person("Jack", "Gallagehr", 55));
		myPersonTree.insert(new Person("Oliver", "Browne", 42));
		myPersonTree.insert(new Person("Charlie", "Hardinge", 8));
		myPersonTree.insert(new Person("Jacob", "Twist", 19));
		
		
      
     /* System.out.println("In-order Traversal:");
		myTree.inOrderTraversal();
		System.out.println();
		
      System.out.println("Pre-order Traversal:");
		myTree.preOrderTraversal();
		System.out.println();
		
      System.out.println("Post-order Traversal:");
		myTree.postOrderTraversal();
		System.out.println();
		
		System.out.println("Tree contains " + myTree.countNodes() + " nodes");
		System.out.println();*/
		
		//practical 1 tester results
		
		/*System.out.println("Find Minimum: "+myTree.findMinimum());
				
		System.out.println(myTree.find(80));
		
	    System.out.println("In-order person Traversal:");
		 myPersonTree.inOrderTraversal();
		 System.out.println();
		 
		 //practical 2 tester results
		 System.out.println("pre-order traversal before rotation:");
		 myTree.preOrderTraversal();
		 
		 myTree.rotateTreeLeft();
		 System.out.println("pre-order traversal after rotation:");
		 myTree.preOrderTraversal();*/
		 
		 //practical 3 tester
		 //RedBlackTree<Integer> myColorTree = new RedBlackTree<Integer>();
		 
		 //myColorTree.insert(10);
		 //myColorTree.insert(5);
		 //myColorTree.insert(15);
		 //myColorTree.insert(20);
		 
		 //System.out.println("Color results:");
		 //myColorTree.preOrderTraversal();
		 
		//practical 4 tester
		 
		 RedBlackTree<Integer> myCaseTree = new RedBlackTree<Integer>();
		 
		 myCaseTree.insert(8);
		 myCaseTree.insert(18);
		 myCaseTree.insert(5);
		 myCaseTree.insert(15);
		 myCaseTree.insert(17);
		 myCaseTree.insert(25);
		 myCaseTree.insert(40);
		 myCaseTree.insert(80);
		 
		
		 System.out.println("Right Right case results:");
		 myCaseTree.preOrderTraversal();
		 
		 //general testers
		myCaseTree.inOrderTraversal();
		myCaseTree.preOrderTraversal();
		myCaseTree.postOrderTraversal();
		
		myCaseTree.find(8);
		myCaseTree.findMinimum();
		myCaseTree.findMax();
		 
	}

}
