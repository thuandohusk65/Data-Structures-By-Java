package section10.queue;

public class Queue<T extends Comparable<T>> {
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int count;

    public boolean isEmpty() {
        return firstNode == null;
    }

    public int size() {
        return count;
    }

    //O(1)
    public void enQueue(T data) {
        count++;
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            lastNode = newNode;
            this.firstNode = this.lastNode;
        } else {
            lastNode.setNextNode(newNode);
            lastNode = newNode;
        }
    }

    //O(1)
    //My way
    public T deQueue() {
        if (isEmpty()) {
            return null;
        }
        count--;
        T dataToDequeue = firstNode.getData();
        firstNode = firstNode.getNextNode();
        return dataToDequeue;
    }
    //Instructor's Way - has some problem.
//    public T deQueue() {
//        this.count--;
//        T dataToDequeue = this.firstNode.getData();
//        this.firstNode = this.firstNode.getNextNode();
//        if(isEmpty()){
//            this.lastNode=null;
//        }
//        return dataToDequeue;
//    }
}
