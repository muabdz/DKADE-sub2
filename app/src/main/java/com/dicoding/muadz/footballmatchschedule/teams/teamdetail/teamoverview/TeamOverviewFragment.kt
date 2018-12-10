package com.dicoding.muadz.footballmatchschedule.teams.teamdetail.teamoverview

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.dicoding.muadz.footballmatchschedule.R
import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.models.Team
//import com.dicoding.muadz.footballmatchschedule.teams.teamdetail.players.PlayerPresenter
import com.dicoding.muadz.footballmatchschedule.utils.invisible
import com.dicoding.muadz.footballmatchschedule.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.*

class TeamOverviewFragment : Fragment(),
    TeamOverviewContract.View {

    private lateinit var teamDescription: TextView
    private lateinit var presenter: TeamOverviewPresenter
    private lateinit var teamDesc: String
    private lateinit var teamId: String
    private lateinit var viewOfLayout: View
    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
//        viewOfLayout = layoutInflater.inflate(R.layout.fragment_overview, container, false)
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamOverviewPresenter(
            this,
            request,
            gson
        )
//        arguments?.getString("teamId")?.let {
//            teamId = it
//        }
        val teamArgs = arguments
        teamId = teamArgs?.getString("teamId").toString()
//        val textView: TextView = viewOfLayout.find(R.id.tvTeamOverview)
//        textView.text = teamDesc
//        return view
        return UI {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL

                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                    )

                    scrollView {
                        isVerticalScrollBarEnabled = false
                        relativeLayout {
//                            backgroundColorResource = R.color.colorPrimary
                            lparams(width = matchParent, height = wrapContent)

                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)

                                orientation = LinearLayout.VERTICAL
                                gravity = Gravity.CENTER_HORIZONTAL

                                teamBadge = imageView { padding = dip(10) }.lparams(height = dip(100))
//                            Picasso.get().load(teamBadgeString).into(teamBadge)

                                teamName = textView {
                                    this.gravity = Gravity.CENTER
                                    textSize = 20f
                                    textColor = Color.BLACK
//                                text = teamNameString
                                }.lparams { topMargin = dip(5) }

                                teamFormedYear = textView {
                                    this.gravity = Gravity.CENTER
                                    textColor = Color.GRAY
//                                text = teamYearString
                                }

                                teamStadium = textView {
                                    this.gravity = Gravity.CENTER
                                    textSize = 18f
                                    textColor = Color.GRAY
//                                text = teamStadiumString
                                }

                                teamDescription = textView().lparams {
                                    topMargin = dip(20)
                                    padding = dip(10)
                                }
                            }
                            progressBar = progressBar {
                                //                            visibility = View.INVISIBLE
                            }.lparams {
                                centerHorizontally()
                            }
                        }
                    }
                }
            }
//            linearLayout {
//                lparams(width = matchParent, height = wrapContent)
//                orientation = LinearLayout.VERTICAL
//                gravity = Gravity.CENTER
//                backgroundColor = Color.GREEN
//
//                teamDescription = textView{
////                    here
//                    text = teamDesc
//                }.lparams {
//                    topMargin = dip(20)
//                }
//
//            }
//            teamId = arguments!!.getString("id")
//            presenter.getTeamDetail(teamId)

//            presenter.getTeamDetail(teamId)
////        presenter.getPlayerList(teamId)
//            swipeRefresh.onRefresh {
//                presenter.getTeamDetail(teamId)
//            }
        }.view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getTeamDetail(teamId)
//        presenter.getPlayerList(teamId)
        swipeRefresh.onRefresh {
            presenter.getTeamDetail(teamId)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<Team>) {
//        teamDescription.text = data[0].teamDescription
        swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].teamBadge).into(teamBadge)
        teamName.text = data[0].teamName
        teamDescription.text = data[0].teamDescription
        teamFormedYear.text = data[0].teamFormedYear
        teamStadium.text = data[0].teamStadium
    }
}