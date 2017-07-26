package SearchEngine;
import java.io.*;
import java.io.IOException;
import java.net.*;
import java.util.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Crawler {

	public static void main(String[] args) {
                                                                                                    //List of words that needs to be skipped or muted while adding 
		                                                                       //wordsor text from webpage to the trie structure
		Set<String> stopWordsSet = new HashSet<String>();
		                                                                           // List to store webpages name
		ArrayList<String> Occurences= new ArrayList<String>();
		Trie d= new Trie ();
		String[] findword = null;
		stopWordsSet.add("else");
		stopWordsSet.add("this");
		stopWordsSet.add("and");
		stopWordsSet.add("there's");
		stopWordsSet.add("otherwise");
		stopWordsSet.add("but");
		stopWordsSet.add("you");
		stopWordsSet.add("are");
		
		Occurences.add(0, "http://www.pcmag/tech/207013.asp");
		Occurences.add(1, "http://www.pcmag/298106.asp");
		Occurences.add(2, "http://www.cnet/wireless");
		Occurences.add(3, "http://www.hubpages/soulful");

		try {

			Iterator itr = Occurences.iterator();
			while(itr.hasNext()) {
				Object element = itr.next();
				int index=  Occurences.indexOf(element);
				URL url = new URL(element.toString());
				HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
				String line = null;
				StringBuilder tmp = new StringBuilder();
				BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				while ((line = in.readLine()) != null) {
					tmp.append(line);
				}
               //Parse html to get information within <BODY> tag
				Document doc = Jsoup.parse(tmp.toString());
				String text = doc.body().text(); 
				System.out.println("Webpage Data:-\n"+text);
				String[] words = text.split(" ");
				int count=0;
				for (String word : text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"))
				{
					int len= word.length();
					if( len>2 && !stopWordsSet.contains(word)  )
					{  
						d.insert(word, index);
						count++;

					}
				} }
			//Take the keywords to be searched from the User
			System.out.println("Enter the word you wish to search::");
			Scanner scanner = new Scanner(System.in);
			String myLine = scanner.nextLine();
			findword = myLine.split(" ");
			ArrayList<Integer> AL= new ArrayList<Integer>();
			ArrayList<String> Webpages = new ArrayList<String>();
			try{
				AL=d.ListWebpages(findword);
				Iterator<Integer> ALItr = AL.iterator();
				while(ALItr.hasNext()) {
					Webpages.add(Occurences.get(ALItr.next()));
				}
				Webpages.stream().forEach(System.out::println);
			}
			catch (NullPointerException e) {
				System.out.print("Not found in any of the Webpages");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
