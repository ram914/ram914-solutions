// Solution For Question-1 in C
// Find the Problem at https://ram914.blogspot.com/2020/01/questions-for-cse-students-to-practice.html
// Solution Written By : Ram Prasd Gudiwada(ram914)
// The file is open for edit suggestions
// There are multiple commands given but this one only mimics tail command
//
//
//
//////////// Below is the solution //////////////

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<sys/stat.h>
#include<sys/types.h>
#include<sys/fcntl.h>
#include<unistd.h>
#define bool short

// function to check if a file exists or not
bool isFileExists(const char* fileName) {
    struct stat buffer;
    return (stat(fileName, &buffer) == 0);
}

// function to find the number of lines in a file
int findNumLinesInFile(const char* fileName) {

    int fd = open(fileName, O_RDONLY); // open the file in read only mode
    if(fd < 0) { // if any error occurred, terminate
        printf("Error: Unable to open file %s", fileName);
        return -1;
    }
    int lines = 0;
    do {
        char *c = (char *) calloc(100, sizeof(char));
        int b_r = read(fd, c, 10);
        if(b_r <= 0) {
            break; // either reached EOF or any error in read
        } else {
            c[b_r] = '\0';
        }
        for(int i = 0; i < b_r; i++) {
            if(c[i] == '\n'){
                lines++;
            }
        }
    } while(1);
    close(fd); // close the file
    return lines;
}

// function to tail the contents of a file
int tail(const int n, const char* fileName) {
    if(!isFileExists(fileName)) {
        printf("Error: Unable to locate file named %s\n", fileName);
        return -1;
    }

    // first find the number of lines in the file
    int lines = findNumLinesInFile(fileName);
    //printf("Number of lines in file : %d\n", lines);

    int fd = open(fileName, O_RDONLY); // open the file in read only mode

    if(fd == -1) { // if any error occurred, terminate
        printf("Error: Unable to open file %s", fileName);
        return -1;
    }

    // find out from which line you should start printing the
    // file contents, it should be last n lines
    // so remove n from number of lines, for those many lines
    // you do not print contents to terminal
    int line_to_start = lines - n;
    while(1) {
        char *c = (char *) calloc(102, sizeof(char)); // create a buffer
        int b_r = read(fd, c, 100); // read into buffer
        if(b_r <= 0) { // if num of bytes read is <= 0, then stop reading
            break; // either reached EOF or any error in read
        } else {
            c[b_r] = '\0'; // else form a string with c
        }

        // now for every line,
        // if line to start is <= 0
        // then print complete contents read to buffer
        if(line_to_start <= 0) {
            printf("%s", c);
        } else { // otherwise
            for(int i = 0; i < b_r; i++) { // loop over contents
                if(line_to_start > 0) { // if still not reached last n lines
                    if(c[i] == '\n') { // read a new line char in buffer
                        line_to_start--; // decrease the number of lines to ommit, by 1.
                    }
                }
                // if in this current buffer if we crossed the last line that we need to
                // skip, then print the rest of the chars to console
                if(line_to_start <= 0) {
                    printf("%c", c[i]);
                }
            }
        }
    }
    close(fd); // close the file
    return 0;
}

// Driver program
int main(int argc, char** argv) {
    if(argc < 2) {
        printf("USAGE: mytail <n> <filename>\n");
        exit(0);
    }

    int n; // number of lines to print
    char* fileName; // name of the file to tail

    //printf("argv1 : %s, argv2 : %s\n", argv[1], argv[2]);
    n = atoi(argv[1]); // read the n
    fileName = (char*) malloc(sizeof(char) * (strlen(argv[2])+1) );
    strcpy(fileName, argv[2]); // read the filename
    //printf("n:%d file:%s\n", n, fileName);

    return tail(n, fileName); // tail the file contents
}
