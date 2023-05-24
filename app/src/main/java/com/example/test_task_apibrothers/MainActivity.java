package com.example.test_task_apibrothers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.test_task_apibrothers.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final List<Integer> scrollImagesList = new ArrayList<>();
    private final ScrollViewsAdapter scrollViewsAdapter1 = new ScrollViewsAdapter();
    private final ScrollViewsAdapter scrollViewsAdapter2 = new ScrollViewsAdapter();
    private final ScrollViewsAdapter scrollViewsAdapter3 = new ScrollViewsAdapter();
    private final int min = 45;
    private final int max = 50;
    private final Random random = new Random();
    private int position1 = random.nextInt((max-min) + 1 + min);
    private int position2 = random.nextInt((max-min) + 1 + min);
    private int position3 = random.nextInt((max-min) + 1 + min);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.recyclerView1.setLayoutManager(new GridLayoutManager(this.getBaseContext(), 1));
        binding.recyclerView1.setAdapter(scrollViewsAdapter1);
        binding.recyclerView2.setLayoutManager(new GridLayoutManager(this.getBaseContext(),1));
        binding.recyclerView2.setAdapter(scrollViewsAdapter2);
        binding.recyclerView3.setLayoutManager(new GridLayoutManager(this.getBaseContext(),1));
        binding.recyclerView3.setAdapter(scrollViewsAdapter3);

        scrollImagesList.add( R.drawable.scroll_image_1);
        scrollImagesList.add( R.drawable.scroll_image_2);
        scrollImagesList.add( R.drawable.scroll_image_3);
        scrollImagesList.add( R.drawable.scroll_image_4);
        scrollImagesList.add( R.drawable.scroll_image_5);

        scrollViewsAdapter1.setItem(scrollImagesList);
        scrollViewsAdapter2.setItem(scrollImagesList);
        scrollViewsAdapter3.setItem(scrollImagesList);

        binding.buttonSpin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        scrollRV();
    }

    private void scrollRV() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
                recyclerView1.smoothScrollToPosition(position1);
                position1 += random.nextInt((max-min) + 1 + min);
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
                recyclerView2.smoothScrollToPosition(position2);
                position2 += random.nextInt((max-min) + 1 + min);
            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                RecyclerView recyclerView3 = findViewById(R.id.recyclerView3);
                recyclerView3.smoothScrollToPosition(position3);
                position3 += random.nextInt((max-min) + 1 + min);
            }
        });
        thread3.start();
    }
}
