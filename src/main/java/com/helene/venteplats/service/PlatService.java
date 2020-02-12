package com.helene.venteplats.service;

import com.helene.venteplats.model.Plat;
import com.helene.venteplats.repository.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatService {
    @Autowired
    PlatRepository platRepository;

    public Plat recupererPlat(int id){

        return platRepository.getOne(id);

    }

    public List<Plat> recupererTousLesPlats() {
        return platRepository.findAll();
    }
}
