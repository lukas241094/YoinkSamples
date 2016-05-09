package org.wallerlab.yoink;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.wallerlab.yoink.domain.Coord;
import org.wallerlab.yoink.domain.Molecule;


@State(Scope.Thread)
public class SetOfBenchmarks {

	double qmThreshold;
	double bufferThreshold;
	
	int numberOfMoleculesS = 50;
	int numberOfMoleculesM = 5_000;
	int numberOfMoleculesL = 500_000;
	int numberOfAtomsS = 3;
	int numberOfAtomsM = 5;
	int numberOfAtomsL = 10;
	int numberOfAtomsXL = 20;
	int numberOfAtomsXXL = 50;
	
	
	List<Molecule> moleculesMsAs;
	List<Molecule> moleculesMmAs;
	List<Molecule> moleculesMlAs;
	
	List<Molecule> moleculesMsAm;
	List<Molecule> moleculesMmAm;
	List<Molecule> moleculesMlAm;
	
	List<Molecule> moleculesMsAl;
	List<Molecule> moleculesMmAl;
	List<Molecule> moleculesMlAl;
	
	List<Molecule> moleculesMsAxl;
	List<Molecule> moleculesMmAxl;
	List<Molecule> moleculesMlAxl;
	
	List<Molecule> moleculesMsAxxl;
	List<Molecule> moleculesMmAxxl;
	List<Molecule> moleculesMlAxxl;
	
	Coord core = new Coord(0.0,0.0,0.0);
	
	MethodStarter starter;
	

	@Setup
	public void setup() {
		qmThreshold = 2.0;
		bufferThreshold = 5.0;
		System.out.println("setting up molecules");
		
	
		moleculesMsAs = 
				IntStream.range(0,numberOfMoleculesS)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsS))
						 .collect(Collectors.toList());
		
		moleculesMmAs = 
				IntStream.range(0,numberOfMoleculesM)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsS))
						 .collect(Collectors.toList());
		
		moleculesMlAs = 
				IntStream.range(0,numberOfMoleculesL)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsS))
						 .collect(Collectors.toList());
		
		moleculesMsAm = 
				IntStream.range(0,numberOfMoleculesS)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsM))
						 .collect(Collectors.toList());
		
		moleculesMmAm = 
				IntStream.range(0,numberOfMoleculesM)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsM))
						 .collect(Collectors.toList());
		
		moleculesMlAm = 
				IntStream.range(0,numberOfMoleculesL)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsM))
						 .collect(Collectors.toList());
		
		moleculesMsAl = 
				IntStream.range(0,numberOfMoleculesS)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsL))
						 .collect(Collectors.toList());
		
		moleculesMmAl = 
				IntStream.range(0,numberOfMoleculesM)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsL))
						 .collect(Collectors.toList());
		
		moleculesMlAl = 
				IntStream.range(0,numberOfMoleculesL)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsL))
						 .collect(Collectors.toList());
		moleculesMsAxl =
				IntStream.range(0, numberOfMoleculesS)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsXL))
						 .collect(Collectors.toList());
		
		moleculesMsAxxl =
				IntStream.range(0, numberOfMoleculesS)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsXXL))
						 .collect(Collectors.toList());
		moleculesMmAxl =
				IntStream.range(0, numberOfMoleculesM)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsXL))
						 .collect(Collectors.toList());
		
		moleculesMmAxxl =
				IntStream.range(0, numberOfMoleculesM)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsXXL))
						 .collect(Collectors.toList());
		
		moleculesMlAxl =
				IntStream.range(0, numberOfMoleculesL)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsXL))
						 .collect(Collectors.toList());
		
		moleculesMlAxxl =
				IntStream.range(0, numberOfMoleculesL)
						 .parallel()
						 .mapToObj(i -> new Molecule(i,numberOfAtomsXXL))
						 .collect(Collectors.toList());

		starter = new MethodStarter();

	}
	
	
