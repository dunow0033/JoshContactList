package com.example.joshcontactlist.ui.contactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joshcontactlist.R
import com.example.joshcontactlist.data.adapter.ContactItemAdapter
import com.example.joshcontactlist.data.db.ContactDatabase
import com.example.joshcontactlist.data.db.entities.ContactItem
import com.example.joshcontactlist.data.repositories.ContactRepository
import com.example.joshcontactlist.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = ContactDatabase(this)
        val repository = ContactRepository(database)
        val factory = ContactViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory).get(ContactViewModel::class.java)

        val adapter = ContactItemAdapter(listOf(), viewModel)

        binding.rvContactItems.layoutManager = LinearLayoutManager(this)
        binding.rvContactItems.adapter = adapter

        viewModel.getAllContactItems().observe(this, Observer {
            adapter.contacts = it
            adapter.notifyDataSetChanged()
        })

        binding.fab.setOnClickListener {
            AddContactItemDialog(this,
            object: AddDialogListener {
                override fun onAddButtonClicked(contact: ContactItem) {
                    viewModel.upsert(contact)
                }
            }).show()
        }

    }
}