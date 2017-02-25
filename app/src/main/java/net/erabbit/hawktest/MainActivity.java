package net.erabbit.hawktest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.orhanobut.hawk.Hawk;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends Activity {

    Map<Date,Integer> createdMap, loadedMap;
    //Map<Long,Integer> createdMap, loadedMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Hawk.init(this).build();
        createdMap = new TreeMap<>();
        createdMap.put(new Date(), 1);
        //createdMap.put(new Date().getTime(), 1);
        if(Hawk.put("map", createdMap)) {
            loadedMap = Hawk.get("map");
            if(loadedMap != null)
                for(Map.Entry<Date,Integer> entry : loadedMap.entrySet()) {
                //for(Map.Entry<Long,Integer> entry : loadedMap.entrySet()) {
                    Log.i("hawk test", "key = " + entry.getKey() + ", value = " + entry.getValue());
                }
            else
                Log.i("hawk test", "load failed");
        }
        else
            Log.i("hawk test", "save failed");
    }
}
