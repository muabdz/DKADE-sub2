package com.dicoding.muadz.footballmatchschedule.teams.teamdetail.players

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dicoding.muadz.footballmatchschedule.R
import com.dicoding.muadz.footballmatchschedule.models.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class PlayerAdapter (private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>(){
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            PlayerUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = players.size

}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val playerImage: ImageView = view.find(R.id.player_image)
    private val playerName: TextView = view.find(R.id.player_name)
    private val playerPos: TextView = view.find(R.id.player_pos)

    fun bindItem(players: Player, listener: (Player) -> Unit) {
        Picasso.get().load(players.playerImage).into(playerImage)
        playerName.text = players.playerName
        playerPos.text = players.playerPos
        itemView.setOnClickListener { listener(players) }
    }
}

class PlayerUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.player_image
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.player_name
                    textSize = 16f
                    textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                }.lparams{
                    margin = dip(15)
                }

                textView {
                    id = R.id.player_pos
                    textSize = 12f
                    textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }

}