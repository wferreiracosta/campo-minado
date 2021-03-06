package br.com.wferreiracosta.modelo;

import br.com.wferreiracosta.excecao.ExplosaoException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Campo {

    private final int linha;
    private final int coluna;

    private boolean aberto = false;
    private boolean minado = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    boolean adicionarVizinho(Campo vizinho){
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        if(deltaGeral == 1 && !diagonal){
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal){
            vizinhos.add(vizinho);
            return true;
        } else {
            return false;
        }
    }

    void alternarMarcacao(){
        if (!isAberto()) {
            marcado = !marcado;
        }
    }

    boolean abrir(){
        if(!isAberto() && !isMarcado()){
            setAberto(true);
            if(isMinado()){
                throw new ExplosaoException();
            }

            if(vizinhacaoSegura()){
                vizinhos.forEach(v -> v.abrir());
            }

            return true;
        }
        return false;
    }

    boolean vizinhacaoSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    void minar(){
        setMinado(true);
    }

    boolean objetivoAlcancado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    long minasNaVizinhanca(){
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciar(){
        this.setAberto(false);
        this.setMinado(false);
        this.setMarcado(false);
    }

    public String toString(){
        if (isMarcado()) {
            return "X";
        } else if (isAberto() && isMinado()) {
            return "*";
        } else if (isAberto() && this.minasNaVizinhanca() > 0) {
            return Long.toString(this.minasNaVizinhanca());
        } else if (isAberto()) {
            return " ";
        } else {
            return "?";
        }
    }
}
