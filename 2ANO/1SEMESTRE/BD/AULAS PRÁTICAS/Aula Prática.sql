Create table biblioteca ( 
 IdBib char(3) primary key, 
 NomeB varchar(15), 
 Cidade varchar(10)
);

insert into biblioteca values ('b01','Caliope', 'Beja');
insert into biblioteca values ('b02','Clio', 'Beja');
insert into biblioteca values ('b03','Erato', 'Braga');
insert into biblioteca values ('b04','Euterpe', 'Braga');
insert into biblioteca values ('b05','Melpomene', 'Evora');
insert into biblioteca values ('b06','Polimnia', 'Evora');
insert into biblioteca values ('b07','Talia', 'Montemor');


Create table autor1(
 CodA  char(3) primary key, 
 NomeA varchar(25),  
 NBiA integer check(NBiA > 0),
 PaisA  varchar(15),
 CidadeA varchar(15)
);

insert into autor1 values ('a01','Eca de Queiros', 123,'Portugal','Povoa de Varzim');
insert into autor1 values ('a02','Jose Saramago', 124,'Portugal','Goulega');


Create table livro(
 ISBN char(9) primary key,
 Titulo varchar(55)
);

insert into livro values ('123', 'O misterio da estrada de Sintra');
insert into livro values ('124', 'O Crime do Padre Amaro');
insert into livro values ('125', 'A Tragedia da Rua das Flores');
insert into livro values ('126', 'O Primo Basilio');

insert into livro values ('127', 'Memorial do Convento');
insert into livro values ('128', 'O Ano da Morte de Ricardo Reis');
insert into livro values ('129', 'A Jangada de Pedra');
insert into livro values ('130', 'Historia do Cerco de Lisboa');
insert into livro values ('131', 'O Evangelho Segundo Jesus Cristo');


Create table socio(
NumS  char(3)  primary key,
NomeS  varchar(25),  
NBIS integer check(NBiS > 0),
CidadeS varchar(10)
);

insert into socio values ('s01','Maria', 125,'Beja');
insert into socio values ('s02','Mariana', 126,'Beja');
insert into socio values ('s03','Manuel', 127,'Evora');
insert into socio values ('s04','Joaquim', 128,'Evora');
insert into socio values ('s05','Fernando', 129,'Braga');
insert into socio values ('s06','Filipe', 130,'Braga');
insert into socio values ('s07','Nuno', 131,'Montemor');
insert into socio values ('s08','Cristina', 132,'Montemor');
insert into socio values ('s09','Ana', 133,'Montemor');


Create table exemplar(
IdLivro char(9) primary key,
ISBN  char(9),
IdBib char(3),
foreign key (ISBN) references livro on  delete restrict,
foreign key (IdBib) references biblioteca on  delete restrict
);

insert into exemplar values ('001','123','b01');
insert into exemplar values ('002','123','b01');
insert into exemplar values ('003','123','b01');
insert into exemplar values ('004','123','b02');
insert into exemplar values ('005','123','b03');
insert into exemplar values ('006','123','b04');
insert into exemplar values ('007','123','b05');
insert into exemplar values ('008','123','b06');
insert into exemplar values ('009','123','b07');

insert into exemplar values ('010','124','b01');
insert into exemplar values ('011','124','b01');
insert into exemplar values ('012','124','b02');
insert into exemplar values ('013','124','b03');
insert into exemplar values ('014','124','b04');

insert into exemplar values ('015','125','b01');
insert into exemplar values ('016','125','b01');
insert into exemplar values ('017','125','b05');
insert into exemplar values ('018','125','b06');
insert into exemplar values ('019','125','b07');

insert into exemplar values ('020','126','b01');
insert into exemplar values ('021','126','b01');
insert into exemplar values ('022','126','b01');
insert into exemplar values ('023','126','b02');
insert into exemplar values ('024','126','b03');
insert into exemplar values ('025','126','b04');
insert into exemplar values ('026','126','b05');
insert into exemplar values ('027','126','b06');
insert into exemplar values ('028','126','b07');

insert into exemplar values ('029','127','b01');
insert into exemplar values ('030','127','b03');
insert into exemplar values ('031','127','b04');
insert into exemplar values ('032','127','b02');

insert into exemplar values ('033','128','b01');
insert into exemplar values ('034','128','b03');
insert into exemplar values ('035','128','b04');
insert into exemplar values ('036','128','b02');

insert into exemplar values ('041','129','b01');
insert into exemplar values ('042','129','b03');

insert into exemplar values ('037','130','b01');
insert into exemplar values ('038','130','b03');

insert into exemplar values ('039','131','b01');
insert into exemplar values ('043','131','b01');
insert into exemplar values ('040','131','b03');


Create table Autoria(
ISBN char(9),
CodA char(3),
primary key (ISBN,CodA),
foreign key (ISBN) references livro on  delete restrict,
foreign key (CodA) references autor1 on  delete restrict
);

insert into autoria values ('123','a01');
insert into autoria values ('124','a01');
insert into autoria values ('125','a01');
insert into autoria values ('126','a01');

insert into autoria values ('127','a02');
insert into autoria values ('128','a02');
insert into autoria values ('129','a02');
insert into autoria values ('130','a02');
insert into autoria values ('131','a02');



Create table requisitou(
NumS char(3),
IdLivro char(9),
Data integer,
primary key (NumS,IdLivro, Data),
foreign key (NumS) references socio on  delete restrict,
foreign key (IdLivro) references exemplar on  delete restrict
);

insert into requisitou values ('s01','001',1);
insert into requisitou values ('s03','002',2);
insert into requisitou values ('s01','010',3);
insert into requisitou values ('s04','011',4);
insert into requisitou values ('s02','038',5);



Create table entregou(
NumS char(3),
IdLivro char(9),
Data integer,
primary key (NumS,IdLivro, Data),
foreign key (NumS) references socio on  delete restrict,
foreign key (IdLivro) references exemplar on  delete restrict
);

insert into entregou values ('s01','001',4);
insert into entregou values ('s04','011',8);


--Ficha 4
--Exercicio 1a)

select NomeS,NumS
from socio
where CidadeS like 'Beja';

--1b)

select NomeA
from autor1, livro, autoria
where Titulo like 'Historia do Cerco de Lisboa' and livro.ISBN = autoria.ISBN and autor1.CodA = autoria.CodA;

--1.c)

select distinct NomeA
from autor1, exemplar, autoria, biblioteca
where Cidade like 'Evora' and autoria.CodA = autor1.codA and autoria.ISBN = exemplar.ISBN and exemplar.IdBib = biblioteca.IdBib;

--1.d)

select distinct Titulo
from livro, exemplar, biblioteca
where biblioteca.IdBib = exemplar.IdBib and livro.ISBN = exemplar.ISBN
except
select distinct Titulo
from livro, exemplar, biblioteca
where Cidade like 'Evora' and exemplar.IdBib = biblioteca.IdBib and livro.ISBN = exemplar.ISBN;

--1.e)

select distinct NomeS, requisitou.NumS
from socio, requisitou, autor1, exemplar, autoria
where NomeA like 'Eca de Queiros' and socio.NumS = requisitou.NumS and requisitou.IdLivro = exemplar.IdLivro and autor1.CodA = autoria.CodA and autoria.ISBN = exemplar.ISBN;

--PERGUNTA ADICIONADA: Quantos exemplares tem a biblioteca de Beja?

select count(IdLivro)as Nex
from exemplar. biblioteca
where Cidade like 'Beja' and 