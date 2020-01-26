package com.ynov.cours_ynov.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(val name: String, val price: Float, val category: String, val description: String, val _id: String, val images: List<String>) :
    Parcelable