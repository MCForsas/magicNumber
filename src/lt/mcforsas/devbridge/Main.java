/**
 * Checks if number is magic.
 * A magic number is an integer in which permutations of the digits are successive multiples of the number (number of
 * digits and order does not change, but can start from different position).
 *
 * The most widely known is 142857:
 * 142857 × 1 = 142857
 * 142857 × 2 = 285714
 * 142857 × 3 = 428571
 * 142857 × 4 = 571428
 * 142857 × 5 = 714285
 * 142857 × 6 = 857142
 *
 * Input Data:
 * > Input a number: 142857
 * Expected Output
 * > It's magic!
 * @author Karolis Poviliunas
 * @version 1.0
 */
package lt.mcforsas.devbridge;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();

        if(isMagicNumber(number)){
            System.out.println("It's magic!");
        }else{
            System.out.println("How unfortunate. It's not magic :C");
        }
    }

    /**
     * Checks if number is magic (an integer in which permutations of the digits are successive multiples of the number)
     * @param number number to check
     * @return weather number is magic
     */
    private static boolean isMagicNumber(String number) {
        //Parse number into long from string
        long numberLong;

        try {
            numberLong = Long.parseLong(number);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }

        //Check by how big of a number it can be multiplied and still have same number of digits
        int maxMultiplicator = maxMultiplicator(numberLong);

        //Loop trough multipliers and check if any of them result in number whose digits are not the same as original
        // number's
        for(int i = 2; i < maxMultiplicator; i++){
            boolean isMagic = isMagicNumbersMultiple(number,String.valueOf(numberLong*i));
            if(!isMagic){
                return false;
            }
        }

        return true;
    }

    /**
     * Checks what max number other number can be multiplied by and still have a same number of digits
     * @param number
     * @return
     */
    private static int maxMultiplicator(long number){
        int maxMultiplicator = 1;

        //Loop from 2 to 9, because multiplying by a smaller number will always produce smaller or equal number, and
        // greater than 9 will always produce bigger number
        for(int i = 2; i < 10; i++){
            String multiplied = String.valueOf(number * (long) i);

            if(multiplied.length() == String.valueOf(number).length()){
                maxMultiplicator = i;
            }else{
                break;
            }
        }

        return maxMultiplicator;
    }

    /**
     * Check if <b>number</b> has the same digits as <b>multiple</b> (order does not change, but can start from
     * different position)
     * @param number number to check against
     * @param multiple number to check for
     * @return if number is magic numbers multiple
     */
    public static boolean isMagicNumbersMultiple(String number, String multiple){
        int numberLength = number.length();

        //Loop the length of number and split it in half in an increasing position and then join the other half to the
        // end of the first.
        for(int i = 0; i < numberLength; i++){
            String searchString;
            String firstHalf = multiple.substring(i+1);
            String secondHalf = multiple.substring(0,i+1);

            searchString = firstHalf.concat(secondHalf);

            if(searchString.equals(number)){
                return true;
            }
        }
        return false;
    }
}
