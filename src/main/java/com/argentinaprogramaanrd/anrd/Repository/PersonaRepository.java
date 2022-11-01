/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaprogramaanrd.anrd.Repository;

import com.argentinaprogramaanrd.anrd.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author operador
 */

@Repository
public interface PersonaRepository extends JpaRepository<Persona , Integer> {
   public Optional<Persona> findByName(String name);
   public boolean existsByName(String name);
}
