package com.gamechange.demo.network.models

import com.google.gson.annotations.SerializedName

data class IssueComment(
    val id: Int,
    val body: String,
    val user: User) {

    data class User(
        @SerializedName("login") val name: String
    )
}
