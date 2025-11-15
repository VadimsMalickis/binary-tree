package rvt;

public class Tree {

    private Node root;

    public Tree() {
        this.root = null;
    }
    public Tree(Node node) {
        this.root = node;
    }
    public Tree(int value) {
        this.root = new Node(value);
    }


    public Node insert(int key) {
        Node node = new Node(key);
        if (root == null) {
            root = node;
            return root;
        }

        if (key < node.key) {
            node.left = insert(key);
        } else if (key > node.key) {
            node.right = insert(key);
        } else {
            return node; // duplicate keys not allowed
        }

        updateHeight(node);

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key) {
            return rotateRight(node);
        }

        if (balance < -1 && key > node.right.key) {
            return rotateLeft(node);
        }

        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private void updateHeight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    public void delete(int key) {
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node node, int key) {
        if (node == null) {
            return node;
        }

        if (key < node.key) {
            node.left = deleteNode(node.left, key);
        } else if (key > node.key) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;
                return temp; // may be null (no child) or the single child
            } else {
                Node temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = deleteNode(node.right, temp.key);
            }
        }

        updateHeight(node);

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public boolean search(int key) {
        return searchNode(root, key);
    }

    private boolean searchNode(Node node, int key) {
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

    public void inorderTraversal() {
        inorderTraversalRec(root);
        System.out.println();
    }

    private void inorderTraversalRec(Node node) {
        if (node != null) {
            inorderTraversalRec(node.left);
            System.out.print(node.key + " ");
            inorderTraversalRec(node.right);
        }
    }

    public void preorderTraversal() {
        preorderTraversalRec(root);
        System.out.println();
    }

    private void preorderTraversalRec(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preorderTraversalRec(node.left);
            preorderTraversalRec(node.right);
        }
    }

    public void postorderTraversal() {
        postorderTraversalRec(root);
        System.out.println();
    }

    private void postorderTraversalRec(Node node) {
        if (node != null) {
            postorderTraversalRec(node.left);
            postorderTraversalRec(node.right);
            System.out.print(node.key + " ");
        }
    }

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getHeight() {
        return height(root);
    }
}
