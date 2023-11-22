package com.falikiali.reqresuserapp.domain.usecase

import androidx.paging.PagingData
import com.falikiali.reqresuserapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {

    fun getUsers(): Flow<PagingData<User>>

}