package br.edu.senac.gerenciamento_medicos.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.senac.gerenciamento_medicos.Medico;


public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    
    
}
