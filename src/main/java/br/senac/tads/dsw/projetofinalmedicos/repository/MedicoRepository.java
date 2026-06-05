package br.senac.tads.dsw.projetofinalmedicos.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.tads.dsw.projetofinalmedicos.Medico;


public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    
    
}
