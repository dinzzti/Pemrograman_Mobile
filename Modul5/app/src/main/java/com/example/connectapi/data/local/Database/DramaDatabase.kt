package com.example.connectapi.data.local.Database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.connectapi.data.local.dao.DramaDao
import com.example.connectapi.data.local.entities.DramaEntity

@Database(entities = [DramaEntity::class], version = 1, exportSchema = false)
abstract class DramaDatabase : RoomDatabase() {

    abstract fun dramaDao(): DramaDao

    companion object {
        @Volatile
        private var INSTANCE: DramaDatabase? = null

        fun getDatabase(context: Context): DramaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DramaDatabase::class.java,
                    "drama_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
