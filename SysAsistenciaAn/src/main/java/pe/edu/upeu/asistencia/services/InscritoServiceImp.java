/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.asistencia.dtos.InscritoDto;
import pe.edu.upeu.asistencia.exceptions.AppException;
import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;
import pe.edu.upeu.asistencia.mappers.InscritoMapper;
import pe.edu.upeu.asistencia.models.Inscrito;
import pe.edu.upeu.asistencia.repositories.InscritoRepository;

/**
 *
 * @author DELL
 */
@RequiredArgsConstructor
@Service
@Transactional
public class InscritoServiceImp implements InscritoService {

    @Autowired
    private InscritoRepository inscritoRepo;
    @Autowired
    private ActividadService actividadService;

    private final InscritoMapper inscritoMapper;

    @Override
    public Inscrito save(InscritoDto.InscritoCrearDto inscrito) {

        Inscrito insEnt=inscritoMapper.inscritoCrearDtoToinscrito(inscrito);
        insEnt.setActividadId(actividadService.getActividadById(inscrito.actividadId()));
        //matEnt.setModFh(null);
        try {
            return inscritoRepo.save(insEnt);
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Inscrito> findAll() {
        try {
            return inscritoRepo.findAll();
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Inscrito inscritox = inscritoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad not exist with id :" + id));
        inscritoRepo.delete(inscritox);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Inscrito getEntidadById(Long id) {
        Inscrito findInscrito = inscritoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("inscr not exist with id :" + id));
        return findInscrito;
    }

    @Override
    public Inscrito update(Inscrito activiad, Long id) {
        Inscrito inscritox = inscritoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("inscr not exist with id :" + id));
        inscritox.setCui(activiad.getCui());
        inscritox.setTipoCui(activiad.getTipoCui());        
        inscritox.setOfflinex(activiad.getOfflinex());
        inscritox.setEvidensPay(activiad.getOfflinex());
        return inscritoRepo.save(inscritox);         
    }

}
