package com.dicoding.muadz.footballmatchschedule.teams.teamdetail.players.playerdetail

import com.dicoding.muadz.footballmatchschedule.models.Player

interface PlayerDetailContract {

    interface View{
        fun showLoading()
        fun hideLoading()
        fun showPlayerDetail(data: List<Player>)
    }

    interface Presenter{
        fun getPlayerDetail(PlayerId: String?)
    }
}