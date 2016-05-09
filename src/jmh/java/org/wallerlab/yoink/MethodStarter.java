package org.wallerlab.yoink;

import static org.wallerlab.yoink.domain.Region.BUFFERZONE;
import static org.wallerlab.yoink.domain.Region.MM;
import static org.wallerlab.yoink.domain.Region.QMZONE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.wallerlab.yoink.domain.Coord;
import org.wallerlab.yoink.domain.Molecule;
import org.wallerlab.yoink.domain.Region;
import org.wallerlab.yoink.functional.AAFunctionalClosestDistanceCalculator;
import org.wallerlab.yoink.functional.PAFunctionalClosestDistanceCalcualtor;
import org.wallerlab.yoink.imperative.AAImperativeClosestDistanceCalculator;
import org.wallerlab.yoink.imperative.PAImperativeClosestDistanceCalculator;

import densityCalculator.FunctionalDensityCalculator;
import densityCalculator.ImperativeDensityCalculator;


public class MethodStarter {
	
	PAImperativeClosestDistanceCalculator imperativeCalculator = new PAImperativeClosestDistanceCalculator();
	PAFunctionalClosestDistanceCalcualtor functionalCalculator = new PAFunctionalClosestDistanceCalcualtor();
	FunctionalDensityCalculator functionalDensityCalculator = new FunctionalDensityCalculator();
	ImperativeDensityCalculator imperativeDensityCalculator = new ImperativeDensityCalculator();
	
	public Map<Region, List<Molecule>> calculateImperative(List<Molecule> molecules,double qmThreshold, double bufferThreshold,Coord core){

		Map<Region, List<Molecule>> regions = new HashMap<>();
		List<Molecule> qmRegion = new ArrayList();
		List<Molecule> bufferRegion = new ArrayList();
		List<Molecule> mmRegion = new ArrayList();
		for (Molecule molecule : molecules){
			
			double distance = imperativeCalculator.calculateClosestDistance(core, molecule);
			if(distance < qmThreshold){
				qmRegion.add(molecule);
			}else if(distance < bufferThreshold){
				bufferRegion.add(molecule);
			}else mmRegion.add(molecule);
		}
		
		regions.put(Region.QMZONE, qmRegion);
		regions.put(Region.BUFFERZONE, bufferRegion);
		regions.put(Region.MM, mmRegion);
		return regions;
	}
	
	
	public Map<Region, List<Molecule>> calculateStreamSerial(List<Molecule> molecules,double qmThreshold, double bufferThreshold,Coord core){

		Map<Region, List<Molecule>> regions =
		molecules.stream()
				// .parallel()
				 .collect(
						 Collectors.groupingBy(molecule -> {
							 double distance = functionalCalculator.calculateClosestDistance(core, molecule);
							 if (distance < qmThreshold) return QMZONE;
							 	else if (distance > bufferThreshold) return MM;
							 		else return BUFFERZONE;
						 }));
		return regions;
	}
	
	
	public Map<Region, List<Molecule>> calculateStreamParallelFunctionalCDC(List<Molecule> molecules,double qmThreshold, double bufferThreshold,Coord core){
		
		Map<Region, List<Molecule>> regions =
		molecules.stream()
				 .parallel()
				 .collect(
						 Collectors.groupingBy(molecule -> {
							 double distance = functionalCalculator.calculateClosestDistance(core, molecule);
							 if (distance < qmThreshold) return QMZONE;
							 	else if (distance > bufferThreshold) return MM;
							 		else return BUFFERZONE;
						 }));
		return regions;
	}

	public Map<Region, List<Molecule>> calculateStreamPrallelImpCDC(List<Molecule> molecules,double qmThreshold, double bufferThreshold,Coord core){


		Map<Region, List<Molecule>> regions =
		molecules.stream()
				 .parallel()
				 .collect(
						 Collectors.groupingBy(molecule -> {
							 double distance = imperativeCalculator.calculateClosestDistance(core, molecule);
							 if (distance < qmThreshold) return QMZONE;
							 	else if (distance > bufferThreshold) return MM;
							 		else return BUFFERZONE;
						 }));
		return regions;
		
	}
	
	public Map<Region, List<Molecule>> calculateStreamParallelstream(List<Molecule> molecules,double qmThreshold, double bufferThreshold,Coord core){
		
		Map<Region, List<Molecule>> regions =
		molecules.parallelStream()
				 .collect(
						 Collectors.groupingBy(molecule -> {
							 double distance = functionalCalculator.calculateClosestDistance(core, molecule);
							 if (distance < qmThreshold) return QMZONE;
							 	else if (distance > bufferThreshold) return MM;
							 		else return BUFFERZONE;
						 }));
		return regions;
	}
	
	
	public double calculateDensityStream(List<Molecule> molecules, Coord currentCoord){
		
		return functionalDensityCalculator.calculate(currentCoord, molecules);
		
	}
	
	public double calculateDensityStreamParallel(List<Molecule> molecules, Coord currentCoord){
		
		return functionalDensityCalculator.calculateParallel(currentCoord, molecules);
		
	}
	
	public double calculateDensityImperative(List<Molecule> molecules, Coord currentCoord){
		
		return imperativeDensityCalculator.calculate(currentCoord, molecules);
		
	}
	


}
