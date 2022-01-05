package section14;


public class AVLTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;

    public Node<T> getRoot() {
        return root;
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
            if (node.getLeftNode() != null) {
                insert(data, node.getLeftNode());
                // the left child is a Null, so we create a left child.
            } else {
                node.setLeftNode(new Node<>(data, node));

            }
            //this is the case when data that need to insert bigger than actual nodes' data.
            //GO TO THE RIGHT SUBTREE
        } else {
            //there is a valid(not Null) right child, so we go down
            if (node.getRightNode() != null) {
                insert(data, node.getRightNode());
                // the right child is a Null, so we create a right child.
            } else {
                node.setRightNode(new Node<>(data, node));
            }
        }
        updateHeight(node);
        //settle the violation
        settleViolations(node);
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
            remove(data, node.getLeftNode());
        } else if (data.compareTo(node.getData()) > 0) {
            remove(data, node.getRightNode());
        } else {

            //WE HAVE FOUND THE ITEM
            Node<T> parent = node.getParentNode();
            //CASE 1: if the node is the leaf node(without left and right child)
            if (node.getLeftNode() == null && node.getRightNode() == null) {
                //whether the node is a left child or right child
                System.out.println("Removing a leaf node ...");

                //the node is the left child
                if (parent != null && parent.getLeftNode() == node) {
                    parent.setLeftNode(null);
                    //the node is the right child
                } else if (parent != null && parent.getRightNode() == node) {
                    parent.setRightNode(null);
                }
                if (parent == null) {
                    root = null;
                }

                node = null;
                updateHeight(parent);
                //settle the violation
                settleViolations(parent);
            }
            //CASE 2: When we remove items with a single child
            //single right child
            else if (node.getRightNode() != null && node.getLeftNode() == null) {
                System.out.println("Removing a node with single right child ...");
                //the node is a left child
                if (parent != null && parent.getLeftNode() == node) {
                    parent.setLeftNode(node.getRightNode());
                    //the node is a right child
                } else if (parent != null && parent.getRightNode() == node) {
                    parent.setRightNode(node.getRightNode());
                }
                //when we deal with root node
                if (parent == null) {
                    root = node.getRightNode();
                }
                node.getRightNode().setParentNode(parent);
                node = null;

                updateHeight(parent);
                settleViolations(parent);

            }//single left child
            else if (node.getRightNode() == null && node.getLeftNode() != null) {
                System.out.println("Removing a node with single left child ...");

                //the node is a left child
                if (parent != null && parent.getLeftNode() == node) {
                    parent.setLeftNode(node.getLeftNode());
                    //the node is a right child
                } else if (parent != null && parent.getRightNode() == node) {
                    parent.setRightNode(node.getLeftNode());
                }
                //when we deal with root node
                if (parent == null) {
                    root = node.getLeftNode();
                }
                node.getLeftNode().setParentNode(parent);
                node = null;
                //remove the node that have 2 child

                updateHeight(parent);
                settleViolations(parent);

            } else {
                System.out.println("Removing a node with 2 children ...");

                //find the predecessor (max item in the left subtree)
                Node<T> predecessor = getPredecessor(node.getLeftNode());

                //swap just the values!!!
                T temp = predecessor.getData();
                predecessor.setData(node.getData());
                node.setData(temp);
                //we have to call the remove method recursively on the predecessor
                //or do this, because predecessor is the right child.
                predecessor.getParentNode().setRightNode(null);
                predecessor = null;
            }
        }
        //settle the violations
    }

    private Node<T> getPredecessor(Node<T> node) {
        if (node.getRightNode() == null) {
            return node;
        } else {
            return getPredecessor(node.getRightNode());
        }
    }

    //O(N)
    @Override
    public void traversal() {
        if (root == null) {
            return;
        } else {
            inOderTraversal(root);
        }
    }

    //O(N)
    private void inOderTraversal(Node<T> node) {
        if (node.getLeftNode() != null) {
            inOderTraversal(node.getLeftNode());
        }
        System.out.print(node + "   ");
        if (node.getRightNode() != null) {
            inOderTraversal(node.getRightNode());
        }
    }

    //update the height of a given node
    private void updateHeight(Node<T> node) {
        node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);
    }

    //return the height paramater for a given node
    private int height(Node<T> node) {
        if (node == null) {
            return -1;
        } else {
            return node.getHeight();
        }
    }

    //balance factor to decide the left heavy or the right heavy
    private int getBalance(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return node.getLeftNode().getHeight() - node.getRightNode().getHeight();
        }
    }

    private void settleViolations(Node<T> node) {
        while (node != null) {
            updateHeight(node);
            settleViolationsHelper(node);
            node = node.getParentNode();
        }
    }

    private void settleViolationsHelper(Node<T> node) {
        int balance = getBalance(node);
        //OK, we know the tree is LEFT HEAVY BUT it can be left-right heavy or left-left  heavy
        if (balance > 1) {
            //left right heavy situation: left rotation
            if (getBalance(node.getLeftNode()) < 0) {
                leftRotation(node.getLeftNode());
            }
            //doubly left heavy situation then just a single right rotation is needed.
            //this is the right rotation
            rightRotation(node);
        }

        //OK, we know the tree is RIGHT HEAVY BUT it can be right-right heavy or right-left  heavy
        //right left heavy situation: right rotation
        if (balance < -1) {
            if (getBalance(node.getRightNode()) > 0) {
                rightRotation(node.getLeftNode());
            }
            //doubly left heavy situation then just a single left rotation is needed.
            //this is the left rotation
            leftRotation(node);
        }
    }

    //it can be done in 0(1)
    private void rightRotation(Node<T> node) {
        //This is the new root node after rotation
        Node<T> tempLeftChild = node.getLeftNode();
        Node<T> grandChild = tempLeftChild.getRightNode();

        //make the rotation - the new root node will be the tempLeftChild
        tempLeftChild.setRightNode(node);
        node.setLeftNode(grandChild);

        //we have to handle the parents
        if (grandChild != null) {
            grandChild.setParentNode(node);
        }
        //we have to handle the parents for the node
        Node<T> tempParent = node.getParentNode();
        node.setParentNode(tempLeftChild);
        tempLeftChild.setParentNode(tempParent);

        //we have to handle the parent
        if (tempLeftChild.getParentNode() != null && tempLeftChild.getParentNode().getLeftNode() == node) {
            tempLeftChild.getParentNode().setLeftNode(tempLeftChild);
        } else if (tempLeftChild.getParentNode() != null && tempLeftChild.getParentNode().getRightNode() == node) {
            tempLeftChild.getParentNode().setRightNode(tempLeftChild);
        } else if (node == root) {
            root = tempLeftChild;
        }
        updateHeight(node);
        updateHeight(tempLeftChild);
    }

    private void leftRotation(Node<T> node) {
        //This is the new root node after rotation
        Node<T> tempRightChild = node.getRightNode();
        Node<T> grandChild = tempRightChild.getLeftNode();

        //make the rotation - the new root node will be the tempLeftChild
        tempRightChild.setLeftNode(node);
        node.setRightNode(grandChild);

        //we have to handle the parents
        if (grandChild != null) {
            grandChild.setParentNode(node);
        }
        //we have to handle the parents for the node
        Node<T> tempParent = node.getParentNode();
        node.setParentNode(tempRightChild);
        tempRightChild.setParentNode(tempParent);

        //we have to handle the parent
        if (tempRightChild.getParentNode() != null && tempRightChild.getParentNode().getLeftNode() == node) {
            tempRightChild.getParentNode().setLeftNode(tempRightChild);
        } else if (tempRightChild.getParentNode() != null && tempRightChild.getParentNode().getRightNode() == node) {
            tempRightChild.getParentNode().setRightNode(tempRightChild);
        } else if (node == root) {
            root = tempRightChild;
        }
        updateHeight(node);
        updateHeight(tempRightChild);
    }
}
