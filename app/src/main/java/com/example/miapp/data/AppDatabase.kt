package com.example.miapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import android.util.Log
import androidx.room.Room

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null


        fun getDatabase (context: Context): AppDatabase {
            Log.d("Database", "Intentando crear la base de datos...")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                Log.d("Database", "Base de datos creada con éxito")
                instance

            }
        }
    }
}
