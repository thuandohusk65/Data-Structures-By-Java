package section11.queuewithonestack;

import section9.stackimplementationlinkedlist.Stack;

public class QueueByStack<T> {
    private Stack<T> stackQueue = new Stack<T>();

    public void enQueue(T data) {
        stackQueue.push(data);
    }

    /*NOTE:we use 2 stack again instead of explicitly define the second stack we
    use the call- stack of program(stack memory or execution task)*/
    public T deQueue() {
        if (stackQueue.size() == 0) {
            return null;
        }

        /*base case for the recursive method calls the first item
        is what we are after (this is what we need for queue's dequeue operation) */
        if (stackQueue.size() == 1) {
            return stackQueue.pop();
        } else {
            //we keep popping the items until we find the last one
            T pull = stackQueue.pop();

            //we call the method recursively
            T lastItems = deQueue();

            //after we find the item that we dequeue have to reinsert the items one by one
            enQueue(pull);

        /*this is the item we are looking for(this is what have been popped off in the
        stack size()==1 section*/
            return lastItems;
        }
    }

}
