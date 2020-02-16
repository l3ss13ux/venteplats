package com.helene.venteplats.repository;

import com.helene.venteplats.model.Plat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatRepository extends JpaRepository<Plat,Integer> {
    @Query(value = "SELECT * FROM plat p WHERE p.id_utilisateur = ?1", nativeQuery = true)
    List<Plat> getPlatsPourUtilisateur(int idUtilisateur);
}
