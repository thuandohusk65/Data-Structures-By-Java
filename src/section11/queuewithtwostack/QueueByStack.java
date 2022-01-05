package section11.queuewithtwostack;

import section9.stackimplementationlinkedlist.Stack;

public class QueueByStack<T> {
    private Stack<T> enQueueStack = new Stack<>();
    private Stack<T> deQueueStack = new Stack<>();
    private int count;

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void enQueue(T data) {
        count++;
        //My way
//        if (deQueueStack.isEmpty()) {
//            deQueueStack.push(data);
//        } else {

//            while (!deQueueStack.isEmpty()) {
//                enQueueStack.push(deQueueStack.pop());
//            }
//            enQueueStack.push(data);
//            while (!enQueueStack.isEmpty()) {
//                deQueueStack.push(enQueueStack.pop());
            enQueueStack.push(data);

    }


    public T deQueue() {
        count--;
        if (deQueueStack.isEmpty()) {
            while (!enQueueStack.isEmpty()) {
                deQueueStack.push(enQueueStack.pop());
            }
        }
        return deQueueStack.pop();
    }
}
