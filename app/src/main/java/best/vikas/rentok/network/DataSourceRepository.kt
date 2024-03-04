package best.vikas.rentok.network


import best.vikas.rentok.db.GitUserDao
import best.vikas.rentok.db.GitUserEntity
import best.vikas.rentok.home.data.UserListResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class DataSourceRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val gitUserDao: GitUserDao,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun fetchUsers(): Flow<List<UserListResponse>> =
        gitUserDao.getAllUser().flatMapConcat { localUsers ->
            if (localUsers.isEmpty()) {
                val userListEntity = mutableListOf<GitUserEntity>()
                apiHelper.fetchUsers().collect {
                    it.forEach { user ->
                        userListEntity.add(
                            GitUserEntity(
                                login = user.login,
                                avatar = user.avatarUrl
                            )
                        )
                    }
                }
                gitUserDao.insert(userListEntity)
                return@flatMapConcat apiHelper.fetchUsers()
            } else {
                val result = mutableListOf<UserListResponse>()
                localUsers.forEach {
                    result.add(
                        UserListResponse(
                            login = it.login,
                            avatarUrl = it.avatar
                        )
                    )
                }
                return@flatMapConcat flowOf(result)
            }
        }

    suspend fun userDetail(userName: String) = apiHelper.userDetails(userName = userName)
}

