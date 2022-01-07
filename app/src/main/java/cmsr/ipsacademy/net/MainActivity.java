package cmsr.ipsacademy.net;

import android.os.Bundle;

import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    EditText title;
    EditText message;
    AppCompatButton buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("all");

        title = findViewById(R.id.noti_title);
        message = findViewById(R.id.noti_message);
        buttonSend = findViewById(R.id.noti_send);

        buttonSend.setOnClickListener(view -> {

            if (!title.getText().toString().isEmpty() && !message.getText().toString().isEmpty()){

                FcmNotificationsSender notificationsSender = new FcmNotificationsSender("/topics/all", title.getText().toString(), message.getText().toString(), getApplicationContext(), MainActivity.this);
                notificationsSender.SendNotifications();

            }else{
                Toast.makeText(this, "The noti details is Empty", Toast.LENGTH_SHORT).show();
            }

        });
    }
}