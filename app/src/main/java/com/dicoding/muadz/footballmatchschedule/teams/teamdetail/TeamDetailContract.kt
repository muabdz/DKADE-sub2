package com.dicoding.muadz.footballmatchschedule.teams.teamdetail

import com.dicoding.muadz.footballmatchschedule.models.Team

interface TeamDetailContract{

    interface View{
        fun showLoading()
        fun hideLoading()
        fun showTeamDetail(data: List<Team>)
    }

    interface Presenter{
        fun getTeamDetail(teamId: String)
    }
}