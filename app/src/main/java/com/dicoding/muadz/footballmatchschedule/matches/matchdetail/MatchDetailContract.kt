package com.dicoding.muadz.footballmatchschedule.matches.matchdetail

import android.widget.ScrollView
import com.dicoding.muadz.footballmatchschedule.models.Match
import com.dicoding.muadz.footballmatchschedule.models.Team

interface MatchDetailContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showMatchDetail(data: List<Match>)
        fun showTeamBadge(logo: List<Team>, id: Int)
        fun setFavorite()
    }

    interface Presenter {
        fun getMatchDetail(matchId: String?)
        fun getTeamBadge(TeamId: String?, id: Int)
        fun addToFavorite(
            idEvent: String?,
            strDate: String?,
            idHomeTeam: String?,
            idAwayTeam: String?,
            strHomeTeam: String?,
            strAwayTeam: String?,
            intHomeScore: String?,
            intAwayScore: String?,
            scrollView: ScrollView
        )

        fun removeFromFavorite(matchId: String, scrollView: ScrollView)
    }
}