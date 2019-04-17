package ru.scratty.translator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class YandexTranslatorTest {

    private val translator =
        YandexTranslator("API_KEY")

    @Test
    fun translate() {
        val first = translator.translate("Привет, как дела?", to = Language.ENGLISH)
        val second = translator.translate(
            TranslateRequest(
                "Привет, как дела?",
                Language.RUSSIAN,
                Language.ENGLISH
            )
        )

        assertEquals(first, second)
    }
}