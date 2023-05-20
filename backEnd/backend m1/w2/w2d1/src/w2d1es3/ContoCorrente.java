package w2d1es3;

public class ContoCorrente {
	String titolare;
	int nMovimenti;
	final int maxMovimenti = 50;
	double saldo;

	ContoCorrente(String titolare, double saldo) {
		this.titolare = titolare;
		this.saldo = saldo;
		nMovimenti = 0;
	}

	void preleva(double x)  throws BancaException {
		if (nMovimenti < maxMovimenti) {
			double saldo2 = saldo - x;
			if(!(saldo2<0)) {
			saldo = saldo - x;}
			else {
				throw new BancaException("conto in rosso");
			}
			}else {
			saldo = saldo - x - 0.50;
		nMovimenti++;}
	}

	double restituisciSaldo() {
		return saldo;
	}
}
