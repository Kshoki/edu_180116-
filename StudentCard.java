import java.util.ArrayList;

import java.util.Calendar;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class StudentCard {
	private String id;	//学籍番号
	private String name;	//学生名
	private int accountBalance;	//残高
	
	private Calendar chargedDate;	//チャージした日時
	
	private BufferedImage image = null;	//画像登録用フィールド
	private String text = "";		//自由テキスト登録用フィールド
	
	static public ArrayList<StudentCard> studentCardList_ = new ArrayList<StudentCard>();	//存在する学生証のリスト
	
	StudentCard(String id, String name){
		this.studentCardList_.add(this);
		this.setId(id);
		this.setName(name);
		this.accountBalance = 0;
		this.chargedDate = Calendar.getInstance();
	}
	
	public String getId(){
		return this.id;
	}
	
	public void setId(String id){
		this.id = id;
	}	
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getAccountBalance(){
		return this.accountBalance;
	}
	
	public void setAccountBalance(int value){
		this.accountBalance = value;
	}
	
	public Calendar getChargedDate(){
		return this.chargedDate;
	}
	
	public void setChargedDate(Calendar date){
		this.chargedDate = date;
	}
	
	public BufferedImage getImage(){
		return this.image;
	}
	
	public void setImage(String imgName){
		try{
			BufferedImage readImg = ImageIO.read(new File(imgName));
			this.image = readImg;
		} catch (IOException e) {
			System.out.println("画像を読み込めませんでした");
		}
	}
	
	public String getText(){
		return this.text;
	}
	
	public void setText(String text){
		this.text = text;
	}
}