package com.dicoding.muadz.footballmatchschedule.matches

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.dicoding.muadz.footballmatchschedule.R
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.viewPager
import org.jetbrains.anko.wrapContent

class MatchFragment : Fragment(){

    private lateinit var viewPagerMain: ViewPager
    private lateinit var tabLayoutMain: TabLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return UI {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER

                tabLayoutMain = tabLayout {
                    id = R.id.tlMatch
                }

                viewPagerMain = viewPager {
                    id = R.id.vpMatch

                }.lparams(width = matchParent, height = matchParent)

            }

            val matchPagerAdapter = MatchPagerAdapter(supportFragmentManager)
            viewPagerMain.adapter = matchPagerAdapter
            tabLayoutMain.setupWithViewPager(viewPagerMain)
        }.view
    }
}