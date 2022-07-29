package br.com.rjpereira.gerenciadortarefas.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.rjpereira.gerenciadortarefas.enums.Prioridade;
import br.com.rjpereira.gerenciadortarefas.enums.Situacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40, nullable = false)
    private String descricao;
    @Column
    private String observacao;
    @Enumerated(EnumType.ORDINAL)
    private Prioridade dificuldade;
    @Enumerated(EnumType.ORDINAL)
    private Situacao situacao;
    @Column(name = "data_finalizacao")
    private LocalDate dataFinalizacao;
    private LocalDateTime created;
    @Column(name = "last_modified")
    private LocalDateTime LastModified;

    @PrePersist
    public void prePersist(){
        this.created = LocalDateTime.now();
        this.LastModified = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.LastModified = LocalDateTime.now();
    }

}
