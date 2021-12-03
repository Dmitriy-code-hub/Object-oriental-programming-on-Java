package sample;
/*import java.util.*;
import java.util.stream.Collectors;


public class Main {
    private static void printOut(List<Location> components) {
        for (Location x: components) {
            System.out.println(x.getPrice() + " $ for " + x.getName());
        }
    }

    public static void main(String[] args) {
        LocationCatalog supplier = new LocationCatalog();
        TransportCatalog t_supplier = new TransportCatalog();
        RegisterLocationCatalog my_catalog = new RegisterLocationCatalog(supplier, t_supplier);
        RegisterLocation my_tur = my_catalog.get("German tour");

        System.out.println("German tour");
        System.out.println(my_tur.getPrice());
        List<Location> components = my_tur.getComponents();
        printOut(components);

        System.out.println("");
        System.out.println("After sorting by price");
        Collections.sort(components, LocationComparatorFactory.get("price"));
        printOut(components);

        System.out.println("");
        System.out.println("Try to find Riu");
        components = components.stream()
                .filter(a -> Objects.equals(a.getName().indexOf("Riu")>-1, true))
                .collect(Collectors.toList());
        printOut(components);
    }

}

*/


import java.util.*;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.collections.*;

public class Main extends Application {
    private RegisterLocation my_tour;
    private ListView<String> componentsView;
    private Label totals;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Select tour");
        VBox rootPanel = new VBox(10);
        rootPanel.setAlignment(Pos.CENTER);
        rootPanel.getChildren().add(new Label("Select tour:"));

        LocationCatalog supplier = new LocationCatalog();
        TransportCatalog t_supplier = new TransportCatalog();
        RegisterLocationCatalog my_catalog = new RegisterLocationCatalog(supplier, t_supplier);
        my_tour = my_catalog.get("German tour");
        componentsView = createView(my_tour);
        totals = new Label();

        rootPanel.getChildren().add(createAddPanel(my_catalog, supplier));
        rootPanel.getChildren().add(componentsView);
        rootPanel.getChildren().add(totals);
        rootPanel.getChildren().add(createSortPanel());

        primaryStage.setScene(new Scene(rootPanel, 300, 400));
        primaryStage.setWidth(1000);
        primaryStage.show();
        updateView(my_tour);
    }

    /* Buttons */

    private Button createAddLocationButton (String label, String id, LocationCatalog catalog) {
        Button addButton = new Button();
        addButton.setText(label);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                my_tour.add(catalog.get(id));
                updateView(my_tour);
            }
        });
        return addButton;
    }

    private Button createSetRegisterLocationButton (String label, String id, RegisterLocationCatalog catalog) {
        Button addButton = new Button();
        addButton.setText(label);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                my_tour = catalog.get(id);
                updateView(my_tour);
            }
        });
        return addButton;
    }

    private Button createAddRegisterLocationButton (String label, String id, RegisterLocationCatalog catalog) {
        Button addButton = new Button();
        addButton.setText(label);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                my_tour.add(catalog.get(id));
                updateView(my_tour);
            }
        });
        return addButton;
    }

    private Button createSortButton (String label, String id) {
        Button sortButton = new Button();
        sortButton.setText(label);
        sortButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sort(id);
            }
        });
        return sortButton;
    }

    private Button createSearchButton (String label) {
        Button searchButton = new Button();
        searchButton.setText(label);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                search();
            }
        });
        return searchButton;
    }

    private Pane createSortPanel() {
        HBox sortPanel = new HBox(10);
        sortPanel.getChildren().add(createSortButton("by name","name"));
        sortPanel.getChildren().add(createSortButton("by days","days"));
        sortPanel.getChildren().add(createSortButton("by price","price"));
        sortPanel.getChildren().add(createSearchButton("Search by name"));
        return sortPanel;
    }

    private Pane createAddPanel(RegisterLocationCatalog kitchen, LocationCatalog supplier) {
        HBox addPanel = new HBox(10);
        addPanel.getChildren().add(createSetRegisterLocationButton("Choose German tour", "German tour", kitchen));
        addPanel.getChildren().add(createSetRegisterLocationButton("Choose Ireland tour", "Ireland tour", kitchen));
        addPanel.getChildren().add(createAddLocationButton("Add Hilton", "Hilton", supplier));
        addPanel.getChildren().add(createAddLocationButton("Add International", "International", supplier));
        addPanel.getChildren().add(createAddRegisterLocationButton("Add German tour", "German tour", kitchen));
        return addPanel;
    }

    /* View */

    private ListView<String> createView(RegisterLocation registerlocation) {
        ListView<String> componentsView = new ListView<String>(renderRegisterLocationToList (registerlocation));
        componentsView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() >= 2) {
                    Location component = registerlocation.getComponents().get(componentsView.getSelectionModel().getSelectedIndex());
                    component.setDays(editDays(component.getDays()));
                    componentsView.setItems(renderRegisterLocationToList(registerlocation));
                    updateView(registerlocation);
                }
            }
        });
        return componentsView;
    }

    private void updateView(RegisterLocation registerlocation) {
        componentsView.setItems(renderRegisterLocationToList(registerlocation));
        totals.setText(renderTotals(registerlocation));
    }

    private void updateView(List<Location> components) {
        componentsView.setItems(renderComponentsToList(components));
    }

    private String renderTotals(RegisterLocation nm) {
        return "Total: " + nm.getDays() + " days, " + nm.getPrice() + " $.";
    }

    private ObservableList<String> renderRegisterLocationToList (RegisterLocation nm) {
        return renderComponentsToList(nm.getComponents());
    }

    private ObservableList<String> renderComponentsToList (List<Location> components) {
        ObservableList<String> componentsList = FXCollections.observableArrayList();
        for (Location x: components) {
            componentsList.add(x.getName() + " - " + x.getDays() + " days, " +x.getPrice() + " $.");
        }
        return componentsList;
    }

    /* Actions */

    private void sort (String id) {
        List<Location> sortedComponents = my_tour.getComponents();
        Collections.sort(sortedComponents, LocationComparatorFactory.get(id));
        updateView(sortedComponents);
    }

    private void search () {
        TextInputDialog td = new TextInputDialog("Enter search string");
        td.setHeaderText("Search by name");
        td.showAndWait();

        List<Location> components = my_tour.getComponents();
        components = components.stream()
                .filter(a -> Objects.equals(a.getName().indexOf(td.getEditor().getText())>-1, true))
                .collect(Collectors.toList());
        updateView(components);
    }

    private double editDays(double days) {
        TextInputDialog td = new TextInputDialog(Double.toString(days));
        td.setHeaderText("Enter days ");
        td.showAndWait();
        return Double.valueOf(td.getEditor().getText()).doubleValue();
    }

}
