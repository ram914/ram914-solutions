// Solution For Question-4 in C
// Find the Problem at https://ram914.blogspot.com/2020/01/questions-for-cse-students-to-practice.html
// Solution Written By : Ram Prasd Gudiwada(ram914)
// The file is open for edit suggestions
//
//
//
//////////// Below is the solution //////////////

/*Read the number of threads, compute the size of the i/p file, split the size of file into equal parts, feed each thread a part to read from.

Here we don't need to be bothered about the left out one or 2 bytes, they will be taken care.

Each thread will open the file & reads char by char(except thread 1 as it is already at the start of the file), till they find a new line. If new line is found, we are sure that from next char we goto a new line and we can have a complete number to read. each thread then starts reading line by line and from that line it will retrive the integer and checks if it is prime or not and then writes it into the output file.
*/
#define _GNU_SOURCE
#include<stdio.h>
#include<string.h>
#include<math.h>
#include<stdlib.h>
#include<unistd.h>
#include<pthread.h>

// Structure to store the details that we pass
// to each thread
typedef struct ThreadInfoT {
    int threadId; // Id of the thread
    char* fileName; // input file name to read
    int start_byte; // starting position of thread
    int end_byte; // ending position of the thread
    char* outputFile; // output filename
} ThreadInfoT;

// Function to check if given number is a prime or not
// This has square root N complexity
int isPrime(long n) {
    if(n < 0) return 0;
    if(n == 1) return 1;
    if (n == 2) return 0;
    for(int i = 2; i < (int)sqrt(n)+1; i++) {
        if(n%i == 0) return 0;
    }
    return 1;
}

void printThreadInfo(ThreadInfoT tit) {
    printf(" Thread %d [fileName  : %s, outputFile : %s, start_byte : %d, end_byte : %d]\n",
            tit.threadId, tit.fileName, tit.outputFile, tit.start_byte, tit.end_byte);
}

// The Thread Function
void *ReadFile(void* arg) {
    //sleep(1); // sleep for one second

    // Now retrieve the Input ThreadInfoT struct from arg
    ThreadInfoT *tinfo = (ThreadInfoT*) arg;
    printf("T%d : STARTING\n", tinfo->threadId); // printing info to user

    FILE* fp = fopen(tinfo->fileName, "r");// open file in read mode

    fseek(fp, tinfo->start_byte, SEEK_SET); // goto the thread's starting position

    int nbytes = 0; // To keep track of how many bytes read

    // If we are not at the start of the file
    // we have to goto the next \n character
    // so that we can start reading a complete integer
    // without any data loss.
    char c;
    if(tinfo->start_byte != 0) {
        do{
            c = (char)fgetc(fp); // read a char
            nbytes++; // increate number of bytes read

            if(c == '\n' || c == EOF) // if we strike \n of EOF
                break; // stop the loop
        } while(1);
    }

    // Here we read the integers one at a time
    // and one from each line
    do {
        char *number;
        size_t lsize = 0;
        long num;
        getline(&number, &lsize, fp); // read a complete line as a string(including \n)

        sscanf(number, "%ld", &num); // read the integer from the string we read
        //printf("T%d: num : %ld,  number: %d\n", tinfo->threadId, num, (int)strlen(number));

        // send num to prime function
        if(isPrime(num)) { // check if num is a prime
            printf("T%d: num : %ld is prime\n", tinfo->threadId, num);

            // if it is a prime, then write it into the output file
            // using threadsafe fwrite file
			FILE* fout = fopen(tinfo->outputFile, "a+");
			fwrite(number, 1, sizeof(number), fout);
			fclose(fout);
        }

        // add the size of number string to nbytes
        nbytes += (int)strlen(number);
    }while((nbytes < (tinfo->end_byte - tinfo->start_byte)) || feof(fp));

    fclose(fp); // close the input file
    printf("T%d : HALTING\n", tinfo->threadId); // printing info to user
}

// Function to find the size of a file
long int findFileSize(char* fileName) {
    FILE* fp = fopen(fileName, "r"); // open file

    fseek(fp, 0L, SEEK_END); // goto end of file

    long int size = ftell(fp); // read the size of file

    fclose(fp); // close the file

    return size; // return the size
}

// Driver function
void main() {

    int N; // number of thread
    char* fileName = "primenumbers.txt"; // input file
    char* outputFile = "oprimenumbers.txt"; // output file
    long int fileSize = findFileSize(fileName);

    printf("File size is %ld bytes.\n", fileSize); // print the size of the file

    // read the number of threads
    printf("Enter the Number of threads :");
    scanf("%d", &N);

    // create N threads and split file into N parts
    // pass the starting and ending bytes for each
    // thread to read
    for(int i = 1; i <= N; i++) {
        // create the ThreadInfoT struct
        pthread_t thread;
        ThreadInfoT *tit = (ThreadInfoT*)malloc(sizeof(ThreadInfoT));

        tit->fileName = (char*) malloc(strlen(fileName)*sizeof(char));
        tit->outputFile = (char*) malloc(strlen(outputFile)*sizeof(char));

        strcpy(tit->fileName, fileName);
        strcpy(tit->outputFile, outputFile);

        tit->threadId = i;
        tit->start_byte = (i-1) * (fileSize/N);
        tit->end_byte = (i)* (fileSize/N);

        printThreadInfo(*tit);

        // create a thread
        pthread_create(&thread, NULL, ReadFile, tit);
        //pthread_join(thread, NULL);
    }
    pthread_exit(0);
}
