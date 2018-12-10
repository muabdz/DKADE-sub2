package com.dicoding.muadz.footballmatchschedule.teams.teamdetail.players

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.dicoding.muadz.footballmatchschedule.R
import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.models.Player
import com.dicoding.muadz.footballmatchschedule.teams.teamdetail.players.playerdetail.PlayerDetailActivity
import com.dicoding.muadz.footballmatchschedule.utils.invisible
import com.dicoding.muadz.footballmatchschedule.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PlayerFragment: Fragment(), AnkoComponent<Context>, PlayerContract.View {

    private lateinit var listPlayer: RecyclerView
    private lateinit var progressBar: ProgressBar
//    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var players: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter
    private lateinit var teamId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)

        val teamArgs = arguments
        teamId = teamArgs?.getString("teamId").toString()

        presenter.getPlayerList(teamId)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))


//        return  UI {
//            linearLayout {
//                lparams (width = matchParent, height = wrapContent)
//                orientation = LinearLayout.VERTICAL
//                topPadding = dip(16)
//                leftPadding = dip(16)
//                rightPadding = dip(16)
//
//                swipeRefresh = swipeRefreshLayout {
//                    setColorSchemeResources(
//                        R.color.colorAccent,
//                        android.R.color.holo_green_light,
//                        android.R.color.holo_orange_light,
//                        android.R.color.holo_red_light)
//
//                    relativeLayout{
//                        lparams (width = matchParent, height = wrapContent)
//
//                        listPlayer = recyclerView {
//                            id = R.id.list_player
//                            lparams (width = matchParent, height = wrapContent)
//                            layoutManager = LinearLayoutManager(ctx)
//                        }
//
//                        progressBar = progressBar {
//                        }.lparams{
//                            centerHorizontally()
//                        }
//                    }
//                }
//            }
//            adapter = PlayerAdapter(players){
//                //            context?.startActivity<PlayerDetailActivity>("teamId" to "${it.teamId}")
//                context?.startActivity<PlayerDetailActivity>()
//            }
//            listPlayer.adapter = adapter
//
//            val teamArgs = arguments
//            teamId = teamArgs?.getString("teamId").toString()
//            presenter.getPlayerList(teamId)
//
//            swipeRefresh.onRefresh {
//                presenter.getPlayerList(teamId)
//            }
//        }.view
    }


    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

//            swipeRefresh = swipeRefreshLayout {
//                setColorSchemeResources(
//                    R.color.colorAccent,
//                    android.R.color.holo_green_light,
//                    android.R.color.holo_orange_light,
//                    android.R.color.holo_red_light)

//                relativeLayout{
//                    lparams (width = matchParent, height = matchParent)
                    backgroundColor = Color.BLUE

                    listPlayer = recyclerView {
                        id = R.id.list_player
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

//                    progressBar = progressBar {
//                    }.lparams{
//                        centerHorizontally()
//                    }
//                }
//            }

            adapter = PlayerAdapter(players){
                //            context?.startActivity<PlayerDetailActivity>("teamId" to "${it.teamId}")
                context?.startActivity<PlayerDetailActivity>()
            }
            listPlayer.adapter = adapter
//        swipeRefresh.onRefresh {
//            presenter.getPlayerList(teamId)
//        }
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerList(data: List<Player>) {
//        swipeRefresh.isRefreshing = false
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }
}