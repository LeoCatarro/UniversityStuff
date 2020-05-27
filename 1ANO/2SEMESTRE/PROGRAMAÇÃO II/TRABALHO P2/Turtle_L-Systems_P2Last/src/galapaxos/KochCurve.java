package galapaxos;

import java.util.HashMap;

    public class KochCurve implements LSystem {

        public String symbolIter; // string que é transformada dps de aplicar a regra ao char introduzido

        HashMap<Character, String> charToString; // dicionario para passar de 1 simbolo para uma string

        public KochCurve() {

            this.charToString = new HashMap<Character, String>(); // inicialização do mapa

        }

        public void setStart(String start) {
            this.symbolIter = start; //inicializa a string para a 1 iteração
        }

        public void addRule(Character symbol, String word) {
            charToString.put(symbol, word);
        }

        public boolean hasChar(char symbol) { // função que verifica se o simbolo introduzido refere-se a uma string ou não
            if (charToString.get(symbol) != null)

                return true;

            else
                return false;
        }

        public String iter(String word) { // função que itera 1 so vez, depois faz as iterações por cada vez que é chamada na loopIter

            String finalString = ""; // Inicilizar a String para ser concatenada ; NOTA : NÃO INCIALIZAR A NULL !
            char c;
            for (int i = 0; i < word.length(); i++) {
                c = word.charAt(i);
                if (hasChar(c))
                    finalString += charToString.get(c);
                else
                    finalString += c;
            }

            return finalString;
        }

        public String loopIter(int n) {

            String aux = symbolIter; //inicializar uma auxiliar para usar na função iter
            for (int i = 0; i < n; i++) {

                charToString.get(aux); // processo inverso a obter a palavra, ou seja vai buscar o simbolo introduzido que gerou a palavra
                aux = iter(aux);

            }
            return aux;


        }
    }

