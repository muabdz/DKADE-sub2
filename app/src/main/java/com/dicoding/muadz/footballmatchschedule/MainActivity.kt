package com.dicoding.muadz.footballmatchschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dicoding.muadz.footballmatchschedule.R.layout.activity_main
import com.dicoding.muadz.footballmatchschedule.favorite.FavoriteMatchFragment
import com.dicoding.muadz.footballmatchschedule.last.LastMatchFragment
import com.dicoding.muadz.footballmatchschedule.next.NextMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener{item ->
            when (item.itemId){
                R.id.last_matches ->{
                    loadLastMatchFragment(savedInstanceState)
                }
                R.id.next_matches ->{
                    loadNextMatchFragment(savedInstanceState)
                }
                R.id.favorites ->{
                    loadFavoriteMatchFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.last_matches
    }

    private fun loadLastMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
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