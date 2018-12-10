package com.dicoding.muadz.footballmatchschedule.teams.teamdetail.players.playerdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.dicoding.muadz.footballmatchschedule.R
import org.jetbrains.anko.Orientation
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent

class PlayerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout {
            lparams(width = matchParent, height = matchParent)
            orientation = LinearLayout.VERTICAL
        }
        setContentView(R.layout.activity_player_detail)
    }
}
