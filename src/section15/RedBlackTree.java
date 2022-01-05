package section15;

public class RedBlackTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;

    @Override
    public void insert(T data) {
        //this is when we insert the first node into the BST(parent is null)
        if (root == null) {
            root = new Node<>(data, null);
            settleViolations(root);
        } else {
            //My way
//            Node<T> actualNode = root;
//            while (true) {
//                //this is the case when data that need to insert bigger than actual nodes' data.
//                //GO TO THE LEFT SUBTREE.
//                if (data.compareTo(actualNode.getData()) < 0) {
//                    //there is a valid(not Null) left child, so we go down
//                    if (actualNode.getLeftChild() != null) {
//                        actualNode = actualNode.getLeftChild();
//                        // the left child is a Null, so we create a left child.
//                    } else {
//                        actualNode.setLeftChild(new Node<>(data, actualNode));
//                        break;
//                    }
//                    //this is the case when data that need to insert smaller than actual nodes' data.
//                    //GO TO THE RIGHT SUBTREE
//                } else {
//                    //there is a valid(not Null) right child, so we go down
//                    if (actualNode.getRightChild() != null) {
//                        actualNode = actualNode.getRightChild();
//                        // the right child is a Null, so we create a right child.
//                    } else {
//                        actualNode.setRightChild(new Node<>(data, actualNode));
//                        break;
//                    }
//                }
//            }
//            //The instructor's way.
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
                //WE HAVE TO CHECK whether the red back properties are violated
                Node<T> newNode = new Node<>(data, node);
                node.setLeftChild(newNode);
                settleViolations(newNode);
            }
            //this is the case when data that need to insert bigger than actual nodes' data.
            //GO TO THE RIGHT SUBTREE
        } else {
            //there is a valid(not Null) right child, so we go down
            if (node.getRightChild() != null) {
                insert(data, node.getRightChild());
                // the right child is a Null, so we create a right child.
            } else {
                //WE HAVE TO CHECK whether the red back properties are violated
                node.setRightChild(new Node<>(data, node));
                settleViolations(node);
            }
        }
    }

    @Override
    public void remove(T data) {
        if (root != null) {
            remove(data, root);
        }
    }


    private void remove(T data, Node<T> node) {
        if (node == null) {
            return;
        }
        //first we have to search for the item we want to remove
        if (data.compareTo(node.getData()) < 0) {
            remove(data, node.getLeftChild());
        } else if (data.compareTo(node.getData()) > 0) {
            remove(data, node.getRightChild());
        } else {
            //WE HAVE FOUND THE ITEM
            Node<T> parent = node.getParentNode();
            //CASE 1: if the node is the leaf node(without left and right child)
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                //whether the node is a left child or right child
                System.out.println("Removing a leaf node ...");

                //the node is the left child
                if (parent != null && parent.getLeftChild() == node) {
                    parent.setLeftChild(null);
                    //the node is the right child
                } else if (parent != null && parent.getRightChild() == node) {
                    parent.setRightChild(null);
                }
                if (parent == null) {
                    root = null;
                }

                node = null;
            }
            //CASE 2: When we remove items with a single child
            //single right child
            else if (node.getRightChild() != null && node.getLeftChild() == null) {
                System.out.println("Removing a node with single right child ...");
                //the node is a left child
                if (parent != null && parent.getLeftChild() == node) {
                    parent.setLeftChild(node.getRightChild());
                    //the node is a right child
                } else if (parent != null && parent.getRightChild() == node) {
                    parent.setRightChild(node.getRightChild());
                }
                //when we deal with root node
                if (parent == null) {
                    root = node.getRightChild();
                }
                node.getRightChild().setParentNode(parent);
                node = null;
            }//single left child
            else if (node.getRightChild() == null && node.getLeftChild() != null) {
                System.out.println("Removing a node with single left child ...");

                //the node is a left child
                if (parent != null && parent.getLeftChild() == node) {
                    parent.setLeftChild(node.getLeftChild());
                    //the node is a right child
                } else if (parent != null && parent.getRightChild() == node) {
                    parent.setRightChild(node.getLeftChild());
                }
                //when we deal with root node
                if (parent == null) {
                    root = node.getLeftChild();
                }
                node.getLeftChild().setParentNode(parent);
                node = null;
                //remove the node that have 2 child
            } else {
                System.out.println("Removing a node with 2 children ...");

                //find the predecessor (max item in the left subtree)
                Node<T> predecessor = getPredecessor(node.getLeftChild());

                //swap just the values!!!
                T temp = predecessor.getData();
                predecessor.setData(node.getData());
                node.setData(temp);
                //we have to call the remove method recursively on the predecessor
                //or do this, because predecessor is the right child.
                predecessor.getParentNode().setRightChild(null);
                predecessor = null;
            }
        }
    }

    private void settleViolations(Node<T> node) {
        Node<T> parentNode = null;
        Node<T> grandParentNode = null;

        //we have to check the violations up to the root node
        while (node != root && isRed(node) && isRed(node.getParentNode())) {
            parentNode = node.getParentNode();
            grandParentNode = node.getParentNode().getParentNode();

            //parent is a left child of it's parent (the node's grandparent)
            if (parentNode == grandParentNode.getLeftChild()) {
                Node<T> uncle = grandParentNode.getRightChild();
                //case 1.) and case 4.) RECOLORING
                if (uncle != null && isRed(uncle)) {
                    grandParentNode.setColor(NodeColor.RED);
                    parentNode.setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    node = grandParentNode;
                } else {
                    //case 2.)
                    if (node == parentNode.getRightChild()) {
                        leftRotation(parentNode);
                        //update the reference we keep going up to the root node
                        node = parentNode;
                        parentNode = grandParentNode;
                    }
                    //case 3.) rotation on the grandparent + parent and grandparent switch color
                    rightRotation(grandParentNode);
                    System.out.println("Recoloring " + parentNode + " + " + grandParentNode);
                    //swap the color of the parent and the grandparent
                    NodeColor tempColor = parentNode.getColor();
                    parentNode.setColor(grandParentNode.getColor());
                    grandParentNode.setColor(tempColor);
                    node = parentNode;
                }
            } else {
                Node<T> uncle = grandParentNode.getLeftChild();
                //case 1.) and case 4.) symmetric partner
                if (uncle != null && isRed(uncle)) {
                    grandParentNode.setColor(NodeColor.RED);
                    parentNode.setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    node = grandParentNode;
                } else {
                    //case 2.) symmetric partner
                    if (node == parentNode.getLeftChild()) {
                        rightRotation(parentNode);
                        node = parentNode;
                        parentNode = grandParentNode;
                    }
                    //case 3.) symmetric partner
                    leftRotation(grandParentNode);
                    System.out.println("Recoloring " + parentNode + " + " + grandParentNode);
                    NodeColor tempColor = parentNode.getColor();
                    parentNode.setColor(grandParentNode.getColor());
                    grandParentNode.setColor(tempColor);
                    node = parentNode;
                }
            }
        }
        if (isRed(root)) {
            System.out.println("Recoloring the root to black ...");
            root.setColor(NodeColor.BLACK);
        }

    }


    private boolean isRed(Node<T> node) {
        if (node == null) {
            return false;
        }
        return node.getColor() == NodeColor.RED;
    }

    private Node<T> getPredecessor(Node<T> node) {
        if (node.getRightChild() == null) {
            return node;
        } else {
            return getPredecessor(node.getRightChild());
        }
    }

    @Override
    public void traverse() {
        if (root == null) {
            return;
        } else {
            inOderTraversal(root);
        }
    }

    //O(N)
    private void inOderTraversal(Node<T> node) {
        if (node.getLeftChild() != null) {
            inOderTraversal(node.getLeftChild());
        }
        System.out.print(node + "   ");
        if (node.getRightChild() != null) {
            inOderTraversal(node.getRightChild());
        }
    }

    //it can be done in 0(1)
    private void rightRotation(Node<T> node) {
        //This is the new root node after rotation
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

}
