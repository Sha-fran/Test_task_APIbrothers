package com.example.test_task_apibrothers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.example.test_task_apibrothers.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final List<DataBox> scrollImagesList = new ArrayList<>();

    private final ScrollViewsAdapter scrollViewsAdapter1 = new ScrollViewsAdapter();
    private final ScrollViewsAdapter scrollViewsAdapter2 = new ScrollViewsAdapter();
    private final ScrollViewsAdapter scrollViewsAdapter3 = new ScrollViewsAdapter();
    private final int min = 35, max = 50;
    private final Random random = new Random();
    private int position1;
    private int position2;
    private int position3;
    int balance = 0, rate = 100;

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

        scrollViewsAdapter1.setItem(createScrollImagesList());
        scrollViewsAdapter2.setItem(createScrollImagesList());
        scrollViewsAdapter3.setItem(createScrollImagesList());

        position1 = random.nextInt((max-min)) + 1 + min;
        position2 = random.nextInt((max-min)) + 1 + min;
        position3 = random.nextInt((max-min)) + 1 + min;

        binding.recyclerView1.smoothScrollToPosition(position1);
        binding.recyclerView2.smoothScrollToPosition(position2);
        binding.recyclerView3.smoothScrollToPosition(position3);

        position1 = random.nextInt((max-min)) + 1 + min;
        position2 = random.nextInt((max-min)) + 1 + min;
        position3 = random.nextInt((max-min)) + 1 + min;

        binding.balanceValue.setText(String.valueOf(balance));
        binding.valueRate.setText(String.valueOf(rate));

        binding.buttonSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollRV(binding.recyclerView1, binding.recyclerView2, binding.recyclerView3, binding.balanceValue);
            }
        });
    }

    private int getCurrentItem(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View view = layoutManager.findViewByPosition(position);
        ScrollViewsAdapter.ScrollViewHolder viewHolder = (ScrollViewsAdapter.ScrollViewHolder) recyclerView.getChildViewHolder(view);
        return viewHolder.getIndex();
    }

    private void scrollRV(RecyclerView recyclerView1, RecyclerView recyclerView2, RecyclerView recyclerView3, TextView balanceValue) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                recyclerView1.smoothScrollToPosition(position1);
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                recyclerView2.smoothScrollToPosition(position2);
            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                recyclerView3.smoothScrollToPosition(position3);
            }
        });
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();

            position1 += random.nextInt((max-min) + 1 + min);
            position2 += random.nextInt((max-min) + 1 + min);
            position3 += random.nextInt((max-min) + 1 + min);

            int index1 = getCurrentItem(recyclerView1);
            int index2 = getCurrentItem(recyclerView2);
            int index3 = getCurrentItem(recyclerView3);

            if (index1 == index2 && index2 == index3) {
                balance += rate * 5;
            } else if (index1 == index2 || index2 == index3) {
                balance += rate * 2;
            } else {
                balance -= rate;
            }
            balanceValue.setText(String.valueOf(balance));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<DataBox> createScrollImagesList() {
        final int[] images = {
                R.drawable.scroll_image_1,
                R.drawable.scroll_image_2,
                R.drawable.scroll_image_3,
                R.drawable.scroll_image_4,
                R.drawable.scroll_image_5
        };

        for (int i = 0; i < images.length; i++) {
            DataBox dataBox = new DataBox(images[i], i );
            scrollImagesList.add(dataBox);
        }
        System.out.println(scrollImagesList.size());
        return scrollImagesList;
    }

}
