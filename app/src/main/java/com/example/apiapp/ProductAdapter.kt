package com.example.apiapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.apiapp.databinding.FragmentDetailsBinding
import com.example.apiapp.databinding.ProductItemBinding
import com.example.apiapp.databinding.TodoItemBinding

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.TodoViewHolder>() {
    inner class TodoViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(todos[adapterPosition])


            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var onItemClick: ((Product) -> Unit)? = null

    var todos: List<Product>
        get() = differ.currentList
        set(value)  {differ.submitList(value)}

    override fun getItemCount(): Int {
         return todos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ProductItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            product = todo
        }
    }


//    class OnClickListener(val clickListener: (product:Product) -> Unit) {
//        fun onClick(product:Product) = clickListener(product)
//    }




}