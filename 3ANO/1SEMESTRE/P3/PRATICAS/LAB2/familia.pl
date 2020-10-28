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


pai(X, Y) :- homem(X), progenitor(X, Y).

avo(X, Z) :- progenitor(X, Y), progenitor(Y, Z).

antepassados(X, Y) :- progenitor(X, Y). 
antepassados(X, Y) :- progenitor(X, Z), antepassados(Z,Y).

irmao(X, Y) :- progenitor(Z, X), progenitor(Z, Y), X\=Y.

tio(X, Y) :- irmao(X, Z), progenitor(Z, Y).

parente(X, Y) :- antepassados(X, Y). 
parente(X, Y) :- antepassados(Y, X).
parente(X, Y) :- antepassados(Z, X), antepassados(Z, Y), X\=Y.