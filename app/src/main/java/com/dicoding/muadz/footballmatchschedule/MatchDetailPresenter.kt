package com.dicoding.muadz.footballmatchschedule

import com.dicoding.muadz.footballmatchschedule.ApiUtils.ApiRepository
import com.dicoding.muadz.footballmatchschedule.ApiUtils.SportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view : MatchDetailContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson
): MatchDetailContract.Presenter {

    override fun getMatchDetail(matchId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getMatchDetail(matchId)),
                Matches::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchDetail(data.matches)
            }
        }
    }
}
