package densityCalculator;

import java.util.List;

import org.wallerlab.yoink.domain.Atom;
import org.wallerlab.yoink.domain.Coord;
import org.wallerlab.yoink.domain.Molecule;

public class ImperativeDensityCalculator {
	
	private AtomDensityCalculator atomDensityCalculator = new AtomDensityCalculator();

	
	public Double calculate(Coord currentCoord, List<Molecule> molecules){
		double density = loopOverEveryAtom(currentCoord,molecules);
		density = Math.max(density, 0.0000001);
		return density;
	}
	
	
	private double loopOverEveryAtom(Coord currentCoord, List<Molecule> molecules){
		
		double density = 0;
		for (Molecule molecule : molecules) {
			for (Atom atom : molecule.getAtoms()) {
				density += atomDensityCalculator.calculate(currentCoord, atom);
			}
		}

		return density;
	}
	
	


}
