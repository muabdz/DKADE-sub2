package com.dicoding.muadz.footballmatchschedule.matches.search

import com.dicoding.muadz.footballmatchschedule.models.Match

interface SearchMatchContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showSearchedMatch(data: List<Match>)
    }

    interface Presenter {
        fun searchMatch(keyWord: String?)
    }
}