import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Printer {
	private File file;
	private FileWriter writer;
	
	Printer(String dir){
		try {
			this.file = File.createTempFile("flights_data_", ".csv", new File(dir));	
			if (!file.exists()) {
				file.createNewFile();
			}
			this.writer = new FileWriter(file);
		} catch (IOException e) {
			System.out.println("Failed to initialize Printer.");
		}
	}
	
	public void exportFlights(List<Flight> flights) {
		for (Flight flight : flights) {
			exportString(flight.toCsvString());
		}
	}
	
	public void printFlights(List<Flight> flights) {
		for (Flight flight : flights) {
			System.out.println(flight.toText());
		}
	}
	
	public void exportString(String data) {
		try {
			writer.write(data + "\n");
		} catch (IOException e) {
			System.out.println("Failed to write data: " + data);
		}
	}
	
	public void close() {
		try {
			this.writer.close();
		} catch(IOException e) {
			System.out.println("Failed to close writer.");
		}
	}
}
