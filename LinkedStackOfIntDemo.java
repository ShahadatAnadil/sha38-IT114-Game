public class LinkedStackOfIntDemo {
    public static void main(String[] args) {

    LinkedStackofInt stack = new LinkedStackofInt(); 
    stack.push( 17 );
    stack.push( 5 );
    stack.push(123);

    System.out.println("Shahadat Anadil, IT-114-004 Adv Program for Infor Tech, Unit 13 In-Class Exercise, sha38@njit.edu");

    System.out.println("isEmpty() returns " + stack.isEmpty());
    System.out.println("Peek at top: " + stack.peek());
    System.out.println(stack.pop()+ " is removed from the stack");
    System.out.println("Peek at top: " + stack.peek());
    System.out.println(stack.pop()+ " is removed from the stack");
    System.out.println(stack.pop()+ " is removed from the stack");
    System.out.println("isEmpty() returns " + stack.isEmpty());

    //what if we call the stack.pop again
   
   // System.out.println(stack.pop()+ " is removed from the stack");

    }
}