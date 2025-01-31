package com.ghosttrio.gilbadak.auth.controller.model.request

data class SignupServiceRequest(
    val email: String,
    val name: String,
    val phone: String,
    val age: String,
    val admissionYear: String
)
