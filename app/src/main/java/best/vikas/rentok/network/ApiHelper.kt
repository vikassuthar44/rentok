package best.vikas.rentok.network

import best.vikas.rentok.home.data.UserDetailsResponse
import best.vikas.rentok.home.data.UserListResponse
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    suspend fun fetchUsers(): Flow<List<UserListResponse>>

    suspend fun userDetails(userName: String): Flow<UserDetailsResponse>
}