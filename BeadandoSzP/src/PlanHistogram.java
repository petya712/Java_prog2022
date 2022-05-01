
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class PlanHistogram extends JPanel{
   
	private static final long serialVersionUID = 1L;
	private int histogramHeight = 800;
    private int barWidth = 50;
    private int barGap = 10;

    private JPanel barPanel;
    private JPanel labelPanel;

    private List<Bar> bars = new ArrayList<Bar>();

    public PlanHistogram(){
    	setBackground(new Color(153, 255, 255));
        setBorder( new EmptyBorder(10, 10, 10, 10) );
        setLayout( new BorderLayout() );

        barPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        barPanel.setToolTipText("Földünktől való távolság (km)");
        barPanel.setBackground(new Color(153, 255, 255));
        Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
        Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder( compound );

        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBackground(new Color(153, 255, 255));
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );

        add(barPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.PAGE_END);
    }

    public void addHistogramColumn(String label, int value, Color color){
        Bar bar = new Bar(label, value, color);
        bars.add( bar );
    }

    public void layoutHistogram(){
        barPanel.removeAll();
        labelPanel.removeAll();

        int maxValue = 0;

        for (Bar bar: bars)
            maxValue = Math.max(maxValue, bar.getValue());

        for (Bar bar: bars){
            JLabel label = new JLabel(bar.getValue() + "");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.BOTTOM);
            System.out.println("bar.getValue: "+bar.getValue()+"\nHistogramheight: "+histogramHeight+"\nmaxValue: "+maxValue); 			//debug segítő
            double barHeight = ((double)bar.getValue() * (double)histogramHeight) / maxValue;
            System.out.println("barHeight: "+barHeight);							//debug segítő
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
            label.setIcon( icon );
            barPanel.add( label );

            JLabel barLabel = new JLabel( bar.getLabel() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add( barLabel );
        }
    }

    private class Bar{
        private String label;
        private int value;
        private Color color;

        public Bar(String label, int value, Color color){
            this.label = label;
            this.value = value;
            this.color = color;
        }

        public String getLabel(){
            return label;
        }

        public int getValue(){
            return value;
        }

        public Color getColor(){
            return color;
        }
    }

    private class ColorIcon implements Icon{
        private int shadow = 3;

        private Color color;
        private int width;
        private int height;

        public ColorIcon(Color color, int width, double height){
            this.color = color;
            this.width = width;
            this.height = (int)height;
        }

        public int getIconWidth(){
            return width;
        }

        public int getIconHeight(){
            return height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y){
            g.setColor(color);
            g.fillRect(x, y, width - shadow, height);
            g.setColor(Color.GRAY);
            g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
        }
    }

    public static void createAndShowGUI(){
        PlanHistogram panel = new PlanHistogram();
        Object platmn[] = {"Jel","Kód","Név","Felfedezési_idő","Felfedező_neve","Távolsága_Földtől"};
		PlaTM ptm = new PlaTM(platmn, 0);
		try{
			BufferedReader in  = new BufferedReader(new FileReader("adatok.txt"));
			in.mark(1500);
			String s=in.readLine();
			s=in.readLine();
			
			while(s!=null){
				String[] st = s.split(";");
				ptm.addRow(new Object[]{false,st[0],st[1],st[2],st[3],st[4] });
				s=in.readLine();
			}
			
			
			in.reset();
			int lengthofptm = ptm.getRowCount(); //Elmentem a fájlban lévő sorok számát a már beolvasott táblából
			s=in.readLine();
			
			ptm.setRowCount(0); 	//A táblát kiűríti, indul a keresett elemek bepakolása
			int temp=0;
		for(int i = 0;lengthofptm>i;i++) {
			if(s!=null){
				s=in.readLine();
				String[] st = s.split(";");
				temp =Integer.parseInt(st[4]);
				panel.addHistogramColumn(st[1],temp, Color.RED);
			}
		}
		in.close();
		}catch(IOException ioe){
			System.out.println("Diagram error: "+ioe.getMessage());
		}
	
        
        panel.layoutHistogram();

        JFrame frame = new JFrame("Histogram Panel");
        frame.getContentPane().add( panel );
        //frame.setLocationByPlatform( true );   //néha furán helyezi el ezzel
        frame.setMinimumSize(new Dimension(1800,1000));
        frame.setVisible( true );
    }

    
}