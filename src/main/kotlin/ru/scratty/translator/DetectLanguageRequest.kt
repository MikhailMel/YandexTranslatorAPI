package ru.scratty.translator

data class DetectLanguageRequest(
    var text: String,
    var hint: List<Language> = emptyList()
) {
    fun toMap() = mutableMapOf(
        "text" to text
    ).apply {
        if (hint.isNotEmpty()) {
            put("hint", hint.joinToString { it.code })
        }
    }
}