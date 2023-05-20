package com.dev_akash.freedictionary.feature_dictionary.data.remote.dto

import com.dev_akash.freedictionary.feature_dictionary.domain.model.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String
) {
    fun toMeaning(): Meaning {
        return Meaning(
            definitions.map { it.toDefinition() },
            partOfSpeech
        )
    }
}