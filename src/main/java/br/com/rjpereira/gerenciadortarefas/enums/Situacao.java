package br.com.rjpereira.gerenciadortarefas.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Situacao {

    CRIADA(0, "Criada"),
    EM_ANDAMENTO(1, "Em andamento"),
    ABORTADA(2, "Abortada"),
    CONCLUIDA(3, "Concluída");

    private int codigo;
    private String descricao;

    private Situacao(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static Situacao getPorCodigo(int codigo) {
        for (Situacao dif : Situacao.values()) {
            if (dif.codigo == codigo) {
                return dif;
            }
        }
        return null;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCodigo() {
        return codigo;
    }
}
