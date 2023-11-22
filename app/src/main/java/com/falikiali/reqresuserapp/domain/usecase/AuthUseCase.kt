package com.falikiali.reqresuserapp.domain.usecase

import com.falikiali.reqresuserapp.data.remote.dto.LoginRequest
import com.falikiali.reqresuserapp.domain.model.Authentication
import com.falikiali.reqresuserapp.helper.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    suspend fun login(req: LoginRequest): Flow<ResultState<Authentication>>

}