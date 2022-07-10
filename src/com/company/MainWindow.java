package com.company;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
public class MainWindow extends Application {
    private TableView table = new TableView();
    private TableView choiceTable = new TableView();
    Ingredient pasta  = new Ingredient("Pasta",taste.neutral);
    Ingredient tomato = new Ingredient("Tomato",taste.salty);
    Ingredient onion  = new Ingredient("Onion",taste.salty);
    Ingredient garlic  = new Ingredient("Garlic",taste.salty);
    Ingredient lemon  = new Ingredient("Lemon",taste.sour);
    Ingredient cranberries  = new Ingredient("Cranberries",taste.bitter);
    Ingredient butter  = new Ingredient("Butter",taste.salty);
    Ingredient strawberries  = new Ingredient("Strawberries",taste.sweet);
    Ingredient milk_chocolate  = new Ingredient("Milk Chocolate",taste.sweet);
    Ingredient beef  = new Ingredient("Beef",taste.salty);
    Ingredient flour  = new Ingredient("Flour",taste.neutral);
    Ingredient egg  = new Ingredient("Egg",taste.neutral);
    ObservableList<Ingredient> ingredientsList = FXCollections.observableArrayList(
            pasta,
            tomato,
            onion,
            garlic,
            lemon,
            cranberries,
            butter,
            strawberries,
            milk_chocolate,
            beef,
            flour,
            egg
    );
    Recipe spag = new Recipe("Spaghetti Bolognese",getBologneseList());
    Recipe cranPie = new Recipe("Cranberry Pie",getCPList());
    Recipe disaster = new Recipe("Disasteer Dish", getDisaster());
    ObservableList<Recipe> recipesList = FXCollections.observableArrayList(
            spag,
            cranPie,
            disaster
    );
    @Override
    public void start(Stage stage) throws Exception {

        MenuBar menubar = new MenuBar();
        Menu m = new Menu("Recipe");
        MenuItem m1 = new MenuItem("Add Recipe");
        MenuItem m2 = new MenuItem("menu item 2");
        MenuItem m3 = new MenuItem("menu item 3");
        m.getItems().add(m1);
        m.getItems().add(m2);
        m.getItems().add(m3);
        menubar.getMenus().add(m);
        menubar.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        menubar.setMaxWidth(404);
        m1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                TableView tableX= new TableView();
                VBox root3 = new VBox();
                HBox el1 = new HBox();
                HBox el2 = new HBox();
                HBox el3 = new HBox();
                HBox el4 = new HBox();
                root3.setPadding(new Insets(8, 0, 0, 8));
                root3.setSpacing(2);
                root3.setPrefSize(440, 380);

                ArrayList<Ingredient> listOfNewDishIngr = new ArrayList<>();

                Button confirmer = new Button("Confirm");
                confirmer.setPadding(new Insets(6, 0, 6, 0));
                confirmer.setFont(new Font("Verdena", 13));
                confirmer.setMinWidth(214);

                Button adder = new Button("Add Ingredient");
                adder.setPadding(new Insets(6, 0, 6, 0));
                adder.setFont(new Font("Verdena", 13));
                adder.setMinWidth(214);
                adder.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        Ingredient ingToAdd = (Ingredient)tableX.getSelectionModel().getSelectedItem();
                        if(listOfNewDishIngr.contains(ingToAdd)) {
                            Alert alert = new Alert(Alert.AlertType.WARNING, "THIS INGREDIENT HAS ALREADY BEEN ADDED!", ButtonType.OK);
                            alert.showAndWait();
                        }
                        if(!listOfNewDishIngr.contains(ingToAdd)){
                            listOfNewDishIngr.add(ingToAdd);
                        }
                    }
                });

                TableColumn nameI = new TableColumn("Ingredient Name");
                tableX.getColumns().add(nameI);
                nameI.setMinWidth(412);
                nameI.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
                tableX.setItems(ingredientsList);
                tableX.setMinWidth(428);
                tableX.setMaxHeight(250);

                Label nameL = new Label("Enter name:");
                TextField name = new TextField();
                Label choose = new Label("Choose Ingredients:");
                nameL.setFont(new Font("Arial",16));
                choose.setFont(new Font("Arial",16));

                el1.getChildren().addAll(nameL,name);
                el2.getChildren().addAll(choose);
                el3.getChildren().addAll(tableX);
                el4.getChildren().addAll(adder,confirmer);
                root3.getChildren().addAll(el1,el2,el3,el4);
                Scene scene = new Scene(root3);
                Stage window = new Stage();
                window.setScene(scene);
                window.show();

                confirmer.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        if(listOfNewDishIngr.size()>=2) {
                            Recipe newRecp = new Recipe(name.getText(), listOfNewDishIngr);
                            newRecp.setOpinion();
                            recipesList.add(newRecp);
                            window.close();
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.WARNING, "YOU NEED TO ADD AT LEAST 2 INGREDIENTS!!!", ButtonType.OK);
                            alert.showAndWait();
                        }
                    }
                });

            }
        });

        spag.setOpinion();
        cranPie.setOpinion();
        disaster.setOpinion();
        //StackPane root = new StackPane();
        //Group root= new Group();
        VBox root = new VBox();
        root.setPadding(new Insets(8, 0, 0, 8));
        root.setSpacing(4);

        Label title = new Label("Cook Book");
        title.setFont(new Font("Verdena", 28));
        TableColumn name = new TableColumn("Recipe Name");
        table.getColumns().add(name);
        name.setMinWidth(404);
        name.setCellValueFactory(new PropertyValueFactory<Recipe, String>("Name"));
        table.setItems(recipesList);
        table.setMaxWidth(406);
        table.setMaxHeight(370);

        Button openButton = new Button("Open Recipe");
        openButton.setPadding(new Insets(10, 0, 10, 0));
        openButton.setFont(new Font("Verdena", 13));
        openButton.setMinWidth(406);
        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Recipe rec = (Recipe) table.getSelectionModel().getSelectedItem();
