package model.vo;

import java.util.Hashtable;

public class Order {
	private Hashtable<String,Coffee> ht;
	private int orderNo;
	private int price;
	
	public Order() {
		ht = new Hashtable<String,Coffee>();
		
	}
	
	public void add(Coffee coffee) {
		ht.put(coffee.getCoffeeName(), coffee);	// key, value
	}
	
	public Hashtable<String,Coffee> getHt(){
		
		return ht;
	}
	
		public int getOrderNo() {
			
		return orderNo;
	}
		
	public void setOrderNo2(int orderNo) {
		this.orderNo=orderNo;
		for(String key:ht.keySet()) // ht.keySet()에서 가져온 key값들을 String key에 저장.
			ht.get(key).setOrderNo(orderNo);
		
	}
	
	public int getPrice() {
		
		return price;
	}
	
	public void setPrice() {
		for(String key: ht.keySet())
			this.price+=ht.get(key).getPrice();
	}
	
	public void editprice() {
		this.price=0;
		for(String key : ht.keySet())
			this.price+=ht.get(key).getPrice();
	}
	
	public String toString() {
		String a = "주문번호 : "+orderNo+"\n";
		if(ht.containsKey("아메리카노")) {
			String b="아메리카노"+" 잔 수 : "+ht.get("아메리카노").getCups()+", 소계"+ht.get("아메리카노").getPrice()+"\n";
			a+=b;	// String 이라서 덧셈 x // a= a+b
		}
		if(ht.containsKey("카페라떼")) {
			String c="카페라떼"+" 잔 수 :"+ht.get("카페라떼").getCups()+", 소계 : "+ht.get("카페라떼").getPrice()+"\n";
			a+=c;	// a= a+c
		}
		if(ht.containsKey("카페라떼")) {
			String d="카푸치노"+" 잔 수 :"+ht.get("카푸치노").getCups()+", 소계 : "+ht.get("카푸치노").getPrice()+"\n";
			a+=d;	// a= a+d
		}
		
		String e= "총계 : "+price;
		a+=e;
		
		return a;
	}


	
}









