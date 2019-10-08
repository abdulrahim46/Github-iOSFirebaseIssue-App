package com.gamechange.demo.ui.main.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamechange.demo.R
import com.gamechange.demo.network.models.IssueComment
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var comments: List<IssueComment> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is CommentViewHolder -> {
                holder.bind(comments[position])
            }
        }
    }

    fun publishComments(comments: List<IssueComment>){
        this.comments = comments
        notifyDataSetChanged()
    }

    class CommentViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val tvUserName = itemView.tvUserName!!
        private val tvBody = itemView.tvCommentBody!!

        fun bind(issueComment: IssueComment){
            tvUserName.text = issueComment.user.name
            tvBody.text = issueComment.body
        }
    }
}