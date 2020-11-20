main:
	li $a0, 1
	li $a1, 5
	j Summe
	nop

Summe:
	beq $a0, $a1, END
	add $v0, $v0, $a0
	addi $a0, $a0, 1
	j Summe
	nop
	


END:
	
	nop