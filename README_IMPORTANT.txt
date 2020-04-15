il faut ajouter javafx.media dans les options vm pour que le programme marche,
les vm options vont ressembler à ca
WINDOWS
--module-path "path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml,javafx.media
LINUX
--module-path path/to/javafx/lib --add-modules javafx.controls,javafx.fxml,javafx.media