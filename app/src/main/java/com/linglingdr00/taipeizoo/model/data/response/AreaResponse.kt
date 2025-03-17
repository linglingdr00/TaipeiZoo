package com.linglingdr00.taipeizoo.model.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AreaResponse(
    @Json(name = "result")
    val result: Result = Result()
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "limit")
        val limit: Int = 0,
        @Json(name = "offset")
        val offset: Int = 0,
        @Json(name = "count")
        val count: Int = 0,
        @Json(name = "sort")
        val sort: String = "",
        @Json(name = "results")
        val results: List<Data> = listOf()
    ) {
        @JsonClass(generateAdapter = true)
        data class Data(
            @Json(name = "_id")
            val id: Int = 0, // 1
            @Json(name = "_importdate")
            val importDate: ImportDate = ImportDate(),
            @Json(name = "e_no")
            val eNo: String = "",
            @Json(name = "e_category")
            val eCategory: String = "", // 戶外區
            @Json(name = "e_name")
            val eName: String = "", // 臺灣動物區
            @Json(name = "e_pic_url")
            val ePicUrl: String = "", // http://www.zoo.gov.tw/iTAP/05_Exhibit/01_FormosanAnimal.jpg
            @Json(name = "e_info")
            val eInfo: String = "", // 臺灣位於北半球，北迴歸線橫越南部，造成亞熱帶溫和多雨的氣候。...
            @Json(name = "e_memo")
            val eMemo: String = "",
            @Json(name = "e_geo")
            val eGeo: String = "", // MULTIPOINT ((121.5805931 24.9985962))
            @Json(name = "e_url")
            val eUrl: String = "" // https://www.zoo.gov.taipei/News_Content.aspx?n=27AD3418659070A4&sms=589C9E9F5E8CEAE4&s=A28758B750F6384F
        ) {
            @JsonClass(generateAdapter = true)
            data class ImportDate(
                @Json(name = "date")
                val date: String = "",
                @Json(name = "timezone_type")
                val timezoneType: Int = 0,
                @Json(name = "timezone")
                val timezone: String = ""
            )
        }
    }
}