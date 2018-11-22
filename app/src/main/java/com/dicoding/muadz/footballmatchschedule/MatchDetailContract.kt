package com.dicoding.muadz.footballmatchschedule

interface MatchDetailContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showMatchDetail(data: List<Event>)

    }

    interface Presenter {
        fun getMatchDetail(matchId: String?)
    }
}