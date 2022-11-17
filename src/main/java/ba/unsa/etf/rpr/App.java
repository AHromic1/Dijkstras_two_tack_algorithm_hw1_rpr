package ba.unsa.etf.rpr;

import java.util.Scanner;
/**
 * Entry point for ExpressionEvaluator - evaluate method
 */
public class App
{

    public static void main( String[] args ) throws RuntimeException {
        try{


            /*NAPOMENA: Kako je u zadatku specificirano samo da je izraz nevalidan kada nema razmaka, tj. nije ništa rečeno
            o tome da li je validan ukoliko ima viška razmaka, pretpostavila sam da jeste*/


            String s = "";


            for (int i = 0; i < args.length; i++){
               if(i < args.length - 1) {
                    s+= args[i] + " ";
                }else {
                    s+= args[i];
                }

            }
            ExpressionEvaluator.validate(s);
      double result = ExpressionEvaluator.evaluate(s);

        System.out.println("Result of the expression is: " + result);


        }
        catch (RuntimeException e){
            System.out.println("Invalid expression! ");
        }
    }
}
