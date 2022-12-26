package android.exercises.exe2.fragments;

import static android.text.TextUtils.isEmpty;

import android.exercises.exe2.R;
import android.exercises.exe2.utils.UserAuthentication;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginFormFragment extends Fragment {
    private View view;

    public LoginFormFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login_form_screen, container, false);

        Button loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(view -> login());

        Button backButton = view.findViewById(R.id.loginBackButton);
        backButton.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_loginFormScreen_to_entryScreen));

        return view;
    }

    public void login() {
        EditText emailField = view.findViewById(R.id.loginEmail);
        EditText passwordField = view.findViewById(R.id.loginPassword);

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (isEmpty(email) || isEmpty(password)) {
            Toast.makeText(getActivity(), "Email and password must be provided in order to log in", Toast.LENGTH_LONG).show();
        }

        Task<AuthResult> login = UserAuthentication.getInstance().login(email, password, getActivity());
        login.addOnSuccessListener(getActivity(), task -> Navigation.findNavController(getActivity().getCurrentFocus()).navigate(R.id.action_loginFormScreen_to_welcomeScreen));
    }
}