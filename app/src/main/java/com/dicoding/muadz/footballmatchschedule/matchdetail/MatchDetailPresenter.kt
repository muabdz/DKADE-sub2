package com.dicoding.muadz.footballmatchschedule.matchdetail

import android.database.sqlite.SQLiteConstraintException
import android.widget.ScrollView
import com.dicoding.muadz.footballmatchschedule.models.Badges
import com.dicoding.muadz.footballmatchschedule.models.Favorite
import com.dicoding.muadz.footballmatchschedule.models.Matches
import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.api.SportDBApi
import com.dicoding.muadz.footballmatchschedule.favorite.MatchDatabaseOpenHelper
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.design.snackbar

class MatchDetailPresenter(
    private val view: MatchDetailContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val database: MatchDatabaseOpenHelper
) : MatchDetailContract.Presenter {

    override fun getMatchDetail(matchId: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getMatchDetail(matchId)).await(),
                Matches::class.java
            )
                view.hideLoading()
                view.showMatchDetail(data.matches)
        }
    }

    override fun getTeamBadge(TeamId: String?, id: Int) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(SportDBApi.getTeamBadge(TeamId)).await(),
                Badges::class.java
            )

                view.hideLoading()
                view.showTeamBadge(data.badges, id)
        }
    }

    override fun addToFavorite(
        idEvent: String?,
        strDate: String?,
        idHomeTeam: String?,
        idAwayTeam: String?,
        strHomeTeam: String?,
        strAwayTeam: String?,
        intHomeScore: String?,
        intAwayScore: String?,
        scrollView: ScrollView
    ) {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.MATCH_ID to idEvent,
                    Favorite.MATCH_DATE to strDate,
                    Favorite.HOME_ID to idHomeTeam,
                    Favorite.AWAY_ID to idAwayTeam,
                    Favorite.HOME_NAME to strHomeTeam,
                    Favorite.AWAY_NAME to strAwayTeam,
                    Favorite.HOME_SCORE to intHomeScore,
                    Favorite.AWAY_SCORE to intAwayScore
                )
            }
            scrollView.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            scrollView.snackbar(e.localizedMessage).show()
        }
    }

    override fun removeFromFavorite(matchId: String, scrollView: ScrollView) {
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(MATCH_ID = {id})",
                    "id" to matchId
                )
            }
            scrollView.snackbar("Removed from favorite").show()
        } catch (e: SQLiteConstraintException) {
            scrollView.snackbar(e.localizedMessage).show()
        }
    }
}
