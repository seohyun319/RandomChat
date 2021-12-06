
//Socket Ŭ����
import java.net.*;

//����� Ŭ����
import java.io.*;

//�׷��� ���� Ŭ����
import java.awt.*; // GUI
import javax.swing.*; // JFrame, JTextField, JTextArea, JScrollPane

//Event ó��
import java.awt.event.*; // ActionListener

public class ChatClient extends JFrame implements ActionListener, Runnable {

    // ======== GUI =========
    JTextField field; // ������ �ؽ�Ʈ �Է�â
    JTextArea textArea; // ���۹��� �ؽ�Ʈ ���

    JScrollPane scroll; // ��ũ�ѹ� ����

    JButton send; // ���� ��ư
    ImageIcon sendIcon; // ���� ������

    JPanel topE; //��ܺ�
    JLabel name; //��������

//    JPanel panelL = new JPanel();
//    JPanel panelR = new JPanel();
    JPanel panelSouth = new JPanel();

    Color col1 = new Color(144, 148, 255);


    // ======== Socket =======
    Socket s; // �������� ����� ����

    // ======== Stream =======
    BufferedReader br; // Ŭ���̾�Ʈ������ ���ڿ� �Է� ��Ʈ��
    PrintWriter printW; // ���ڿ� ��� ��Ʈ��

    // ������ ������ ���ڿ��� �������� �޾ƿ� ���ڿ� ����
    String str, str1;
 // ======== ������ ========
 public ChatClient() {
     // â, ������ ������Ʈ ���� �� ����
     field = new JTextField();
     textArea = new JTextArea();
     sendIcon = new ImageIcon("src/main/java/img/send_btn.png");
     Image newImg = sendIcon.getImage().getScaledInstance(36, 36,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
     sendIcon = new ImageIcon(newImg);  // transform it back
     send = new JButton(sendIcon);
     topE = new JPanel();
     topE.setBackground(col1);
     name = new JLabel("ģ��");
     name.setFont(new Font("HY������M", Font.BOLD, 17));
     name.setForeground(Color.white);
     name.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10)); //�е�
     topE.add(name);

     // �ؽ�Ʈ ���â�� ��ũ�� �� ����
     scroll = new JScrollPane(textArea);

     //topE�� ��ܿ� ����
     add(topE, "North");
     topE.setPreferredSize(new Dimension(300, 44));

     // BorderLayout ��ġ������, JTextArea�� ���߾ӿ� ����
     add(scroll, "Center");
     setBackground(Color.white);
     scroll.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));

     // �ؽ�Ʈ �ʵ�+��ư�� �ϴܿ� ����
     panelSouth.setLayout(new BorderLayout(1,2));

     panelSouth.add(field, BorderLayout.WEST);
     panelSouth.add(send, BorderLayout.EAST);
     field.setPreferredSize(new Dimension(300, 35));
     send.setPreferredSize(new Dimension(35, 35));

     field.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
     add(panelSouth, BorderLayout.SOUTH);

     // �ؽ�Ʈ �ʵ忡�� �̺�Ʈ(enter)�� �Է¹ް� �ش� ��ü���� �̺�Ʈ ó��
     field.addActionListener(this);

     // â ũ�� ����
//     setBounds(200, 200, 500, 350);
     setSize(350,650);

     // â�� ���̵���, ũ�� ���� �����ϵ��� ����
     setVisible(true);
     setResizable(true);

     // �ؽ�Ʈ �ʵ忡 Ŀ�� �Է�
     field.requestFocus();

     // X��ư Ŭ���� ���� ����ǵ��� ����
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     // ������ ����, ������� ���� ���� �ֱ� ������ ���� ó�� �ʼ�
     try {
         // Ŭ���̾�Ʈ �� ���� ���� �ʱ�ȭ
         // Socket(host, port), host: ���� ���� IP �ּ�, port: ���� ��Ʈ ��ȣ
         s = new Socket("192.168.11.105", 8080);
         System.out.println("s>>>" + s);

         // ========== Server�� Stream ���� ===========
         br = new BufferedReader(new InputStreamReader(s.getInputStream()));

         // PrintWriter ��Ʈ���� autoFlush ��� Ȱ��ȭ
         printW = new PrintWriter(s.getOutputStream(), true);

     } catch (Exception e) {
         System.out.println("���� ����>>>" + e);
     }

     // Thread ��ü ����, Runnable �������̽��� �����ϱ� ������ this �ۼ�
     Thread ct = new Thread(this);

     // Ŭ���̾�Ʈ ������ ���� �� run() ȣ��
     ct.start();
 }

//class RoundedBorder implements Border
//{
//    private int r;
//    private Color bgColor;
//    RoundedBorder(int r) {
//        this.r = r;
//    }
//    RoundedBorder(int r, Color bgColor) {
//        this.r = r;
//        this.bgColor = bgColor;
//    }
//    public Insets getBorderInsets(Component c) {
//        return new Insets(this.r+1, this.r+1, this.r+2, this.r);
//    }
//    public boolean isBorderOpaque() {
//        return true;
//    }
//    public void paintBorder(Component c, Graphics g, int x, int y,
//                            int width, int height) {
////        if (bgColor != null) {
////            g.setColor(bgColor);
////        } else {
////            g.setColor(getBackground());
////        }
//        g.setColor(col1);
//        g.setColor(getForeground());
//        g.fillRoundRect(x, y, width-1, height-1, r, r); //paint background
//        g.drawRoundRect(x, y, width-1, height-1, r, r);  //paint border
//    }
//}



//    class MyPanel extends JPanel {
//        public MyPanel() {
////�ʿ��� �ʱ�ȭ ��� ����
//            setBorder(BorderFactory.createLineBorder(Color.black));
//        }
//        public void paintComponent(Graphics g) {
//// ���⿡ �׸��� �׸��� �ڵ带 ������
//            g.setColor(col1);
//            g.drawRoundRect(50, 50, 35, 35, 15, 15);
//            g.fillRoundRect(50, 50, 35, 35, 15, 15);
//        }
//    }





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
     printW.println(str);
     printW.flush();
 }

 public static void main(String[] args) {

     // Ŭ���̾�Ʈ ��ü ����, ������ ȣ��
     new ChatClient();

 }

}