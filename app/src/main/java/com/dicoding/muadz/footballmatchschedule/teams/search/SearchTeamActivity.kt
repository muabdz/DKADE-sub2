package com.dicoding.muadz.footballmatchschedule.teams.search

import android.app.SearchManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.dicoding.muadz.footballmatchschedule.R
import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.models.Team
import com.dicoding.muadz.footballmatchschedule.teams.TeamAdapter
import com.dicoding.muadz.footballmatchschedule.teams.teamdetail.TeamDetailActivity
import com.dicoding.muadz.footballmatchschedule.utils.invisible
import com.dicoding.muadz.footballmatchschedule.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class SearchTeamActivity : AppCompatActivity(), SearchTeamContract.View {
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var presenter: SearchTeamPresenter
    private lateinit var adapter: TeamAdapter
    var searchQuery = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchTeamPresenter(this, request, gson)

        linearLayout {
            lparams(width = matchParent, height = matchParent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = R.id.search_match_list
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(this@SearchTeamActivity)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
        adapter = TeamAdapter(teams) {
            startActivity<TeamDetailActivity>(
                "teamId" to "${it.teamId}",
                "teamName" to "${it.teamName}",
                "teamBadge" to "${it.teamBadge}"
//                "teamFormedYear" to "${it.teamFormedYear}",
//                "teamStadium" to "${it.teamStadium}",
//                "teamDescription" to "${it.teamDescription}"
            )
        }
        listTeam.adapter = adapter

        swipeRefresh.onRefresh {
            if (searchQuery.isNotEmpty()) {
                presenter.searchTeam(searchQuery)
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showSearchedTeam(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.search_button)?.actionView as SearchView?
        searchView?.queryHint = "Search Teams.."
        searchView?.isIconified = false
        searchView?.maxWidth = Int.MAX_VALUE
        searchView?.setIconifiedByDefault(false)
        MenuItem.SHOW_AS_ACTION_ALWAYS
        searchView?.requestFocus()
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager

        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(keyWord: String?): Boolean {
                if (keyWord != null && keyWord.isNotEmpty()) {
                    if (keyWord.length > 2) {
                        searchQuery = keyWord
                        listTeam.recycledViewPool.clear()
                        teams.clear()
                        presenter.searchTeam(keyWord)
                    } else {
                        toast("Keyword is less than 3 letter")
                    }
                } else {
                    toast("No keyword input")
                }
                return true
            }

            override fun onQueryTextChange(keyWord: String?): Boolean {
                return false
            }
        })

        return true
    }

}
