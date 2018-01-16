import java.util.Calendar;

class ICCharger {
	private StudentCard insertedStudentCard1;	//挿入した学生証
	private StudentCard insertedStudentCard2;	//残高移行用の挿入学生証
	
	public void insertStudentCard(StudentCard card){	//学生証の挿入
		this.insertedStudentCard1 = card;
	}	
	
	public void chargeMoney(int value) {	//残高への追加
		if(this.insertedStudentCard1 != null){
			this.insertedStudentCard1.setAccountBalance(this.insertedStudentCard1.getAccountBalance()+value);
			this.insertedStudentCard1.setChargedDate(Calendar.getInstance());	//チャージした日時を更新する
			System.out.println(value + "円チャージしました");
		} else {
			System.out.println("学生証が挿入されていません");	//エラーメッセージ出力
		}
	}
	
	public void moveMoney(int value, StudentCard card) {		//残高の移行（学生証1→学生証2）
		this.insertedStudentCard2 = card;
		if(this.insertedStudentCard1 != null){
			if(this.insertedStudentCard1.getAccountBalance() > value){
				int balance = this.insertedStudentCard1.getAccountBalance()-value;
				this.insertedStudentCard1.setAccountBalance(balance);
				this.insertedStudentCard2.setAccountBalance(this.insertedStudentCard2.getAccountBalance()+value);
				System.out.println(this.insertedStudentCard1.getName()+"の学生証から"+this.insertedStudentCard2.getName()+"の学生証へ"+value + "円移しました");
				
				//移動元の学生証
				System.out.println("移動元の学生証");
				System.out.println("学籍番号："+insertedStudentCard1.getId());			//学籍番号出力
				System.out.println("学生名："+ insertedStudentCard1.getName());			//学生名出力
				System.out.println("残高："+ insertedStudentCard1.getAccountBalance() + "円");			//残高出力
				//移動先の学生証
				System.out.println("移動先の学生証");
				System.out.println("学籍番号："+insertedStudentCard2.getId());			//学籍番号出力
				System.out.println("学生名："+ insertedStudentCard2.getName());			//学生名出力
				System.out.println("残高："+ insertedStudentCard2.getAccountBalance() + "円");			//残高出力	
				System.out.println("");
			}else{
				System.out.println("残高が足りません");	//エラーメッセージ出力
			}
		} else {
			System.out.println("学生証が挿入されていません");	//エラーメッセージ出力
		}
	}
	
	
	public void printAccountBalance(){		//残高の表示
		String insertedCardId = insertedStudentCard1.getId();
		String insertedCardName = insertedStudentCard1.getName();
		int insertedCardBalance = insertedStudentCard1.getAccountBalance();
		Calendar insertedCardChargedDate = insertedStudentCard1.getChargedDate();
		String strInsertedCardChargedDate = ""+String.format("%04d",insertedCardChargedDate.get(Calendar.YEAR))	//年
                          					+"/"+String.format("%02d",insertedCardChargedDate.get(Calendar.MONTH)+1) //月 0から始まるので1足す
                          					+"/"+String.format("%02d",insertedCardChargedDate.get(Calendar.DATE));	//日
		
		System.out.println("学籍番号："+insertedCardId);			//学籍番号出力
		System.out.println("学生名："+ insertedCardName);			//学生名出力
		System.out.println("残高："+ insertedCardBalance + "円");			//残高出力
		System.out.println("最新チャージ年月日："+ strInsertedCardChargedDate);	//最新チャージ年月日を出力
		System.out.println("");
	}	
	
	public void printText(){
		String insertedCardId = insertedStudentCard1.getId();
		String insertedCardName = insertedStudentCard1.getName();
		System.out.println("学籍番号："+insertedCardId);			//学籍番号出力
		System.out.println("学生名："+ insertedCardName);			//学生名出力
		
		String text = insertedStudentCard1.getText();
		
		System.out.println("自由テキスト："+text);
		System.out.print("キーフレーズ：");
		
		String[] keyPhrases = API.getKeyPhrases(text);
		StringBuilder kp = new StringBuilder();
		
		int phrasesNum = Math.min(keyPhrases.length, 3);	//キーフレーズの上限を3個に設定
		for(int i=0; i<phrasesNum ;i++){
			kp.append(keyPhrases[i]);
			if(i < phrasesNum-1) {
				kp.append(", ");
			}
		}
		System.out.println(new String(kp));
		System.out.println();
	}
	
	public void changeText(String text){
		insertedStudentCard1.setText(text);
	}
}