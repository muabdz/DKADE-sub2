package com.dicoding.muadz.footballmatchschedule

import com.dicoding.muadz.footballmatchschedule.ApiUtils.Event

interface NextMatchContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showNextMatch(data: List<Event>)

    }

    interface Presenter {
        fun getNextMatch()
    }
}