//	//Molecules small atoms small
//
//	@Benchmark
//	public void imperativeMsAs(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMsmallAsmall, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMsAs(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMsmallAsmall, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelMsAs(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelFunctionalCDC(moleculesMsmallAsmall, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelImperativeCDCMsAs(Blackhole bh) {
//		bh.consume(starter.calculateStreamPrallelImpCDC(moleculesMsmallAsmall, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelstreamMsAs(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMsmallAsmall, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	
//	//molecules medium atoms small
//	
//	@Benchmark
//	public void imperativeMmAs(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMmediumAsmall, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMmAs(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMmediumAsmall, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelMmAs(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelFunctionalCDC(moleculesMmediumAsmall, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelImperativeCDCMmAs(Blackhole bh) {
//		bh.consume(starter.calculateStreamPrallelImpCDC(moleculesMmediumAsmall, qmThreshold, bufferThreshold,core));
//	}
//	@Benchmark
//	public void functionalParallelstreamMmAs(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMmediumAsmall, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	
//	//molecules large atoms small
//	
//	
//	
//	@Benchmark
//	public void imperativeMlAs(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMlargeAsmall, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMlAs(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMlargeAsmall, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelMlAs(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelFunctionalCDC(moleculesMlargeAsmall, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelImperativeCDCMlAs(Blackhole bh) {
//		bh.consume(starter.calculateStreamPrallelImpCDC(moleculesMlargeAsmall, qmThreshold, bufferThreshold,core));
//	}
//	@Benchmark
//	public void functionalParallelstreamMlAs(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMlargeAsmall, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	
//	//molecules small atoms medium
//	
//	
//	
//	@Benchmark
//	public void imperativeMsAm(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMsmallAmedium, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMsAm(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMsmallAmedium, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelMsAm(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelFunctionalCDC(moleculesMsmallAmedium, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelImperativeCDCMsAm(Blackhole bh) {
//		bh.consume(starter.calculateStreamPrallelImpCDC(moleculesMsmallAmedium, qmThreshold, bufferThreshold,core));
//	}
//	@Benchmark
//	public void functionalParallelstreamMsAm(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMsmallAmedium, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	
//	//molecules medium atoms medium
//	
//	
//	@Benchmark
//	public void imperativeMmAm(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMmediumAmedium, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMmAm(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMmediumAmedium, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelMmAm(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelFunctionalCDC(moleculesMmediumAmedium, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelImperativeCDCMmAm(Blackhole bh) {
//		bh.consume(starter.calculateStreamPrallelImpCDC(moleculesMmediumAmedium, qmThreshold, bufferThreshold,core));
//	}
//	@Benchmark
//	public void functionalParallelstreamMmAm(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMmediumAmedium, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	
//	//molecules large atoms medium
//	
//	@Benchmark
//	public void imperativeMlAm(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMlargeAmedium, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMlAm(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMlargeAmedium, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelMlAm(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelFunctionalCDC(moleculesMlargeAmedium, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelImperativeCDCMlAm(Blackhole bh) {
//		bh.consume(starter.calculateStreamPrallelImpCDC(moleculesMlargeAmedium, qmThreshold, bufferThreshold,core));
//	}
//	@Benchmark
//	public void functionalParallelstreamMlAm(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMlargeAmedium, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	
//	//molecules small atoms large
//	
//	
//	
//	@Benchmark
//	public void imperativeMsAl(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMsmallAlarge, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMsAl(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMsmallAlarge, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelMsAl(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelFunctionalCDC(moleculesMsmallAlarge, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelImperativeCDCMsAl(Blackhole bh) {
//		bh.consume(starter.calculateStreamPrallelImpCDC(moleculesMsmallAlarge, qmThreshold, bufferThreshold,core));
//	}
//	@Benchmark
//	public void functionalParallelstreamMsAl(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMsmallAlarge, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	//molecules medium atoms large
//	
//	@Benchmark
//	public void imperativeMmAl(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMmediumAlarge, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMmAl(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMmediumAlarge, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelMmAl(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelFunctionalCDC(moleculesMmediumAlarge, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelImperativeCDCMmAl(Blackhole bh) {
//		bh.consume(starter.calculateStreamPrallelImpCDC(moleculesMmediumAlarge, qmThreshold, bufferThreshold,core));
//	}
//	@Benchmark
//	public void functionalParallelstreamMmAl(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMmediumAlarge, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	//molecules large atoms large
//	
//	@Benchmark
//	public void imperativeMlAl(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMlargeAlarge, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMlAl(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMlargeAlarge, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelMlAl(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelFunctionalCDC(moleculesMlargeAlarge, qmThreshold, bufferThreshold,core));
//	}
//	
//	@Benchmark
//	public void functionalParallelImperativeCDCMlAl(Blackhole bh) {
//		bh.consume(starter.calculateStreamPrallelImpCDC(moleculesMlargeAlarge, qmThreshold, bufferThreshold,core));
//	}
//	@Benchmark
//	public void functionalParallelstreamMlAl(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMlargeAlarge, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	//molecules small atoms very large
//	
//	@Benchmark
//	public void imperativeMsAxl(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMsmallAxl, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMsAxl(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMsmallAxl, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalParallelMsAxl(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMsmallAxl, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	
//	
//	//molecules small atoms very very large
//	
//	@Benchmark
//	public void imperativeMsAxxl(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMsmallAxxl, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMsAxxl(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMsmallAxxl, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalParallelMsAxxl(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMsmallAxxl, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	
//	//molecules medium atoms very large
//	
//	@Benchmark
//	public void imperativeMmAxl(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMmediumAxl, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMmAxl(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMmediumAxl, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalParallelMmAxl(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMmediumAxl, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	
//	
//	//molecules meidum atoms very very large
//	
//	@Benchmark
//	public void imperativeMmAxxl(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMmediumAxxl, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMmAxxl(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMmediumAxxl, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalParallelMmAxxl(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMmediumAxxl, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	
//	//molecules large atoms very large
//	
//	@Benchmark
//	public void imperativeMlAxl(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMlargeAxl, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMlAxl(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMlargeAxl, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalParallelMlAxl(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMlargeAxl, qmThreshold, bufferThreshold,core));
//	}
//	
//	
//	
//	
//	//molecules large atoms very very large
//	
//	@Benchmark
//	public void imperativeMlAxxl(Blackhole bh) {
//		bh.consume(starter.calculateImperative(moleculesMlargeAxxl, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalMlAxxl(Blackhole bh) {
//		bh.consume(starter.calculateStreamSerial(moleculesMlargeAxxl, qmThreshold, bufferThreshold,core));
//	}
//
//	@Benchmark
//	public void functionalParallelMlAxxl(Blackhole bh) {
//		bh.consume(starter.calculateStreamParallelstream(moleculesMlargeAxxl, qmThreshold, bufferThreshold,core));
//	}
	
	
	//Benchmarks for DensCalc Molecules small Atoms small
	
	@Benchmark
	public void densityCalcImperativeMsAs(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMsAs, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMsAs(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMsAs, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMsAs(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMsAs, core));
	}
	
	
	//Benchmarks for DensCalc Molecules medium Atoms small
	
	@Benchmark
	public void densityCalcImperativeMmAs(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMmAs, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMmAs(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMmAs, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMmAs(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMmAs, core));
	}
	
	
	
	
	//Benchmarks for DensCalc Molecules large Atoms small
	
	@Benchmark
	public void densityCalcImperativeMlAs(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMlAs, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMlAs(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMlAs, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMlAs(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMlAs, core));
	}
	
	
	
	
	
	//Benchmarks for DensCalc Molecules small Atoms medium
	
	@Benchmark
	public void densityCalcImperativeMsAm(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMsAm, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMsAm(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMsAm, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMsAm(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMsAm, core));
	}
	
	
	
	//Benchmarks for DensCalc Molecules medium Atoms medium
	
	@Benchmark
	public void densityCalcImperativeMmAm(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMmAm, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMmAm(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMmAm, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMmAm(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMmAm, core));
	}
	
	
	//Benchmarks for DensCalc Molecules large Atoms medium
	
	@Benchmark
	public void densityCalcImperativeMlAm(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMlAm, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMlAm(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMlAm, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMlAm(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMlAm, core));
	}
	
	
	
	
	
	//Benchmarks for DensCalc Molecules small Atoms large
	
	@Benchmark
	public void densityCalcImperativeMsAl(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMsAl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMsAl(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMsAl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMsAl(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMsAl, core));
	}
	
	
	
	
	
	//Benchmarks for DensCalc Molecules medium Atoms large
	
	@Benchmark
	public void densityCalcImperativeMmAl(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMmAl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMmAl(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMmAl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMmAl(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMmAl, core));
	}
	
	
	
	
	
	//Benchmarks for DensCalc Molecules large Atoms large
	
	@Benchmark
	public void densityCalcImperativeMlAl(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMlAl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMlAl(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMlAl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMlAl(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMlAl, core));
	}
	
	
	
	
	//Benchmarks for DensCalc Molecules small Atoms xl
	
	@Benchmark
	public void densityCalcImperativeMsAxl(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMsAxl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMsAxl(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMsAxl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMsAxl(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMsAxl, core));
	}
	
	
	
	
	//Benchmarks for DensCalc Molecules medium Atoms xl
	
	@Benchmark
	public void densityCalcImperativeMmAxl(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMmAxl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMmAxl(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMmAxl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMmAxl(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMmAxl, core));
	}
	
	
	
	
	//Benchmarks for DensCalc Molecules large Atoms xl
	
	@Benchmark
	public void densityCalcImperativeMlAxl(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMlAxl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMlAxl(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMlAxl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMlAxl(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMlAxl, core));
	}
	
	
	
	//Benchmarks for DensCalc Molecules small Atoms xxl
	
	@Benchmark
	public void densityCalcImperativeMsAxxl(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMsAxxl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMsAxxl(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMsAxxl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMsAxxl(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMsAxxl, core));
	}
	
	
	
	
	//Benchmarks for DensCalc Molecules medium Atoms xxl
	
	@Benchmark
	public void densityCalcImperativeMmAxxl(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMmAxxl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMmAxxl(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMmAxxl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMmAxxl(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMmAxxl, core));
	}
	
	
	
	
	//Benchmarks for DensCalc Molecules large Atoms xxl
	
	@Benchmark
	public void densityCalcImperativeMlAxxl(Blackhole bh){
		bh.consume(starter.calculateDensityImperative(moleculesMlAxxl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalMlAxxl(Blackhole bh){
		bh.consume(starter.calculateDensityStream(moleculesMlAxxl, core));
	}
	
	@Benchmark
	public void densityCalcFunctionalParallelMlAxxl(Blackhole bh){
		bh.consume(starter.calculateDensityStreamParallel(moleculesMlAxxl, core));
	}
	
	
	
	
	
	

	public static void main(String[] args) throws Exception {
		Options options = new OptionsBuilder()
				.include(SetOfBenchmarks.class.getSimpleName())
				.warmupIterations(5)
				.measurementIterations(10)
				.forks(2)
				.build();
		new Runner(options).run();
	}
}
