package com.dicoding.muadz.footballmatchschedule

import com.dicoding.muadz.footballmatchschedule.ApiUtils.ApiRepository
import com.dicoding.muadz.footballmatchschedule.ApiUtils.Matches
import com.dicoding.muadz.footballmatchschedule.ApiUtils.SportDBApi
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