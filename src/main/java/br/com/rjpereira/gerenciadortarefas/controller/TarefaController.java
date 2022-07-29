package br.com.rjpereira.gerenciadortarefas.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rjpereira.gerenciadortarefas.dto.TarefaDto;
import br.com.rjpereira.gerenciadortarefas.model.Tarefa;
import br.com.rjpereira.gerenciadortarefas.service.TarefaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("api/tarefas")
public class TarefaController {
    
    private TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<Tarefa>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> findById(@PathVariable @Positive @NotNull Long id){
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Tarefa> create(@RequestBody @Valid TarefaDto tarefaDto){
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.create(tarefaDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> update(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid TarefaDto tarefaDto){
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.update(id, tarefaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive @NotNull Long id){
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
