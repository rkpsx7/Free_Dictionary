package com.dev_akash.freedictionary.feature_dictionary.data.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.dev_akash.freedictionary.utils.Constants.EMPTY_LIST_PLACEHOLDER_IN_STRING
import com.dev_akash.freedictionary.utils.JsonParser
import com.dev_akash.freedictionary.feature_dictionary.domain.model.Meaning
import com.dev_akash.freedictionary.feature_dictionary.domain.model.Phonetic
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class TypeConvertors(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromJsonToMeanings(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json, object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromMeaningsToJson(meaning: List<Meaning>): String {
        return jsonParser.toJson(
            meaning, object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: EMPTY_LIST_PLACEHOLDER_IN_STRING
    }

    @TypeConverter
    fun fromJsonToPhonetics(json: String): List<Phonetic> {
        return jsonParser.fromJson<ArrayList<Phonetic>>(
            json, object : TypeToken<ArrayList<Phonetic>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromPhoneticsToJson(phonetic: List<Phonetic>): String {
        return jsonParser.toJson(
            phonetic, object : TypeToken<ArrayList<Phonetic>>() {}.type
        ) ?: EMPTY_LIST_PLACEHOLDER_IN_STRING
    }

}