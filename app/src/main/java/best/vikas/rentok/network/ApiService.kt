package best.vikas.rentok.network

import best.vikas.rentok.home.data.UserDetailsResponse
import best.vikas.rentok.home.data.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users")
    suspend fun fetchUser(
    ): List<UserListResponse>

    @GET("/users/{username}")
    suspend fun userDetails(
        @Path("username")
        userName: String,
    ) : UserDetailsResponse
}