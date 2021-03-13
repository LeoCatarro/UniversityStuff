/*  
    Base de Dados   
*/
/*1) Base de dados*/
homem('Afonso Henriques','rei de Portugal',1109).
homem('Henrique de Borgonha','conde de Portugal',1069).

homem('Sancho I','rei de Portugal',1154).
homem('Fernando II','rei de Leao',1137).
homem('Afonso IX', 'rei de Leao e Castela', 1171).
homem('Afonso II', 'rei de Portugal',1185).

homem('Sancho II', 'rei de Portugal',1207).
homem('Afonso III', 'rei de Portugal',1210).


mulher('Teresa de Castela', 'condessa de Portugal', 1080).
mulher('Mafalda', 'condessa de Saboia', 1125).
mulher('Urraca', 'infanta de Portugal',1151).
mulher('Dulce de Barcelona','infanta de Aragao',1160).
mulher('Berengaria', 'infanta de Portugal',1194).
mulher('Urraca C','infanta de Castela',1186).


filho('Afonso Henriques','Henrique de Borgonha').
filho('Afonso Henriques','Teresa de Castela').
filho('Urraca','Afonso Henriques').
filho('Sancho I','Afonso Henriques').
filho('Urraca','Mafalda').
filho('Sancho I','Mafalda').
filho('Afonso IX','Urraca').
filho('Afonso IX','Fernando II').
filho('Afonso II','Sancho I').
filho('Afonso II','Dulce de Barcelona').
filho('Berengaria','Sancho I').
filho('Berengaria','Dulce de Barcelona').
filho('Sancho II','Afonso II').
filho('Afonso III','Afonso II').
filho('Sancho II','Urraca C').
filho('Afonso III','Urraca C').


/*  
    Predicados   
*/
/*2) Defina o predicado irmao(Nome1, Nome2) e Nome1 != Nome2*/
irmao(N1, N2) :- filho(N1, PaiOuMae), filho(N2, PaiOuMae), N1 \= N2.


/*3) Defina o pedricado primoDireito(Nome1, Nome2)*/
primoDireito(X,Y) :- filho(X,P), filho(Y,V), irmao(P,V), X\=Y, !. 


/*4) Defina o predicado irmaos(Nome1, Lista)*/
irmaos(N1, L1) :- findall(N2, irmao(N1, N2), L), sort(L, L1).


/*5) Defina o predicado primos(N1, Lista)*/
primos(N1, N2) :- primoDireito(N1, N2).
primos(N1,N2) :- primoDireito(N1, Z), filho(N2, Z).
primos(N1, N2) :- filho(N1, P), filho(N2,V), primos(P,V).


/*6) Defina o predicado: esposa(nomeMulher,nomeHomem)*/
esposa(M, H) :- mulher(M,_,_), filho(F, H), filho(F, M), M\=H.


/*7) Defina o predicado: descende(nome,-[ascendentes])*/
progenitor(X, Y) :- homem(X,_,_), filho(Y, X).
progenitor(X, Y) :- mulher(X,_,_), filho(Y, X).

descendeAux(X, Y) :- progenitor(X, Y).
descendeAux(X, Y) :- progenitor(Z, X), descendeAux(Z, Y).

descende(X, L) :- findall(Y, descendeAux(X,Y), L1), sort(L1, L).


/*8) Defina o predicado: descendentes(nome,-[descendentes])*/

descendentesAux(X, Y) :- filho(X, Y).
descendentesAux(X, Y) :- filho(Z, X), descendentesAux(Z, Y).

descendentes(X, L) :- findall(Y, descendentesAux(X,Y), L1), sort(L1, L).


/*9) Defina o predicado: ascendentes(nome, c(Nome,AscendentesPai,AscendentesMae))*/


/*10) Defina o predicado: descendentes(Nome,c(Nome,NomeEsposa,-[DescendentesFilho*]))*/


/*11) Defina o predicado: descendentesNivel(Nome, Nivel, [Nomes])*/


/*12) Defina o predicado: ascendentesNivel(Nome, Nivel, [Nomes])*/


/*13) Defina o predicado: ordenaNomesAlf(+lista,-listaordenada)*/
/*Usando isertion sort*/
isort(I, S) :- isort(I, [], S).
isort([], S, S).
isort([X|Xs], SI, SO) :- insord(X, SI, SX), isort(Xs, SX, SO).

insord(X, [], [X]).
insord(X, [A|As], [X,A|As]) :- compare(<, X, A).
insord(X, [A|As], [A|AAs]) :- \+compare(<, X, A), insord(X, As, AAs).

ordenaNomesAlf(In, Out) :- isort(In, Out).


/*14) Defina o predicado: ordenaNomesIdade(+lista,-listaordenada)*/
/* Nota: Onde vou buscar a idade?*/


/*15) Complete a base de dados incluindo informacao ate ao fim da primeira dinastiaD Joao I, mestre de Aviz.*/
/* Nota: Não colocado!*/


/*16) Defina o predicado troca(+lista,p1,p2,-listaTrocada), que dada uma lista,constroi a listaTrocada que tem os mesmo elementos da lista excepto os elementos que estao nas posicoes p1 e p2 que devem trocar de posicao.*/