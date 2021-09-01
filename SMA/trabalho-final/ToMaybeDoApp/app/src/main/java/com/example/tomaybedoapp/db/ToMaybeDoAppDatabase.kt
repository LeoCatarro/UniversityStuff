package com.example.tomaybedoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tomaybedoapp.models.Note

@Database(entities = [Note::class], version = 1, exportSchema = true)
abstract class ToMaybeDoAppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    //Companion object -> Everything inside of it will be visible in other classes
    companion object {

        //Make NoteDatabase a singleton class(our notedatabase will have only one instance of it class)
        private var INSTANCE: ToMaybeDoAppDatabase? = null

        fun getDatabase(context: Context): ToMaybeDoAppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            //Everything on it will be protected from concurrent execution by multiple threads
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToMaybeDoAppDatabase::class.java,
                    "toMaybeDoApp_database"
                ).build()

                INSTANCE = instance

                return instance
            }
        }
    }
}