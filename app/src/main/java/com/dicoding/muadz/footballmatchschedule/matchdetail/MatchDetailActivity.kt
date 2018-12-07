package com.dicoding.muadz.footballmatchschedule.matchdetail

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import com.dicoding.muadz.footballmatchschedule.models.Badge
import com.dicoding.muadz.footballmatchschedule.models.Favorite
import com.dicoding.muadz.footballmatchschedule.models.Match
import com.dicoding.muadz.footballmatchschedule.R
import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.favorite.MatchDatabaseOpenHelper
import com.dicoding.muadz.footballmatchschedule.utils.invisible
import com.dicoding.muadz.footballmatchschedule.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select


class MatchDetailActivity : AppCompatActivity(),
    MatchDetailContract.View {

    private lateinit var progressBar: ProgressBar
    private lateinit var scrollView: ScrollView
    private lateinit var matchDetailPresenter: MatchDetailPresenter
    private lateinit var matchId: String
    private lateinit var homeId: String
    private lateinit var awayId: String
    private var idEvent: String? = null
    private var strDate: String? = null
    private var idHomeTeam: String? = null
    private var idAwayTeam: String? = null
    private var strHomeTeam: String? = null
    private var strAwayTeam: String? = null
    private var intHomeScore: String? = null
    private var intAwayScore: String? = null
    private var matches: MutableList<Match> = mutableListOf()
    private var badges: MutableList<Badge> = mutableListOf()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private val Context.database: MatchDatabaseOpenHelper
        get() = MatchDatabaseOpenHelper.getInstance(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setContentView(R.layout.activity_match_detail)
        scrollView = findViewById(R.id.scrollView)

        val request = ApiRepository()
        val gson = Gson()
        matchId = intent.getStringExtra("matchId")
        homeId = intent.getStringExtra("homeId")
        awayId = intent.getStringExtra("awayId")

        favoriteState()

        matchDetailPresenter =
                MatchDetailPresenter(this, request, gson, database)
        matchDetailPresenter.getMatchDetail(matchId as String?)
        matchDetailPresenter.getTeamBadge(homeId as String?, R.id.ivTeam1)
        matchDetailPresenter.getTeamBadge(awayId as String?, R.id.ivTeam2)

        progressBar = findViewById(R.id.progressBar1)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs(
                    "(MATCH_ID = {id})",
                    "id" to matchId
                )
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progressBar = findViewById(R.id.progressBar1)
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar = findViewById(R.id.progressBar1)
        progressBar.invisible()
    }

    override fun showMatchDetail(data: List<Match>) {
        matches.clear()
        matches.addAll(data)

        val matchDate: TextView = findViewById(R.id.tvTanggal)
        val homeName: TextView = findViewById(R.id.tvTeam1)
        val homeScore: TextView = findViewById(R.id.tvScore1)
        val awayName: TextView = findViewById(R.id.tvTeam2)
        val awayScore: TextView = findViewById(R.id.tvScore2)
        val homeGoals: TextView = findViewById(R.id.tvGoal1)
        val awayGoals: TextView = findViewById(R.id.tvGoal2)
        val homeShots: TextView = findViewById(R.id.tvShot1)
        val awayShots: TextView = findViewById(R.id.tvShot2)
        val homeGk: TextView = findViewById(R.id.tvGoalkeeper1)
        val awayGk: TextView = findViewById(R.id.tvGoalkeeper2)
        val homeDef: TextView = findViewById(R.id.tvDefense1)
        val awayDef: TextView = findViewById(R.id.tvDefense2)
        val homeMf: TextView = findViewById(R.id.tvMidfield1)
        val awayMf: TextView = findViewById(R.id.tvMidfield2)
        val homeFw: TextView = findViewById(R.id.tvForward1)
        val awayFw: TextView = findViewById(R.id.tvForward2)
        val homeSub: TextView = findViewById(R.id.tvSubtitutes1)
        val awaySub: TextView = findViewById(R.id.tvSubtitutes2)

        idEvent = matches.first().idEvent
        strDate = matches.first().strDate
        idHomeTeam = matches.first().idHomeTeam
        strHomeTeam = matches.first().strHomeTeam
        intHomeScore = matches.first().intHomeScore
        idAwayTeam = matches.first().idAwayTeam
        strAwayTeam = matches.first().strAwayTeam
        intAwayScore = matches.first().intAwayScore
        val strHomeGoalDetails: String? = matches.first().strHomeGoalDetails
        val strAwayGoalDetails: String? = matches.first().strAwayGoalDetails
        val intHomeShots: String? = matches.first().intHomeShots
        val intAwayShots: String? = matches.first().intAwayShots
        val strHomeLineupGoalkeeper: String? = matches.first().strHomeLineupGoalkeeper
        val strHomeLineupDefense: String? = matches.first().strHomeLineupDefense
        val strHomeLineupMidfield: String? = matches.first().strHomeLineupMidfield
        val strHomeLineupForward: String? = matches.first().strHomeLineupForward
        val strHomeLineupSubstitutes: String? = matches.first().strHomeLineupSubstitutes
        val strAwayLineupGoalkeeper: String? = matches.first().strAwayLineupGoalkeeper
        val strAwayLineupDefense: String? = matches.first().strAwayLineupDefense
        val strAwayLineupMidfield: String? = matches.first().strAwayLineupMidfield
        val strAwayLineupForward: String? = matches.first().strAwayLineupForward
        val strAwayLineupSubstitutes: String? = matches.first().strAwayLineupSubstitutes

        matchDate.text = strDate
        homeName.text = strHomeTeam
        homeScore.text = intHomeScore
        awayName.text = strAwayTeam
        awayScore.text = intAwayScore
        homeGoals.text = strHomeGoalDetails
        awayGoals.text = strAwayGoalDetails
        homeShots.text = intHomeShots
        awayShots.text = intAwayShots
        homeGk.text = strHomeLineupGoalkeeper
        awayGk.text = strAwayLineupGoalkeeper
        homeDef.text = strHomeLineupDefense
        awayDef.text = strAwayLineupDefense
        homeMf.text = strHomeLineupMidfield
        awayMf.text = strAwayLineupMidfield
        homeFw.text = strHomeLineupForward
        awayFw.text = strAwayLineupForward
        homeSub.text = strHomeLineupSubstitutes
        awaySub.text = strAwayLineupSubstitutes

    }

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
                if (!progressBar1.isShown) {
                    if (isFavorite) matchDetailPresenter.removeFromFavorite(
                        matchId,
                        scrollView
                    ) else matchDetailPresenter.addToFavorite(
                        idEvent,
                        strDate,
                        idHomeTeam,
                        idAwayTeam,
                        strHomeTeam,
                        strAwayTeam,
                        intHomeScore,
                        intAwayScore,
                        scrollView
                    )

                    isFavorite = !isFavorite
                    setFavorite()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showTeamBadge(logo: List<Badge>, id: Int) {
        badges.clear()
        badges.addAll(logo)

        val ivLogo: ImageView = findViewById(id)
        val teamBadge: String? = badges.first().strTeamBadge

        Picasso.get().load(teamBadge).into(ivLogo)
    }

    override fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}
