# t1=esp2(y)

main: 
     add $a0, $zero, 2
     jal exp2
     nop 
     add $t1, $zero, $v0
     j End

exp2:
     sllv $v0, $a0, $a0
     sub $v0, $v0, 4
     jr $t1
End:
     