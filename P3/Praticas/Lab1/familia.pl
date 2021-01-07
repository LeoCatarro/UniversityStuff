/*
    Predicados
*/
homem(joao).
homem(rui).
homem(manuel).
homem(ricardo).
mulher(maria).
mulher(ana).
mulher(rita).
mulher(silvia).

progenitor(joao, maria).
progenitor(joao, rui).
progenitor(manuel, joao).
progenitor(ricardo, manuel).
progenitor(ana, rui).
progenitor(rita, joao).
progenitor(rita, silvia).


/*
    Regras
*/
pai(X, Y) :- homem(X), progenitor(X, Y).



/*
    RESPOSTAS
*/

/*1)*/
avo(X, Y) :- progenitor(X,Z), progenitor(Z,Y).
/*R.: X=manuel , X=rita */

/*2)*/
antepassado(X,Y) :- progenitor(X,Y).                            %Caso_Base
antepassado(X,Y) :- progenitor(Z,X), antepassado(Z,Y).          %Recursiva
/*R.: X=joao, X=maria, X=rui */

/*3)*/
irmao(X,Y):- progenitor(Z,X), progenitor(Z,Y), X \= Y.
/*R.: X=silvia */

/*4)*/
tio(X,Y) :- homem(X), progenitor(Z,Y), irmao(Z,X).
tia(X,Y) :- mulher(X), progenitor(Z,Y), irmao(Z,X).
/*R.: X=silvia Y=maria , X=silvia Y=rui */

/*5)*/
parente(X,Y) :- antepassado(X,Y).
parente(X,Y) :- antepassado(Y,X).
parente(X,Y) :- antepassado(C,X), antepassado(C,Y), X\=Y.
/*R.: X = joao Y = maria ? 

X = joao Y = rui

X = manuel Y = joao

X = ricardo Y = manuel

X = ana Y = rui

X = rita Y = joao

X = rita Y = silvia

X = maria Y = maria

X = maria Y = rui

X = maria Y = joao

X = maria Y = manuel

X = maria Y = joao

X = maria Y = silvia

X = rui Y = maria

X = rui
Y = rui

X = rui
Y = joao

X = rui
Y = manuel

X = rui
Y = joao

X = rui
Y = silvia

X = joao
Y = joao

X = joao
Y = manuel

X = manuel
Y = manuel

X = rui
Y = rui

X = joao
Y = joao

X = joao
Y = silvia

X = silvia
Y = joao

X = silvia
Y = silvia

X = maria
Y = joao

X = rui
Y = joao

X = joao
Y = manuel

X = manuel
Y = ricardo

X = rui
Y = ana

X = joao
Y = rita

X = silvia
Y = rita

X = maria
Y = maria

X = rui
Y = maria

X = joao
Y = maria

X = manuel
Y = maria

X = joao
Y = maria

X = silvia
Y = maria

X = maria
Y = rui

X = rui
Y = rui

X = joao
Y = rui

X = manuel
Y = rui

X = joao
Y = rui

X = silvia
Y = rui

X = joao
Y = joao

X = manuel
Y = joao

X = manuel
Y = manuel

X = rui
Y = rui

X = joao
Y = joao

X = silvia
Y = joao

X = joao
Y = silvia

X = silvia
Y = silvia

X = maria
Y = rui

X = maria
Y = joao

X = maria
Y = manuel

X = maria
Y = joao

X = maria
Y = silvia

X = rui
Y = maria

X = rui
Y = joao

X = rui
Y = manuel

X = rui
Y = joao

X = rui
Y = silvia

X = joao
Y = manuel

X = joao
Y = silvia

X = silvia
Y = joao

X = maria
Y = rui

X = maria
Y = joao

X = maria
Y = manuel

X = maria
Y = joao

X = maria
Y = silvia

X = rui
Y = maria

X = rui
Y = joao

X = rui
Y = manuel

X = rui
Y = joao

X = rui
Y = silvia

X = joao
Y = maria

X = joao
Y = rui

X = joao
Y = manuel

X = joao
Y = silvia

X = manuel
Y = maria

X = manuel
Y = rui

X = manuel
Y = joao

X = manuel
Y = joao

X = manuel
Y = silvia

X = joao
Y = maria

X = joao
Y = rui

X = joao
Y = manuel

X = joao
Y = silvia

X = silvia
Y = maria

X = silvia
Y = rui

X = silvia
Y = joao

X = silvia
Y = manuel

X = silvia
Y = joao

X = maria
Y = rui

X = maria
Y = joao

X = maria
Y = manuel

X = maria
Y = joao

X = maria
Y = silvia

X = maria
Y = rui

X = rui
Y = maria

X = rui
Y = joao

X = rui
Y = manuel

X = rui
Y = joao

X = rui
Y = silvia

X = joao
Y = maria

X = joao
Y = rui

X = joao
Y = manuel

X = joao
Y = silvia

X = joao
Y = rui

X = manuel
Y = maria

X = manuel
Y = rui

X = manuel
Y = joao

X = manuel
Y = joao

X = manuel
Y = silvia

X = manuel
Y = rui

X = joao
Y = maria

X = joao
Y = rui

X = joao
Y = manuel

X = joao
Y = silvia

X = joao
Y = rui

X = silvia
Y = maria

X = silvia
Y = rui

X = silvia
Y = joao

X = silvia
Y = manuel

X = silvia
Y = joao

X = silvia
Y = rui

X = joao
Y = maria

X = joao
Y = rui

X = joao
Y = manuel

X = joao
Y = silvia

X = manuel
Y = maria

X = manuel
Y = rui

X = manuel
Y = joao

X = manuel
Y = joao

X = manuel
Y = silvia

X = manuel
Y = joao

X = rui
Y = maria

X = rui
Y = joao

X = rui
Y = manuel

X = rui
Y = joao

X = rui
Y = silvia

X = joao
Y = maria

X = joao
Y = rui

X = joao
Y = manuel

X = joao
Y = silvia

X = silvia
Y = maria

X = silvia
Y = rui

X = silvia
Y = joao

X = silvia
Y = manuel

X = silvia
Y = joao

X = joao
Y = silvia

X = silvia
Y = joao
*/