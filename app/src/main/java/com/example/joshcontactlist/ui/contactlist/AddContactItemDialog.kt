package com.example.joshcontactlist.ui.contactlist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.joshcontactlist.data.db.entities.ContactItem
import com.example.joshcontactlist.databinding.DialogAddContactItemBinding

class AddContactItemDialog(context: Context, var addDialogListener: AddDialogListener):
    AppCompatDialog(context){

    private lateinit var binding: DialogAddContactItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddContactItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            tvAdd.setOnClickListener {
                val name = etName.text.toString()
                val number = etNumber.text.toString()
                val email = etEmail.text.toString()
                val occupation = etOccupation.text.toString()

                if (name.isEmpty() || number.isEmpty() || email.isEmpty() || occupation.isEmpty()) {
                    Toast.makeText(
                        context,
                        "Please enter all the information required",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                val contact = ContactItem(name, number, email, occupation)
                addDialogListener.onAddButtonClicked(contact)
                dismiss()
            }

            tvCancel.setOnClickListener {
                cancel()
            }
        }
    }
}