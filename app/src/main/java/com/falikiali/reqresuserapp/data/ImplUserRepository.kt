package com.falikiali.reqresuserapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.falikiali.reqresuserapp.data.paging.UsersPagingSource
import com.falikiali.reqresuserapp.data.remote.api.ApiService
import com.falikiali.reqresuserapp.data.remote.dto.ItemUsersResponse
import com.falikiali.reqresuserapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImplUserRepository @Inject constructor(private val apiService: ApiService): UserRepository {

    override fun getUser(): Flow<PagingData<ItemUsersResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 6,
                initialLoadSize = 6,
                prefetchDistance = 1
            ),
            pagingSourceFactory = {
                UsersPagingSource(apiService)
            }
        ).flow
    }

}