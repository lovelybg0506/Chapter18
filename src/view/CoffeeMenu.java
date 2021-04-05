package view;

import java.util.Scanner;

import controller.CoffeeManager;
import model.exception.CoffeeException;
import model.vo.Coffee;

public class CoffeeMenu {
	public static CoffeeManager cm = new CoffeeManager();
	
	public static void showMenu() {
		Scanner sc = new Scanner(System.in);

		do {
			initMenu(4.0);
			System.out.print("메뉴 확인 : ");
			int sel = sc.nextInt();

			switch (sel) {
			case 1:
				insertCoffee();
				break; // case마다 break; 추가.
			case 2:
				selectCoffeeOne();
				break;
			case 3:
				updateCoffee();
				break;
			case 4:
				deleteCoffee();
				break;
			case 5:
				deleteSubCoffee();
				break;
			case 6:
				selectCoffeeAll();
				break;
			case 7:
				System.out.print("정말 종료하시겠습니까?(Y/N): ");
				if (sc.next().toUpperCase().charAt(0) == 'Y') {
					cm.close();
					return;
				} else {
					System.out.println("메뉴를 다시 불러옵니다.");
				}
				break;
			default:
				System.out.println("잘못 누르셨습니다.");
			}
		} while (true);
	}

	public static void initMenu(double version) {
		System.out.println("-----------------------------");
		System.out.println("==길동이의 커피 프린세스 버전" + version + "==");
		System.out.println("-----------------------------");
		System.out.println("1.아메리카노");
		System.out.println("2.카페라떼");
		System.out.println("3.카푸치노");
		System.out.println("-----------------------------");
		System.out.println("1.커피주문");
		System.out.println("2.주문번호 확인");
		System.out.println("3.주문내용 변경");
		System.out.println("4.주문 취소");
		System.out.println("5.주문 전체 내역 확인");
		System.out.println("6.프로그램 종료");

	}

	public static void insertCoffee() {
//		어떤커피선택?
		Scanner sc = new Scanner(System.in);
		int sel;
		
		do {
		do {
			System.out.println("------메뉴------");
			System.out.println("주문하실 커피를 선택하세요.");
			System.out.println("1.아메리카노");
			System.out.println("2.카페라떼");
			System.out.println("3.카푸치노");
			System.out.println("------메뉴------");

			System.out.print("메뉴 선택 : ");
			sel = sc.nextInt();
			if (sel > 0 && sel < 4)
				break;
			System.out.println("없는 메뉴입니다. 다시 선택하세요.");
		} while (sel < 0 || sel > 4);
		
			System.out.println("주문 잔 수 : ");
			int cups = sc.nextInt();
			cm.insertCoffee(new Coffee(sel, cups));
			
			System.out.println("더 주문하시겠습니까?(Y/N)");
			if (sc.next().toUpperCase().charAt(0) == 'N')
				break; //참일때.
		} while(true);	//거짓일때
			
		

			System.out.println("주문 정보 확인 : ");
		System.out.println(cm.insertCoffee(new Coffee(sel, cups)));

	}

	public static void selectCoffeeOne() {
		Scanner sc = new Scanner(System.in);
		System.out.println("주문 번호 확인 : ");
		int orderNo = sc.nextInt();
		try {
			System.out.println("현재 주문 내역 : ");
			System.out.println(cm.verifyCoffee(orderNo));
		} catch (CoffeeException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void updateCoffee() {
		Scanner sc = new Scanner(System.in);
		System.out.println("주문 번호 확인 : ");
		int orderNo = sc.nextInt();

		try {
			System.out.println("주문 내역 확인");
			System.out.println(cm.verifyCoffee(orderNo));// 기존 주문 내역을 보여준다.
			System.out.println("------메뉴------");
			System.out.println("교체하실 커피를 선택하세요.");
			System.out.println("1.아메리카노");
			System.out.println("2.카페라떼");
			System.out.println("3.카푸치노");
			System.out.println("------메뉴------");

			System.out.print("메뉴 선택 : ");
			int sel = sc.nextInt();

			System.out.println("몇 잔 주문하시겠습니까? : ");
			int cups = sc.nextInt();
			cm.updateCoffee(orderNo, new Coffee(sel, cups)); // 이부분을 생각하지 못함.// 업데이트 해준다.
		} catch (CoffeeException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void deleteCoffee() {
		Scanner sc = new Scanner(System.in);
		System.out.println("주문 번호 확인 : ");
		int orderNo = sc.nextInt();

		try {
			System.out.println("주문 내역 확인");
			System.out.println(cm.verifyCoffee(orderNo));
			System.out.println("정말 취소 하시겠습니까?(Y/N)");
			if (sc.next().toUpperCase().charAt(0) == 'Y') { // toUpperCase() 하는이유 : 소문자로 입력받아도 대문자로 자동교체하려고
				cm.deleteCoffee(orderNo - 1);// 실질적으로 취소하는 코드 // orderNo => orderNo-1로 수정.
				System.out.println("주문이 정상적으로 취소 되었습니다.");
			} else {
				System.out.println("메인으로 돌아갑니다.");
			}
		} catch (CoffeeException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void selectCoffeeAll() {

		for (Coffee co : cm.getOrderList())
			System.out.println(co);
	}

}
