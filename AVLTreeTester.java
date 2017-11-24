import java.util.ArrayList;
/**
 * @author RyderDale
 *
 */
public class AVLTreeTester {
   private static ArrayList inorders = new ArrayList();
   private static ArrayList preorders = new ArrayList();
   private static ArrayList postorders = new ArrayList();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
        
		// Trial 1
		tree.insert( 1, "Hail" );
        tree.insert( 2, "to" );
        tree.insert( 3, "Oklahoma" );
        tree.insert( 4, "Christian" );
        tree.insert( 5, "Hail" );
        tree.insert( 6, "thy" );
        tree.insert( 7, "purpose" );
        tree.insert( 8, "full" );
        tree.insert( 9, "and" );
        tree.insert( 10, "free" );
        tree.insert( 11, "life" );
        tree.insert( 12, "and" );
        tree.insert( 13, "truth" );
        tree.insert( 14, "for" );
        tree.insert( 15, "AlmaMater" );
        
        // Delete nodes
        tree.delete( 15 );                 // no children
        tree.delete( 4 );                  // two children
        
        System.out.println("Trial 1: \n");
        // inorder will print them out in order of key values
        inorders = tree.inorder();
        System.out.print("\n");
        
        // preorder will print the left nodes then the right
        preorders = tree.preorder();
        System.out.print("\n");
        
        // postorder prints the right then the left
        postorders = tree.postorder();
        System.out.print("\n\n");
        
       /* System.out.println( "Height Check 1: " +  tree.getHeight( 1 ) );
        System.out.println( "Height Check 2: " +  tree.getHeight( 2 ) );
        System.out.println( "Height Check 3: " +  tree.getHeight( 3 ) );
        System.out.println( "Height Check 4: " +  tree.getHeight( 4 ) );
        System.out.println( "Height Check 5: " +  tree.getHeight( 5 ) );
        System.out.println( "Height Check 6: " +  tree.getHeight( 6 ) );
        System.out.println( "Height Check 7: " +  tree.getHeight( 7 ) );
        System.out.println( "Height Check 8: " +  tree.getHeight( 8 ) );
        System.out.println( "Height Check 9: " +  tree.getHeight( 9 ) );
        System.out.println( "Height Check 10: " +  tree.getHeight( 10 ) );
        System.out.println( "Height Check 11: " +  tree.getHeight( 11 ) );
        System.out.println( "Height Check 12: " +  tree.getHeight( 12 ) );
        System.out.println( "Height Check 13: " +  tree.getHeight( 13 ) );
        System.out.println( "Height Check 14: " +  tree.getHeight( 14 ) );
        System.out.println( "Height Check 15: " +  tree.getHeight( 15 ) ); */
        
        System.out.println( "\nNodes: " + tree.numberOfNodes() );
        
        // Clear lists and destroy tree between trials
        inorders.clear();
        preorders.clear();
        postorders.clear();
        tree.destroy();
         
        // Trial 2
        tree.insert( 8, "full" );
        tree.insert( 9, "and" );
        tree.insert( 4, "Christian" );
        tree.insert( 10, "free" );
        tree.insert( 11, "life" );
        tree.insert( 5, "Hail" );
        tree.insert( 2, "to" );
        tree.insert( 15, "AlmaMater" );
        tree.insert( 14, "for" );
        tree.insert( 6, "thy" );
        tree.insert( 13, "truth" );
        tree.insert( 12, "and" );
        tree.insert( 7, "purpose" );
        tree.insert( 3, "Oklahoma" );
        tree.insert( 1, "Hail" );
        
        // Delete
        tree.delete( 13 );    //one child
        
        System.out.println("Trial 2: \n");
        
        // inorder will print them out in order of key values
        inorders = tree.inorder();
        System.out.print("\n");
        
        // preorder will print the left nodes then the right
        preorders = tree.preorder();
        System.out.print("\n");
        
        // postorder prints the right then the left
        postorders = tree.postorder();
        System.out.print("\n");
        
       /* System.out.println( "Height Check 1: " +  tree.getHeight( 1 ) );
        System.out.println( "Height Check 2: " +  tree.getHeight( 2 ) );
        System.out.println( "Height Check 3: " +  tree.getHeight( 3 ) );
        System.out.println( "Height Check 4: " +  tree.getHeight( 4 ) );
        System.out.println( "Height Check 5: " +  tree.getHeight( 5 ) );
        System.out.println( "Height Check 6: " +  tree.getHeight( 6 ) );
        System.out.println( "Height Check 7: " +  tree.getHeight( 7 ) );
        System.out.println( "Height Check 8: " +  tree.getHeight( 8 ) );
        System.out.println( "Height Check 9: " +  tree.getHeight( 9 ) );
        System.out.println( "Height Check 10: " +  tree.getHeight( 10 ) );
        System.out.println( "Height Check 11: " +  tree.getHeight( 11 ) );
        System.out.println( "Height Check 12: " +  tree.getHeight( 12 ) );
        System.out.println( "Height Check 13: " +  tree.getHeight( 13 ) );
        System.out.println( "Height Check 14: " +  tree.getHeight( 14 ) );
        System.out.println( "Height Check 15: " +  tree.getHeight( 15 ) ); */
        
        System.out.println( "\nNodes: " + tree.numberOfNodes() );
	}

}
