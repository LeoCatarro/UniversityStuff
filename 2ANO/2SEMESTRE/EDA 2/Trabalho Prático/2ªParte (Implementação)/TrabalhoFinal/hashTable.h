#include <stdio.h>
#include <stdbool.h>

typedef struct student Student;

Student *student_new();
unsigned long hash(char *key);
int searchPos(char *key);
int loadFactor();
Student *find(FILE *file, char *key);
bool insertStudent(FILE *file, Student *student);
bool removeStudent(FILE *file, char *key);
void quit(FILE *file, Student *student);
void finished(FILE *file, Student *student);
void reHash();
void print();