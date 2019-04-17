package ru.scratty.translator

import com.google.gson.Gson
import ru.scratty.http.Http
import ru.scratty.http.HttpUrl

class YandexTranslator(private val apiKey: String) {

    companion object {
        private const val SCHEME = "https"
        private const val HOST = "translate.yandex.net"
        private const val ROOT_PATH = "/api/v1.5/tr.json/"
        private const val TRANSLATE_PATH = ROOT_PATH + "translate"

        private const val KEY_PARAMETER = "key"
    }

    fun translate(text: String, from: Language? = null, to: Language) = translate(
        TranslateRequest(text, from, to)
    )

    fun translate(translateRequest: TranslateRequest): TranslateResult {
        val url = HttpUrl(
            SCHEME,
            HOST, path = TRANSLATE_PATH
        )
        val parameters = translateRequest.toMap()
        parameters[KEY_PARAMETER] = apiKey

        val response = Http.post(url, parameters)

        if (response.code == 200) {
            return Gson().fromJson<TranslateResult>(response.data, TranslateResult::class.java)
        } else {
            throw TranslateError(response.data)
        }
    }
}