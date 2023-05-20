package com.dev_akash.freedictionary.feature_dictionary.domain.model


data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String,
    val phonetics: List<Phonetic>,
    val word: String
)