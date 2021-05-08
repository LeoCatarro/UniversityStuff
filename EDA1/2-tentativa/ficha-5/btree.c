#include "btree.h"
#include <stdlib.h>
#include "fatal.h"

struct TreeNode{
    ElementType Element;
    BTree  Left;
    BTree  Right;
};


BTree MakeEmpty( BTree T ){
}


BTree SetTree( ElementType X, BTree Left, BTree Right ){

}


Position Find( ElementType X, BTree T ){
}


ElementType Retrieve( Position P ){
}
