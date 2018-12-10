package com.dicoding.muadz.footballmatchschedule.matches.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.*
import com.dicoding.muadz.footballmatchschedule.R
import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.matches.MatchRecycleViewAdapter
import com.dicoding.muadz.footballmatchschedule.models.Match
import com.dicoding.muadz.footballmatchschedule.utils.invisible
import com.dicoding.muadz.footballmatchschedule.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class SearchMatchActivity : AppCompatActivity(), SearchMatchContract.View {

    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var searchMatchPresenter: SearchMatchPresenter
    private lateinit var adapter: MatchRecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        searchMatchPresenter = SearchMatchPresenter(this, request, gson)
        val keyWord = intent.getStringExtra("keyWord")

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
                        id = R.id.search_match_list
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(this@SearchMatchActivity)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }

        adapter = MatchRecycleViewAdapter(matches)
        listMatch.adapter = adapter
        searchMatchPresenter.searchMatch(keyWord)

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showSearchedMatch(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.search_button)?.actionView as SearchView?
        searchView?.queryHint = "Search Matches"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(keyWord: String?): Boolean {
                searchMatchPresenter.searchMatch(keyWord)
                return false
            }
        })

        return true
    }
}

