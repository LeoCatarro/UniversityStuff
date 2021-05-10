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


BTree CreateTree(ElementType rootElement)
{
    BTree T = malloc(sizeof(struct TreeNode));
    T->Element = rootElement;
    T->Left = NULL;
    T->Right = NULL;
    return T;
}


BTree SetTree( ElementType X, BTree Left, BTree Right ){
    BTree node = malloc(sizeof(struct TreeNode));
    node->Element = X;
    node->Left = Left;
    node->Right = Right;
    return node;
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
    if( T == NULL )
        return NULL;
    else
        if( T->Right == NULL )
            return T->Element;
        else
            return FindMax( T->Right ); 
}

ElementType FindMin( BTree T ) {
    if( T == NULL )
        return NULL;
    else
        if( T->Left == NULL )
            return T->Element;
        else
            return FindMin(T->Left); 
}   

int main()
{
    BTree T = CreateTree(5);

    T->Left = SetTree(3, SetTree(1, NULL, NULL), SetTree(4, NULL, NULL));

    T->Right = SetTree(8, SetTree(7, NULL, NULL), SetTree(9, NULL, NULL));

    printf("%d\n", FindMax(T));

    printf("%d\n", FindMin(T));


    return 0;
}
