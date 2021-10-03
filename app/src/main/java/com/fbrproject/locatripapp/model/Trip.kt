package com.fbrproject.locatripapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Trip(
    var desc: String? = "",
    var pengemudi: String? = "",
    var kapasitas: String? = "",
    var judul: String? = "",
    var poster: String? = "",
    var rating: String? = "",
    var price: Long? = 0L
) : Parcelable