% decide qual é a melhor jogada num estado do jogo
% alfabeta(Estado, MelhorJogada)

% se é estado terminal não há jogada 
alfabeta(Ei,terminou) :- terminal(Ei).

% Nota: assume que o jogador é o "x"
alfabeta(Ei,Opf) :- 
	findall(Vc-Op, (oper(Ei,x,Op,Es), alfabeta_min(Es,Vc,1,-10000,10000)), L),
	escolhe_max(L,Opf).

% se um estado é terminal o valor é dado pela função de utilidade
% Nota: assume que o jogador é o "x"
alfabeta_min(Ei,Val,_,_,_) :- 
	terminal(Ei), 
	valor(Ei,Val), !.

alfabeta_min(Ei,Val,P,Alfa,Beta) :- 
	P1 is P+1, jogador(P1,J),
	V is 10000,
	findall(Es, oper(Ei,J,_,Es), L),
	processa_lista_min(L, P1, V, Alfa, Beta, Val), !.

processa_lista_min([], _, V, _, _, V).
processa_lista_min([H|T], P, V, A, B, V1) :-
	alfabeta_max(H, V2, P, -10000, 10000),
	min(V, V2, V3),
	(V3 < A, V1 is V3; min(B, V3, B1), processa_lista_min(T, P, V3, A, B1, V1)).

min(A,B,A) :- A < B, !.
min(_, B, B).

alfabeta_max(Ei,Val,_,_,_) :- 
	terminal(Ei), 
	valor(Ei,Val), !.

alfabeta_max(Ei,Val,P,Alfa,Beta) :- 
	P1 is P+1, jogador(P1,J),
	V is -10000,
	findall(Es, oper(Ei,J,_,Es), L),
	processa_lista_max(L, P1, V, Alfa, Beta, Val), !.

processa_lista_max([], _, V, _, _, V).
processa_lista_max([H|T], P, V, A, B, V1) :-
	alfabeta_min(H, V2, P, -10000, 10000),
	max(V, V2, V3),
	(V3 >= B, V1 is V3; max(A, V3, A1), processa_lista_max(T, P, V3, A1, B, V1)).

max(A,B,B) :- A < B, !.
max(A, _, A).

% jogador "x" nas jogadas impares e jogador "o" nas jogadas pares
jogador(P, o) :- X is P mod 2, X = 0.
jogador(P, x) :- X is P mod 2, X = 1.

% Se a profundidade (P) é par, retorna em Val o maximo de V
seleciona_valor(V,P,Val) :- 
	X is P mod 2, X=0,!, 
	maximo(V,Val).

% Senão retorna em Val o minimo de V
seleciona_valor(V,_,Val):- minimo(V,Val).

%% Predicados auxiliares

escolhe_max([A|R],Val):- escolhe_max(R,A,Val).

escolhe_max([],_-Op,Op).
escolhe_max([A-_|R],X-Op,Val) :- A < X,!, escolhe_max(R,X-Op,Val).
escolhe_max([A|R],_,Val):- escolhe_max(R,A,Val).


maximo([A|R],Val):- maximo(R,A,Val).

maximo([],A,A).
maximo([A|R],X,Val):- A < X,!, maximo(R,X,Val).
maximo([A|R],_,Val):- maximo(R,A,Val).

minimo([A|R],Val):- minimo(R,A,Val).

minimo([],A,A).
minimo([A|R],X,Val):- A > X,!, minimo(R,X,Val).
minimo([A|R],_,Val):- minimo(R,A,Val).