package com.dicoding.muadz.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import com.dicoding.muadz.footballmatchschedule.Adapter.LastRecycleViewAdapter
import com.dicoding.muadz.footballmatchschedule.Adapter.MainPagerAdapter
import com.dicoding.muadz.footballmatchschedule.ApiUtils.ApiRepository
import com.dicoding.muadz.footballmatchschedule.Utils.invisible
import com.dicoding.muadz.footballmatchschedule.Utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class MainActivity : AppCompatActivity(){

    private lateinit var viewPagerMain: ViewPager
    private lateinit var tabLayoutMain: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout{
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER

            tabLayoutMain = tabLayout {
                id = R.id.tlMain
            }

            viewPagerMain = viewPager {
                id = R.id.vpMain

            }.lparams(width = matchParent, height = matchParent)

        }

        val mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        viewPagerMain.adapter = mainPagerAdapter
        tabLayoutMain.setupWithViewPager(viewPagerMain)
    }
}