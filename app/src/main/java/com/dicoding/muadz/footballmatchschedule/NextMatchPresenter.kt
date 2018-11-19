package com.dicoding.muadz.footballmatchschedule

import com.dicoding.muadz.footballmatchschedule.ApiUtils.ApiRepository
import com.dicoding.muadz.footballmatchschedule.ApiUtils.Matches
import com.dicoding.muadz.footballmatchschedule.ApiUtils.SportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(
    private val view : NextMatchContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) : NextMatchContract.Presenter {

    override fun getNextMatch() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(SportDBApi.getNextMatches()),
                Matches::class.java
            )

            uiThread {
                view.hideLoading()
                view.showNextMatch(data.matches)
            }
        }
    }

}