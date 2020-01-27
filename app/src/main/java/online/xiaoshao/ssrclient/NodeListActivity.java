package online.xiaoshao.ssrclient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private SimpleAdapter simpleAdapter;
    private ListView listView;
    private List<NodeData> listNode;
    private List<Map<String, Object>> arrayList;
    private Handler handler;
    private String[] params = new String[]{"info", "detail"};
    private int[] ids = new int[]{R.id.info, R.id.detail};
    private String token;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_list);
        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<Map<String, Object>>();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.arg1 == 1) {
                    listNode = (List<NodeData>) msg.obj;
                    for (NodeData i : listNode) {
                        HashMap<String, Object> temp = new HashMap<String, Object>();
                        temp.put("info", i.getRemarks());
                        temp.put("detail", String.format(getString(R.string.info), i.getServer(), i.getServer_port(), i.getMethod(), i.getPassword(), i.getProtocol(), i.getObfs()));
                        arrayList.add(temp);
                        simpleAdapter = new SimpleAdapter(NodeListActivity.this, arrayList, R.layout.list, params, ids);
                        listView.setAdapter(simpleAdapter);
                        listView.setOnItemClickListener(NodeListActivity.this);
                    }
                }else {
                    Toast.makeText(NodeListActivity.this, getString(R.string.error2), Toast.LENGTH_SHORT).show();
                }
            }
        };
        token = getIntent().getStringExtra("token");
        url = "https://xiaoshao.online/api/node";
        new Node(url, "GET", "access_token", token, handler, NodeListActivity.this).start();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(NodeListActivity.this);
        builder.setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.alert))
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                })
                .setIcon(R.mipmap.ic_launcher)
                .show();
    }
}
