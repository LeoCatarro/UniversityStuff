%tabuleiro de jogo
/*
        (1,1)(2,1)(3,1)
        (1,2)(2,2)(3,2)
        (1,3)(2,3)(3,3)
        (1,4)(2,4)(3,4)
*/

%estado inicial
%estado_inicial([[v,v,x], [v,o,o], [o,x,o], [x,o,x]]).
%estado_inicial([[v,v,v], [v,v,o], [o,x,o], [x,o,x]]).
%estado_inicial([[v,v,v], [v,v,v], [o,x,o], [x,o,x]]).
%estado_inicial([[v,v,v], [v,v,v], [o,x,o], [x,o,v]]).
%estado_inicial([[v,v,v], [v,v,v], [o,x,o], [x,v,v]]).
%estado_inicial([[v,v,v], [v,v,v], [o,x,o], [v,v,v]]).
%estado_inicial([[v,v,v], [v,v,v], [o,x,v], [v,v,v]]).
%estado_inicial([[v,v,v], [v,v,v], [o,v,v], [v,v,v]]).
estado_inicial([[v,v,v], [v,v,v], [v,v,v], [v,v,v]]).

%COLUNAS
% 1ª coluna
colunas([[X,X,X],_,_,_], X):- X \= v.
colunas([[X,X,X],_,_,_], X):- X \= v.
% 2ª coluna
colunas([_,[X,X,X],_,_], X):- X \= v.
colunas([_,[X,X,X],_,_], X):- X \= v.
% 3ª coluna
colunas([_,_,[X,X,X],_], X):- X \= v.
colunas([_,_,[X,X,X],_], X):- X \= v.
% 4ª coluna
colunas([_,_,_,[X,X,X]], X):- X \= v.
colunas([_,_,_,[X,X,X]], X):- X \= v.

%LINHAS
% 1ª linha
linhas([[X|_],[X|_],[X|_],_], X):- X \= v.
linhas([_,[X|_],[X|_],[X|_]], X):- X \= v.
% 2ª linha
linhas([[_,X|_],[_,X|_],[_,X|_],_], X):- X \= v.
linhas([_,[_,X|_],[_,X|_],[_,X|_]], X):- X \= v.
% 3ª linha
linhas([[_,_,X],[_,_,X],[_,_,X],_], X):- X \= v.
linhas([_,[_,_,X],[_,_,X],[_,_,X]], X):- X \= v.

%DIAGONAIS
% Diagonais Ascendentes
diagonais([[_,_,X],[_,X,_],[X,_,_],_], X):- X \= v.
diagonais([_,[_,_,X],[_,X,_],[X,_,_]], X):- X \= v.
% Diagonais Descendentes
diagonais([[X,_,_],[_,X,_],[_,_,X],_], X):- X \= v.
diagonais([_,[X,_,_],[_,X,_],[_,_,X]], X):- X \= v.

% Funcao de utilidade, retorna o valor dos estados terminais 1 ganha -1 perde
valor(F, 1):- linhas(F, x).
valor(F, 1):- colunas(F, x).
valor(F, 1):- diagonais(F, x).
valor(F, -1):- linhas(F, o).
valor(F, -1):- colunas(F, o).
valor(F, -1):- diagonais(F, o).
valor(_,0).

op1(E, J, joga(X,Y), En):-
    joga_vazio(E,J, X,Y, En).

% Predicado em que o tabuleiro ta cheio
cheio([C1, C2, C3, C4]):-
    append(C1, C2, C12),
    append(C3, C4, C34),
    append(C12, C34, FB),
    \+ member(v, FB).

terminal(F):- linhas(F,_).
terminal(F):- colunas(F,_).
terminal(F):- diagonais(F,_).
terminal(F):- cheio(F).


joga_vazio([[v,L2,L3],C2,C3,C4], J, 1,1, [[J,L2,L3],C2,C3,C4]):- L2\=v, L3\=v.
joga_vazio([[L1,v,L3],C2,C3,C4], J, 2,1, [[L1,J,L3],C2,C3,C4]):- L3\=v.
joga_vazio([[L1,L2,v],C2,C3,C4], J, 3,1, [[L1,L2,J],C2,C3,C4]).
joga_vazio([C1,[v,L2,L3],C3,C4], J, 1,2, [C1,[J,L2,L3],C3,C4]):- L2\=v, L3\=v.
joga_vazio([C1,[L1,v,L3],C3,C4], J, 2,2, [C1,[L1,J,L3],C3,C4]):- L3\=v.
joga_vazio([C1,[L1,L2,v],C3,C4], J, 3,2, [C1,[L1,L2,J],C3,C4]).
joga_vazio([C1,C2,[v,L2,L3],C4], J, 1,3, [C1,C2,[J,L2,L3],C4]):- L2\=v, L3\=v.
joga_vazio([C1,C2,[L1,v,L3],C4], J, 2,3, [C1,C2,[L1,J,L3],C4]):- L3\=v.
joga_vazio([C1,C2,[L1,L2,v],C4], J, 3,3, [C1,C2,[L1,L2,J],C4]).
joga_vazio([C1,C2,C3,[v,L2,L3]], J, 1,4, [C1,C2,C3,[J,L2,L3]]):- L2\=v, L3\=v.
joga_vazio([C1,C2,C3,[L1,v,L3]], J, 2,4, [C1,C2,C3,[L1,J,L3]]):- L3\=v.
joga_vazio([C1,C2,C3,[L1,L2,v]], J, 3,4, [C1,C2,C3,[L1,L2,J]]).

%------------------- Main Commands to play -----------------------------------------
joga_minimax :-  
	consult(minimax3linha),
	inicializarNos, 
	estado_inicial(Ei), 
	minimax_decidir(Ei,Op),
    write('Melhor Jogada para o estado: '), write(Ei), nl,
	write(Op),nl,
	retract(nos(N)), 
	write(N), nl. 


joga_alfabeta :-  
    consult(alfabeta),
    estado_inicial(Ei), 
    alfabeta(Ei,Op),
    write(Op),nl.
