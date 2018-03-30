package ir.unary.zhin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


class BottomSheetAdapter(internal var mContext: Context, internal var tabPosition: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.bottom_layout_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }


    internal inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var tv: TextView

        init {
            tv = itemView.findViewById(R.id.tv_bottomItem)
            var pre = ""
            when (tabPosition) {
                0 -> pre = "نزدیک ترین "
                1 -> pre = "پربازدید "
                2 -> pre = "جدیدترین "
            }
            tv.text = "$pre#$adapterPosition"
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Toast.makeText(mContext , "#$adapterPosition clicked", Toast.LENGTH_SHORT).show()
        }
    }

}
