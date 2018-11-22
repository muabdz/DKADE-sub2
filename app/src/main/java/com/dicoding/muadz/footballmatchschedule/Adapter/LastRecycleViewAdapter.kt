package com.dicoding.muadz.footballmatchschedule.Adapter

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.dicoding.muadz.footballmatchschedule.Event
import com.dicoding.muadz.footballmatchschedule.LastMatchContract
import com.dicoding.muadz.footballmatchschedule.MatchDetailActivity
import com.dicoding.muadz.footballmatchschedule.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class LastRecycleViewAdapter(private val matches: List<Event>) : RecyclerView.Adapter<LastRecycleViewAdapter.MatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder{
        return MatchViewHolder(MatchCard().createView(AnkoContext.create(parent.context, parent)))
    }
    override fun getItemCount(): Int =matches.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int){
        holder.bindItem(matches[position])
    }

class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val matchDate: TextView = view.find(R.id.tvTanggal)
    private val homeName: TextView = view.find(R.id.tvTeam1)
    private val homeScore: TextView = view.find(R.id.tvScore1)
    private val awayName: TextView = view.find(R.id.tvTeam2)
    private val awayScore: TextView = view.find(R.id.tvScore2)
    private val matchCardView : LinearLayout = view.find(R.id.matchCard)

    fun bindItem(matches: Event) {
        matchDate.text = matches.strDate
        homeName.text = matches.strHomeTeam
        homeScore.text = matches.intHomeScore
        awayName.text = matches.strAwayTeam
        awayScore.text = matches.intAwayScore

        matchCardView.setOnClickListener {
            matchCardView.context.startActivity<MatchDetailActivity>("matchId" to matches.idEvent)
        }

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
}