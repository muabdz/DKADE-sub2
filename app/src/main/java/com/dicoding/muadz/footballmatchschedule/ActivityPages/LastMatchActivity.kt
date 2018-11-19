package com.dicoding.muadz.footballmatchschedule.ActivityPages


import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import android.support.v7.widget.RecyclerView
import com.dicoding.muadz.footballmatchschedule.ApiUtils.Event
import com.dicoding.muadz.footballmatchschedule.LastMatchContract
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LastMatchActivity : AppCompatActivity(), LastMatchContract.View {

    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
//                presenter.getTeamList(leagueName)
            }
        }
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLastMatch(data: List<Event>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
