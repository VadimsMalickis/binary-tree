package rvt;

/**
 * Represents a node in an AVL tree.
 */
public class AVLNode {
    int key;
    int height;
    AVLNode left;
    AVLNode right;

    public AVLNode(int key) {
        this.key = key;
        this.height = 1;
        this.left = null;
        this.right = null;
    }

    public int getKey() {
        return key;
    }

    public int getHeight() {
        return height;
    }

    public AVLNode getLeft() {
        return left;
    }

    public AVLNode getRight() {
        return right;
    }
}
