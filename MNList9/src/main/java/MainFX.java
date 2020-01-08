import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;

public class MainFX extends Application {

    private TextField textFieldMu;
    private LineChart<Number,Number> figure;

    private Button buttonDrawTvsX;
    private Button buttonDrawTvsV;
    private Button buttonDrawXvsV;
    private Button buttonDrawAll;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Harmonic oscillator");

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20,20,30,30));

        Label label = new Label("Enter mu value and draw charts");
        label.setFont(Font.font("Times", FontWeight.BOLD,16));

        layout.getChildren().add(label);

        Label labelMu = new Label(" mu: ");

        textFieldMu = new TextField();
        textFieldMu.setText("1");
        textFieldMu.setPrefWidth(80);

        HBox hBox = new HBox(labelMu,textFieldMu);
        hBox.setAlignment(Pos.CENTER);

        layout.getChildren().add(hBox);

        buttonDrawTvsX = new Button("Draw x(t)");
        buttonDrawTvsX.setPrefWidth(100);

        buttonDrawTvsV = new Button("Draw v(t)");
        buttonDrawTvsV.setPrefWidth(100);

        buttonDrawXvsV = new Button("Draw x(v)");
        buttonDrawXvsV.setPrefWidth(100);

        buttonDrawAll = new Button("Draw all");
        buttonDrawAll.setPrefWidth(100);

        HBox hbox2 = new HBox(buttonDrawTvsX,buttonDrawTvsV,buttonDrawXvsV,buttonDrawAll);
        hbox2.setAlignment(Pos.CENTER);
        layout.getChildren().add(hbox2);

        NumberAxis x = new NumberAxis();

        NumberAxis y = new NumberAxis();






        buttonDrawTvsX.setOnAction(e->{
            double [] xStart = {1., 0};
            double [] xStop = {0,0};

            HOStepHandler hoStepHandler = new HOStepHandler();

            FirstOrderIntegrator eulerIntegrator = new EulerIntegrator(0.001);
            eulerIntegrator.addStepHandler(hoStepHandler);

            double mu = Double.parseDouble(textFieldMu.getText());
            HarmonicOscillator harmonicOscillator = new HarmonicOscillator(mu);
            eulerIntegrator.integrate(harmonicOscillator,0,xStart,20.,xStop);

            figure.setCreateSymbols(false);
            figure.getData().clear();

            XYChart.Series data = hoStepHandler.getValuesTvsX();
            data.setName("TvsX");

            figure.setLegendVisible(false);
            figure.getData().add(data);
        });

        buttonDrawTvsV.setOnAction(e->{
            double [] xStart = {1., 0};
            double [] xStop = {0,0};

            HOStepHandler hoStepHandler = new HOStepHandler();

            FirstOrderIntegrator eulerIntegrator = new EulerIntegrator(0.001);
            eulerIntegrator.addStepHandler(hoStepHandler);

            double mu = Double.parseDouble(textFieldMu.getText());
            HarmonicOscillator harmonicOscillator = new HarmonicOscillator(mu);
            eulerIntegrator.integrate(harmonicOscillator,0,xStart,20.,xStop);


            figure.setCreateSymbols(false);
            figure.getData().clear();

            XYChart.Series data = hoStepHandler.getValuesTvsV();
            data.setName("TvsV");

            figure.setLegendVisible(false);
            figure.getData().add(data);

        });

        buttonDrawXvsV.setOnAction(e->{
            double [] xStart = {1., 0};
            double [] xStop = {0,0};

            HOStepHandler hoStepHandler = new HOStepHandler();

            FirstOrderIntegrator eulerIntegrator = new EulerIntegrator(0.001);
            eulerIntegrator.addStepHandler(hoStepHandler);

            double mu = Double.parseDouble(textFieldMu.getText());
            HarmonicOscillator harmonicOscillator = new HarmonicOscillator(mu);
            eulerIntegrator.integrate(harmonicOscillator,0,xStart,20.,xStop);


            figure.setCreateSymbols(false);
            figure.getData().clear();

            XYChart.Series data = hoStepHandler.getValuesXvsV();
            data.setName("XvsV");

            figure.setLegendVisible(false);
            figure.getData().add(data);

        });

        buttonDrawAll.setOnAction(e->{
            double [] xStart = {1., 0};
            double [] xStop = {0,0};

            HOStepHandler hoStepHandler = new HOStepHandler();

            FirstOrderIntegrator eulerIntegrator = new EulerIntegrator(0.001);
            eulerIntegrator.addStepHandler(hoStepHandler);

            double mu = Double.parseDouble(textFieldMu.getText());
            HarmonicOscillator harmonicOscillator = new HarmonicOscillator(mu);
            eulerIntegrator.integrate(harmonicOscillator,0,xStart,20.,xStop);


            figure.setCreateSymbols(false);
            figure.getData().clear();

            XYChart.Series data;
            data = hoStepHandler.getValuesTvsX();
            data.setName("x(t)");
            figure.getData().add(data);
            data = hoStepHandler.getValuesTvsV();
            data.setName("v(t)");
            figure.getData().add(data);
            data = hoStepHandler.getValuesXvsV();
            data.setName("x(v)");
            figure.getData().add(data);

            figure.setLegendVisible(true);
        });

        figure = new LineChart<Number,Number>(x,y);

        layout.getChildren().add(figure);

        Scene scene = new Scene(layout, 500,600);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
