package com.linglingdr00.taipeizoo.model.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlantResponse(
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
            @Json(name = "f_name_ch")
            val fNameCh: String = "", // "九芎"
            @Json(name = "f_summary")
            val fSummary: String = "",
            @Json(name = "f_keywords")
            val fKeywords: String = "",
            @Json(name = "f_alsoknown")
            val fAlsoKnown: String = "", // "苞飯花、拘那花、小果紫薇、南紫薇、猴不爬、怕癢樹、南紫薇、九荊..."
            @Json(name = "f_geo")
            val fGeo: String = "", // "MULTIPOINT ((121.5804577 24.9979216),  (121.5805328 24.9959671),  (121.5836763 24.9957094),  (121.5894029 24.9951126),  (121.5899205 24.9945669))"
            @Json(name = "f_location")
            val fLocation: String = "", // "臺灣動物區；蟲蟲探索谷；熱帶雨林區；鳥園區；兩棲爬蟲動物館"
            @Json(name = "f_name_en")
            val fNameEn: String = "", // "Subcostate Crape Myrtle"
            @Json(name = "f_name_latin")
            val fNameLatin: String = "", // "Lagerstroemia subcostata"
            @Json(name = "f_family")
            val fFamily: String = "", // "千屈菜科 Lythraceae"
            @Json(name = "f_genus")
            val fGenus: String = "", // "紫薇屬Lagerstroemia"
            @Json(name = "f_brief")
            val fBrief: String = "", // "分布於中低海拔森林及長江以南的地區，為台灣的原生樹種。主要生長在潮..."
            @Json(name = "f_feature")
            val fFeature: String = "", // "紅褐色的樹皮剝落後呈灰白色，樹幹光滑堅硬。葉有極短的柄，長橢圓形..."
            @Json(name = "f_function＆application")
            val fFunctionApplication: String = "", // "1. 優良薪炭材：木質堅硬耐燒，是臺灣優良的薪炭材..."
            @Json(name = "f_code")
            val fCode: String = "",
            @Json(name = "f_pic01_alt")
            val fPic01Alt: String = "",
            @Json(name = "f_pic01_url")
            val fPic01Url: String = "", // "http://www.zoo.gov.tw/iTAP/04_Plant/Lythraceae/subcostata/subcostata_1.jpg"
            @Json(name = "f_pic02_alt")
            val fPic02Alt: String = "",
            @Json(name = "f_pic02_url")
            val fPic02Url: String = "",
            @Json(name = "f_pic03_alt")
            val fPic03Alt: String = "",
            @Json(name = "f_pic03_url")
            val fPic03Url: String = "",
            @Json(name = "f_pic04_alt")
            val fPic04Alt: String = "",
            @Json(name = "f_pic04_url")
            val fPic04Url: String = "",
            @Json(name = "f_pdf01_alt")
            val fPdf01Alt: String = "",
            @Json(name = "f_pdf01_url")
            val fPdf01Url: String = "",
            @Json(name = "f_pdf02_alt")
            val fPdf02Alt: String = "",
            @Json(name = "f_pdf02_url")
            val fPdf02Url: String = "",
            @Json(name = "f_voice01_alt")
            val fVoice01Alt: String = "",
            @Json(name = "f_voice01_url")
            val fVoice01Url: String = "",
            @Json(name = "f_voice02_alt")
            val fVoice02Alt: String = "",
            @Json(name = "f_voice02_url")
            val fVoice02Url: String = "",
            @Json(name = "f_voice03_alt")
            val fVoice03Alt: String = "",
            @Json(name = "f_voice03_url")
            val fVoice03Url: String = "",
            @Json(name = "f_vedio_url")
            val fVideoUrl: String = "",
            @Json(name = "f_update")
            val fUpdate: String = "",
            @Json(name = "f_cid")
            val fCid: String = ""
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