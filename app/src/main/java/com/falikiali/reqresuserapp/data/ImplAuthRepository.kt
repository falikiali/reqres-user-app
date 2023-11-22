package com.falikiali.reqresuserapp.data

import com.falikiali.reqresuserapp.base.BaseResponse
import com.falikiali.reqresuserapp.data.remote.api.ApiService
import com.falikiali.reqresuserapp.data.remote.dto.LoginRequest
import com.falikiali.reqresuserapp.domain.model.Authentication
import com.falikiali.reqresuserapp.domain.repository.AuthRepository
import com.falikiali.reqresuserapp.helper.ResultState
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class ImplAuthRepository @Inject constructor(private val apiService: ApiService): AuthRepository {

    override suspend fun login(req: LoginRequest): Flow<ResultState<Authentication>> {
        return flow {
            emit(ResultState.Loading)
            try {
                val response = apiService.login(req)

                if (response.isSuccessful) {
                    val data = response.body()?.toDomain()
                    emit(ResultState.Success(data!!))
                } else {
                    response.errorBody()?.let {
                        val er = Gson().fromJson(it.charStream(), BaseResponse::class.java)
                        emit(ResultState.Failed(er.error, response.code()))
                    }
                }
            } catch (e: HttpException) {
                emit(ResultState.Failed(e.message(), e.code()))
            } catch (e: Throwable) {
                emit(ResultState.Failed(e.message.toString(), 0))
            }
        }.flowOn(Dispatchers.IO)
    }

}