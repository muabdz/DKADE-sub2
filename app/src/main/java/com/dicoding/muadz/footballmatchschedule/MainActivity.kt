package com.dicoding.muadz.footballmatchschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dicoding.muadz.footballmatchschedule.R.layout.activity_main
import com.dicoding.muadz.footballmatchschedule.favorite.FavoriteMatchFragment
import com.dicoding.muadz.footballmatchschedule.matches.MatchFragment
import com.dicoding.muadz.footballmatchschedule.matches.next.NextMatchFragment
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
                    loadNextMatchFragment(savedInstanceState)
                }
                R.id.favorites ->{
                    loadFavoriteMatchFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.matches
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadNextMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoriteMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)
                .commit()
        }
    }
}