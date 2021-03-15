all: client server


# insira aqui as regras para o make


client:	classes/sd/EchoClient.class


runclient: client
	java -classpath classes sd.EchoClient $(A1) $(A2) "$(A3)"


server:   # to do...





# eliminar temporarios e .class
clean:
	find . -name \*~ | xargs rm -f
	find . -name \*.class | xargs rm -f
