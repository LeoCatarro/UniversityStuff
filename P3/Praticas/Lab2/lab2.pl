/*1) Considere o predicado minimo/3, que verifica se o terceiro argumento é o mínimo entre os dois primeiros
a) Problema: o "=", caso eles sejam iguais terá valor de retorno!*/
minimo(X,Y,X) :- X < Y.
minimo(X,Y,Y) :- Y < X.


/*2) Os predicados member/2 e memberchk/2 determinam se uma lista contém um determinado termo. Proponha implementações para estes predicados (chamando-lhes nomes diferentes, de forma a não fazer conflito com as versões nativas do interpretador).*/
membro(X, [X|_]).
membro(X, [_|L]) :- membro(X,L). 

/*    ! -> usado para quando achar o termo uma segunda vez, parar    */
membrocheck(X, [X|_]) :- !.
membrocheck(X, [_|L]) :- membrocheck(X,L), !.


/*3) Proponha uma implementação para um predicado nonmember/2, que faz o inverso dos anteriores.*/
nomember(X, [X|_]).
nomember(X, [_|L]) :- nomember(X,L).


/*4) O predicado is/2 é usado para fazer cálculos aritméticos:*/
    /*a) Verifique (e perceba) quais das seguintes queries são ou não válidas
        ?- X is 3+5.            -->VÁLIDA (X=8)
        ?- 8 is 3+5.            -->VÁLIDA (yes)
        ?- (3+5) is (3+5).      -->INVÁLIDA (no)
        ?- X is 3+a.            -->INVÁLIDA (error)
        ?- X is 3+Y.            -->INVÁLIDA (error)
        ?- Y=1, X is 3+Y.       -->VÁLIDA (Y=1 X=4)
*/

/*b) Implemente um predicado factorial/2 que sucede quando o segundo argumento é o factorial do primeiro*/
factorial(X,Y) :-