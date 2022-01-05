package section15;

public class Node<T> {
    private T data;
    private Node<T> rightChild;
    private Node<T> leftChild;
    private Node<T> parentNode;
    private NodeColor color;

    public Node(T data, Node<T> parentNode) {
        this.data = data;
        this.parentNode = parentNode;
        this.color = NodeColor.RED;
    }

    public T getData() {
        return data;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public Node<T> getParentNode() {
        return parentNode;
    }

    public NodeColor getColor() {
        return color;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setParentNode(Node<T> parentNode) {
        this.parentNode = parentNode;
    }

    public void setColor(NodeColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return  "" + data;

    }
}
