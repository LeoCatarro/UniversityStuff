.data
imgInput:   .asciiz"/home/leonardo/Desktop/TrabalhoASC-FINAL/lena.gray"     	 #Ficheiro de Input
imgOutput:  .asciiz"/home/leonardo/Desktop/TrabalhoASC-FINAL/lena2.gray"    	 #Ficheiro de Output após aplicado o mean_filter
imgOutput2: .asciiz"/home/leonardo/Desktop/TrabalhoASC-FINAL/lena3.gray"	 #Ficheiro de Output após aplicado o median_filter
bufferIn:  .space 262144                                                 	 #Buffer que contem a foto de Input
bufferOut: .space 262144						 	 #Buffer que conterá a foto de Output	
bufferMedian: .space 9							 	 #Buffer usado para o median_filter

.text
MAIN:
	#Argumentos e jump(Função de leitura)
	la $a0, imgInput
	jal read_gray_image
	nop
	
	#Argumentos e jump(Função de aplicaçao do filtro da média)	
	la $a0, bufferIn
	la $a1, bufferOut	
	la $a2, 512
	jal mean_filter
	nop
	
	#Argumentos e jump(Função de escrita)
	la $a0, imgOutput
	la $a1, bufferOut
	la $a2, 262144
	jal write_gray_image
	nop
	
	#Argumentos e jump(Função de aplicaçao do filtro da mediana)
	la $a0, bufferIn
	la $a1, bufferOut
	la $a2, 512
	jal median_filter
	nop
	
	#Argumentos e jump(Função de escrita)
	la $a0, imgOutput2
	la $a1, bufferOut
	la $a2, 262144
	jal write_gray_image
	nop
		
	#Fim do programa
	li $v0, 10			
	syscall


#########################################################################################	
#read_gray_image - Esta funcao que recebe o nome de um ficheiro, abre o ficheiro e le a #
#                  imagem para memória(dados dinamicos)					#
#											#
#Argumentos:										#
# a0 - string contendo o nome do ficheiro para abrir					#								
# 											#
#Retorna:										#
# v0 - endereço onde se encontra a imagem						#
#########################################################################################	 
read_gray_image:

	#Abrir o ficheiro para leitura
	la $a0, imgInput						#String que contem o nome do Ficheiro para leitura				
	li $a1, 0							
	li $a2, 0							#Ler apenas
	li $v0, 13 							#Abrir
	syscall
	move $s0, $v0

	#Leitura do ficheiro
	li $v0, 14							#Leitura
	move $a0, $s0
	la $a1, bufferIn						#Colocação do Buffer no $a0
	li $a2, 262144							#Tamanho do buffer e colocação dele em $a1
	syscall
	
	#Fechar o ficheiro
	li $v0, 16							
	syscall
	
	#Fim da função
	jr $ra
	nop

################################################################################
#write_gray_image - Esta funcao escreve uma imagem em formato GRAY num ficheiro#
#									       #
#Argumentos:								       #
# a0 - nome do ficheiro                                                        #
# a1 - bufferOut							       #
# a2 - comprimento do buffer				                       #
#                                                                              #
#Retorna:                                                                      #
# Não retorna nada!                                                            #
################################################################################
write_gray_image:

	#Abrir o ficheiro para escrever
	li $a1, 1							#Escrever
	li $a2, 0							#**Ignorado**
	li $v0, 13							#Abrir
	syscall
	
	#Escrever no ficheiro
	move $a0, $v0
	la $a1, bufferOut					        #Colocação do BufferOut em $a1
	li $a2, 262144
	li $v0, 15							#Escrita
	syscall

	#Fechar o ficheiro
	li $v0, 16							
	syscall
	
	#Fim da função
	jr $ra
	nop

