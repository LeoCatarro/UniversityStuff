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
            return Retrieve(T);
        else
            return FindMax( T->Right ); 
}

ElementType FindMin( BTree T ) {
    if( T == NULL )
        return NULL;
    else
        if( T->Left == NULL )
            return Retrieve(T);
        else
            return FindMin(T->Left); 
}   


void PrintInOrder(BTree T)
{
    if (T != NULL) {
        PrintInOrder(T->Left);
        printf("%d ", T->Element);
        PrintInOrder(T->Right);
    }
}

void PrintPreOrder( BTree T ){
    if(T != NULL){
        printf("%d ", T->Element);
        PrintPreOrder(T->Left);
        PrintPreOrder(T->Right);
    }
}

void PrintPosOrder( BTree T ){
    if(T != NULL){
        PrintPosOrder(T->Left);
        PrintPosOrder(T->Right);
        printf("%d ", T->Element);
    }
}


int main()
{
    BTree T = CreateTree(5);
    T->Left = SetTree(3, SetTree(1, NULL, NULL), SetTree(4, NULL, NULL));
    T->Right = SetTree(8, SetTree(7, NULL, NULL), SetTree(9, NULL, NULL));


    printf("%d\n", FindMax(T));
    printf("%d\n", FindMin(T));

    //PrintInOrder Test
    printf("In Order: ");
    PrintInOrder(T);
    printf("\n");

    //PrintPreOrder Test
     printf("Pre Order: ");
    PrintPreOrder(T);
    printf("\n");
    
    //PrintPosOrder Test
    printf("Pos Order: ");
    PrintPosOrder(T);
    printf("\n");


    return 0;
}
