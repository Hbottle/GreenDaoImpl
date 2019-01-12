package com.bottle.alive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bottle.alive.db.DaoManager;
import com.bottle.alive.db.MyDatabase;
import com.bottle.alive.db.dao.UserDaoProxy;
import com.bottle.alive.db.schema.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private UserDaoProxy userDaoHandler;
    private Button btnGreenDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnGreenDao = findViewById(R.id.btnGreenDao);
        this.btnGreenDao.setOnClickListener(this);
        MyDatabase.init(this, "default");
        userDaoHandler = DaoManager.getInstance().getUserDaoHandler();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGreenDao:
                onGreendaoTest(v);
                break;
            default:
                break;
        }
    }

    public void onGreendaoTest(View view) {
        long id = System.currentTimeMillis();
        com.bottle.alive.db.schema.User user = new com.bottle.alive.db.schema.User(id, "User-" + id, 17, 170);
        long userId = userDaoHandler.insert(user);
        String msg = "GreenDao 插入 User:" + userId;
        Log.d(TAG, msg);
        List<User> users = userDaoHandler.loadAll();
        if(users != null && users.size() > 0) {
            String tips = "count:" + (users == null? 0 : users.size());
            Toast.makeText(this, tips, Toast.LENGTH_SHORT).show();
            for(com.bottle.alive.db.schema.User user1 : users) {
                Log.d(TAG, user1.getUserName());
            }
        }
    }
}
