package dad.calendario;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CalendarController implements Initializable {

	private static final int COLUMNS = 4;

	// modelo

	private IntegerProperty year = new SimpleIntegerProperty();


	// vista

	private MesComponent[] MesComponent;

	@FXML
	private BorderPane view;

	@FXML
	private Label anio;

	@FXML
	private GridPane monthsPane;

	public CalendarController() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Calendario.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void initialize(URL location, ResourceBundle resources) {

		this.MesComponent = new MesComponent[12];
		for (int month = 0; month < 12; month++) {
			int col = month % COLUMNS;
			int row = month / COLUMNS;
			MesComponent[month] = new MesComponent();
			MesComponent[month].setMonth(month + 1);
			MesComponent[month].yearProperty().bind(year);
			monthsPane.add(MesComponent[month], col, row);
		}

		this.anio.textProperty().bind(this.year.asString());

		this.year.set(LocalDate.now().getYear());

	}

	public BorderPane getView() {
		return view;
	}

	@FXML
	private void onPreviousButtonAction(ActionEvent e) {
		int width = (int) monthsPane.getWidth();
		int height = (int) monthsPane.getHeight();

		SnapshotParameters param = new SnapshotParameters();
		param.setFill(Color.TRANSPARENT);

		Image prevCalendarSnapshot = monthsPane.snapshot(param, new WritableImage(width, height));
		ImageView prevImageView = new ImageView(prevCalendarSnapshot);

		year.set(year.get() - 1);

		Image newCalendarSnapshot = monthsPane.snapshot(param, new WritableImage(width, height));
		ImageView newImageView = new ImageView(newCalendarSnapshot);

		Pane pane = new Pane(newImageView, prevImageView);
		pane.setClip(new Rectangle(width, height));


	}

	@FXML
	private void onNextButtonAction(ActionEvent e) {
		int width = (int) monthsPane.getWidth();
		int height = (int) monthsPane.getHeight();

		SnapshotParameters param = new SnapshotParameters();
		param.setFill(Color.TRANSPARENT);

		Image prevCalendarSnapshot = monthsPane.snapshot(param, new WritableImage(width, height));
		ImageView prevImageView = new ImageView(prevCalendarSnapshot);

		year.set(year.get() + 1);

		Image newCalendarSnapshot = monthsPane.snapshot(param, new WritableImage(width, height));
		ImageView newImageView = new ImageView(newCalendarSnapshot);

		Pane pane = new Pane(newImageView, prevImageView);
		pane.setClip(new Rectangle(width, height));


		
	}

}
