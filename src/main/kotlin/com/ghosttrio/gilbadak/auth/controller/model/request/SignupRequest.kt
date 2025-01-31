package com.ghosttrio.gilbadak.auth.controller.model.request

import com.ghosttrio.gilbadak.util.ServiceRequestConverter

data class SignupRequest(
    val email: String,
    val name: String,
    val phone: String,
    val age: String,
    val admissionYear: String
): ServiceRequestConverter<SignupServiceRequest> {

    override fun convert(): SignupServiceRequest {
        return SignupServiceRequest(email, name, phone, age, admissionYear)
    }
}
