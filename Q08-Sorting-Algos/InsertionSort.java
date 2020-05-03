// Solution For Question-8 in java
// Find the Problem at https://ram914.blogspot.com/2020/01/questions-for-cse-students-to-practice.html
// Solution Written By : Ram Prasd Gudiwada(ram914)
// The file is open for edit suggestions
//
//
//
//////////// Below is the solution //////////////

// CODE
class InsertionSort extends Sort {

    @Override
    public void sort(int[] A) {
        int N = A.length;
        for(int i = 1; i < N; i++) {
            int j = i - 1;
            int key = A[i];

            while(j >= 0 && key < A[j]) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = key;
        }
    }
}
