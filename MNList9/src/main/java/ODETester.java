import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;

import java.util.Arrays;

public class ODETester {
    public static void main(String[] args) {
        HarmonicOscillator harmonicOscillator = new HarmonicOscillator(1);
        FirstOrderIntegrator eulerIntegrator = new EulerIntegrator(0.001);

        HOStepHandler hoStepHandler = new HOStepHandler();
        eulerIntegrator.addStepHandler(hoStepHandler);

        double [] xStart = {1., 0};
        double [] xStop = {0,0};

        eulerIntegrator.integrate(harmonicOscillator,0.,xStart,Math.PI,xStop);
        System.out.println(eulerIntegrator.integrate(harmonicOscillator,0.,xStart,Math.PI,xStop));
        System.out.println(Arrays.toString(xStart));
        System.out.println(Arrays.toString(xStop));

        System.out.println(hoStepHandler.xValues);
        System.out.println(hoStepHandler.tValues);
        System.out.println(hoStepHandler.vValues);

    }
}
