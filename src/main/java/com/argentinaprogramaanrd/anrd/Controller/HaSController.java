/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaprogramaanrd.anrd.Controller;

import com.argentinaprogramaanrd.anrd.Entity.HaS;
import com.argentinaprogramaanrd.anrd.Security.Controller.Helpers.Message;
import com.argentinaprogramaanrd.anrd.Service.HaSService;
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
@CrossOrigin(origins = "https://frontendargprog-befe5.web.app")
//@CrossOrigin(origins = "http://localhost:4200")
public class HaSController {

    @Autowired
    HaSService hasService;

    @GetMapping("/has")
    public ResponseEntity<List<HaS>> index() {
        List<HaS> list = hasService.index();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/has")
    public ResponseEntity<?> store(@RequestBody HaS has) {
        if (StringUtils.isBlank(has.getName())) {
            return new ResponseEntity(new Message("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (has.getPorcentaje() <= 0) {
            return new ResponseEntity(new Message("El porcentaje de H and S es obligatorio."), HttpStatus.BAD_REQUEST);
        }

        if (hasService.existsByName(has.getName())) {
            return new ResponseEntity(new Message("El nombre ya existe."), HttpStatus.BAD_REQUEST);
        }
        System.err.println(has.getName());
        hasService.save(has);
        return new ResponseEntity(new Message("H y S creada correctamente."), HttpStatus.OK);
    }

    @PutMapping("/has/{id}/update")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody HaS has) {

        if (!hasService.existsById(id)) {
            return new ResponseEntity(new Message("No existe la educación que busca"), HttpStatus.NOT_FOUND);
        }

        if (hasService.existsByName(has.getName()) && hasService.getByName(has.getName()).get().getId() != id) {
            return new ResponseEntity(new Message("El nombre ya existe."), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(has.getName())) {
            return new ResponseEntity(new Message("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (has.getPorcentaje() <= 0) {
            return new ResponseEntity(new Message("El porcentaje de H and S es obligatorio."), HttpStatus.BAD_REQUEST);
        }

        HaS educacion_original = hasService.show(id).get();

        educacion_original.setName(has.getName());
        educacion_original.setPorcentaje(has.getPorcentaje());

        hasService.save(educacion_original);
        return new ResponseEntity(new Message("H and S guardada correctamente."), HttpStatus.OK);

    }

    @DeleteMapping("/has/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!hasService.existsById(id)) {
            return new ResponseEntity(new Message("No existe el H and S que busca"), HttpStatus.NOT_FOUND);
        }

        hasService.delete(id);
        return new ResponseEntity(new Message("H and S eliminada correctamente."), HttpStatus.OK);

    }
}
