package com.capstone.nongglenonggle.domain.entity

data class WorkerSearchRecommend(
    val title: String="",
    val firstAddress: String = "",
    val workType : String="",
    val pay : List<Any> = listOf(),
    val uid:String="",
    val imageUrl:String="",
    val recruitPeriod : Map<String, Any> = emptyMap(),
)



