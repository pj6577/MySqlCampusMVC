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
        String message = "1.로그인 2. 회원가입 3.종료";
        while(true) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if(userChoice ==1 ) {
                logIn();
                if(logIn != null && logIn.getUserClass() == 1) {
                    //직원 메뉴
                  //staffShowMenu();
                } else if(logIn != null && logIn.getUserClass()==2) {
                    //교수메뉴
                }else if(logIn != null && logIn.getUserClass()==3 ) {
                    //직원 메뉴
                }
            }else if(userChoice == 2 ) {
                register();
            }else if(userChoice == 3 ) {
                System.out.println("사용해주셔서 감사합니다 종료합니다");
                break;
            }
        }
    }
    //로그인
    private void logIn() {
        String message;
        message = "이름을 입력해주세요";
        String userName = ScannerUtil.nextLine(scanner, message);
        message = "비밀번호를 입력해주세요";
        String passWord = ScannerUtil.nextLine(scanner, message);

        UserController controller = new UserController(connector);
        logIn = controller.logIn(userName, passWord);
        
        while(logIn == null) {
            System.out.println("잘못입력하셧습니다");
            userName = ScannerUtil.nextLine(scanner, "아이디를 입력해주세요 뒤로 가시려면 x를 입력해주세요");
            if(userName.equalsIgnoreCase("X")) {
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
        u.setUserClass(ScannerUtil.nextInt(scanner, message));
        
        UserController controller = new UserController(connector);
        
        while(!controller.register(u)) {
            System.out.println("잘못입력하셨습니다");
            String yesNo = ScannerUtil.nextLine(scanner, "새로운 아이디를 입력하시겠습니까? Y/N");
            if(yesNo.equalsIgnoreCase("N")) {
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
    
}
