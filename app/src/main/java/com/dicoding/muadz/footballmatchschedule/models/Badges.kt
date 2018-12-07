package com.dicoding.muadz.footballmatchschedule.models

import com.google.gson.annotations.SerializedName

data class Badges(
    @SerializedName("teams")
    val badges: List<Badge>
)