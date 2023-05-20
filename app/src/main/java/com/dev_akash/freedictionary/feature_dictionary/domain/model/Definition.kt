package com.dev_akash.freedictionary.feature_dictionary.domain.model

data class Definition(
    val antonyms: List<String>?,
    val definition: String?,
    val example: String?,
    val synonyms: List<String>?
)
