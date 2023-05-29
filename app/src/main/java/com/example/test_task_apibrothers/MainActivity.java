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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private final List<Integer> scrollImagesList = new ArrayList<>();
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

        scrollImagesList.add( R.drawable.scroll_image_1);
        scrollImagesList.add( R.drawable.scroll_image_2);
        scrollImagesList.add( R.drawable.scroll_image_3);
        scrollImagesList.add( R.drawable.scroll_image_4);
        scrollImagesList.add( R.drawable.scroll_image_5);

        scrollViewsAdapter1.setItem(scrollImagesList);
        scrollViewsAdapter2.setItem(scrollImagesList);
        scrollViewsAdapter3.setItem(scrollImagesList);

        binding.valueRate.setText(String.valueOf(rate));

        position1 = random.nextInt((max-min)) + 1 + min;
        position2 = random.nextInt((max-min)) + 1 + min;
        position3 = random.nextInt((max-min)) + 1 + min;

        binding.recyclerView1.smoothScrollToPosition(position1);
        binding.recyclerView2.smoothScrollToPosition(position2);
        binding.recyclerView3.smoothScrollToPosition(position3);

        position1 = random.nextInt((max-min)) + 1 + min;
        position2 = random.nextInt((max-min)) + 1 + min;
        position3 = random.nextInt((max-min)) + 1 + min;

        binding.buttonSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scrollRV(binding.recyclerView1, binding.recyclerView2, binding.recyclerView3, binding.balanceValue);
            }
        });
    }

    private int getCurrentItem(RecyclerView recyclerView) {
        return ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
    }

    private void scrollRV(RecyclerView recyclerView1, RecyclerView recyclerView2, RecyclerView recyclerView3, TextView balanceValue) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                recyclerView1.smoothScrollToPosition(position1);
            }
        }));

        executorService.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                recyclerView2.smoothScrollToPosition(position2);
            }
        }));

        executorService.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                recyclerView3.smoothScrollToPosition(position3);
            }
        }));

        executorService.execute(new Thread(new Runnable() {
            Handler handler = new Handler(Looper.getMainLooper());

            @Override
            public void run() {
                int index1 = getCurrentItem(recyclerView1) % 5;
                int index2 = getCurrentItem(recyclerView2) % 5;
                int index3 = getCurrentItem(recyclerView3) % 5;

                if (index1 == index2 && index2 == index3) {
                    balance += rate * 5;
                } else if (index1 == index2 || index2 == index3) {
                    balance += rate * 2;
                } else {
                    balance -= rate;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        balanceValue.setText(String.valueOf(balance));
                    }
                });

            }
        }));
        executorService.shutdown();

        position1 += random.nextInt((max-min) + 1 + min);
        position2 += random.nextInt((max-min) + 1 + min);
        position3 += random.nextInt((max-min) + 1 + min);

//        int index1 = getCurrentItem(recyclerView1) % 5;
//        int index2 = getCurrentItem(recyclerView2) % 5;
//        int index3 = getCurrentItem(recyclerView3) % 5;
//
//        if (index1 == index2 && index2 == index3) {
//            balance += rate * 5;
//        } else if (index1 == index2 || index2 == index3) {
//            balance += rate * 2;
//        } else {
//            balance -= rate;
//        }
//
//        balanceValue.setText(String.valueOf(balance));
    }
}
