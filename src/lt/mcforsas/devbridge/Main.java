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
 *
 */
package lt.mcforsas.devbridge;

import java.util.Scanner;

public class Main {

    //TODO: add documentation to all methods.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();

        if(isMagicNumber(number)){
            System.out.println("It's magic!");
        }else {
            System.out.println("How unfortunate. It's not magic :C. Try another one? Y/N");
        }
        //TODO: remove asking for another number
        if(scanner.nextLine().equals("y")){
            main(args);
        }
    }


    private static boolean isMagicNumber(String number) {
        //Parse number into int
        int numberInt;
        try {
            numberInt = Integer.parseInt(number);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }

        int maxMultiplicator = maxMultiplicator(numberInt);


        for(int i = 2; i < maxMultiplicator; i++){
            boolean isMagic = isMagicNumbersMultiple(number,String.valueOf(numberInt*i));
            if(!isMagic){
                return false;
            }
        }

        return true;
    }

    private static int maxMultiplicator(Integer number){
        int maxMultiplicator = 1;
        //Loop from 2 to 9, because multiplying by a smaller number will always produce smaller or equal number, and
        // greater than 9 will always prduce bigger number
        for(int i = 2; i < 9; i++){
            String multiplied = String.valueOf(number * i);
            if(multiplied.length() == String.valueOf(number).length()){
                maxMultiplicator = i;
            }else{
                break;
            }
        }

        return maxMultiplicator;
    }

    public static boolean isMagicNumbersMultiple(String magicNumber, String multiple){
        String searchString;
        int numberLength = magicNumber.length();
        for(int i = 0; i < numberLength-1; i++){
            String firstHalf = multiple.substring(i+1);
            String secodHalf = multiple.substring(0,i+1);
            searchString = firstHalf.concat(secodHalf);

            if(searchString.equals(magicNumber)){
                return true;
            }
        }
        return false;
    }
}
