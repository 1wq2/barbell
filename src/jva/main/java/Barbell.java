package jva.main.java;

import jva.test.java.WorkException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Barbell {

    private static ArrayList<Integer> list = new ArrayList<>();

    private static int arraySum = 0;

    public static void main(String[] args) throws WorkException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter numbers for your array");

        while(in.hasNextInt()){
            list.add(in.nextInt());
        }
        System.out.println(list);

        raisingTheBarbell(list);
    }
    private static int raisingTheBarbell(ArrayList<Integer> array) throws WorkException {

        checkRestrictions(array);
        int result = removeDuplicates(array);
        if (array.size() > 2) {
            result = sumWeight(result, list);
        } else {
            result = checkSize(array);
        }
        return result;
    }

    /**
     * This method checks the restrictions imposed on the program.
     * @param array
     */
    private static void checkRestrictions(ArrayList<Integer> array) throws WorkException {
        boolean isNeedSave = false;

        for(Integer i : list)
            arraySum+=i;

        if (arraySum <= 10000) {
            for (int i = 0; i < array.size() - 1; i++) {
                if (array.get(i) <= 20 && array.get(i) >= 1) {
                    isNeedSave = true;
                }
            }
        } else {
            throw new WorkException("The total weight of the barbell > 10000.");
        }
        if (!isNeedSave) {
            System.out.println("The weight of the plate does not fit.");
        }
    }
    /**
     * This method checks the array size.
     * @param array
     */
    private static int checkSize(ArrayList<Integer> array) {
        int isNeedSave = 0;
        if (array.size() <= 2) {
            if (array.get(0) == array.get(1)) {
                isNeedSave = array.get(0) * 2;
            }
        }
        return isNeedSave;
    }
    /**
     * This method removes duplicates in the array.
     * @param array
     */
    private static int removeDuplicates(ArrayList<Integer> array) {
        int i = 0;
        int sum = 0;

        Collections.sort(array);

        while (i < array.size()) {
            if (i == array.size() - 1) {
                list.add(array.get(i));
                break;
            }
            if (array.get(i) == array.get(i + 1)) {
                sum += array.get(i) * 2;
                i += 2;
            } else {
                list.add(array.get(i++));
            }
        }
        return sum;
    }
    /**
     * One option of calculating the weight of plates on the barbell.
     */
    private static int sumWeight(int sum, ArrayList<Integer> list) {
        Collections.reverse(list);
        int result, sumLeft = 0, sumRight = 0;
        int halfAmount = arraySum / 2;

        for (Integer aList : list) {
            if (Math.abs(sumLeft + aList) == halfAmount |
                    Math.abs(sumLeft + aList - sumRight) <= Math.abs(sumRight + aList - sumLeft)) {
                if (sumLeft == halfAmount) {
                    break;
                }
                sumLeft += aList;
            } else {
                sumRight += aList;
            }
        }
        if (sumLeft == sumRight) {
            if (sum == 0) {
                result = sumLeft + sumRight;
            } else {
                result = sumLeft + sumRight + sum;
            }
        } else {
            result = sum;
            for (int i = 0; i < list.size(); i++) {
                for (int j = 1; j < list.size(); j++) {
                    if (result == list.get(i) + list.get(j)) {
                        result += list.get(i) + list.get(j);
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * This method prints information to the console..
     */
    public int printTest(int[] array) throws WorkException {
        int value = raisingTheBarbell(list);
        if (value > 0) {
            System.out.print("The total weight of the barbell " + value);
            System.out.println("The weight on the left and right side = " + value / 2);
        } else {
            System.out.println("The total weight of the barbell " + value + ". There is no weight for the barbell");
        }
        return value;
    }

}
