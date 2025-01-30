package com.ghosttrio.gilbadak.auth.controller.model.request

data class SignupRequest(
    val email: String,
    val name: String,
    val phone: String,
    val age: String,
    val admissionYear: String
)
