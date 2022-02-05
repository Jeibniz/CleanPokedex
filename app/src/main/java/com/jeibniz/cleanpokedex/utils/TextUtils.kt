package com.jeibniz.cleanpokedex.utils

import java.util.Locale

class TextUtils {
    companion object {
        fun firstLetterToUpperCase(text: String): String {
            if (text.isEmpty()) {
                return text
            }
            return text.substring(0, 1).uppercase(Locale.ROOT) + text.substring(1)
        }

        fun removeNewLine(text: String): String {
            return text.replace('\n', ' ')
        }
    }
}
