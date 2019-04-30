package ru.scratty.translator

data class SupportedLanguagesRequest(
    var lang: Language
) {
    fun toMap() = mutableMapOf(
        "ui" to lang.code
    )
}