package com.example.bmsce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;

public class aboutCollege extends AppCompatActivity {
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutcollege);

        userEmail = getIntent().getStringExtra("userEmail");
        if (userEmail == null) {
            userEmail = "No message found";
        }

        TextView textView = findViewById(R.id.textView);
        ImageView imageView1 = findViewById(R.id.imageView1);
        imageView1.setImageResource(R.drawable.aboutbms);

        String visionariesInfo = "<p><p><h2>The Visionaries</h2><p>B.M.S. College of Engineering (BMSCE) was Founded in the year 1946 by Late Sri. B. M. Sreenivasaiah a great visionary and philanthropist and nurtured by his illustrious son Late Sri. B. S. Narayan.</p><p>BMSCE is the first private sector initiative in engineering education in India. BMSCE has completed 70+ years of dedicated service in the field of Engineering Education.</p><p>Started with only 03 undergraduate courses, BMSCE today offers 13 Undergraduate & 16 Postgraduate courses both in conventional and emerging areas.</p>";

        textView.setText(HtmlCompat.fromHtml(visionariesInfo, HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    public void goBack(View view) {
        String userType = getIntent().getStringExtra("userType");

        Intent intent;
        if ("Student".equals(userType)) {
            intent = new Intent(this, StudentHome.class);
        } else if ("TPO".equals(userType)) {
            intent = new Intent(this, TPOHome.class);
            intent.putExtra("userEmail",userEmail);

        } else {
            throw new IllegalArgumentException("Invalid user type");
        }

        startActivity(intent);
        finish();
    }
}