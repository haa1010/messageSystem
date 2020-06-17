package graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import javax.swing.*;
import java.lang.String;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Graph extends JFrame {

    public Graph() {   // the constructor will contain the panel of a certain size and the close operations
        super("Statistical graph"); // calls the super class constructor

        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createChartPanel() { // this method will create the chart panel containing the graph
        String chartTitle = "Statistical graph";
        String xAxisLabel = "Time";
        String yAxisLabel = "Number";

        XYDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL , true, true, false);
        customizeChart(chart);
        return new ChartPanel(chart);
    }

    private XYDataset createDataset() {    // this method creates the data as time series
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries numberOfBoy = new XYSeries("Number of boys");
        XYSeries numberOfGirl = new XYSeries("Number of girls");
        XYSeries churnRate = new XYSeries("Customer churn rate (%)");
        XYSeries wrongMessage = new XYSeries("Number of wrong messages");

        ArrayList<Integer> boys = graph.Database.getData("boys");
        ArrayList<Integer> girls = graph.Database.getData("girls");
        ArrayList<Integer> del = graph.Database.getData("del");
        ArrayList<Integer> wrong = graph.Database.getData("wrong");
        ArrayList<String> date = graph.Database.getDate();
        int i;
        for(i = 0; i < boys.size(); i++){
            numberOfBoy.add(Double.parseDouble(date.get(i).split("-")[2]), boys.get(i));
        }
        for(i = 0; i < girls.size(); i++){
            numberOfGirl.add(Double.parseDouble(date.get(i).split("-")[2]), girls.get(i));
        }
        for(i = 0; i < del.size(); i++){
            churnRate.add(Double.parseDouble(date.get(i).split("-")[2]), del.get(i) * 100 /(boys.get(i) + girls.get(i)));
        }
        for(i = 0; i < wrong.size(); i++){
            wrongMessage.add(Double.parseDouble(date.get(i).split("-")[2]), wrong.get(i));
        }

        dataset.addSeries(numberOfBoy);
        dataset.addSeries(numberOfGirl);
        dataset.addSeries(churnRate);
        dataset.addSeries(wrongMessage);

        return dataset;
    }

    private void customizeChart(JFreeChart chart) {   // here we make some customization
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // sets paint color for each series
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.YELLOW);
        renderer.setSeriesPaint(3, Color.MAGENTA);

        // sets thickness for series (using strokes)
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        renderer.setSeriesStroke(3, new BasicStroke(2.0f));

        // sets paint color for plot outlines
        plot.setOutlinePaint(Color.BLUE);
        plot.setOutlineStroke(new BasicStroke(2.0f));

        // sets renderer for lines
        plot.setRenderer(renderer);

        // sets plot background
        plot.setBackgroundPaint(Color.GRAY);

        // sets paint color for the grid lines
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Graph().setVisible(true);
            }
        });
    }
}