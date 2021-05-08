typedef int ElementType;

#ifndef _BTree_H
#define _BTree_H

struct TreeNode;
typedef struct TreeNode *Position;
typedef struct TreeNode *BTree;

BTree MakeEmpty( BTree T );
BTree SetTree( ElementType X, BTree Left, BTree Right );

Position Find( ElementType X, BTree T );

ElementType Retrieve( Position P );


#endif
