package com.example.quicktalk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quicktalk.data.User
import com.example.quicktalk.databinding.UserItemBinding


class UsersAdapter(val listener: ClickListener): ListAdapter<User, UsersAdapter.UserViewHolder>(UserDiffUtil()) {

    inner class UserViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: User){
            binding.run {
                userName.text = "${user.name} ${user.lastName}, ${user.age}"
                if (user.isOnline == true){
                    onlineImage.setBackgroundResource(R.drawable.circle_green)
                }
                else{
                    onlineImage.setBackgroundResource(R.drawable.circle_red)
                }
            }
            binding.root.setOnClickListener {
                listener.onClick(user)
            }

            binding.onlineImage.setOnClickListener {
                listener.onImageClick(user)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class UserDiffUtil: DiffUtil.ItemCallback<User>(){
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}

interface ClickListener{
    fun onClick(user: User)
    fun onImageClick(user: User)
}