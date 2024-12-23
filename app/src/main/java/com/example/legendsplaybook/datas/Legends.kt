package com.example.legendsplaybook.datas

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Legends(
    val name: String,
    val role: String,
    val class_legends: String,
    val photo_cover: Int,
    val photo_full: Int,
    val description: String,
    val real_name: String,
    val age: String,
    val home: String,
    val tactical_ability: String,
    val passive_ability: String,
    val ultimate_ability: String,
    val classIcon: Int
) : Parcelable