package com.projecteugene.gradient

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.projecteugene.gradient.databinding.ActivityMainBinding
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainActivityViewModel
    private lateinit var mBinding: ActivityMainBinding
    private var color1: String? = null
    private var color2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this

        mViewModel.color1.observe(this, Observer {
            color1 = it
            createColors(color1, color2)
        })

        mViewModel.color2.observe(this, Observer {
            color2 = it
            createColors(color1, color2)
        })
    }

    private fun createColors(color1: String?, color2: String?) {
        if (color1 == null || color2 == null) return
        val rgb1 = RGB.parseString(color1)
        val rgb2 = RGB.parseString(color2)
        if (rgb1 == null || rgb2 == null) return
        val step = (rgb2 - rgb1) / 20
        var newRgb = rgb1

        val animation = AnimationDrawable()

        var bitmap = createSquaredBitmap(newRgb)
        animation.addFrame(BitmapDrawable(resources, bitmap), 70)
        for (i in 1 until 20) {
            newRgb += step
            bitmap = createSquaredBitmap(newRgb)
            animation.addFrame(BitmapDrawable(resources, bitmap), 70)
        }
        for (i in 19 downTo 1) {
            newRgb -= step
            bitmap = createSquaredBitmap(newRgb)
            animation.addFrame(BitmapDrawable(resources, bitmap), 70)
        }
        mBinding.ivBack.setImageDrawable(animation)
        animation.start()

    }

    private fun createSquaredBitmap(rgb: RGB): Bitmap {
        val dstBmp = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(dstBmp)
        canvas.drawARGB(rgb.a, rgb.redInt, rgb.greenInt, rgb.blueInt)
        return dstBmp
    }
}
