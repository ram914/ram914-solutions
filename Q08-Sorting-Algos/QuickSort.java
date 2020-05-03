// Solution For Question-8 in java
// Find the Problem at https://ram914.blogspot.com/2020/01/questions-for-cse-students-to-practice.html
// Solution Written By : Ram Prasd Gudiwada(ram914)
// The file is open for edit suggestions
//
//
//
//////////// Below is the solution //////////////

// CODE
class QuickSort extends Sort {

    @Override
    public void sort(int[] array) {
        quicksort(array, 0, array.length-1);
    }

    void quicksort(int A[], int p, int r) {
        if(p < r) {
            int pos = partition(A, p, r);
            quicksort(A, p, pos - 1);
            quicksort(A, pos + 1, r);
        }
    }

    int partition(int A[], int i, int j) {
        int pivot = A[j];
        int small = i - 1;
        for(int k = i; k < j; k++) {
            if(A[k] <= pivot) {
                small++;
                swap(A, k, small);
            }
        }
        swap(A, j, small + 1);
        return small + 1;
    }
}
