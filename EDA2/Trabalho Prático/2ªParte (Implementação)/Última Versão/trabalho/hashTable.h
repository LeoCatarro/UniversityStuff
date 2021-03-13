#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

//hashtable.h nao e incluida 2 vezes causando erro
#ifndef HASHTABLE_H
#define HASHTABLE_H
#define ID_SIZE 7
#define P_ID_SIZE 3

//Student Hashtable

typedef struct student 
{
    char id[ID_SIZE], p_id[P_ID_SIZE];
    bool quit, finished;
    bool isStudent;
}Student;

Student *student_new();
unsigned long hashStudent(char *s);
bool searchPosStudent(FILE *file, Student *student);
bool insertPosStudent(FILE *file, Student *student);
bool insertStudent(FILE *file, Student *student);
bool removeStudent(FILE *file, Student *student);
bool abandoned(FILE *file, Student *student);
bool finished(FILE *file, Student *student);
char *getCountry(FILE *file, Student *student);


//Country Hashtable

typedef struct country
{
    char p_id[P_ID_SIZE];
    bool isCountry;
    int active, finished, abandoned, total; 
}Country;

Country *country_new();
unsigned long hashCountry(char *s);
bool searchPosCountry(FILE *file, Country *country);
bool insertPosCountry(FILE *file, Country *country);
bool insertCountry(FILE *file, Country *country);
void addActive(FILE *file, Country *country);
void removeActive(FILE *file, Country *country);
void addAbandoned(FILE *file, Country *country);
void addFinished(FILE *file, Country *country);
#endif