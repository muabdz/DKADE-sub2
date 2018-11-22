package com.dicoding.muadz.footballmatchschedule

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
