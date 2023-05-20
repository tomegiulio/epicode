package projectw1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/*
		 * audio audio1=new audio("QUEVEDO", 0,3); audio1.diminuisciVolume();
		 * audio1.aumentaVolume(); audio1.play();
		 * 
		 * Video video1=new Video("video1",3,3,10); video1.play();
		 * 
		 * Picture pic1=new Picture("pic1",0); //pic1.aumentaLuminosita();
		 * pic1.diminuisciLuminsita(); pic1.show();
		 */
		scann();
	}

	public static void scann() {
		// array di file
		File[] fileArr = new File[5];
		int tipoFile = 0;
		System.out.println("Inserisci 5 file nella lista");
		Scanner scanner = new Scanner(System.in);

		// selezione tipo di file
		for (int i = 0; i < fileArr.length; i++) {
			System.out.println("Di che tipo è il file numero " + (i + 1) + " ?");
			System.out.println("1.VIDEO \r" + "2.AUDIO \r" + "3.IMG");
			tipoFile = Integer.parseInt(scanner.nextLine());
			System.out.println("Dai un nome al tuo file numero " + (i + 1));
			String fileName = scanner.nextLine();
			if (tipoFile == 1) {
				System.out.println("Indica la durata del tuo VIDEO");
				int durataVideo = Integer.parseInt(scanner.nextLine());
				fileArr[i] = new Video(fileName, 10, durataVideo, 10);
			} else if (tipoFile == 2) {
				System.out.println("Indica la durata del tuo AUDIO");
				int durataAudio = Integer.parseInt(scanner.nextLine());
				fileArr[i] = new audio(fileName, 10, durataAudio);
			} else if (tipoFile == 3) {
				fileArr[i] = new Picture(fileName, 10);
			} else {
				System.out.println("Scegli un numero valido!!");
			}}
			System.out.println("Benvenuti ecco la vostra playList");

			getPlayList(fileArr);

			System.out.println("Scegli il numero da riprodurre, oppure 0 per uscire");

			int numeroTraccia = Integer.parseInt(scanner.nextLine());

			if (numeroTraccia == 0) {
				System.out.println("Good bye");
				scanner.close();
			} else {
				while (numeroTraccia != -1) {

					switch (numeroTraccia) {
					case 0:
						System.out.println("Riproduzione interrotta");
						numeroTraccia = -1;
						break;
					case 1:
						riproduci(fileArr[0]);
						System.out.println("Riprodurre ancora? si o no?");
						String input0 = scanner.nextLine();

						if (input0.equals("si")) {
							numeroTraccia = 1;
						} else {
							getPlayList(fileArr);
							System.out.println("Scegli il numero da riprodurre, oppure 0 per uscire");
							numeroTraccia = Integer.parseInt(scanner.nextLine());
						}
						break;

					case 2:
						riproduci(fileArr[1]);

						System.out.println("Riprodurre ancora? si o no?");
						String input2 = scanner.nextLine();

						if (input2.equals("si")) {
							numeroTraccia = 2;
						} else {
							getPlayList(fileArr);
							System.out.println("Scegli il numero da riprodurre, oppure 0 per uscire");
							numeroTraccia = Integer.parseInt(scanner.nextLine());
						}
						break;

					case 3:
						riproduci(fileArr[2]);

						System.out.println("Riprodurre ancora? si o no?");
						String input3 = scanner.nextLine();

						if (input3.equals("si")) {
							numeroTraccia = 3;
						} else {
							getPlayList(fileArr);
							System.out.println("Scegli il numero da riprodurre, oppure 0 per uscire");
							numeroTraccia = Integer.parseInt(scanner.nextLine());
						}
						break;

					case 4:
						riproduci(fileArr[3]);

						System.out.println("Riprodurre ancora? si o no?");
						String input4 = scanner.nextLine();

						if (input4.equals("si")) {
							numeroTraccia = 4;
						} else {
							getPlayList(fileArr);
							System.out.println("Scegli il numero da riprodurre, oppure 0 per uscire");
							numeroTraccia = Integer.parseInt(scanner.nextLine());
						}
						break;

					case 5:
						riproduci(fileArr[4]);

						System.out.println("Riprodurre ancora? si o no?");
						String input5 = scanner.nextLine();

						if (input5.equals("si")) {
							numeroTraccia = 5;
						} else {
							getPlayList(fileArr);
							System.out.println("Scegli il numero da riprodurre, oppure 0 per uscire");
							numeroTraccia = Integer.parseInt(scanner.nextLine());
						}
						break;

					default:
						System.out.println("scegli un numero valido!");

						getPlayList(fileArr);
						System.out.println("Scegli il numero da riprodurre, oppure 0 per uscire");
						numeroTraccia = Integer.parseInt(scanner.nextLine());
					}

				}
			}

		}

	

	static void getPlayList(File[] playlist) {
		for (int i = 0; i < playlist.length; i++) {
			System.out.println("" + (i + 1) + "." + playlist[i].fileName);
		}
	}

	static void riproduci(File file) {
		if (file.getTipo().equals("IMG")) {
			file.show();
		} else {
			file.play();
		}
	}
}
