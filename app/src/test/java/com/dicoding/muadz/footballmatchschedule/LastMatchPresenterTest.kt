package com.dicoding.muadz.footballmatchschedule

import com.dicoding.muadz.footballmatchschedule.api.ApiRepository
import com.dicoding.muadz.footballmatchschedule.last.LastMatchContract
import com.dicoding.muadz.footballmatchschedule.last.LastMatchPresenter
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.io.File
import java.nio.file.Files

class LastMatchPresenterTest {
    private lateinit var presenter: LastMatchPresenter

    @Mock
    private lateinit var view: LastMatchContract.View

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LastMatchPresenter(view, apiRepository, gson)
    }

    @Test
    fun testGetLastMatch() {
        val matches: MutableList<Match> = mutableListOf()
        val response = Matches(matches)

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    jsonDummy(),
                    Matches::class.java
                )
            ).thenReturn(response)

            presenter.getLastMatch()

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLastMatch(matches)
            Mockito.verify(view).hideLoading()
        }
    }

    private fun jsonDummy(): String {
        val dummyFile = File(LastMatchPresenterTest::class.java.getResource("../json/lastmatch_dummy.json").path)
        return String(Files.readAllBytes(dummyFile.toPath()))
    }
}