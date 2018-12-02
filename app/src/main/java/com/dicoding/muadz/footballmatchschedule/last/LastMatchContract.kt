package com.dicoding.muadz.footballmatchschedule.last

import com.dicoding.muadz.footballmatchschedule.Match

interface LastMatchContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showLastMatch(data : List<Match>)
        fun showErrorPage()
    }

    interface Presenter {
        fun getLastMatch()
    }
}
