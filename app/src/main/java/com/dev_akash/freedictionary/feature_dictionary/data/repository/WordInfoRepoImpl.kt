package com.dev_akash.freedictionary.feature_dictionary.data.repository

import android.util.Log
import com.dev_akash.freedictionary.utils.network_utils.Resource
import com.dev_akash.freedictionary.utils.network_utils.validate
import com.dev_akash.freedictionary.feature_dictionary.data.local.WordInfoDao
import com.dev_akash.freedictionary.feature_dictionary.data.remote.DictionaryApi
import com.dev_akash.freedictionary.feature_dictionary.domain.model.WordInfo
import com.dev_akash.freedictionary.feature_dictionary.domain.repository.WordInfoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class WordInfoRepoImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepo {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {

            val remoteWordInfos = api.getWordInfo(word).validate()
            if (remoteWordInfos is Resource.Error) {
                emit(
                    Resource.Error(
                        remoteWordInfos.message?:"",
                        wordInfos
                    )
                )
            }
            remoteWordInfos.data?.let { wordInfoDtos ->

                dao.deleteWordInfos(wordInfoDtos.map { it.word })
                dao.insertWordInfoList(wordInfoDtos.map { it.toWordInfoEntity() })

            }
        } catch (e: IOException) {
            Log.d("rkpsx7DictionaryTesting","IOException repo aaya")
            emit(
                Resource.Error(
                    "",
                    wordInfos
                )
            )
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}
