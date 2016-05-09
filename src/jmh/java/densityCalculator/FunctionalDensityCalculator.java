package densityCalculator;

import java.util.List;

import org.wallerlab.yoink.domain.Coord;
import org.wallerlab.yoink.domain.Molecule;


public class FunctionalDensityCalculator {
	
	private AtomDensityCalculator atomDensityCalculator = new AtomDensityCalculator();
	
	
	public Double calculate(Coord currentCoord, List<Molecule> molecules){
		double density = loopOverEveryAtom(currentCoord,molecules);
		density = Math.max(density, 0.0000001);
		return density;
	}
	
	public Double calculateParallel(Coord currentCoord, List<Molecule> molecules){
		double density = loopOverEveryAtomParallel(currentCoord,molecules);
		density = Math.max(density, 0.0000001);
		return density;
	}
	
	
	private double loopOverEveryAtom(Coord currentCoord, List<Molecule> molecules){
		
		return molecules.stream()
						.flatMap(molecule -> molecule.getAtoms().stream())
						.mapToDouble(atom -> atomDensityCalculator.calculate(currentCoord, atom))
						.sum();
	}
	
	private double loopOverEveryAtomParallel(Coord currentCoord, List<Molecule> molecules){
		
		return molecules.parallelStream()
						.flatMap(molecule -> molecule.getAtoms().stream())
						.mapToDouble(atom -> atomDensityCalculator.calculate(currentCoord, atom))
						.sum();
	}
	
	
	

}
