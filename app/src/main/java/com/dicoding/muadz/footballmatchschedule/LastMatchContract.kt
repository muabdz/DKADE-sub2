package com.dicoding.muadz.footballmatchschedule

import com.dicoding.muadz.footballmatchschedule.ApiUtils.Event

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
