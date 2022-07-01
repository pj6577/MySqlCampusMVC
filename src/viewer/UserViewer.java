package viewer;

import java.util.Scanner;

import connector.DBConnector;
import connector.MySqlConnector;
import controller.MajorController;
import controller.UserController;
import model.UserDTO;
import util.ScannerUtil;
import java.sql.SQLException;
import viewer.MajorViewer;

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
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                logIn();
                if (logIn.getUserClass().equals("����")) {
                    // ���� �޴�
                    System.out.println("�����Դϴ�");
                    // staffShowMenu();
                } else if (logIn.getUserClass().equals("����")) {
                    // �����޴�
                    professorMenu();
                    System.out.println("���� �Դϴ�");
                } else if (logIn.getUserClass().equals("�л�")) {
                    // �л� �޴�
                    System.out.println("�л� �Դϴ�");
                }
            } else if (userChoice == 2) {
                register();
            } else if (userChoice == 3) {
                System.out.println("������ּż� �����մϴ� �����մϴ�");
                break;
            }
        }
    }

    // �α���
    private void logIn() {
        String message;
        message = "�̸��� �Է����ּ���";
        String userName = ScannerUtil.nextLine(scanner, message);
        message = "��й�ȣ�� �Է����ּ���";
        String passWord = ScannerUtil.nextLine(scanner, message);

        UserController controller = new UserController(connector);
        logIn = controller.logIn(userName, passWord);

        while (logIn == null) {
            System.out.println("�߸��Է��ϼ˽��ϴ�");
            userName = ScannerUtil.nextLine(scanner, "���̵� �Է����ּ��� �ڷ� ���÷��� x�� �Է����ּ���");
            if (userName.equalsIgnoreCase("X")) {
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
        int userChoice = ScannerUtil.nextInt(scanner, message);
        while (true) {
            if (userChoice == 1) {
                u.setUserClass("����");
                break;
            } else if (userChoice == 2) {
                u.setUserClass("����");
                break;
            } else if (userChoice == 3) {
                u.setUserClass("�л�");
            } else {
                System.out.println("�ùٸ� �Է��� �ƴմϴ�");
                userChoice = ScannerUtil.nextInt(scanner, message);
            }
        }
        UserController controller = new UserController(connector);

        while (!controller.register(u)) {
            System.out.println("�߸��Է��ϼ̽��ϴ�");
            String yesNo = ScannerUtil.nextLine(scanner, "���ο� ���̵� �Է��Ͻðڽ��ϱ�? Y/N");
            if (yesNo.equalsIgnoreCase("N")) {
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

    public void professorMenu() {
        System.out.println("������ �޴��Դϴ�");
        System.out.println("1�� ���� �޴� 2�� �л� �޴� 3�� �α׾ƿ�");
        int userChoice = scanner.nextInt();   
        if(userChoice ==1 ) {
            System.out.println("1�� ���� ����ϱ� 2�� ���� �������� ���� ����");
             int psChoice = scanner.nextInt();
             if(psChoice ==1 ) {
                 MajorViewer majorViewer = new MajorViewer(scanner, connector, logIn);
                majorViewer.insertClass();
                
             }else if(psChoice == 2 ) {
                 MajorViewer majorViewer2 = new MajorViewer(scanner, connector, logIn);
                majorViewer2.printClass();
             }
        }else if(userChoice == 2 ) {
            System.out.println("1�� �л� ���� ��� 2�� �л� ���� ����");
            int ps2Choice = scanner.nextInt();
            if(ps2Choice == 1 ) {
                
            }else if(ps2Choice ==2 ) {
                
            }
        }
    }

   

}
