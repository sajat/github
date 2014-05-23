package progkornybeadando;

public class Shielding {
	static char character = '#';
	
	/**
	 * Beállítja a keretre az árnyékot
	 * @param s a formázott keret szöveggel együtt.
	 * @return a megkapott formázott string + az árnyék hozzá.
	 */
	public static String shielding(String s){
		StringBuilder tarolo = new StringBuilder();
		Text newtext = new Text();
		int utolso_elem = 0;
		newtext.setString(s);
		for(int i=0;i<newtext.getStringHeightSize();i++){
			tarolo.append(newtext.getNextStringID(i)+Shielding.getCharacter()+"\n");
			utolso_elem = newtext.getNextStringID(i).length();
		}
		for(int j=0;j<2;j++){
			utolso_elem -= 1;
			for(int i=0;i<utolso_elem;i++){
				tarolo.append(Shielding.getCharacter());
			}
			tarolo.append("\n");
		}
		tarolo.append("");
		return tarolo.toString();
	}
	
	/**
	 * Visszaadja a beállított árnyékkaraktert.
	 * @return árnyékoló karakter
	 */
	static char getCharacter(){
		return Shielding.character;
	}
	/**
	 * Beállítja az árnyékoló karaktert
	 * @param c a felhasználó által megadott árnyékoló karakter.
	 */
	static void setCharacter(char c){
		Shielding.character = c;
	}
}
