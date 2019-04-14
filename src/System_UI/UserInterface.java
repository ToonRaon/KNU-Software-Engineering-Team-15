package System_UI;

import User.*;
import Exchange.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.util.Calendar;

public class UserInterface extends JFrame {
    private JList list;
    private JList list2;
    private JList list3;
    private JTabbedPane Tab = new JTabbedPane(JTabbedPane.LEFT);

	private credit_UI CreditViewIsapped;
	
    private static RecruitmentList sampleList;
    private static File fp = new File("database/rerucitment/Rerucitment DB.txt");

    Student user;
    Administer admin;
    Guest guest;
    int userType;
    int year, month, date;
    
    
    public UserInterface(Student userinfo) {
        super("��ȯ�л� ���α׷�");

        if(userinfo.getStudentID() == -1){
            admin = new Administer(userinfo.getName(), userinfo.getYear());
            userType = 1;
        }
        else if(userinfo.getStudentID() == -2){
            guest = new Guest();
            userType = 2;
        }
        else{
            user = userinfo;
            userType = 0;
        }

        //DB�� �ҷ����� �κ�
        if(fp.length() > 0) {
            try {
                FileInputStream fis = new FileInputStream(fp);
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    sampleList = (RecruitmentList) ois.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("��������� ����");
                System.exit(1);
            }
        }
        else{
            sampleList = new RecruitmentList();
        }
        
        //���� ��¥�� �ҷ��ͼ� ����������� ���¸� �ٲ��ִ� �κ�
        Calendar cal = Calendar.getInstance();
        year = cal.get(cal.YEAR);
        month = cal.get(cal.MONTH) + 1;
        date = cal.get(cal.DATE);
        
        sampleList.setRecruitState(year, month, date);
        

        setLayout(null);

        Tab.setBounds(10, 10, 650, 480);

        Tab.addTab("����", new Initial());
        Tab.addTab("�������� ��ȸ", new RecruitLook());
        if(userType == 0) {
            Tab.addTab("�����Ȳ ��ȸ", new StateLook());
    		try {
				Tab.addTab("�̼����� ����", new credit_UI(user.getStudentID(), this, false));
	    		CreditViewIsapped = new credit_UI(user.getStudentID(), this, true);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
    		Tab.addTab("������� ��ȸ", CreditViewIsapped);
        }
        Tab.addTab("�İ߽��� ��ȸ", new JPanel());    //�̱���
        try {
			Tab.addTab("QNA �Խ���", new QnA_UI(userinfo.getStudentID()));
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
        if(userType == 1) {
            Tab.addTab("�������� �ۼ�", new RecruitCreate());
            Tab.addTab("�������� ����", new RecruitDelete());
        }

        Tab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                list.clearSelection();
                if(userType == 0)
                    list3.clearSelection();
            }
        });

        add(Tab);

