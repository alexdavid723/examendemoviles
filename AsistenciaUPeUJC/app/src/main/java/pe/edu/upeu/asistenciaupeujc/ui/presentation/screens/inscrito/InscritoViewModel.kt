package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.inscrito
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Inscrito
import pe.edu.upeu.asistenciaupeujc.modelo.InscritoconActividad
import pe.edu.upeu.asistenciaupeujc.repository.InscritoRepository
import javax.inject.Inject
@HiltViewModel
class InscritoViewModel @Inject constructor(
    private val incriRepo: InscritoRepository,
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val activ: LiveData<List<InscritoconActividad>> by lazy {
        incriRepo.reportarInscritos()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addInscrito() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            }
    }

    fun deleteInscrito(toDelete: InscritoconActividad) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMINAR", toDelete.toString())
            incriRepo.deleteInscrito(toDelete);
        }
    }
}