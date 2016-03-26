package stacks;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class converts an infix expression to 
 * a postfix expression using the LIFO mechanism
 * of stack.
 * The stack is a char array for which basic stack
 * operations are defined.. 
 * 
 * @author priya matta
 *
 */
public class InfixToPostfix {
   
   private MyStack opStack;
   private String input;
   private String output = "";
   
   
   public InfixToPostfix(final String in) {
      input = in;
      int stackSize = input.length();
      opStack = new MyStack(stackSize);
   }
   
   /**
    * This method compares the precedence of the operators
    * and accordingly does a stack push/pop operation. 
    * @param opThis the incoming operator.
    * @param prec1 the precedence of the incoming operator.
    */
   public void onRecievingOper(final char opThis, final int prec1) {
      while (!opStack.isEmpty()) {
         char opTop = opStack.pop();
         if (opTop == '(') {
            opStack.push(opTop);
            break;
         }
         else {
            int prec2;
            if (opTop == '+' || opTop == '-')
            prec2 = 1;
            else
            prec2 = 2;
            if (prec2 < prec1) { 
               opStack.push(opTop);    // if opTop has lower precedence, leave it on stack
               break;
            }
		    else
            output = output + opTop;  // else pop opTop append it to output
         }
      }
      opStack.push(opThis);
   }
   
   /**
    * This method pops operators from the stack 
    * until a opening parentheses is encountered. 
    * @param ch
    */
   public void onRecievingRightParen(final char ch){ 
      while (!opStack.isEmpty()) {
         char chx = opStack.pop();
         if (chx == '(') 
         break; 
         else
         output = output + chx; 
      }
   }
   /**
    * 
    * @return output as String.
    */
   public String converttoPostFix() {
	      for (int j = 0; j < input.length(); j++) {
	         char ch = input.charAt(j);
	         switch (ch) {
				case '+':
				case '-':
					onRecievingOper(ch, 1);
					break;
				case '*':
				case '/':
					onRecievingOper(ch, 2);
					break;
				case '(':
					opStack.push(ch);
					break;
				case ')':
					onRecievingRightParen(ch);
					break;
				default:
					output = output + ch;
					break;
	         }
	      }
	      while (!opStack.isEmpty()) {
	         output = output + opStack.pop();
	      }	      
	      return output; 
	   }
   
   
   public static void main(String[] args) 
   throws IOException {
	   System.out.println("Enter infix expression: ");
	   // String input = "A * (B + C * D) + E";
	   Scanner scanner = new Scanner(System.in);
	   String input = scanner.nextLine();
	   System.out.println("Your infix expression is " + input);
	   scanner.close();	   
       String output;
       InfixToPostfix converter = new InfixToPostfix(input);
       output = converter.converttoPostFix(); 
       System.out.println("And the Postfix equivalent is " + output + '\n');
   }  
   
   
   class MyStack {
      private int size;
      private char[] stackArray;
      private int top;
      
      public MyStack(int maxSize) {
         size = maxSize;
         stackArray = new char[size];
         top = -1;
      }
      
      public void push(char j) {
         stackArray[++top] = j;
      }
      
      public char pop() {
         return stackArray[top--];
      }
      
      public char peek() {
         return stackArray[top];
      }
      
      public boolean isEmpty() {
         return (top == -1);
     }
   }
}
