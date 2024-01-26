public class MyStack<T> {
    private final int maxSize;
    private int top; // Index of the top element in the stack
    private final T[] stackArray; //Array to store the elements of the stack

    // Constructor to initialize the stack with a given maximum size
    public MyStack(int maxSize) {
        this.top = -1;  // Initialize top as -1 to represent an empty stack
        this.maxSize = maxSize;
        stackArray = (T[]) new Object[maxSize];
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return (top == -1); // The stack is empty if the top is -1
    }

    // Check if the stack is full
    public boolean isFull() {
        return (top == (maxSize - 1));
    }

    // Push an element onto the stack
    public void push(T x) {
        if (isFull()) {
            System.out.println("Stack is Full"); // If the stack is full, print an error message
        } else {
            stackArray[++top] = x; // Increment 'top' and add the element to the stack
        }
    }

    // Pop and return the top element from the stack
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is Empty"); // If the stack is empty, print an error message
            return null;
        } else {
            return stackArray[top--]; // Return the top element and decrement 'top'
        }
    }

    // Peek and return the top element without removing it from the stack
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is Empty"); // If the stack is empty, print an error message
            return null;
        } else {
            return stackArray[top]; // Return the top element
        }
    }

}
