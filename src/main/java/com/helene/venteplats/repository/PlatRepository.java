package com.helene.venteplats.repository;

import com.helene.venteplats.model.Plat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatRepository extends JpaRepository<Plat,Integer>, JpaSpecificationExecutor<Plat> {
    @Query(value = "SELECT * FROM plat p WHERE p.utilisateur_id = ?1", nativeQuery = true)
    List<Plat> getPlatsUnUtilisateur(int idUtilisateur);

}
