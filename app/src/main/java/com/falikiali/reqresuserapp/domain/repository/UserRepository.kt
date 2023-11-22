package com.falikiali.reqresuserapp.domain.repository

import androidx.paging.PagingData
import com.falikiali.reqresuserapp.data.remote.dto.ItemUsersResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(): Flow<PagingData<ItemUsersResponse>>

}