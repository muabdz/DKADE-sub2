package com.dicoding.muadz.footballmatchschedule.models

import com.google.gson.annotations.SerializedName

data class SearchMatches(
    @SerializedName("event")
    val matches: List<Match>
)