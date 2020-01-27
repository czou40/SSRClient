package online.xiaoshao.ssrclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editEmail;
    private EditText editPasswd;
    private Button button;
    private Handler handler;
    private String email;
    private String passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.arg1==1){
                    String res = (String) msg.obj;
                    if (res.equals("1")) {
                        Toast.makeText(MainActivity.this, getString(R.string.error1), Toast.LENGTH_SHORT).show();
                    } else if (res.equals("2")) {
                        Toast.makeText(MainActivity.this, getString(R.string.error2), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, getString(R.string.loggedin), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,NodeListActivity.class);
                        intent.putExtra("token",res);
                        startActivity(intent);
                    }
                }
            }
        };
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPasswd = (EditText) findViewById(R.id.editPasswd);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        email=editEmail.getText().toString();
        passwd=editPasswd.getText().toString();
        if(!email.isEmpty() && !passwd.isEmpty()) {
            setProgressBarIndeterminateVisibility(true);
            Toast.makeText(this, getString(R.string.loggingin), Toast.LENGTH_SHORT).show();
            String url = "https://xiaoshao.online/api/token";
            new Token(url, "POST", "email", email, "passwd", passwd, handler, this).start();
        }
        else{
            Toast.makeText(this, getString(R.string.error0), Toast.LENGTH_SHORT).show();
        }
    }
}
