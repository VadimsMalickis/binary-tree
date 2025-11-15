package rvt;

public class App {
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println("Inorder traversal of the constructed AVL tree is:");
        tree.inorderTraversal();

        System.out.println("Preorder traversal of the constructed AVL tree is:");
        tree.preorderTraversal();

        System.out.println("Postorder traversal of the constructed AVL tree is:");
        tree.postorderTraversal();

        tree.delete(40);

        System.out.println("Inorder traversal after deletion of 40:");
        tree.inorderTraversal();
    }
}
