package com.example.mynew.ui.home;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mynew.R;

public class HomeFragment extends Fragment implements View.OnTouchListener {

    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;
    int[] resources = { R.drawable.xtj, R.drawable.xtj1,  R.drawable.xtj2, R.drawable.xtj3 };
    private HomeViewModel homeViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        mViewFlipper =  view.findViewById(R.id.viewFlipper);

        for (int i = 0; i < resources.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(resources[i]);
            mViewFlipper.addView(imageView);

            mViewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
            mViewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
            mViewFlipper.setAutoStart(true);
            mViewFlipper.setFlipInterval(2000); // flip every 2 seconds (2000ms)
            CustomGestureDetector customGestureDetector = new CustomGestureDetector();
            mGestureDetector = new GestureDetector(getActivity(), customGestureDetector);
        }
        return view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return false;
    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                mViewFlipper.showNext();
            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                mViewFlipper.showPrevious();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }



}
