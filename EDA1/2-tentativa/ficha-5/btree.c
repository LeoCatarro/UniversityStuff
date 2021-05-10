#include "btree.h"
#include <stdlib.h>
#include "fatal.h"

struct TreeNode{
    ElementType Element;
    BTree  Left;
    BTree  Right;
};


BTree MakeEmpty( BTree T ){
    if( T != NULL ){
        MakeEmpty( T->Left );
        MakeEmpty( T->Right );
        free( T );
    }
    return NULL; 
}

BTree CreateTree()
{
    BTree T = malloc(sizeof(struct TreeNode));
    return T;
}


BTree SetTree( ElementType X, BTree Left, BTree Right ){
    BTree root = malloc(sizeof(struct TreeNode));
    root->Element = X;
    root->Left = NULL;
    root->Right = NULL;
    return root;
}

/*
BTree GetLeft(BTree T)
{
    if(T == NULL)
        return NULL;
    
    return T->Left;
}


BTree GetRight(BTree T)
{
    if(T == NULL)
        return NULL;
    
    return T->Right;
}
*/

Position Find( ElementType X, BTree T ){
    if( T == NULL )
        return NULL;

    if( X < T->Element )
        return Find( X, T->Left );

    else
        if( X > T->Element )
            return Find( X, T->Right );
        else
            return T;
}


ElementType Retrieve( Position P ){
    return P->Element;
}


ElementType FindMax( BTree T ) {
    if(T == NULL)
        return NULL;
    
    else
        if(T->Right != NULL)
            return FindMax(T->Right);
        else
            return NULL;
}


ElementType FindMin( BTree T ) {
    if(T == NULL)
        return NULL;
    
    else
        if(T->Left != NULL)
            return FindMin(T->Left);
        else
            return NULL;
}   


int main()
{
    BTree T = CreateTree();
    
    return 0;
}
