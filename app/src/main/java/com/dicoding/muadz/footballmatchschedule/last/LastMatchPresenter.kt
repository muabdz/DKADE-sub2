package com.dicoding.muadz.footballmatchschedule.last

import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.api.SportDBApi
import com.dicoding.muadz.footballmatchschedule.Matches
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LastMatchPresenter(
    private val view : LastMatchContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) : LastMatchContract.Presenter {

    override fun getLastMatch() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(SportDBApi.getLastMatches()),
                Matches::class.java
            )

            uiThread {
                view.hideLoading()
                view.showLastMatch(data.matches)
            }
        }
    }

}