package com.dev_akash.freedictionary.feature_dictionary.domain.repository

import com.dev_akash.freedictionary.utils.network_utils.Resource
import com.dev_akash.freedictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepo {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}