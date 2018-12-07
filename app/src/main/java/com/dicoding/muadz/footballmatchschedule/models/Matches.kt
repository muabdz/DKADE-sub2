package com.dicoding.muadz.footballmatchschedule.models

import com.google.gson.annotations.SerializedName

data class Matches(
    @SerializedName("events")
    val matches: List<Match>
)