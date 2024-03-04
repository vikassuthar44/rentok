package best.vikas.rentok.home.data

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)
