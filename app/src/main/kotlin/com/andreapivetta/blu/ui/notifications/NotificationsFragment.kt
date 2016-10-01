package com.andreapivetta.blu.ui.notifications

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreapivetta.blu.R
import com.andreapivetta.blu.common.utils.visible
import com.andreapivetta.blu.data.model.Notification
import com.andreapivetta.blu.data.storage.AppStorageFactory

/**
 * Created by andrea on 28/07/16.
 */
class NotificationsFragment : Fragment(), NotificationsMvpView {

    companion object {
        fun newInstance() = NotificationsFragment()
    }

    private val presenter by lazy { NotificationsPresenter(AppStorageFactory.getAppStorage()) }
    private var adapter = NotificationsAdapter()
    private lateinit var emptyView: ViewGroup

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_notifications, container, false)
        val notificationsRecyclerView =
                rootView?.findViewById(R.id.notificationsRecyclerView) as RecyclerView
        notificationsRecyclerView.layoutManager = LinearLayoutManager(context)
        notificationsRecyclerView.setHasFixedSize(true)
        notificationsRecyclerView.adapter = adapter

        emptyView = rootView?.findViewById(R.id.emptyLinearLayout) as ViewGroup

        presenter.getNotifications()

        return rootView
    }

    override fun showNotifications(notifications: List<Notification>) {
        adapter.dataSet = notifications
        adapter.notifyDataSetChanged()
    }

    override fun hideEmptyMessage() {
        emptyView.visible(false)
    }
}