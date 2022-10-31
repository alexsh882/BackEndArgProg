/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaprogramaanrd.anrd.Service;

import com.argentinaprogramaanrd.anrd.Entity.HaS;
import com.argentinaprogramaanrd.anrd.Repository.HaSRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author operador
 */
@Transactional
@Service
public class HaSService {

    @Autowired
    HaSRepository hasRepository;

    public List<HaS> index() {
        return hasRepository.findAll();
    }

    public Optional<HaS> show(int id) {
        return hasRepository.findById(id);
    }

    public Optional<HaS> getByName(String name) {
        return hasRepository.findByName(name);
    }

    public void save(HaS has) {
        hasRepository.save(has);
    }

    public void delete(int id) {
        hasRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return hasRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return hasRepository.existsByName(name);
    }

}
