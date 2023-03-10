package dad.calendario;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import dad.calendario.utils.DateUtils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MesComponent extends GridPane implements Initializable {
	
	private static final DateFormat FORMATTER = new SimpleDateFormat("MMMM"); 

	// model

	private IntegerProperty month = new SimpleIntegerProperty();
	private IntegerProperty year = new SimpleIntegerProperty();
	
	// view
	
	@FXML
	private Label MesLabel;
	
	@FXML
	private List<Label> ListaLabel;

	public MesComponent() {
		super();
		
		// carga la vista desde FXML (MonthCalendar es controlador y vista a la vez [porque es un componente])
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MesComponent.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	
	public void initialize(URL location, ResourceBundle resources) {

		this.month.addListener((o, ov, nv) -> onModelChanged(o, ov, nv));
		this.year.addListener((o, ov, nv) -> onModelChanged(o, ov, nv));
		
	}

	private void onModelChanged(ObservableValue<? extends Number> o, Number ov, Number nv) {

		int first = DateUtils.Primerdia(year.get(), month.get()) - 1;
		int last = DateUtils.Ultimodia(year.get(), month.get());
		for (int i = 0; i < first; i++) {
			ListaLabel.get(i).setText("");
			ListaLabel.get(i).getStyleClass().remove("today");
		}
		for (int i = first, j = 1; i < first + last; i++, j++) {
			ListaLabel.get(i).setText("" + j);
			if (LocalDate.of(year.get(), month.get(), j).equals(LocalDate.now())) {
				ListaLabel.get(i).getStyleClass().add("today");				
			} else {
				ListaLabel.get(i).getStyleClass().remove("today");				
			}
		}
		for (int i = first + last; i < ListaLabel.size(); i++) {
			ListaLabel.get(i).setText("");
			ListaLabel.get(i).getStyleClass().remove("today");
		}
		Date day = DateUtils.Nuevafecha(year.get(), month.get(), 1);
		MesLabel.setText(FORMATTER.format(day));
		
	}

	public IntegerProperty monthProperty() {
		return this.month;
	}

	public int getMonth() {
		return this.monthProperty().get();
	}

	public void setMonth(final int month) {
		this.monthProperty().set(month);
	}

	public IntegerProperty yearProperty() {
		return this.year;
	}

	public int getYear() {
		return this.yearProperty().get();
	}

	public void setYear(final int year) {
		this.yearProperty().set(year);
	}

}
