import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class executeShellCommand {

	public static void main(String[] args) throws IOException, InterruptedException {

		executeShellCommand obj = new executeShellCommand();

		String domainName = "google.com";
	
		//in windows
		String ping = "ping -n 3 " + domainName;
		String pwd = "pwd";
		//String ngrok = "/cygdrive/c/Kishore/Grade9(2015-2016)/ScienceFair/ngrok.bash";
		String ngrok = "ngrok";
		
		ProcessBuilder pb = new ProcessBuilder(pwd);
		Process p = pb.start();
		p.waitFor();
		//PrintStream printStream = new PrintStream(p.getOutputStream());
		System.out.println("> " + new StringBuffer(p.getInputStream().toString()));
		StringBuffer str = new StringBuffer();
		str.append(new BufferedReader(new InputStreamReader(p.getInputStream())).toString() + "");
		System.out.println("> " + str);
		/*printStream.println();
		printStream.flush();
		printStream.close();*/
		//System.out.println(p.getOutputStream());
		
		String output = obj.executeCommand(pwd);
		System.out.println(">> " + output);
		System.out.println(">> " + output);
		output = obj.executeCommand(ping);
		System.out.println(">> " + output);
		System.out.println("DONE!");

	}

	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

}