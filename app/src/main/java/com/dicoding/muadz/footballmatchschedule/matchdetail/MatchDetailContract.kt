package com.dicoding.muadz.footballmatchschedule.matchdetail

import android.widget.ScrollView
import com.dicoding.muadz.footballmatchschedule.modals.Badge
import com.dicoding.muadz.footballmatchschedule.modals.Match

interface MatchDetailContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showMatchDetail(data: List<Match>)
        fun showTeamBadge(logo: List<Badge>, id: Int)
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