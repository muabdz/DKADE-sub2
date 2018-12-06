package com.dicoding.muadz.footballmatchschedule.favorite

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.dicoding.muadz.footballmatchschedule.modals.Favorite
import com.dicoding.muadz.footballmatchschedule.R
import com.dicoding.muadz.footballmatchschedule.matchdetail.MatchDetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteMatchFragment : Fragment(), FavoriteMatchView {

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteRecycleViewAdapter
    private lateinit var listMatch: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val Context.database: MatchDatabaseOpenHelper
        get() = MatchDatabaseOpenHelper.getInstance(applicationContext)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            linearLayout {
                lparams(width = matchParent, height = matchParent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                swipeRefresh = swipeRefreshLayout {

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        listMatch = recyclerView {
                            id = R.id.favoritematch_list
                            lparams(width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }
                    }
                }
                swipeRefresh.onRefresh {
                    showFavorite()
                }
            }

            adapter = FavoriteRecycleViewAdapter(favorites) {
                context?.startActivity<MatchDetailActivity>(
                    "matchId" to "${it.matchId}",
                    "homeId" to "${it.homeId}",
                    "awayId" to "${it.awayId}"
                )
            }
            listMatch.adapter = adapter
            showFavorite()

        }.view
    }

    override fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}