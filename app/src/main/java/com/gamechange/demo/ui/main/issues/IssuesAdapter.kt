package com.gamechange.demo.ui.main.issues

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamechange.demo.R
import com.gamechange.demo.network.models.Issue
import kotlinx.android.synthetic.main.item_issue.view.*

class IssuesAdapter(private var issueClickListener: IssueViewHolder.IssueClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var issues: List<Issue> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_issue, parent, false)
        return IssueViewHolder(view, issueClickListener)
    }

    override fun getItemCount() = issues.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is IssueViewHolder -> {
                holder.bind(issues[position])
            }
        }
    }

    fun publishIssues(issues: List<Issue>){
        this.issues = issues
        notifyDataSetChanged()
    }

    class IssueViewHolder constructor(
        itemView: View,
        private val issueClickListener: IssueClickListener): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val tvTitle = itemView.tvTitle!!
        private val tvBody = itemView.tvBody!!

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(issue: Issue){
            tvTitle.text = issue.title
            try {
                tvBody.text = "${issue.body.subSequence(0, 139)} ........"
            } catch (e: IndexOutOfBoundsException) {
                tvBody.text = issue.body
            }
        }

        override fun onClick(v: View) {
            issueClickListener.onIssueClicked(adapterPosition)
        }

        interface IssueClickListener {
            fun onIssueClicked(position: Int)
        }
    }
}
