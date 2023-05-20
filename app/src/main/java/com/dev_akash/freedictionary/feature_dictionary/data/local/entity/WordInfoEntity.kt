package com.dev_akash.freedictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dev_akash.freedictionary.feature_dictionary.domain.model.Meaning
import com.dev_akash.freedictionary.feature_dictionary.domain.model.Phonetic
import com.dev_akash.freedictionary.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    @PrimaryKey
    val id: Long? = null,
    val meanings: List<Meaning>,
    val phonetic: String,
    val phonetics: List<Phonetic>,
    val word: String
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings,
            phonetic,
            phonetics,
            word
        )
    }
}
