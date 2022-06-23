package br.com.agls.gerenciadorpix.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agls.gerenciadorpix.domain.model.TransacaoPix;

@Repository
public interface TransacaoPixRepository extends JpaRepository<TransacaoPix, Long>{

}
