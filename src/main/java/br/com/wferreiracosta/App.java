package br.com.wferreiracosta;

import br.com.wferreiracosta.modelo.Tabuleiro;

public class App {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
        tabuleiro.abrir(3, 3);
        tabuleiro.altenarMarcacao(4, 4);
        tabuleiro.altenarMarcacao(4, 5);
        System.out.println(tabuleiro);
    }
}
