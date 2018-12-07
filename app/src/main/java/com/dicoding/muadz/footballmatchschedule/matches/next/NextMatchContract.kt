package com.dicoding.muadz.footballmatchschedule.matches.next

import com.dicoding.muadz.footballmatchschedule.models.Match

interface NextMatchContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showNextMatch(data: List<Match>)

    }

    interface Presenter {
        fun getNextMatch(leagueId: String? = "4328")
    }
}
