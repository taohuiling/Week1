package com.example.mytqyb;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class MyHttpConnection {
	/**
	 * ����post���ݷ���
	 * @param path http·��
	 * @param param json��ʽ�Ĳ���
	 * @return
	 * @throws JSONException
	 */
	public String doPost(String path, JSONObject param) throws JSONException {
		String result = "";
		try {
			URL url = new URL(path);
			// ����HttpURLConnection�������ǿ��Դ���ҳ�л�ȡ��ҳ����
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// ��λΪ���룬���ó�ʱʱ��Ϊ5��
			conn.setConnectTimeout(5 * 1000);
			
			// http�����ڣ������Ҫ��Ϊtrue, Ĭ���������false;
			conn.setDoOutput(true);
			// �����Ƿ��httpUrlConnection���룬Ĭ���������true;
			//conn.setDoInput(true);
			// HttpURLConnection������ͨ��HTTPЭ������path·���ģ�������Ҫ��������ʽ�����Բ����ã���ΪĬ��Ϊget
			// Post ������ʹ�û���
			conn.setUseCaches(false);
			// �趨���͵����������ǿ����л���java����
			// (����������,�ڴ������л�����ʱ,��WEB����Ĭ�ϵĲ�����������ʱ������java.io.EOFException)
			conn.setRequestMethod("POST"); // ��������ʽ  
			//��ȡ��������󣬰�����������ʹ�������ӿ�
	        OutputStream out = conn.getOutputStream();
	        out.write(getPostDataString(param).getBytes("UTF-8"));
	        out.flush();
	       
	        out.close();  
			
			if (conn.getResponseCode() == 200) {
				InputStream is = conn.getInputStream(); // ��ȡ����������ʱ��������������
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader bufferReader = new BufferedReader(isr);
				String inputLine = "";
				while ((inputLine = bufferReader.readLine()) != null) {
					result += inputLine + "\n";
				}
				is.close();
				isr.close();
				bufferReader.close();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return result;

	}
	
	public String doGet(String path) {
		HttpURLConnection conn = null; //���Ӷ���  
        String reslut = "";  
        try {  
            URL url = new URL(path); //URL����  
            conn = (HttpURLConnection)url.openConnection(); //ʹ��URL��һ������  
            conn.setConnectTimeout(5 * 1000);
            conn.setDoInput(true); //����������������������  
            //conn.setDoOutput(true); //������������������ϴ�  
            conn.setUseCaches(false); //��ʹ�û���  
            conn.setRequestMethod("GET"); //ʹ��get����  
            InputStream is = conn.getInputStream();   //��ȡ����������ʱ��������������  
            InputStreamReader isr = new InputStreamReader(is);  
            BufferedReader bufferReader = new BufferedReader(isr);  
            String inputLine  = "";  
            while((inputLine = bufferReader.readLine()) != null){  
                reslut += inputLine + "\n";  
            }  
            is.close();
            isr.close();
            bufferReader.close();
  
        } catch (MalformedURLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{  
           
        }  
  
        return reslut;  
    
	}
	 private String getPostDataString(JSONObject params) throws UnsupportedEncodingException, JSONException {
	        StringBuilder result = new StringBuilder();
	        boolean first = true;
	        Iterator<String> keys = params.keys();
	        
	       
	        while( keys.hasNext() ) {
	            if (first)
	                first = false;
	            else
	                result.append("&");
	            String key = (String)keys.next();
	            result.append(URLEncoder.encode(key, "UTF-8"));
	            result.append("=");
	            result.append(URLEncoder.encode(params.getString(key), "UTF-8"));
	        }

	        return result.toString();
	    }
}
