package com.dicoding.muadz.footballmatchschedule.favorite

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

class FavoriteFragment : Fragment(){

    private lateinit var viewPagerFav: ViewPager
    private lateinit var tabLayoutMain: TabLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        return UI {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER

                tabLayoutMain = tabLayout {
                    id = R.id.tlFavorite
                }

                viewPagerFav = viewPager {
                    id = R.id.vpFavorite

                }.lparams(width = matchParent, height = matchParent)

            }

            val favoritePagerAdapter = FavoritePagerAdapter(childFragmentManager)
            viewPagerFav.adapter = favoritePagerAdapter
            tabLayoutMain.setupWithViewPager(viewPagerFav)
        }.view
    }
}