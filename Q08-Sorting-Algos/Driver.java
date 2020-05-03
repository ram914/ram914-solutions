// Solution For Question-8 in java
// Find the Problem at https://ram914.blogspot.com/2020/01/questions-for-cse-students-to-practice.html
// Solution Written By : Ram Prasd Gudiwada(ram914)
// The file is open for edit suggestions
//
//
//
//////////// Below is the solution //////////////

// CODE
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Driver {
    //final static int MAX = 1000;
    final static int MIN = 100;

    // create an array of sizes
    final static int sizes[] = {2000, 20000, 50000, 100000, 500000};

    public static void main(String... args) throws IOException {
        long begin, end;
        double time_spent;
        int A[];
        int size;

        System.out.println("ArraySize   BubbleSort   InsertionSort   SelectionSort   MergeSort   QuickSort   RadixSort");

        // Open the fil to write the output
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("SortComparison.csv")));
        out.println("ArraySize,InsertionSort,SelectionSort,MergeSort,QuickSort,RadixSort");
        for(int i = 0; i < sizes.length; i++) { // for each array size, run the three algorithms
            size = sizes[i];
            System.out.printf("%-9d   ", size);
            out.printf("%-9d,", size);

            // BUBBLE SORT
            BubbleSort bs = new BubbleSort();
            A = getRandInts(size, size);
            begin = System.currentTimeMillis();
            bs.sort(A); // sort the array
            end = System.currentTimeMillis();
            time_spent = (end - begin) / 1000.0;
            System.out.printf("%-10.4f   ", time_spent);
            out.printf("%-10.4f,", time_spent);

            // INSERTION SORT
            InsertionSort is = new InsertionSort();
            A = getRandInts(size, size);
            begin = System.currentTimeMillis();
            is.sort(A); // sort the array
            end = System.currentTimeMillis();
            time_spent = (end - begin) / 1000.0;
            System.out.printf("%-13.4f   ", time_spent);
            out.printf("%-13.4f,", time_spent);

            // SELECTION SORT
            SelectionSort ss = new SelectionSort();
            A = getRandInts(size, size);
            begin = System.currentTimeMillis();
            ss.sort(A); // sort the array
            end = System.currentTimeMillis();
            time_spent = (end - begin) / 1000.0;
            System.out.printf("%-13.4f   ", time_spent);
            out.printf("%-13.4f,", time_spent);

            // MERGE SORT
            MergeSort ms = new MergeSort();
            A = getRandInts(size, size);
            begin = System.currentTimeMillis();
            ms.sort(A);
            end = System.currentTimeMillis();
            time_spent = (end - begin) / 1000.0;
            System.out.printf("%-9.4f   ", time_spent);
            out.printf("%-9.4f,", time_spent);

            // QUICK SORT
            QuickSort qs = new QuickSort();
            A = getRandInts(size, size);
            begin =System.currentTimeMillis();
            qs.sort(A);
            end = System.currentTimeMillis();
            time_spent = (end - begin) / 1000.0;
            System.out.printf("%-12.4f", time_spent);
            out.printf("%-12.4f,", time_spent);

            // RADIX SORT
            RadixSort rs = new RadixSort();
            A = getRandInts(size, size);
            begin =System.currentTimeMillis();
            rs.sort(A);
            end = System.currentTimeMillis();
            time_spent = (end - begin) / 1000.0;
            System.out.printf("%-9.6f", time_spent);
            out.printf("%-9.6f", time_spent);

            System.out.println();
            out.println();
        }
        out.close();
    }

    // Function to Generate random number array
    private static int[] getRandInts(int size, int max) {
        int[] A = new int[size];
        for(int i = 0; i < size; i++) {
            int random = (int)((Double)(Math.random()*max)).longValue();
            A[i] = random;
        }
        return A;
    }
}
