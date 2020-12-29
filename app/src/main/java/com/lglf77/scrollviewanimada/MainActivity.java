package com.lglf77.scrollviewanimada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends AppCompatActivity {

    ScrollView scrollView;
    CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scroll_view);
        coordinatorLayout = findViewById(R.id.coordinator_layout);

        class OutOfScreenBottomSheetBehavior extends BottomSheetBehavior<FrameLayout> {
            private int statusBarHeight;

            public OutOfScreenBottomSheetBehavior(Context context, AttributeSet attrs) {
                super(context, attrs);

                int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
                }
            }

            @Override
            public boolean layoutDependsOn(CoordinatorLayout parent, FrameLayout child, View dependency) {
                return dependency.getId() == R.id.behavior_dependency;
            }

            @Override
            public boolean onDependentViewChanged(CoordinatorLayout parent, FrameLayout child, View dependency) {
                int[] dependencyLocation = new int[2];

                dependency.getLocationInWindow(dependencyLocation);
                Log.d("BEHAVIOR", "Location: " + dependencyLocation[1]);

                if (dependencyLocation[1] <= statusBarHeight) {
                    if (getState() != STATE_EXPANDED) {
                        setState(STATE_EXPANDED);
                    }
                } else {
                    setState(STATE_HIDDEN);
                }
                return false;
            }
        }
    }
}