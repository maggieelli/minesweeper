import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class MinesweeperView {

	Group group;
	GridPane root;
	
	public MinesweeperView(int rows, int cols, double dimensions) {
		// TODO Auto-generated constructor stub
		group = new Group();
		Scene scene = new Scene(root, 500, 500);
		root = new GridPane();
		root.setPadding(new Insets(10, 10, 10, 10));
		group.getChildren().add(root);
	}
	
	public void setImageAt(Image img, int row, int col) {
		
	}

	public void setDimensions(int numRows, int numCols) {
		
	}
}
