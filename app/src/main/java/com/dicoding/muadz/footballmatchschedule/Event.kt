package com.dicoding.muadz.footballmatchschedule

data class Event(
    val dateEvent: String? = null,
    val idEvent: String? = null,
    val intAwayScore: String? = null,
    val intHomeScore: String? = null,
    val strAwayTeam: String? = null,
    val strDate: String? = null,
    val strHomeTeam: String? = null,
    val strHomeGoalDetails: String? = null,
    val strAwayGoalDetails: String? = null,
    val intHomeShots: String? = null,
    val intAwayShots: String? = null,
    val strHomeLineupGoalkeeper: String? = null,
    val strHomeLineupDefense: String? = null,
    val strHomeLineupMidfield: String? = null,
    val strHomeLineupForward: String? = null,
    val strHomeLineupSubstitutes: String? = null,
    val strAwayLineupGoalkeeper: String? = null,
    val strAwayLineupDefense: String? = null,
    val strAwayLineupMidfield: String? = null,
    val strAwayLineupForward: String? = null,
    val strAwayLineupSubstitutes: String? = null
)