package excelOperations;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class dataVisualizer extends JPanel {
     static ArrayList<Integer> IMF_gdp_estimate = new ArrayList<>();
     static ArrayList<Integer> UN_gdp_estimate = new ArrayList<>();
     static ArrayList<Integer> WB_gdp_estimate = new ArrayList<>();
     static ArrayList<String> Country_name = new ArrayList<>();
     static ArrayList<String> Regions = new ArrayList<>();
     static ArrayList<Integer> IMF_years = new ArrayList<>();
     static ArrayList<Integer> UN_years = new ArrayList<>();
     static ArrayList<Integer> WB_years = new ArrayList<>();
     static ArrayList<Integer> Ratios = new ArrayList<>();
     static ArrayList<Integer> estimateAverage = new ArrayList<>();

     public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

     public static final int FRAME_X = (int)screenSize.getWidth();
     public static final int FRAME_Y = (int)screenSize.getHeight();

     static JPanel y_axisLabel_panel = new JPanel();
     static JLabel y_axis_label = new JLabel("GDP estimate (US$)");
     static JPanel x_axisLabel_panel = new JPanel();
     static JLabel x_axis_label = new JLabel("Years");

     static Object[] colors = {"#ba7a52", "#469185", "#6b412d", "#324d7a",
                    "#603a7a", "#6e476e", "#a85151", "#c2ae67", "#6ca5a6", "#7d7099"};

     static final Object[][] Y_AXIS_INTERVAL_POINTS = {
             {100, 20}, {100, 100}, {100, 200}, {110, 300}, {110, 400}, {120, 500}, {120, 600}
     };
     static Object[][] X_AXIS_INTERVAL_POINTS = {
             {200, 700}, {560, 700}, {920, 700}, {1280, 700}
     };
     static ArrayList<Integer> y_breakpoints = new ArrayList<>();
     static ArrayList<Integer> x_breakpoints = new ArrayList<>();

    public static void transferAndConvert(List<gdpStructure> obj){
        for (excelOperations.gdpStructure gdpStructure : obj) {
            int IMF_est = (int) gdpStructure.getIMFestimate();
            int UN_est = (int) gdpStructure.getUNestimate();
            int WB_est = (int) gdpStructure.getWBestimate();
            int IMF_year = (int) gdpStructure.getIMFyear();
            int UN_year = (int) gdpStructure.getUNyear();
            int WB_year = (int) gdpStructure.getWByear();
            String country = gdpStructure.getCountry();
            String region = gdpStructure.getRegion();
            Country_name.add(country);
            IMF_gdp_estimate.add(IMF_est);
            UN_gdp_estimate.add(UN_est);
            WB_gdp_estimate.add(WB_est);
            IMF_years.add(IMF_year);
            UN_years.add(UN_year);
            WB_years.add(WB_year);
            Regions.add(region);
        }
    }

    public static void CalcAverage() {
        for (int i = 0; i < Country_name.size(); i++) {
            int average = (IMF_gdp_estimate.get(i) +
                    UN_gdp_estimate.get(i) + WB_gdp_estimate.get(i)) / 3;
            estimateAverage.add(average);
        }
    }

    public static void statisticalRatio(){
        for (Integer integer : estimateAverage) {
            int ratio = ((integer / FRAME_X) / 12);
            Ratios.add(ratio);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(x_axisLabel_panel.getWidth(), x_axisLabel_panel.getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawLine(200, 650, FRAME_X, 650); // X- axis
        g.drawLine(200, 0, 200, FRAME_Y - 215); // Y- axis

        g.drawString("35, 000, 000", 100,20);
        g.drawString("20, 000,000", 100,100);
        g.drawString("5, 000, 000", 100,200);
        g.drawString("500, 000", 110,300);
        g.drawString("100, 000", 110,400);
        g.drawString("50, 000", 120,500);
        g.drawString("10, 000", 120,600);

        g.drawString("2019", 200,700);
        g.drawString("2020", 500,700);
        g.drawString("2021", 850,700);
        g.drawString("2022", 1180,700);

        for (Object[] yAxisIntervalPoint : Y_AXIS_INTERVAL_POINTS) {
            y_breakpoints.add((int) yAxisIntervalPoint[1]);
        }
        for (Object[] xAxisIntervalPoint : X_AXIS_INTERVAL_POINTS) {
            x_breakpoints.add((int) xAxisIntervalPoint[0]);
        }
    }

    public static void dataDisplay(JFrame f) {
        Random random = new Random();
        int randomColor = random.nextInt(colors.length);
        final int BUTTON_WIDTH = 10;
        final int BUTTON_HEIGHT = 10;

        for(int i=0; i < estimateAverage.size(); i++){
            JButton button = new JButton();
            if (estimateAverage.get(i) > 20000000) {
                if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2020) {
                    int x = 550 + (int) (Math.random() * (1080 - 550) + 1);
                    int y = 140 + (int) (Math.random() * (60 -140) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2021) {
                    int x = 1080 + (int) (Math.random() * (1280 - 1080) + 1);
                    int y = 100 + (int) (Math.random() * (-100) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else if (IMF_years.get(i) == 2020 && UN_years.get(i) == 2020) {
                    int x = 400 + (int) (Math.random() * (760 - 400) + 1);
                    int y = 100 + (int) (Math.random() * (-100) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else{
                    int x = 400 + (int) (Math.random() * (500 - 400) + 1);
                    int y = 100 + (int) (Math.random() * (-100) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
            } else if (20000000 > estimateAverage.get(i)  && estimateAverage.get(i) > 10000000) {
                if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2020) {
                    int x = 550 + (int) (Math.random() * (1080 - 550) + 1);
                    int y = 170 + (int) (Math.random() * (130 - 170) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);

                } else if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2021) {
                    int x = 1080 + (int) (Math.random() * (1280 - 1080) + 1);
                    int y = 170 + (int) (Math.random() * (130 - 170) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);

                } else if (IMF_years.get(i) == 2020 && UN_years.get(i) == 2020) {
                    int x = 400 + (int) (Math.random() * (760 - 400) + 1);
                    int y = 170 + (int) (Math.random() * (130 - 170) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                } else {
                    int x = 400 + (int) (Math.random() * (500 - 400) + 1);
                    int y = 170 + (int) (Math.random() * (130 - 170) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
            } else if (10000000 > estimateAverage.get(i) && estimateAverage.get(i) > 500000) {
                if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2020) {
                    int x = 550 + (int) (Math.random() * (1080 - 550) + 1);
                    int y = 300 + (int) (Math.random() * (280 - 320) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2021) {
                    int x = 1080 + (int) (Math.random() * (1280 - 1080) + 1);
                    int y = 300 + (int) (Math.random() * (280 - 320) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else if (IMF_years.get(i) == 2020 && UN_years.get(i) == 2020) {
                    int x = 400 + (int) (Math.random() * (760 - 400) + 1);
                    int y = 300 + (int) (Math.random() * (280 - 320) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else {
                    int x = 400 + (int) (Math.random() * (500 - 400) + 1);
                    int y = 300 + (int) (Math.random() * (280 - 320) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
            } else if (500000 > estimateAverage.get(i) && estimateAverage.get(i) > 100000) {
                if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2020) {
                    int x = 550 + (int) (Math.random() * (1080 - 550) + 1);
                    int y = 400 + (int) (Math.random() * (370 - 400) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2021) {
                    int x = 1080 + (int) (Math.random() * (1280 - 1080) + 1);
                    int y = 400 + (int) (Math.random() * (370 - 400) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else if (IMF_years.get(i) == 2020 && UN_years.get(i) == 2020) {
                    int x = 400 + (int) (Math.random() * (760 - 400) + 1);
                    int y = 400 + (int) (Math.random() * (370 - 400) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else {
                    int x = 400 + (int) (Math.random() * (500 - 400) + 1);
                    int y = 400 + (int) (Math.random() * (370 - 400) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
            } else if (100000 > estimateAverage.get(i) && estimateAverage.get(i) > 50000) {
                if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2020) {
                    int x = 550 + (int) (Math.random() * (1080 - 550) + 1);
                    int y = 450 + (int) (Math.random() * (550 - 450) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2021) {
                    int x = 1080 + (int) (Math.random() * (1280 - 1080) + 1);
                    int y = 450 + (int) (Math.random() * (550 - 450) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else if (IMF_years.get(i) == 2020 && UN_years.get(i) == 2020) {
                    int x = 400 + (int) (Math.random() * (900 - 400) + 1);
                    int y = 450 + (int) (Math.random() * (550 - 450) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else {
                    int x = 400 + (int) (Math.random() * (500 - 400) + 1);
                    int y = 450 + (int) (Math.random() * (550 - 450) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
            } else if (50000 > estimateAverage.get(i) && estimateAverage.get(i) > 10000) {
                if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2020) {
                    int x = 550 + (int) (Math.random() * (1080 - 550) + 1);
                    int y = 600 + (int) (Math.random() * (500 - 600) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2021) {
                    int x = 1080 + (int) (Math.random() * (1280 - 1080) + 1);
                    int y = 600 + (int) (Math.random() * (500 - 600) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else if (IMF_years.get(i) == 2020 && UN_years.get(i) == 2020) {
                    int x = 400 + (int) (Math.random() * (760 - 400) + 1);
                    int y = 600 + (int) (Math.random() * (500 - 600) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else {
                    int x = 400 + (int) (Math.random() * (500 - 400) + 1);
                    int y = 600 + (int) (Math.random() * (500 - 600) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
            }
            else {
                if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2020) {
                    int x = 550 + (int) (Math.random() * (1080 - 550) + 1);
                    int y = 640 + (int) (Math.random() * (680 - 640) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else if (IMF_years.get(i) == 2021 && UN_years.get(i) == 2021) {
                    int x = 1080 + (int) (Math.random() * (1280 - 1080) + 1);
                    int y = 640 + (int) (Math.random() * (680 - 640) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else if (IMF_years.get(i) == 2020 && UN_years.get(i) == 2020) {
                    int x = 400 + (int) (Math.random() * (760 - 400) + 1);
                    int y = 640 + (int) (Math.random() * (680 - 640) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
                else {
                    int x = 320 + (int) (Math.random() * (600 - 320) + 1);
                    int y = 640 + (int) (Math.random() * (680 - 640) + 1);
                    button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
                }
            }
            button.setLayout(null);
            button.setBackground(Color.decode((String) colors[randomColor]));

            if (Country_name.get(i).equals("Malaysia")) {
                button.setBackground(Color.decode("#ff0000"));
            }

            int finalI = i;
            button.addActionListener(e -> JOptionPane.showMessageDialog(f,
                    "Country: " + Country_name.get(finalI) + "\n" +
                    "Region: " + Regions.get(finalI) + "\n" +
                    "IMF estimate: $" + IMF_gdp_estimate.get(finalI) + "\n" +
                    "UN estimate: $" + UN_gdp_estimate.get(finalI) + "\n" +
                    "WB estimate: $" + WB_gdp_estimate.get(finalI) + "\n"));

            f.add(button);
        }
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("GDP Estimate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_X, FRAME_Y);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setBackground(Color.decode("#72757a"));
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Average GDP (US$ million) by country");
        title.setFont(new Font("Ariel", Font.BOLD, 30));
        titlePanel.add(title);
        frame.add(titlePanel, BorderLayout.NORTH);
        x_axisLabel_panel.add(x_axis_label);
        x_axis_label.setFont(new Font("Ariel", Font.BOLD, 15));
        y_axisLabel_panel.add(y_axis_label);
        y_axis_label.setFont(new Font("Ariel", Font.BOLD, 12));
        y_axisLabel_panel.setPreferredSize(new Dimension(120, 50));
        x_axisLabel_panel.setPreferredSize(new Dimension(120, 50));
        frame.add(x_axisLabel_panel, BorderLayout.SOUTH);
        frame.add(y_axisLabel_panel, BorderLayout.WEST);
        dataDisplay(frame);
        frame.add(new dataVisualizer());
    }
}