package com.metrixia.bizcardscanr

import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.metrixia.bizcardscanr.databinding.ActivityExportBinding
import com.metrixia.bizcardscanr.room.CardDatabase
import java.io.File
import java.io.FileWriter

class ExportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnExportCsv.setOnClickListener {
            exportToCsv()
        }
    }

    private fun exportToCsv() {
        val cards = CardDatabase.getInstance(this).cardDao().getAll()

        val file = File(
            getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
            "BizCards_${System.currentTimeMillis()}.csv"
        )

        FileWriter(file).use { writer ->
            writer.append("ID,Text\n")
            for (card in cards) {
                writer.append("${card.id},\"${card.text}\"\n")
            }
        }

        Toast.makeText(this, "Exported to: ${file.absolutePath}", Toast.LENGTH_LONG).show()
    }
}
