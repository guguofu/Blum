package com.andreapivetta.blu.ui.timeline

import com.andreapivetta.blu.arch.MvpView
import twitter4j.Status
import twitter4j.User

/**
 * Created by andrea on 17/05/16.
 */
interface TimelineMvpView : MvpView {

    fun showTweets(tweets: MutableList<Status>)

    fun showTweet(tweet: Status)

    fun showMoreTweets(tweets: MutableList<Status>)

    fun getLastTweetId(): Long

    fun stopRefresh()

    fun showEmpty()

    fun showError()

    fun showSnackBar(message: String)

    fun showLoading()

    fun hideLoading()

    fun showNewTweet(status: Status, user: User)

    fun favoriteAdded(status: Status)

    fun favoriteRemoved(status: Status)

    fun retweetAdded(status: Status)

    fun retweetRemoved(status: Status)

}