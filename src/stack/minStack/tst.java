package stack.minStack;

public class tst {
    public static void main(String[] args) {
        MinStackBrute minStack = new MinStackBrute();
        minStack.push(1);
        minStack.push(2);
        minStack.push(0);
        // return 0
        minStack.pop();
        System.out.println(minStack.top());    // return 2
        System.out.println(minStack.getMin()); // return 1

        MinStackOptimal minStack2 = new MinStackOptimal();
        minStack2.push(1);
        minStack2.push(2);
        minStack2.push(0);
        // return 0
        minStack2.pop();
        System.out.println(minStack2.top());    // return 2
        System.out.println(minStack2.getMin()); // return 1
    }
}
