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


BTree SetTree( ElementType X, BTree Left, BTree Right ){

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
}
