package entities.enums;

import entities.exeptions.CorteNaoEspecificado;

import java.time.Duration;

public enum CortesPadrao {
    NORMAL ("NORMAL",30,30.00,1),
    DEGRADE ("DEGRADE",45,45.00,2),
    LUZES ("LUZES",60,60.00,3);

    private String corte;
    private int duracao;
    private Double preco;
    private int codigo;

    CortesPadrao(String corte, int duracao, double preco,int codigo){

        this.corte = corte;
        this.duracao = duracao;
        this.preco = preco;
        this.codigo = codigo;


    }

    public String getCorte() {
        return corte;
    }

    public int getDuracao() {
        return duracao;
    }

    public Double getPreco() {
        return preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public Duration duracaoDeCorte(){
        return Duration.ofMinutes(getDuracao());
    }

    @Override
    public String toString() {
        return  "corte='" + corte + '\'' +
                ", duracao=" + duracao +
                ", preco=" + preco;
    }
}
