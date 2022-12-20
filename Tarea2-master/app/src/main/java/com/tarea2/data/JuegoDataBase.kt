package com.tarea2.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tarea2.model.Juego

@Database(entities = [Juego::class], version = 1, exportSchema = false)
abstract class JuegoDataBase: RoomDatabase() {
    abstract  fun JuegoDao() : JuegoDao

    companion object{
       @Volatile
       private var INSTANCE: JuegoDataBase? = null

        fun getDataBase(context: android.content.Context) : JuegoDataBase{
            val temp = INSTANCE
            if(temp != null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JuegoDataBase::class.java,
                    "Juego_database").build()
                    INSTANCE = instance
                return instance
            }
        }
    }
}