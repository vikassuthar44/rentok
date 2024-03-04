/*
package best.vikas.rentok.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import best.vikas.rentok.home.data.UserListResponse
import javax.inject.Inject

class DataSource @Inject constructor(
    private val apiHelper: ApiHelper,
) : PagingSource<Int, UserListResponse>() {

    override fun getRefreshKey(state: PagingState<Int, UserListResponse>): Int? {
        return  state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserListResponse> {
        return try {
            val currentPage = params.key ?: 1
            val userListResponse = apiHelper.fetchUsers()
            LoadResult.Page(
                data = userListResponse.body(),
                prevKey = if(currentPage == 1) null else currentPage - 1,
                nextKey = if(userListResponse.body().isEmpty()) null
            )
        }
    }

}*/
