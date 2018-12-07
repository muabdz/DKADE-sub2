package com.dicoding.muadz.footballmatchschedule.last

import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.api.SportDBApi
import com.dicoding.muadz.footballmatchschedule.models.Matches
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LastMatchPresenter(
    private val view: LastMatchContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) : LastMatchContract.Presenter {

    override fun getLastMatch(leagueId: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {

            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getLastMatches(leagueId)).await(),
                Matches::class.java
            )

            view.hideLoading()
            view.showLastMatch(data.matches)
        }
    }

}