        //����ɶ� �����ͺ��̽��� ���ε��ϴ� �κ�
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    FileOutputStream fos = new FileOutputStream(fp);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    try{
                        oos.writeObject(sampleList);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }catch (Exception ex){
                    System.out.println("��������� ����");
                    System.exit(1);
                }
            }
        });
    }

    class Initial extends JPanel{
		private JLabel ment;

        public Initial(){
            list = new JList(sampleList.printList());   //��ȸ�� �ʿ��� ����Ʈ
            list.setVisibleRowCount(20);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setFixedCellHeight(20);
            list.setFixedCellWidth(120);

            list2 = new JList(sampleList.printList2());  //������ �ʿ��� ����Ʈ
            list2.setVisibleRowCount(20);
            list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list2.setFixedCellHeight(20);
            list2.setFixedCellWidth(300);

            if(userType == 0) {
                list3 = new JList(sampleList.printState(user.getStudentID()));  //�����Ȳ�� �ʿ��� ����Ʈ
                list3.setVisibleRowCount(19);
                list3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                list3.setFixedCellHeight(19);
                list3.setFixedCellWidth(500);
            }

            setLayout(null);
            JLabel intro;
            if(userType == 0){
                intro = new JLabel("�������. " + user.getName() + "��       �й� : " + user.getStudentID());
            }
            else if(userType == 1){
                intro = new JLabel("�������. " + admin.getName() + "��");
            }
            else{
                intro = new JLabel("�մ� �������� �����ϼ̱���! ������� �����̿�");
            }
            intro.setBounds(20,140, 400, 20);
            add(intro);

            JLabel dateYo = new JLabel("���� ��¥ : " + year +"�� "+ month + "�� " + date + "��");
            dateYo.setBounds(20, 220, 400, 20);
            add(dateYo);
         
            ment = new JLabel("���Ͻô� ����� ���� �޴����� �������ּ���.");
            ment.setBounds(20, 180, 300, 20);
            add(ment);
        }
    }
    class RecruitLook extends JPanel{
        private JButton select;
        private JPanel con;

        public RecruitLook(){
            setLayout(new FlowLayout());
            setSize(500, 400);

            add(new JScrollPane(list));

            con = new JPanel();
            con.setPreferredSize(new Dimension(380,450));
            con.setBackground(Color.WHITE);
            add(con);

            list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int index = list.getSelectedIndex();
                    con.removeAll();
                    if(index >= 0) {
                        con.add(sampleList.printContents(index));
                        select = new JButton("���ÿ��� �ۼ�");

                        select.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                            	if(sampleList.getRecruitState(index) == 0) {
                            		if (sampleList.checkUser(index, user.getStudentID()) == false) {
                                        Application newone = user.ApplicationCreate(sampleList.getRecruitNum(index));
                                        sampleList.apply(index, newone);
                                        list3.setListData(sampleList.printState(user.getStudentID()));
                                        JOptionPane.showMessageDialog(null, "���ÿ��� �ۼ��� �Ϸ�Ǿ����ϴ�.", "�˸�", JOptionPane.PLAIN_MESSAGE);
                                    } else
                                        JOptionPane.showMessageDialog(null, "�̹� ������ ���������Դϴ�.", "�˸�", JOptionPane.PLAIN_MESSAGE);
                            	}
                            	else {
                            		JOptionPane.showMessageDialog(null, "��û�� �� ���� �����Դϴ�.", "�˸�", JOptionPane.PLAIN_MESSAGE);
                            	}
                            }
                        });
                        if(userType == 0)
                            con.add(select);
                    }
                    con.revalidate();
                    con.repaint();
                }
            });
        }
    }
    class StateLook extends JPanel{
        private JButton del;
        private JButton sel;

        public StateLook(){
            setLayout(new FlowLayout());
            setSize(500, 400);

            add(new JScrollPane(list3));

            sel = new JButton("���� ���");
            sel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String test = (String)list3.getSelectedValue();
                    int in = test.indexOf(".");
                    test = test.substring(0, in);
                    sampleList.choiceYo(Integer.parseInt(test), user.getStudentID());
                    list3.setListData(sampleList.printState(user.getStudentID()));
                }
            });
            add(sel);

            del = new JButton("���� ���");
            del.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String test = (String)list3.getSelectedValue();
                    int in = test.indexOf(".");
                    test = test.substring(0, in);
                    sampleList.deleteAplication(Integer.parseInt(test), user.getStudentID());
                    list3.setListData(sampleList.printState(user.getStudentID()));
                    JOptionPane.showMessageDialog(null, "��ҵǾ����ϴ�.", "�˸�", JOptionPane.PLAIN_MESSAGE);
                }
            });
            add(del);
        }
    }
    class RecruitCreate extends JPanel {
        private JTextField[] format = new JTextField[10];
        private JTextArea contents = new JTextArea();
        private JButton confirm;

        public RecruitCreate() {
            setLayout(new FlowLayout());
            setSize(500, 400);

            format[0] = new JTextField("Number of Recruitment (ex. 1)", 44);
            format[1] = new JTextField("Title", 44);
            format[2] = new JTextField("Nation", 44);
            format[3] = new JTextField("University", 44);
            format[4] = new JTextField("Major", 44);
            format[5] = new JTextField("Start Year", 44);
            format[6] = new JTextField("Start Semester", 44);
            format[7] = new JTextField("Period", 44);
            format[8] = new JTextField("Deadline (ex. 20180908)", 44);
            format[9] = new JTextField("Resisteration Deadline (ex. 20181010)", 44);

            contents = new JTextArea("Contents");
            contents.setColumns(44);
            contents.setRows(8);

            confirm = new JButton("�ۼ��Ϸ�");
            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (sampleList.checkList(Integer.parseInt(format[0].getText())) == false) {
                        Recruitment newone = admin.createRecruitment(Integer.parseInt(format[0].getText()), format[1].getText(), contents.getText(), Integer.parseInt(format[8].getText()),
                                Integer.parseInt(format[9].getText()), Integer.parseInt(format[5].getText()), Integer.parseInt(format[6].getText()), Integer.parseInt(format[7].getText()),
                                format[2].getText(), format[3].getText(), format[4].getText());
                        sampleList.addList(newone);
                        list.setListData(sampleList.printList());
                        list2.setListData(sampleList.printList2());
                        JOptionPane.showMessageDialog(null, "�������� �ۼ��� �Ϸ�Ǿ����ϴ�.", "�˸�", JOptionPane.PLAIN_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "���� ��ȣ�� �������� �̹� �����մϴ�.", "�˸�", JOptionPane.PLAIN_MESSAGE);
                }
            });

            for (int i = 0; i < 10; i++) {
                add(format[i]);
            }

            add(contents);
            add(confirm);
        }
    }
    class RecruitDelete extends JPanel {
        private JButton delet;

        public RecruitDelete() {
            setLayout(new FlowLayout());
            setSize(500, 400);

            add(new JScrollPane(list2));

            delet = new JButton("����");
            delet.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = list2.getSelectedIndex();
                    if (index == -1)
                        JOptionPane.showMessageDialog(null, "������ �������� �����ϼ���.", "�˸�", JOptionPane.PLAIN_MESSAGE);
                    else{
                        sampleList.deleteList(index);
                        list.setListData(sampleList.printList());
                        list2.setListData(sampleList.printList2());
                        JOptionPane.showMessageDialog(null, "�������� �����Ǿ����ϴ�.", "�˸�", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });

            add(delet);
        }
    }
	public credit_UI getCreditViewIsapped() {
		return CreditViewIsapped;
	}
}