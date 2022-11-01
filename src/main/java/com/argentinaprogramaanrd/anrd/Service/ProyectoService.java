/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaprogramaanrd.anrd.Service;

import com.argentinaprogramaanrd.anrd.Entity.Proyecto;
import com.argentinaprogramaanrd.anrd.Repository.ProyectoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author operador
 */
@Service
@Transactional
public class ProyectoService {
    @Autowired
    ProyectoRepository proyectoRepository;

   
    public List<Proyecto> index() {
        return proyectoRepository.findAll();
    }

        
    public Optional<Proyecto> show(int id) {
        return proyectoRepository.findById(id);
    }

    
    public void save(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    
    public void delete(int id) {
        proyectoRepository.deleteById(id);

    }
    
    public Optional<Proyecto> getByTitle(String name) {
        return proyectoRepository.findByTitle(name);
    }
    
    public boolean existsById(int id){
        return proyectoRepository.existsById(id);                
    }
    
    public boolean existsByTitle(String name) {
        return proyectoRepository.existsByTitle(name);
    }
}
