package ru.scratty.translator

data class TranslateRequest(
    var text: String,
    var from: Language? = null,
    var to: Language,
    var format: Format? = null,
    var options: Int? = null
) {
    fun toMap() = mutableMapOf(
        "text" to text
    ).apply {
        put("lang", if (from != null) "${from!!.code}-${to.code}" else to.code)
        format?.let { put("format", format!!.text) }
        options?.let { put("options", options!!.toString()) }
    }
}

enum class Format(val text: String) {
    PLAIN("plain"),
    HTML("html")
}