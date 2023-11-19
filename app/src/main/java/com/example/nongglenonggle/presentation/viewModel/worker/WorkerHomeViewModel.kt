package com.example.nongglenonggle.presentation.viewModel.worker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nongglenonggle.domain.entity.NoticeContent
import com.example.nongglenonggle.domain.entity.ResumeContent
import com.example.nongglenonggle.domain.entity.SeekerHomeFilterContent
import com.example.nongglenonggle.domain.entity.WorkerHomeData
import com.example.nongglenonggle.domain.usecase.FetchWorkerDataUseCase
import com.example.nongglenonggle.domain.usecase.GetAllNoticeUseCase
import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class WorkerHomeViewModel @Inject constructor(
    private val fetchWorkerDataUseCase: FetchWorkerDataUseCase,
    private val getAllNoticeUseCase: GetAllNoticeUseCase
)
    :ViewModel(){
    private val _userDetail = MutableLiveData<WorkerHomeData?>()
    val userDetail : LiveData<WorkerHomeData?> = _userDetail

    private val _allNotice = MutableLiveData<List<SeekerHomeFilterContent>>()
    val allNotice:LiveData<List<SeekerHomeFilterContent>> = _allNotice

    val _homeResume = MutableLiveData<ResumeContent>()
    val homeResume:LiveData<ResumeContent> = _homeResume

    fun fetchUserInfo(){
        viewModelScope.launch {
            val user = fetchWorkerDataUseCase.invoke()
            _userDetail.value = user
        }
    }
    fun getAllNotice(){
        viewModelScope.launch {
            getAllNoticeUseCase().collect{data->
                _allNotice.value = data
            }
        }
    }

    val _isResume = MutableLiveData<Boolean>()
    val isResume:LiveData<Boolean> = _isResume

    private val _haveData = MutableLiveData<Boolean>()
    val haveData:LiveData<Boolean> = _haveData

    fun updateVisible(){
        if(allNotice.value != null){
            _haveData.value = true
        }
        else{
            _haveData.value = false
        }
    }

    fun fetchResumeVisible(){
        if(_userDetail.value?.refs != null){
            _isResume.value = true
            Log.d("fetchResumeVisible", "true")
        }
        else{
            _isResume.value = false
            Log.d("fetchResumeVisible", "false")
        }
    }
    suspend fun setUserFromRef(documentReference: DocumentReference) : ResumeContent?{
        return try{
            val documentSnapshot = documentReference.get().await()
            documentSnapshot.toObject(ResumeContent::class.java)
        }catch (e:Exception){
            null
        }
    }

    init{
        fetchUserInfo()
        fetchResumeVisible()
        getAllNotice()
        _haveData.value = false
    }
}