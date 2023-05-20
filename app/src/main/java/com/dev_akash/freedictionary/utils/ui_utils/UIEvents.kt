package com.dev_akash.freedictionary.utils.ui_utils

sealed class UIEvents {
    data class ShowSnackBar(val msg: String = "") : UIEvents()
}
