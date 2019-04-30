package ru.scratty.translator

import com.google.gson.GsonBuilder
import ru.scratty.http.Http
import ru.scratty.http.HttpUrl

class YandexTranslator(private val apiKey: String) {

    companion object {
        private const val SCHEME = "https"
        private const val HOST = "translate.yandex.net"
        private const val ROOT_PATH = "/api/v1.5/tr.json/"
        private const val TRANSLATE_PATH = ROOT_PATH + "translate"
        private const val DETECT_LANGUAGE_PATH = ROOT_PATH + "detect"
        private const val LIST_SUPPORTED_LANGUAGES_PATH = ROOT_PATH + "getLangs"

        private const val KEY_PARAMETER = "key"
    }

    fun translate(text: String, from: Language? = null, to: Language) = translate(
        TranslateRequest(text, from, to)
    )

    fun translate(translateRequest: TranslateRequest): TranslateResult {
        val url = HttpUrl(
            SCHEME,
            HOST,
            path = TRANSLATE_PATH
        )
        val parameters = translateRequest.toMap()
        parameters[KEY_PARAMETER] = apiKey

        val response = Http.post(url, parameters)

        if (response.code == 200) {
            return GsonBuilder()
                .registerTypeAdapter(FromToLanguages::class.java, FromToLanguages.deserializer)
                .create()
                .fromJson<TranslateResult>(response.data, TranslateResult::class.java)
        } else {
            throw TranslateError(response.data)
        }
    }

    fun detectLanguage(text: String, vararg hint: Language) = detectLanguage(
        DetectLanguageRequest(text, hint.toList())
    )

    fun detectLanguage(detectLanguageRequest: DetectLanguageRequest): DetectLanguageResult {
        val url = HttpUrl(
            SCHEME,
            HOST,
            path = DETECT_LANGUAGE_PATH
        )

        val parameters = detectLanguageRequest.toMap()
        parameters[KEY_PARAMETER] = apiKey

        val response = Http.post(url, parameters)

        if (response.code == 200) {
            return GsonBuilder()
                .registerTypeAdapter(Language::class.java, Language.deserializer)
                .create()
                .fromJson<DetectLanguageResult>(response.data, DetectLanguageResult::class.java)
        } else {
            throw TranslateError(response.data)
        }
    }

    fun getSupportedLanguages(lang: Language) = getSupportedLanguages(
        SupportedLanguagesRequest(lang)
    )

    fun getSupportedLanguages(supportedLanguagesRequest: SupportedLanguagesRequest): SupportedLanguagesResult {
        val url = HttpUrl(
            SCHEME,
            HOST,
            path = LIST_SUPPORTED_LANGUAGES_PATH
        )

        val parameters = supportedLanguagesRequest.toMap()
        parameters[KEY_PARAMETER] = apiKey

        val response = Http.post(url, parameters)

        if (response.code == 200) {
            return GsonBuilder()
                .registerTypeAdapter(SupportedLanguagesResult::class.java, SupportedLanguagesResult.deserializer)
                .create()
                .fromJson<SupportedLanguagesResult>(response.data, SupportedLanguagesResult::class.java)
        } else {
            throw TranslateError(response.data)
        }
    }
}