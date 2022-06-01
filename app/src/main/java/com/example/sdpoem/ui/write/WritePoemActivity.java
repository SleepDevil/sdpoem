package com.example.sdpoem.ui.write;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sdpoem.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@Dao
interface PoemDao {
    @Insert
    void insertPoem(Poem poem);

    @Delete
    void deletePoem(Poem poem);

    @Query("select * from poem")
    List<Poem> getPoemList();
}

@Database(entities = {Poem.class}, version = 1, exportSchema = false)
abstract class MyDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "written_poem";
    private static MyDatabase databaseInstance;

    public static synchronized MyDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room
                    .databaseBuilder(context.getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                    .build();
        }
        return databaseInstance;
    }

    public abstract PoemDao poemDao();
}

public class WritePoemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyDatabase db = Room.databaseBuilder(getApplicationContext(),
                MyDatabase.class, "written_poem").build();
        PoemDao poemDao = db.poemDao();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_poem);
        Button submitPoem = findViewById(R.id.submitPoem);
        EditText Title = findViewById(R.id.editTitle);
        EditText Content = findViewById(R.id.editContent);
        new Thread(() -> {
            Log.d("TAG", "onCreate33333: " + db.poemDao().getPoemList());
        }).start();

        submitPoem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = Title.getText().toString();
                String content = Content.getText().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd"); //设置时间格式
                formatter.setTimeZone(TimeZone.getTimeZone("GMT+08")); //设置时区
                Date curDate = new Date(System.currentTimeMillis()); //获取当前时间
                String createDate = formatter.format(curDate);
                Log.d("TAG", "onClick: 3333");
                new Thread(() -> {
                    Log.d("TAG", "onClick: 444");
                    db.poemDao().insertPoem(new Poem(title, content, createDate));
                }).start();
                Toast.makeText(WritePoemActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}