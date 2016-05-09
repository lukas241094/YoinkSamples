package org.wallerlab.yoink.functional;

import org.wallerlab.yoink.domain.Molecule;
import org.wallerlab.yoink.service.AtomAtomDistanceCalculator;

public class AAFunctionalClosestDistanceCalculator {

	private AtomAtomDistanceCalculator calculator = new AtomAtomDistanceCalculator();

	public double calculateClosestDistance(Molecule core, Molecule molecule) {

		return molecule.getAtoms().stream()
					   .flatMapToDouble(atom -> {
											return core.getAtoms().stream()
																  .mapToDouble(coreAtom -> {
																						return calculator.calculateDistance(atom, coreAtom);
						});
					   	})
					   .min()
					   .getAsDouble();

	}

}
