function split(p,a)
{
    let verify = [];
    let unverify = [];
    
    for(let i=0 ; i<a.lenght ; i++)
    {
        if(a[i].p == true)
        {
            verify.push(a[i]);
        }
        else
        {
            unverify.push(a[i]);
        }
    }
    return [verify , unverify];
}

//Ou: Possivelmente +correta

function split(p,a)
{
    let verify = [];
    let unverify = [];
    
    for(let i=0 ; i<a.lenght ; i++)
    {
        if(p(a[i]) == true)
        {
            verify.push(a[i]);
        }
        else
        {
            unverify.push(a[i]);
        }
    }
    return [verify , unverify];
}