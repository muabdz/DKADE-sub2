package com.dicoding.muadz.footballmatchschedule.next

import com.dicoding.muadz.footballmatchschedule.Match

interface NextMatchContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showNextMatch(data: List<Match>)

    }

    interface Presenter {
        fun getNextMatch()
    }
}
