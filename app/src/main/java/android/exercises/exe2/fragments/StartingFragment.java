package android.exercises.exe2.fragments;

import android.exercises.exe2.R;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StartingFragment extends Fragment {
    public StartingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry_screen, container, false);

        final View.OnClickListener onClickListener = clickedView -> {
            NavController navController = Navigation.findNavController(clickedView);

            switch (clickedView.getId()) {
                case R.id.loginScreenBtn:
                    navController.navigate(R.id.action_entryScreen_to_loginFormScreen);

                    break;
                case R.id.RegisterScreenBtn:
                    navController.navigate(R.id.action_entryScreen_to_registerFormScreen5);

                    break;
            }
        };

        Button loginButton = view.findViewById(R.id.loginScreenBtn);
        loginButton.setOnClickListener(onClickListener);

        Button registerButton = view.findViewById(R.id.RegisterScreenBtn);
        registerButton.setOnClickListener(onClickListener);

        return view;
    }
}