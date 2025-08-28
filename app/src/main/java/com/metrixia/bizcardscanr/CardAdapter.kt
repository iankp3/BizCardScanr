package com.metrixia.bizcardscanr

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.metrixia.bizcardscanr.room.CardEntity

class CardAdapter(private val cards: List<CardEntity>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]
        holder.binding.cardText.text = card.text
    }

    override fun getItemCount() = cards.size
}