package com.tortoise.splashscreen

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import com.tortoise.splashscreen.adapter.WalkThroughAdapter
import com.tortoise.splashscreen.databinding.ActivityWalkThroughBinding

class WalkThroughActivity : AppCompatActivity() {

    lateinit var wkAdapter: WalkThroughAdapter
    private val dots = arrayOfNulls<TextView>(3)
    var currentPage: Int = 0
    private lateinit var binding: ActivityWalkThroughBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk_through)
        binding = ActivityWalkThroughBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        wkAdapter = WalkThroughAdapter(this)
        binding.vpWalkThrough.adapter = wkAdapter
        dotIndicator(0)
        binding.btnPrevious.visibility = View.INVISIBLE
        binding.btnPrevious.isEnabled = false
        initAction()
    }


    private fun initAction() {
        binding.vpWalkThrough.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                dotIndicator(position)
                currentPage = position
            }

        })
        binding.btnNext.setOnClickListener {

            binding.btnPrevious.visibility = View.VISIBLE
            binding.btnPrevious.isEnabled = true
            if (binding.vpWalkThrough.currentItem + 1 < dots.size) {
                binding.vpWalkThrough.currentItem += 1
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        binding.btnPrevious.setOnClickListener {
            if (currentPage == 0) {
                binding.btnPrevious.visibility = View.INVISIBLE
                binding.btnPrevious.isEnabled = false
            } else {
                binding.vpWalkThrough.currentItem = currentPage - 1
            }
        }
    }

    fun dotIndicator(p: Int) {
        binding.llDots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.textSize = 35f
            dots[i]?.setTextColor(resources.getColor(R.color.gray))
            dots[i]?.text = HtmlCompat.fromHtml("&#8226;", HtmlCompat.FROM_HTML_MODE_LEGACY)//?
            binding.llDots.addView(dots[i])
        }
        if (dots.isNotEmpty()) {
            dots[p]?.setTextColor(resources.getColor(R.color.purple_200))
        }
    }
}

