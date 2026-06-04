package br.edu.senac.gerenciamento_medicos.service;

import br.edu.senac.gerenciamento_medicos.Medico;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MedicoService {

    private AtomicInteger contadorIds = new AtomicInteger(0);
    public Map<Integer, Medico> mapMedicos = new ConcurrentHashMap<>();

    public List<Medico> listar(){
        return new ArrayList<>(mapMedicos.values());
    }

    public Optional<Medico> obterPorId(Integer id){
        return Optional.ofNullable(mapMedicos.get(id));
    }

    public Medico salvar(Medico medico){
        Integer novoId = contadorIds.incrementAndGet();
        medico.setId(novoId);
        mapMedicos.put(novoId, medico);
        return medico;
    }

    public Optional<Medico> atualizar(Integer id, Medico medicoAtualizado){

        if (!mapMedicos.containsKey(id)){
            return Optional.empty();
        }

        medicoAtualizado.setId(id);

        mapMedicos.put(id, medicoAtualizado);
        return Optional.of(medicoAtualizado);
    }

    public boolean excluir(Integer id){
        return mapMedicos.remove(id) != null;
    }
}
