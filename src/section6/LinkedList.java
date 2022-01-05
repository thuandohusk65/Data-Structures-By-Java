package section6;

public class LinkedList<T extends Comparable<T>> implements List<T> {
    //this is the head node or the root node
    private Node<T> root;
    private int numOfItems;

    @Override
    public void reverse() {
        if (root == null || root.getNextNode() == null) {
            return;
        }
        Node<T> nextNode = root.getNextNode();
        Node<T> laterNode = root.getNextNode().getNextNode();
        root.setNextNode(null);
        while (true) {
            nextNode.setNextNode(root);
            root = nextNode;
            if (laterNode != null) {
                nextNode = laterNode;
                laterNode = laterNode.getNextNode();
            } else {
                break;
            }
        }
    }
/*Instructor's Way
    public void reverse() {
        Node<T> currentNode = this.root;
        Node<T> previousNode = null;
        Node<T> nextNode = null;
        while (currentNode != null) {
            nextNode = currentNode.getNextNode();
            currentNode.setNextNode(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
            this.root = previousNode;
        }
    }*/


    @Override
    public Node<T> getMiddleNode() {
        if (root == null) {
            return null;
        }
        Node<T> fastPoint = this.root;
        Node<T> slowPoint = this.root;
        while (fastPoint.getNextNode() != null && fastPoint.getNextNode().getNextNode() != null) {
            slowPoint = slowPoint.getNextNode();
            fastPoint = fastPoint.getNextNode().getNextNode();
        }
        return slowPoint;
    }

    @Override
    public void insert(T data) {
        if (root == null) {
            //this is the first item in the linked list
            root = new Node<>(data);
        } else {
            //that this is not the first item in the linked list
            insertTheBeginning(data);
        }
    }

    //we just have to update the references 0(1)
    private void insertTheBeginning(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNextNode(root);
        root = newNode;
    }

    //because we have to start with the root node
    //first we have to find the last node in O(N)
    private void insertEnd(T data, Node<T> node) {
        //when we keep looking for the last node O(N)
        if (node.getNextNode() != null) {
            insertEnd(data, node.getNextNode());
        } else {
            //we have found the last node.
            node.setNextNode(new Node<>(data));
        }


    }

    @Override
    public void remove(T data) {
        if (root == null) {
            return;
        }
        //we want to remove the first node if it equals to data O(N)
        if (root.getData().compareTo(data) == 0) {
            root = root.getNextNode();
            //my way
        } else {
            Node<T> actualNode = root;
            //we have to find the node we want to remove
            while (actualNode.getNextNode() != null && actualNode.getNextNode().getData().compareTo(data) != 0) {
                actualNode = actualNode.getNextNode();
            }
            if (actualNode.getNextNode() != null) {
                Node<T> removeNode = actualNode.getNextNode();
                actualNode.setNextNode(actualNode.getNextNode().getNextNode());
                numOfItems--;
                removeNode = null;
            }
            return;
            //instructor's way
            //remove(data,root,root.getNextNode())
        }
    }

    //instructor's way
    private void remove(T data, Node<T> previousNode, Node<T> actualNode) {
        //we have to find the node that needs to be removed.
        while (actualNode != null) {
            //this is the node we want to remove
            if (actualNode.getData().compareTo(data) == 0) {
                numOfItems--;
                previousNode.setNextNode(actualNode.getNextNode());
                actualNode = null;
                return;
            }
            previousNode = actualNode;
            actualNode = actualNode.getNextNode();
        }
    }

    @Override
    public void traverse() {
        if (root == null) {
            return;
        }
        Node<T> actualNode = root;
        while (actualNode != null) {
            System.out.println(actualNode);
            actualNode = actualNode.getNextNode();

        }
    }

    @Override
    public int size() {
        return numOfItems;
    }
}
