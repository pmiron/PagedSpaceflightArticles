package md.pavel.icehoney.pagedspaceflightarticles.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*
import md.pavel.icehoney.pagedspaceflightarticles.R
import md.pavel.icehoney.pagedspaceflightarticles.list.data.response.Article


class MainListAdapter(private var context: Context?) :
    PagingDataAdapter<Article, MainListAdapter.ViewHolder>(DataDifferentiator) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textViewTitle.text = getItem(position)?.title
        holder.itemView.textViewSummary.text = getItem(position)?.summary
        context?.let {
            Glide.with(it).load(getItem(position)?.imageUrl).into(holder.itemView.textViewImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    object DataDifferentiator : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}
