// Solution For Question-8 in java
// Find the Problem at https://ram914.blogspot.com/2020/01/questions-for-cse-students-to-practice.html
// Solution Written By : Ram Prasd Gudiwada(ram914)
// The file is open for edit suggestions
//
//
//
//////////// Below is the solution //////////////

// CODE
class MergeSort extends Sort {
    private int len;
    // MERGE SORT
    void merge(int A[], int p, int q, int r) {

        int LM[] = new int[len];
        int lt = p;
        int rt = r;
        int md = q + 1;
        int k = lt;

        while(lt <= q && md <= rt) {
            if(A[lt] <= A[md]) {
                LM[k++] = A[lt++];
            }
            else {
                LM[k++] = A[md++];
            }
        }

        while(lt <= q)
            LM[k++] = A[lt++];

        while(md <= rt) {
            LM[k++] = A[md++];
        }

        for(int i1 = p; i1 <= r; i1++) {
            A[i1] = LM[i1];
        }
    }

    void mergesort(int A[], int p, int r) {
        if(p < r) {
            int q = (p + r) / 2;
            mergesort(A, p, q);
            mergesort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    @Override
    public void sort(int[] array) {
        this.len = array.length;
        mergesort(array, 0, array.length-1);
    }
}
