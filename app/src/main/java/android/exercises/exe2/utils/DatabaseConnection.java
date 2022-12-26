package android.exercises.exe2.utils;

import android.exercises.exe2.models.UserInfo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        this.database = FirebaseDatabase.getInstance();
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    FirebaseDatabase database;
    private final String USERS_COLLECTION_ID = "users";

    public void registerUser(String id, UserInfo userInfo) {
        DatabaseReference myRef = database.getReference(USERS_COLLECTION_ID).child(id);
        myRef.setValue(userInfo);
    }

    /*public UserInfo getUser(String id) {
        DatabaseReference myRef = database.getReference(USERS_COLLECTION_ID).child(id);
        final UserInfo[] value = new UserInfo[1];

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value[0] = dataSnapshot.getValue(UserInfo.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        return value[0];
    };*/
}
