package w2dd3es1;


public class threads extends Thread{
	int indiceI;
	int indiceF;
	int[] arr;
	int[] threadArr;
	int summ;
	public threads (int indiceI,int indiceF,int[] arr) {
		this.indiceI=indiceI;
		this.indiceF=indiceF;
		this.arr=arr;
	}
	@Override
	public void run() {
		for(int i=indiceI;i<indiceF;i++) {
			 summ =summ+arr[i];}
		System.out.println(summ);
		
		}
	}
	

