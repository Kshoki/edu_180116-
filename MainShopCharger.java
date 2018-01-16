public class MainShopCharger {
	public static void main(String[] args){	//main
		ICCharger charger1 = new ICCharger();
		ICCharger charger2 = new ICCharger();	//チャージャーは増やせる
		
		StudentCard studentCard1 = new StudentCard("0001","佐藤太郎");
		StudentCard studentCard2 = new StudentCard("0002","鈴木花子");

		//チャージャー1で処理
		charger1.insertStudentCard(studentCard1);
		charger1.chargeMoney(3000);						//studentCard1に3000円チャージ
		charger1.printAccountBalance();			//studentCard1の残高とチャージ年月日を出力
		charger1.changeText("こんにちは、僕は佐藤です。趣味はサッカーです。よろしくお願いします。");
		charger1.printText();		
		
		//チャージャー2で処理
		charger2.insertStudentCard(studentCard2);
		charger2.chargeMoney(5000);						//studentCard2に5000円チャージ
		charger2.printAccountBalance();			//studentCard2の残高とチャージ年月日を出力
		charger2.changeText("こんにちは、私は鈴木です。好きな食べ物はカレーライスです。よろしくお願いします。");
		charger2.printText();
		
		//チャージャー2で譲渡
		charger2.moveMoney(4000,studentCard1);	//鈴木→佐藤へ4000円譲渡
		//チャージャー1で譲渡
		charger1.moveMoney(1000,studentCard2);	//佐藤→鈴木へ1000円譲渡
	}
}