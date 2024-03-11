package com.example.quiz1.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.quiz1.CampusApplication
import com.example.quiz1.data.CampusRepository
import com.example.quiz1.model.CampusModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

sealed interface CampusUiState {
    data class Success(val campus: List<CampusModel>) : CampusUiState
    data object Loading : CampusUiState
    data object Error : CampusUiState

}

class CampusViewModel(private val campusRepository: CampusRepository) : ViewModel() {
    var campusUiState: CampusUiState by mutableStateOf(CampusUiState.Loading)
        private set

    private val tag = "RESULT_CAMPUS"
    init {
        getListCampus()
    }

    fun getListCampus() {
        viewModelScope.launch {
            campusUiState = CampusUiState.Loading
            campusRepository.getCampus().enqueue(object : Callback<List<CampusModel>> {
                override fun onResponse(call: Call<List<CampusModel>>, response: Response<List<CampusModel>>) {
                    if (response.isSuccessful) {
                        response.body().let {
                            campusUiState = response.body()?.let { CampusUiState.Success(campus = it) }!!
                        }
                        Log.i(tag, "${response.body()}")
                    }
                }

                override fun onFailure(call: Call<List<CampusModel>>, t: Throwable) {
                    campusUiState = CampusUiState.Error
                    Log.i(tag, "Error: ${t.message}")
                }

            })
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CampusApplication)
                val campusRepository = application.container.campusRepository
                CampusViewModel(campusRepository = campusRepository)
            }

        }
    }
}