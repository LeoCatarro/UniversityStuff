#include "abp.h"
#include <stdlib.h>
#include "fatal.h"

struct TreeNode{
    ElementType Element;
    SearchTree  Left;
    SearchTree  Right;
};


SearchTree CreateTree(ElementType rootElement)
{
    SearchTree T = malloc(sizeof(struct TreeNode));
    T->Element = rootElement;
    T->Left = NULL;
    T->Right = NULL;
    return T;
}


SearchTree MakeEmpty( SearchTree T ){
    if( T != NULL ){
        MakeEmpty( T->Left );
        MakeEmpty( T->Right );
        free( T );
    }
    return NULL; 
}


Position Find( ElementType X, SearchTree T ){
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


Position FindMin( SearchTree T ){
    if( T == NULL )
        return NULL;
    else
        if( T->Left == NULL )
            return T;
        else
            return FindMin(T->Left); 
}


Position FindMax( SearchTree T ){
    if( T == NULL )
        return NULL;
    else
        if( T->Right == NULL )
            return T;
        else
            return FindMax( T->Right ); 
}


SearchTree Insert( ElementType X, SearchTree T ){
}


SearchTree Delete( ElementType X, SearchTree T ){
}


ElementType Retrieve( Position P ){
    return P->Element;
}


int main()
{

    return 0;
}