package com.dicoding.muadz.footballmatchschedule.FragmentPages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.muadz.footballmatchschedule.Adapter.LastRecycleViewAdapter
import com.dicoding.muadz.footballmatchschedule.ApiUtils.ApiRepository
import com.dicoding.muadz.footballmatchschedule.Event
import com.dicoding.muadz.footballmatchschedule.LastMatchContract
import com.dicoding.muadz.footballmatchschedule.LastMatchPresenter
import com.dicoding.muadz.footballmatchschedule.Utils.invisible
import com.dicoding.muadz.footballmatchschedule.Utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LastMatchFragment : Fragment(), LastMatchContract.View{

    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var matches: MutableList<Event> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var lastMatchPresenter: LastMatchPresenter
    private lateinit var adapter: LastRecycleViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val request = ApiRepository()
        val gson = Gson()
        lastMatchPresenter = LastMatchPresenter(this, request, gson)
        return UI {
            linearLayout {
                lparams (width = matchParent, height = matchParent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                swipeRefresh = swipeRefreshLayout {

                    relativeLayout{
                        lparams (width = matchParent, height = wrapContent)

                        listMatch = recyclerView {
                            lparams (width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar = progressBar {
                        }.lparams{
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

    override fun showLastMatch(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
