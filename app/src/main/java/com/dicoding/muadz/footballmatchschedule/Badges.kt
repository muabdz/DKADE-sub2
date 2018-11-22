package com.dicoding.muadz.footballmatchschedule

import com.google.gson.annotations.SerializedName

data class Badges(
    @SerializedName("teams")
    val badges: List<Badge>
)