typedef int ElementType;
typedef unsigned int Index;
struct ListNode;
typedef struct ListNode *Position;
struct HashTbl;
typedef struct HashTbl *HashTable;

long HashWords(unsigned long Key, int TableSize );
HashTable InitializeKeysTable( int TableSize );
void DestroyKeysTable( HashTable H );
int FindKey(wchar_t Key, HashTable H );
Position FindNthKey(wchar_t Key, int Index, HashTable T );
void InsertNthKey(wchar_t Key, int Index, HashTable T );
void InsertT9Keys(HashTable T);
wchar_t RetrieveKey( Position P );
void PrintHashKeysTable(HashTable T);
unsigned long StringToIntAccordingT9Keys(wchar_t *word, HashTable KeysTable);
