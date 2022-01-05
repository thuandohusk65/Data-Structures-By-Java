package section13;

public class Node {

    private Integer data;
    private Node leftNode;
    private Node rightNode;
    //use when implementing the removing method.
    private Node parentNode;

    public Node(Integer data, Node parentNode) {
        this.data = data;
        this.parentNode = parentNode;
    }


    public Integer getData() {
        return data;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public String toString() {
        return "" + data;
    }
}
