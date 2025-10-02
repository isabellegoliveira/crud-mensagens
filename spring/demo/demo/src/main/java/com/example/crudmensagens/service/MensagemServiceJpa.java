package com.example.crudmensagens.service;

import com.example.crudmensagens.model.Mensagem;
import com.example.crudmensagens.repository.MensagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensagemServiceJpa implements MensagemService {

    private final MensagemRepository repo;

    public MensagemServiceJpa(MensagemRepository repo) {
        this.repo = repo;
    }

    @Override
    public Mensagem criar(Mensagem mensagem) {
        mensagem.setId(null); // garantir geração
        return repo.save(mensagem);
    }

    @Override
    public List<Mensagem> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Mensagem> buscarPorId(Long id) {
        return repo.findById(id);
    }

    @Override
    public Optional<Mensagem> atualizar(Long id, Mensagem mensagem) {
        return repo.findById(id).map(existing -> {
            existing.setTexto(mensagem.getTexto());
            return repo.save(existing);
        });
    }

    @Override
    public boolean deletar(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
