package pe.com.gadolfolozano.testapp.stepper.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import pe.com.gadolfolozano.testapp.R;

public class StepperView extends LinearLayout implements ViewPager.OnPageChangeListener {

    private CustomViewPager vpAdvance;
    private List<Fragment> fragments = new ArrayList<>();

    private AppCompatButton btnBack;
    private AppCompatButton btnNext;
    private ViewPagerAdapter adapter = null;
    private ProgressBar pbAdvance;
    private int colorProgressBar;

    private int currentPosition = 0;

    public StepperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = inflate(context, R.layout.view_stepper, this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StepperView);

        colorProgressBar = typedArray.getColor(R.styleable.StepperView_colorProgress,
                ContextCompat.getColor(context, R.color.colorAccent));
        typedArray.recycle();

        btnBack = view.findViewById(R.id.btn_back);
        btnNext = view.findViewById(R.id.btn_next);
        pbAdvance = view.findViewById(R.id.pb_advance);
        vpAdvance = view.findViewById(R.id.vp_advance);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpAdvance.setCurrentItem(currentPosition - 1, true);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpAdvance.setCurrentItem(currentPosition + 1, true);
            }
        });

        setColorProgressBar(colorProgressBar);

    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;

        setupViews();
    }

    private void setupViews() {
        if (getContext() instanceof AppCompatActivity) {
            if (adapter == null) {
                adapter = new ViewPagerAdapter(((AppCompatActivity) getContext()).getSupportFragmentManager());
            }

            pbAdvance.setMax(fragments.size());
            pbAdvance.setProgress(1);

            adapter.setFragments(fragments);
            vpAdvance.setAdapter(adapter);
            vpAdvance.setOffscreenPageLimit(fragments.size());
            adapter.notifyDataSetChanged();
            vpAdvance.addOnPageChangeListener(this);
        }
    }

    public void setColorProgressBar(int color) {
        this.colorProgressBar = color;
        pbAdvance.getProgressDrawable().setColorFilter(colorProgressBar, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        //Do nothing
    }

    @Override
    public void onPageSelected(int i) {
        currentPosition = i;
        pbAdvance.setProgress(i + 1);
        btnBack.setEnabled(i != 0);
        btnNext.setEnabled(i != fragments.size() - 1);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        //Do nothing
    }

}
