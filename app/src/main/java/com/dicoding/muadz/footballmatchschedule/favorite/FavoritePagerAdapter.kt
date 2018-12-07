package com.dicoding.muadz.footballmatchschedule.favorite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dicoding.muadz.footballmatchschedule.favorite.match.FavoriteMatchFragment
import com.dicoding.muadz.footballmatchschedule.favorite.team.FavoriteTeamFragment

class FavoritePagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FavoriteMatchFragment()
            }
            else -> {
                FavoriteTeamFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Matches"
            else -> "Teams"
        }
    }
}