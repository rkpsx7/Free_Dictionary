package com.dev_akash.freedictionary

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.dev_akash.freedictionary.feature_dictionary.presentation.WordInfoViewModel
import com.dev_akash.freedictionary.feature_dictionary.presentation.screens.WordInfoItem
import com.dev_akash.freedictionary.theme.AppTheme
import com.dev_akash.freedictionary.theme.FreeDictionaryTheme
import com.dev_akash.freedictionary.theme.resources.colorDarkBackground
import com.dev_akash.freedictionary.theme.resources.colorDarkPrimary
import com.dev_akash.freedictionary.theme.resources.colorLightBackground
import com.dev_akash.freedictionary.utils.AppUtils
import com.dev_akash.freedictionary.utils.ui_utils.UIEvents
import com.dev_akash.freedictionary.utils.ui_utils.getVectorIdByTheme
import com.dev_akash.freedictonary.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalComposeUiApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setStatusBarColor()
            FreeDictionaryTheme {
                val viewModel: WordInfoViewModel by viewModels()
                val state = viewModel.wordInfoState.value
                val scaffoldState = rememberScaffoldState()
                val keyboardController = LocalSoftwareKeyboardController.current

                LaunchedEffect(key1 = true) {
                    viewModel.eventFlow.collectLatest {
                        when (it) {
                            is UIEvents.ShowSnackBar -> {
                                keyboardController?.hide()
                                scaffoldState.snackbarHostState.showSnackbar(it.msg)
                            }
                        }
                    }
                }

                Scaffold(
                    scaffoldState = scaffoldState,
                ) {
                    Box(
                        modifier = Modifier.background(AppTheme.colors.background)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Free Dictionary",
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .wrapContentHeight(),
                                    style = AppTheme.typography.h1,
                                    color = AppTheme.colors.primary
                                )

                                IconButton(onClick = {
                                    AppUtils.toggleTheme()
                                }) {
                                    Icon(
                                        modifier = Modifier
                                            .height(24.dp)
                                            .width(24.dp),
                                        imageVector = ImageVector.vectorResource(
                                            id = getVectorIdByTheme(
                                                lightRes = R.drawable.ic_moon,
                                                darkRes = R.drawable.ic_white_sun
                                            )
                                        ),
                                        contentDescription = "Toggle Theme",
                                        tint = Color.Unspecified
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 2.dp,
                                    color = AppTheme.colors.primary,
                                    shape = RoundedCornerShape(4.dp)
                                ),
                                value = viewModel.searchQuery.value,
                                onValueChange = viewModel::onSearch,
                                label = {
                                    Text(text = "Find word", color = AppTheme.colors.textSecondary)
                                },
                                textStyle = androidx.compose.ui.text.TextStyle(
                                    color = AppTheme.colors.textPrimary, fontSize = 16.sp
                                ),
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    cursorColor = AppTheme.colors.primary,
                                    focusedIndicatorColor = AppTheme.colors.primary,
                                    unfocusedIndicatorColor = AppTheme.colors.secondary
                                ),
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = "Search word",
                                        tint = colorDarkPrimary
                                    )
                                },
                                trailingIcon = {
                                    if (viewModel.searchQuery.value.isNotBlank()) {
                                        IconButton(onClick = { viewModel.clearSearchField() }) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = "Clear",
                                                tint = colorDarkPrimary
                                            )
                                        }
                                    }
                                })
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(state.wordInfoItems.size) { index ->
                                    val wordInfo = state.wordInfoItems[index]
                                    if (index > 0) Spacer(modifier = Modifier.height(10.dp))

                                    WordInfoItem(wordInfo = wordInfo)
                                    if (index < state.wordInfoItems.size - 1) Divider(
                                        color = if (AppUtils.isDarkMode) colorLightBackground else colorDarkBackground
                                    )
                                }
                            }
                        }
                        if (state.isLoading) {
                            InfiniteProgressBar()
                        }
                    }
                }
            }
        }
    }

    private fun setStatusBarColor() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(
            this,
            if (AppUtils.isDarkMode) R.color.status_bar_dark else R.color.status_bar_light
        )
    }
}

@Composable
fun InfiniteProgressBar() {
    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp),
        color = AppTheme.colors.primary,
        backgroundColor = Color.Transparent,
    )
}