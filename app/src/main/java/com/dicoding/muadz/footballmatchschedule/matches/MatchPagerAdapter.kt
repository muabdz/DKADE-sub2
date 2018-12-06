package com.dicoding.muadz.footballmatchschedule.matches

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dicoding.muadz.footballmatchschedule.last.LastMatchFragment
import com.dicoding.muadz.footballmatchschedule.next.NextMatchFragment

class MatchPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LastMatchFragment()
            }
            else -> {
                NextMatchFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Last Match"
            else -> "Next Match"
        }
    }
}