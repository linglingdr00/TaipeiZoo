package com.linglingdr00.taipeizoo.model.data.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.linglingdr00.taipeizoo.model.database.ZooDatabase

@Entity(tableName = ZooDatabase.TABLE_ANIMAL)
data class AnimalItem(
    @PrimaryKey val id: Int = 0, // 1
    @ColumnInfo val aNameCh: String = "", // "大貓熊"
    @ColumnInfo val aAlsoKnown: String = "", // "貓熊、熊貓"
    @ColumnInfo val aGeo: String = "", // "MULTIPOINT ((121.5831587 24.9971109))"
    @ColumnInfo val aLocation: String = "", // "新光特展館（大貓熊館）"
    @ColumnInfo val aNameEn: String = "", // "Giant Panda"
    @ColumnInfo val aNameLatin: String = "", // "Ailuropoda melanoleuca"
    @ColumnInfo val aPhylum: String = "", // "脊索動物門"
    @ColumnInfo val aClass: String = "", // "哺乳綱"
    @ColumnInfo val aOrder: String = "", // "食肉目"
    @ColumnInfo val aFamily: String = "", // "熊科"
    @ColumnInfo val aConservation: String = "", // "易危(VU)"
    @ColumnInfo val aDistribution: String = "", // "目前僅存於中國四川、甘肅和陜西三省境內。"
    @ColumnInfo val aHabitat: String = "", // "海拔2600-3,000公尺的高山中，會因季節的變化而改變其居住的海拔高度...
    @ColumnInfo val aFeature: String = "", // "1、 成熊身長約為120-180公分，體重約80-120公斤，幼熊出生時體長約...
    @ColumnInfo val aBehavior: String = "", // "1、獨居的動物，除了交配季節或雌性的育幼時期，牠們都是獨自居住的。...
    @ColumnInfo val aDiet: String = "", // "大貓熊以竹為主食(佔日糧中大約99%)"
    @ColumnInfo val aCrisis: String = "", // "過去大貓熊棲息地裡的竹林竹種較為單一，1983年曾發生棲地內箭竹週期...
    @ColumnInfo val aThemeName: String = "", // "YA!大貓熊-臺北大貓熊保育網"
    @ColumnInfo val aPic01Url: String = "", // "http://www.zoo.gov.tw/iTAP/03_Animals/PandaHouse/Panda_Pic01.jpg"
    @ColumnInfo val aPic02Url: String = "",
    @ColumnInfo val aPic03Url: String = "",
    @ColumnInfo val aPic04Url: String = ""
)
