package section14;

public class Node<T> {
    private T data;
    private Node<T> leftNode;
    private Node<T> rightNode;
    //because we have to notify the
    private Node<T> parentNode;
    private int height;

    public Node(T data, Node<T> parentNode) {
        this.data = data;
        this.parentNode = parentNode;
    }

    public T getData() {
        return data;
    }

    public Node<T> getLeftNode() {
        return leftNode;
    }

    public Node<T> getRightNode() {
        return rightNode;
    }

    public void setLeftNode(Node<T> leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node<T> rightNode) {
        this.rightNode = rightNode;
    }

    public Node<T> getParentNode() {
        return parentNode;
    }

    public int getHeight() {
        return height;
    }

    public void setData(T data) {
        this.data = data;
    }



    public void setParentNode(Node<T> parentNode) {
        this.parentNode = parentNode;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "" + data;
    }
}
