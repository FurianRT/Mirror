package com.furianrt.domain.entities

data class Mood(
    val id: String,
    val name: String,
    val category: Category,
    val custom: Boolean,
    val icon: MoodIcon
) {

    enum class Category {
        GREAT,
        GOOD,
        OKAY,
        BAD,
        AWFUL
    }
}
