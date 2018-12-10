package com.dicoding.muadz.footballmatchschedule.favorite.team

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dicoding.muadz.footballmatchschedule.R
import com.dicoding.muadz.footballmatchschedule.R.color.colorAccent
import com.dicoding.muadz.footballmatchschedule.models.FavoriteTeam
import com.dicoding.muadz.footballmatchschedule.teams.teamdetail.TeamDetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteTeamFragment : Fragment(), AnkoComponent<Context> {

    private var favorites: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoriteTeamAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var noFavorite: TextView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val Context.database: TeamDatabaseOpenHelper
        get() = TeamDatabaseOpenHelper.getInstance(applicationContext)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    noFavorite = textView {
                        text = context.getString(R.string.empty_team)
                        textSize = 20f
                        padding = dip(50)
                        visibility = View.INVISIBLE
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteTeamAdapter(favorites) {
            context?.startActivity<TeamDetailActivity>(
                "teamId" to "${it.teamId}",
                "teamName" to "${it.teamName}",
                "teamBadge" to "${it.teamBadge}"
            )
        }

        listTeam.adapter = adapter
        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteTeam.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (favorite.isEmpty()) {
                noFavorite.visibility = View.VISIBLE
            } else {
                favorites.addAll(favorite)
            }
            adapter.notifyDataSetChanged()
        }
    }
}