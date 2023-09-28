package pe.edu.upeu.asistenciaupeujc.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "inscrito")
data class Inscrito(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var cui: String,
    var tipoCui: String,
    var evidensPay: String,
    var offlinex: String,
    var actividadId: Long
)
data class InscritoconActividad(
    var id: Long,
    var cui: String,
    var tipoCui: String,
    var evidensPay: String,
    var offlinex: String,
    var actividadId: Long,
    var nombreActividad: String
)

data class InscritoReport(
    var id: Long,
    var cui: String,
    var tipoCui: String,
    var evidensPay: String,
    var offlinex: String,
    var actividadId: Actividad
)

