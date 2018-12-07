package com.dicoding.muadz.footballmatchschedule.last

import com.dicoding.muadz.footballmatchschedule.models.Match

interface LastMatchContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showLastMatch(data: List<Match>)
    }

    interface Presenter {
        fun getLastMatch(leagueId: String? = "4328")
    }
}
