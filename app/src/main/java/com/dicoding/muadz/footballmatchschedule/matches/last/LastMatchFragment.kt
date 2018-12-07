package com.dicoding.muadz.footballmatchschedule.matches.last

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
import com.dicoding.muadz.footballmatchschedule.models.Match
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
    private lateinit var spinner: Spinner
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

                spinner = spinner {
                    id = R.id.spinner
                }

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

                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {}

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        lastMatchPresenter.getLastMatch(lastMatchParams())
                    }

                }

                swipeRefresh.onRefresh {
                    lastMatchPresenter.getLastMatch(lastMatchParams())
                }
            }
            val spinnerItems = resources.getStringArray(R.array.league)
            val spinnerAdapter = ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item, spinnerItems
            )
            spinner.adapter = spinnerAdapter

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

    fun lastMatchParams(): String? {
        return when (spinner.selectedItem.toString()) {
            "English Premier League" -> "4328"
            "English League Championship" -> "4329"
            "German Bundesliga" -> "4331"
            "Italian Serie A" -> "4332"
            "Spanish La Liga" -> "4335"
            else -> null
        }
    }
}
