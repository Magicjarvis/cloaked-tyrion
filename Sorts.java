package com.magicjarvis.common.cloakedtyrion;

import java.util.Arrays; 

/**
 * Basic implementation of some common sorts. Implementations are typically
 * done with integers so that the logic remains clear. They are not meant to
 * be used for anything other than learning.
 *
 * @author Jarvis Johnson (magicjarvis@gmail.com)
 */
public class Sorts {
    public static void quicksort(int[] array) {
        quicksort(array, 0, array.length); 
    }

    /** Makes the recursive calls. */
    private static void quicksort(int[] array, int left, int right) {
        if (right - left <= 1) return;
        int pivot = partition(array, left, right);
        quicksort(array, left, pivot);
        quicksort(array, pivot, right);
    }

    /** In-place partition algorithm */
    private static int partition(int[] array, int left, int right) {
        int pivot = right - 1;
        int pivotValue = array[pivot];
        int store = left;
        
        for (int i = left; i < pivot; i++) {
            if (array[i] <= pivotValue) swap(array, i, store++);
        }
        swap(array, pivot, store);
        return store;
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
