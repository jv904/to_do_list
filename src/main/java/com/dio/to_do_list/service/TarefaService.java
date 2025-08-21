package com.dio.to_do_list.service;

import com.dio.to_do_list.model.Tarefa;
import com.dio.to_do_list.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    // Injeção de dependência via construtor
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    // Criar tarefa
    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // Listar todas as tarefas
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    // Buscar tarefa por ID
    public Optional<Tarefa> buscarTarefa(Long id) {
        return tarefaRepository.findById(id);
    }

    // Atualizar tarefa
    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setTitulo(tarefaAtualizada.getTitulo());
                    tarefa.setDescricao(tarefaAtualizada.getDescricao());
                    tarefa.setConcluida(tarefaAtualizada.isConcluida());
                    return tarefaRepository.save(tarefa);
                }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    // Deletar tarefa
    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }
}
