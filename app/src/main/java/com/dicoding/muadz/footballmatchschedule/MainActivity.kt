package com.dicoding.muadz.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.viewPager

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout{
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            padding = dip(16)

            viewPager {
               // lparams(width = matchParent, height = matchParent)
                id = R.id.vpMain

            }.lparams(width = matchParent, height = matchParent)

        }
    }
}

//Layout:
//        tabbed view
//        cardview
//        detail
//