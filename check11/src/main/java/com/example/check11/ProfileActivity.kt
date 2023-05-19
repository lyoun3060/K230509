package com.example.check11

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.check11.databinding.ActivityProfileBinding
import java.lang.Exception

class ProfileActivity : AppCompatActivity() {
    lateinit var binding : ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
//            try {
//                val calRatio = calculateInSampleSize(
//                    it.data!!.data!!,
//                    resources.getDimensionPixelSize(R.dimen.imgSize),
//                    resources.getDimensionPixelSize(R.dimen.imgSize),
//                )
//            }
        }
    }
    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int):Int{
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try{
            var inputStream = contentResolver.openInputStream(fileUri)

            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        }catch (e:Exception){
            e.printStackTrace()
        }
        val(height:Int, width:Int) = options.run{outHeight to outWidth}
        var inSampleSize = 1
    }
}