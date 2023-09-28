package pe.edu.upeu.asistencia.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pe.edu.upeu.asistencia.dtos.InscritoDto;
import pe.edu.upeu.asistencia.models.Inscrito;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T20:35:06-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (JetBrains s.r.o.)"
)
@Component
public class InscritoMapperImpl implements InscritoMapper {

    @Override
    public InscritoDto toMaterialesxDto(Inscrito entidad) {
        if ( entidad == null ) {
            return null;
        }

        InscritoDto.InscritoDtoBuilder inscritoDto = InscritoDto.builder();

        inscritoDto.id( entidad.getId() );
        inscritoDto.cui( entidad.getCui() );
        inscritoDto.tipoCui( entidad.getTipoCui() );
        inscritoDto.evidensPay( entidad.getEvidensPay() );
        inscritoDto.offlinex( entidad.getOfflinex() );
        inscritoDto.actividadId( entidad.getActividadId() );

        return inscritoDto.build();
    }

    @Override
    public Inscrito inscritoCrearDtoToinscrito(InscritoDto.InscritoCrearDto entidadCrearDto) {
        if ( entidadCrearDto == null ) {
            return null;
        }

        Inscrito inscrito = new Inscrito();

        inscrito.setId( entidadCrearDto.id() );
        inscrito.setCui( entidadCrearDto.cui() );
        inscrito.setTipoCui( entidadCrearDto.tipoCui() );
        inscrito.setEvidensPay( entidadCrearDto.evidensPay() );
        inscrito.setOfflinex( entidadCrearDto.offlinex() );

        return inscrito;
    }
}
