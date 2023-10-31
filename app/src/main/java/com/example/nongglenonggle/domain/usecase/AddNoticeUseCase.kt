package com.example.nongglenonggle.domain.usecase

import com.example.nongglenonggle.domain.entity.NoticeContent
import com.example.nongglenonggle.domain.repository.FirestoreSetRepository
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class AddNoticeUseCase @Inject constructor(private val firestoreSetRepository: FirestoreSetRepository) {
    suspend operator fun invoke(noticeContent: NoticeContent):DocumentReference{
        return firestoreSetRepository.addNoticeData(noticeContent)
    }
}