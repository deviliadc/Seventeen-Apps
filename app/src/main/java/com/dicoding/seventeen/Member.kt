package com.dicoding.seventeen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    val name: String?,
    val realName: String?,
    val position: String?,
    val dob: String?,
    val nationality: String?,
    val description: String?,
    val photo: Int
):Parcelable
