package Factory;

import GUI.JWindow;
import org.dreambot.api.wrappers.widgets.message.Message;
import prayer.MainClass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AnswareMessage {
    private MainClass mainClass;
    private Factory _factory;
    public AnswareMessage(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }
    public void Answer(Message message, JWindow window){
        if(!window.isTalker()){
            if(PlayerIsExist(message.getUsername()) && !(message.getUsername().equals(mainClass.getLocalPlayer().getName()))){
                mainClass.getKeyboard().type(ResponseCutter(SendPostMessage(message.getMessage())));
            }
        }
    }
    private boolean PlayerIsExist(String message){
        for(int i = 0; i < mainClass.getPlayers().all().size(); i++){
            if(message.contains(mainClass.getPlayers().all().get(i).getName())){
                return true;
            }
        }
        return false;
    }
    private String SendPostMessage(String msg){
        String rawData = "ENTRY=" + msg + "";
        String type = "application/x-www-form-urlencoded";
        String Response = "";
        try {
            String encodedData = URLEncoder.encode( rawData, "UTF-8" );
            URL u = new URL("http://elbot-e.artificial-solutions.com/cgi-bin/elbot.cgi");
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty( "Content-Type", type );
            conn.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()));
            OutputStream os = conn.getOutputStream();
            os.write(encodedData.getBytes());
            BufferedReader br = null;
            if (conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    Response += strCurrentLine;
                }
            }
            return Response;
        } catch (java.io.IOException e) {
            mainClass.log("" + e.toString());
        }
        return Response;
    }
    private String ResponseCutter(String response){
        response = response.replaceAll("<!-- Country:   -->", "");
        String Message = "";
        for(int i = response.indexOf("<!-- Begin Response !-->"); i < response.indexOf("<!-- End Response !-->"); i++){
            Message += response.charAt(i);
        }
        return Message.replace('"', ' ').replace("<!-- Begin Response !-->", "");
    }
}
