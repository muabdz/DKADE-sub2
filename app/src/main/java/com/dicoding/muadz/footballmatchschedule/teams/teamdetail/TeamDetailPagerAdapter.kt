package com.dicoding.muadz.footballmatchschedule.teams.teamdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dicoding.muadz.footballmatchschedule.teams.teamdetail.players.PlayerFragment
import com.dicoding.muadz.footballmatchschedule.teams.teamdetail.teamoverview.TeamOverviewFragment

class TeamDetailPagerAdapter(fm: FragmentManager, teamId: String) : FragmentPagerAdapter(fm) {

//    var fragments = arrayListOf<Fragment>()
//    var fragmentTitles = arrayListOf<String>()
    //    private val teamOverviewArgs = Bundle().apply {
//        putString("teamDescription", teamDesc)
//    }
//    private val fragmentArgs = Bundle().apply {
//        putString("teamId", teamId)
//    }
//    fun addFragment(fragment: Fragment, fragmentTitle: String) {
//        fragments.add(fragment)
//        fragmentTitles.add(fragmentTitle)
//    }

    //    override fun getItem(position: Int) = fragments[position]
//
//
//    override fun getCount() = fragments.size
//
//    override fun getPageTitle(position: Int) = fragmentTitles[position]
    private val fragmentArgs = Bundle().apply {
        putString("teamId", teamId)
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                val teamOverview =
                    TeamOverviewFragment()
                teamOverview.arguments = fragmentArgs
                teamOverview
            }
            else -> {

                val player = PlayerFragment()
                player.arguments = fragmentArgs
                player
            }
        }
    }


    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Overview"
            else -> "Player"
        }
    }
}