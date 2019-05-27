package pe.com.gadolfolozano.testapp.stepper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pe.com.gadolfolozano.testapp.R;

public class DummyFragment extends Fragment {
    private static final String ARG_TEXT = "arg_text";

    private String dummyText;

    public DummyFragment() {
        // Required empty public constructor
    }

    public static DummyFragment newInstance(String dummyText) {
        DummyFragment fragment = new DummyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, dummyText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dummyText = getArguments().getString(ARG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dummy, container, false);

        TextView textViewDummy = view.findViewById(R.id.textViewDummy);
        textViewDummy.setText(dummyText);

        return view;
    }
}
