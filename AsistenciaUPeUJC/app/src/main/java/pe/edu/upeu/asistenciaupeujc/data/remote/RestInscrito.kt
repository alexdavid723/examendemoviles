package pe.edu.upeu.asistenciaupeujc.data.remote

import pe.edu.upeu.asistenciaupeujc.modelo.Facultad
import pe.edu.upeu.asistenciaupeujc.modelo.Inscrito
import pe.edu.upeu.asistenciaupeujc.modelo.InscritoReport
import pe.edu.upeu.asistenciaupeujc.modelo.InscritoconActividad
import pe.edu.upeu.asistenciaupeujc.modelo.MsgGeneric
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RestInscrito {
    @GET("/asis/inscrito/list")
    suspend fun reportarInscrito(@Header("Authorization") token:String): Response<List<InscritoReport>>

    @GET("/asis/inscrito/buscar/{id}")
    suspend fun getInscritoId(@Header("Authorization") token:String, @Query("id") id:Long): Response<Inscrito>

    @DELETE("/asis/inscrito/eliminar/{id}")
    suspend fun deleteInscrito(@Header("Authorization") token:String, @Path("id") id:Long): Response<MsgGeneric>

    @PUT("/asis/inscrito/editar/{id}")
    suspend fun actualizarInscrito(@Header("Authorization") token:String, @Path("id") id:Long, @Body inscrito: Inscrito): Response<Inscrito>

    @POST("/asis/inscrito/crear")
    suspend fun insertarInscrito(@Header("Authorization") token:String, @Body inscrito: Inscrito): Response<Inscrito>
}