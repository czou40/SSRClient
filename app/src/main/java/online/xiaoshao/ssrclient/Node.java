package online.xiaoshao.ssrclient;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a on 2018/1/1.
 */

public class Node extends NetworkConnection {

    public Node(String url, String method, String key1, String v1, Handler handler, Context context) {
        this.url = url;
        this.method = method;
        this.key1 = key1;
        this.v1 = v1;
        this.handler = handler;
        this.context = context;
    }

    @Override
    public List<NodeData> getResult() {
        String result = null;
        Map<String, String> params = new HashMap<>();
        params.put(key1, v1);
        try {
            result = net(url, params, method);
            JSONObject jsonObject = JSON.parseObject(result);
            if (jsonObject.getInteger("ret") == 1) {
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                //TypeReference typeRef = new TypeReference<List<NodeData>>() {
                //};
                //List<NodeData> list = (List<NodeData>) JSON.parseObject(jsonArray.toJSONString(), typeRef);
                return JSON.parseArray(jsonArray.toJSONString(), NodeData.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void run() {
        final List<NodeData> list = getResult();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Message message = Message.obtain();
                if (list != null) {
                    message.arg1 = 1;
                    message.obj = list;
                    handler.sendMessage(message);
                }
                else
                {
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }
}
