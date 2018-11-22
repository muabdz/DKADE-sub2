package com.dicoding.muadz.footballmatchschedule

interface MatchDetailContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showMatchDetail(data: List<Event>)
        fun showTeamBadge(logo: List<Badge>, id: Int)
    }

    interface Presenter {
        fun getMatchDetail(matchId: String?)
        fun getTeamBadge(TeamId: String?, id: Int)
    }
}