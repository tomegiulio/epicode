package projectw1;

public class Video extends File implements Volume, Luminosita{
	
	private int volume;
	private double durata;
	private int luminosita;
	private String tipo="";
	
	public Video(String fileName,int volume,double durata,int luminosita) {
		this.fileName=fileName;
		this.volume=volume;
		this.setDurata(durata);
		this.setLuminosita(luminosita);
	}
	
	

	@Override
	public void play() {
		String vol="";
		String lum="";
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
		//lum
		switch(luminosita) {
		case 1:
			lum="*";
			break;
		case 2:
			lum="**";
			break;
		case 3:
			lum="***";
			break;
		case 4:
			lum="****";
			break;
		case 5:
			lum="*****";
			break;
		case 6:
			lum="******";
			break;
		case 7:
			lum="*******";
			break;
		case 8:
			lum="********";
			break;
		case 9:
			lum="*********";
		case 10:
			lum="**********";
			break;
		default:
			lum="la luminosita' e' settata a 0";
			break;
		}
		
		if(durata<1) {
			System.out.println(vol+" "+lum+" "+fileName);
		}else {
		do {
			
			++durataLoop;
			System.out.println(vol+" "+lum+" "+fileName);
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
		if(luminosita<10) {
			++luminosita;}else {
				System.out.println("la luminosita e' gia al massimo");
			}
		
	}



	@Override
	public void diminuisciLuminsita() {
		if(luminosita>0) {
			--luminosita;}else {
				System.out.println("la luminosita e' gia al minimo");
			}
		
	}



	public int getLuminosita() {
		return luminosita;
	}



	public void setLuminosita(int luminosita) {
		this.luminosita = luminosita;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	@Override
	protected void show() {
		// TODO Auto-generated method stub
		
	}
}