package w2d2es3;

import java.util.HashMap;
import java.util.Map;

public class Main3 {

	public static void main(String[] args) {
		
		creaContatto("3383368820","ulio");
		creaContatto("3383368821","tulio");
		getInfo("3383368820");
		System.out.println(vodafone);
		//rimuoviContatto("3383368820");
		//System.out.println(vodafone);
		

	}
	static Map<String,String> vodafone=new HashMap<String,String>();
	
	public static void creaContatto(String numero,String nome){
		vodafone.put(numero,nome);
	}
	public static void rimuoviContatto(String number) {
		if(vodafone.containsKey(number)) {
			vodafone.remove(number);}
	}
	public static void getInfo(String number) {
		System.out.println(vodafone.get(number));
	}	
	}
	

