package ba.unsa.etf.rpr;


import java.util.Stack;
import java.lang.Double;



/**
 * @author Amina Hromić
 * @version 1.0
 * class for performing basic arithmetic operations of a given expression
 */
public class ExpressionEvaluator {

    private static Stack <Double> val = new Stack<> ();  //cannot be primitive type
    private static Stack<String> op = new Stack<> ();



    /**
     *a public method used to parse a String
     * @param  s a String to be parsed
     * @return String[] a String array consisting of individual parsed numbers
     */
     public static String[] parsedString(String s){
        return s.split(" ");
    }

    /**
     * a private method for performing given arithmetic operations
     * @param o an operand to be used in arithmetic operations of type String
     * @param  a a double value which will be used in an arithmetic operation with operand o
     * @param b  a double value which will be used in an arithmetic operation with operand o
     * @return  a double value which represents the result of an arithmetic operation
     */
    public static double value(String o, double a, double b){
        double result = 0;  //has to be intitialised
        if(o.equals("+")){  //equals!!!
            result = a + b;
        }
        else if(o.equals("-")){
            result = a - b;
        }
        else if(o.equals("*") ){
            result = a * b;
        }
        if(o.equals("/")){
            result = a / b;
        }

        if(o.equals("sqrt")) {
           result =  Math.sqrt(b);  //works only on one operand
        }

        return result;
    }

    /**
     * checks if the expression is in the right format as well as if the valid operators and operands are used
     * @param s String to be validated
     */

    public static void validate(String s) throws RuntimeException{

        //checks if the expression is in the right format (blank spaces between every part of the expression)
     /*   int expr = 0;
        int ws = 0;
        for(int i = 0; i < s.length(); i++){

            if(s.charAt(i) != ' '){
                while(s.charAt(i) >= 0 && s.charAt(i) <= 9) {//conditions based on ASCII code
                    //if we come across a number, we need to count it aw a whole, and not its separate digits
                    i = i + 1;
                }
                expr = expr + 1;
            }
            else ws = ws + 1;
        }


        if(ws < expr - 1) {
            throw new RuntimeException("Invalid expression!");
        }*/

        //checks if the expression has valid operand / operators

        String [] p = parsedString(s);
        for(String x: p){

            if(!(x.equals("0") || x.equals("1") || x.equals("2") || x.equals("3") || x.equals("4")
                    || x.equals("5") || x.equals("6") || x.equals("7") || x.equals("8") || x.equals("9")
                    || x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/") || x.equals("sqrt")
                    || x.equals("(") || x.equals(")") ))

                throw new RuntimeException("Invalid operands and/or operators!");
        }

        int leftp = 0, rightp = 0;
        for(String x: p) {

            if (x.equals("(")) leftp = leftp + 1;
            if (x.equals(")")) rightp = rightp + 1;
        }
        if (leftp != rightp)  throw new RuntimeException("Invalid parentheses!");

    }



    /**
     * a public class for performing arithmetic operations given in a String expression
     * @param s a String containing the expression to be calculated
     * @return a double value representing the result of an expression
     */
    public static double evaluate(String s) {
       //parse s
       String[] exp = parsedString(s);

       //go through exp
       for (String x : exp) {
           double dx = 0;

           if(x.equals(")")) {  // if we come across a right parentheses, it's time to calculate a value and put it in a val stack
               while (!(op.peek()).equals("(")) { //while the top is not a left parenthesis
                   String o = op.pop();
                   if(o.equals("sqrt")){
                       double r = val.pop();  //we need only one operand
                       double res = value(o, r, r);
                       val.push(res);
                   }
                   else {
                       double b = val.pop();  //b comes first due to FIFO principle of a stack
                       double a = val.pop();
                       double v = value(o, a, b);
                       val.push(v);
                   }
               }
               op.pop(); //a left parenthesis is now removed
           }
           else if (!(x.equals(")"))) {  //operands and operators are  pushed in the right stack
               if (x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/") || x.equals("(") || x.equals("sqrt"))
                   op.push(x);
               else {
                   dx = Double.parseDouble(x);
                   val.push(dx);
               }
           }

       }



       return val.pop(); //the last remaining value in val stack is the required one
   }

}
