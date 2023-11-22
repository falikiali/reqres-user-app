package com.falikiali.reqresuserapp.data.remote.dto

import com.falikiali.reqresuserapp.domain.model.Authentication
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("token") val token: String
) {
    fun toDomain(): Authentication {
        return Authentication(token)
    }
}
