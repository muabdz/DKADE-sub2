package com.dicoding.muadz.footballmatchschedule

interface LastMatchContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showLastMatch(data : List<Event>)
    }

    interface Presenter {
        fun getLastMatch()
    }
}
