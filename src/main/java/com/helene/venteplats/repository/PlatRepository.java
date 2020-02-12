package com.helene.venteplats.repository;

import com.helene.venteplats.model.Plat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatRepository extends JpaRepository<Plat,Integer> {

}
