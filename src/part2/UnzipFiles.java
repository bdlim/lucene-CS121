package part2;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class UnzipFiles {
	public static void main(String[] args) throws Exception {
		
		String zippedLocation = "/Users/bdlim/Desktop/pgdvd042010";
		String unzippedLocation = "/Users/bdlim/Desktop/Unzipped";
		
		unzipDirectory(zippedLocation, unzippedLocation);
		
		return;
	}

	public static void unzipDirectory(String directory, String destination) throws IOException{
		File f = new File(directory);
		File[] files = f.listFiles();
		
		File d = new File(destination);
		if (files != null){
			for (int i = 0; i < files.length; i++){
				File file = files[i];
				if (!file.isHidden()){
					if (file.isDirectory()){
						unzipDirectory(file.getPath(), destination);
					} else if (file.toString().toLowerCase().contains(".zip")) {
						unzipFile(file.getPath(), destination);
						System.out.println("UNZIPPING: " + file.toString());
					} else {
						System.out.println("NOT ZIPPED" + file.getPath());
						File dest = new File(d, file.getName());
						Files.move(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
					}
				}
			}
		}
	}
	
	public static void unzipFile(String source, String destination){
		//String source = "some/compressed/file.zip";
		//String destination = "some/destination/folder";
		String password = "password";

		try {
			ZipFile zipFile = new ZipFile(source);
			if (zipFile.isEncrypted()) {
				zipFile.setPassword(password);
			}
			zipFile.extractAll(destination);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
}
