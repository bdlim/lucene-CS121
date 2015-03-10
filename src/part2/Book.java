package part2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Book {
	
	private String title = "";
	private String author = "";
	private String releaseDate = "";
	private String language = "";
	
	public Book() {	
	}
	
	void parse(FileInputStream fis) throws IOException {
		BufferedReader breader =new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
		
		// read only the first 50 lines of the file
		int linesToRead = 50;
		
		for (int i=0; i < linesToRead; i++) {
			String line = breader.readLine();
			
			if(line == null) break;
			
			if (line.startsWith("Title:") && line.length() > 7) {
				title = line.substring(7);
			}
			else if (line.startsWith("Author:") && line.length() > 8) {
				author = line.substring(8);
			}
			else if (line.startsWith("Release Date:") && line.length() > 14) {
				releaseDate = line.substring(14);

			}
			else if (line.startsWith("Language:") && line.length() > 10) {
				language = line.substring(10);
			}
		}
		
	}
	
	public String getAuthor() { return author; }
	public String getTitle() { return title;	}
	public String getReleaseDate() { return releaseDate;	}
	public String getLanguage() { return language;	}
}
