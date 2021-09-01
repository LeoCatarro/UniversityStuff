%
% Estado inicial
%
estado_inicial(e(7,5,3,1)).

%
% Estado terminal
%
terminal(e(0,0,0,0)).

%
% Operações de transição
%
%Retira N peças da 1ªColuna(1º indice da lista de cada estado)
op1(e(N1,N2,N3,N4), J, retira(1, N), e(N11,N2,N3,N4)) :-
    N11 is N1-N,
    N11 >= 0,
    inv(J,J1).
%Retira N peças da 2ªColuna(2º indice da lista de cada estado)
op1(e(N1,N2,N3,N4), J, retira(2, N), e(N1,N22,N3,N4)) :- 
    N22 is N2-N,
    N22 >= 0,
    inv(J,J1).
%Retira N peças da 3ªColuna(3º indice da lista de cada estado)
op1(e(N1,N2,N3,N4), J, retira(3, N), e(N1,N2,N33,N4)) :- 
    N33 is N3-N,
    N33 >= 0,
    inv(J,J1).
%Retira N peças da 4ªColuna(4º indice da lista de cada estado)
op1(e(N1,N2,N3,N4), J, retira(4, N), e(N1,N2,N3,N44)) :- 
    N44 is N4-N,
    N44 >= 0,
    inv(J,J1).

%
% Funcao utilidade(1 se primeiro jogador ganha ; -1 se perde)
%
valor2(X, Res) :- X='x' -> Res is 1 ; Res is -1.
printValor(X) :- X=1 -> write('Jogador Ganhou!') ; write('Jogador Adversário Ganhou!').

%
% Passa a jogada ao proximo jogador(Se a jogada corrente tiver sido feito pelo jogador 'a' a proxima será do jogador 'b' e vice-versa)
%
inv(x, y).
inv(y, x).

%função de utilidade, retorna o valor dos estados terminais, 1 ganha -1 perde
valor(e(0,0,0,0),1,P):- 
    X is P mod 2, 
    X=0,!.
valor(e(0,0,0,0),-1,_).

%
%Ciclo de jogadas
%
ciclo_jogo(e(0,0,0,0),_,_,_,_) :- valor2(J, Res), printValor(Res), nl.
ciclo_jogo(E,_,_,_, J) :-
    write('Coluna a tirar peças: '), nl,
    read(Coluna),
    write('Quantas peças tirar na coluna '), write(Coluna), write(':'), nl,
    read(Npecas),
    op1(E, J, retira(Coluna, Npecas), Es),
    write(Es), nl,
    ciclo_jogo(Es, _,_,_,J).

%-----------------------------------------------%
%           Comando para jogar o nim            %
%-----------------------------------------------%
joga :-
    estado_inicial(Ei),
    write(Ei), nl,
    ciclo_jogo(Ei,_,_,_,x),
    write('Game Over!').


joga_minimax :-
    consult(minimaxNim),
	estado_inicial(Ei), 
	minimax_decidir(Ei,Op),
	write(Op),nl,
	write(N), nl. 