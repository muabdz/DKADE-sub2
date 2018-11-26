package com.dicoding.muadz.footballmatchschedule.next

import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.api.SportDBApi
import com.dicoding.muadz.footballmatchschedule.Matches
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