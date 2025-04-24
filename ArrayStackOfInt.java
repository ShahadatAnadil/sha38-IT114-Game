import java.util.Arrays;

public class ArrayStackOfInt {

    private int[] items = new int[10];

    private int top = 0;
    
    public void push(int number){
        if (top == items.length)
            items = Arrays.copyOf( items, 2 * items.length );
        items[top] =number;
        top++;
    }

    public int pop() {
        if (top ==0)
            throw new IllegalStateException();
        //declare topItem and initialize to top value
        int topItem = items[top-1];
        items[top-1] = 0;
        top--;
        return topItem;
    }

    public boolean isEmpty(){
        return (top == 0);
    }

    public int peek() {
        if (top ==0)
            throw new IllegalStateException("Empty stack, you cannot peek");
        return items[top -1];
    }
}
