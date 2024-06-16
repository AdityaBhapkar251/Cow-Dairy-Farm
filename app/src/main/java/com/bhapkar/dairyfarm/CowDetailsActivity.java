package com.bhapkar.dairyfarm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;

import com.bhapkar.dairyfarm.R;
import com.bhapkar.dairyfarm.data.model.Cow;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CowDetailsActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private CowViewModel cowViewModel;
    private Cow cow;
    private ImageView cowImage;
    private Uri imageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow_details);

        int cowId = getIntent().getIntExtra("cowId", -1);
        if (cowId == -1) {
            finish(); // Invalid cow ID, finish the activity
            return;
        }

        cowViewModel = new ViewModelProvider(this).get(CowViewModel.class);
        cowViewModel.setCowId(cowId);

        TextView cowName = findViewById(R.id.cow_name);
        EditText cowDescription = findViewById(R.id.cow_description);
        EditText cowImageUrl = findViewById(R.id.cow_image_url);

        cowImage = findViewById(R.id.cow_image);

        cowViewModel.getCowById().observe(this, new Observer<Cow>() {
            @Override
            public void onChanged(Cow cow) {
                if (cow != null) {
                    CowDetailsActivity.this.cow = cow;
                    cowName.setText(cow.getName());
                    cowDescription.setText(cow.getDescription());
                    cowImageUrl.setText(cow.getImageUrl());
                    if (cow.getImageUrl() != null) {
                        Glide.with(CowDetailsActivity.this).load(Uri.parse(cow.getImageUrl())).into(cowImage);
                    }
                }
            }
        });

        cowImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        });
//option


        FloatingActionButton fab = findViewById(R.id.fab_add_details);
        fab.setOnClickListener(v -> {
            cow.setDescription(cowDescription.getText().toString());
            cow.setImageUrl(cowImageUrl.getText().toString());
            cowViewModel.update(cow);
        });
    }
    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Glide.with(this).load(imageUri).into(cowImage);
            if (cow != null) {
                cow.setImageUrl(imageUri.toString());
                cowViewModel.insert(cow); // Update cow with new image URI
            }
        }
    }
}