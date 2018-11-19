package com.dicoding.muadz.footballmatchschedule.ApiUtils

import java.net.URL

class ApiRepository{

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}