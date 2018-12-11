package com.dicoding.muadz.footballmatchschedule.teams.teamdetail.players.playerdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.dicoding.muadz.footballmatchschedule.R
import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.models.Player
import com.dicoding.muadz.footballmatchschedule.utils.invisible
import com.dicoding.muadz.footballmatchschedule.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailContract.View {

    private lateinit var progressBar: ProgressBar
    private lateinit var playerId: String
    private lateinit var presenter: PlayerDetailPresenter
    private var player: MutableList<Player> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)


        supportActionBar?.title = "Player Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerDetailPresenter(this, request, gson)

        playerId = intent.getStringExtra("playerId")
        presenter.getPlayerDetail(playerId)

    }

    override fun showLoading() {
        progressBar = findViewById(R.id.progressBar2)
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar = findViewById(R.id.progressBar2)
        progressBar.invisible()
    }

    override fun showPlayerDetail(data: List<Player>) {
        player.clear()
        player.addAll(data)

        val playerImageView: ImageView = findViewById(R.id.ivPlayer)
        val playerName: TextView = findViewById(R.id.tvPlayerName)
        val playerPos: TextView = findViewById(R.id.tvPlayerPos)
        val playerHeight: TextView = findViewById(R.id.tvPlayerHeight)
        val playerWeight: TextView = findViewById(R.id.tvPlayerWeight)
        val playerDesc: TextView = findViewById(R.id.tvPlayerDesc)

        when(player.first().playerImage){
            null -> playerImageView.setImageResource(R.drawable.ic_no_player)
            else -> Picasso.get().load(player.first().playerImage).fit().centerInside().into(playerImageView)
        }

        playerName.text = player.first().playerName
        playerPos.text = player.first().playerPos
        playerHeight.text = player.first().playerHeight
        playerWeight.text = player.first().playerWeight
        playerDesc.text = player.first().playerDesc

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
