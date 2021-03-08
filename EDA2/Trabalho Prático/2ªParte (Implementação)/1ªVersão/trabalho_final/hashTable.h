#include <stdio.h>
#include <stdbool.h>

typedef struct student Student;

Student *student_new();
unsigned long hash(char *key);
bool searchPos(FILE *file, Student *student);
bool insertPos(FILE *file, Student *student);
int loadFactor();
bool findStudent(FILE *file, Student *student);
bool insertStudent(FILE *file, Student *student);
bool removeStudent(FILE *file, Student *student);
void quit(FILE *file, Student *student);
void finished(FILE *file, Student *student);
void reHash();
void print();