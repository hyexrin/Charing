package com.hyexrin.chairing.admin.Mem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyexrin.chairing.R
import com.hyexrin.chairing.admin.Wheel.Wheel
import kotlinx.android.synthetic.main.activity_mem_main.*

class MemMainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mem_main)

        btnMemList.setOnClickListener {
            val intent = Intent(this, MemListActivity::class.java)
            startActivity(intent)
        }
        btnMemSearch.setOnClickListener {
            val intent = Intent(this, MemSearchActivity::class.java)
            startActivity(intent)
        }
    }

}