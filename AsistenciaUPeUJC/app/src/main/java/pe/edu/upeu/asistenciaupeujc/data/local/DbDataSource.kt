package pe.edu.upeu.asistenciaupeujc.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.asistenciaupeujc.data.local.dao.ActividadDao
import pe.edu.upeu.asistenciaupeujc.data.local.dao.InscritoDao
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.modelo.Facultad
import pe.edu.upeu.asistenciaupeujc.modelo.Inscrito

@Database(entities = [Actividad::class, Inscrito::class], version = 5)
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao():ActividadDao
    abstract fun inscritoDao():InscritoDao

}