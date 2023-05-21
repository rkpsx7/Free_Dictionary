package com.dev_akash.freedictionary.utils.ui_utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.dev_akash.freedictionary.theme.AppTheme

@Composable
fun getVectorIdByTheme(
    @DrawableRes lightRes: Int,
    @DrawableRes darkRes: Int,
): Int {
    return if (AppTheme.isDarkMode) darkRes else lightRes
}