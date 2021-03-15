import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Year;
public class RegionalPopulationGrowthSimulator {
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		System.out.println("City/Province name: ");
		String cityName = sc.nextLine();
		System.out.println("Year to start at: ");
		int startingYear = sc.nextInt();
		int currentYear = Year.now().getValue();
		int totalYears = currentYear-startingYear;
		System.out.println("Population in year " + startingYear + ": ");
		int startingPopulation = sc.nextInt();
		String toFile = simulator(cityName, totalYears, startingPopulation, startingYear);
		String filename = cityName + " Population Growth " + startingYear + "-" + currentYear + ".txt";
		PrintWriter cityPop = new PrintWriter(filename);
		cityPop.print(toFile);
		System.out.println("Results saved to " + filename);
		cityPop.close();
	}
	public static String simulator(String cityName, int years, int initPop, int initYear) throws InterruptedException {
		String toFile;
		Scanner sc = new Scanner(System.in);
		System.out.println("Average annual growth (0.21 = 21%): ");
		float growth = sc.nextFloat();
		int newPop = 0;
		System.out.println("Estimated population each year since " + (Year.now().getValue()-years) + ": ");
		toFile = "Estimated population each year since " + (Year.now().getValue()-years) + ": ";
		for(int i = 0; i <= years; i++) {
			if(i == 0) {
				System.out.println(initYear + ": " + initPop);
				toFile += "\n" + initYear + ": " + initPop;
				newPop = initPop;
			}
			else {
				newPop += Math.round(newPop*growth);
				System.out.println((i + initYear) + ": " + newPop);
				toFile += "\n" + (i + initYear) + ": " + newPop;
			}
			TimeUnit.MILLISECONDS.sleep(100);
		}
		return toFile;
	}
}