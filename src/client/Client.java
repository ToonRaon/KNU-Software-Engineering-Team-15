package client;

import org.json.JSONObject;

public class Client {
	
	JSONObject file;
	
	public Client() {
		file = new JSONObject();
	}
	
	public String getString(String key) {
		return file.getString(key);
	}
	
	public void setString(String key, String value) {
		file.put(key, value);
	}
	
	//key�� ����Ǿ� �ִ� ������ �ڿ� ���ο� value�� �����δ�.
	//addEnter true�̸� "���� ������" + "\n" + "�� ������"
	//addEnter false�̸� "���� ������" + "�� ������"
	public void addString(String key, String value, boolean addEnter) {
		String temp = file.getString(key);
		file.put(key, temp + (addEnter ? "\n" : "") + value);
	}
	
}
