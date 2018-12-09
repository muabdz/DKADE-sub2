package com.dicoding.muadz.footballmatchschedule.teams.teamdetail.players

import com.dicoding.muadz.footballmatchschedule.models.Player

interface PlayerContract {

    interface View{
        fun showLoading()
        fun hideLoading()
        fun showPlayerList(data: List<Player>)

    }

    interface Presenter{
        fun getPlayerList(teamId: String?)
    }

}