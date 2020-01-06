import javafx.scene.chart.XYChart;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

import java.util.ArrayList;
import java.util.List;

public class HOStepHandler implements StepHandler {
    protected List<Double> tValues = new ArrayList<>();
    protected List<Double> xValues = new ArrayList<>();
    protected List<Double> vValues = new ArrayList<>();
    @Override
    public void init(double t0, double[] x0, double t) {
        tValues.add(t0);
        xValues.add(x0[0]);
        vValues.add(x0[1]);
    }

    @Override
    public void handleStep(StepInterpolator interpolator, boolean isLast)
            throws MaxCountExceededException {
        double t = interpolator.getCurrentTime();
        double [] x = interpolator.getInterpolatedState();
        tValues.add(t);
        xValues.add(x[0]);
        vValues.add(x[1]);
    }
    public XYChart.Series getValuesTvsX(){
        if(tValues.size() == 0 || xValues.size() == 0){
            throw new IllegalArgumentException("Sizes don't match.");
        }
        XYChart.Series values = new XYChart.Series();
        for (int i = 0; i < xValues.size() ; i++) {
            values.getData().add(new XYChart.Data(tValues.get(i),xValues.get(i)));
        }
        return values;
    }

    public XYChart.Series getValuesTvsV(){
        if(tValues.size() == 0 || vValues.size() == 0){
            throw new IllegalArgumentException("Sizes don't match.");
        }
        XYChart.Series values = new XYChart.Series();
        for (int i = 0; i < vValues.size() ; i++) {
            values.getData().add(new XYChart.Data(tValues.get(i),vValues.get(i)));
        }
        return values;
    }

    public XYChart.Series getValuesXvsV(){
        if(xValues.size() == 0 || vValues.size() == 0){
            throw new IllegalArgumentException("Sizes don't match.");
        }
        XYChart.Series values = new XYChart.Series();
        for (int i = 0; i < xValues.size() ; i++) {
            values.getData().add(new XYChart.Data(xValues.get(i),vValues.get(i)));
        }
        return values;
    }
}
