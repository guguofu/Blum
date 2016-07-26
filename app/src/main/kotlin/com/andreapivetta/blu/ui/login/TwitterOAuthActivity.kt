package com.andreapivetta.blu.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.andreapivetta.blu.BuildConfig
import com.andreapivetta.blu.R
import com.andreapivetta.blu.common.pref.AppSettingsImpl
import com.andreapivetta.blu.data.twitter.TwitterUtils
import com.andreapivetta.twitterloginview.TwitterLoginListener
import com.andreapivetta.twitterloginview.TwitterLoginView
import twitter4j.auth.AccessToken

class TwitterOAuthActivity : Activity(), TwitterLoginListener {

    private var view: TwitterLoginView? = null
    private var oauthStarted: Boolean = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view = TwitterLoginView(this)

        setContentView(view)

        oauthStarted = false
    }

    override fun onResume() {
        super.onResume()

        if (oauthStarted)
            return

        oauthStarted = true

        view!!.start(BuildConfig.TWITTER_CONSUMER_KEY, BuildConfig.TWITTER_CONSUMER_SECRET,
                BuildConfig.TWITTER_CALLBACK, this)
    }

    override fun onSuccess(accessToken: AccessToken) {
        AppSettingsImpl.saveAccessToken(accessToken)
        TwitterUtils.init()

        showMessage(getString(R.string.authorized_by, accessToken.screenName))

        setResult(Activity.RESULT_OK, Intent())
        finish()
    }

    override fun onFailure(resultCode: Int) {
        showMessage(getString(R.string.failed_due, getString(
                if (resultCode == TwitterLoginView.CANCELLATION) R.string.cancellation
                else R.string.error)))
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
