package com.dicoding.muadz.footballmatchschedule.matches.matchdetail

import android.database.sqlite.SQLiteConstraintException
import android.widget.ScrollView
import com.dicoding.muadz.footballmatchschedule.models.FavoriteMatch
import com.dicoding.muadz.footballmatchschedule.models.Matches
import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.api.SportDBApi
import com.dicoding.muadz.footballmatchschedule.favorite.match.MatchDatabaseOpenHelper
import com.dicoding.muadz.footballmatchschedule.models.Teams
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
                Teams::class.java
            )

                view.hideLoading()
                view.showTeamBadge(data.teams, id)
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
                    FavoriteMatch.TABLE_FAVORITE,
                    FavoriteMatch.MATCH_ID to idEvent,
                    FavoriteMatch.MATCH_DATE to strDate,
                    FavoriteMatch.HOME_ID to idHomeTeam,
                    FavoriteMatch.AWAY_ID to idAwayTeam,
                    FavoriteMatch.HOME_NAME to strHomeTeam,
                    FavoriteMatch.AWAY_NAME to strAwayTeam,
                    FavoriteMatch.HOME_SCORE to intHomeScore,
                    FavoriteMatch.AWAY_SCORE to intAwayScore
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
                    FavoriteMatch.TABLE_FAVORITE, "(MATCH_ID = {id})",
                    "id" to matchId
                )
            }
            scrollView.snackbar("Removed from favorite").show()
        } catch (e: SQLiteConstraintException) {
            scrollView.snackbar(e.localizedMessage).show()
        }
    }
}
