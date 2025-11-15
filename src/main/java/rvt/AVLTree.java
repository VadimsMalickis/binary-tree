package rvt;

/**
 * AVL Tree implementation - a self-balancing binary search tree.
 * Maintains O(log n) height for all operations.
 */
public class AVLTree {
    private AVLNode root;

    public AVLTree() {
        this.root = null;
    }

    /**
     * Get the height of a node.
     */
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    /**
     * Get the balance factor of a node.
     */
    private int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    /**
     * Update the height of a node.
     */
    private void updateHeight(AVLNode node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    /**
     * Right rotate subtree rooted with y.
     */
    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    /**
     * Left rotate subtree rooted with x.
     */
    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        updateHeight(x);
        updateHeight(y);

        return y;
    }

    /**
     * Insert a key into the AVL tree.
     */
    public void insert(int key) {
        root = insertNode(root, key);
    }

    private AVLNode insertNode(AVLNode node, int key) {
        // Perform normal BST insertion
        if (node == null) {
            return new AVLNode(key);
        }

        if (key < node.key) {
            node.left = insertNode(node.left, key);
        } else if (key > node.key) {
            node.right = insertNode(node.right, key);
        } else {
            // Duplicate keys not allowed
            return node;
        }

        // Update height of this ancestor node
        updateHeight(node);

        // Get the balance factor
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && key < node.left.key) {
            return rotateRight(node);
        }

        // Right Right Case
        if (balance < -1 && key > node.right.key) {
            return rotateLeft(node);
        }

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    /**
     * Delete a key from the AVL tree.
     */
    public void delete(int key) {
        root = deleteNode(root, key);
    }

    private AVLNode deleteNode(AVLNode node, int key) {
        // Perform standard BST delete
        if (node == null) {
            return node;
        }

        if (key < node.key) {
            node.left = deleteNode(node.left, key);
        } else if (key > node.key) {
            node.right = deleteNode(node.right, key);
        } else {
            // Node with only one child or no child
            if (node.left == null || node.right == null) {
                AVLNode temp = node.left != null ? node.left : node.right;

                // No child case
                if (temp == null) {
                    return null;
                } else {
                    // One child case
                    return temp;
                }
            } else {
                // Node with two children: Get the inorder successor
                AVLNode temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = deleteNode(node.right, temp.key);
            }
        }

        // Update height of the current node
        updateHeight(node);

        // Get the balance factor
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }

        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right Right Case
        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }

        // Right Left Case
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    /**
     * Find the node with minimum value.
     */
    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Search for a key in the AVL tree.
     */
    public boolean search(int key) {
        return searchNode(root, key);
    }

    private boolean searchNode(AVLNode node, int key) {
        if (node == null) {
            return false;
        }

        if (key == node.key) {
            return true;
        }

        if (key < node.key) {
            return searchNode(node.left, key);
        } else {
            return searchNode(node.right, key);
        }
    }

    /**
     * In-order traversal of the tree.
     */
    public void inorderTraversal() {
        inorderTraversalRec(root);
        System.out.println();
    }

    private void inorderTraversalRec(AVLNode node) {
        if (node != null) {
            inorderTraversalRec(node.left);
            System.out.print(node.key + " ");
            inorderTraversalRec(node.right);
        }
    }

    /**
     * Pre-order traversal of the tree.
     */
    public void preorderTraversal() {
        preorderTraversalRec(root);
        System.out.println();
    }

    private void preorderTraversalRec(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preorderTraversalRec(node.left);
            preorderTraversalRec(node.right);
        }
    }

    /**
     * Post-order traversal of the tree.
     */
    public void postorderTraversal() {
        postorderTraversalRec(root);
        System.out.println();
    }

    private void postorderTraversalRec(AVLNode node) {
        if (node != null) {
            postorderTraversalRec(node.left);
            postorderTraversalRec(node.right);
            System.out.print(node.key + " ");
        }
    }

    /**
     * Get the root node.
     */
    public AVLNode getRoot() {
        return root;
    }

    /**
     * Check if the tree is empty.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Get the height of the tree.
     */
    public int getHeight() {
        return height(root);
    }
}
