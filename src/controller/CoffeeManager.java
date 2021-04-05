package controller;

import java.util.ArrayList;

import model.dao.CoffeeDao;
import model.exception.CoffeeException;
import model.vo.Coffee;
import model.vo.Order;

public class CoffeeManager {
	private ArrayList<Order> orderList; // 주문 리스트
	private CoffeeDao coDao; // 주문 정보를 저장
	private Order od; // 현재 주문 수량

	public CoffeeManager() {
		coDao = new CoffeeDao();
		orderList = coDao.openList();
		od = new Order();
	}

	public Order insertCoffee(Coffee coffee) { // 커피 주문 정보 등록
		switch (coffee.getCoffeeName()) {
		case "아메리카노":
			if (!(od.getHt().containsKey("아메리카노"))) {		// !를 붙였기 때문에 반대
				od.getHt().put(coffee.getCoffeeName(), coffee);
			} else {
				od.getHt().get("아메리카노").plusCups(coffee.getCups());
			}
			break;

		case "카페라떼":
			break;
		case "카푸치노":
			break;
		}
	}

	public Coffee verifyCoffee(int orderNo) throws CoffeeException {

		if (orderNo > orderList.size()) {
			throw new CoffeeException("잘못된 주문 정보입니다.");
		}
		return orderList.get(orderNo - 1); // orderNo => orderNo-1로 수정.
	}

	public void updateCoffee(int orderNo, Coffee coffee) {
		coffee.setOrderNo(orderNo);
		orderList.set(orderNo - 1, coffee); // orderNo => orderNo-1로 수정.
	}

	public void deleteCoffee(int idx) { // 실제 넘어오는 숫자는 인덱스값
		orderList.remove(idx);

		if (idx != orderList.size()) {
			for (int i = idx; i < orderList.size(); i++) {
				orderList.get(i).setOrderNo(i + 1); // 이부분이 좀 어려움
			}
		}
	}

	public ArrayList<Coffee> getOrderList() {

		return orderList;
	}

	public void setOrderList(ArrayList<Coffee> orderList) {
		this.orderList = orderList;
	}

	public void close() {
		coDao.saveList(orderList);
	}

}
