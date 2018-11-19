package com.dicoding.muadz.footballmatchschedule.ApiUtils

import com.google.gson.annotations.SerializedName

data class Matches(
    @SerializedName("events")
    val matches: List<Event>
)