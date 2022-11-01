/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaprogramaanrd.anrd.Controller;

import com.argentinaprogramaanrd.anrd.Entity.Proyecto;
import com.argentinaprogramaanrd.anrd.Security.Controller.Helpers.Message;
import com.argentinaprogramaanrd.anrd.Service.ProyectoService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author operador
 */
@RestController
@CrossOrigin(origins = {"https://frontendargprog-befe5.web.app", "http://localhost:4200"})
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;

    
    @GetMapping("proyectos")
    public ResponseEntity<List<Proyecto>> index() {
        List<Proyecto> list = proyectoService.index();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("proyectos")
    public ResponseEntity<?> store(@RequestBody Proyecto proyecto) {
        if (StringUtils.isBlank(proyecto.getTitle())) {
            return new ResponseEntity(new Message("El titulo del proyecto es obligatorio."), HttpStatus.BAD_REQUEST);
        }

        if (proyectoService.existsByTitle(proyecto.getTitle())) {
            return new ResponseEntity(new Message("El titulo del proyecto ya existe."), HttpStatus.BAD_REQUEST);
        }

        System.err.println(proyecto.getTitle());
        proyectoService.save(proyecto);
        return new ResponseEntity(new Message("Proyecto creado correctamente."), HttpStatus.OK);
    }

    @PutMapping("proyectos/{id}/update")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Proyecto proyecto) {

        if (StringUtils.isBlank(proyecto.getTitle())) {
            return new ResponseEntity(new Message("El titulo del proyecto es obligatorio."), HttpStatus.BAD_REQUEST);
        }

        if (proyectoService.existsByTitle(proyecto.getTitle()) && proyectoService.getByTitle(proyecto.getTitle()).get().getId() != id) {
            return new ResponseEntity(new Message("El titulo del proyecto ya existe."), HttpStatus.BAD_REQUEST);
        }

        Proyecto proyecto_original = proyectoService.show(id).get();

        proyecto_original.setTitle(proyecto.getTitle());
        proyecto_original.setDateFrom(proyecto.getDateFrom());

        proyecto_original.setDescription(proyecto.getDescription());

        proyecto_original.setImage(proyecto.getImage());

        proyectoService.save(proyecto);
        return new ResponseEntity(new Message("Proyecto guardado correctamente."), HttpStatus.OK);

    }

    @DeleteMapping("proyectos/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Message("No existe el Proyecto que busca"), HttpStatus.NOT_FOUND);
        }

        proyectoService.delete(id);
        return new ResponseEntity(new Message("Proyecto eliminado correctamente."), HttpStatus.OK);

    }
}
