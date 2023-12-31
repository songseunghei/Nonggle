package com.capstone.nongglenonggle.presentation.viewModel.farmer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.nongglenonggle.domain.entity.NoticeContent
import com.capstone.nongglenonggle.domain.usecase.GetNoticeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FarmerNoticeCompleteViewModel @Inject constructor(
    private val getNoticeUseCase: GetNoticeUseCase
) : ViewModel() {
    private val _noticeDetail = MutableStateFlow<NoticeContent?>(null)
    val isDataReady = MutableLiveData<Boolean>()
    val noticeDetail: StateFlow<NoticeContent?> get() = _noticeDetail

    private val _isWorker = MutableLiveData<Boolean>()
    val isWorker:LiveData<Boolean> = _isWorker

    fun noticeForFarmer(){
        _isWorker.postValue(false)
    }

    fun fetchNoticeDetail(uid:String){
        viewModelScope.launch {
            val data= getNoticeUseCase.invoke(uid).collect{data->
                _noticeDetail.value = data
            }
            isDataReady.value = true
        }
    }

    init{
        isDataReady.value = false
        _isWorker.value = true
    }
}