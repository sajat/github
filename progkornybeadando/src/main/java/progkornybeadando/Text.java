package progkornybeadando;

import java.util.ArrayList;

public class Text {
	protected String szoveg = null;
	protected int next = 0;
	protected ArrayList<String> splitSzoveg;
	
	/**
	 * Beállítja az adott példányban a szöveget.
	 * @param s a felhasználó által megadott string
	 */
	public void setString(String s){
		this.szoveg = s;
	}
	
	/**
	 * Visszaadja a felhasználó által megadott szöveget
	 * @return string a felhasználó által megadott szöveg
	 */
	public String getString(){
		return this.szoveg;
	}
	
	/**
	 * Visszaadja a következő id- string értéket
	 * @param id string key
	 * @return visszatér egy stringel amiből el van távolítva a felesleges szóközök
	 */
	public String getNextStringID(int id){
		this.getSplitSzoveg();
		return splitSzoveg.get(id).trim();
	}
	
	/**
	 * Visszaadja a feldarabolt szöveget. Ha a szöveg még nem volt feldarabolva, akkor
	 * feldarabolja a szöveget.
	 * @return visszatér a feldarabolt stringel.
	 */
	public ArrayList<String> getSplitSzoveg(){
		if(splitSzoveg == null){
			this.splitText();
		}
		return splitSzoveg;
	}
	
	/**
	 * Feldarabolja a felhasználó által megadott stringet.
	 * @return visszatér egy ArrayListel amiben a feldarabolt szöveg szerepel.
	 */
	protected ArrayList<String> splitText(){
		splitSzoveg = new ArrayList<String>();
		String split[] = this.getString().split("\n");
		for(String i : split){
			splitSzoveg.add(i);
		}
		return splitSzoveg;
	}
	/**
	 * Visszaadja a string feldarabolt magasságát.
	 * @return a feldrabolt string magassága
	 */
	public int getStringHeightSize(){
		return splitText().size();
	}
	
	/**
	 * Vissszaaja a paraméterben megadott string hosszát.
	 * @param s - a feldarabolt string egyik sora
	 * @return visszatér az adott string hosszával
	 */
	public int getStringWidthSize(String s){
		return s.length();
	}
	
}
