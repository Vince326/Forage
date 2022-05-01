/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.forage.data

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.forage.model.Forageable
import com.example.forage.ui.ForageableDetailFragmentArgs

/**
 * Room database to persist data for the Forage app.
 * This database stores a [Forageable] entity
 */
// TODO: create the database with all necessary annotations, methods, variables, etc.
@Database(entities = arrayOf(Forageable::class), version = 1, exportSchema = false,)
abstract class ForageDatabase : RoomDatabase(){
    abstract fun forageableDao() : ForageableDao

    companion object{
        @Volatile
        private var INSTANCE: ForageDatabase? = null
    //Gets database. Elvis operator returns existing instance of database or creates new instance
        fun getDatabase(context: Context): ForageDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    ForageDatabase::class.java,
                    "forage_database")
                    .createFromAsset("database/forage_database.db")
                    .build()
                INSTANCE = instance

                instance
            }

        }
    }
}

