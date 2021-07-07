package com.picpay.desafio.android

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.features.users.activity.MainActivity
import com.picpay.desafio.android.features.users.viewmodel.UsersViewModel
import com.picpay.desafio.base.PicPayAPITest
import com.picpay.desafio.utilities.Mock
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.not
import org.junit.Test
import org.koin.core.inject


class MainActivityTest: PicPayAPITest() {

    private val viewModel: UsersViewModel by inject()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun shouldDisplayRecyclerViewItem() {
        mockWebServer.dispatcher = getMockWebServerDispatcher(200)
        runBlocking {
            viewModel.loadUsers()
            viewModel.state.take(3)
        }

        launchActivity<MainActivity>().apply {
            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 0, withText(Mock.Data.users.first().name))
            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 0, withText(Mock.Data.users.first().username))
        }
    }

    @Test
    fun shouldDisplayRecyclerView() {
        mockWebServer.dispatcher = getMockWebServerDispatcher(200)
        runBlocking {
            viewModel.loadUsers()
            viewModel.state.take(3)
        }

        launchActivity<MainActivity>().apply {
            onView(ViewMatchers.withId(R.id.recyclerView)).check(matches(isDisplayed()))
            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 0, withText(Mock.Data.users.first().name))
            RecyclerViewMatchers.checkRecyclerViewItem(R.id.recyclerView, 0, withText(Mock.Data.users.first().username))
        }
    }

    @Test
    fun shouldDisplayErrorView() {
        mockWebServer.dispatcher = getMockWebServerDispatcher(400)
        runBlocking {
            viewModel.loadUsers()
            viewModel.state.take(3)
        }

        launchActivity<MainActivity>().apply {
            onView(ViewMatchers.withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
            onView(ViewMatchers.withId(R.id.user_list_progress_bar)).check(matches(not(isDisplayed())))
            onView(ViewMatchers.withId(R.id.error_view)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayLoadingView() {
        runBlocking {
            viewModel.loadUsers()
            viewModel.state.take(2)
        }

        launchActivity<MainActivity>().apply {
            onView(ViewMatchers.withId(R.id.user_list_progress_bar)).check(matches(isDisplayed()))
            onView(ViewMatchers.withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
            onView(ViewMatchers.withId(R.id.error_view)).check(matches(not(isDisplayed())))
        }
    }

}