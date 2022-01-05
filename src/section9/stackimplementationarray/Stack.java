package section9.stackimplementationarray;

public class Stack<T> {
    private T[] stack;
    private int count;

    public Stack() {
        stack = (T[]) new Object[1];
    }

    //we just have to add the new item to the end of the array O(1)
    public void push(T newData) {
        //ARRAYS ARE NOT DYNAMIC DATA STRUCTURE!!!
        //we have to resize the underlying array if necessary]
        //If there are too many items:we double the size of the array
        //If there are too few items: then we decrease (shrink) the array
        if (count == stack.length) {
            resize(count * 2);
        }
        //we just have to insert into the array
        stack[count++] = newData;
    }

    //returns(remove) the last item that we have inserted O[1]
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T lastItem = stack[--count];
        //obsolete references - avoid memory leaks
        stack[count] = null;

        //maybe we have to resize the array and decrease the size
        //the stack is 25% full
        if(count > 0 && count == stack.length/4)
        resize(stack.length/2);

        return lastItem;

    }

    //O(1)
    public boolean isEmpty() {
        return count == 0;
    }

    //O(1)
    public int size() {
        return count;
    }

    //this is the bottleneck of the application O(N)
    private void resize(int capacity) {
        System.out.println("need to resize the array ...");
        T[] newStack = (T[]) new Object[capacity];
        //we copy one by one from stack to newStack
        for (int i = 0; i < count; i++) {
            newStack[i] = stack[i];
        }
        //update the references of stack
        stack = newStack;

    }
}
