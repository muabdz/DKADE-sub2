package com.dicoding.muadz.footballmatchschedule.teams.teamdetail.players

import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.api.SportDBApi
import com.dicoding.muadz.footballmatchschedule.models.Players
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(
    private val view: PlayerContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson
): PlayerContract.Presenter{
    override fun getPlayerList(teamId: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getPlayers(teamId)).await(),
                Players::class.java
            )
            view.hideLoading()
            view.showPlayerList(data.player)
        }
    }
}