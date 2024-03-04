package best.vikas.rentok.util

sealed class Screen(
    val route: String,
) {
    data object UserList: Screen("userlist")
    data object UserDetail: Screen("userdetail")
}