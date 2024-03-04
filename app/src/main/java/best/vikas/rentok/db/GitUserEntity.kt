package best.vikas.rentok.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "git_user")
data class GitUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "login")
    val login: String,
    @ColumnInfo(name = "avatar")
    val avatar: String,
)