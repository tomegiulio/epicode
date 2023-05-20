package projectw1;

public class Picture extends File implements Luminosita {
	private int luminosita;
	private String tipo="IMG";
	
	public Picture(String fileName,int luminosita) {
		this.fileName=fileName;
		this.setLuminosita(luminosita);
	}
	public void show() {
		String lum="";
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
		System.out.println(lum+" "+fileName);
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
	public void play() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void aumentaVolume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void diminuisciVolume() {
		// TODO Auto-generated method stub
		
	}
	


}
