package user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserInstanceMaker {
	public static void main(String[] args){
        File test= new File("User DB.txt");
        ArrayList<Student> userset= new ArrayList<>();
        Student temp = new Student(2015123456, "��浿", "��ǻ���к�", 2, 2.14, "test1@naver.com", "010-1264-1231");
        Student temp2 = new Student(-1, "������", "�����ڿ��л���ü", 777, 0, "year�� �����ڹ�ȣ", "�쳢��");
        Student temp3 = new Student(2015111111, "ȫ�浿", "��ǻ���к�", 2, 4.12, "test@naver.com", "010-1234-5678");

        userset.add(temp);
        userset.add(temp2);
        userset.add(temp3);

        try {
            FileOutputStream fos = new FileOutputStream(test);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try{
                oos.writeObject(userset);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }catch (Exception ex){
            System.out.println("��������� ����");
            System.exit(1);
        }
        
        System.out.println("�����Ϸ�");
    }
}
