package best.vikas.rentok.db

import kotlinx.coroutines.flow.Flow

interface GitUserRepo {

    suspend fun getAllUser(): Flow<List<GitUserEntity>>

    suspend fun insert(gitUserEntity: List<GitUserEntity>)
}