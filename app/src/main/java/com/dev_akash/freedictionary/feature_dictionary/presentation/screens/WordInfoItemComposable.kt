package com.dev_akash.freedictionary.feature_dictionary.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev_akash.freedictionary.feature_dictionary.domain.model.WordInfo
import com.dev_akash.freedictionary.theme.AppTheme

@Composable
fun WordInfoItem(
    modifier: Modifier = Modifier,
    wordInfo: WordInfo
) {
    val textColor = AppTheme.colors.textPrimary

    Column(modifier = modifier) {
        Text(
            text = wordInfo.word,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Text(
            text = wordInfo.phonetic,
            fontWeight = FontWeight.Light,
            color = textColor
        )
        Spacer(modifier = Modifier.height(16.dp))

        wordInfo.meanings.forEach { meaning ->
            Text(
                text = meaning.partOfSpeech,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            meaning.definitions.forEachIndexed { i, definition ->
                Text(
                    text = "${i + 1}. ${definition.definition}",
                    color = textColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                definition.example?.let { example ->
                    Text(
                        text = "Example: $example",
                        color = textColor
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}