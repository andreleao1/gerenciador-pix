package br.com.agls.gerenciadorpix.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agls.gerenciadorpix.domain.model.ChavePix;

@Repository
public interface ChavePixRepository extends JpaRepository<ChavePix, Long>{

	boolean existsByChave(String chave);
}
