package com.linglingdr00.taipeizoo.model.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimalResponse(
    val result: Result = Result()
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        val limit: Int = 0,
        val offset: Int = 0,
        val count: Int = 0,
        val sort: String = "",
        val results: List<Data> = listOf()
    ) {
        @JsonClass(generateAdapter = true)
        data class Data(
            @Json(name = "_id")
            val id: Int = 0, // 1
            @Json(name = "_importdate")
            val importDate: ImportDate = ImportDate(),
            @Json(name = "a_name_ch")
            val aNameCh: String = "", // "大貓熊"
            @Json(name = "a_summary")
            val aSummary: String = "",
            @Json(name = "a_keywords")
            val aKeywords: String = "",
            @Json(name = "a_alsoknown")
            val aAlsoKnown: String = "", // "貓熊、熊貓"
            @Json(name = "a_geo")
            val aGeo: String = "", // "MULTIPOINT ((121.5831587 24.9971109))"
            @Json(name = "a_location")
            val aLocation: String = "", // "新光特展館（大貓熊館）"
            @Json(name = "a_poigroup")
            val aPoiGroup: String = "",
            @Json(name = "a_name_en")
            val aNameEn: String = "", // "Giant Panda"
            @Json(name = "a_name_latin")
            val aNameLatin: String = "", // "Ailuropoda melanoleuca"
            @Json(name = "a_phylum")
            val aPhylum: String = "", // "脊索動物門"
            @Json(name = "a_class")
            val aClass: String = "", // "哺乳綱"
            @Json(name = "a_order")
            val aOrder: String = "", // "食肉目"
            @Json(name = "a_family")
            val aFamily: String = "", // "熊科"
            @Json(name = "a_conservation")
            val aConservation: String = "", // "易危(VU)"
            @Json(name = "a_distribution")
            val aDistribution: String = "", // "目前僅存於中國四川、甘肅和陜西三省境內。"
            @Json(name = "a_habitat")
            val aHabitat: String = "", // "海拔2600-3,000公尺的高山中，會因季節的變化而改變其居住的海拔高度...
            @Json(name = "a_feature")
            val aFeature: String = "", // "1、 成熊身長約為120-180公分，體重約80-120公斤，幼熊出生時體長約...
            @Json(name = "a_behavior")
            val aBehavior: String = "", // "1、獨居的動物，除了交配季節或雌性的育幼時期，牠們都是獨自居住的。...
            @Json(name = "a_diet")
            val aDiet: String = "", // "大貓熊以竹為主食(佔日糧中大約99%)"
            @Json(name = "a_crisis")
            val aCrisis: String = "", // "過去大貓熊棲息地裡的竹林竹種較為單一，1983年曾發生棲地內箭竹週期...
            @Json(name = "a_interpretation")
            val aInterpretation: String = "",
            @Json(name = "a_theme_name")
            val aThemeName: String = "", // "YA!大貓熊-臺北大貓熊保育網"
            @Json(name = "a_theme_url")
            val aThemeUrl: String = "",
            @Json(name = "a_adopt")
            val aAdopt: String = "",
            @Json(name = "a_code")
            val aCode: String = "", // "Panda"
            @Json(name = "a_pic01_alt")
            val aPic01Alt: String = "",
            @Json(name = "a_pic01_url")
            val aPic01Url: String = "", // "http://www.zoo.gov.tw/iTAP/03_Animals/PandaHouse/Panda_Pic01.jpg"
            @Json(name = "a_pic02_alt")
            val aPic02Alt: String = "",
            @Json(name = "a_pic02_url")
            val aPic02Url: String = "",
            @Json(name = "a_pic03_alt")
            val aPic03Alt: String = "",
            @Json(name = "a_pic03_url")
            val aPic03Url: String = "",
            @Json(name = "a_pic04_alt")
            val aPic04Alt: String = "",
            @Json(name = "a_pic04_url")
            val aPic04Url: String = "",
            @Json(name = "a_pdf01_alt")
            val aPdf01Alt: String = "",
            @Json(name = "a_pdf01_url")
            val aPdf01Url: String = "",
            @Json(name = "a_pdf02_alt")
            val aPdf02Alt: String = "",
            @Json(name = "a_pdf02_url")
            val aPdf02Url: String = "",
            @Json(name = "a_voice01_alt")
            val aVoice01Alt: String = "",
            @Json(name = "a_voice01_url")
            val aVoice01Url: String = "",
            @Json(name = "a_voice02_alt")
            val aVoice02Alt: String = "",
            @Json(name = "a_voice02_url")
            val aVoice02Url: String = "",
            @Json(name = "a_voice03_alt")
            val aVoice03Alt: String = "",
            @Json(name = "a_voice03_url")
            val aVoice03Url: String = "",
            @Json(name = "a_vedio_url")
            val aVideoUrl: String = "",
            @Json(name = "a_update")
            val aUpdate: String = "",
            @Json(name = "a_cid")
            val aCid: String = ""
        ) {
            @JsonClass(generateAdapter = true)
            data class ImportDate(
                val date: String = "",
                @Json(name = "timezone_type")
                val timezoneType: Int = 0,
                val timezone: String = ""
            )
        }
    }
}