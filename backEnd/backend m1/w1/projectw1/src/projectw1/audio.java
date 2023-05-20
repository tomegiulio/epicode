package projectw1;

public class audio extends File implements Volume{
	
	private int volume;
	private double durata;
	private String tipo="audio";
	
	public audio(String fileName,int volume,double durata) {
		this.fileName=fileName;
		this.volume=volume;
		this.setDurata(durata);
	}
	
	

	@Override
	public void play() {
		String vol="";
		int durataLoop=0;
		switch(volume) {
		case 1:
			vol="!";
			break;
		case 2:
			vol="!!";
			break;
		case 3:
			 vol="!!!";
			break;
		case 4:
			vol="!!!!";
			break;
		case 5:
			vol="!!!!!";
			break;
		case 6:
			vol="!!!!!!";
			break;
		case 7:
			vol="!!!!!!!";
			break;
		case 8:
			vol="!!!!!!!!";
			break;
		case 9:
			 vol="!!!!!!!!!";
		case 10:
			 vol="!!!!!!!!!!";
			break;
		default:
			vol="volume is off";
			break;
		}
		if(durata<1) {
			System.out.println(vol+fileName);
		}else {
		do {
			
			++durataLoop;
			System.out.println(vol+" "+fileName);
		}while(!(durataLoop==((int) durata)));
		}
	}

	@Override
	public void aumentaVolume() {
		if(volume<10) {
		++volume;}else {
			System.out.println("il volume e' gia al massimo");
		}
		
	}
	@Override
	public void diminuisciVolume() {
		if(volume>0) {
		--volume;}else {
			System.out.println("il volume e' gia off");
		}
	}
	public double getDurata() {
		return durata;
	}

	public void setDurata(double durata) {
		this.durata = durata;
	}



	@Override
	public void aumentaLuminosita() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void diminuisciLuminsita() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void show() {
		// TODO Auto-generated method stub
		
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
