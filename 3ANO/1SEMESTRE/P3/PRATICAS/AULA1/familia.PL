homem(joao).
homem(rui).
mulher(ana).
mulher(maria).
progenitor(joao,maria).
progenitor(ana,maria).
pai(X,Y):-homem(X),progenitor(X,Y).