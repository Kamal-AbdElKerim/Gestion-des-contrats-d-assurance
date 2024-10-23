package com.game.repository;

import com.game.entity.Habitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitationRepository extends JpaRepository<Habitation, Long> {
}
