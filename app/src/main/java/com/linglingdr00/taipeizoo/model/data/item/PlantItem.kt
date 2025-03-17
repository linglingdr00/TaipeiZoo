package com.linglingdr00.taipeizoo.model.data.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.linglingdr00.taipeizoo.model.database.ZooDatabase

@Entity(tableName = ZooDatabase.TABLE_PLANT)
data class PlantItem(
    @PrimaryKey val id: Int = 0, // 1
    @ColumnInfo val fNameCh: String = "", // "九芎"
    @ColumnInfo val fAlsoKnown: String = "", // "苞飯花、拘那花、小果紫薇、南紫薇、猴不爬、怕癢樹、南紫薇、九荊..."
    @ColumnInfo val fGeo: String = "", // "MULTIPOINT ((121.5804577 24.9979216),  (121.5805328 24.9959671),  (121.5836763 24.9957094),  (121.5894029 24.9951126),  (121.5899205 24.9945669))"
    @ColumnInfo val fLocation: String = "", // "臺灣動物區；蟲蟲探索谷；熱帶雨林區；鳥園區；兩棲爬蟲動物館"
    @ColumnInfo val fNameEn: String = "", // "Subcostate Crape Myrtle"
    @ColumnInfo val fNameLatin: String = "", // "Lagerstroemia subcostata"
    @ColumnInfo val fFamily: String = "", // "千屈菜科 Lythraceae"
    @ColumnInfo val fGenus: String = "", // "紫薇屬Lagerstroemia"
    @ColumnInfo val fBrief: String = "", // "分布於中低海拔森林及長江以南的地區，為台灣的原生樹種。主要生長在潮..."
    @ColumnInfo val fFeature: String = "", // "紅褐色的樹皮剝落後呈灰白色，樹幹光滑堅硬。葉有極短的柄，長橢圓形..."
    @ColumnInfo val fFunctionApplication: String = "", // "1. 優良薪炭材：木質堅硬耐燒，是臺灣優良的薪炭材..."
    @ColumnInfo val fPic01Url: String = "", // "http://www.zoo.gov.tw/iTAP/04_Plant/Lythraceae/subcostata/subcostata_1.jpg"
)
