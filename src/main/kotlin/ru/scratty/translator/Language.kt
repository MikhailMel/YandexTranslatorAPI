package ru.scratty.translator

import com.google.gson.JsonDeserializer

enum class Language(val code: String) {
    AZERBAIJAN("az"),
    ALBANIAN("sq"),
    AMHARIC("am"),
    ENGLISH("en"),
    ARABIC("ar"),
    ARMENIAN("hy"),
    AFRIKAANS("af"),
    BASQUE("eu"),
    BASHKIR("ba"),
    BELARUSIAN("be"),
    BENGALI("bn"),
    BURMESE("my"),
    BULGARIAN("bg"),
    BOSNIAN("bs"),
    WELSH("cy"),
    HUNGARIAN("hu"),
    VIETNAMESE("vi"),
    HAITIAN ("ht"),
    GALICIAN("gl"),
    DUTCH("nl"),
    HILL_MARI("mrj"),
    GREEK("el"),
    GEORGIAN("ka"),
    GUJARATI("gu"),
    DANISH("da"),
    HEBREW("he"),
    YIDDISH("yi"),
    INDONESIAN("id"),
    IRISH("ga"),
    ITALIAN("it"),
    ICELANDIC("is"),
    SPANISH("es"),
    KAZAKH("kk"),
    KANNADA("kn"),
    CATALAN("ca"),
    KYRGYZ("ky"),
    CHINESE("zh"),
    KOREAN("ko"),
    XHOSA("xh"),
    KHMER("km"),
    LAOTIAN("lo"),
    LATIN("la"),
    LATVIAN("lv"),
    LITHUANIAN("lt"),
    LUXEMBOURGISH("lb"),
    MALAGASY("mg"),
    MALAY("ms"),
    MALAYALAM("ml"),
    MALTESE("mt"),
    MACEDONIAN("mk"),
    MAORI("mi"),
    MARATHI("mr"),
    MARI("mhr"),
    MONGOLIAN("mn"),
    GERMAN("de"),
    NEPALI("ne"),
    NORWEGIAN("no"),
    PUNJABI("pa"),
    PAPIAMENTO("pap"),
    PERSIAN("fa"),
    POLISH("pl"),
    PORTUGUESE("pt"),
    ROMANIAN("ro"),
    RUSSIAN("ru"),
    CEBUANO("ceb"),
    SERBIAN("sr"),
    SINHALA("si"),
    SLOVAKIAN("sk"),
    SLOVENIAN("sl"),
    SWAHILI("sw"),
    SUNDANESE("su"),
    TAJIK("tg"),
    THAI("th"),
    TAGALOG("tl"),
    TAMIL("ta"),
    TATAR("tt"),
    TELUGU("te"),
    TURKISH("tr"),
    UDMURT("udm"),
    UZBEK("uz"),
    UKRAINIAN("uk"),
    URDU("ur"),
    FINNISH("fi"),
    FRENCH("fr"),
    HINDI("hi"),
    CROATIAN("hr"),
    CZECH("cs"),
    SWEDISH("sv"),
    SCOTTISH("gd"),
    ESTONIAN("et"),
    ESPERANTO("eo"),
    JAVANESE("jv"),
    JAPANESE("ja");

    companion object {

        fun getLanguageByCode(code: String): Language {
            val lang = values().find { it.code == code }

            if (lang != null) {
                return lang
            } else {
                throw RuntimeException("Invalid language code ($code)")
            }
        }

        val deserializer = JsonDeserializer<Language> { json, _, _ -> getLanguageByCode(json!!.asString) }
    }
}