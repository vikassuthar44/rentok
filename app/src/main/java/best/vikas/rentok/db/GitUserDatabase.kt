package best.vikas.rentok.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GitUserEntity::class], version = 1, exportSchema = false)
abstract class GitUserDatabase : RoomDatabase() {

    abstract fun gitUserDao(): GitUserDao

}