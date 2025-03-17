package com.linglingdr00.taipeizoo.model.data.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.linglingdr00.taipeizoo.model.database.ZooDatabase

@Entity(tableName = ZooDatabase.TABLE_AREA)
data class AreaItem(
    @PrimaryKey val id: Int, // 1
    @ColumnInfo val eCategory: String = "", // 戶外區
    @ColumnInfo val eName: String = "", // 臺灣動物區
    @ColumnInfo val ePicUrl: String = "", // http://www.zoo.gov.tw/iTAP/05_Exhibit/01_FormosanAnimal.jpg
    @ColumnInfo val eInfo: String = "", // 臺灣位於北半球，北迴歸線橫越南部，造成亞熱帶溫和多雨的氣候。...
    @ColumnInfo val eGeo: String = "", // MULTIPOINT ((121.5805931 24.9985962))
    @ColumnInfo val eUrl: String = "" // https://www.zoo.gov.taipei/News_Content.aspx?n=27AD3418659070A4&sms=589C9E9F5E8CEAE4&s=A28758B750F6384F
)