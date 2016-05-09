package densityCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.wallerlab.yoink.domain.Coord;
import org.wallerlab.yoink.domain.Molecule;

public class TestMain {

	public static void main(String[] args) {
		

		System.out.println("Hello");
		List<Molecule> molecules =
				IntStream.range(0, 5_000_000)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,10))
						 .collect(Collectors.toList());
		System.out.println("List Done");
		
		Coord currentCoord = new Coord(0.0,0.0,0.0);
		
		FunctionalDensityCalculator functionalDensityCalculator = new FunctionalDensityCalculator();
		
		long one = System.nanoTime();
		double density = functionalDensityCalculator.calculate(currentCoord, molecules);
		long two = System.nanoTime();
		double duration = (two - one)/1000_000_000.0;
		
		System.out.println("duration in s "+ duration);

	}

}
