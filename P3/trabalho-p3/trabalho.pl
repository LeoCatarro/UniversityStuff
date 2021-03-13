/*Tamanho da lista*/
listlen([],0).
listlen([_|T], Len) :- listlen(T, Y), Len is Y+1.

/*Predicado para comparar listas*/
cmplists([],[]).
cmplists([H|T], [H1|T1]) :- H=H1, cmplists(T,T1).

cmpTupleList([], []).
cmpTupleList((L,H), (L1,H1)) :- L\=L1, cmplists(H, H1).

/*
secondpostuplelen(T, Len):
    T-->tuplo
    Len-->Variável para guardar o tamanho da lista na 2a posição do tuplo T

    O que faz?
        -Este predicado recebe um tuplo e guarda na variável Len, o tamanho da lista representativa do simbolo presente na primeira posição do tuplo
*/
secondpostuplelen((_,X), Len) :- listlen(X, Len).

/*
listCodeCopy(L1,L2):
    L1 --> Lista Input
    L2 -->  Lista de Output, com códigos dos simbolos
    
    O que faz?
        -Este predicado, adaptado a tuplos, itera a lista L1, e copia a lista representativa do simbolo para L2. Obtendo assim L2, com apenas os códigos dos simbolos
*/
listCodeCopy([], []).
listCodeCopy([(_,H)|T1], [H|T2]) :- listCodeCopy(T1,T2).

/*
symbolCopy(L1,L2):
    L1 --> Lista Input
    L2 -->  Lista de Output, com os simbolos

    O que faz?
        -Este predicado recebe 2 listas e copia os simbolos da lista de input, colocando-os na de output
*/    
symbolCopy([], []).
symbolCopy([(H,_)|T1], [H|T2]) :- symbolCopy(T1,T2).

/*
unite(A1, A2, A3):
    A1--> simbolo
    A2--> código

    O que faz?
        - Este predicado recebe em A1, 1 simbolo e em A2 um código, pondo em A3, o correspondente tuplo (A1, A2).
*/
unite([], [], []).
unite(L1, L2, (L1,L2)).

/*
cmptuple(X, Y, X):
    X--> tuplo
    Y--> tuplo

    O que faz?
        -Compara o tamanho das segundas posições de 2 tuplos e retorna tuplo cujo lista representativa do simbolo é menor
*/
cmptuple(X, Y, X) :- secondpostuplelen(X, Xlen), secondpostuplelen(Y, Ylen), Xlen<Ylen. 

/*
isort(Input, Output):
    Input, Output --> listas

    O que faz?   
        -Sort da lista de input de acordo com o tamanho das segundas posições dos tuplos de cada posição da lista
        Nota: Usado o algoritmo isort() que consta nos slides, embora alterado para se adaptar ao dados de input(lista de tuplos)

        Insere á cabeça caso o comprimento da lista da segunda posição de cada tuplo seja menor que a anterior
        Caso contrário, insere na cauda
*/
isort(I, S) :- isort(I, [], S).     
isort([], S, S).
isort([X|Xs], SI, SO) :- insord(X, SI, SX), isort(Xs, SX, SO).

insord(X, [], [X]).
insord(X, [A|As], [X,A|As]) :- cmptuple(X,A,X).     
insord(X, [A|As], [A|AAs]) :- \+cmptuple(X,A,X), insord(X, As, AAs).

/*
perm(N,Set,L, Output, SymbolList,FlatList, God):

    O que faz?
        - Permutações possiveis da lista Set, com N simbolos.
*/
eval([],_).
eval([H|T],Set):- member(H,Set), eval(T,Set).

perm(N,Set,L, CodesList, SymbolList, CodeFlatList, TupleOfLists):- 
    length(L,N),
    eval(L,Set),
    listsCopyAndUnite(L, CodesList, SymbolList, CodeFlatList, TupleOfLists).
   
/*
listsCopyAndUnite

    O que faz?
        -Este predicado "cria" as SymbolList(lista que apenas contem simbolos), FlatList(lista criada apartir de CodeList(lista de listas), sendo lhe aplicado flatten())
        e finalmente une SymbolList e CodeFlatList em um tuplo: (SymbolList, CodeFlatList), colocando-o em TupleOfLists.
*/
listsCopyAndUnite(L, CodesList, SymbolList, CodeFlatList, TupleOfLists) :-
    listCodeCopy(L,CodesList),
    symbolCopy(L, SymbolList),
    flatten(CodesList, CodeFlatList),
    unite(SymbolList, CodeFlatList, TupleOfLists).
    