#########################################################################################
#mean_filter: funcao que calcula a convolucao da imagem, com uma mascara(uma matriz 3x3)#
#             										#
#Argumentos:										#
# a0 - bufferIn										#
# a1 - bufferOut 									#
# a2 - dimensoes da imagem								#
# 											#
#Retorna:										#
# Não retorna nada!									#
#########################################################################################
mean_filter:
	
	#Valores iniciais
	addi $t3, $zero, 511				#Contador: $t3=511, inicio do contador
	addi $a0, $a0, 511							
	addi $a1, $a1, 511

	FOR:
	lbu $t1, 0($a0)					#Load do byte central da matriz 3x3
	add $t2, $zero, $t1

	lbu $t1, -512($a0)				#Load do byte do canto superior esquerdo da matriz 3x3
	add $t2, $t2, $t1

	lbu $t1, -511($a0)				#Load do byte acima do byte central
	add $t2, $t2, $t1

	lbu $t1, -510($a0)				#Load do byte do canto superior direito
	add $t2, $t2, $t1

	lbu $t1, -1($a0)				#Load do byte á esquerda do byte central
	add $t2, $t2, $t1

	lbu $t1, 1($a0)					#Load do byte á direira do byte central
	add $t2, $t2, $t1

	lbu $t1, 511($a0)				#Load do byte do canto inferior esquerdo da matriz 3x3
	add $t2, $t2, $t1

	lbu $t1, 512($a0)				#Load do byte abaixo do byte central
	add $t2, $t2, $t1

	lbu $t1, 513($a0)				#Load do byte do canto inferior direito da matriz 3x3
	add $t2, $t2, $t1

	div $t2, $t2, 9					#Cálculo da média				
	sb $t2, 513($a1)				#Colocação do resultado no píxel a alterar


	#INCREMENTAÇOES
	addi $a0, $a0, 1				#a0 = a0 + 1(Forçar a avancar um pixel no BufferIn)	
	addi $a1, $a1, 1				#a1 = a1 + 1(Forçar a avançar um pixel no BufferOut)
	addi $t3, $t3, 1				#t3 = t3 + 1(Incremento do contador)

	ble $t3, 262144, FOR				#Enquanto t3<=262144, volta á label FOR
	nop

	#Final da Função	
	jr $ra 
	nop

###################################################################################################################################
#median_filter:															  #
#															          #
#Argumentos;                            											  #
# a0 - bufferOriginal             												  #
# a1 - bufferFiltrada													          #
# a2- dimensão da imagem													  #
#																  #
#Retorna:															  #
# Não retorna nada!														  #
###################################################################################################################################
median_filter:

	addi $sp, $sp, -4				#Alocação de espaço da pilha			
	sw $s0, 0($sp)					#Colocação de $s0 na posição mem[$sp+0] da pilha

	#Valores inicias
	addi $s0, $zero, 511
	addi $a0, $a0, 511
	addi $a1, $a1, 511


	la $t0, bufferMedian				#Colocação do BufferMedian no registo $t0

FOR1:
	lbu $t1, 0($a0)				        ###
	lbu $t2, -512($a0)				  #	
	lbu $t3, -511($a0)				  #	
	lbu $t4, -510($a0)				  #	Load do bytes de uma matriz 3x3
	lbu $t5, -1($a0)				  #	em que o byte **** é o byte central
	lbu $t6, 1($a0)					  #
	lbu $t7, 511($a0)				  #
	lbu $t8, 512($a0)			          #
	lbu $t9, 513($a0)				###

	sb $t1, 0($t0)					###
	sb $t2, 4($t0)					  #
	sb $t3, 8($t0)					  #
	sb $t4, 12($t0)					  #	Store dos bytes carregados no Buffer da mediana
	sb $t5, 16($t0)					  #
	sb $t6, 20($t0)					  #
	sb $t7, 24($t0)					  #
	sb $t8, 28($t0)					  #
	sb $t9, 32($t0)					###


	#Organização, por ordem crescente, dos bytes no Buffer
	SORT:						   	#Comparação de t1 com t2: Se t1>t2, trocam-se os valores de t1 e t2
	xor $t1, $t1, $t2
	xor $t2, $t2, $t1
	xor $t1, $t1, $t2
	bgt $t1, $t2, SORT
	nop

	SORT1:						   	#Comparação de t1 com t3: Se t1>t3, trocam-se os valores de t1 e t3
	xor $t1, $t1, $t3
	xor $t3, $t3, $t1
	xor $t1, $t1, $t3
	bgt $t1, $t3, SORT1
	nop

	SORT2:						   	#Comparação de t1 com t4: Se t1>t4, trocam-se os valores de t1 e t4
	xor $t1, $t1, $t4
	xor $t4, $t4, $t1
	xor $t1, $t1, $t4
	bgt $t1,$t4, SORT2
	nop

	SORT3:						   	#Comparação de t1 com t5: Se t1>t5, trocam-se os valores de t1 e t5
	xor $t1, $t1, $t5
	xor $t5, $t5, $t1
	xor $t1, $t1, $t5
	bgt $t1,$t5, SORT3
	nop

	SORT4:						   	#Comparação de t1 com t6: Se t1>t6, trocam-se os valores de t1 e t6
	xor $t1, $t1, $t6
	xor $t6, $t6, $t1
	xor $t1, $t1, $t6
	bgt $t1,$t6, SORT4
	nop

	SORT5:						   	#Comparação de t1 com t7: Se t1>t7, trocam-se os valores de t1 e t7
	xor $t1, $t1, $t7
	xor $t7, $t7, $t1
	xor $t1, $t1, $t7
	bgt $t1,$t7, SORT5
	nop

	SORT6:						   	#Comparação de t1 com t8: Se t1>t8, trocam-se os valores de t1 e t8
	xor $t1, $t1, $t8
	xor $t8, $t8, $t1
	xor $t1, $t1, $t8
	bgt $t1,$t8, SORT6
	nop

	SORT7:						   	#Comparação de t1 com t9: Se t1>t9, trocam-se os valores de t1 e t9
	xor $t1, $t1, $t9
	xor $t9, $t9, $t1
	xor $t1, $t1, $t9
	bgt $t1,$t9, SORT7
	nop
