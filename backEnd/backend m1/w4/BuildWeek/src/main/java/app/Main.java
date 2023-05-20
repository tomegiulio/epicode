package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.busDAO;
import dao.distributoreDAO;
import dao.rivenditoreDAO;
import dao.tramDAO;
import dao.trattaDAO;
import dao.userDAO;
import entities.abbonamento;
import entities.biglietto;
import entities.bus;
import entities.distributore;
import entities.rivenditore;
import entities.tessera;
import entities.tram;
import entities.tratta;
import entities.tratteripetute;
import entities.user;
import enumm.emissione;
import enumm.lineaRoma;
import enumm.scadenza;
import enumm.statoServizio;
import enumm.status;
import enumm.timbro;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BuildWeek");

	public static void main(String[] args) {
		/////////// -----------insert
		/////////// venditori//macchinette///////////////////////////////////////////////////
		//insertRivenditore(lineaRoma.MUNICIPIO);
		//insertDistributore(lineaRoma.MUNICIPIO);
		
		/// insert user e
		/// tessera///////////////////////////////////////////////////////////////////
		/// insertUser("mr", "dao");
		// getUserById(4);

		// insertTessera(4);
		// insertTessera(4);

		//// rinnova la
		//// tessera////////////////////////////////////////////////////////////////////
		// rinnovaTessera(10);

		//// insert abbonamento mensile o settimanale dalla macchinetta o dal
		//// rivenditore/////////////////////////////////////////
		// insertAbbonamentoMacchinetta(emissione.MENSILE, 3, 1);
		// insertAbbonamentoRivenditore(emissione.SETTIMANALE,1,1);

		////////// insert biglietto da macchinetta o
		////////// distributore//////////////////////////////////////////////
		// insertBigliettoDistrubutore(1);
		// insertBigliettoRiventore(1);

		////////// insert tratta////////////////////////////////////////////////////
		//insertTratta(lineaRoma.FONTANA_DI_TREVI,lineaRoma.MUNICIPIO,11,10);

		/////////// insert
		/////////// mezzi///////////////////////////////////////////////////////////////////////
		// insertBus();
		///insertTram();

		/// run
		/// mezzo/////////////////////////////////////////////////////////////////////////////////////////////
		 //runTram(6,5);
		// runBus(1,1);
		////////////////

		/// query get ticket macchinette
		/// rivenditori////////////////////////////////////////////////////////////////////
		// getTicketRivenditore(1);
		// getTicketDistributore(1);

		//// query get ticket timbrati dai
		//// mezzi//////////////////////////////////////////////////////////
		// getTicketTrue("bus",1);
		/// getTicketTrueByTime("bus", 1, LocalDate.now());

		//// get abbonamento associato a
		//// tessera/////////////////////////////////////////////////////////////////////////////////////
		// getSubCard(1);

		/// get abbonamenti creati in una determinata zona in una determinata
		/// data/////////////////////////////////////////////////////
		/// getSubsFrom1(lineaRoma.FONTANA_DI_TREVI, LocalDate.now());

		//// funzione validizazione
		//// ticket//////////////////////////////////////////////////////////////////////////////////////////
		// validatorTicket(1,"bus",1);

		///////// validatore
		///////// abbonamenti///////////////////////////////////////////////////////////////////////////////////////////////////
		// validatorSubById(1);

		/// get tratte di un
		/// mezzo////////////////////////////////////////////////////////////////////////////////////////////////////////
		///getTripTram(6);
		// getTripBus(1);

		/// manutenzione dei
		/// mezzi/////////////////////////////////////////////////////////////////////////////////////////////////////////
		// manutenzioneBus(1);
		// manutenzioneTram(1);
		// endManutBus(1);
		// endManutTram(1);
		// showManutenzioneTram(1);
		
		open();

	}

	///////////// scannnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn
	public static void open() {
		boolean esegui = true;

		do {
			System.out.println();
			System.out.println("MENU");
			System.out.println("""
					1. Registra tessera
					2. Compra biglietto
					3. Compra abbonamento
					4. Rinnova abbonamento
					5. Viaggia
					6. Per aprire il menu Personale
					0. Per uscire""");

			try {
				int scelta = scanner.nextInt();

				switch (scelta) {
				case 0 -> {
					System.out.println("ARRIVEDERCI!");
					esegui = false;
				}
				case 1 -> {
					System.out.println("Inserisci nome");
					String x = scanner.next();

					System.out.println("Inserisci cognome");
					String y = scanner.next();
					insertTesseraScann(x, y);
					System.out.println("creata una tessera a nome di " + x + " " + y);
				}
				case 2 -> {
					System.out.println("Scegli come acquistare il biglietto");
					System.out.println("0=rivenditore");
					System.out.println("1=distributore");
					int x = Integer.parseInt(scanner.next());
					if (x == 0) {
						getZoneScannerR();
						System.out.println("inserisci il numero della zona da selezionare");
						int g = Integer.parseInt(scanner.next());
						try {
							insertBigliettoRiventore(g);
						} catch (Exception e) {
							System.out.println("inserici una zona valida");
							System.exit(0);
						}
						System.out.println("Hai acquistato un biglietto, caccia i soldi!");
					} else if (x == 1) {
						getZoneScannerD();
						System.out.println("inserisci il numero della zona da selezionare");
						int g = Integer.parseInt(scanner.next());
						try {
							insertBigliettoDistrubutore(g);
						} catch (Exception e) {
							System.out.println("inserici una zona valida");
							System.exit(0);
						}
						System.out.println("Hai acquistato biglietto, caccia i soldi!");
					} else {
						System.out.println("errore scegli un numero valido");
					}

				}
				case 3 -> {
					System.out.println("Scegli come acquistare l'abbonamento");
					System.out.println("0=rivenditore");
					System.out.println("1=distributore");
					int x = Integer.parseInt(scanner.next());
					if (x == 0) {
						getZoneScannerR();
						System.out.println("inserisci il numero della zona da selezionare");
						int g = Integer.parseInt(scanner.next());
						try {
							System.out.println("Inserisci il codice della tessera");
							int y = Integer.parseInt(scanner.next());
							System.out.println("Inserisci la durata del tuo abbonamento");
							System.out.println("1=mensile");
							System.out.println("2=settimanale");
							int z = Integer.parseInt(scanner.next());
							emissione e = null;
							if (z == 1) {
								e = emissione.MENSILE;

							} else if (z == 2) {
								e = emissione.SETTIMANALE;
							} else {
								System.out.println("Iserisci numero valido");
							}
							insertAbbonamentoRivenditore(e, y, g);
						} catch (Exception e) {
							System.out.println("inserici una zona valida");
							System.exit(0);
						}
						System.out.println("Hai acquistato un abbonamento, caccia i soldi!");
					} else if (x == 1) {
						getZoneScannerR();
						System.out.println("inserisci il numero della zona da selezionare");
						int g = Integer.parseInt(scanner.next());
						try {
							System.out.println("Inserisci il codice della tessera");
							int y = Integer.parseInt(scanner.next());
							System.out.println("Inserisci la durata del tuo abbonamento");
							System.out.println("1=mensile");
							System.out.println("2=settimanale");
							int z = Integer.parseInt(scanner.next());
							emissione e = null;
							if (z == 1) {
								e = emissione.MENSILE;

							} else if (z == 2) {
								e = emissione.SETTIMANALE;
							} else {
								System.out.println("Iserisci numero valido");
							}
							insertAbbonamentoMacchinetta(e, y, g);
						} catch (Exception e) {
							System.out.println("inserici una zona valida");
							System.exit(0);
						}
						System.out.println("Hai acquistato un abbonamento, caccia i soldi!");
					} else {
						System.out.println("errore scegli un numero valido");
					}

				}
				case 4 -> {

					System.out.println("Rinnova l'abbonamento, inserisci in numero identificativo dell'abbonamento");
					int x = Integer.parseInt(scanner.next());
					System.out.println("Inserisci la durata del tuo abbonamento");
					System.out.println("1=mensile");
					System.out.println("2=settimanale");
					int z = Integer.parseInt(scanner.next());
					emissione e = null;
					if (z == 1) {
						e = emissione.MENSILE;
						System.out.println("abbonamento numero " + x + " rinnovato aggiunti 30 gg di durata");

					} else if (z == 2) {
						e = emissione.SETTIMANALE;
						System.out.println("abbonamento numero " + x + " rinnovato aggiunti 7 gg di durata");
					} else {
						System.out.println("Iserisci numero valido");
					}
					try {
						rinnovaSub(x, e);
					} catch (Exception ez) {
						System.out.println("rinnovo fallito");
					}

				}
				case 5 -> {
					System.out.println("Seleziona il numero della tratta da percorrere");
					getTratteScanner();
					int idTratta = Integer.parseInt(scanner.next());
					System.out.println("Seleziona mezzo");
					System.out.println("1=tram");
					System.out.println("2=bus");
					int tipoMezzo = Integer.parseInt(scanner.next());
					if (tipoMezzo == 1) {
						getTramScanner(idTratta);
						System.out.println("seleziona uno dei tram disponibili per la tratta");
						int idBusToRun = Integer.parseInt(scanner.next());
						try {
							runTram(idBusToRun, idTratta);
						} catch (Exception e) {
							System.out.println("inserisci numero tram valido");
							System.exit(0);
						}
						System.out.println("Valida biglietto o abbonamento?");
						System.out.println("1=abbonamento?");
						System.out.println("2=biglietto?");
						int ticOrSub = Integer.parseInt(scanner.next());
						if (ticOrSub == 1) {
							System.out.println("inserisci numero abbonamento");
							int subCod = Integer.parseInt(scanner.next());
							try {
								validatorSubById(subCod);
							} catch (Exception e) {
								System.out.println("inserisci numero abbonamento valido");
							}
							System.out.println(
									"hai convalidato abbonamento numero " + subCod + ", sei a bordo del tram numero "
											+ idBusToRun + " sulla tratta " + getTrattaScanner(idTratta));
						} else if (ticOrSub == 2) {
							System.out.println("inserisci numero biglietto");
							int bigliettoCod = Integer.parseInt(scanner.next());
							try {
								validatorTicket(bigliettoCod, "tram", idBusToRun);
							}

							catch (Exception e) {
								System.out.println("inserisci numero abbonamento valido");
							}
							System.out.println("hai convalidato biglietto numero " + bigliettoCod
									+ ", sei a bordo del tram numero " + idBusToRun + " sulla tratta "
									+ getTrattaScanner(idTratta));
						} else {
							System.out.println("inserisci un numero valido");
						} ///////
					} else if (tipoMezzo == 2) {
						getBusScanner(idTratta);
						System.out.println("seleziona uno dei bus disponibili per la tratta");
						int idBusToRun = Integer.parseInt(scanner.next());
						try {
							runBus(idBusToRun, idTratta);
						} catch (Exception e) {
							System.out.println("inserisci un numero bus valido");
							System.exit(0);
						}
						System.out.println("Valida biglietto o abbonamento?");
						System.out.println("1=abbonamento?");
						System.out.println("2=biglietto?");
						int ticOrSub = Integer.parseInt(scanner.next());
						if (ticOrSub == 1) {
							System.out.println("inserisci numero abbonamento");
							int subCod = Integer.parseInt(scanner.next());
							try {
								validatorSubById(subCod);
							} catch (Exception e) {
								System.out.println("inserisci numero abbonamento valido");
							}
							System.out.println(
									"hai convalidato abbonamento numero " + subCod + ", sei a bordo del bus numero "
											+ idBusToRun + " sulla tratta " + getTrattaScanner(idTratta));
						} else if (ticOrSub == 2) {
							System.out.println("inserisci numero biglietto");
							int bigliettoCod = Integer.parseInt(scanner.next());
							try {
								validatorTicket(bigliettoCod, "bus", idBusToRun);
							}

							catch (Exception e) {
								System.out.println("inserisci numero abbonamento valido");
							}
							System.out.println(
									"hai convalidato biglietto numero " + bigliettoCod + ", sei a bordo del bus numero "
											+ idBusToRun + " sulla tratta " + getTrattaScanner(idTratta));

						} else {
							System.out.println("inserisci un numero valido");
						}
					} else {
						System.out.println("inserisci un numero valido");
						System.exit(0);
					}

				}
				case 6 -> {
					System.out.println("Hai scelto menu Manutenzione");
					System.out.println("""
							1. MANUTENZIONE DEI MEZZI
							2. GET INFO DEI MEZZI
							3. GET INFO DISTRIBUTORI
							4. GET INFO RIVENDITORI
							5. ASSOCIA MEZZO A UNA TRATTA
							6. CREA TRATTA
							0. Per uscire""");
					int scelta2 = scanner.nextInt();

					switch (scelta2) {
					case 0 -> {
						System.out.println("ARRIVEDERCI!");
						esegui = false;
					}
					case 1 -> {
						System.out.println("inserire tipo di mezzo");
						System.out.println("0=tram");
						System.out.println("1=bus");
						int s = Integer.parseInt(scanner.next());
						if (s == 0) {
							System.out.println("digita:");
							System.out.println("0 per settare un mezzo come in manutenzione");
							System.out.println("1 per settare un mezzo come in servizio");
							System.out.println("2 per ottenere registro delle manutenzioni");
							System.out.println("3 per mostare id mezzi in manutenzione");
							System.out.println("4 per mostare id mezzi in servizio");
							System.out.println("5 per inserire tram");
							int y = Integer.parseInt(scanner.next());
							if (y == 0) {
								getTramDisponibili();
								System.out.println("inserisci un numero del tram");
								int x = Integer.parseInt(scanner.next());
								manutenzioneTram(x);
								
							} else if (y == 1) {
								getTramRotti();
								System.out.println("inserisci un numero del tram");
								int x = Integer.parseInt(scanner.next());
								endManutTram(x);
								
							} else if (y == 2) {
								System.out.println("inserisci un numero del tram");
								int x = Integer.parseInt(scanner.next());
								showManutenzioneTram(x);
							} else if (y == 3) {
								getTramRotti();
							} else if (y == 4) {
								getTramDisponibili();
							}else if (y == 5) {
								insertTram();
							} else {
								System.out.println("inserire un numero valido");
							}
						} else if (s == 1) {
							System.out.println("digita:");
							System.out.println("0 per settare un mezzo come in manutenzione");
							System.out.println("1 per settare un mezzo come in servizio");
							System.out.println("2 per ottenere registro delle manutenzioni");
							System.out.println("3 per mostare id mezzi in manutenzione");
							System.out.println("4 per mostare id mezzi in servizio");
							System.out.println("5 per inserire bus");
							int y = Integer.parseInt(scanner.next());
							if (y == 0) {
								getBusDisponibili();
								System.out.println("inserisci un numero del bus");
								int x = Integer.parseInt(scanner.next());
								manutenzioneBus(x);
								
							} else if (y == 1) {
								getBusRotti();
								System.out.println("inserisci un numero del bus");
								int x = Integer.parseInt(scanner.next());
								endManutBus(x);
								
							} else if (y == 2) {
								System.out.println("inserisci un numero del bus");
								int x = Integer.parseInt(scanner.next());
								showManutenzioniBus(x);
							} else if (y == 3) {
								getBusRotti();
							} else if (y == 4) {
								getBusDisponibili();
							} else if (y == 5) {
								insertBus();
							} else {
								System.out.println("inserire un numero valido");
							}
						} else {
							System.out.println("inserisci un numero valido");
						}
					}
					case 2 -> {
						System.out.println("inserire tipo di mezzo");
						System.out.println("0=tram");
						System.out.println("1=bus");
						int tipoMezzo = Integer.parseInt(scanner.next());
						
						if (tipoMezzo == 0) {
							System.out.println("inserisci id mezzo");
							int id = Integer.parseInt(scanner.next());
							System.out.println("inserire operazione");
							System.out.println("0=mostra tratte efettuate mezzo");
							System.out.println("1=mostra biglietti vidimati mezzo");
							System.out.println("2=mostra biglietti vidimati in tot data");
							int Op = Integer.parseInt(scanner.next());
							if(Op==0) {
								getTripTram(id);
							}else if(Op==1){
								 getTicketTrue("tram",id);
							}else if(Op==2){
								System.out.println("inserisci data da controllare");
								String zz=scanner.next();
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
								formatter = formatter.withLocale( Locale.ITALIAN );  
								LocalDate date = LocalDate.parse(zz, formatter);
								 getTicketTrueByTime("tram",id,date);
							}else {
								System.out.println("inserire numero valido");
							}
						}else if(tipoMezzo == 1) {
							System.out.println("inserisci id mezzo");
							int id = Integer.parseInt(scanner.next());
							System.out.println("inserire operazione");
							System.out.println("0=mostra tratte efettuate mezzo");
							System.out.println("1=mostra biglietti vidimati mezzo");
							System.out.println("2=mostra biglietti vidimati in tot data");
							int Op = Integer.parseInt(scanner.next());
							if(Op==0) {
								getTripBus(id);
							}else if(Op==1){
								 getTicketTrue("bus",id);
							}else if(Op==2){
								System.out.println("inserisci data da controllare");
								String zz=scanner.next();
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
								formatter = formatter.withLocale( Locale.ITALIAN );  
								LocalDate date = LocalDate.parse(zz, formatter);
								 getTicketTrueByTime("bus",id,date);
							}else {
								System.out.println("inserire numero valido");
							} 
							
						}else {
							System.out.println("inserisci numero valido");
						}
					}
					case 3 -> {
						
						System.out.println("selziona operazione:");
						System.out.println("0=get abbonamenti");
						System.out.println("1=get biglietti:");
						System.out.println("2=insert distributore");
						int s = Integer.parseInt(scanner.next());
						if(s==0) {
							System.out.println("inserisci id DISTRIBUTORE");
							int id = Integer.parseInt(scanner.next());
							getSubDis(id);
						}else if(s==1){
							System.out.println("inserisci id DISTRIBUTORE");
							int id = Integer.parseInt(scanner.next());
							getTicketDistributore(id);
						}else if(s==2){
							System.out.println("digita la zona in cui creare un distributore");
							enumLoop();
							int se = Integer.parseInt(scanner.next());
							lineaRoma xy=null;
							if(se==1) {
								xy=lineaRoma.STAZIONE_CENTRALE;
							}else if(se==2) {
								xy=lineaRoma.MUNICIPIO;
							}else if(se==3) {
								xy=lineaRoma.TRASTEVERE;
							}else if(se==4) {
								xy=lineaRoma.CAMPO_DE_FIORI;
							}else if(se==5) {
								xy=lineaRoma.FONTANA_DI_TREVI;
							}else if(se==6) {
								xy=lineaRoma.PIAZZA_DEL_POPOLO;
							}else if(se==7) {
								xy=lineaRoma.PIAZZA_DI_SPAGNA;
							}else {
								System.out.println("inserisci numero valido");
							}
							insertDistributore(xy);
						}else {
							System.out.println("inserire numero valido");
						}
					}
					case 4 -> {
						
						System.out.println("selziona operazione:");
						System.out.println("0=get abbonamenti");
						System.out.println("1=get biglietti:");
						System.out.println("2=insert rivenditore");
						int s = Integer.parseInt(scanner.next());
						if(s==0) {
							System.out.println("inserisci id rivenditore");
							int id = Integer.parseInt(scanner.next());
							getSubRiv(id);
							
						}else if(s==1){
							System.out.println("inserisci id rivenditore");
							int id = Integer.parseInt(scanner.next());
							getTicketRivenditore(id);
						}else if(s==2){
							System.out.println("digita la zona in cui creare un distributore");
							enumLoop();
							int se = Integer.parseInt(scanner.next());
							lineaRoma xy=null;
							if(se==1) {
								xy=lineaRoma.STAZIONE_CENTRALE;
							}else if(se==2) {
								xy=lineaRoma.MUNICIPIO;
							}else if(se==3) {
								xy=lineaRoma.TRASTEVERE;
							}else if(se==4) {
								xy=lineaRoma.CAMPO_DE_FIORI;
							}else if(se==5) {
								xy=lineaRoma.FONTANA_DI_TREVI;
							}else if(se==6) {
								xy=lineaRoma.PIAZZA_DEL_POPOLO;
							}else if(se==7) {
								xy=lineaRoma.PIAZZA_DI_SPAGNA;
							}else {
								System.out.println("inserisci numero valido");
							}
							insertRivenditore(xy);
						}else {
							System.out.println("inserire numero valido");
						}
						
					}
					case 5 -> {
						System.out.println("assegna mezzo a una tratta...");
						System.out.println("inserisci");
						System.out.println("0-per inserire tram");
						System.out.println("1- per inserire bus");
						int se = Integer.parseInt(scanner.next());
						if(se==0) {
							System.out.println("inserisci numero tratta da associare");
							getTratteScanner();
							int t = Integer.parseInt(scanner.next());
							getTramDisponibili();
							System.out.println("seleziona numero del tram da associare");
							int b = Integer.parseInt(scanner.next());
							try {
								runTram(b,t);
							}catch(Exception e) {
								System.out.println("inserisci dei valori validi");
							}
							
						}else if(se==1) {
							System.out.println("inserisci numero tratta da associare");
							getTratteScanner();
							int t = Integer.parseInt(scanner.next());
							getBusDisponibili();
							System.out.println("seleziona numero del bus da associare");
							int tr = Integer.parseInt(scanner.next());
							try {
								runBus(tr,t);
							}catch(Exception e) {
								System.out.println("inserisci dei valori validi");
							}
							
							
						}else {
							System.out.println("inserisci un numero valido");
						}

					}
					case 6->{
						System.out.println("digita la zona di partenza");
                        enumLoop();
                        int se = Integer.parseInt(scanner.next());
                        lineaRoma xy=null;
                        if(se==1) {
                            xy=lineaRoma.STAZIONE_CENTRALE;
                        }else if(se==2) {
                            xy=lineaRoma.MUNICIPIO;
                        }else if(se==3) {
                            xy=lineaRoma.TRASTEVERE;
                        }else if(se==4) {
                            xy=lineaRoma.CAMPO_DE_FIORI;
                        }else if(se==5) {
                            xy=lineaRoma.FONTANA_DI_TREVI;
                        }else if(se==6) {
                            xy=lineaRoma.PIAZZA_DEL_POPOLO;
                        }else if(se==7) {
                            xy=lineaRoma.PIAZZA_DI_SPAGNA;
                        }else {
                            System.out.println("inserisci numero valido");
                        }
                        System.out.println("digita la zona di arrivo");
                        enumLoop();
                        int si = Integer.parseInt(scanner.next());
                        lineaRoma xz=null;
                        if(si==1) {
                            xz=lineaRoma.STAZIONE_CENTRALE;
                        }else if(si==2) {
                            xz=lineaRoma.MUNICIPIO;
                        }else if(si==3) {
                            xz=lineaRoma.TRASTEVERE;
                        }else if(si==4) {
                            xz=lineaRoma.CAMPO_DE_FIORI;
                        }else if(si==5) {
                            xz=lineaRoma.FONTANA_DI_TREVI;
                        }else if(si==6) {
                            xz=lineaRoma.PIAZZA_DEL_POPOLO;
                        }else if(si==7) {
                            xz=lineaRoma.PIAZZA_DI_SPAGNA;
                        }else {
                            System.out.println("inserisci numero valido");
                        }
                        System.out.println("inserisci minuti");
                        int m = Integer.parseInt(scanner.next());
                        
                        System.out.println("inserisci kilometri");
                        int k = Integer.parseInt(scanner.next());
                        try {
                        	insertTratta(xy,xz,m,k); 
                        }catch(Exception e) {
                        	System.out.println("inserisci dei valori validi");
                       
                        }
					}
					default -> System.out.println("Inserisci un input valido");

					}
				}

				default -> System.out.println("Inserisci un input valido");
				}

			} catch (InputMismatchException e) {
				System.out.println("Qualcosa è andato storto, riprova");
				scanner.nextLine();
			}
		} while (esegui);

		scanner.close();

	}

	// robe dA SCANNER
	public static void getSubDis(int id) {
		final EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT d FROM distributore d");


			@SuppressWarnings("unchecked")
			List<distributore> resultList = q.getResultList();
			@SuppressWarnings("unused")
			Set<abbonamento> abbF = null;

			for (distributore x : resultList) {
				if(x.getSellerCode()==id) {
				Set<abbonamento> y = x.getAbbonamentiEmessi();
				for(abbonamento z:y) {
					System.out.println("abbonamento numero: "+z.getSubCode());
				}}else {
					
				}
			}
				}catch(Exception e) {
		System.out.println("errore getsubsdis");
		}}

	public static void enumLoop() {
		int counter =0;
	for (lineaRoma x : lineaRoma.values()) {
		counter++;
		System.out.println(counter +" "+x);
	}
	}
	public static void getSubRiv(int id) {
		final EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT d FROM rivenditore d");


			@SuppressWarnings("unchecked")
			List<rivenditore> resultList = q.getResultList();
			@SuppressWarnings("unused")
			Set<abbonamento> abbF = null;

			for (rivenditore x : resultList) {
				if(x.getSellerCode()==id) {
				Set<abbonamento> y = x.getAbbonamentiEmessi();
				for(abbonamento z:y) {
					System.out.println("abbonamento numero: "+z.getSubCode());
				}}else {
					
				}
			}
				}catch(Exception e) {
		System.out.println("errore getsubsdis");
		}}

	
	public static void getBusScanner(int idTratta) {
		final EntityManager em = emf.createEntityManager();
		ArrayList<Integer> busDisponili = new ArrayList<Integer>();
		try {
			Query q = em.createQuery("SELECT x FROM bus x");

			@SuppressWarnings("unchecked")
			List<bus> resultList = q.getResultList();
			for (bus x : resultList) {
				for (tratta y : x.getViaggi()) {
					int roadId = y.getRoadCode();
					status ss = x.getStatuss();
					if (roadId == idTratta && ss == status.SERVIZIO) {
						busDisponili.add(x.getBusCode());
					}
				}

			}
			for (int y : busDisponili) {
				if (!(y == idTratta)) {

				} else {
					System.out.println("bus numero " + y + " disponibile per tratta");
				}
			}
			try {
				int maximum = busDisponili.get(0);
				for (int i = 0; i < busDisponili.size(); i++) {
					if (maximum < busDisponili.get(i))
						maximum = busDisponili.get(i);
				}
			} catch (Exception e) {
				System.out.println("nessun bus disponibile");
				System.exit(0);
			}

		} finally {
			em.close();
		}
	}

	public static void getTramRotti() {
		final EntityManager em = emf.createEntityManager();
		ArrayList<tram> tramDisponili = new ArrayList<tram>();
		try {
			Query q = em.createQuery("SELECT x FROM tram x");

			@SuppressWarnings("unchecked")
			List<tram> resultList = q.getResultList();
			for (tram x : resultList) {

				status ss = x.getStatuss();
				if (ss == status.MANUTENZIONE) {
					tramDisponili.add(x);
				}
			}
			for (tram y : tramDisponili) {
				System.out.println("tram numero " + y.getTramCode() + " in manutenzione");
			}

		} finally {
			em.close();
		}
	}

	public static void getBusRotti() {
		final EntityManager em = emf.createEntityManager();
		ArrayList<bus> tramDisponili = new ArrayList<bus>();
		try {
			Query q = em.createQuery("SELECT x FROM bus x");

			@SuppressWarnings("unchecked")
			List<bus> resultList = q.getResultList();
			for (bus x : resultList) {

				status ss = x.getStatuss();
				if (ss == status.MANUTENZIONE) {
					tramDisponili.add(x);
				}
			}
			for (bus y : tramDisponili) {
				System.out.println("bus numero " + y.getBusCode() + " in manutenzione");
			}

		} finally {
			em.close();
		}
	}

	public static void getBusDisponibili() {
		final EntityManager em = emf.createEntityManager();
		ArrayList<bus> tramDisponili = new ArrayList<bus>();
		try {
			Query q = em.createQuery("SELECT x FROM bus x");

			@SuppressWarnings("unchecked")
			List<bus> resultList = q.getResultList();
			for (bus x : resultList) {

				status ss = x.getStatuss();
				if (ss == status.SERVIZIO) {
					tramDisponili.add(x);
				}
			}
			for (bus y : tramDisponili) {
				System.out.println("bus numero " + y.getBusCode() + " in servizio");
			}

		} finally {
			em.close();
		}
	}

	public static void getTramDisponibili() {
		final EntityManager em = emf.createEntityManager();
		ArrayList<tram> tramDisponili = new ArrayList<tram>();
		try {
			Query q = em.createQuery("SELECT x FROM tram x");

			@SuppressWarnings("unchecked")
			List<tram> resultList = q.getResultList();
			for (tram x : resultList) {

				status ss = x.getStatuss();
				if (ss == status.SERVIZIO) {
					tramDisponili.add(x);
				}
			}
			for (tram y : tramDisponili) {
				System.out.println("tram numero " + y.getTramCode() + " in servizio");
			}

		} finally {
			em.close();
		}
	}

	public static void getTramScanner(int idTratta) {
		final EntityManager em = emf.createEntityManager();
		ArrayList<Integer> tramDisponili = new ArrayList<Integer>();
		try {
			Query q = em.createQuery("SELECT x FROM tram x");

			@SuppressWarnings("unchecked")
			List<tram> resultList = q.getResultList();
			for (tram x : resultList) {
				for (tratta y : x.getViaggi()) {
					int roadId = y.getRoadCode();
					status ss = x.getStatuss();
					if (roadId == idTratta && ss == status.SERVIZIO) {
						tramDisponili.add(x.getTramCode());
					}
				}

			}
			for (int y : tramDisponili) {
				if (!(y == idTratta)) {

				} else {
					System.out.println("tram numero " + y + " disponibile per tratta");
				}
			}
			try {
				int maximum = tramDisponili.get(0);
				for (int i = 0; i < tramDisponili.size(); i++) {
					if (maximum < tramDisponili.get(i))
						maximum = tramDisponili.get(i);
				}
			} catch (Exception e) {
				System.out.println("nessun tram disponibile");
				System.exit(0);
			}

		} finally {
			em.close();
		}
	}

	public static String getTrattaScanner(int id) {
		final EntityManager em = emf.createEntityManager();
		String f = null;
		try {
			Query q = em.createQuery("SELECT d FROM tratta d");
			@SuppressWarnings("unchecked")
			List<tratta> resultList = q.getResultList();

			for (tratta x : resultList) {
				if (x.getRoadCode() == id) {
					lineaRoma z = x.getPartenza();
					lineaRoma y = x.getArrivo();
					String s1 = y.toString();
					String s2 = z.toString();
					f = s1 + " " + s2;
				}
			}
		} finally {
			em.close();
		}
		return f;
	}

	public static void getTratteScanner() {
		final EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT d FROM tratta d");
			@SuppressWarnings("unchecked")
			List<tratta> resultList = q.getResultList();

			for (tratta x : resultList) {
				System.out.println(x.getRoadCode() + " partenza:" + x.getPartenza() + " arrivo: " + x.getArrivo());

			}
		} finally {
			em.close();
		}
	}

	public static void getZoneScannerD() {
		final EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT d FROM distributore d");
			@SuppressWarnings("unchecked")
			List<distributore> resultList = q.getResultList();

			for (distributore x : resultList) {
				System.out.println(x.getSellerCode() + " " + x.getZona());

			}
		} finally {
			em.close();
		}
	}

	public static void getZoneScannerR() {
		final EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT d FROM rivenditore d");
			@SuppressWarnings("unchecked")
			List<rivenditore> resultList = q.getResultList();

			for (rivenditore x : resultList) {
				System.out.println(x.getSellerCode() + " " + x.getZona());

			}
		} finally {
			em.close();
		}
	}

	public static void insertTesseraScann(String name, String c) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		user uss = new user(name, c);
		try {
			t.begin();
			em.persist(uss);
			t.commit();

		} finally {
			tessera tess = new tessera();
			tess.setUserr(uss);
			uss.setTessera(tess);
			if (tess.getDataScad().isBefore(LocalDate.now())) {
				tess.setVal(scadenza.SCADUTO);
			} else {
				tess.setVal(scadenza.VALIDO);
			}
			t.begin();
			em.persist(tess);
			t.commit();

		}

		try {

		} finally {
			em.close();
		}
	}

