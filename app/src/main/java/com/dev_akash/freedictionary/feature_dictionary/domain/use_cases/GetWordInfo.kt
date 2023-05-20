package com.dev_akash.freedictionary.feature_dictionary.domain.use_cases

import com.dev_akash.freedictionary.utils.network_utils.Resource
import com.dev_akash.freedictionary.feature_dictionary.domain.model.WordInfo
import com.dev_akash.freedictionary.feature_dictionary.domain.repository.WordInfoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWordInfo @Inject constructor(
    private val repo: WordInfoRepo
) {

    operator fun invoke(word:String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()){
            return flow {  }
        }

        return repo.getWordInfo(word)
    }
}