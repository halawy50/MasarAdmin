package com.masar.masaradmin.Data

import java.util.Date

data class Delivery_Info_Data (
    var id : String = "",
    var national_ID : String = "",
    var name : String = "",
    var birthdate : Date = Date(),// تاريخ الميلاد
    var license_start: Date = Date(),//تاريخ انشاء الرخصة
    var license_end: Date = Date(),//تاريخ انشاء الرخصة
    var vehicle: String = "", // نوع المركبة
    var absorptive_capacity : Int = 0,// الطاقة الاستيعابية
    var license_number : String = "", // رقم الرخصة
    var image : String ="",
    var rate : Double = 0.0,
    var approve : Int = 0,
    var city : String="",
    var stateAccount:Boolean=true,
    var idCompany :String = "",
    var nameCompany : String = ""

    )