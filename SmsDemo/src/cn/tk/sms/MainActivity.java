package cn.tk.sms;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button contactBut,sendBut;
	private EditText et_number,et_content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		et_number = (EditText) this.findViewById(R.id.et_number);
		contactBut = (Button) this.findViewById(R.id.sendSms);
		sendBut = (Button) this.findViewById(R.id.sendBut);
		et_content = (EditText)this.findViewById(R.id.et_content);
		contactBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),SelectcontactActivity.class);
				//startActivity(intent);
				startActivityForResult(intent, 0);
			}
		});
		//发送短信
		sendBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String contact_number = et_number.getText().toString().trim();
				String content = et_content.getText().toString().trim();
				if(TextUtils.isEmpty(contact_number)){
					Toast.makeText(MainActivity.this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				SmsManager smsManager = SmsManager.getDefault();
				PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, new Intent(), 0);
				if(content.length()>=70){
					List<String> lists = smsManager.divideMessage(content);
					for(String str:lists){
						smsManager.sendTextMessage(contact_number, null, str, pendingIntent, null);
					}
				}else{
					smsManager.sendTextMessage(contact_number, null, content, pendingIntent, null);
				}
				Toast.makeText(MainActivity.this, "短信发送成功", Toast.LENGTH_SHORT).show();
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(data!=null){
			String number = data.getStringExtra("number");
			et_number.setText(number);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	

	

}
