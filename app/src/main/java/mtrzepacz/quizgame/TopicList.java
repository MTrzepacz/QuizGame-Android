package mtrzepacz.quizgame;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TopicList extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_list);
        listView = (ListView) findViewById(R.id.ListView1);

        ArrayList<String> testList = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i++)
            testList.add(i,i + " Item");

        adapter = new ArrayAdapter<String>(this,R.layout.list_view_basic_item,testList);

        listView.setAdapter(adapter);
    }
}

