package ru.scratty.translator

import com.google.gson.JsonDeserializer

data class SupportedLanguagesResult(
    val dirs: List<FromToLanguages>,
    val langs: List<LocalizedLanguage>
) {
    companion object {
        val deserializer = JsonDeserializer<SupportedLanguagesResult> { json, _, context ->
            val dirs = json!!.asJsonObject
                .getAsJsonArray("dirs")
                .map {
                    val arr = it.asString.split("-")
                    FromToLanguages(Language.getLanguageByCode(arr[0]), Language.getLanguageByCode(arr[1]))
                }

            val langs = json.asJsonObject
                .getAsJsonObject("langs")
                .entrySet()
                .map {
                    LocalizedLanguage(Language.getLanguageByCode(it.key), it.value.asString)
                }

            SupportedLanguagesResult(dirs, langs)
        }
    }
}