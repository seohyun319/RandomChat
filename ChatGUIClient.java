
//Socket Ŭ����
import java.net.*;

//����� Ŭ����
import java.io.*;

//�׷��� ���� Ŭ����
import java.awt.*; // GUI
import javax.swing.*; // JFrame, JTextField, JTextArea, JScrollPane

//Event ó��
import java.awt.event.*; // ActionListener

public class ChatGUIClient extends JFrame implements ActionListener, Runnable {

 // ======== GUI =========
 JTextField field; // ������ �ؽ�Ʈ �Է�â
 JTextArea textArea; // ���۹��� �ؽ�Ʈ ���

 JScrollPane scroll; // ��ũ�ѹ� ����

 JButton send; // ���� ��ư

 // ======== Socket =======
 Socket s; // �������� ����� ����

 // ======== Stream =======
 BufferedReader br; // Ŭ���̾�Ʈ������ ���ڿ� �Է� ��Ʈ��
 PrintWriter pw; // ���ڿ� ��� ��Ʈ��

 // ������ ������ ���ڿ��� �������� �޾ƿ� ���ڿ� ����
 String str, str1;

 // ======== ������ ========
 public ChatGUIClient() {
     // â, ������ ������Ʈ ���� �� ����
     field = new JTextField();
     textArea = new JTextArea();

     // �ؽ�Ʈ ���â�� ��ũ�� �� ����
     scroll = new JScrollPane(textArea);

     // BorderLayout ��ġ������, JTextArea�� ���߾ӿ� ����
     add(scroll, "Center");

     // �ؽ�Ʈ �ʵ带 �ϴܿ� ����
     add(field, BorderLayout.SOUTH);

     // �ؽ�Ʈ �ʵ忡�� �̺�Ʈ(enter)�� �Է¹ް� �ش� ��ü���� �̺�Ʈ ó��
     field.addActionListener(this);

     // â ũ�� ����
     setBounds(200, 200, 500, 350);

     // â�� ���̵��� ����
     setVisible(true);

     // �ؽ�Ʈ �ʵ忡 Ŀ�� �Է�
     field.requestFocus();

     // X��ư Ŭ���� ���� ����ǵ��� ����
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     // ������ ����, ������� ���� ���� �ֱ� ������ ���� ó�� �ʼ�
     try {
         // Ŭ���̾�Ʈ �� ���� ���� �ʱ�ȭ
         // Socket(host, port), host: ���� ���� IP �ּ�, port: ���� ��Ʈ ��ȣ
         s = new Socket("192.168.26.103", 8080);
         System.out.println("s>>>" + s);

         // ========== Server�� Stream ���� ===========
         br = new BufferedReader(new InputStreamReader(s.getInputStream()));

         // PrintWriter ��Ʈ���� autoFlush ��� Ȱ��ȭ
         pw = new PrintWriter(s.getOutputStream(), true);

     } catch (Exception e) {
         System.out.println("���� ����>>>" + e);
     }

     // Thread ��ü ����, Runnable �������̽��� �����ϱ� ������ this �ۼ�
     Thread ct = new Thread(this);

     // Ŭ���̾�Ʈ ������ ���� �� run() ȣ��
     ct.start();
 }

 // Runnable �������̽� run() �޼ҵ� �������̵�
 public void run() {
     // �� �̻� �Է��� ���� �� ���� ������ JTextArea(ä��â)�� ���
     try {
         while ((str1 = br.readLine()) != null) {
             textArea.append(str1 + "\n"); // ������ ���� ���ڸ� ä��â�� ���η� ���
         }
     } catch (Exception e) {
         e.printStackTrace();
         ;
     }
 }

 // ActionListener �޼ҵ� �������̵�, �Է¶����� enter�Է½� ������ �ڵ�
 public void actionPerformed(ActionEvent e) {
     // ���� �� �޼����� str ������ ����
     str = field.getText();

     // ������ ���� �� �ؽ�Ʈ�ʵ� �ʱ�ȭ
     field.setText("");

     // ���� �� �޼��� ��� -> ������ br.readLine()���� �о����
     pw.println(str);
     pw.flush();
 }

 public static void main(String[] args) {

     // Ŭ���̾�Ʈ ��ü ����, ������ ȣ��
     new ChatGUIClient();

 }

}