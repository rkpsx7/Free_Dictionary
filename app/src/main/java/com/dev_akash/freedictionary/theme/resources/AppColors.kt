package com.dev_akash.freedictionary.theme.resources

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.dev_akash.freedictionary.theme.AppTheme
import com.dev_akash.freedictionary.theme.darkColorsPalette
import com.dev_akash.freedictionary.theme.lightColorsPalette

class AppColors(
    primary: Color,
    secondary: Color,
    textPrimary: Color,
    textSecondary: Color,
    background: Color,
    error: Color,
    isLight: Boolean
) {
    var primary by mutableStateOf(primary, structuralEqualityPolicy())
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var background by mutableStateOf(background)
        private set
    var error by mutableStateOf(error)
        private set
    var isLight by mutableStateOf(isLight)
        private set


    fun copy(
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        textPrimary: Color = this.textPrimary,
        textSecondary: Color = this.textSecondary,
        background: Color = this.background,
        error: Color = this.error,
        isLight: Boolean = this.isLight
    ): AppColors = AppColors(
        primary,
        secondary,
        textPrimary,
        textSecondary,
        background,
        error,
        isLight
    )

    fun updateColorsFrom(other: AppColors) {
        primary = other.primary
        secondary = other.secondary
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        background = other.background
        error = other.error
    }

}

internal val LocalColors = when(AppTheme.isDarkMode){
    true ->{
        staticCompositionLocalOf{ darkColorsPalette() }
    }
    false ->{
        staticCompositionLocalOf{ lightColorsPalette() }
    }
}