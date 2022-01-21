package mtrzepacz.quizgame;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import mtrzepacz.quizgame.database.DbAdapter;
import mtrzepacz.quizgame.database.StartingTopicsConstants;

public class TopicList extends AppCompatActivity {

    //view
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
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
        testList = new ArrayList<>();
        loadTopics();
        addNewItemButton = findViewById(R.id.addNewItemButton);
        topicNameEditText = findViewById(R.id.topicNameEditText);
        listView = (ListView) findViewById(R.id.ListView1);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_view_basic_item, testList);
        onClickListeners();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String string = (String) listView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), PlayOrAddNewQuestionActivity.class);
                intent.putExtra("categoryName",string);
                startActivity(intent);
            }
        });
        listView.setAdapter(arrayAdapter);
    }

    public void onClickListeners() {
        addNewItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testList.add(topicNameEditText.getText().toString());
                dbAdapter.addNewCategory(topicNameEditText.getText().toString());
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }

    public void loadTopics() {
        dbAdapter = new DbAdapter(getApplicationContext());
        dbAdapter.open();
        getAllTopics();
    }

    private void getAllTopics() {
        topicCursor = getAllTopicsFromDb();

        if (topicCursor != null && topicCursor.moveToFirst()) {
            do {
                //long id = topicCursor.getLong(StartingTopicsConstants.ID_COLUMN);
                String name = topicCursor.getString(StartingTopicsConstants.TOPIC_NAME_COLUMN);
                if (StringUtils.isNotBlank(name)){
                    testList.add(name);
                }

            } while (topicCursor.moveToNext());
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

