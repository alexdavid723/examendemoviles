package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.inscrito

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import com.google.gson.Gson
import pe.edu.upeu.asistenciaupeujc.modelo.ComboModel
import pe.edu.upeu.asistenciaupeujc.modelo.Inscrito
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.Spacer
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.NameTextField

@Composable
    fun InscritoForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: InscritoFormViewModel = hiltViewModel()
) {
    val inscritoD:Inscrito
    if (text!="0"){
        inscritoD = Gson().fromJson(text, Inscrito::class.java)
    }else{
        inscritoD= Inscrito(0,"","", "","", 0)
    }
    formulario(inscritoD.id!!,
        darkMode,
        navController,
        inscritoD,
        viewModel
    )

}



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MissingPermission",
    "CoroutineCreationDuringComposition"
)
@Composable
fun formulario(id:Long,
               darkMode: MutableState<Boolean>,
               navController: NavHostController,
               inscrito: Inscrito,
               viewModel: InscritoFormViewModel
){

    Log.i("VERRR", "d: "+inscrito?.id!!)
    val person=Inscrito(0,"","", "","", 0)

    Scaffold(modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)){
        BuildEasyForms { easyForm ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                NameTextField(easyForms = easyForm, text =inscrito?.cui!!,"cui:", MyFormKeys.NAME )
                NameTextField(easyForms = easyForm, text =inscrito?.tipoCui!!,"tipocui:", MyFormKeys.NAME )
                NameTextField(easyForms = easyForm, text =inscrito?.evidensPay!!,"evidensPay:", MyFormKeys.NAME )
                NameTextField(easyForms = easyForm, text =inscrito?.offlinex!!,"offlinex:", MyFormKeys.NAME )

                NameTextField(easyForms = easyForm, text =inscrito?.actividadId!!.toString(),"actividadID:", MyFormKeys.NAME )


                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        person.cui=(lista.get(0) as EasyFormsResult.StringResult).value
                        person.tipoCui=splitCadena((lista.get(1) as EasyFormsResult.GenericStateResult<String>).value)
                        person.evidensPay=splitCadena((lista.get(2) as EasyFormsResult.GenericStateResult<String>).value)
                        person.offlinex=(lista.get(3) as EasyFormsResult.GenericStateResult<String>).value
                        person.actividadId = (lista.get(4) as EasyFormsResult.GenericStateResult<String>).value.toLong()

                        if (id==0.toLong()){
                            Log.i("AGREGAR", "OF:"+ person.offlinex)
                            Log.i("AGREGARID", "OF:"+ person.actividadId)
                            viewModel.addInscrito(person)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editInscrito(person)
                            navController.navigate(Destinations.ActividadUI.route)
                        }

                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.ActividadUI.route)
                    }
                }
            }
        }
    }
}


fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}