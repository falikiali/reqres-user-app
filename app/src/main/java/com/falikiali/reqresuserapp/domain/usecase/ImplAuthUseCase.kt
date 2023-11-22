package com.falikiali.reqresuserapp.domain.usecase

import com.falikiali.reqresuserapp.data.remote.dto.LoginRequest
import com.falikiali.reqresuserapp.domain.model.Authentication
import com.falikiali.reqresuserapp.domain.repository.AuthRepository
import com.falikiali.reqresuserapp.helper.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImplAuthUseCase @Inject constructor(private val authRepository: AuthRepository): AuthUseCase {

    override suspend fun login(req: LoginRequest): Flow<ResultState<Authentication>> {
        return authRepository.login(req)
    }

}