package br.senac.tads.dsw.projetofinalmedicos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.senac.tads.dsw.projetofinalmedicos.Medico;
import br.senac.tads.dsw.projetofinalmedicos.repository.MedicoRepository;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> listar() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> obterPorId(Integer id) {
        return medicoRepository.findById(id);
    }

    public Medico salvar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Optional<Medico> atualizar(Integer id, Medico medicoAtualizado) {
        if (!medicoRepository.existsById(id)) {
            return Optional.empty();
        }
        medicoAtualizado.setId(id);
        return Optional.of(medicoRepository.save(medicoAtualizado));
    }

    public boolean excluir(Integer id) {
        if (!medicoRepository.existsById(id)) {
            return false;
        }
        medicoRepository.deleteById(id);
        return true;
    }
}