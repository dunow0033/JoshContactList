package com.example.joshcontactlist.ui.contactlist

import com.example.joshcontactlist.data.db.entities.ContactItem

interface AddDialogListener {
    fun onAddButtonClicked(contact: ContactItem)
}