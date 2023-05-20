package d3w1es3;

import java.util.Scanner;

public class Main {
	private static String parola=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		do {
		parola =scan();
		char[] arr = parola.toCharArray();
		for (char x : arr) {
			System.out.print(x + ", ");
		}}while(!":q".equals(parola));
		
	}

	static String scan() {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter :q ");
		String userName = myObj.nextLine();
		return userName;

	}

}
