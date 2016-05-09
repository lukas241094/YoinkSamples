package org.wallerlab.yoink.functional;

import org.wallerlab.yoink.domain.Coord;
import org.wallerlab.yoink.domain.Molecule;
import org.wallerlab.yoink.service.PointAtomDistanceCalculator;

public class PAFunctionalClosestDistanceCalcualtor {
	
	private PointAtomDistanceCalculator calculator = new PointAtomDistanceCalculator();

	public double calculateClosestDistance(Coord core, Molecule molecule) {

		return molecule.getAtoms().stream()
								  .mapToDouble(atom -> calculator.calculateDistance(core, atom))
								  .min()
								  .getAsDouble();


	}

}
