package com.game.repository;

import com.game.entity.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {

    Devis findBySanteId(Long santeId); // Derived query method

}
