package com.falikiali.reqresuserapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.falikiali.reqresuserapp.data.remote.api.ApiService
import com.falikiali.reqresuserapp.data.remote.dto.ItemUsersResponse

class UsersPagingSource(private val apiService: ApiService): PagingSource<Int, ItemUsersResponse>() {
    override fun getRefreshKey(state: PagingState<Int, ItemUsersResponse>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemUsersResponse> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getUsers(page)

            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}