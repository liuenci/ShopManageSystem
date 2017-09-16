package cn.view.UI;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.FileOutputStream;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;  
  
public class Demo {  
    public static void main(String[] args) {  
    	double x=23.5455; 
        NumberFormat ddf1=NumberFormat.getNumberInstance() ; 
        ddf1.setMaximumFractionDigits(2); 
        String s= ddf1.format(x) ; 
        System.out.print(s); 
        // 1. �õ�����  
        CategoryDataset dataset = getDataSet();  
        // 2. ����chart  
        JFreeChart chart = ChartFactory.createBarChart3D(  
                "ˮ������ͼ", // ͼ�����  
                "ˮ��", // Ŀ¼�����ʾ��ǩ--����  
                "����", // ��ֵ�����ʾ��ǩ--����  
                dataset, // ���ݼ�  
                PlotOrientation.VERTICAL, // ͼ����ˮƽ��  
                true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ����  
                false, // �Ƿ����ɹ���  
                false // �Ƿ�����URL����  
                );  
        // 3. ����chart������ʾ����  
        processChart(chart);  
  
        // 4. chart���ͼƬ  
        writeChartAsImage(chart);  
  
        // 5. chart ��swing��ʽ���  
        ChartFrame pieFrame = new ChartFrame("XXX", chart);  
        pieFrame.pack();  
        pieFrame.setVisible(true);  
  
    }  
  
    /** 
     * ��ȡһ����ʾ�õ�������ݼ����� 
     *  
     * @return 
     */  
    private static CategoryDataset getDataSet() {  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        dataset.addValue(100, "����", "ƻ��");  
        dataset.addValue(120, "�Ϻ�", "ƻ��");  
        dataset.addValue(160, "����", "ƻ��");
        dataset.addValue(160, "����", "ƻ��");  
        dataset.addValue(210, "����", "����");  
        dataset.addValue(220, "�Ϻ�", "����");  
        dataset.addValue(230, "����", "����");  
        dataset.addValue(330, "����", "����");  
        dataset.addValue(340, "�Ϻ�", "����");  
        dataset.addValue(340, "����", "����");  
        dataset.addValue(420, "����", "�㽶");  
        dataset.addValue(430, "�Ϻ�", "�㽶");  
        dataset.addValue(400, "����", "�㽶");  
        dataset.addValue(510, "����", "��֦");  
        dataset.addValue(530, "�Ϻ�", "��֦");  
        dataset.addValue(510, "����", "��֦");  
        return dataset;  
    }  
  
    /** 
     * ���ͼ������ʾ���� 
     *  
     * @param chart 
     */  
    private static void processChart(JFreeChart chart) {  
        CategoryPlot plot = chart.getCategoryPlot();  
        CategoryAxis domainAxis = plot.getDomainAxis();  
        ValueAxis rAxis = plot.getRangeAxis();  
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,  
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);  
        TextTitle textTitle = chart.getTitle();  
        textTitle.setFont(new Font("����", Font.PLAIN, 20));  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
        domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
        rAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        rAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
        chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));  
        // renderer.setItemLabelGenerator(new LabelGenerator(0.0));  
        // renderer.setItemLabelFont(new Font("����", Font.PLAIN, 12));  
        // renderer.setItemLabelsVisible(true);  
    }  
  
    /** 
     * ���ͼƬ 
     *  
     * @param chart 
     */  
    private static void writeChartAsImage(JFreeChart chart) {  
        FileOutputStream fos_jpg = null;  
        try {  
            fos_jpg = new FileOutputStream("D:\\fruit.jpg");  
            ChartUtilities.writeChartAsJPEG(fos_jpg, 1, chart, 400, 300, null);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                fos_jpg.close();  
            } catch (Exception e) {  
            }  
        }  
    }  
}  