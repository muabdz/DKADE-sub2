package com.dicoding.muadz.footballmatchschedule.teams.teamdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dicoding.muadz.footballmatchschedule.teams.teamdetail.players.PlayerFragment

class TeamDetailPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var fragments = arrayListOf<Fragment>()
    var fragmentTitles = arrayListOf<String>()
    //    private val teamOverviewArgs = Bundle().apply {
//        putString("teamDescription", teamDesc)
//    }
//    private val playerArgs = Bundle().apply {
//        putString("teamId", teamId)
//    }
    fun addFragment(fragment: Fragment, fragmentTitle: String) {
        fragments.add(fragment)
        fragmentTitles.add(fragmentTitle)
    }

    override fun getItem(position: Int) = fragments[position]


    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int) = fragmentTitles[position]
}