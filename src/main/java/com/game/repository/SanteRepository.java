package com.game.repository;

import com.game.entity.Sante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanteRepository extends JpaRepository<Sante, Long> {
}
