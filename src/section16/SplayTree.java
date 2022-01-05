package section16;

public class SplayTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;

    @Override
    public T getRoot() {
        if (root == null) return null;
        return root.getData();
    }

    @Override
    public void insert(T data) {
        //this is when we insert the first node into the BST(parent is null)
        if (root == null) {
            root = new Node<>(data, null);
        } else {
            insert(data, root);
        }
    }


    public void insert(T data, Node<T> node) {
        //this is the case when data that need to insert smaller than actual nodes' data.
        //GO TO THE LEFT SUBTREE.
        if (node.getData().compareTo(data) > 0) {
            //there is a valid(not Null) left child, so we go down
            if (node.getLeftChild() != null) {
                insert(data, node.getLeftChild());
                // the left child is a Null, so we create a left child.
            } else {
                Node<T> newNode = new Node<>(data, node);
                node.setLeftChild(newNode);
                //we have to do splaying (have to make rotations)
                splay(newNode);
            }
            //this is the case when data that need to insert bigger than actual nodes' data.
            //GO TO THE RIGHT SUBTREE
        } else {
            //there is a valid(not Null) right child, so we go down
            if (node.getRightChild() != null) {
                insert(data, node.getRightChild());
                // the right child is a Null, so we create a right child.
            } else {
                Node<T> newNode = new Node<>(data, node);
                node.setRightChild(newNode);
                splay(newNode);
            }
        }
    }

    private void splay(Node<T> node) {
        //while the node is not the root node
        while (node.getParentNode() != null) {
            //if the grandparent is null it means that we have to make a simple rotation
            //ZIG CASE
            if (node.getParentNode().getParentNode() == null) {
                if (node.getParentNode().getLeftChild() == node) {
                    //if the node is the left child of the root then RIGHT ROTATE on the root
                    rightRotation(node.getParentNode());
                    //if the node is the right child of the root then LEFT ROTATE on the root
                } else {
                    leftRotation(node.getParentNode());
                }
                //ZIG-ZIG SITUATION
            } else if (node.getParentNode().getLeftChild() == node && node.getParentNode().getParentNode().getLeftChild() == node.getParentNode()) {
                rightRotation(node.getParentNode().getParentNode());
                rightRotation(node.getParentNode());
                //this is the symmetric of the ZIG-ZIG situation
            } else if (node.getParentNode().getRightChild() == node && node.getParentNode().getParentNode().getRightChild() == node.getParentNode()) {
                leftRotation(node.getParentNode().getParentNode());
                leftRotation(node.getParentNode());
                //ZIG_ZAG SITUATION
            } else if (node.getParentNode().getLeftChild() == node && node.getParentNode().getParentNode().getRightChild() == node.getParentNode()) {
                rightRotation(node.getParentNode());
                leftRotation(node.getParentNode());
                //this is the symmetric of the ZIG-ZIG situation
            } else if (node.getParentNode().getRightChild() == node && node.getParentNode().getParentNode().getLeftChild() == node.getParentNode()) {
                leftRotation(node.getParentNode());
                rightRotation(node.getParentNode());
            }

        }
    }

    //it can be done in 0(1)
    private void rightRotation(Node<T> node) {
        //This is the new root node after rotation
        System.out.println("Rotating to the right on node: " + node);
        Node<T> tempLeftChild = node.getLeftChild();
        Node<T> grandChild = tempLeftChild.getRightChild();

        //make the rotation - the new root node will be the tempLeftChild
        tempLeftChild.setRightChild(node);
        node.setLeftChild(grandChild);

        //we have to handle the parents
        if (grandChild != null) {
            grandChild.setParentNode(node);
        }
        //we have to handle the parents for the node
        Node<T> tempParent = node.getParentNode();
        node.setParentNode(tempLeftChild);
        tempLeftChild.setParentNode(tempParent);

        //we have to handle the parent
        if (tempLeftChild.getParentNode() != null && tempLeftChild.getParentNode().getLeftChild() == node) {
            tempLeftChild.getParentNode().setLeftChild(tempLeftChild);
        } else if (tempLeftChild.getParentNode() != null && tempLeftChild.getParentNode().getRightChild() == node) {
            tempLeftChild.getParentNode().setRightChild(tempLeftChild);
        } else if (node == root) {
            root = tempLeftChild;
        }
    }

    private void leftRotation(Node<T> node) {
        //This is the new root node after rotation
        System.out.println("Rotating to the left on node: " + node);
        Node<T> tempRightChild = node.getRightChild();
        Node<T> grandChild = tempRightChild.getLeftChild();

        //make the rotation - the new root node will be the tempLeftChild
        tempRightChild.setLeftChild(node);
        node.setRightChild(grandChild);

        //we have to handle the parents
        if (grandChild != null) {
            grandChild.setParentNode(node);
        }
        //we have to handle the parents for the node
        Node<T> tempParent = node.getParentNode();
        node.setParentNode(tempRightChild);
        tempRightChild.setParentNode(tempParent);

        //we have to handle the parent
        if (tempRightChild.getParentNode() != null && tempRightChild.getParentNode().getLeftChild() == node) {
            tempRightChild.getParentNode().setLeftChild(tempRightChild);
        } else if (tempRightChild.getParentNode() != null && tempRightChild.getParentNode().getRightChild() == node) {
            tempRightChild.getParentNode().setRightChild(tempRightChild);
        } else if (node == root) {
            root = tempRightChild;
        }

    }

    @Override
    public T find(T data) {
        if (root == null) {
            return null;
        } else {
            return find(data, root);
        }
    }

    public T find(T data, Node<T> node) {
        //this is when we look for a given item in the tree
        if (data.compareTo(node.getData()) < 0) {
            find(data, node.getLeftChild());
        } else if (data.compareTo(node.getData()) > 0) {
            find(data, node.getRightChild());
        } else {
            //We have found the item we are looking for
            splay(node);
            return data;
        }
        return null;
    }

    @Override
    public void traverse() {
        if (root != null) {
            traverse(root);
        }
    }

    private void traverse(Node<T> node) {
        if (node.getLeftChild() != null) {
            traverse(node);
        }
        System.out.println(node);
        if (node.getRightChild() != null) {
            traverse(node);
        }
    }
}
