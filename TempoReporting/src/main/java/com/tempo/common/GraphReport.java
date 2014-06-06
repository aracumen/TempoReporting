package com.tempo.common;

import java.awt.Rectangle;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class GraphReport extends JPanel implements RenderedImage {


	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	   * @param args
	   * @throws IOException 
	   */
	  public static void CreatePng(int pass, int fail, int skip) throws IOException {
	    //Prepare the data set
	    DefaultPieDataset pieDataset = new DefaultPieDataset();
	    
	    pieDataset.setValue("Fail["+fail+"]", fail);
	    pieDataset.setValue("Skip["+skip+"]", skip);
	    pieDataset.setValue("Pass["+pass+"]", pass);
	    

	    //Create the chart
	    //JFreeChart chart = ChartFactory.createPieChart3D("Execution Chart", pieDataset, true, true, true);
	    JFreeChart chart = ChartFactory.createPieChart("Execution Chart", pieDataset, true, true, true);
	    
	    //Save chart as PNG
	      ChartUtilities.saveChartAsPNG(new File("./exec.png"), chart, 400, 300);
	  }

	@Override
	public WritableRaster copyData(WritableRaster arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Raster getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Raster getData(Rectangle arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMinTileX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinTileY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumXTiles() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumYTiles() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getProperty(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getPropertyNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SampleModel getSampleModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<RenderedImage> getSources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Raster getTile(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTileGridXOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTileGridYOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTileHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTileWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
