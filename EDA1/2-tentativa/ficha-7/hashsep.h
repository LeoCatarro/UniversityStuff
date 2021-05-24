typedef int ElementType;


#ifndef _HashSep_H
#define _HashSep_H

struct ListNode;
typedef struct ListNode *Position;

struct HashTbl;
typedef struct HashTbl *HashTable;

HashTable InitializeTable( int TableSize );
void DestroyTable( HashTable H );

Position Find( ElementType Key, HashTable H );
void Insert( ElementType Key, HashTable H );
ElementType Retrieve( Position P );
void PrintHashTable(HashTable T);

HashTable Delete( ElementType X, HashTable T );
HashTable MakeEmpty( HashTable T );

#endif