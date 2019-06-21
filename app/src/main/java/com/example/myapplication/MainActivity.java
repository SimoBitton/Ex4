package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
Main window class - from here the program begins
 */
public class MainActivity extends AppCompatActivity {

    /*
    Variables to be bound with elements from view (start activity)
     */
    Button button;
    EditText ed1, ed2;
    TextView IP, Port;


   /*
   Variables to save data from users input in first activity
    */
    public static String ipAddress;
    public static String portNumber;

    /*
    a static variable to be updated when connection has begun
     */
    public static TcpClient mTcpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        IP = (TextView) findViewById(R.id.textView);
        Port = (TextView) findViewById(R.id.textView2);
        /*
        code to be executed when "connect" bottom is pressed
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ipAddress = ed2.getText().toString();
                portNumber = ed1.getText().toString();
                new ConnectTask().execute("");
                Intent intent = new Intent(v.getContext(), SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    /*
    Async Task to make sure each command will be committed without a NetworkOnMainThreadException
     */
    public class ConnectTask extends AsyncTask<String, String, TcpClient> {

        @Override
        protected TcpClient doInBackground(String... message) {
            //we create a TCPClient object
            mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
                    publishProgress(message);
                }
            });
            mTcpClient.run();
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //response received from server
            Log.d("test", "response " + values[0]);
            //process server response here....
        }
    }
}