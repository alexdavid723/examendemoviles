package pe.edu.upeu.asistenciaupeujc.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujc.modelo.Inscrito
import pe.edu.upeu.asistenciaupeujc.modelo.InscritoconActividad

@Dao
interface InscritoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarInscrito(inscrito: Inscrito)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarInscritos(inscrito: List<Inscrito>)

    @Update
    suspend fun modificarInscrito(inscrito: Inscrito)

    @Query("delete  from inscrito where id=:inscrito")
    suspend fun eliminarInscrito(inscrito: Long)
    /*@Delete
    suspend fun eliminarMaterialesx(materialesx: Materialesx)*/

    @Query("select * from inscrito where id=:idx")
    fun buscarInscrito(idx: Long): LiveData<Inscrito>

    @Transaction
    @Query("select m.*, a.nombreActividad from inscrito m, actividad a where m.actividadId=a.id")
    fun reportaInscrito():LiveData<List<InscritoconActividad>>


}