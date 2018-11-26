package com.dicoding.muadz.footballmatchschedule.favorite

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.dicoding.muadz.footballmatchschedule.Favorite
import com.dicoding.muadz.footballmatchschedule.R
import com.dicoding.muadz.footballmatchschedule.R.id.*
import org.jetbrains.anko.*

class FavoriteRecycleViewAdapter(private val favorites: List<Favorite>,
                                 private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(MatchCard().createView(AnkoContext.create(parent.context, parent)))
    }
    override fun getItemCount(): Int =favorites.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorites[position], listener)
    }
}

class MatchCard : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL
                id = R.id.matchCard

                textView {
                    id = R.id.tvTanggal
                    textSize = 14f
                    gravity = Gravity.CENTER
                }

                linearLayout {
                    textView {
                        id = R.id.tvTeam1
                        textSize = 14f
                        padding = dip(10)
                        gravity = Gravity.START
                    }.lparams(width = 0, weight = 3f, height = wrapContent)

                    textView {
                        id = R.id.tvScore1
                        textSize = 20f
                        gravity = Gravity.CENTER
                    }.lparams(width = 0, weight = 1f, height = wrapContent)

                    textView {
                        id = R.id.tvVersus
                        textSize = 12f
                        text = "vs"
                        gravity = Gravity.CENTER
                        padding = dip(10)
                    }.lparams(width = 0, weight = 1f, height = wrapContent)

                    textView {
                        id = R.id.tvScore2
                        textSize = 20f
                        gravity = Gravity.CENTER
                    }.lparams(width = 0, weight = 1f, height = wrapContent)

                    textView {
                        id = R.id.tvTeam2
                        textSize = 14f
                        padding = dip(10)
                        gravity = Gravity.END
                    }.lparams(width = 0, weight = 3f, height = wrapContent)
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    gravity = Gravity.CENTER
                    padding = dip(10)
                }

            }
        }
    }
}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val matchDate: TextView = view.find(tvTanggal)
    private val homeName: TextView = view.find(tvTeam1)
    private val homeScore: TextView = view.find(tvScore1)
    private val awayName: TextView = view.find(tvTeam2)
    private val awayScore: TextView = view.find(tvScore2)
    private val matchCardView : LinearLayout = view.find(matchCard)

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        matchDate.text = favorite.matchDate
        homeName.text = favorite.homeName
        homeScore.text = favorite.homeScore
        awayName.text = favorite.awayName
        awayScore.text = favorite.awayScore

        matchCardView.setOnClickListener {listener(favorite)}

    }
}