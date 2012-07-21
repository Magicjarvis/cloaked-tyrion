/**
 * Basic implementation of some common sorts. Implementations are typically
 * done with integers so that the logic remains clear. They are not meant to
 * be used for anything other than learning.
 *
 * @author Jarvis Johnson (magicjarvis@gmail.com)
 */
public class Sorts {

  /** Standard implementation of Mergesort. */
  public static void mergesort(int[] array) {
    mergesort(array, 0, array.length); 
  }

  private static void mergesort(int[] array, int left, int right) {
    if (right - left <= 1) return;
    int mid = (right + left) / 2;
    mergesort(array, left, mid);
    mergesort(array, mid, right);
    merge(array, left, mid, right);
  }

  /** Merges and copies the sorted array back into the array passed in */
  private static void merge(int[] array, int left, int mid, int right) {
    int[] sorted = new int[right - left];

    int leftProbe = left;
    int rightProbe = mid;

    // TODO(Magicjarvis): This logic is somewhat redundant, but it makes sense
    // I should find an alternative that's also clear, yet less redundant.
    for (int i = 0; leftProbe < mid || rightProbe < right; i++) {
      if (leftProbe < mid && rightProbe < right) {
        if (array[leftProbe] < array[rightProbe]) {
          sorted[i] = array[leftProbe++];
        } else {
          sorted[i] = array[rightProbe++]; 
        }
      } else if (leftProbe < mid) {
        sorted[i] = array[leftProbe++];
      } else {
        sorted[i] = array[rightProbe++]; 
      }
    }

    for (int i = 0; i < sorted.length; i++) {
      array[left + i] = sorted[i]; 
    }
  }

  /** Standard implementation of Quicksort */
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
