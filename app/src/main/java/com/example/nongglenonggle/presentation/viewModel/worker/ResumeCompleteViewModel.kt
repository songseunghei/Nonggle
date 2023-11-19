package com.example.nongglenonggle.presentation.viewModel.worker

import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.nongglenonggle.domain.entity.Model
import com.example.nongglenonggle.domain.entity.ResumeContent
import com.example.nongglenonggle.domain.usecase.GetResumeUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ResumeCompleteViewModel @Inject constructor(
    private val getResumeUseCase: GetResumeUseCase
): ViewModel(){
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val _resumeDetail = MutableLiveData<ResumeContent>()
    val resumeDetail:LiveData<ResumeContent> = _resumeDetail

    val _resumeforFarmer = MutableLiveData<Boolean>()
    val resumeforFarmer:LiveData<Boolean> = _resumeforFarmer


    var certification:String=""
    var locations:String=""
    var items:String=""

    fun fetchResumeDetail(setting1:String, setting2:String,uid:String) {
        viewModelScope.launch {
            try {
                getResumeUseCase.invoke(setting1,setting2,uid).collect { data ->
                    if (data != null) {
                        _resumeDetail.value = data
                        Log.d("ResumeCompleteViewModel","${resumeforFarmer.value}")
                    } else {
                        Log.e("ResumeCompleteViewModel", "Data is null")
                    }
                }
            } catch (e: Exception) {
                Log.e("ResumeCompleteViewModel", "Error fetching data", e)
            }
        }
    }

    suspend fun getOffererName():String{
        val uid = firebaseAuth.currentUser?.uid
        return withContext(Dispatchers.IO){
            try{
                val snapshot = firestore.collection("Farmer").document(uid!!).get().await()
                snapshot.getString("userName").orEmpty()
            }catch (e:Exception){
                Log.e("getOffererName","${e}")
                ""
            }
        }
    }

    init{
        _resumeforFarmer.postValue(true)
    }

}