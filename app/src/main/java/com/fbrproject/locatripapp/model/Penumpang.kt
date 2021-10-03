package com.fbrproject.locatripapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Penumpang(
    var username: String? = "",
    var nama: String? = "",
    var url: String? = "",
    var kursi: String? = "",
    var pembayaran: String? = ""
) : Parcelable