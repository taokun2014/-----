package cn.tk.sms;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cn.tk.domain.ContactInfo;
import cn.tk.service.ContactInfoUtils;

public class SelectcontactActivity extends Activity {
	private ListView listview;
	private List<ContactInfo> contactInfos = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectcontact);
		listview = (ListView) findViewById(R.id.listview);
		contactInfos = ContactInfoUtils.readinfo(this);
		if(contactInfos!=null){
			System.out.println("----------contactInfos length---------"+contactInfos.size());
		}
		listview.setAdapter(new MyAdapter());
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//System.out.println("------arg2--------"+arg2);
				ContactInfo contactinfo = contactInfos.get(arg2);
				String number = contactinfo.getNumber();
				
				//关闭当前的activity，调用下面的方法
				Intent data = new Intent();
				data.putExtra("number", number);
				setResult(0, data);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selectcontact, menu);
		return true;
	}
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return contactInfos.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ContactInfo info = contactInfos.get(position);
			View view = View.inflate(SelectcontactActivity.this, R.layout.contact_item, null);
			TextView tvName = (TextView) view.findViewById(R.id.tv_name);
			TextView tvNumber = (TextView) view.findViewById(R.id.tv_number);
			tvName.setText(info.getName());
			tvNumber.setText(info.getNumber());
			return view;
		}
		
	}

}