/*
loop(From, To, Index):
    -From --> Partida
    -To --> Limite
    -Index --> Posição atual

    O que faz este predicado?
        Este predicado basicamente simula um ciclo for.
*/
loop(From,_,From).
loop(From, To, X):-
    From<To,
    Next is From+1,
    loop(Next, To, X).

/*
allPerms(Start, End,Set,L, CodesList, SymbolList, CodeFlatList, TupleOfLists):
    -Start --> inicio do ciclo
    -End --> final do ciclo
    -Set -->Lista de tuplos de Input
    -L -->Output

    O que faz este predicado?
        Este predicado, inicialmente ordena a lista de input, através do predicado isort() pelos tamanhos dos códigos representativos dos simbolos 
        De seguida executa o predicato perm() tantas vezes quantas forem indicadas no loop()
        Será mostrado a cada iteração completa as listas de combinaçoes possíveis com tamanho Index.
        O predicado mostrará as listas de combinaçoes possiveis desde o tamanho "Start" até "End"       
*/
allPerms(Start, End, Set, L, CodesList, SymbolList, CodeFlatList, TupleOfLists):-
    isort(Set, Sortedset),
    loop(Start, End, Index),
    perm(Index, Sortedset, L, CodesList, SymbolList, CodeFlatList, TupleOfLists).

/*
iterFindRepeat e interFindRepeat2:
    iterFindRepeat2 --> primeiro ciclo for
    iterFindRepeat --> segundo ciclo for

    O que faz?
        -Simulação de um Nested For, para procurar códigos duplicados na lista. 
        
*/
iterFindRepeat([], [], []).
iterFindRepeat(X, [H|_], Out) :- cmpTupleList(X,H), append([], [H,X], Out), !.
iterFindRepeat(X, [_|T], Out) :- iterFindRepeat(X, T, Out).

iterFindRepeat2([], [], []).
iterFindRepeat2([H|_], L, Out) :- iterFindRepeat(H, L, Out). 
iterFindRepeat2([_|T], L, Out) :- iterFindRepeat2(T, L, Out).

/*
findDuplicate:

    O que faz?
        -retorna uma lista com os simbolos ou palavras ambiguas em uma lista no formato [(S1, C1), (S2, C1)], em que:
            S1 e S2 --> simbolo ou lista de simbolos
            C1 --> código ambiguo, que representa S1 e S2.
*/
findDuplicate(Start, End, Set, L, CodesList, SymbolList, CodeFlatList, TupleOfLists, Out, Out2) :-
    findall(TupleOfLists, allPerms(Start, End, Set, L, CodesList, SymbolList, CodeFlatList, TupleOfLists), Out),
    iterFindRepeat2(Out, Out, Out2).

/*
FINAL PREDICATE

    ambiguo(Input, M, T1, T2):
        Input --> lista de input
        M --> código ambiuo
        T1 e T2 --> simbolo ou listas de simbolos ambiguos

        O que faz?
            - retorna em M, a mensagem codificada e em T1 e T2 duas das suas possíveis interpretações
*/
ambiguo(Input, M, T1, T2):-
    findDuplicate(1,4, Input, _, _, _, _, _,_, Out2),
    Out2 = [(H1,H2), (H3,H2)],
    append([], H2, M),
    append([], H3, T1),
    append([], H1, T2), !.

   
/*  ###################################
    #   TESTES FORNECIDOS PELO PROF   #
    ###################################
    1ºExemplo:
        ambiguo( [(a, [0,1,0]), (c, [0,1]), (j, [0,0,1]), (l, [1,0]), (p, [0]), (s, [1]), (v, [1,0,1])], M, T1, T2). 

        Output:
            M = [0,1]
            T1 = [c]
            T2 = [p,s]

    2ºExemplo:
        ambiguo( [(a, [0,1,1,0]), (b, [0,1,1,1,1,1]), (c, [1,1,0,0,1,1,1,1]), (f, [1,0,1,1,1,0]), (j, [0,1,0]), (l, [0,1,0,0]), (r, [0,1,1,1,0])], M , T1, T2).

        Output:
            M = [0,1,0,0,1,1,0,0,1,1,1,1,1,0,1,1,1,0]
            T1 = [l,c,f]
            T2 = [j,a,b,r]
*/
