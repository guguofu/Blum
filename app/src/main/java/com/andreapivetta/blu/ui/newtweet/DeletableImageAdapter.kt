package com.andreapivetta.blu.ui.newtweet

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import com.andreapivetta.blu.R
import com.andreapivetta.blu.common.utils.loadUri
import kotlinx.android.synthetic.main.photo_deletable.view.*

/**
 * Created by andrea on 29/05/16.
 */
class DeletableImageAdapter :
        RecyclerView.Adapter<DeletableImageAdapter.DeletableImageViewHolder>() {

    class DeletableImageViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        val photoImageView: ImageView = root.tweetPhotoImageView
        val deleteButton: ImageButton = root.deleteButton
    }

    val images: MutableList<Uri> = mutableListOf()

    override fun onBindViewHolder(holder: DeletableImageViewHolder, position: Int) {
        holder.photoImageView.loadUri(images[position])
        holder.deleteButton.setOnClickListener {
            images.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = DeletableImageViewHolder(
            LayoutInflater.from(parent?.context).inflate(R.layout.photo_deletable, parent, false))

    override fun getItemCount() = images.size
}