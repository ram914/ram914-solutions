// Solution For Question-8 in java
// Find the Problem at https://ram914.blogspot.com/2020/01/questions-for-cse-students-to-practice.html
// Solution Written By : Ram Prasd Gudiwada(ram914)
// The file is open for edit suggestions
//
//
//
//////////// Below is the solution //////////////

// CODE
class BubbleSort extends Sort {

    @Override
    public void sort(int[] A) {
        int N = A.length;
        for(int i = 0; i < N; i++) {
            for(int j = i+1; j < N; j++) {
                if(A[i] > A[j]) {
                    swap(A, i, j);
                }
            }
        }
    }
}
