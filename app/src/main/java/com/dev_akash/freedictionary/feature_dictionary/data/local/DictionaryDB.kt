package com.dev_akash.freedictionary.feature_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dev_akash.freedictionary.feature_dictionary.data.local.entity.WordInfoEntity
import com.dev_akash.freedictionary.feature_dictionary.data.util.TypeConvertors


@Database(
    entities = [WordInfoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConvertors::class)
abstract class DictionaryDB : RoomDatabase() {

    abstract val dao: WordInfoDao
}