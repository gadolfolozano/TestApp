package pe.com.gadolfolozano.testapp.stepper;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import pe.com.gadolfolozano.testapp.R;
import pe.com.gadolfolozano.testapp.stepper.custom.StepperView;

public class StepperActiviy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepper_activiy);

        StepperView stepperView = findViewById(R.id.stepperView);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(DummyFragment.newInstance(getString(R.string.dummy_text_1)));
        fragments.add(DummyFragment.newInstance(getString(R.string.dummy_text_2)));
        fragments.add(DummyFragment.newInstance(getString(R.string.dummy_text_3)));
        fragments.add(DummyFragment.newInstance(getString(R.string.dummy_text_4)));
        stepperView.setFragments(fragments);
    }
}
