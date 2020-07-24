function p(a)
{
    if(a%2 == 0)
    {
        return true;
    }
    return false;
}


function count_pred(p,x)
{
    let res=0;

    for(let i=0 ; i<x.length ; i++)
    {
        if(p(x[i]) == true)
        {
            res++;
        }
    }
    return res;
}

function main()
{
    let arr = [0,1,2,3,4];

    count_pred(p,arr);
}