//                VBox root2 = new VBox();
//                HBox el1 = new HBox();
//                HBox el2 = new HBox();
//                HBox el3 = new HBox();
//                root2.setPadding(new Insets(8, 0, 0, 8));
//                root2.setSpacing(2);
//
//                //root2.setPrefSize(440, 100);
//                root2.setMinHeight(100);
//                root2.setMinWidth(440);
//
//                Label name= new Label("Name: ");
//                Label n = new Label(rec.getName());
//                name.setFont(new Font("Verdena", 14));
//                n.setFont(new Font("Verdena", 14));
//
//                Label desc= new Label("Description: ");
//                Label d = new Label(rec.getOpinion());
//                desc.setFont(new Font("Verdena", 14));
//                d.setFont(new Font("Verdena", 14));
//
//                Label ingr= new Label("Ingredients: ");
//                Label i = new Label(rec.getIngredients());
//                ingr.setFont(new Font("Verdena", 14));
//                i.setFont(new Font("Verdena", 14));
//
//                el1.getChildren().addAll(name,n);
//                el1.setBackground(new Background(new BackgroundFill(Color.SILVER,
//                        CornerRadii.EMPTY,
//                        Insets.EMPTY)));
//                el1.setMaxWidth(430);
//                el2.getChildren().addAll(desc,d);
//                el2.setBackground(new Background(new BackgroundFill(Color.GRAY,
//                        CornerRadii.EMPTY,
//                        Insets.EMPTY)));
//                el2.setMaxWidth(430);
//                el3.getChildren().addAll(ingr,i);
//                el3.setBackground(new Background(new BackgroundFill(Color.SILVER,
//                        CornerRadii.EMPTY,
//                        Insets.EMPTY)));
//                el3.setMaxWidth(430);
//                root2.getChildren().addAll(el1,el2,el3);
//                Scene scene = new Scene(root2);
//                Stage window = new Stage();
//                window.setScene(scene);
//                window.show();

                TableView tableXX= new TableView();
                ObservableList<Recipe> chosenRecList = FXCollections.observableArrayList(rec);
                VBox root2 = new VBox();
                root2.setPadding(new Insets(8, 0, 0, 8));
                root2.setSpacing(2);
                root2.setPrefSize(920, 82);
                TableColumn nameI = new TableColumn("Name");
                TableColumn nameII = new TableColumn("Description");
                TableColumn nameIII = new TableColumn("Ingredients");
                tableXX.getColumns().addAll(nameI,nameII,nameIII);
                nameI.setMinWidth(300);
                nameII.setMinWidth(300);
                nameIII.setMinWidth(300);
                nameI.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
                nameII.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("opinion"));
                nameIII.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("ingredientString"));
                tableXX.setItems(chosenRecList);
                tableXX.setMinWidth(900);
                tableXX.setMaxWidth(900);
                tableXX.setMaxHeight(63);
                root2.getChildren().addAll(tableXX);
                Scene scene = new Scene(root2);
                Stage window = new Stage();
                window.setScene(scene);
                window.show();
            }
        });

        root.getChildren().addAll(menubar,title,table,openButton);
        Scene scene = new Scene(root,420,500);
        stage.setScene(scene);
        stage.setTitle("Recipe Reader");
        stage.show();
    }
    public ArrayList<Ingredient> getBologneseList(){
        ArrayList<Ingredient> x = new ArrayList<>();
        x.add(pasta);
        x.add(tomato);
        x.add(garlic);
        x.add(beef);
        return x;
    }
    public ArrayList<Ingredient> getCPList(){
        ArrayList<Ingredient> x = new ArrayList<>();
        x.add(cranberries);
        x.add(butter);
        x.add(flour);
        x.add(egg);
        return x;
    }
    public ArrayList<Ingredient> getDisaster(){
        ArrayList<Ingredient> x = new ArrayList<>();
        x.add(pasta);
        x.add(tomato);
        x.add(lemon);
        x.add(cranberries);
        x.add(strawberries);
        return x;
    }
}

