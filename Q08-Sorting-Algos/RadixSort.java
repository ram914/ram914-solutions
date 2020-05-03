// Solution For Question-8 in java
// Find the Problem at https://ram914.blogspot.com/2020/01/questions-for-cse-students-to-practice.html
// Solution Written By : Ram Prasd Gudiwada(ram914)
// The file is open for edit suggestions
//
//
//
//////////// Below is the solution //////////////

//CODE
public class RadixSort extends Sort {

    //Sorting in non decreasing order
    private void radixSort(int arr[]) {
        //Finding maximum number to count the number of digits
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        int p = 1;
        int pass = 1;

        while(max / p > 0) {
            arr = countingSort(arr, arr.length, p);
            //System.out.print("After pass " + pass + " : ");
            //Printing array after pass
            //this.printArray(arr);
            pass++;
            p *= 10;
        }
    }

    private int[] countingSort(int arr[], int N, int p) {
        int output[] = new int[N];
        int count[] = new int[10]; //keeping count if digits <=9

        for(int i = 0; i < N; i++)
            count[(arr[i] / p) % 10]++;

        //Applying counting sort so now the array contains actual position of the digits
        for(int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        //staring from N-1 helps to keep digits in order
        for(int i = N - 1; i >= 0; i--) {
            output[count[(arr[i] / p) % 10] - 1] = arr[i];
            count[(arr[i] / p) % 10]--;
        }
        return output;
    }

    @Override
    public void sort(int[] array) {
        this.radixSort(array);
    }
}
