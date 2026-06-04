package br.edu.senac.gerenciamento_medicos.controller;

import br.edu.senac.gerenciamento_medicos.Medico;
import br.edu.senac.gerenciamento_medicos.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService){
        this.medicoService = medicoService;
    }

    @GetMapping
    public List<Medico> listar(){
        return medicoService.listar();
    }

    @GetMapping("/{id}")
    public Medico obterPorId(@PathVariable Integer id){

        Optional<Medico> optMedico = medicoService.obterPorId(id);

        if (optMedico.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Medico não encontrado");
        }

        return optMedico.get();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody Medico medico){

        Medico medicoCriado = medicoService.salvar(medico);

        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(medicoCriado.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(medicoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Medico medico
    ){

        Optional<Medico> medicoAtualizado = medicoService.atualizar(id, medico);

        if (medicoAtualizado.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Médico não encontrado"
            );
        }
        return ResponseEntity.ok(medicoAtualizado.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id){

        boolean removido = medicoService.excluir(id);

        if (!removido){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Médico não encontrado"
            );
        }
        return ResponseEntity.noContent().build();
    }

}
