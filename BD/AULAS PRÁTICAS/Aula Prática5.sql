--Criação das Tabelas(Aula Prática 5-BD)

Create table livros (
    ISBN char(3) primary key;
    Titulo varchar(55)
);

Create table autor (
    CodigoAutor char(3) primary key;
    Nome varchar(25),
    Morada varchar(100),
    Cidade varchar(30),
    Telefone int(9)
);

Create table autoria (
    CodigoAutor char(3),
    ISBN char(3),
    primary key (CodigoAutor,ISBN),    
);    
    
--CRIAR O RESTO DAS TABELAS    