package model.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.vo.Coffee;
import model.vo.Order;

public class CoffeeDao {

	public ArrayList<Order> openList() {
		ArrayList<Order> list = new ArrayList<>();

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("coffee.dat"))) { // coffee.dat라는 파일에 데이터를
																								// 저장하기위한 방법.
			while (in.available() != -1) { // 읽어올 데이터가 있는동안
				list.add((Order) in.readObject()); // 데이터를 읽어오다가 문제가 생기면 IOException,ClassNFException으로 throws. (반환타입
			}										// Object이라 캐스팅후 변수에 add로 저장후 대입)
		} catch (EOFException e) { // file의 끝을 체크하는 exception (EOF = End Of File)
			System.out.println("불러오기 성공하였습니다.");
		} catch (IOException e) { // (IO = Input Output)
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) { // (ClassNotFound = 내가 찾고자하는 클래스가 없다)
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	public int saveList(ArrayList<Order> list) {
		int result = -1;
		
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("coffee.dat"))){	// coffee.dat에 저장
			for(Order lst : list) {
				out.writeObject(lst);
			}
			result=1;
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public void outStream(ObjectOutputStream out, Coffee co) {
		try {
			out.writeObject(co);
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}















