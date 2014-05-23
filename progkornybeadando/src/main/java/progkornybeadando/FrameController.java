package progkornybeadando;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrameController {
	static String valign = "CENTER";
	static String halign = "CENTER";
	static int height;
	static int width;
	static char jfs = '+',jls = '+',bfs = '+',bls = '+',bal = '|',jobb = '|',felso = '-',also = '-';
	private static Logger logger = LoggerFactory.getLogger(FrameController.class);
	
	/**
	 * Függőleges enum értékek
	 */
	public static enum VerticalAlignment {
        TOP, CENTER, BOTTOM;
    }
	
	/**
	 * Vízszintes enum értékek
	 */
	public static enum HorizontalAlignment {
        LEFT, CENTER, RIGHT;
    }
	
	/**
	 * E metódus meghívásával van lehetőség a keret beállítására.
	 * @param s - a felhasználó által megadott 8 karakter hosszúságú String
	 * @return 
	 * 	<ul>
	 * 		<li>
	 * 			<b>true</b> ha sikerült a beállítás
	 * 		</li>
	 * 		<li>
	 * 			<b>false</b> ha nem sikerült a beállítás
	 * 		</li>
	 * </ul>
	 */
	public static boolean setKeret(String s){
		if(s.length() == 8){
			FrameController.jfs = s.charAt(0);
			FrameController.jls = s.charAt(1);
			FrameController.bls = s.charAt(2);
			FrameController.bfs = s.charAt(3);
			FrameController.felso = s.charAt(4);
			FrameController.bal = s.charAt(5);			
			FrameController.also = s.charAt(6);			
			FrameController.jobb = s.charAt(7);	
			logger.info("Sikeres keret karakter beállítás");
			return true;
		}else{
			logger.error("Sikeretelen keret karakter beállítás");
			return false;
		}
	}

	/**
	 * Ez a metódus végzi el a felhasználó által megadott string pozicionálását és a keret kirajzolását.
	 * @param text - egy Text objektum ami tartalmazza a felhasználó által megadott szöveg adatait.
	 * @return Visszaad egy stringet ami meg van formázva kirajzolásra készen
	 */
	public static String drawFrame(Text text){
		StringBuilder tarolo = new StringBuilder();
		int text_start = FrameController.calculateRowStart(text.getStringHeightSize());
		for(int i=0;i<height;i++){
			if(i==0){
				for(int j=0;j<width;j++){
					if(j==0){
						tarolo.append(FrameController.jfs);
					}
					else if(j == width-1){
						tarolo.append(FrameController.bfs);
					}else{
						tarolo.append(felso);
					}
					
				}
				tarolo.append("\n");
			}
			else if(i==height-1){
				for(int j=0;j<width;j++){
					if(j==0){
						tarolo.append(FrameController.jls);
					}
					else if(j == width-1){
						tarolo.append(FrameController.bls);
					}else{
						tarolo.append(also);
					}
				}
				tarolo.append("\n");
			}
			else if(text_start == i){
				for(int j=0;j<text.getStringHeightSize();j++){
					int start_cols = FrameController.calculateColStart(text.getStringWidthSize(text.getNextStringID(j)));					
					tarolo.append(FrameController.bal);
					for(int k=0;k<FrameController.width-1;k++){
						if(start_cols == k){
							tarolo.append(text.getNextStringID(j));
							k += text.getStringWidthSize(text.getNextStringID(j));
						}else{
							tarolo.append(" ");
						}
					}
					tarolo.append(FrameController.jobb+"\n");
					
				}
				i += (text.getStringHeightSize()-1);
			}else{
				tarolo.append(FrameController.bal);
				for(int k=0;k<FrameController.width-2;k++){
					tarolo.append(" ");
				}
				tarolo.append(FrameController.jobb+"\n");
			}
			
			
		}
		return tarolo.toString();
	}

	/**
	 * Árnyékos keret kirajzolását teszi lehetővé
	 * @param s - a felhasználó által megadott szöveg
	 * @param height - a keret magassága
	 * @param width - a keret szélessége
	 * @param vAlignment - a szöveg függőleges pozicionálása
	 * @param hAlignment - a szöveg vízszintes pozicionálása
	 * @param character - a felhasználó által megadott árnyék kerete
	 * @return visszatér a formázott kerettel és benne a szöveggel + egy kis árnyékkal
	 */
	public static String shieldingFrame(String s, int height, int width,VerticalAlignment vAlignment, HorizontalAlignment hAlignment,char character){
		logger.info("Árnyékos kerettel rajzolás");
		String newstring = FrameController.frame(s, height, width,vAlignment, hAlignment);
		Shielding.setCharacter(character);
		return Shielding.shielding(newstring);
	}

	/**
	 * Kirajzolja a keretet szövegpozicionálással
	 * @param s - a felhasználó által megadott szöveg
	 * @param height - keret magassága
	 * @param width - keret szélessége 
	 * @param vAlignment - függőleges szöveg pozició
	 * @param hAlignment - vízszintes pozició
	 * @return Visszatér a formázott kerettel és benne a szöveggel.
	 */
	public static String frame(String s, int height, int width,VerticalAlignment vAlignment, HorizontalAlignment hAlignment) {
		logger.info("Szöveg pozicionálásos rajzolás");
		FrameController.setTextVerticalAlign(vAlignment.name());
		FrameController.setTextHorizontalAlign(hAlignment.name());
		return showFrame(s,height,width);
	}
	
	/**
	 * Kirajzolja a keretet az alapértelmezett beállításokal.
	 * @param s - a felhasználó által megadott szoveg
	 * @param height - keret magassága
	 * @param width - keret szélessége
	 * @return Visszatér a formázott kerettel és benne a szöveggel.
	 */
	public static String showFrame(String s, int height, int width) {
		logger.info("Sima rajzolás");
		FrameController.height = height;
		FrameController.width = width;
		Text text = new Text();
		text.setString(s);
		return FrameController.drawFrame(text);
	}
	
	/**
	 * Leelenőrzi, hogy a megadott függőleges pozició helyes-e
	 * @param s a felhasználó által megadott függőleges pozició	      
	 * @return 
	 * 	<ul>
	 *    <li>
	 *      <b>true</b> ha helyes a függőleges pozició
	 *    </li>
	 *    <li>
	 *      <b>false</b> ha helytelen a megadott függőleges pozició
	 *    </li>
	 *  </ul>
	 */
	public static boolean isVerticalAlignment(String s){
		try{
			FrameController.VerticalAlignment.valueOf(s);
		}catch(Exception e){
			logger.error("Hibás függőleges enum érték");
			return false;
		}
		return true;
	}
	
	/**
	 * Leelenőrzi, hogy a megadott vízszintes pozició helyes-e
	 * @param s a felhasználó által megadott vízszintes pozició	      
	 * @return 
	 * 	<ul>
	 *    <li>
	 *      <b>true</b> ha helyes a vízszintes pozició
	 *    </li>
	 *    <li>
	 *      <b>false</b> ha helytelen a megadott vízszintes pozició
	 *    </li>
	 *  </ul>
	 */
	public static boolean isHorizontalAlignment(String s){
		try{
			FrameController.HorizontalAlignment.valueOf(s);
		}catch(Exception e){
			logger.error("Hibás vízszintes enum érték");
			return false;
		}
		return true;
	}
	
	/**
	 * Beállítja a felhasználó által megadott függőleges poziciót
	 * @param s a felhasználó által megadott függőleges pozició	      
	 */
	public static void setTextVerticalAlign(String s){
		if(FrameController.isVerticalAlignment(s)){
			FrameController.valign = s;
			logger.info("Sikeres függőleges szöveg pozició beállítás");
		}
			
	}
	/**
	 * Beállítja a felhasználó által megadott vízszintes poziciót
	 * @param s a felhasználó által megadott vízszintes pozició	      
	 */
	public static void setTextHorizontalAlign(String s){
		if(FrameController.isHorizontalAlignment(s)){
			FrameController.halign = s;
			logger.info("Sikeres vízszintes szöveg pozició beállítás");
		}
			
		
	}
	/**
	 * Kiszámítja, hogy az aktuális sorú szövegnek hanyadik karakternél
	 * kell kezdődnie.
	 * @param textWidth a felhasználó által megadott szöveg sortördelés utáni egyik sorának
	 * a szöveg hosszúsága
	 * @return az adott sorban hanyadik karakternél kell kezdődnie a szövegnek	      
	 */
	public static int calculateColStart(int textWidth){
		if(FrameController.halign == "CENTER"){
			return (FrameController.width - textWidth)/2;
		}
		if(FrameController.halign =="RIGHT"){
			return FrameController.width - textWidth-2;
		}
		return 1;
	}
	/**
	 * Kiszámítja, hogy a szövegnek hanyadik sorban kell kezdődnie.
	 * @param textHeight a felhasználó által megadott szöveg magassága
	 * @return egy int-el tér vissza ami megadja, hogy hanyadik sorban kell elkezdeni kirajzolni a szöveget.     
	 */
	public static int calculateRowStart(int textHeight){
		
		if(FrameController.valign == "CENTER"){
			return (FrameController.height - textHeight)/2;
		}
		if(FrameController.valign =="BOTTOM"){
			return FrameController.height - textHeight-1;
		}
		return 1;
	}
}
