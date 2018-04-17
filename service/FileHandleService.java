package com.sino.service;

import java.util.Vector;

import com.sino.pojo.PrWordProcedure;

public interface FileHandleService {

	public Vector<PrWordProcedure> findPrWords();

	public void executeFileType1(PrWordProcedure procedure);

	public void executeFileType2(PrWordProcedure procedure);

	public void executeFileType3(PrWordProcedure procedure);

	public void executeFileType4(PrWordProcedure procedure);

	public void executeFileType5(PrWordProcedure procedure);

	public void executeFileType6(PrWordProcedure procedure);

	public void executeFileType7(PrWordProcedure procedure);

	public void executeFileType8(PrWordProcedure procedure);

	public void executeFileType9(PrWordProcedure procedure);

	public void executeFileType10(PrWordProcedure procedure);

}
