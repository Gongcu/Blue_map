package com.bluemap.overcom_blue.ui.main.board.post_details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluemap.overcom_blue.R
import com.bluemap.overcom_blue.databinding.ItemCommentBinding
import com.bluemap.overcom_blue.databinding.ItemReplyCommentBinding
import com.bluemap.overcom_blue.model.Comment

class CommentAdapter(val context: Context,
                     var list: List<Comment>,
                     val commentItemClick:(Comment)->Unit,
                     val commentLikeClick:(Int)->Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    constructor(context: Context,commentItemClick: (Comment) -> Unit,commentLikeClick: (Int) -> Unit)
            :this(context,ArrayList(),commentItemClick,commentLikeClick)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            REPLY -> {
                val binding = ItemReplyCommentBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                return ReplyViewHolder(binding)
            }
            GENERAL ->{
                val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                return ViewHolder(binding)
            }
            else ->{//cannot reach this
                val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tag=position
        if(holder is ViewHolder)
            holder.bind(list[position])
        else if(holder is ReplyViewHolder)
            holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        if(list.isNotEmpty()){
            return if(list[position].parent==null)
                0
            else
                1
        }else
            return super.getItemViewType(position)
    }


    fun setList(list:ArrayList<Comment>){
        this.list=list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        init {
            binding.replyCommentBtn.setOnClickListener(this)
            binding.likeBtn.setOnClickListener(this)
        }

        fun bind(model: Comment){
            binding.root.tag=model.id
            binding.model=model
        }

        override fun onClick(v: View) {
            when(v.id) {
                R.id.reply_comment_btn -> commentItemClick(list[adapterPosition])
                R.id.like_btn -> commentLikeClick(adapterPosition)
            }
        }
    }

    inner class ReplyViewHolder(private val binding: ItemReplyCommentBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        init {
            binding.likeBtn.setOnClickListener(this)
        }

        fun bind(model: Comment){
            binding.root.tag=model.id
            binding.model=model
        }

        override fun onClick(v: View) {
            commentLikeClick(adapterPosition)
        }
    }

    companion object{
        const val GENERAL = 0
        const val REPLY = 1
    }

}