package com.example.crudmensagens.service;

import com.example.crudmensagens.model.Mensagem;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MensagemServiceInMemory implements MensagemService {

    private final Map<Long, Mensagem> armazenamento = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public Mensagem criar(Mensagem mensagem) {
        Long id = sequence.incrementAndGet();
        mensagem.setId(id);
        armazenamento.put(id, mensagem);
        return mensagem;
    }

    @Override
    public List<Mensagem> listar() {
        return new ArrayList<>(armazenamento.values());
    }

    @Override
    public Optional<Mensagem> buscarPorId(Long id) {
        return Optional.ofNullable(armazenamento.get(id));
    }

    @Override
    public Optional<Mensagem> atualizar(Long id, Mensagem mensagem) {
        if (!armazenamento.containsKey(id)) {
            return Optional.empty();
        }
        mensagem.setId(id);
        armazenamento.put(id, mensagem);
        return Optional.of(mensagem);
    }

    @Override
    public boolean deletar(Long id) {
        return armazenamento.remove(id) != null;
    }
}
