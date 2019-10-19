package com.sukai.butterfly_simple;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.sukai.butterfly_simple.annotations.Builder;
import com.sukai.butterfly_simple.annotations.Optional;
import com.sukai.butterfly_simple.annotations.Required;

/**
 * Created by sukaidev on 2019/10/03.
 */
@Builder
public class UserActivity extends AppCompatActivity {

    @Required
    String name;

    @Required
    int age;

    @Optional
    String title;

    @Optional
    String company;

    @Optional
    String wordPlace;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ((AppCompatTextView) findViewById(R.id.nameView)).setText(name);
        ((AppCompatTextView) findViewById(R.id.ageView)).setText(String.valueOf(age));
        ((AppCompatTextView) findViewById(R.id.titleView)).setText(title);
        ((AppCompatTextView) findViewById(R.id.companyView)).setText(company);
    }
}
