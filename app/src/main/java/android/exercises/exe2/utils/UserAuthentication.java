package android.exercises.exe2.utils;

import static android.text.TextUtils.isEmpty;
import static java.lang.String.format;
import static java.util.Objects.isNull;

import android.exercises.exe2.R;
import android.exercises.exe2.models.UserInfo;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserAuthentication {
    private final FirebaseAuth firebaseAuth;
    private static UserAuthentication instance;

    private UserAuthentication() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public static UserAuthentication getInstance() {
        if (isNull(instance)) {
            instance = new UserAuthentication();
        }

        return instance;
    }


    public Task<AuthResult> login(String email, String password, FragmentActivity executor) {
        Task<AuthResult> loginTaskResult = firebaseAuth.signInWithEmailAndPassword(email, password);
        loginTaskResult.addOnSuccessListener(executor, task -> Toast.makeText(executor, format("%s was successfully logged in", getCurrentUser().getEmail()), Toast.LENGTH_LONG).show());
        loginTaskResult.addOnFailureListener(executor, task -> Toast.makeText(executor, format("Failed to log in with email %s. %s", email, task.getMessage()), Toast.LENGTH_LONG).show());

        return loginTaskResult;
    }

    public Task<AuthResult> register(String email, String phone, String address, String password, FragmentActivity executor) {
        Task<AuthResult> registerTaskResult = firebaseAuth.createUserWithEmailAndPassword(email, password);
        registerTaskResult.addOnFailureListener(executor, task -> Toast.makeText(executor, format("Register failed. %s", task.getMessage()), Toast.LENGTH_LONG).show());
        registerTaskResult.addOnSuccessListener(executor, task -> {
            String userId = getCurrentUser().getUid();
            saveUserInfo(new UserInfo(email, userId, phone, address));

            login(email, password, executor);

            Toast.makeText(executor, format("%s was successfully registered", getCurrentUser().getEmail()), Toast.LENGTH_LONG).show();
        });

        return registerTaskResult;
    }

    private void saveUserInfo(UserInfo userInfo) {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.registerUser(userInfo.getId(), userInfo);
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }
}
