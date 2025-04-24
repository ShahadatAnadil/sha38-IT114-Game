public class ArrayStackOfIntDemo {
    
    public static void main(String[] args) {
        System.out.println("Shahadat Anadil, IT-114-004 Adv Program for Infor Tech, Unit 13 In-Class Exercise, sha38@njit.edu");
        
        ArrayStackOfInt stack = new ArrayStackOfInt();
        stack.push(17);
        stack.push(5);
        stack.push(123);

        System.out.println("Peek at top: " + stack.peek());
        System.out.println(stack.pop() + " is removed from the stack");
        System.out.println("Peek at top: " + stack.peek());
        System.out.println(stack.pop() + " is removed from the stack");

    }
}
