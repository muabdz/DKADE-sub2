package com.dicoding.muadz.footballmatchschedule.teams

import com.dicoding.muadz.footballmatchschedule.models.Team

interface TeamContract {

    interface View{
        fun showLoading()
        fun hideLoading()
        fun showTeamList(data: List<Team>)

    }

    interface Presenter{
        fun getTeamList(league: String?)
    }

}