typedef int ElementType;

#ifndef _BTree_H
#define _BTree_H

struct TreeNode;
typedef struct TreeNode *Position;
typedef struct TreeNode *BTree;

//Implemented to implement BTree
BTree MakeEmpty( BTree T );
BTree SetTree( ElementType X, BTree Left, BTree Right );
Position Find( ElementType X, BTree T );
ElementType Retrieve( Position P );


//Ficha-5
ElementType FindMax( BTree T );
ElementType FindMin( BTree T );

void PrintInOrder(BTree T);
void PrintPreOrder( BTree T );
void PrintPosOrder( BTree T );

#endif
