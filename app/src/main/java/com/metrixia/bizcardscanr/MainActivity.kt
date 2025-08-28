package com.metrixia.bizcardscanr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.metrixia.bizcardscanr.databinding.ActivityMainBinding
import com.metrixia.bizcardscanr.room.CardDatabase
import com.metrixia.bizcardscanr.room.CardEntity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = CardDatabase.getInstance(this).cardDao()
        val cards = dao.getAll()

        adapter = CardAdapter(cards)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.totalCount.text = "Total: ${cards.size}"

        binding.btnScan.setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }

        binding.btnExport.setOnClickListener {
            startActivity(Intent(this, ExportActivity::class.java))
        }
    }
}
