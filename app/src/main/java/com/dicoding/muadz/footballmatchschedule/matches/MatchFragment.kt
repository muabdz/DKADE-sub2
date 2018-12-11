package com.dicoding.muadz.footballmatchschedule.matches

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.LinearLayout
import com.dicoding.muadz.footballmatchschedule.R
import com.dicoding.muadz.footballmatchschedule.matches.search.SearchMatchActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.support.v4.viewPager

class MatchFragment : Fragment() {

    private lateinit var viewPagerMain: ViewPager
    private lateinit var tabLayoutMain: TabLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
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

            val matchPagerAdapter = MatchPagerAdapter(childFragmentManager)
            viewPagerMain.adapter = matchPagerAdapter
            tabLayoutMain.setupWithViewPager(viewPagerMain)
        }.view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.button_search_menu, menu)

//        val searchView = menu?.findItem(R.id.search_button)?.actionView as SearchView
//        searchView?.queryHint = "Search Match"
//        searchView?.setIconifiedByDefault(true)

//        }
//        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(keyWord: String): Boolean {
//                if (keyWord.isNotEmpty()) {
//                    if (keyWord.length > 2) {
//                        searchView.isIconified = true
//                        searchView.clearFocus()
//                        context?.startActivity<SearchMatchActivity>("keyWord" to keyWord)
//                    } else {
//                        toast("Keyword is less than 3 letter")
//                    }
//                } else {
//                    toast("No keyword input")
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                return false
//            }
//        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item!!.itemId){
            R.id.search_button->{
                context?.startActivity<SearchMatchActivity>()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }


}