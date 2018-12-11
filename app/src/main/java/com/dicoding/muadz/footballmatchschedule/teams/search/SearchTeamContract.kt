package com.dicoding.muadz.footballmatchschedule.teams.search

import com.dicoding.muadz.footballmatchschedule.models.Team

interface SearchTeamContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showSearchedTeam(data: List<Team>)
    }

    interface Presenter {
        fun searchTeam(keyWord: String?)
    }
}