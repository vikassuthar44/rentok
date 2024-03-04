package best.vikas.rentok.db

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitUserRepoImpl @Inject constructor(
    private val gitUserDao: GitUserDao,
) : GitUserRepo {
    override suspend fun getAllUser(): Flow<List<GitUserEntity>> = gitUserDao.getAllUser()

    override suspend fun insert(gitUserEntity: List<GitUserEntity>) =
        gitUserDao.insert(gitUserEntity)
}