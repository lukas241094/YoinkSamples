package densityCalculator;


import org.wallerlab.yoink.domain.Atom;
import org.wallerlab.yoink.domain.Coord;
import org.wallerlab.yoink.domain.Element;
import org.wallerlab.yoink.functional.PAFunctionalClosestDistanceCalcualtor;
import org.wallerlab.yoink.service.PointAtomDistanceCalculator;

public class AtomDensityCalculator {
	private PointAtomDistanceCalculator distanceCalculator = new PointAtomDistanceCalculator();
	
	public double calculate(Coord currentCoord,Atom atom){
		double distance = distanceCalculator.calculateDistance(currentCoord, atom);
		Element atomType = atom.getElement();
		double exp1 = Math.exp(-distance / atomType.z1());
		double exp2 = Math.exp(-distance / atomType.z2());
		double exp3 = Math.exp(-distance / atomType.z3());
		double density = atomType.c1() * exp1 + atomType.c2() * exp2
				+ atomType.c3() * exp3;
		return density;
		
	}

}
