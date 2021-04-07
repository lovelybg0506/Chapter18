package controller;

import java.util.ArrayList;

import model.dao.CoffeeDao;
import model.exception.CoffeeException;
import model.vo.Coffee;
import model.vo.Order;

public class CoffeeManager {
	private ArrayList<Order> orderList; // 주문 리스트
	private CoffeeDao coDao; // 주문 정보를 저장	// Dao = data관련 단어.
	private Order od; // 현재 주문 수량

	public CoffeeManager() {
		coDao = new CoffeeDao();
		orderList = coDao.openList();
		od = new Order();	// Order Class를 객체로 만들어서  od 변수에연결.
	}

	public Order insertCoffee(Coffee coffee) { // 커피 주문 정보 등록
		switch (coffee.getCoffeeName()) {
		case "아메리카노":
			if (!(od.getHt().containsKey("아메리카노"))) { // !를 붙였기 때문에 반대
				od.getHt().put(coffee.getCoffeeName(), coffee);
			} else {
				od.getHt().get("아메리카노").plusCups(coffee.getCups());
			}
			break;

		case "카페라떼":
			if (!(od.getHt().containsKey("카페라떼"))) { // !를 붙였기 때문에 반대
				od.getHt().put(coffee.getCoffeeName(), coffee);
			} else {
				od.getHt().get("카페라떼").plusCups(coffee.getCups());
			}
			break;

		case "카푸치노":
			if (!(od.getHt().containsKey("카푸치노"))) { // !를 붙였기 때문에 반대
				od.getHt().put(coffee.getCoffeeName(), coffee);
			} else {
				od.getHt().get("카푸치노").plusCups(coffee.getCups());
			}
			break;
		}
		return od;
	}

	public void insertOrder() { // 실제 주문들어온것을 orderList에 저장.
		orderList.add(od);
		od.setOrderNo2(orderList.size());
	}

	public Order verifyCoffee(int orderNo) throws CoffeeException {

		if (orderNo > orderList.size()) {
			throw new CoffeeException("잘못된 주문 정보입니다.");
		}
		return orderList.get(orderNo - 1); // orderNo => orderNo-1로 수정.
	}

	public void updateCoffee(int orderNo, Coffee coffee) {
		coffee.setOrderNo(orderNo);
		Order localOd = orderList.get(orderNo - 1);

		switch (coffee.getCoffeeName()) {
		case "아메리카노":
			if (localOd.getHt().containsKey("아메리카노")) {
				localOd.getHt().replace("아메리카노", coffee);
			} else {
				localOd.getHt().put("아메리카노", coffee);
			}
			break;

		case "카페라떼":
			if (localOd.getHt().containsKey("카페라떼")) {
				localOd.getHt().replace("카페라떼", coffee);
			} else {
				localOd.getHt().put("카페라떼", coffee);
			}
			break;

		case "카푸치노":
			if (localOd.getHt().containsKey("카푸치노")) {
				localOd.getHt().replace("카푸치노", coffee);
			} else {
				localOd.getHt().put("카푸치노", coffee);
			}
			break;
		}
		orderList.set(orderNo-1, localOd); // 커피종류 수정
		localOd.editprice(); // 가격수정
	}

	public void deleteCoffee(int idx) { // 실제 넘어오는 숫자는 인덱스값
		orderList.remove(idx);
		for (int i = idx; i < orderList.size(); i++) {
			orderList.get(i).setOrderNo2(i + 1); // get(i) 해당위치의 setOrderNo 주문번호 이동
		}
	}
	
	public boolean deleteSubCoffee(int idx, int sel) {
		Order localOd=orderList.get(idx);
		
		switch(sel) {
		case 1:
			orderList.get(idx).getHt().remove("아메리카노");
			break;
		case 2:
			orderList.get(idx).getHt().remove("카페라떼");
			break;
		case 3:
			orderList.get(idx).getHt().remove("카푸치노");
			break;
		}
		
		orderList.set(idx,localOd);
		localOd.editprice();
		
		if(localOd.getHt().isEmpty()) {	//isEmpty = getHt()가 비어있는가?
			return true;
		}
		return false;
	}

	public boolean check(int idx, int sel) {
		switch(sel) {
		case 1:
			if(orderList.get(idx).getHt().containsKey("아메리카노"))
				return true;
			else
				return false;
		case 2:
			if(orderList.get(idx).getHt().containsKey("카페라떼"))
				return true;
			else
				return false;
		case 3:
			if(orderList.get(idx).getHt().containsKey("카푸치노"))
				return true;
			else
				return false;
		default:
			return false;
		}
	}
	
	public ArrayList<Order> getOrderList() {

		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}

	public void close() {
		coDao.saveList(orderList);
	}

	public Order getOrder() {
		return od;
	}
	
	public void clearOrder() {
		od = new Order();	// 새로운 객체로 만들어서 다시연결
	}
	
}










