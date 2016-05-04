package org.yhn.yq.client.view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yhn.yq.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TrendsAcitivy extends Activity{
	private List<Map<String,String>> data = new ArrayList<Map<String,String>>(); 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_trends);
		
		ListView listView = (ListView) findViewById(R.id.lv_trends);  
       
		jieXi(BuddyActivity.buddyStr);
        SimpleAdapter adapter=new SimpleAdapter(  
                this,  
                data,
                R.layout.trends_listview_item,
                new String[]{"nick","trends"},
                new int[]{R.id.nick_trends,R.id.trends_trends});  
        listView.setAdapter(adapter);
		
	}
	
	private void jieXi(String buddyStr){
		String ss[] = buddyStr.split(" ");
        for(String a: ss){
        	if(a!=""){
	        	String b[]=a.split("_");
	            Map<String,String> map=new  HashMap<String,String>();
	            map.put("nick", b[1]);
	            map.put("trends", b[3]);
	            data.add(map);
        	}
        }
	}
}
