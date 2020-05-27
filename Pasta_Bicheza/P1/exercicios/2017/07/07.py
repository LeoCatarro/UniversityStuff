h = int(input('Qual a altura? '))
sp = h-1	# espacos
i = 0
while i<h:
	s = ''
	j = 0
	while j<sp:
		s = s+' '
		j = j+1
	while j+sp<2*h-1:
		s = s+'*'
		j = j+1
	sp = sp-1
	i = i+1
	print(s)
s = ''
i = 0
while i<h-1:
	s = s+' '
	i = i+1
s = s+'*' 
print(s)
print(s)
