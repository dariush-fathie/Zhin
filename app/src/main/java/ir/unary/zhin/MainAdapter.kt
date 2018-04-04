package ir.unary.zhin

import android.content.Context
import android.media.Image
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import cz.intik.overflowindicator.OverflowPagerIndicator
import cz.intik.overflowindicator.SimpleSnapHelper
import android.R.attr.delay
import android.util.Log


class MainAdapter(internal var mContext: Context, internal var layoutType: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        if (viewType == 0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.slider, parent, false)
            return SliderHolder(view)
        } else {
            when (layoutType) {
                0 -> { // big item
                    view = LayoutInflater.from(mContext).inflate(R.layout.main_item_big, parent, false)
                    return BigItemHolder(view)
                }
                1 -> { // middle item
                    view = LayoutInflater.from(mContext).inflate(R.layout.main_item_middle, parent, false)
                    return MediumItemHolder(view)
                }
                else -> { // small item
                    view = LayoutInflater.from(mContext).inflate(R.layout.main_item_small, parent, false)
                    return SmallItemHolder(view)
                }
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 0
        } else {
            return 1
        }
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        if (holder.adapterPosition == 0){
            stopTime()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SliderHolder) {
            holder.rvSlider.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            holder.rvSlider.adapter = SliderAdapter(mContext, 4)
            holder.overflowPagerIndicator.attachToRecyclerView(holder.rvSlider)
            if (holder.rvSlider.onFlingListener == null) {
                SimpleSnapHelper(holder.overflowPagerIndicator).attachToRecyclerView(holder.rvSlider)
            }
            startTimer(holder.rvSlider)
        } else {

        }
    }

    lateinit var timer:Handler

    private fun startTimer(slider:RecyclerView){
        var i = (slider.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        if (!this::timer.isInitialized) {
            timer = Handler()
            timer.postDelayed(object :Runnable {
                override fun run() {
                    Log.e("SDDSF","SDFDS$i")
                    slider.smoothScrollToPosition(++i)
                }
            } , 2500)
        }
    }

    private fun stopTime(){
        try {
            timer.removeCallbacksAndMessages(null)
        } catch (e:Exception){

        }
    }

    override fun getItemCount(): Int {
        return 100
    }


    internal inner class BigItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var ivAd: ImageView = itemView.findViewById(R.id.iv_adsImage)
        var ivLike: ImageView = itemView.findViewById(R.id.iv_like)
        var ivComment: ImageView = itemView.findViewById(R.id.iv_comment)
        var ivLocation: ImageView = itemView.findViewById(R.id.iv_location)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvSubTitle1: TextView = itemView.findViewById(R.id.tv_subTitle1)
        var tvSubTitle2: TextView = itemView.findViewById(R.id.tv_subTitle2)

        init {
            itemView.setOnClickListener(this)
            ivLike.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Toast.makeText(mContext, "#$adapterPosition clicked", Toast.LENGTH_SHORT).show()
            when (view.id) {
                ivLike.id -> ivLike.setImageResource(R.drawable.ic_liked)
            }
        }
    }

    internal inner class MediumItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var ivAd: ImageView = itemView.findViewById(R.id.iv_adsImage)
        var ivLike: ImageView = itemView.findViewById(R.id.iv_like)
        var ivMore: ImageView = itemView.findViewById(R.id.iv_more)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvSubTitle1: TextView = itemView.findViewById(R.id.tv_subTitle1)

        init {
            itemView.setOnClickListener(this)
            ivMore.setOnClickListener(this)
            ivLike.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            when (view.id) {
                ivMore.id -> showPopUpMenu()
                ivLike.id -> ivLike.setImageResource(R.drawable.ic_liked)
            }
        }

        private fun showPopUpMenu() {
            val mPopupMenu = PopupMenu(mContext, ivMore)
            mPopupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_comment -> Toast.makeText(mContext, "comment", Toast.LENGTH_SHORT).show()
                    R.id.menu_location -> Toast.makeText(mContext, "location", Toast.LENGTH_SHORT).show()
                }
                false
            })
            mPopupMenu.inflate(R.menu.popup_menu_1)
            mPopupMenu.show()
        }

    }

    internal inner class SmallItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var ivAd: ImageView = itemView.findViewById(R.id.iv_adsImage)
        var ivMore: ImageView = itemView.findViewById(R.id.iv_more)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)

        init {
            itemView.setOnClickListener(this)
            ivMore.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            when (view.id) {
                ivMore.id -> showPopUpMenu()
            }
        }

        private fun showPopUpMenu() {
            val mPopupMenu = PopupMenu(mContext, ivMore)
            mPopupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_comment -> Toast.makeText(mContext, "comment", Toast.LENGTH_SHORT).show()
                    R.id.menu_like -> {
                        Toast.makeText(mContext, "like", Toast.LENGTH_SHORT).show()
                    }
                    R.id.menu_location -> Toast.makeText(mContext, "location", Toast.LENGTH_SHORT).show()
                }
                false
            })
            mPopupMenu.inflate(R.menu.popup_menu_2)
            mPopupMenu.show()
        }
    }

    internal inner class SliderHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val rvSlider: RecyclerView = itemView.findViewById(R.id.rv_slider)
        val overflowPagerIndicator: OverflowPagerIndicator = itemView.findViewById(R.id.indicator)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Toast.makeText(mContext, "$adapterPosition", Toast.LENGTH_SHORT).show()
        }

    }

}
