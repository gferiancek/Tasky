package com.gavinferiancek.tasky.core.domain

data class User(
    val token: String,
    val name: String,
    val id: String,
)

fun User.getInitials(): String {
    // Initials are two characters long, so if <= just return name
    if (name.length <= 2) return name.uppercase()

    val splitName = name.uppercase().split(" ")
    return when(splitName.count()) {
        1 -> splitName[0].substring(0, 2)
        else -> "${splitName.first()[0]}${splitName.last()[0]}"
    }
}