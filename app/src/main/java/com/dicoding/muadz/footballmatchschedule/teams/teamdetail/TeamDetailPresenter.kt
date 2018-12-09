package com.dicoding.muadz.footballmatchschedule.teams.teamdetail

import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.api.SportDBApi
import com.dicoding.muadz.footballmatchschedule.models.Teams
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter(
    private val view: TeamDetailContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson
): TeamDetailContract.Presenter {

    override fun getTeamDetail(teamId: String) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getTeamDetail(teamId)).await(),
                Teams::class.java
            )
            view.hideLoading()
            view.showTeamDetail(data.teams)

        }
    }
}