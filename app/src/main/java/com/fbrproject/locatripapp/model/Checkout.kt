package com.fbrproject.locatripapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Checkout(
    var kursi: String? = "",
    var harga: Long? = 0L
) : Parcelable