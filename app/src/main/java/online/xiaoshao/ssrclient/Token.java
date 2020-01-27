package online.xiaoshao.ssrclient;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by a on 2018/1/1.
 */

public class Token extends NetworkConnection {
    public Token(String url, String method, String key1, String v1, String key2, String v2, Handler handler, Context context) {
        this.url = url;
        this.method = method;
        this.key1 = key1;
        this.v1 = v1;
        this.key2 = key2;
        this.v2 = v2;
        this.handler = handler;
        this.context = context;
    }

    @Override
    public String getResult() {
        String result = null;
        Map<String,String> params = new HashMap<>();
        params.put(key1, v1);
        params.put(key2, v2);
        try {
            result = net(url, params, method);
            JSONObject jsonObject = JSON.parseObject(result);
            if (jsonObject.getInteger("ret") == 1) {
                JSONObject data = jsonObject.getJSONObject("data");
                return data.getString("token");
            } else {
                return "1";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "2";
        }
    }

    @Override
    public void run() {
        final String res = getResult();
        handler.post(new Runnable() {
            @Override
            public void run() {
                    Message message = Message.obtain();
                    message.arg1=1;
                    message.obj=res;
                    handler.sendMessage(message);
            }
        });
    }
}
