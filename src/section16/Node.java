package section16;

public class Node<T> {
    private T data;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private Node<T> parentNode;

    public Node(T data, Node<T> parentNode) {
        this.data = data;
        this.parentNode = parentNode;
    }

    public T getData() {
        return data;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public Node<T> getParentNode() {
        return parentNode;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public void setParentNode(Node<T> parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public String toString() {
        return "" + data;
    }
}
