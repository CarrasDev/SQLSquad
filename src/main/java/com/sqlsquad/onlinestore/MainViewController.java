package com.sqlsquad.onlinestore;

import com.sqlsquad.onlinestore.view.AddPedidos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainViewController {

    @FXML
    private AnchorPane viewPane;  // Contenedor para la vista dinámica

    @FXML
    private AnchorPane detailsPane;  // Área para información adicional (se puede actualizar de forma similar)

    // Metodo de inicialización: se invoca cuando se carga el FXML
    @FXML
    public void initialize() {
        // Cargamos una vista predeterminada, por ejemplo, la vista de Artículos.
         loadView("add-articulos.fxml");
    }

    // Maneja el clic en el botón "Artículos"
    @FXML
    private void handleArticulos(ActionEvent event) {
        loadView("add-articulos.fxml");
    }

    // Maneja el clic en el botón "Mostrar Artículos"
    @FXML
    private void handleLookArticulos(ActionEvent event) {
        loadView("look-articulos.fxml");
    }

    // Maneja el clic en el botón "Clientes"
    @FXML
    private void handleClientes(ActionEvent event) {
        loadView("add-clientes.fxml");
    }

    // Maneja el clic en el botón "Mostrar Clientes"
    @FXML
    private void handleLookClientes(ActionEvent event) {
        loadView("look-clientes.fxml");
    }

    // Maneja el clic en el botón "Pedidos"
    @FXML
    private void handlePedidos(ActionEvent event) {
        loadView("add-pedidos.fxml");
    }

    @FXML
    private void handleLookPedidos(ActionEvent event) {
        loadView("look-pedidos.fxml");
    }

    /**
     * Metodo para cargar una vista FXML en el contenedor 'viewPane'
     * @param fxmlFile El nombre del archivo FXML a cargar
     */


    public void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent view = loader.load();

            // Obtener el controlador de la nueva vista
            Object controller = loader.getController();

            // Si el controlador es de tipo 'AddPedidos', pásale la instancia de 'MainViewController'
            // Permite que la vista 'AddPedidos' pueda acceder a la vista 'MainViewController' Para crear un nuevo cliente
            if (controller instanceof AddPedidos) {
                ((AddPedidos) controller).setMainViewController(this);
            }

            viewPane.getChildren().clear();
            viewPane.getChildren().add(view);
            // Aseguramos que la vista se redimensione (anclándola a los 4 lados)
            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



