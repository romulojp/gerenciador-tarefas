package br.com.rjpereira.gerenciadortarefas.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.rjpereira.gerenciadortarefas.dto.TarefaDto;
import br.com.rjpereira.gerenciadortarefas.enums.Prioridade;
import br.com.rjpereira.gerenciadortarefas.enums.Situacao;
import br.com.rjpereira.gerenciadortarefas.exception.RecordNotFoundException;
import br.com.rjpereira.gerenciadortarefas.model.Tarefa;
import br.com.rjpereira.gerenciadortarefas.repository.TarefaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Validated
public class TarefaService {
    
    private final TarefaRepository tarefaRepository;

    public List<Tarefa> findAll(){
        return tarefaRepository.findAll();
    }

    public Tarefa findById(@Positive @NotNull Long id){
        return tarefaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Tarefa create(@Valid TarefaDto dto){
        Tarefa tarefa = createTarefaByTarefaDto(dto);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa update(@Positive @NotNull Long id, @Valid TarefaDto dto){
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setDescricao(dto.getDescricao());
            tarefa.setDificuldade(Prioridade.getPorCodigo(dto.getDificuldade()));
            tarefa.setSituacao(Situacao.getPorCodigo(dto.getSituacao()));
            tarefa.setObservacao(dto.getObservacao());
            tarefa.setDataFinalizacao(dto.getDataFinalizacao());
            return tarefaRepository.save(tarefa);
        }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@Positive @NotNull Long id){
        tarefaRepository.delete(this.findById(id));;
    }

    public Tarefa createTarefaByTarefaDto(TarefaDto dto){
        return Tarefa.builder()
        .descricao(dto.getDescricao())
        .dificuldade(Prioridade.getPorCodigo(dto.getDificuldade()))
        .situacao(Situacao.getPorCodigo(dto.getSituacao()))
        .observacao(dto.getObservacao())
        .dataFinalizacao(dto.getDataFinalizacao()).build();
    }
}
