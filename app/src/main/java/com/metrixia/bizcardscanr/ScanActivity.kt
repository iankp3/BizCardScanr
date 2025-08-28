package com.metrixia.bizcardscanr

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.metrixia.bizcardscanr.databinding.ActivityScanBinding

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding
    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    // ✅ Replaces deprecated startActivityForResult
    private val imageCaptureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val bitmap = result.data?.extras?.get("data") as? Bitmap
                bitmap?.let { runTextRecognition(it) }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.captureButton.setOnClickListener {
            val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            imageCaptureLauncher.launch(intent)
        }
    }

    private fun runTextRecognition(bitmap: Bitmap) {
        val image = InputImage.fromBitmap(bitmap, 0)
        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                val rawText = visionText.text
                binding.resultText.text = rawText

                // ✅ Pass to DetailsActivity for review
                val intent = Intent(this, DetailsActivity::class.java).apply {
                    putExtra("scanned_text", rawText)
                }
                startActivity(intent)
            }
            .addOnFailureListener {
                binding.resultText.text = "Error: ${it.message}"
            }
    }
}
