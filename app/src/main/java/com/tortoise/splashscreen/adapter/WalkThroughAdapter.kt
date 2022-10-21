package com.tortoise.splashscreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.tortoise.splashscreen.R
import com.tortoise.splashscreen.databinding.SlideWalkthoughBinding

class WalkThroughAdapter(private val context: Context) : PagerAdapter() {
    private val imgArray: IntArray = intArrayOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
    )
    private val titleArray: Array<String> = arrayOf("Frame 37", "Frame 39", "Frame 38")
    private val descArray:Array<String> = arrayOf("k1dmekmd","fmk2mgrkmg","m3mefevkrm")
    override fun getCount(): Int {
        return imgArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view: View = `object` as View
        container.removeView(view)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding: SlideWalkthoughBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.slide_walkthough, container, false
        )
        binding.ivImg.setImageDrawable(ContextCompat.getDrawable(context,imgArray[position]))
        binding.tvTitle.text= titleArray[position]
        binding.txtDesc.text=descArray[position]
        container.addView(binding.root)
        return binding.root
    }
}