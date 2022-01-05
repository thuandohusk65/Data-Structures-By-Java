package section12;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

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
            //My way
            Node<T> actualNode = root;
            while (true) {
                //this is the case when data that need to insert bigger than actual nodes' data.
                //GO TO THE LEFT SUBTREE.
                if (data.compareTo(actualNode.getData()) < 0) {
                    //there is a valid(not Null) left child, so we go down
                    if (actualNode.getLeftChild() != null) {
                        actualNode = actualNode.getLeftChild();
                        // the left child is a Null, so we create a left child.
                    } else {
                        actualNode.setLeftChild(new Node<>(data, actualNode));
                        break;
                    }
                    //this is the case when data that need to insert smaller than actual nodes' data.
                    //GO TO THE RIGHT SUBTREE
                } else {
                    //there is a valid(not Null) right child, so we go down
                    if (actualNode.getRightChild() != null) {
                        actualNode = actualNode.getRightChild();
                        // the right child is a Null, so we create a right child.
                    } else {
                        actualNode.setRightChild(new Node<>(data, actualNode));
                        break;
                    }
                }
            }
            //The instructor's way.
            //insert(data,root);
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
                node.setLeftChild(new Node<>(data, node));
            }
            //this is the case when data that need to insert bigger than actual nodes' data.
            //GO TO THE RIGHT SUBTREE
        } else {
            //there is a valid(not Null) right child, so we go down
            if (node.getRightChild() != null) {
                insert(data, node.getRightChild());
                // the right child is a Null, so we create a right child.
            } else {
                node.setRightChild(new Node<>(data, node));
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

    private Node<T> getPredecessor(Node<T> node) {
        if (node.getRightChild() == null) {
            return node;
        } else {
            return getPredecessor(node.getRightChild());
        }
    }


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
        if (node.getLeftChild() != null) {
            inOderTraversal(node.getLeftChild());
        }
        System.out.print(node + "   ");
        if (node.getRightChild() != null) {
            inOderTraversal(node.getRightChild());
        }
    }

    @Override
    public T getMin() {
        if (root == null) {
            return null;
        } else {
            //the min item is the leftmost item in the tree.
            return getMin(root);
        }
    }

    public T getMin(Node<T> node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        } else {
            return node.getData();
        }
    }

    @Override
    public T getMax() {
        if (root == null) {
            return null;
        } else {
            //the max item is the rightmost item in the tree.
            return getMax(root);
        }
    }

    public T getMax(Node<T> node) {
        if (node.getRightChild() != null) {
            return getMax(node.getRightChild());
        } else {
            return node.getData();
        }
    }


    //compare not only values but also structures.
}
