package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Main main=new Main();
        Scanner ler = new Scanner(System.in);

        var input = 1;

        while(input != 0){
            System.out.println("Entre com o valor investido:");
            input = ler.nextInt();
            System.out.println("Quantidade de views esperadas: "+main.processar(Double.valueOf(input)));
        }
    }

    private Double clica(Double view){
        //retorna  quantidade clicks
        return (view*0.12);
    }
    private Double compartilha(Double click){
        //retorna a quantidade de compartilhamentos
        return  ((click * 0.15));
    }

    private Double getQuantidadePessoasCompartilharam(Double views){
        return round(compartilha(clica(views)));
    }

    private Double viewCompartilhamento(Double viewsTotais, Double qtdPessoasCompartilharam, int qtdCompartilhamentoTotal){
        if(qtdCompartilhamentoTotal != 3 && qtdPessoasCompartilharam >= 1){
            double views = qtdPessoasCompartilharam * 40;

            viewsTotais += views;

            Double pessoasCompartilharam = getQuantidadePessoasCompartilharam(views);

            viewsTotais = viewCompartilhamento(viewsTotais, pessoasCompartilharam, qtdCompartilhamentoTotal+1);
        }
        return viewsTotais;
    }

    private Double processar(Double valor){
        //variavel que controla quantas vezes pode compartilhar
        int qtdCompartilhamentos = 0;

        Double views=(valor*30);

        Double pessoasCompartilharam = getQuantidadePessoasCompartilharam(views);

        views = viewCompartilhamento(views,pessoasCompartilharam, qtdCompartilhamentos);

        return views;
    }

    //FUNÇÃO QUE ARREDONDA PARA BAIXO A QUANTIDADE DE PESSOAS QUE IRAO COMPARTILHAR
    //EX: 12,7 -> ENTAO ELE ARREDONDA PARA 12 (QUANTIDADE REAL)
    public static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(0, RoundingMode.DOWN);
        return bd.doubleValue();
    }
}