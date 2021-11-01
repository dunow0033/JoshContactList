package com.example.joshcontactlist.ui.contactlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.joshcontactlist.data.repositories.ContactRepository

@Suppress("UNCHECKED_CAST")
class ContactViewModelFactory(
    private val repository: ContactRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactViewModel(repository) as T
    }
}