package mtrzepacz.quizgame;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import mtrzepacz.quizgame.database.DbAdapter;
import mtrzepacz.quizgame.database.StartingTopicsConstants;
import mtrzepacz.quizgame.database.TopicDto;

public class TopicList extends AppCompatActivity {

    //view
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private Button addNewItemButton;
    private EditText topicNameEditText;


    private List<String> testList;

    //database
    private Cursor topicCursor;
    private DbAdapter dbAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_list);
        addNewItemButton = findViewById(R.id.addNewItemButton);
        topicNameEditText = findViewById(R.id.topicNameEditText);
        listView = (ListView) findViewById(R.id.ListView1);
        testList = new ArrayList<>();
        loadTopics();
        ///for(int i = 0 ; i < 20 ; i++)
          //  testList.add(i,i + " Item");

        adapter = new ArrayAdapter<String>(this,R.layout.list_view_basic_item,testList);

        listView.setAdapter(adapter);
        onClickListeners();
    }

    public void onClickListeners(){
        addNewItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testList.add(topicNameEditText.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void loadTopics(){
        dbAdapter = new DbAdapter(getApplicationContext());
        dbAdapter.open();
        getAllTopics();
    }

    private void getAllTopics() {
        topicCursor = getAllTopicsFromDb();

        if(topicCursor != null && topicCursor.moveToFirst()){
            do {
                //long id = topicCursor.getLong(StartingTopicsConstants.ID_COLUMN);
                String name = topicCursor.getString(StartingTopicsConstants.NAME_COLUMN);
                testList.add(name);
            } while(topicCursor.moveToNext());
        }
    }

    private Cursor getAllTopicsFromDb() {
        topicCursor = dbAdapter.getAllTopics();
        if (topicCursor != null) {
            startManagingCursor(topicCursor);
            topicCursor.moveToFirst();
        }
        return topicCursor;
    }
}

