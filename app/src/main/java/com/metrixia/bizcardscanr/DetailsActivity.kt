package com.metrixia.bizcardscanr

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.metrixia.bizcardscanr.databinding.ActivityDetailsBinding
import com.metrixia.bizcardscanr.room.CardDatabase
import com.metrixia.bizcardscanr.room.CardEntity

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receive text from ScanActivity
        val rawText = intent.getStringExtra("scanned_text") ?: ""

        // Try to pre-fill some fields (very basic parsing)
        binding.etNotes.setText(rawText)

        // Save button
        binding.btnSave.setOnClickListener {
            val card = CardEntity(
                name = binding.etName.text.toString().ifBlank { null },
                email = binding.etEmail.text.toString().ifBlank { null },
                phone = binding.etPhone.text.toString().ifBlank { null },
                company = binding.etCompany.text.toString().ifBlank { null },
                notes = binding.etNotes.text.toString().ifBlank { null }
            )

            CardDatabase.getInstance(this).cardDao().insertCard(card)

            Toast.makeText(this, "Card saved!", Toast.LENGTH_SHORT).show()
            finish() // Close activity
        }
    }
}
