package com.furianrt.domain.entities

data class Image(
    val id: String,
    val entryId: String,
    val url: String,
    val addedAt: Long,
    val order: Int
)