////////////////
	//// converti time
	public static double convertMin(double hour) {
		int minutes = (int) (hour * 60);
		return minutes;
	}

	///
	public static double convertHou(double d) {
		double hourInt = d / 60;
		return hourInt;
	}

	/// UTENTE UTENTE UTENTE UTENTE UTENTE UTENTE UTENTE UTENTE UTENTE
	///////////////// inseerimento nuovo utente//////////////////////////
	public static void insertUser(String n, String c) {
		user us = new user(n, c);
		userDAO UserDAO = new userDAO();
		UserDAO.save(us);
	}



	/// TESSERA TESSERA TESSERA TESSERA TESSERA TESSERA TESSERA TESSERA TESSERA
	///////////////// inseerimento nuova carta//////////////////////////
	public static void insertTessera(int id) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			user uss = em.find(user.class, id);
			tessera tess = new tessera();
			tess.setUserr(uss);
			uss.setTessera(tess);
			if (tess.getDataScad().isBefore(LocalDate.now())) {
				tess.setVal(scadenza.SCADUTO);
			} else {
				tess.setVal(scadenza.VALIDO);
			}
			t.begin();
			em.persist(tess);
			t.commit();
			t.begin();
			em.persist(uss);
			t.commit();
		} finally {
			em.close();
		}
	}

	//////// rinnovo carta//////////////
	public static void rinnovaTessera(int id) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			tessera tess = em.find(tessera.class, id);
			if (tess.getDataScad().isBefore(LocalDate.now())) {
				tess.setVal(scadenza.SCADUTO);
				tess.setDataScad(LocalDate.now().plusYears(1));
			} else {
				tess.setVal(scadenza.VALIDO);
				tess.setDataScad(tess.getDataScad().plusYears(1));
			}
			t.begin();
			em.persist(tess);
			t.commit();
		} finally {
			em.close();
		}
	}

	// DISTRIBUTORE DISTRIBUTORE DISTRIBUTORE DISTRIBUTORE DISTRIBUTORE DISTRIBUTORE
	// DISTRIBUTORE DISTRIBUTORE DISTRIBUTORE DISTRIBUTORE
	/////// insert distributore
	public static void insertDistributore(lineaRoma zona) {

		distributore dis = new distributore();
		dis.setZona(zona);
		distributoreDAO DistributoreDAO = new distributoreDAO();
		DistributoreDAO.save(dis);
	}

	//////////////////////////////////////////////////////
	public static void getTicketDistributore(int id) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			distributore dis = em.find(distributore.class, id);
			for (biglietto x : dis.getBigliettiEmessi()) {
				System.out.println("biglietto " + x.getTicketCode());
			}
			t.begin();
			em.persist(dis);
			t.commit();
		} finally {
			em.close();
		}
	}

	// RIVENDITORE RIVENDITORE RIVENDITORE RIVENDITORE RIVENDITORE RIVENDITORE
	// RIVENDITORE RIVENDITORE RIVENDITORE
	/// insert rivenditore
	public static void insertRivenditore(lineaRoma zona) {
		rivenditore riv = new rivenditore();
		riv.setZona(zona);
		rivenditoreDAO RivenditoreDAO = new rivenditoreDAO();
		RivenditoreDAO.save(riv);
	}

	//////////////////////////////////////////////////////////
	public static void getTicketRivenditore(int id) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			rivenditore riv = em.find(rivenditore.class, id);
			for (biglietto x : riv.getBigliettiEmessi()) {
				System.out.println("biglietto " + x.getTicketCode());
			}
			t.begin();
			em.persist(riv);
			t.commit();
		} finally {
			em.close();
		}
	}

	// TRAM TRAM TRAM TRAM TRAM TRAM
	// insert tram
	public static void insertTram() {
		tram tra = new tram();
		tramDAO TramDAO = new tramDAO();
		TramDAO.save(tra);
	}

	///
	public static void endManutTram(int tramId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			tram t1 = em.find(tram.class, tramId);
			if (t1.getStatuss() == status.MANUTENZIONE) {
				t1.setStatuss(status.SERVIZIO);
				int counter = 0;
				for (String x : t1.getManutenzione()) {
					if (x == null) {
						t1.getManutenzione()[counter] = "tram numero " + t1.getTramCode()
								+ " esce dalla manutenzione in data: " + LocalDate.now();
					} else {
						counter++;
					}

				}
			} else {
				System.out.println("il tram e' gia in servizio!");
			}
			t.begin();
			em.persist(t1);
			t.commit();
		} finally {
			em.close();
		}
	}

	//////////
	public static void showManutenzioneTram(int busId) {
		final EntityManager em = emf.createEntityManager();
		try {
			tram b1 = em.find(tram.class, busId);
			for (String x : b1.getManutenzione()) {
				if (!(x == null)) {
					System.out.println(x);
				}
			}

		} finally {
			em.close();
		}
	}

	/////////////////////////////////
	public static void runTram(int tramId, int trattaId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		ArrayList<Integer>check=new ArrayList<Integer>();
		try {
			Query q = em.createQuery("SELECT t FROM tram t");
			@SuppressWarnings("unchecked")
			List<tram> resultList = q.getResultList();
				for(tram x:resultList) {
					for(tratta y:x.getViaggi()) {
					check.add(y.getRoadCode());
					}
			}}catch(Exception e) {
				System.out.println(e);}
		if(check.contains(trattaId)) {
			tratta tratt = em.find(tratta.class, trattaId);
			tratta trat=new tratta();
			tram tramm = em.find(tram.class, tramId);
			trat.setPartenza(tratt.getPartenza());
			trat.setArrivo(tratt.getArrivo());
			trat.setKilometri(tratt.getKilometri());
			trat.setMinutiStimati(tratt.getMinutiStimati());
			///
			double time = convertMin(trat.getKilometri() / tramm.getKilometriOrari());
			trat.setMinutiReal(time);
			tramm.getViaggi().add(trat);
			t.begin();
			em.persist(trat);
			t.commit();
		}else {
		////////////////////////////////////
		try {
			tram tramm = em.find(tram.class, tramId);
			tratta tratt = em.find(tratta.class, trattaId);
			
			if (tramm.getStatuss() == status.SERVIZIO) {
				if (tramm.getViaggi().contains(tratt)) {
					tratteripetute tra2 = new tratteripetute();
					tra2.setArrivo(tratt.getArrivo());
					tra2.setPartenza(tratt.getPartenza());
					tra2.setKilometri(tratt.getKilometri());
					tra2.setMinutiStimati(convertMin(tratt.getMinutiStimati()));

					double time = convertMin(tratt.getKilometri() / tramm.getKilometriOrari());
					tra2.setMinutiReal(time);
					tramm.getViaggiripetuti().add(tra2);
					t.begin();
					em.persist(tra2);
					t.commit();
				} else {

					double time = convertMin(tratt.getKilometri() / tramm.getKilometriOrari());
					tratt.setMinutiReal(time);
					tramm.getViaggi().add(tratt);
					t.begin();
					em.persist(tramm);
					t.commit();
				}
			} else {
				System.out.println("tram in manutenzione!");
			}

		} finally {
			em.close();
		}}
	}

	///////////////////////////
	public static void getTripTram(int tramId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		tram tr = em.find(tram.class, tramId);
		for (tratta x : tr.getViaggi()) {
			System.out.println(x.getPartenza() + " " + x.getArrivo() + " " + x.getMinutiReal());
		}
		for (tratteripetute y : tr.getViaggiripetuti()) {
			System.out.println(y.getPartenza() + " " + y.getArrivo() + " " + y.getMinutiReal());
		}

		t.begin();
		t.commit();
	}

	/////////////////////
	public static void manutenzioneTram(int tramId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			tram t1 = em.find(tram.class, tramId);
			if (t1.getStatuss() == status.SERVIZIO) {
				t1.setStatuss(status.MANUTENZIONE);
				int counter = 0;
				for (String x : t1.getManutenzione()) {
					if (x == null) {
						t1.getManutenzione()[counter] = "tram numero " + t1.getTramCode()
								+ " entra in manutenzione in data: " + LocalDate.now();
					} else {
						counter++;
					}

				}
			} else {
				System.out.println("il tram e' gia in manutenzione!");
			}
			t.begin();
			em.persist(t1);
			t.commit();
		} finally {
			em.close();
		}
	}

	// BUS BUS BUS BUS BUS BUS BUS BUS BUS BUS BUS BUS
	/// insert bus
	public static void insertBus() {
		bus bus = new bus();
		busDAO BusDAO = new busDAO();
		BusDAO.save(bus);
	}

	////////////////
	public static void runBus(int busId, int trattaId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		ArrayList<Integer>check=new ArrayList<Integer>();
		try {
			Query q = em.createQuery("SELECT t FROM bus t");
			@SuppressWarnings("unchecked")
			List<bus> resultList = q.getResultList();
				for(bus x:resultList) {
					for(tratta y:x.getViaggi()) {
					check.add(y.getRoadCode());
					}
			}}catch(Exception e) {
				System.out.println(e);}
		if(check.contains(trattaId)) {
			tratta tratt = em.find(tratta.class, trattaId);
			tratta trat=new tratta();
			bus tramm = em.find(bus.class, busId);
			trat.setPartenza(tratt.getPartenza());
			trat.setArrivo(tratt.getArrivo());
			trat.setKilometri(tratt.getKilometri());
			trat.setMinutiStimati(tratt.getMinutiStimati());
			///
			double time = convertMin(trat.getKilometri() / tramm.getKilometriOrari());
			trat.setMinutiReal(time);
			tramm.getViaggi().add(trat);
			t.begin();
			em.persist(trat);
			t.commit();}else {
		try {
			bus tramm = em.find(bus.class, busId);
			tratta tratt = em.find(tratta.class, trattaId);
			if (tramm.getStatuss() == status.SERVIZIO) {
				if (tramm.getViaggi().contains(tratt)) {
					tratteripetute tra2 = new tratteripetute();
					tra2.setArrivo(tratt.getArrivo());
					tra2.setPartenza(tratt.getPartenza());
					tra2.setKilometri(tratt.getKilometri());
					tra2.setMinutiStimati(convertMin(tratt.getMinutiStimati()));

					double time = convertMin(tratt.getKilometri() / tramm.getKilometriOrari());
					tra2.setMinutiReal(time);
					tramm.getViaggiripetuti().add(tra2);
					t.begin();
					em.persist(tra2);
					t.commit();
				} else {

					double time = convertMin(tratt.getKilometri() / tramm.getKilometriOrari());
					tratt.setMinutiReal(time);
					tramm.getViaggi().add(tratt);
					t.begin();
					em.persist(tramm);
					t.commit();
				}
			} else {
				System.out.println("bus in manutenzione!");
			}

		} finally {
			em.close();
		}}
	}

	/////////////////////////////////
	public static void getTripBus(int busId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		bus buss = em.find(bus.class, busId);
		for (tratta x : buss.getViaggi()) {
			System.out.println(x.getPartenza() + " " + x.getArrivo() + " " + x.getMinutiReal());
		}
		for (tratteripetute y : buss.getViaggiripetuti()) {
			System.out.println(y.getPartenza() + " " + y.getArrivo() + " " + y.getMinutiReal());
		}

		t.begin();
		t.commit();
	}

	/////////////////////////////////
	public static void manutenzioneBus(int busId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			bus b1 = em.find(bus.class, busId);
			if (b1.getStatuss() == status.SERVIZIO) {
				b1.setStatuss(status.MANUTENZIONE);
				int counter = 0;
				for (String x : b1.getManutenzione()) {
					if (x == null) {
						b1.getManutenzione()[counter] = "bus numero " + b1.getBusCode()
								+ " entra in manutenzione in data: " + LocalDate.now();
					} else {
						counter++;
					}

				}
			} else {
				System.out.println("il bus e' gia in manutenzione!");
			}
			t.begin();
			em.persist(b1);
			t.commit();
		} finally {
			em.close();
		}
	}

	//////////////////////////////////////
	public static void endManutBus(int busId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			bus b1 = em.find(bus.class, busId);
			if (b1.getStatuss() == status.MANUTENZIONE) {
				b1.setStatuss(status.SERVIZIO);
				int counter = 0;
				for (String x : b1.getManutenzione()) {
					if (x == null) {
						b1.getManutenzione()[counter] = "bus numero " + b1.getBusCode()
								+ " esce dalla manutenzione in data: " + LocalDate.now();
					} else {
						counter++;
					}

				}
			} else {
				System.out.println("il bus e' gia in servizio!");
			}
			t.begin();
			em.persist(b1);
			t.commit();
		} finally {
			em.close();
		}
	}

	/////////////////////////////////////////
	public static void showManutenzioniBus(int busId) {
		final EntityManager em = emf.createEntityManager();
		try {
			bus b1 = em.find(bus.class, busId);
			for (String x : b1.getManutenzione()) {
				if (!(x == null)) {
					System.out.println(x);
				}
			}

		} finally {
			em.close();
		}
	}

	// BIGLIETTI BIGLIETTI BIGLIETTI BIGLIETTI BIGLIETTI BIGLIETTI BIGLIETTI
	// BIGLIETTI BIGLIETTI BIGLIETTI BIGLIETTI BIGLIETTI
	//////////////////////////////
	public static void insertBigliettoRiventore(int sellerId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			biglietto b = new biglietto();
			rivenditore river = em.find(rivenditore.class, sellerId);
			b.setRivenditore(river);
			t.begin();
			em.persist(b);
			t.commit();

		} finally {
			em.close();
		}
	}

	///////////////////////
	public static void insertBigliettoDistrubutore(int sellerId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			distributore dis = em.find(distributore.class, sellerId);
			if (dis.getAttivo() == statoServizio.IN_SERVIZIO) {
				biglietto b = new biglietto();
				b.setDistributore(dis);
				t.begin();
				em.persist(b);
				t.commit();
			} else {
				System.out.println("distributore fuori servizio");
			}

		} finally {
			em.close();
		}
	}

	////////////////////////////////
	public static void validatorTicket(int biglietto, String mezzo, int mezzoId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			biglietto tic = em.find(biglietto.class, biglietto);
			if (tic.getUsato() == timbro.FALSE) {
				tic.setUsato(timbro.TRUE);
				tic.setDataTim(LocalDate.now());
				if (mezzo == "tram") {
					tram tra = em.find(tram.class, mezzoId);
					tra.getBiglietti().add(tic);
				} else {
					bus bu = em.find(bus.class, mezzoId);
					bu.getBiglietti().add(tic);
				}
				t.begin();
				em.persist(tic);
				t.commit();
			} else {
				System.out.println("biglietto non valido");
				System.exit(0);
			}
		} finally {
			em.close();
		}
	}

	//////////////////////////////////////////
	public static void getTicketTrue(String mezzo, int mezzoId) {
		final EntityManager em = emf.createEntityManager();
		if (mezzo == "tram") {
			tram tra = em.find(tram.class, mezzoId);
			for (biglietto x : tra.getBiglietti()) {
				System.out.println("numero biglietto vidimato sul tram " + mezzoId + " = " + x.getTicketCode());
			}
		} else {
			bus buss = em.find(bus.class, mezzoId);
			for (biglietto x : buss.getBiglietti()) {
				System.out.println("numero biglietto vidimato sul bus " + mezzoId + "  = " + x.getTicketCode());
			}

		}
	}

	////////////////////////////////////////////////
	public static void getTicketTrueByTime(String mezzo, int mezzoId, LocalDate date) {
		final EntityManager em = emf.createEntityManager();
		if (mezzo == "tram") {
			tram tra = em.find(tram.class, mezzoId);
			for (biglietto x : tra.getBiglietti()) {
				if (x.getDataTim().isEqual(date)) {
					System.out.println("numero biglietto vidimato sul tram " + mezzoId + " in data " + date + " = "
							+ x.getTicketCode());
				}
			}
		} else {
			bus buss = em.find(bus.class, mezzoId);
			for (biglietto x : buss.getBiglietti()) {
				if (x.getDataTim().isEqual(date)) {
					System.out.println("numero biglietto vidimato sul bus " + mezzoId + " in data " + date + " = "
							+ x.getTicketCode());
				}
			}

		}
	}

	// TRATTA TRATTA TRATTA TRATTA TRATTA TRATTA TRATTA TRATTA TRATTA TRATTA
	////////////////////////////////
	public static void insertTratta(lineaRoma start, lineaRoma end, double minuti, double km) {
		tratta tra = new tratta();
		tra.setArrivo(end);
		tra.setPartenza(start);
		tra.setKilometri(km);
		tra.setMinutiStimati(minuti);
		trattaDAO TrattaDAO = new trattaDAO();
		TrattaDAO.save(tra);
	}

	// ABBONAMENTO ABBONAMENTO ABBONAMENTO ABBONAMENTO ABBONAMENTO
	public static void validatorSubById(int subId) {
		final EntityManager em = emf.createEntityManager();
		abbonamento abb = em.find(abbonamento.class, subId);
		if (abb.getDataScad().isBefore(LocalDate.now())) {
			abb.setVal(scadenza.SCADUTO);
			System.out.println("abbonamento numero " + abb.getSubCode() + " scaduto!");
		} else {
			System.out.println("abbonamento numero " + abb.getSubCode() + " valido");
		}
	}

	public static void insertAbbonamentoMacchinetta(emissione emiss, int userId, int sellerId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			distributore dis = em.find(distributore.class, sellerId);
			if (dis.getAttivo() == statoServizio.IN_SERVIZIO) {
				try {

					abbonamento a = new abbonamento(emiss);
					tessera tes = em.find(tessera.class, userId);
					a.setUser(tes);
					tes.setAbbonamentiCard(a);

					if (a.getType() == emissione.MENSILE) {
						a.setDataScad(a.getDataEmm().plusMonths(1));
					} else {
						a.setDataScad(a.getDataEmm().plusDays(7));
					}
					if (a.getDataScad().isBefore(LocalDate.now())) {
						a.setVal(scadenza.SCADUTO);
					} else {
						a.setVal(scadenza.VALIDO);
					}
					a.setDistributore(dis);
					t.begin();
					em.persist(a);
					t.commit();
				} finally {
					em.close();
				}
			} else {
				System.out.println("distributore fuori servizio");
			}

		} finally {
			em.close();
		}
	}

	public static void insertAbbonamentoRivenditore(emissione emiss, int userId, int sellerId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			rivenditore riv = em.find(rivenditore.class, sellerId);
			abbonamento a = new abbonamento(emiss);
			tessera tes = em.find(tessera.class, userId);
			a.setUser(tes);
			tes.setAbbonamentiCard(a);

			if (a.getType() == emissione.MENSILE) {
				a.setDataScad(a.getDataEmm().plusMonths(1));
			} else {
				a.setDataScad(a.getDataEmm().plusDays(7));
			}
			if (a.getDataScad().isBefore(LocalDate.now())) {
				a.setVal(scadenza.SCADUTO);
			} else {
				a.setVal(scadenza.VALIDO);
			}
			a.setRivenditore(riv);
			t.begin();
			em.persist(a);
			t.commit();
		} finally {
			em.close();
		}
	}

	public static void insertAbbonamento(emissione emiss, int userId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {

			abbonamento a = new abbonamento(emiss);
			tessera tes = em.find(tessera.class, userId);
			a.setUser(tes);
			tes.setAbbonamentiCard(a);

			if (a.getType() == emissione.MENSILE) {
				a.setDataScad(a.getDataEmm().plusMonths(1));
			} else {
				a.setDataScad(a.getDataEmm().plusDays(7));
			}
			if (a.getDataScad().isBefore(LocalDate.now())) {
				a.setVal(scadenza.SCADUTO);
			} else {
				a.setVal(scadenza.VALIDO);
			}
			t.begin();
			em.persist(a);
			t.commit();
		} finally {
			em.close();
		}
	}

	public static void rinnovaSub(int id, emissione periodo) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();

		try {
			abbonamento subb = em.find(abbonamento.class, id);
			validatorSubById(id);
			if (subb.getVal() == scadenza.SCADUTO) {
				if (periodo == emissione.SETTIMANALE) {
					subb.setDataScad(LocalDate.now().plusWeeks(1));
				} else {
					subb.setDataScad(LocalDate.now().plusMonths(1));
				}
			} else {
				if (periodo == emissione.SETTIMANALE) {
					subb.setDataScad(subb.getDataScad().plusWeeks(1));
				} else {
					subb.setDataScad(subb.getDataScad().plusMonths(1));
				}
			}
			t.begin();
			em.persist(subb);
			t.commit();
		} finally {
			em.close();
		}
	}

	public static void getSubCard(int id) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			tessera tessera = em.find(tessera.class, id);

			System.out.println("abbonamento numero: " + tessera.getAbbonamentiCard().getSubCode());

			t.begin();
			em.persist(tessera);
			t.commit();
		} finally {
			em.close();
		}
	}

	public static void getSubsFrom1(lineaRoma zona, LocalDate date) {
		final EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT d FROM distributore d WHERE d.zona=:d");
			q.setParameter("d", zona);

			@SuppressWarnings("unchecked")
			List<distributore> resultList = q.getResultList();

			for (distributore x : resultList) {
				Set<abbonamento> y = x.getAbbonamentiEmessi();
				for (abbonamento z : y) {
					if (z.getDataEmm().isEqual(date)) {
						System.out.println("abbonamento : " + z.getSubCode() + " emesso da macchinetta di: " + zona
								+ " in data " + date);
					}
				}
			}
			for (distributore x2 : resultList) {
				Set<biglietto> y2 = x2.getBigliettiEmessi();
				;
				for (biglietto z2 : y2) {
					if (z2.getDataEmm().isEqual(date)) {
						System.out.println("biglietto : " + z2.getTicketCode() + " emesso da macchinetta di: " + zona
								+ " in data " + date);
					}
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			Query q = em.createQuery("SELECT r FROM rivenditore r WHERE r.zona=:d");
			q.setParameter("d", zona);

			@SuppressWarnings("unchecked")
			List<rivenditore> resultList = q.getResultList();

			for (rivenditore x : resultList) {
				Set<abbonamento> y = x.getAbbonamentiEmessi();
				for (abbonamento z : y) {
					if (z.getDataEmm().isEqual(date)) {
						System.out.println("abbonamento: " + z.getSubCode() + " emesso da venditore di: " + zona
								+ " in data " + date);
					}
				}
			}
			for (rivenditore x2 : resultList) {
				Set<biglietto> y2 = x2.getBigliettiEmessi();
				;
				for (biglietto z2 : y2) {
					if (z2.getDataEmm().isEqual(date)) {
						System.out.println("biglietto: " + z2.getTicketCode() + " emesso da venditore di: " + zona
								+ " in data " + date);
					}
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
	}}




