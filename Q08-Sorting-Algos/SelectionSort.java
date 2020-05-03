// Solution For Question-8 in java
// Find the Problem at https://ram914.blogspot.com/2020/01/questions-for-cse-students-to-practice.html
// Solution Written By : Ram Prasd Gudiwada(ram914)
// The file is open for edit suggestions
//
//
//
//////////// Below is the solution //////////////

// CODE
class SelectionSort extends Sort {
    @Override
    public void sort(int[] array) {
        for(int i = 0; i < array.length; i++) {
            int smallIndex = getSmallest(array, i);
            swap(array, i, smallIndex);
        }
    }

    private int getSmallest(int[] array, int startFrom) {
        int min = array[startFrom];
        int mIndex = startFrom;
        for(int i = startFrom; i < array.length; i++) {
            if(array[i] < min) {
                mIndex = i;
            }
        }
        return mIndex;
    }
}
