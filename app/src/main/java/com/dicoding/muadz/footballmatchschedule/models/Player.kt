package com.dicoding.muadz.footballmatchschedule.models

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("idPlayer")
    val playerId: String? = null,

    @SerializedName("strCutout")
    val playerImage: String? = null,

    @SerializedName("strDescriptionEN")
    val playerDesc: String? = null,

    @SerializedName("strHeight")
    val playerHeight: String? = null,

    @SerializedName("strPlayer")
    val playerName: String? = null,

    @SerializedName("strPosition")
    val playerPos: String? = null,

    @SerializedName("strWeight")
    val playerWeight: String? = null
)