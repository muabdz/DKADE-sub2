package com.dicoding.muadz.footballmatchschedule.next

import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.api.SportDBApi
import com.dicoding.muadz.footballmatchschedule.Matches
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(
    private val view : NextMatchContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) : NextMatchContract.Presenter {

    override fun getNextMatch() {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(SportDBApi.getNextMatches()).await(),
                Matches::class.java
            )

                view.hideLoading()
                view.showNextMatch(data.matches)
        }
    }

}