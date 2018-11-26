package com.dicoding.muadz.footballmatchschedule.matchdetail

import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.api.SportDBApi
import com.dicoding.muadz.footballmatchschedule.Badges
import com.dicoding.muadz.footballmatchschedule.Matches
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

    override fun getTeamBadge(TeamId: String?, id: Int) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getTeamBadge(TeamId)),
                Badges::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamBadge(data.badges, id)
            }
        }
    }
}
