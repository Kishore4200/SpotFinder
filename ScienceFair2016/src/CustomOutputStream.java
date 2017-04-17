import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class CustomOutputStream {
    public static void main(String[] args) throws Exception {

        URL oracle = new URL("https://onedrive.live.com/prev?id=6C78FC80FE16B42%211502&cid=06C78FC80FE16B42&parId=6C78FC80FE16B42%211494&v=TextFileEditor");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
		}
        in.close();
    }
}