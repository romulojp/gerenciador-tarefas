package br.com.rjpereira.gerenciadortarefas.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Prioridade {
    BAIXA(0, "Baixa"),
    MEDIA(1, "Média"),
    ALTA(2, "Alta");

    private int codigo;
    private String descricao;

    private Prioridade(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }


    public static Prioridade getPorCodigo(int codigo){
        for(Prioridade dif : Prioridade.values()){
            if(dif.codigo == codigo){
                return  dif;
            }
        }
        return null;
    }

    public String getDescricao(){
        return descricao;
    }

    public int getCodigo(){
        return codigo;
    }
}
