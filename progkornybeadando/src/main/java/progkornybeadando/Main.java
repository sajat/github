package progkornybeadando;

public class Main {

	public static void main(String[] args) {
		//FrameController.setTextHorizontalAlign("LEFT");
		//FrameController.setTextVerticalAlign("BOTTOM");
		//FrameController.setKeret("*/*-abcd");
		//System.out.println(FrameController.showFrame("Ez itt a szoveg\n ez meg egy új sor\nez meg egy újabb \n de mostmár elég",10,30));
		String s = "Ez itt a szoveg\n ez meg egy új sor\nez meg egy újabb \n de mostmár elég";
		System.out.println(FrameController.shieldingFrame(s,10,30,FrameController.VerticalAlignment.CENTER,FrameController.HorizontalAlignment.CENTER,'c'));
	}

}
