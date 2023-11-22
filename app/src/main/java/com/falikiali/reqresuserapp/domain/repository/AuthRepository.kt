package com.falikiali.reqresuserapp.domain.repository

import com.falikiali.reqresuserapp.data.remote.dto.LoginRequest
import com.falikiali.reqresuserapp.domain.model.Authentication
import com.falikiali.reqresuserapp.helper.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(req: LoginRequest): Flow<ResultState<Authentication>>

}