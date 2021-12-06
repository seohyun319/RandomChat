
//Socket 클래스
import java.awt.geom.RoundRectangle2D;
import java.net.*;

//입출력 클래스
import java.io.*;

//그래픽 관련 클래스
import java.awt.*; // GUI
import javax.swing.*; // JFrame, JTextField, JTextArea, JScrollPane
import javax.swing.border.Border;

//Event 처리
import java.awt.event.*; // ActionListener

public class ChatClient extends JFrame implements ActionListener, Runnable {

    // ======== GUI =========
    JTextField field; // 전송할 텍스트 입력창
    JTextArea textArea; // 전송받은 텍스트 출력

    JScrollPane scroll; // 스크롤바 생성

    JButton send; // 전송 버튼
    ImageIcon sendIcon; // 전송 아이콘

//    JPanel panelL = new JPanel();
//    JPanel panelR = new JPanel();
    JPanel panelSouth = new JPanel();

    Color col1 = new Color(144, 148, 255);


    // ======== Socket =======
    Socket s; // 서버와의 통신을 위함

    // ======== Stream =======
    BufferedReader br; // 클라이언트에서의 문자열 입력 스트림
    PrintWriter pw; // 문자열 출력 스트림

    // 서버로 전송할 문자열과 서버에서 받아올 문자열 변수
    String str, str1;
 // ======== 생성자 ========
 public ChatClient() {
     // 창, 부착할 컴포넌트 생성 및 연결
     field = new JTextField();
     textArea = new JTextArea();
     sendIcon = new ImageIcon("src/main/java/img/send_btn.png");
     Image newImg = sendIcon.getImage().getScaledInstance(36, 36,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
     sendIcon = new ImageIcon(newImg);  // transform it back
     send = new JButton(sendIcon);



     // 텍스트 출력창에 스크롤 바 연결
     scroll = new JScrollPane(textArea);

     // BorderLayout 배치관리자, JTextArea를 정중앙에 부착
     add(scroll, "Center");

     // 텍스트 필드+버튼을 하단에 부착
     panelSouth.setLayout(new BorderLayout(1,2));

     panelSouth.add(field, BorderLayout.WEST);
     panelSouth.add(send, BorderLayout.EAST);
     field.setPreferredSize(new Dimension(300, 35));
     send.setPreferredSize(new Dimension(35, 35));
     add(panelSouth, BorderLayout.SOUTH);

     // 텍스트 필드에서 이벤트(enter)를 입력받고 해당 객체에서 이벤트 처리
     field.addActionListener(this);

     // 창 크기 지정
//     setBounds(200, 200, 500, 350);
     setSize(350,650);

     // 창이 보이도록, 크기 조절 가능하도록 설정
     setVisible(true);
     setResizable(true);

     // 텍스트 필드에 커서 입력
     field.requestFocus();

     // X버튼 클릭시 정상 종료되도록 설정
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     // 서버와 연결, 연결되지 않을 수도 있기 때문에 예외 처리 필수
     try {
         // 클라이언트 측 소켓 정보 초기화
         // Socket(host, port), host: 접속 서버 IP 주소, port: 서버 포트 번호
         s = new Socket("192.168.11.105", 8080);
         System.out.println("s>>>" + s);

         // ========== Server와 Stream 연결 ===========
         br = new BufferedReader(new InputStreamReader(s.getInputStream()));

         // PrintWriter 스트림의 autoFlush 기능 활성화
         pw = new PrintWriter(s.getOutputStream(), true);

     } catch (Exception e) {
         System.out.println("접속 오류>>>" + e);
     }

     // Thread 객체 생성, Runnable 인터페이스를 구현하기 때문에 this 작성
     Thread ct = new Thread(this);

     // 클라이언트 스레드 실행 → run() 호출
     ct.start();
 }


 // Runnable 인터페이스 run() 메소드 오버라이딩
 public void run() {
     // 더 이상 입력을 받을 수 없을 때까지 JTextArea(채팅창)에 출력
     try {
         while ((str1 = br.readLine()) != null) {
             textArea.append(str1 + "\n"); // 상대방이 보낸 문자를 채팅창에 세로로 출력
         }
     } catch (Exception e) {
         e.printStackTrace();
         ;
     }
 }

 // ActionListener 메소드 오버라이딩, 입력란에서 enter입력시 실행할 코드
 public void actionPerformed(ActionEvent e) {
     // 내가 쓴 메세지를 str 변수에 저장
     str = field.getText();

     // 변수에 저장 후 텍스트필드 초기화
     field.setText("");

     // 내가 쓴 메세지 출력 -> 상대방은 br.readLine()으로 읽어들임
     pw.println(str);
     pw.flush();
 }

 public static void main(String[] args) {

     // 클라이언트 객체 생성, 생성자 호출
     new ChatClient();

 }

}