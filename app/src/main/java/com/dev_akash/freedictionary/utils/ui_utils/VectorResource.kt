package com.dev_akash.freedictionary.utils.ui_utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.dev_akash.freedictionary.utils.AppUtils

@Composable
fun getVectorIdByTheme(
    @DrawableRes lightRes: Int,
    @DrawableRes darkRes: Int,
): Int {
    return if (AppUtils.isDarkMode) darkRes else lightRes
}