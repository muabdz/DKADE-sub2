package com.dicoding.muadz.footballmatchschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dicoding.muadz.footballmatchschedule.R.layout.activity_main
import com.dicoding.muadz.footballmatchschedule.favorite.FavoriteFragment
import com.dicoding.muadz.footballmatchschedule.matches.MatchFragment
import com.dicoding.muadz.footballmatchschedule.teams.TeamFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener{item ->
            when (item.itemId){
                R.id.matches ->{
                    loadMatchFragment(savedInstanceState)
                }
                R.id.teams ->{
                    loadTeamFragment(savedInstanceState)
                }
                R.id.favorites ->{
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.matches
//        if (Intent.ACTION_SEARCH == intent.action) {
//            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
//                searchMatchPresenter.searchMatch(query)
//            }
//        }
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TeamFragment(), TeamFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }
}