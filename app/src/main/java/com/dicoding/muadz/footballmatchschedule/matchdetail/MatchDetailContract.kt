package com.dicoding.muadz.footballmatchschedule.matchdetail

import com.dicoding.muadz.footballmatchschedule.Badge
import com.dicoding.muadz.footballmatchschedule.Match

interface MatchDetailContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showMatchDetail(data: List<Match>)
        fun showTeamBadge(logo: List<Badge>, id: Int)
    }

    interface Presenter {
        fun getMatchDetail(matchId: String?)
        fun getTeamBadge(TeamId: String?, id: Int)
    }
}