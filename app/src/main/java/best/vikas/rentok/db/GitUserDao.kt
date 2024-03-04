package best.vikas.rentok.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GitUserDao {

    @Query("SELECT * FROM git_user")
    fun getAllUser(): Flow<List<GitUserEntity>>

    @Insert
    fun insert(gitUserEntity: List<GitUserEntity>)
}