#########################################################################
	SORT8:							#Comparação de t2 com t3: Se t2>t3, trocam-se os valores de t2 e t3
	xor $t2, $t2, $t3
	xor $t3, $t3, $t2
	xor $t2, $t2, $t3
	bgt $t2,$t3, SORT8
	nop

	SORT9:							#Comparação de t2 com t4: Se t2>t4, trocam-se os valores de t2 e t4
	xor $t2, $t2, $t4
	xor $t4, $t4, $t2
	xor $t2, $t2, $t4
	bgt $t2,$t4, SORT9
	nop

	SORT10:							#Comparação de t2 com t5: Se t2>t5, trocam-se os valores de t2 e t5
	xor $t2, $t2, $t5
	xor $t5, $t5, $t2
	xor $t2, $t2, $t5
	bgt $t2,$t5, SORT10
	nop

	SORT11:							#Comparação de t2 com t6: Se t2>t6, trocam-se os valores de t2 e t6
	xor $t2, $t2, $t6
	xor $t6, $t6, $t2
	xor $t2, $t2, $t6
	bgt $t2,$t6, SORT11
	nop

	SORT12:							#Comparação de t2 com t7: Se t2>t7, trocam-se os valores de t2 e t7
	xor $t2, $t2, $t7
	xor $t7, $t7, $t2
	xor $t2, $t2, $t7
	bgt $t2,$t7, SORT12
	nop

	SORT13:							#Comparação de t2 com t8: Se t2>t8, trocam-se os valores de t2 e t8
	xor $t2, $t2, $t8
	xor $t8, $t8, $t2
	xor $t2, $t2, $t8
	bgt $t2,$t8, SORT13
	nop

	SORT14:							#Comparação de t2 com t9: Se t2>t9, trocam-se os valores de t2 e t9
	xor $t2, $t2, $t9
	xor $t9, $t9, $t2
	xor $t2, $t2, $t9
	bgt $t2,$t9, SORT14
	nop
###############################################################################
	SORT15:							#Comparação de t3 com t4: Se t3>t4, trocam-se os valores de t3 e t4
	xor $t3, $t3, $t4
	xor $t4, $t4, $t3
	xor $t3, $t3, $t4
	bgt $t3,$t4, SORT15
	nop

	SORT16:							#Comparação de t3 com t5: Se t3>t5, trocam-se os valores de t3 e t5
	xor $t3, $t3, $t5
	xor $t5, $t5, $t3
	xor $t3, $t3, $t5
	bgt $t3,$t5, SORT16
	nop

	SORT17:							#Comparação de t3 com t6: Se t3>t6, trocam-se os valores de t3 e t6
	xor $t3, $t3, $t6
	xor $t6, $t6, $t3
	xor $t3, $t3, $t6
	bgt $t3,$t6, SORT17
	nop

	SORT18:							#Comparação de t3 com t7: Se t3>t7, trocam-se os valores de t3 e t7
	xor $t3, $t3, $t7
	xor $t7, $t7, $t3
	xor $t3, $t3, $t7
	bgt $t3,$t7, SORT18
	nop

	SORT19:							#Comparação de t3 com t8: Se t3>t8, trocam-se os valores de t3 e t8
	xor $t3, $t3, $t8
	xor $t8, $t8, $t3
	xor $t3, $t3, $t8
	bgt $t3,$t8, SORT19
	nop

	SORT20:							#Comparação de t3 com t9: Se t3>t9, trocam-se os valores de t3 e t9
	xor $t3, $t3, $t9
	xor $t9, $t9, $t3
	xor $t3, $t3, $t9
	bgt $t3,$t9, SORT20
	nop
