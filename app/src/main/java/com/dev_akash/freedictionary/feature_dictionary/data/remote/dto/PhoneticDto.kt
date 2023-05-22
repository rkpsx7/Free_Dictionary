package com.dev_akash.freedictionary.feature_dictionary.data.remote.dto

import com.dev_akash.freedictionary.feature_dictionary.domain.model.Phonetic

data class PhoneticDto(
    val audio: String?,
    val text: String?
) {
    fun toPhonetic(): Phonetic {
        return Phonetic(
            audio,
            text
        )
    }
}