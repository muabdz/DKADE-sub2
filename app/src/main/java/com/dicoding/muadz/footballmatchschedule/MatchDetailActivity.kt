package com.dicoding.muadz.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.dicoding.muadz.footballmatchschedule.ApiUtils.ApiRepository
import com.dicoding.muadz.footballmatchschedule.Utils.invisible
import com.dicoding.muadz.footballmatchschedule.Utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class MatchDetailActivity : AppCompatActivity(), MatchDetailContract.View {

    private lateinit var progressBar: ProgressBar
    private var matches: MutableList<Event> = mutableListOf()
    private var badges: MutableList<Badge> = mutableListOf()
    private lateinit var matchDetailPresenter: MatchDetailPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        val request = ApiRepository()
        val gson = Gson()
        val matchId = intent.extras.get("matchId")
        val homeId = intent.extras.get("homeId")
        val awayId = intent.extras.get("awayId")
        matchDetailPresenter = MatchDetailPresenter(this, request, gson)
        matchDetailPresenter.getMatchDetail(matchId as String?)
        matchDetailPresenter.getTeamBadge(homeId as String?, R.id.ivTeam1)
        matchDetailPresenter.getTeamBadge(awayId as String?, R.id.ivTeam2)
//        progressBar = view.find(R.id.progressBar)

        progressBar = findViewById(R.id.progressBar1)
    }

    override fun showLoading() {
        progressBar = findViewById(R.id.progressBar1)
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar = findViewById(R.id.progressBar1)
        progressBar.invisible()
    }

    override fun showMatchDetail(data: List<Event>) {
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

        val strDate: String? = matches.first().strDate
        val strHomeTeam: String? = matches.first().strHomeTeam
        val intHomeScore: String? = matches.first().intHomeScore
        val strAwayTeam: String? = matches.first().strAwayTeam
        val intAwayScore: String? = matches.first().intAwayScore
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

    override fun showTeamBadge(logo: List<Badge>, id: Int) {
        badges.clear()
        badges.addAll(logo)

        val ivLogo: ImageView = findViewById(id)
        val teamBadge: String? = badges.first().strTeamBadge

        Picasso.get().load(teamBadge).into(ivLogo)
    }

}
