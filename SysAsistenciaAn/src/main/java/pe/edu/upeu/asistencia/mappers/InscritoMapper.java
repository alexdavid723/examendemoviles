package pe.edu.upeu.asistencia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.asistencia.dtos.InscritoDto;
import pe.edu.upeu.asistencia.models.Inscrito;
@Mapper(componentModel = "spring")
public interface InscritoMapper {
    InscritoDto toMaterialesxDto(Inscrito entidad);

    //@Mapping(target = "id", ignore = true)
    @Mapping(target = "actividadId", ignore = true)
    Inscrito inscritoCrearDtoToinscrito(InscritoDto.InscritoCrearDto entidadCrearDto);
}
