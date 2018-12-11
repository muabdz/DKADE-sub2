package com.dicoding.muadz.footballmatchschedule.teams.search

import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.api.SportDBApi
import com.dicoding.muadz.footballmatchschedule.models.Teams
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter (
    private val view: SearchTeamContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) : SearchTeamContract.Presenter {

    override fun searchTeam(keyWord: String?){
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {

            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.searchTeam(keyWord)).await(),
                Teams::class.java
            )

            view.hideLoading()
            view.showSearchedTeam(data.teams)
        }
    }
}