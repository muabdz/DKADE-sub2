package com.dicoding.muadz.footballmatchschedule.teams.teamdetail.teamoverview

import com.dicoding.muadz.footballmatchschedule.models.Team

interface TeamOverviewContract{

    interface View{
        fun showLoading()
        fun hideLoading()
        fun showTeamDetail(data: List<Team>)
    }

    interface Presenter{
        fun getTeamDetail(teamId: String)
    }
}