package com.dev_akash.freedictionary.feature_dictionary.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev_akash.freedictionary.AudioPlayer
import com.dev_akash.freedictionary.feature_dictionary.domain.model.WordInfo
import com.dev_akash.freedictionary.theme.AppTheme
import com.dev_akash.freedictionary.utils.ui_utils.getVectorIdByTheme
import com.dev_akash.freedictonary.R

@Composable
fun WordInfoItem(
    modifier: Modifier = Modifier,
    wordInfo: WordInfo,
    audioPlayer: AudioPlayer
) {
    val textColor = AppTheme.colors.textPrimary

    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = wordInfo.word,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Spacer(modifier = Modifier.height(16.dp))

            wordInfo.phonetics[0].audio?.let {
                if (it.isNotBlank() && it.isNotEmpty()) {
                    IconButton(onClick = { audioPlayer.play(it) }) {
                        Icon(
                            modifier = Modifier
                                .height(24.dp)
                                .width(24.dp),
                            imageVector = ImageVector.vectorResource(
                                id = getVectorIdByTheme(
                                    lightThemeRes = R.drawable.ic_speaker_black,
                                    darkThemeRes = R.drawable.ic_speaker_white
                                )
                            ),
                            contentDescription = "Play Word",
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
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

//@Preview(showSystemUi = true)
//@Composable
//fun prev() {
//    WordInfoItem(wordInfo = WordInfo(emptyList(), "Phonetic", emptyList(), "Word"))
//}