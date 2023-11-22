package com.falikiali.reqresuserapp.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.falikiali.reqresuserapp.domain.model.User
import com.falikiali.reqresuserapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImplUserUseCase @Inject constructor(private val userRepository: UserRepository): UserUseCase {

    override fun getUsers(): Flow<PagingData<User>> {
        return userRepository.getUser().map { data ->
            data.map { it.toDomain() }
        }
    }

}