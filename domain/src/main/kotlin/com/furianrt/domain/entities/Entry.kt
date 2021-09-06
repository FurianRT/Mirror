package com.furianrt.domain.entities

data class Entry(
    val id: String,
    val note: String,
    val timestamp: Long,
    val updatedAt: Long,
    val createdAt: Long,
    val mood: Mood,
    val occupations: Set<Occupation>,
    val images: List<Image>
)