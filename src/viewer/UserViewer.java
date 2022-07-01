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
        String message = "1.로그인 2. 회원가입 3.종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                logIn();
                if (logIn.getUserClass().equals("직원")) {
                    // 직원 메뉴
                    System.out.println("직원입니다");
                    // staffShowMenu();
                } else if (logIn.getUserClass().equals("교수")) {
                    // 교수메뉴
                    professorMenu();
                    System.out.println("교수 입니다");
                } else if (logIn.getUserClass().equals("학생")) {
                    // 학생 메뉴
                    System.out.println("학생 입니다");
                }
            } else if (userChoice == 2) {
                register();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다 종료합니다");
                break;
            }
        }
    }

    // 로그인
    private void logIn() {
        String message;
        message = "이름을 입력해주세요";
        String userName = ScannerUtil.nextLine(scanner, message);
        message = "비밀번호를 입력해주세요";
        String passWord = ScannerUtil.nextLine(scanner, message);

        UserController controller = new UserController(connector);
        logIn = controller.logIn(userName, passWord);

        while (logIn == null) {
            System.out.println("잘못입력하셧습니다");
            userName = ScannerUtil.nextLine(scanner, "아이디를 입력해주세요 뒤로 가시려면 x를 입력해주세요");
            if (userName.equalsIgnoreCase("X")) {
                break;
            }
            userName = ScannerUtil.nextLine(scanner, "비밀번호를 입력해주세요");
            logIn = controller.logIn(userName, passWord);
        }
    }

    private void register() {
        UserDTO u = new UserDTO();
        String message = " 사용하실 아이디를 입력해주세요";
        u.setUserName(ScannerUtil.nextLine(scanner, message));
        message = " 사용하실 비밀번호 를 입력해주세요";
        u.setPassWord(ScannerUtil.nextLine(scanner, message));
        message = " 직원은1 교수는 2 학생은 3 입력";
        int userChoice = ScannerUtil.nextInt(scanner, message);
        while (true) {
            if (userChoice == 1) {
                u.setUserClass("직원");
                break;
            } else if (userChoice == 2) {
                u.setUserClass("교수");
                break;
            } else if (userChoice == 3) {
                u.setUserClass("학생");
            } else {
                System.out.println("올바른 입력이 아닙니다");
                userChoice = ScannerUtil.nextInt(scanner, message);
            }
        }
        UserController controller = new UserController(connector);

        while (!controller.register(u)) {
            System.out.println("잘못입력하셨습니다");
            String yesNo = ScannerUtil.nextLine(scanner, "새로운 아이디를 입력하시겠습니까? Y/N");
            if (yesNo.equalsIgnoreCase("N")) {
                break;
            }
            u.setUserName(ScannerUtil.nextLine(scanner, "사용하실 아이디를 입력해주새요"));
        }
    }

//    public void staffShowMenu() {
//        String message = "1.강의 정보  2. 학생 정보 3. 로그아웃";
//        while(logIn != null) {
//            int userChoice = ScannerUtil.nextInt(scanner, message);
//            if(userChoice == 1 ) {
//                BoardViewer boardViewer = new BoardViewer(Scanner, connector, logIn);  
//            }else if(userChoice ==2 ) {
//                
//            }else if(userChoice ==3 ) {
//             System.out.println("로그아웃 하셨습니다");
//                 logIn = null;
//            }
//            
//        }
//    }

    public void professorMenu() {
        System.out.println("교수님 메뉴입니다");
        System.out.println("1번 강의 메뉴 2번 학생 메뉴 3번 로그아웃");
        int userChoice = scanner.nextInt();   
        if(userChoice ==1 ) {
            System.out.println("1번 강의 등록하기 2번 현재 진행중인 강의 보기");
             int psChoice = scanner.nextInt();
             if(psChoice ==1 ) {
                 MajorViewer majorViewer = new MajorViewer(scanner, connector, logIn);
                majorViewer.insertClass();
                
             }else if(psChoice == 2 ) {
                 MajorViewer majorViewer2 = new MajorViewer(scanner, connector, logIn);
                majorViewer2.printClass();
             }
        }else if(userChoice == 2 ) {
            System.out.println("1번 학생 성적 등록 2번 학생 성적 보기");
            int ps2Choice = scanner.nextInt();
            if(ps2Choice == 1 ) {
                
            }else if(ps2Choice ==2 ) {
                
            }
        }
    }

   

}
