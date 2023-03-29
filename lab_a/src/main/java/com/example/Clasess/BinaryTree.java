package com.example.Clasess;


class BinaryTreeNode {
    int value;
    BinaryTreeNode left;
    BinaryTreeNode right;

    public BinaryTreeNode(int value) {
        this.value = value;
        left = null;
        right = null;
    }
}

public class BinaryTree {
    BinaryTreeNode root;

    public BinaryTree() {
        root = null;
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    private BinaryTreeNode insert(BinaryTreeNode node, int value) {
        if (node == null) {
            node = new BinaryTreeNode(value);
        } else if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        }
        return node;
    }
}
