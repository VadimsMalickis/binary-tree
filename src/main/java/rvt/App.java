package rvt;

public class App {
    public static void main(String[] args) {
        System.out.println("AVL Tree (Balanced Binary Search Tree) Demonstration");
        System.out.println("=====================================================\n");

        AVLTree tree = new AVLTree();

        // Insert elements
        System.out.println("Inserting elements: 10, 20, 30, 40, 50, 25");
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println("\nIn-order traversal (sorted order):");
        tree.inorderTraversal();

        System.out.println("Pre-order traversal:");
        tree.preorderTraversal();

        System.out.println("Post-order traversal:");
        tree.postorderTraversal();

        System.out.println("\nTree height: " + tree.getHeight());

        // Search operations
        System.out.println("\nSearching for 25: " + tree.search(25));
        System.out.println("Searching for 100: " + tree.search(100));

        // Delete operations
        System.out.println("\nDeleting element 40");
        tree.delete(40);
        System.out.println("In-order traversal after deletion:");
        tree.inorderTraversal();

        System.out.println("Tree height after deletion: " + tree.getHeight());
    }
}
