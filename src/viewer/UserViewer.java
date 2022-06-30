package viewer;

import java.util.Scanner;

import connector.DBConnector;
import connector.MySqlConnector;
import controller.UserController;
import model.UserDTO;
import util.ScannerUtil;

public class UserViewer {
    private Scanner scanner;
    private UserDTO logIn;
    private DBConnector connector;
    
    
    public UserViewer(Scanner scanner, DBConnector connector) {
        this.scanner = scanner;
        this.connector = connector;
    }
    
    public void showIndex() {
        String message = "1.�α��� 2. ȸ������ 3.����";
        while(true) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if(userChoice ==1 ) {
                logIn();
                if(logIn != null && logIn.getUserClass() == 1) {
                    //���� �޴�
                  //staffShowMenu();
                } else if(logIn != null && logIn.getUserClass()==2) {
                    //�����޴�
                }else if(logIn != null && logIn.getUserClass()==3 ) {
                    //���� �޴�
                }
            }else if(userChoice == 2 ) {
                register();
            }else if(userChoice == 3 ) {
                System.out.println("������ּż� �����մϴ� �����մϴ�");
                break;
            }
        }
    }
    //�α���
    private void logIn() {
        String message;
        message = "�̸��� �Է����ּ���";
        String userName = ScannerUtil.nextLine(scanner, message);
        message = "��й�ȣ�� �Է����ּ���";
        String passWord = ScannerUtil.nextLine(scanner, message);

        UserController controller = new UserController(connector);
        logIn = controller.logIn(userName, passWord);
        
        while(logIn == null) {
            System.out.println("�߸��Է��ϼ˽��ϴ�");
            userName = ScannerUtil.nextLine(scanner, "���̵� �Է����ּ��� �ڷ� ���÷��� x�� �Է����ּ���");
            if(userName.equalsIgnoreCase("X")) {
                break;
            }
            userName = ScannerUtil.nextLine(scanner, "��й�ȣ�� �Է����ּ���");
            logIn = controller.logIn(userName, passWord);
        }
    }
    
    private void register() {
        UserDTO u = new UserDTO();
        String message = " ����Ͻ� ���̵� �Է����ּ���";
        u.setUserName(ScannerUtil.nextLine(scanner, message));
        message = " ����Ͻ� ��й�ȣ �� �Է����ּ���";
        u.setPassWord(ScannerUtil.nextLine(scanner, message));
        message = " ������1 ������ 2 �л��� 3 �Է�";
        u.setUserClass(ScannerUtil.nextInt(scanner, message));
        
        UserController controller = new UserController(connector);
        
        while(!controller.register(u)) {
            System.out.println("�߸��Է��ϼ̽��ϴ�");
            String yesNo = ScannerUtil.nextLine(scanner, "���ο� ���̵� �Է��Ͻðڽ��ϱ�? Y/N");
            if(yesNo.equalsIgnoreCase("N")) {
                break;
            }
            u.setUserName(ScannerUtil.nextLine(scanner, "����Ͻ� ���̵� �Է����ֻ���"));
            
        } 
    }
//    public void staffShowMenu() {
//        String message = "1.���� ����  2. �л� ���� 3. �α׾ƿ�";
//        while(logIn != null) {
//            int userChoice = ScannerUtil.nextInt(scanner, message);
//            if(userChoice == 1 ) {
//                BoardViewer boardViewer = new BoardViewer(Scanner, connector, logIn);  
//            }else if(userChoice ==2 ) {
//                
//            }else if(userChoice ==3 ) {
//             System.out.println("�α׾ƿ� �ϼ̽��ϴ�");
//                 logIn = null;
//            }
//            
//        }
//    }
    
}
