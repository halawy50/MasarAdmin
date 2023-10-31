package com.masar.masaradmin.Data

import android.os.Parcelable
import java.util.Date

data class BookInfo_Data (
    var idBook :String ="",
    var nameDelivery :String= "",
    var idDelivery :String= "",
    var name_Person :String="",
    var typeCate :String= "",
    var idPerson: String ="",
    var city :String= "",
    var round :String= "", //المدرية
    var phoneNumber :String= "",
    var go_back :String= "",
    var subscribe :String= "",
    var address_from :String= "",
    var address_to :String="",
    var stateBook:Int = 1,
    var priceBook:Double = 0.0,
    var startBook:Date = Date(),
    var endBook:Date = Date(),
    var counter :Int=1,
    var idComany: String = "",
    var nameComany:String=""
  )