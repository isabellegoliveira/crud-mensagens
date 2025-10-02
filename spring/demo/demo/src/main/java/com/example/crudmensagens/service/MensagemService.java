package com.example.crudmensagens.service;

import com.example.crudmensagens.model.Mensagem;
import com.example.crudmensagens.repository.MensagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensagemService {

    private final MensagemRepository repository;

    public MensagemService(MensagemRepository repository) {
        this.repository = repository;
    }

    public Mensagem criar(Mensagem mensagem) {
        return repository.save(mensagem);
    }

    public List<Mensagem> listar() {
        return repository.findAll();
    }

    public Optional<Mensagem> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Mensagem> atualizar(Long id, Mensagem novaMensagem) {
        return repository.findById(id).map(m -> {
            m.setTexto(novaMensagem.getTexto());
            return repository.save(m);
        });
    }

    public boolean deletar(Long id) {
        return repository.findById(id).map(m -> {
            repository.delete(m);
            return true;
        }).orElse(false);
    }
}
