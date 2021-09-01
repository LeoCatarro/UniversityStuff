/*Contar nós*/
:-dynamic(nos/1).
nos(0).

inc :- retract(nos(N)), N1 is N+1, asserta(nos(N1)).

/*Número de Lugares*/
lugares(8).

/*Estado Inicial*/
estado_inicial(e([
    v(c(1),D,_),
    v(c(2),D,_),
    v(c(3),D,_),
    v(c(4),D,_),
    v(c(5),D,_),
    v(c(6),D,_),
    v(c(7),D,_),
    v(c(8),D,_)], [])):- pessoas(D).

/*Pessoas*/
pessoas(['Maria', 'Manuel', 'Madalena', 'Joaquim', 'Ana', 'Julio', 'Matilde', 'Gabriel']).

/*Pesquisa Backtracking*/
back(e([],A),A).
back(E,Sol):- 
    sucessor(E,E1),
    inc,
    ve_restricoes(E1), 
    back(E1,Sol).
    
/*Pesquisa ForwardChecking*/
forward(e([],A),A).
forward(E,Sol):- 
    sucessor(E,E1),
    inc,
    ve_restricoes(E1), 
    forCheck(E1,E2),
    forward(E2, Sol).


forCheck(e(Lni,[v(N,D,V)|Li]), e(Lnii, [v(N,D,V)|Li])) :- corta(V,Lni,Lnii).

corta(_,[],[]).
corta(V, [v(N,D,_)|Li], [v(N,D1,_)|Lii]):- delete(D,V,D1), corta(V,Li,Lii).


/*Sucessor*/
sucessor(e([v(N,D,V)|R],E),e(R,[v(N,D,V)|E])):- member(V,D).


/*Restricoes*/
restrict(I, X, Y, J) :- restricoes(L), member(esq(X, Y), L), \+ (I is J+1; (I=1, J=8)).
restrict(I, X, Y, J) :- restricoes(L), member(dir(X, Y), L), \+ (I is J-1 ; (I=8, J=1)).
restrict(I, X, Y, J) :- restricoes(L), member(lado(X, Y), L), \+ ((I is J+1; (I=1, J=8));(I is J-1;(I=8, J=1)) ).
restrict(I, X, _, _) :- restricoes(L), member(cabeceira(X),L), \+ (I=1; I=5).
restrict(I, X, Y, J) :- restricoes(L), member(frente(X, Y), L), \+ (I is J-4;(I=4, J=6);(I=2, J=8)).
restrict(I, X, Y, J) :- I \= J, X=Y.

restricoes([esq('Manuel','Maria'), lado('Joaquim', 'Matilde'), frente('Joaquim','Maria'), cabeceira('Gabriel')]).

/*Ve Restricoes*/
ve_restricoes(e(_Nafec,Afect)):- 
    \+ (member(v(c(I),_Di,Vi), Afect), 
    member(v(c(J),_Dj,Vj),Afect),
    restrict(I,Vi,Vj,J)).
   

/*Output*/
esc([]).
esc([H|R]) :- write(H), nl, esc(R).


/*
    MAIN COMMANDS
*/
pesquisa_back:- 
    nos(0),
    estado_inicial(E0), 
    back(E0,A), 
    sort(A, L), 
    esc(L),
    nos(N), 
    write('Nós: '),
    write(N).

pesquisa_forward:- 
    nos(0),
    estado_inicial(E0), 
    forward(E0,A), 
    sort(A, L), 
    esc(L),
    nos(N), 
    write('Nós: '),
    write(N).