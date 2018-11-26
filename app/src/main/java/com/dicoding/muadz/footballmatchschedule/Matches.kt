package com.dicoding.muadz.footballmatchschedule

import com.google.gson.annotations.SerializedName

data class Matches(
    @SerializedName("events")
    val matches: List<Match>
)