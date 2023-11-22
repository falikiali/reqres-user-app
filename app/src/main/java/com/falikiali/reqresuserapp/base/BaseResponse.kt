package com.falikiali.reqresuserapp.base

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @field:SerializedName("error") val error: String
)
