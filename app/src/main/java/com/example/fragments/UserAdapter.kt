package com.example.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fragments.databinding.ItemUserBinding
import com.example.fragments.model.User
import com.squareup.picasso.Picasso

interface OnUserListener {
    fun onClick(user:User)
}
class UserAdapter(private val listener: OnUserListener): ListAdapter<User, UserAdapter.ViewHolder>(UserItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemUserBinding = ItemUserBinding.inflate(layoutInflater,parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.binding.tvFullname.text = user.name
        holder.binding.tvCountry.text = user.country
        Picasso.get().load(user.imgUrl).placeholder(R.drawable.ic_launcher_background).into(holder.binding.ivAvatar)

        holder.binding.root.setOnClickListener{
            listener.onClick(user)
        }
    }
    inner class ViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)
}



class UserItemCallback: DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
}