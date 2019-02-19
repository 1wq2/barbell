package java.main;

import java.test.WorkException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Barbell {

    private ArrayList<Integer> list = new ArrayList<>();

    private int arraySum = 0;
    /**
     * This method checks the restrictions imposed on the program.
     */
    private void checkRestrictions(int[] array) throws WorkException {
        boolean isNeedSave = false;
        this.arraySum = Arrays.stream(array).sum();
        if (this.arraySum <= 10000) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] <= 20 && array[i] >= 1) {
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
     */
    private int checkSize(int[] array) {
        int isNeedSave = 0;
        if (array.length <= 2) {
            if (array[0] == array[1]) {
                isNeedSave = array[0] * 2;
            }
        }
        return isNeedSave;
    }
    /**
     * This method removes duplicates in the array.
    */
    private int removeDuplicates(int[] array) {
        int i = 0;
        int sum = 0;
        Arrays.sort(array);
        while (i < array.length) {
            if (i == array.length - 1) {
                this.list.add(array[i]);
                break;
            }
            if (array[i] == array[i + 1]) {
                sum += array[i] * 2;
                i += 2;
            } else {
                this.list.add(array[i++]);
            }
        }
        return sum;
    }
    /**
     * One option of calculating the weight of plates on the barbell.
     */
    private int sumWeight(int sum, ArrayList<Integer> list) {
        Collections.reverse(list);
        int result, sumLeft = 0, sumRight = 0;
        int halfAmount = this.arraySum / 2;

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
     main method
     */
    private int raisingTheBarbell(int[] array) throws WorkException {
        checkRestrictions(array);
        int result = removeDuplicates(array);
        if (array.length > 2) {
            result = sumWeight(result, this.list);
        } else {
            result = checkSize(array);
        }
        return result;
    }
    /**
     * This method prints information to the console..
     */
    public int printTest(int[] array) throws WorkException {
        int value = raisingTheBarbell(array);
        if (value > 0) {
            System.out.print("The total weight of the barbell " + value);
            System.out.println("The weight on the left and right side = " + value / 2);
        } else {
            System.out.println("The total weight of the barbell " + value + ". There is no weight for the barbell");
        }
        return value;
    }

}
