.data 
	#File specificaion
	Original_file:  .asciiz "lena512color.rgb"
	Gray_file: .asciiz "lena512.gray"
	Final_file: .asciiz "lena_final.gray"
	#All buffers
	Original_buffer:  .space 786432 #786432 = 512 x 512 x 3
	GRAY_buffer: .space 262144 #262144 = 512 x 512
	CONTOUR_buffer: .space 262144 
	Horizontal_buffer: .space 262144
	Vertical_buffer: .space 262144
	Outline_buffer: .space 262144
	#buffer size
	Original_buffer_size:  .word 786432
	GRAY_buffer_size: .word 262144
	
.text

main:	
	#argumentos para a função read_rgb_image
	la $a0, Original_file
	la $a1, Original_buffer
	la $a2, Original_buffer_size
	jal read_rgb_image
	nop
	#argumentos para a função rgb_to_gray
	move $a0, $s1 
	la $a1, GRAY_buffer
	jal rgb_to_gray
	nop
	#chama a função write_gray_image
	jal write_gray_image
	nop


read_rgb_image:
	# Place file and size in register
	move $s0, $a0
	move $s1, $a1
	move $s2, $a2
	
	# Open file
	move $a0, $s0 #File's directory/name
	li $a1, 0
	li $a2, 0     # Read file
	li $v0, 13    # Option for opening file
	syscall      

	# Read file
	move $a0, $v0  # File descriptor
	move $a1, $s1  # Buffer with result
	move $a2, $s2  # Space of information to read
	li $v0, 14     # Read file
	syscall
	
	# Store read buffer
	

	# Close file
	li $v0, 16 # Close file
	syscall
	
	# Return
	move $v0, $s1 # Make return
	jr $ra#votla para a função main
	nop

rgb_to_gray:

	li $s2,262144	#contador	
	move $s0, $a0	#buffer original
	move $s1, $a1	#buffer GRAY
	
loop:
	beq $s2,$zero,fim	#confirmar se o contador ja esta a zero
	nop 
	
	li $t0, 30	#escala de conversao R
	li $t1, 59	#escala de conversao G
	li $t2, 11	#escala de conversao B
	
	lbu $t3, 0($s0)	#byte correspondente ao R
	lbu $t4, 1($s0)	#byte correspondente ao G
	lbu $t5, 2($s0)	#byte correspondente ao B
	
	mul $t0,$t0,$t3	#multiplicação para converter o R
	mul $t1,$t1,$t4	#multiplicação para converter o G
	mul $t2,$t2,$t5	#multiplicação para converter o B
	
	add $t0,$t0,$t1	#soma para a conversao para GRAY
	add $t0,$t0,$t2	#2ª soma para a conversao para GRAY
	
	div $t0,$t0,100	#mudar para decimal
	sb $t0,0($s1)	#guardar o pixel no buffer GRAY
		
	addi $s0,$s0,3	#avançar o buffer original
	addi $s1,$s1,1	#avançar o buffer GRAY
	
	sub $s2,$s2,1	#reduzir o contador do loop
	
	j loop
	nop


fim:	
	jr $ra#votla para a função main
	nop

write_gray_image:
	#create file
	la $a0, Gray_file
	li $a1, 1
	la $a2, 0
	li $v0,13
	syscall
	move $s2, $v0
	#write to file	
	li $v0, 15
	move $a0,$s2
	la $a1, GRAY_buffer
	li $a2, 262144
	syscall
	#close file
	li $v0, 16
	move $a0,$s2
	syscall
	
