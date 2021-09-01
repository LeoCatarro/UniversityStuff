%%%%%%%%%%%%%%%%%%%%%%%%%%% 1 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicado que regista as salas por onde o agente passou
:- dynamic(visitadas/1).

% indica o tamanho do tabuleiro
tamanho(7).

% verifica se a sala ja foi visitada, e caso nao tenha sido, marca como tal
nao_visitadas(X):-
    \+ visitadas(X),
    asserta(visitadas(X)).

% tuplo com as coordenadas onde o agente se encontra e a caixa
% estado_inicial((Xagente, Yagente), (Xcaixa, Ycaixa)
estado_inicial( ((2,1), (2,2)) ).

% estado visitado
visitadas((1,5)).

% tuplo com as coordenadas onde o agente pretende chegar
estado_final( ((_,_), (5,7)) ).

% portas bloqueadas entre salas
% bloqueada(sala_1, sala_2).
bloqueada((1,2),(1,3)).
bloqueada((1,4),(1,3)).
bloqueada((2,2),(2,3)).
bloqueada((2,4),(2,3)).
bloqueada((3,3),(2,3)).
bloqueada((1,1),(2,1)).
bloqueada((2,2),(2,1)).
bloqueada((3,1),(2,1)).
bloqueada((2,6),(2,7)).
bloqueada((1,7),(2,7)).
bloqueada((3,7),(2,7)).
bloqueada((4,3),(4,4)).
bloqueada((3,4),(4,4)).
bloqueada((4,5),(4,4)).
bloqueada((5,3),(5,4)).
bloqueada((5,5),(5,4)).
bloqueada((6,3),(6,4)).
bloqueada((7,4),(6,4)).
bloqueada((6,5),(6,4)).

% Regra que utiliza as operacoes que o agente pode usar
% op(estado_atual, operacao, estado_seguinte, custo).
op( ((Xa, Y), (Xc, Y)), direita, ((Wa, Y), (Wc, Y)), 1):-
    Xa =\= Xc-1 -> Xa is Xc-1,
    tamanho(T), Xc < T, 
    Wa is Xa+1, 
    Wc is Xc+1, 
    \+ bloqueada((Xa, Y), (Wa, Y)), 
    \+ bloqueada((Xc, Y), (Wc, Y)), 
    nao_visitadas((Wa, Y)), 
    nao_visitadas((Wc,Y)); fail.

op(((X, Ya), (X, Yc)), cima, ((X, Za), (X, Zc)), 1):- 
    Ya =\= Yc-1 -> Ya is Yc-1, 
    tamanho(T), 
    Yc < T, 
    Za is Ya+1, 
    Zc is Yc+1, 
    \+ bloqueada((X, Ya), (X,Za)), 
    \+ bloqueada((X, Yc), (X,Zc)), 
    nao_visitadas((X,Za)), 
    nao_visitadas((X,Zc)); fail.

op(((X, Ya), (X, Yc)), baixo, ((X, Za), (X, Zc)), 1):-
    Ya =\= Yc+1 -> Ya is Yc+1, 
    Yc > 1, 
    Za is Ya-1, 
    Zc is Yc-1, 
    \+ bloqueada((X, Ya), (X,Za)), 
    \+ bloqueada((X, Yc), (X,Zc)), 
    nao_visitadas((X,Za)), 
    nao_visitadas((X,Zc)); fail.

op( ((Xa, Y), (Xc, Y)), esquerda, ((Wa, Y), (Wc, Y)), 1):-
    Xa =\= Xc+1 -> Xa is Xc+1, 
    Xc > 1, Wa is Xa-1, 
    Wc is Xc-1, 
    \+ bloqueada((Xa, Y), (Wa, Y)), 
    \+ bloqueada((Xc, Y), (Wc, Y)), 
    nao_visitadas((Wa, Y)), 
    nao_visitadas((Wc, Y)); fail.

% Predicado que servirá para contar os estados visitados e em memória
:- dynamic(max_em_memoria/1).
:- dynamic(visitados/1).

% Pesquisa em Profundidade
pesquisa_profundidade([no(E, Pai,Op,C,P)|_],no(E, Pai, Op, C, P)) :- 
    retract(visitados(X)),
    Y is X + 1,
    asserta(visitados(Y)), 
	estado_final(E).

pesquisa_profundidade([E|R],Sol):- 
	expande(E,Lseg),
    insere_inicio(Lseg,R,LFinal),
    retract(max_em_memoria(X)),
    length(LFinal, L),
    max(X, L, Z),
    asserta(max_em_memoria(Z)),
    pesquisa_profundidade(LFinal,Sol).

expande(no(E,Pai,Op,C,P),L):- 
	findall(no(En,no(E,Pai,Op,C,P), Opn, Cnn, P1),
                (op(E,Opn,En,Cn), P1 is P+1, Cnn is Cn+C),
                L).

pesquisa_p:-
    asserta(max_em_memoria(0)),
    asserta(visitados(0)),
    estado_inicial(S0),
	pesquisa_profundidade([no(S0,[],[],0,0)], S),
    write(S), nl,
    write('Visitados: ' ),
    retract(visitados(Y)),
    write(Y), nl,
    write('Em Memoria: ' ).
    %retract(max_em_memoria(X)),
    %write(X).
    
insere_inicio(A,B,C) :- append(A, B, C).
insere_fim(A,B,C) :- append(B, A, C).

% Funcao que retorna o maximo
max(A, A, A).
max(A, B, C):-
    A > B,
    C is A.
max(A, B, C):-
    A < B,
    C is B.

%%%%%%%%%%%%%%%%%%%%% 2.1 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Heuristica admissivel
heur((X, Y), R):- 
    estado_final(PF),
    distancia((X, Y), PF, R).

%Calcula a distancia
distancia((X, Y), (W, Z), D):-
    X1 is X-W,
    abs(X1, AX),
    Y1 is Y-Z,
    abs(Y1, AY),
    D is AX+AY.

abs(X, X):- X > 0.
abs(X, R):- R is -X.


% Heuristica admissivel(Distância Euclidiana)
heur2(P1, D) :- estado_final(PF),  
                euclidean_distance(P1, PF, D).

euclidean_distance((X1, Y1), (X2, Y2), R) :- R is sqrt((X2-X1)^2 + (Y2-Y1)^2).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Pesquia A*
%representacao dos nos
%no(Estado,no_pai,Operador,Custo,CustoHeuristica,Profundidade)

pesquisa_aux([no(E,Pai,Op,C,CH,P)|_],no(E,Pai,Op,C,CH,P)) :- 
    retract(visitados(X)),
    Y is X + 1,
    asserta(visitados(Y)), 
	estado_final(E).
pesquisa_aux([E|R],Sol):- 
	expandeInformada(E,Lseg),
    insere_ordenado(Lseg,R,LFinal),
    retract(max_em_memoria(X)),
    length(LFinal, L),
    max(X, L, Z),
    asserta(max_em_memoria(Z)),
    pesquisa_aux(LFinal,Sol).

expandeInformada(no(E,Pai,Op,C,CH,P),L):- 
	findall(no(En,no(E,Pai,Op,C,CH,P), Opn, Cnn, CHn, P1),
                (op(E,Opn,En,Cn), P1 is P+1, Cnn is Cn+C, heur(En, H), CHn is Cnn + H),
                L).

pesquisa_a:-
    asserta(max_em_memoria(0)),
    asserta(visitados(0)),
	estado_inicial(S0),
	pesquisa_aux([no(S0,[],[],0,0,0)], S),
	write(S), nl,
    write('Visitados: ' ),
    retract(visitados(Y)),
    write(Y), nl,
    write('Em Memoria: ' ),
    retract(max_em_memoria(X)),
    write(X).

ins_ord(E, [], [E]).
ins_ord(no(E,Pai,Op,C,CH,P), [no(E1,Pai1,Op1,C1,CH1,P1)|T], [no(E,Pai,Op,C,CH,P),no(E1,Pai1,Op1,C1,CH1,P1)|T]) :- CH =< CH1.
ins_ord(no(E,Pai,Op,C,CH,P), [no(E1,Pai1,Op1,C1,CH1,P1)|T], [no(E1,Pai1,Op1,C1,CH1,P1)|T1]) :-
	ins_ord(no(E,Pai,Op,C,CH,P), T, T1).	

insere_ordenado([],L,L).
insere_ordenado([A|T], L, LF):- 
	ins_ord(A,L,L1),
	insere_ordenado(T, L1, LF).
