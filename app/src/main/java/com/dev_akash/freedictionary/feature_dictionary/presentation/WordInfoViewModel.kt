package com.dev_akash.freedictionary.feature_dictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev_akash.freedictionary.utils.network_utils.Resource
import com.dev_akash.freedictionary.utils.ui_utils.UIEvents
import com.dev_akash.freedictionary.feature_dictionary.domain.model.WordInfo
import com.dev_akash.freedictionary.feature_dictionary.domain.use_cases.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _wordInfoState = mutableStateOf(WordInfoState())
    val wordInfoState: State<WordInfoState> = _wordInfoState

    private val _eventFlow = MutableSharedFlow<UIEvents>()
    val eventFlow: SharedFlow<UIEvents> = _eventFlow


    private var searchJob: Job? = null
    fun onSearch(word: String) {
        _searchQuery.value = word
        setLoading()
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            getWordInfo(word).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _wordInfoState.value = _wordInfoState.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _wordInfoState.value = _wordInfoState.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(
                            UIEvents.ShowSnackBar(
                                result.message ?: ""
                            )
                        )
                    }

                    is Resource.Loading -> {
                        setLoading(result.data ?: emptyList())
                    }
                }
            }.launchIn(this)
        }
    }

    private fun setLoading(wordInfoItems: List<WordInfo> = emptyList()) {
        _wordInfoState.value = _wordInfoState.value.copy(
            wordInfoItems = wordInfoItems,
            isLoading = true
        )
    }

    fun clearSearchField(){
        _searchQuery.value = ""
        _wordInfoState.value = WordInfoState()
    }
}