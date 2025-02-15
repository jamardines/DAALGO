import java.util.Arrays;

class Main {
   public static void main(String[] args) {
      int[] arr = {7, 10, 11, 9, 1};
        System.out.println("Original Array: " + Arrays.toString(arr));

        MergeSort(arr, 0, arr.length - 1);

        System.out.println("Sorted Array: " + Arrays.toString(arr));
   }
   
   static void MergeSort(int[] array, int left, int right) {
      
      if (left < right) {
         int mid = (left - right) / 2;
         
         MergeSort(array, left, mid);
         MergeSort(array, mid+1, right);
         
         Merge(array, left, mid, right);
      }
   }
   
   static void Merge(int[] array, int left, int mid, int right) {
      
      int n1 = mid - left + 1;
      int n2 = right - mid;
      
      int[] leftArray = new int[n1];
      int[] rightArray = new int[n2];
      
      for (int i = 0; i < n1; i++) {
         leftArray[i] = array[left + i];
      }
      
      for (int j = 0; j < n2; j++) {
         rightArray[j] = array[mid + 1 + j];
      }
      
      int iIndex = 0, jIndex = 0, kIndex = left;
      
      while (iIndex < n1 && jIndex < n2) {
         if (leftArray[iIndex] <= rightArray[jIndex]) {
            array[kIndex++] = leftArray[iIndex++];
         } else {
            array[kIndex] = rightArray[jIndex++];
         }
      }
   }
}
