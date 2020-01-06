import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

public class HarmonicOscillator implements FirstOrderDifferentialEquations {
    private double mu;

    public HarmonicOscillator(double mu) {
        this.mu = mu;
    }

    @Override
    public int getDimension() {
        return 2;
    }

    @Override
    public void computeDerivatives(double t, double[] x, double[] dxdt)
            throws MaxCountExceededException, DimensionMismatchException {
        dxdt[0] = x[1];
        dxdt[1] = -mu*(1-Math.pow(x[0],2))*x[1]-x[0];

    }
}
