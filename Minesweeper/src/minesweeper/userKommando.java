package minesweeper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class userKommando {
	Scanner scan = new Scanner(System.in);
	public String userEingabe;
	public boolean spielEnde = false;
	private boolean isFlageGesetzt = false;
	private int spalte, zeile;
	public int markierenCounter = -1;
	
	/*
	 * gibt die Eingabe des Users zur�ck
	 */
	public String eingabeUser(){
		userEingabe = scan.nextLine();
		return userEingabe;
	}
	/*
	 * gibt die Spalte zur�ck
	 */
	public int getSpalte(){
		String[] pos = userEingabe.split("", 2);
		int spalte = Integer.parseInt(pos[0]);
		return spalte;
	}
	/*
	 * gibt die Zeile zur�ck
	 */
	public int getZeile(){
		String[] pos = userEingabe.split("", 2);
		int zeile = Integer.parseInt(pos[1]);
		return zeile;
	}
	/*
	 * l�sst kommando ausw�hlen mit unterschiedlichen Handlungen
	 */
	public String kommando() throws IOException{
		switch (userEingabe) { 
		case "M":
			System.out.print("Geben sie jetzt die gew�nschte Position (10-99): ");
			userEingabe = scan.nextLine();
			markierenCounter++;
			markieren();
			break;

		case "T":
			System.out.print("Geben sie jetzt die gew�nschte Position (10-99): ");
			userEingabe = scan.nextLine();
			aufdecken();
			break;
		}
		return userEingabe;
	}
	
	/*
	 * deckt eine Zelle auf.
	 */
	public void aufdecken(){
		spalte = getSpalte();
		zeile = getZeile();
		int[] bombenPos = Spielplan.myGame.zellen.bombenPos;
		String[] bomben = Arrays.toString(bombenPos).split("[\\[\\]]")[1].split(", ");//verwandelt der int array zu ein String array.
		System.out.println("Bomben Position: "+Arrays.toString(bomben)); //kontrolle
		
		for (String bombe : bomben) {
			if (userEingabe.equals(bombe)) {
				System.out.println("VERLOREN");
				Spielplan.myGame.zeigeBomben();
				spielEnde = true;	
			}
		}
		if (spielEnde == false) {
			Spielplan.myGame.updateSpielfeld(spalte, zeile, Spielplan.myGame.bombenNaehe());
			//Spielplan.alleNachbarOeffnen(spalte, zeile);
		}
	}
	/*
	 * markiert eine Zelle
	 */
	public void markieren(){
		spalte = getSpalte();
		zeile = getZeile();
		int pos = spalte * 10 + zeile;
		if (Spielplan.myGame.zellen.checkGesetzt(spalte, zeile) == false) {
			Spielplan.myGame.zellen.flagenPos[markierenCounter] = pos;//f�gt die markiert Flage im flagenPos array.
			Spielplan.myGame.updateSpielfeld(spalte, zeile, "!");	
		}
	}
}
