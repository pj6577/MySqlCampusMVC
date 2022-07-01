package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import connector.DBConnector;
import connector.MySqlConnector;
import controller.MajorController;
import controller.UserController;
import model.MajorDTO;
import model.UserDTO;
import util.ScannerUtil;
import java.sql.SQLException;

public class MajorViewer {
    private Scanner scanner;
    private UserDTO logIn;
    private DBConnector connector;

    public MajorViewer(Scanner scanner, DBConnector connector, UserDTO logIn) {
        this.scanner = scanner;
        this.connector = connector;
        this.logIn = logIn;
    }

    // ���� ���
    public void insertClass() {
        MajorDTO m = new MajorDTO();
        String message = "���� �̸� ���";
        m.setMajorName(ScannerUtil.nextLine(scanner, message));
        m.setProfessor(logIn.getUserName());

        MajorController controller = new MajorController(connector);
        controller.insert(m);

    }

    // �� ���� ��� ����
    public void printClass() {
        MajorController controller = new MajorController(connector);
        ArrayList<MajorDTO> list = controller.selectAll();

        if (list.isEmpty()) {
            System.out.println("���� ���ǰ� �����ϴ�");
        } else {
            for (MajorDTO m : list) {
                if (logIn.getUserName().equals(m.getProfessor())) {
                    System.out.printf("%d. %s\n", m.getId(), m.getMajorName());
                }
                String message = "�󼼺��� �� ������ ��ȣ�� �ڷ� ���Ƿ��� 0�� �Է����ּ���";
                int userChoice = ScannerUtil.nextInt(scanner, message);
                while (userChoice != 0 && controller.selectOne(userChoice) == null) {
                    System.out.println("�� �� �Է��ϼ̽��ϴ�");
                    userChoice = ScannerUtil.nextInt(scanner, message);
                }
                if (userChoice != 0) {
                    printOne(userChoice);
                }

            }
        }
    }

    private void printOne(int userChoice) {
        
    }

}
