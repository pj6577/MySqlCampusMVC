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

    // 강의 등록
    public void insertClass() {
        MajorDTO m = new MajorDTO();
        String message = "강의 이름 등록";
        m.setMajorName(ScannerUtil.nextLine(scanner, message));
        m.setProfessor(logIn.getUserName());

        MajorController controller = new MajorController(connector);
        controller.insert(m);

    }

    // 내 강의 목록 보기
    public void printClass() {
        MajorController controller = new MajorController(connector);
        ArrayList<MajorDTO> list = controller.selectAll();

        if (list.isEmpty()) {
            System.out.println("아직 강의가 없습니다");
        } else {
            for (MajorDTO m : list) {
                if (logIn.getUserName().equals(m.getProfessor())) {
                    System.out.printf("%d. %s\n", m.getId(), m.getMajorName());
                }
                String message = "상세보기 할 강의의 번호나 뒤로 가실려면 0을 입력해주세요";
                int userChoice = ScannerUtil.nextInt(scanner, message);
                while (userChoice != 0 && controller.selectOne(userChoice) == null) {
                    System.out.println("잘 못 입력하셨습니다");
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
