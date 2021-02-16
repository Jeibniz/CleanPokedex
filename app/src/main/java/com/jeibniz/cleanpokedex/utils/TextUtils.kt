package com.jeibniz.cleanpokedex.utils

import java.util.*

class TextUtils {
    companion object {
        fun firstLetterToUpperCase(text: String) : String {
            if (text.length <= 0) {
                return text
            }
            return text.substring(0, 1).toUpperCase(Locale.ROOT) + text.substring(1)
        }
    }
}