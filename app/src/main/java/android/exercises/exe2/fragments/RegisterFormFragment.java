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

public class RegisterFormFragment extends Fragment {
    private View view;

    public RegisterFormFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register_form_screen, container, false);

        Button registerButton = view.findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(view -> register());

        Button backButton = view.findViewById(R.id.registerBackButton);
        backButton.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_registerFormScreen_to_entryScreen));

        return view;
    }

    public void register() {
        EditText emailField = view.findViewById(R.id.registerEmailEditText);
        EditText passwordField = view.findViewById(R.id.registerPasswordEditText);
        EditText addressField = view.findViewById(R.id.registerAddressEditText);
        EditText phoneField = view.findViewById(R.id.registerPhoneEditText);

        String email = emailField.getText().toString();
        String phone = phoneField.getText().toString();
        String address = addressField.getText().toString();
        String password = passwordField.getText().toString();

        if (isEmpty(email) || isEmpty(password) || isEmpty(address) || isEmpty(phone)) {
            Toast.makeText(getActivity(), "Some fields are missing. Make sure to fill them all before trying to register", Toast.LENGTH_LONG).show();
        } else {
            UserAuthentication userAuthentication = UserAuthentication.getInstance();

            Task<AuthResult> register = userAuthentication.register(email, phone, address, password, getActivity());
            register.addOnSuccessListener(getActivity(), task -> Navigation.findNavController(getActivity().getCurrentFocus()).navigate(R.id.action_registerFormScreen_to_welcomeScreen));
        }
    }
}