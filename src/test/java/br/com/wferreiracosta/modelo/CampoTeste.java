package br.com.wferreiracosta.modelo;

import br.com.wferreiracosta.excecao.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampoTeste {

    Campo campo;

    @BeforeEach
    void iniciar(){
        this.campo = new Campo(3,3);
    }

    @Test
    void vizinhoDistanciaUmEsquerda(){
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void vizinhoDistanciaUmDireita(){
        Campo vizinho = new Campo(3, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void vizinhoDistanciaUmCima(){
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void vizinhoDistanciaUmBaixo(){
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void naoVizinho(){
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void valorPadraoAtributoMarcado(){
        assertFalse(campo.isMarcado());
    }

    @Test
    void alternarMarcado(){
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void alternarMarcadoDuasChamadas(){
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void abrirMinadoMarcado(){
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void abrirMinadoNaoMarcado(){
        campo.minar();
        assertThrows(ExplosaoException.class, () -> {
           campo.abrir();
        });
    }

    @Test
    void abrirComVizinhos(){
        Campo campo11 = new Campo(1, 1);

        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void abrirComVizinhos2(){
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 1);
        campo12.minar();

        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertFalse(campo11.isAberto());
        assertTrue(campo22.isAberto());
    }
}
