package com.example.bmsce;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;

public class aboutCollege extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutcollege);

        TextView textView = findViewById(R.id.textView);
        ImageView imageView1 = findViewById(R.id.imageView1);
        imageView1.setImageResource(R.drawable.aboutbms);

        String visionariesInfo = "<p><p><h2>The Visionaries</h2><p>B.M.S. College of Engineering (BMSCE) was Founded in the year 1946 by Late Sri. B. M. Sreenivasaiah a great visionary and philanthropist and nurtured by his illustrious son Late Sri. B. S. Narayan.</p><p>BMSCE is the first private sector initiative in engineering education in India. BMSCE has completed 70+ years of dedicated service in the field of Engineering Education.</p><p>Started with only 03 undergraduate courses, BMSCE today offers 13 Undergraduate & 16 Postgraduate courses both in conventional and emerging areas.</p>";

        textView.setText(HtmlCompat.fromHtml(visionariesInfo, HtmlCompat.FROM_HTML_MODE_LEGACY));
    }
}