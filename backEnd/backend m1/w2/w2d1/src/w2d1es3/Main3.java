package w2d1es3;

public class Main3 {
	public static void main(String args[]) throws BancaException {
		ContoCorrente conto1 = new ContoCorrente("Grossi Mario", 20000.0);
		//ContoOnLine conto2 = new ContoOnLine("Giulio", 9999999.0,2000);
		conto1.preleva(2000000);
		System.out.println("Saldo conto: " + conto1.restituisciSaldo());
		/*try {
			conto1.preleva(1750.5);

			System.out.println("Saldo conto: " + conto1.restituisciSaldo());
		} catch (BancaException e) {
			System.out.println("Errore durante il prelievo: " + e);
			e.printStackTrace();
		}

		ContoOnLine conto2 = new ContoOnLine("Rossi Luigi", 50350.0, 1500);

		conto2.stampaSaldo();

		try {
			conto2.preleva(2000);

			conto2.stampaSaldo();

		} catch (BancaException e) {
			System.out.println("Errore durante il prelievo: " + e);
			e.printStackTrace();
		}*/
		
	}
}