###############################################################################
	SORT21:							#Comparação de t4 com t5: Se t4>t5, trocam-se os valores de t4 e t5
	xor $t4, $t4, $t5
	xor $t5, $t5, $t4
	xor $t4, $t4, $t5
	bgt $t4,$t5, SORT21
	nop

	SORT22:							#Comparação de t4 com t6: Se t4>t6, trocam-se os valores de t4 e t6
	xor $t4, $t4, $t6
	xor $t6, $t6, $t4
	xor $t4, $t4, $t6
	bgt $t4,$t6, SORT22
	nop

	SORT23:							#Comparação de t4 com t7: Se t4>t7, trocam-se os valores de t4 e t7
	xor $t4, $t4, $t7
	xor $t7, $t7, $t4
	xor $t4, $t4, $t7
	bgt $t4,$t7, SORT23
	nop

	SORT24:							#Comparação de t4 com t8: Se t4>t8, trocam-se os valores de t4 e t8
	xor $t4, $t4, $t8
	xor $t8, $t8, $t4
	xor $t4, $t4, $t8
	bgt $t4,$t8, SORT24
	nop

	SORT25:							#Comparação de t4 com t9: Se t4>t9, trocam-se os valores de t4 e t9
	xor $t4, $t4, $t9
	xor $t9, $t9, $t4
	xor $t4, $t4, $t9
	bgt $t4,$t9, SORT25
	nop
###########################################################################
	SORT26:							#Comparação de t5 com t6: Se t5>t6, trocam-se os valores de t5 e t6
	xor $t5, $t5, $t6
	xor $t6, $t6, $t5
	xor $t5, $t5, $t6
	bgt $t5,$t6, SORT26
	nop

	SORT27:							#Comparação de t5 com t7: Se t5>t7, trocam-se os valores de t5 e t7
	xor $t5, $t5, $t7
	xor $t7, $t7, $t5
	xor $t5, $t5, $t7
	bgt $t5,$t7, SORT27
	nop

	SORT28:							#Comparação de t5 com t8: Se t5>t8, trocam-se os valores de t5 e t8
	xor $t5, $t5, $t8
	xor $t8, $t8, $t5
	xor $t5, $t5, $t8
	bgt $t5,$t8, SORT28
	nop

	SORT29:							#Comparação de t5 com t9: Se t5>t9, trocam-se os valores de t5 e t9
	xor $t5, $t5, $t9
	xor $t9, $t9, $t5
	xor $t5, $t5, $t9
	bgt $t5,$t9, SORT29
	nop
#############################################################################
	SORT30:							#Comparação de t6 com t7: Se t6>t7, trocam-se os valores de t6 e t7
	xor $t6, $t6, $t7
	xor $t7, $t7, $t6
	xor $t6, $t6, $t7
	bgt $t6,$t7, SORT30
	nop

	SORT31:							#Comparação de t6 com t8: Se t6>t8, trocam-se os valores de t6 e t8
	xor $t6, $t6, $t8
	xor $t8, $t8, $t6
	xor $t6, $t6, $t8
	bgt $t6,$t8, SORT31
	nop

	SORT32:							#Comparação de t6 com t9: Se t6>t9, trocam-se os valores de t6 e t9
	xor $t6, $t6, $t9
	xor $t9, $t9, $t6
	xor $t6, $t6, $t9
	bgt $t6,$t9, SORT32
	nop
#############################################################################
	SORT33:							#Comparação de t7 com t8: Se t7>t8, trocam-se os valores de t7 e t8
	xor $t7, $t7, $t8
	xor $t8, $t8, $t7
	xor $t7, $t7, $t8
	bgt $t7,$t8, SORT33
	nop

	SORT34:							#Comparação de t7 com t9: Se t7>t9, trocam-se os valores de t7 e t9
	xor $t7, $t7, $t9
	xor $t9, $t9, $t7
	xor $t7, $t7, $t9
	bgt $t7,$t9, SORT34
	nop
#############################################################################
	SORT35:							#Comparação de t8 com t9: Se t8>t9, trocam-se os valores de t8 e t9
	xor $t8, $t8, $t9
	xor $t9, $t9, $t8
	xor $t8, $t8, $t9
	bgt $t8, $t9, SORT35
	nop
#############################################################################

	#Incrementações
	addi $a0, $a0, 1					#a0 = a0 + 1 , forçar o BufferIn a avançar um byte, ou seja, um pixel
	addi $a1, $a1, 1					#a1 = a1 +1 , forlar o BufferOut a avancar um byte, ou seja, um pixel
	addi $s0, $s0, 1					#s0 = s0 + 1, incremento do contador
	sb $t5, 513($a1)					#Colocação do byte que é a mediana, no byte em alteração

	ble $s0, 262144, FOR1					#Enquanto s0<=262144 , repete o processo da label FOR1
	nop

	lw $s0, 0($sp)						
	addi $sp, $sp, 4					#Incremento do espaço na pilha, pois foi deixado de ser usado
	
	#Final da Função
	jr $ra
	nop
