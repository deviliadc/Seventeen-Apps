package com.dicoding.seventeen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.seventeen.R
import com.google.android.material.textview.MaterialTextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_member)

        val name = intent.getStringExtra("name")
        val realName = intent.getStringExtra("real_name")
        val dob = intent.getStringExtra("dob")
        val position = intent.getStringExtra("position")
        val nationality = intent.getStringExtra("nationality")
        val description = intent.getStringExtra("description")
        val photo = intent.getIntExtra("member_photo", R.drawable.ic_launcher_foreground)

        val nameTextView = findViewById<MaterialTextView>(R.id.tvSeventeen_DetailActivity)
        val realNameTextView = findViewById<MaterialTextView>(R.id.tvMemberRealName_DetailActivity)
        val dobTextView = findViewById<MaterialTextView>(R.id.tvMemberDOB_DetailActivity)
        val positionTextView = findViewById<MaterialTextView>(R.id.tvMemberPosition_DetailActivity)
        val nationalityTextView = findViewById<MaterialTextView>(R.id.tvMemberNationality_DetailActivity)
        val descriptionTextView = findViewById<MaterialTextView>(R.id.tvMemberDescription_DetailActivity)
        val photoImageView = findViewById<ImageView>(R.id.imageMember_DetailActivity)
        val btnShare = findViewById<Button>(R.id.btnShare)

        nameTextView.text = getString(R.string.detail_name, name)
        realNameTextView.text = getString(R.string.detail_real_name, realName)
        dobTextView.text = getString(R.string.detail_dob, dob)
        positionTextView.text = getString(R.string.detail_position, position)
        nationalityTextView.text = getString(R.string.detail_nationality, nationality)
        descriptionTextView.text = getString(R.string.detail_description, description)

        Glide.with(this).load(photo).into(photoImageView)

        btnShare.setOnClickListener {
            val shareText = """
                Name: $name
                Real Name: $realName
                Date of Birth: $dob
                Position: $position
                Nationality: $nationality
                Description: $description
            """.trimIndent()

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)

            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        val btnBack = findViewById<Button>(R.id.btn_back)

        btnBack.setOnClickListener {
            finish()
        }
    }
}
