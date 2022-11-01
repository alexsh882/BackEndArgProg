/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaprogramaanrd.anrd.Repository;

import com.argentinaprogramaanrd.anrd.Entity.Proyecto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author operador
 */
@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    public Optional<Proyecto> findByTitle(String title);

    public boolean existsByTitle(String title);
}
