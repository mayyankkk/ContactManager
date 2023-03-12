package com.example.contactmanager

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.contactmanager.databinding.ActivityAddContactBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddContact : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    private lateinit var binding: ActivityAddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dialog= Dialog(this)
        dialog.setContentView(R.layout.dialogue_box)
        val buttonOk: Button? =dialog.findViewById(R.id.ok)




        binding.addBtn.setOnClickListener {
            if(binding.nameInput.text.toString()!="" && binding.numInput.text.toString()!=""){
                val contact= Contact(binding.nameInput.text.toString(),binding.numInput.text.toString())
                databaseReference=FirebaseDatabase.getInstance().getReference("Contacts")
                databaseReference.setValue(contact).addOnSuccessListener {
                    Toast.makeText(this,"Contact Added",Toast.LENGTH_SHORT).show()
                    dialog.show()
                    buttonOk?.setOnClickListener {
                        dialog.dismiss()
                    }
                }
            }
        }
    }
}