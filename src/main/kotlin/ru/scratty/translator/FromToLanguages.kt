package ru.scratty.translator

import com.google.gson.JsonDeserializer

data class FromToLanguages(
    val from: Language,
    val to: Language
) {
    companion object {
        val deserializer = JsonDeserializer<FromToLanguages> { json, _, _ ->
            val arr = json!!.asString.split("-")
            FromToLanguages(Language.getLanguageByCode(arr[0]), Language.getLanguageByCode(arr[1]))
        }
    }
}