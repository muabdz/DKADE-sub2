package com.dicoding.muadz.footballmatchschedule.Adapter

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dicoding.muadz.footballmatchschedule.ApiUtils.Event
import com.dicoding.muadz.footballmatchschedule.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

//class LastRecycleViewAdapter(private val matches: List<Event>) : RecyclerView.Adapter<MatchViewHolder>(){
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder{
//
//    }
//    override fun getItemCount(): Int =matches.size
//
//    override fun onBindViewHolder(holder: MatchViewHolder, position: Int){
//        holder.bindItem(matches[position])
//    }
    class LastRecycleViewAdapter(private val matches: List<Event>){


    }

class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view){

    fun bindItem(matches: Event) {
//        Picasso.get().load(teams.teamBadge).into(teamBadge)
//        teamName.text = teams.teamName

    }
}

class MatchCard : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                textView {
                    id = R.id.tvTanggal
                    textSize = 16f
                }

                linearLayout {
                    textView {
                        id = R.id.tvTeam1
                        textSize = 16f
                        padding = dip(10)
                    }

                    textView {
                        id = R.id.tvScore1
                        textSize = 16f
                    }

                    textView {
                        id = R.id.tvVersus
                        textSize = 16f
                        text = "vs"
                        padding = dip(10)
                    }

                    textView {
                        id = R.id.tvScore2
                        textSize = 16f
                    }

                    textView {
                        id = R.id.tvTeam2
                        textSize = 16f
                        padding = dip(10)
                    }
                }.lparams{
                    width = matchParent
                    height = wrapContent
                    gravity = Gravity.CENTER
                    padding = dip(10)
                }

            }
        }
    }

}