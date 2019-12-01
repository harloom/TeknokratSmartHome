package com.dreamwithus.teknokratsmarthome.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Iot(
    var ac: Boolean = false,
    var fire: Boolean = false,
    var gas :Boolean = false,
    var kelembaban : Int = 0,
    var lampu : Boolean = true,
    var pintu :Boolean = true,
    var suhu : Int = 0

) : Parcelable