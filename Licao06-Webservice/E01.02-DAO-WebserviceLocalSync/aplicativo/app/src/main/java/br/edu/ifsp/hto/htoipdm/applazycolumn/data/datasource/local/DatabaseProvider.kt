package br.edu.ifsp.hto.htoipdm.applazycolumn.data.datasource.local

import android.content.Context
import androidx.room.Room

class DatabaseProvider {
    companion object {
        @Volatile
        private var db: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return db ?: synchronized(this) {
                db ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "banco"
                ).build().also { db = it }
            }
        }
    }
}