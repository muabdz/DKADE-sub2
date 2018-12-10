package com.dicoding.muadz.footballmatchschedule.matches.search

import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.api.SportDBApi
import com.dicoding.muadz.footballmatchschedule.models.Matches
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter (
    private val view: SearchMatchContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson
    ) : SearchMatchContract.Presenter {

        override fun searchMatch(keyWord: String?) {
            view.showLoading()
            GlobalScope.launch(Dispatchers.Main) {

                val data = gson.fromJson(
                    apiRepository
                        .doRequest(SportDBApi.searchMatch(keyWord)).await(),
                    Matches::class.java
                )

                view.hideLoading()
                view.showSearchedMatch(data.matches)
            }
        }
}