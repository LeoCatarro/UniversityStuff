/*1)    Lista: [ A , A+1 , ... , B ]*/
sequencia(A,A,[A]).     
sequencia(A,B,[A | R]) :- 
    A<B,
    A1 is A+1,
    sequencia(A1,B,R).


/*2)    Lista: [ A , A , A+1 , A+1 , ... , B , B ]*/ 
double([],[]).
double([A | B], [A, A | B2]) :-
    double(B,B2).


/*3)    Adjacente*/
adj(E1,E2, [E1,E2 | _]).
adj(E1,E2, [E2,E1 | _]).
adj(E1, E2, [_ | T]) :-
    adj(E1, E2, [T]).