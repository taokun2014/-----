package cn.tk.service;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import cn.tk.domain.ContactInfo;

public class ContactInfoUtils {

	public static List<ContactInfo> readinfo(Context context){
	
		List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
		ContentResolver resolver = context.getContentResolver();
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		
		Uri uriData = Uri.parse("content://com.android.contacts/data");
		
		Cursor cursor = resolver.query(uri, new String[]{"contact_id"}, null, null, null);//获取存放的联系人ID
		
		while(cursor.moveToNext()){
			String id = cursor.getString(0);
			if(id!=null){
				Cursor datacursor = resolver.query(uriData, new String[]{"data1","mimetype"}, "raw_contact_id=?", new String[]{id}, null);
				ContactInfo contactInfo = new ContactInfo();
				while(datacursor.moveToNext()){
					String data= datacursor.getString(0);
					String mimetype = datacursor.getString(1);
					System.out.println("----------data= " + data);
					System.out.println("----------mimetype= " + mimetype);
					
					if("vnd.android.cursor.item/name".equals(mimetype)){
						contactInfo.setName(data);
					}else if("vnd.android.cursor.item/phone_v2".equals(mimetype)){
						contactInfo.setNumber(data);
					}
				}
				contactInfos.add(contactInfo);
			}
		}
		return contactInfos;
	}
}
