package com.dicoding.muadz.footballmatchschedule

data class Favorite(val id: Long?,
                    val matchId: String?,
                    val matchDate: String?,
                    val homeId: String?,
                    val awayId: String?,
                    val homeName: String?,
                    val awayName: String?,
                    val homeScore: String?,
                    val awayScore: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val HOME_ID: String = "HOME_ID"
        const val AWAY_ID: String = "AWAY_ID"
        const val HOME_NAME: String = "HOME_NAME"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"

    }
}