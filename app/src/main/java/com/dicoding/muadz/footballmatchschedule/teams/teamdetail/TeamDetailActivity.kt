package com.dicoding.muadz.footballmatchschedule.teams.teamdetail

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.dicoding.muadz.footballmatchschedule.R
import com.dicoding.muadz.footballmatchschedule.favorite.team.TeamDatabaseOpenHelper
import com.dicoding.muadz.footballmatchschedule.models.FavoriteTeam
import com.dicoding.muadz.footballmatchschedule.models.Team
import com.dicoding.muadz.footballmatchschedule.teams.teamdetail.teamoverview.TeamOverviewPresenter
//import com.dicoding.muadz.footballmatchschedule.teams.teamdetail.players.PlayerPresenter
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class TeamDetailActivity : AppCompatActivity() {

    private lateinit var viewPagerOverview: ViewPager
    private lateinit var tabLayoutOverview: TabLayout
    private lateinit var linearLayout: LinearLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    //    private lateinit var teamDescription: TextView
    private lateinit var presenter: TeamOverviewPresenter
    //    private lateinit var presenter: PlayerPresenter
    private lateinit var teams: Team
    private lateinit var teamId: String
    private lateinit var teamNameString: String
    private lateinit var teamBadgeString: String
    private lateinit var teamYearString: String
    private lateinit var teamStadiumString: String
    private lateinit var teamDescString: String
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private val Context.database: TeamDatabaseOpenHelper
        get() = TeamDatabaseOpenHelper.getInstance(applicationContext)
    private var teamDescription: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        teamId = intent.getStringExtra("teamId")
        teamNameString = intent.getStringExtra("teamName")
        teamBadgeString = intent.getStringExtra("teamBadge")
//        teamYearString = intent.getStringExtra("teamFormedYear")
//        teamDescString = intent.getStringExtra("teamDescription")
//        teamStadiumString = intent.getStringExtra("teamStadium")
        teams = Team(teamId, teamNameString, teamBadgeString)

        linearLayout = linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
//            backgroundColor = R.color.colorPrimary

//            swipeRefresh = swipeRefreshLayout {
//                setColorSchemeResources(
//                    R.color.colorAccent,
//                    android.R.color.holo_green_light,
//                    android.R.color.holo_orange_light,
//                    android.R.color.holo_red_light
//                )

//                scrollView {
////                    isVerticalScrollBarEnabled = false
//                    relativeLayout {
//                        backgroundColorResource = R.color.colorPrimary
//                        lparams(width = matchParent, height = wrapContent)
//
//                        linearLayout {
//                            lparams(width = matchParent, height = matchParent)
//
//                            orientation = LinearLayout.VERTICAL
//                            gravity = Gravity.CENTER_HORIZONTAL

//                            teamBadge = imageView { padding = dip(10) }.lparams(height = dip(100))
//                            Picasso.get().load(teamBadgeString).into(teamBadge)
//
//                            teamName = textView {
//                                this.gravity = Gravity.CENTER
//                                textSize = 20f
//                                textColor = Color.WHITE
//                                text = teamNameString
//                            }.lparams { topMargin = dip(5) }
//
//                            teamFormedYear = textView {
//                                this.gravity = Gravity.CENTER
//                                textColor = Color.LTGRAY
//                                text = teamYearString
//                            }
//
//                            teamStadium = textView {
//                                this.gravity = Gravity.CENTER
//                                textSize = 18f
//                                textColor = Color.LTGRAY
//                                text = teamStadiumString
//                            }

            tabLayoutOverview = tabLayout {
                id = R.id.tl_team
            }

            viewPagerOverview = viewPager {
                id = R.id.vp_team

            }.lparams(width = matchParent, height = matchParent)

//                            teamDescription = textView().lparams{
//                                topMargin = dip(20)
//                            }
//                        }
//                        progressBar = progressBar {
//                            visibility = View.INVISIBLE
//                        }.lparams {
//                            centerHorizontally()
//                        }
//                    }
//                }
//            }
        }

        favoriteState()
//        val request = ApiRepository()
//        val gson = Gson()
//        presenter = TeamOverviewPresenter(this, request, gson)


//        presenter.getTeamDetail(teamId)
//        presenter.getPlayerList(teamId)
//        swipeRefresh.onRefresh {
//                        presenter.getTeamDetail(teamId)
//        }

        val teamDetailPagerAdapter = TeamDetailPagerAdapter(supportFragmentManager, teamId)
//        val teamOverviewArgs = Bundle().apply {
//            putString("teamDescription", teamDescString)
//        }
//        val fragmentArgs = Bundle().apply {
//            putString("teamId", teamId)
//        }
//        val teamOverview = TeamOverviewFragment()
//        teamOverview.arguments = teamOverviewArgs
//        val player = PlayerFragment()
//        player.arguments = fragmentArgs
//        teamDetailPagerAdapter.addFragment(teamOverview, "Overview")
//        teamDetailPagerAdapter.addFragment(player, "Player")
        viewPagerOverview.adapter = teamDetailPagerAdapter
        tabLayoutOverview.setupWithViewPager(viewPagerOverview)
        tabLayoutOverview.setTabTextColors(Color.LTGRAY, Color.WHITE)
        tabLayoutOverview.setBackgroundColor(resources.getColor(R.color.colorPrimary))
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE)
                .whereArgs(
                    "(TEAM_ID = {teamId})",
                    "teamId" to teamId
                )
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

//    override fun showLoading() {
//        progressBar.visible()
//    }
//
//    override fun hideLoading() {
//        progressBar.invisible()
//    }
//
//    override fun showTeamDetail(data: List<Team>) {
//        teams = Team(
//            data[0].teamId,
//            data[0].teamName,
//            data[0].teamBadge
//        )
//        swipeRefresh.isRefreshing = false
//        Picasso.get().load(data[0].teamBadge).into(teamBadge)
//        teamName.text = data[0].teamName
//        teamDescription = data[0].teamDescription
//        teamFormedYear.text = data[0].teamFormedYear
//        teamStadium.text = data[0].teamStadium
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteTeam.TABLE_FAVORITE,
                    FavoriteTeam.TEAM_ID to teams.teamId,
                    FavoriteTeam.TEAM_NAME to teams.teamName,
                    FavoriteTeam.TEAM_BADGE to teams.teamBadge
                )
            }
//            swipeRefresh.snackbar("Added to favorite").show()
            linearLayout.snackbar("Added to favorite").show()

        } catch (e: SQLiteConstraintException) {
            linearLayout.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteTeam.TABLE_FAVORITE, "(TEAM_ID = {teamId})",
                    "teamId" to teamId
                )
            }
            linearLayout.snackbar("Removed from favorite").show()
        } catch (e: SQLiteConstraintException) {
            linearLayout.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}