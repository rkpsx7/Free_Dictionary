package com.dev_akash.freedictionary.utils.ui_utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.dev_akash.freedictionary.theme.AppTheme

@Composable
fun getVectorIdByTheme(
    @DrawableRes lightThemeRes: Int,
    @DrawableRes darkThemeRes: Int,
): Int {
    return if (AppTheme.isDarkMode) darkThemeRes else lightThemeRes
}