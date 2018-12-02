package com.dicoding.muadz.footballmatchschedule.last

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.dicoding.muadz.footballmatchschedule.Match
import com.dicoding.muadz.footballmatchschedule.R
import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.utils.invisible
import com.dicoding.muadz.footballmatchschedule.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LastMatchFragment : Fragment(), LastMatchContract.View {

    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var lastMatchPresenter: LastMatchPresenter
    private lateinit var adapter: LastRecycleViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val request = ApiRepository()
        val gson = Gson()
        lastMatchPresenter = LastMatchPresenter(this, request, gson)
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
                            id = R.id.last_match_list
                            lparams(width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar = progressBar {
                        }.lparams {
                            centerHorizontally()
                        }
                    }
                }
                swipeRefresh.onRefresh {
                    lastMatchPresenter.getLastMatch()
                }
            }

            adapter = LastRecycleViewAdapter(matches)
            listMatch.adapter = adapter
            lastMatchPresenter.getLastMatch()

        }.view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLastMatch(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showErrorPage() {
        

    }
}
