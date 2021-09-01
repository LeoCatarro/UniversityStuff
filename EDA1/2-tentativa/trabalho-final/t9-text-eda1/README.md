# Serviço de Mensagens Curtas Inteligente - Trabalho Estrutura de Dados e Algoritmos I (2020/2021)

## 1 Objetivo
Desenvolver uma aplicação que implementa a tecnologia de escrita de mensagens curtas in-
teligente T9 1 (Text on 9 Keys).
Esta tecnologia permite escrever palavras com as teclas numéricas dos telemóveis: cada
tecla corresponde a 3 ou 4 letras do alfabeto e a utilização de um dicionário permite introduzir
uma palavra através dos algarismos correspondentes a cada uma das suas letras.


## 2 Descrição do trabalho
A aplicação a desenvolver permite escrever a mensagem palavra a palavra até a indicação de
fim de mensagem. utilizando a tecnologia T9, a aplicação recebe os algarismos da palavra
pretendida e sugere uma palavra; se essa palavra não for a desejada, sugere outras. Caso não
existam mais sugestões, a palavra é introduzida pelo teclado e adicionada ao dicionário.
Para simplificar o trabalho, as alterações ao dicionário não são mantidas para posteriores
utilizações da aplicação e não são introduzidos sinais de pontuação nem espaços utilizando,
na escrita das palavras, apenas as teclas 2 a 9. A palavra 1 (um) corresponde à conclusão da
mensagem e a palavra 0 (zero) ao fim do programa.


## 3 Funcionalidades
O trabalho deve funcionar com dicionários de diferentes lı́nguas e com diferentes
tamanhos. Se o ficheiro dicionario.txt for dicionário a utilizar, a aplicação deve ser
executada através do comando
$ MCurtas dicionario.txt
A aplicação deve implementar as seguintes funcionalidades:
1. Carregar o dicionário no inı́cio e indicar quanto tempo demorou o processo.
2. Apresentar uma tabela com a informação para o utilizador digitar os algarismos corre-
spondentes às letras:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2: a b c á à â ã ç
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 3: d e f é ê 
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 4: g h i í
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5: j k l
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 6: m n o ó ô õ
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 7: p q r s
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 8: t u v ú
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 9: w x y z

3. Após a escrita da sequência de algarismos é sugerida uma palavra que o utilizador
aceita ou não. Se não aceitar, devem ser sugeridas mais palavras até se esgotarem. Se
não existirem mais sugestões o utilizador deve escrever a palavra a partir do teclado,
que deverá ser introduzida no dicionário; esta nova palavra deverá então pertencer ao
conjunto de sugestões posteriores de escrita de palavras (mas apenas para a própria
sessão).
4. Ao ser introduzida a palavra 1 (um), termina a escrita da mensagem. A mensagem final
de ser depois apresentada pelo programa.
5. Ao ser introduzida a palavra 0 (zero) o programa termina.


## 4 Funcionalidades adicionais
A aplicação poderá ser melhorada com as seguintes funcionalidades:
* Atualização do dicionário para posteriores utilizações. Neste caso, o dicionário deverá
ser escrito num novo ficheiro; se o dicionário original for dicionário.txt, o novo nome
deverá ser dicionario-update.txt.
* Sugestão de palavras ordenada pela frequência de utilização de cada palavra. Neste caso,
o dicionário utilizado contém, para além da palavra, a sua frequência de utilização. Para
novas palavras, a frequência associada deverá ter o valor 0.
Estas funcionalidades não são obrigatórias e apenas devem ser consideradas depois de
implementadas as funcionalidades descritas na secção 3.
A sua implementação dará origem a pontos extra na avaliação do trabalho.


## 5 Dicionários
Para teste da aplicação são disponibilizados 4 dicionários:
* portuguese.txt: lista de palavras da lı́ngua Portuguesa anterior ao Acordo Ortográfico.
É composta por 459,998 palavras com comprimentos entre 1 e 27 caracteres.
* portuguese-large.txt: lista de palavras da lı́ngua Portuguesa posterior ao Acordo
Ortográfico. É composta por 997,942 palavras com comprimentos entre 1 e 29 caracteres;
* english.txt: lista de palavras da lı́ngua Inglesa. É composta por 98,569 palavras com
comprimentos entre 1 e 23 caracteres.
* english-freq.txt: lista de palavras da lı́ngua Inglesa com frequência de utilização. É
composta por 333,334 palavras com comprimentos entre 1 e 38 caracteres.

## Como compilar e correr o programa
* Compile Line: gcc -std=c99 -Wall hashtable_words.c hashtable_keys.c MCurtas.c -o MCurtas -lm
* Run Line: ./Mcurtas "dictionaryName.txt"

### Enunciado feito por:
* Prof. Teresa Gonçalves

### Trabalho Realizado por:
* Leonardo Catarro - 43025
* João Matos - 32409
