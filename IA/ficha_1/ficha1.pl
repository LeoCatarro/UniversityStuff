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