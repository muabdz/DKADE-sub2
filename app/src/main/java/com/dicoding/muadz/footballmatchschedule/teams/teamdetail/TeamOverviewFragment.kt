package com.dicoding.muadz.footballmatchschedule.teams.teamdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.models.Team
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI

class TeamOverviewFragment : Fragment(){
    companion object {
        fun newInstance(id: String): TeamOverviewFragment {
            val fragment = TeamOverviewFragment()
            val args = Bundle()
            args.putString("id", id)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var teamDescription: TextView
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teamId: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

//        val request = ApiRepository()
//        val gson = Gson()
//        presenter = TeamDetailPresenter(this, request, gson)
//        arguments?.getString("id")?.let {
//            teamId = it
//        }

        return UI {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER

                teamDescription = textView{
//                    here
                }.lparams {
                    topMargin = dip(20)
                }

            }
//            teamId = arguments!!.getString("id")
//            presenter.getTeamDetail(teamId)
        }.view
    }

//    override fun showLoading() {}
//
//    override fun hideLoading() {}
//
//    override fun showTeamDetail(data: List<Team>) {
//        teamDescription.text = data[0].teamDescription
//    }
}