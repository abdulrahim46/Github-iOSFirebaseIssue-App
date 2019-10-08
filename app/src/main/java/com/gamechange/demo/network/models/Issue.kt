package com.gamechange.demo.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Issue(
    val id: Int,
    val title: String,
    val body: String,
    @SerializedName("updated_at") val updatedAt: Date
)
