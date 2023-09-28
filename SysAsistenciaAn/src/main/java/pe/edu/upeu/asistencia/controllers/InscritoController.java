/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.asistencia.dtos.InscritoDto;
import pe.edu.upeu.asistencia.models.Actividad;
import pe.edu.upeu.asistencia.models.Inscrito;
import pe.edu.upeu.asistencia.services.InscritoService;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/asis/inscrito")
public class InscritoController {

    @Autowired
    private InscritoService inscritoService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Inscrito>> listActividad() {
        List<Inscrito> actDto = inscritoService.findAll();

        //Gson gson = new Gson();
        //String jsonCartList = gson.toJson(actDto);
        return ResponseEntity.ok().body(actDto);
        //return new ResponseEntity<>(actDto, HttpStatus.OK);
    }
    @PostMapping("/crear")
    public ResponseEntity<Inscrito> saveInscrito(@RequestBody InscritoDto.InscritoCrearDto inscrito) {
        Inscrito data = inscritoService.save(inscrito);
        return ResponseEntity.ok(data);
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Inscrito> getEntidadById(@PathVariable Long id) {
        Inscrito inscrito = inscritoService.getEntidadById(id);
        return ResponseEntity.ok(inscrito);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteInscrito(@PathVariable Long id) {
        Inscrito inscrito = inscritoService.getEntidadById(id);
        return ResponseEntity.ok(inscritoService.delete(inscrito.getId()));
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<Inscrito> updateInscrito(@PathVariable Long id, @RequestBody Inscrito inscritoDetails) {
        Inscrito updatedInscrito = inscritoService.update(inscritoDetails, id);
        return ResponseEntity.ok(updatedInscrito);
    }
}
