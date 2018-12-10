package com.dicoding.muadz.footballmatchschedule.teams.teamdetail.players.playerdetail

import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.api.SportDBApi
import com.dicoding.muadz.footballmatchschedule.models.PlayerDetail
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerDetailPresenter(
    private val view: PlayerDetailContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson
): PlayerDetailContract.Presenter{
    override fun getPlayerDetail(playerId: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getPlayers(playerId)).await(),
                PlayerDetail::class.java
            )
            view.hideLoading()
            view.showPlayerDetail(data.players)
        }
    }
}