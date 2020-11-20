# Computação Gráfica - Exemplos

## Dependências


Os exemplos têm quase todos de ser servidos. Por exemplo, na directoria `cg`, no terminal basta correr...

    com python3: python -m http.server 8080
    com python2: python -m SimpleHTTPServer 8080

## Organização das directorias

    ~cg/
        exemplos/
        	js/
        lib/
            @jquery.js             --> ???/jquery-<VERSION>.min.js
            @jquery.mousewheel.js  --> ???/jquery.mousewheel.min.js
        	@x3dom.js              --> ???/x3dom.js
        	@x3dom-full.js         --> ???/x3dom-full.js
        	@x3dom.css             --> ???/x3dom.css
        	@three.js              --> ???/three.min.js
            @tween.js              --> ???/tween.min.js
        	sim/
                sim.js
                ...
        media/
        modelos/
            base_<XPTO>.html
        doc/
            010-INTRO.pdf
            ...
        ...

O símbolo `@` indica que o ficheiro é um _link simbólico_. Se o seu sistema operativo não suporta _links_ está na hora de evoluir para melhor.

Esta organização supõe que as bibliotecas não incluídas no arquivo `zip` foram descarregadas "algures" (`???`) e que o conteúdo da directoria `lib/` consiste (quase) essencialmente em _links_ para os ficheiros indicados.