#include <stdio.h>
#include <stdbool.h>

typedef struct country Country;

Country *country_new();
unsigned long hash(char *key);
bool searchPos(FILE *file, Country *country);
bool insertPos(FILE *file, Country *country);
int loadFactor();
bool findStudent(FILE *file, Country *country);
bool insertStudent(FILE *file, Country *country);
bool removeStudent(FILE *file, Country *country);
void quit(FILE *file, Country *country);
void finished(FILE *file, Country *country);
void reHash();
void print();