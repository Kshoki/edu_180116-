import java.io.*;
import java.net.*;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;

import org.json.*;

class API {
	private static String requestURL = "https://westcentralus.api.cognitive.microsoft.com";
	private static String keyPhrasesURL = "/text/analytics/v2.0/keyPhrases";
	
	//APIキー
	private static String accessKey = "beec94d80fd44842a3b90879d63d1e66";
	
	//Singleton インスタンスの作成ダメ
	private API(){}
	
	public static JSONObject callKeyPhrasesAPI(JSONObject request) throws Exception {
        String text = request.toString();
        byte[] encoded_text = text.getBytes("UTF-8");

        URL url = new URL(requestURL+keyPhrasesURL);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/json");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", accessKey);
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.write(encoded_text, 0, encoded_text.length);
        wr.flush();
        wr.close();

        StringBuilder response = new StringBuilder ();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return new JSONObject(response.toString());
    }

	//キーフレーズ取得
	public static String[] getKeyPhrases(String text){
		try{
			JSONObject request = createRequestJson(text);
			JSONObject response = callKeyPhrasesAPI(request); //APIを呼ぶ
			ArrayList<String> keyPhrasesList = new ArrayList<String>();
			
			JSONArray jsonKeyPhrasesArray = response.getJSONArray("documents").getJSONObject(0).getJSONArray("keyPhrases");
			for(int i=0; i<jsonKeyPhrasesArray.length(); i++){
				keyPhrasesList.add(jsonKeyPhrasesArray.getString(i));	//キーフレーズを格納
			}
			return keyPhrasesList.toArray(new String[0]);
		} catch(Exception e) {
			System.out.println("エラーです");
			String[] emptyArray = {};
			return emptyArray;
		}
	}
	
	//JSONの作成
	private static JSONObject createRequestJson(String text) throws JSONException {
		JSONObject base = new JSONObject();
		JSONArray documentsArray = new JSONArray();
		base.put("documents",documentsArray);

		JSONObject document = new JSONObject();
		document.put("language","ja");
		document.put("id","1");
		document.put("text",text);
		documentsArray.put(document);
		
		return base;
	}
}