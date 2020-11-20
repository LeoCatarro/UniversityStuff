.data

.asciiz "Hello World!"


.text


li $a0, 0x10010000

li $a1, 0x0000006f 	#ascii code for 'o'



zahl:
move  $v0, $zero     # v0 = 0
	
addi $sp, $sp, -4
	
sw $s0, 0($sp)
	


L:      
lbu   $s0, 0($a0)       # lesen
        
addi $a0, $a0, 1
        
bne  $s0, $a1, SKIP    # springen
	
nop
        
addi $v0, $v0, 1       # zahl


SKIP:   
bne $s0, $zero, L
        
nop
        
j END
        
nop



END: 	
lw $s0, 0($sp)
	
addi $sp, $sp, 4
	
jr $ra
nop