package Cartoon;

import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.input.KeyEvent;



public class Glove{
	
	private ImageView _glove;
	private ImageView _gloveGrab;
	
	public Glove(Pane glovePane) {
	    Image image = new Image(this.getClass().getResourceAsStream("glove.png"));
		_glove = new ImageView();
        _glove.setImage(image);
        _glove.setRotate(70);
        _glove.setX(Constants.GLOVE_POSITIONX);
        _glove.setY(Constants.GLOVE_POSITIONY);
        _glove.setFitWidth(Constants.GLOVE_FITWIDTH);
        _glove.setFitHeight(Constants.GLOVE_FITHEIGHT);
        
        Image image_grab = new Image(this.getClass().getResourceAsStream("glove_grab.png"));
        _gloveGrab = new ImageView();
        _gloveGrab.setImage(image_grab);
        _gloveGrab.setRotate(70);
        _gloveGrab.setX(Constants.GLOVE_POSITIONX);
        _gloveGrab.setY(Constants.GLOVE_POSITIONY);
        _gloveGrab.setFitWidth(Constants.GLOVE_FITWIDTH);
        _gloveGrab.setFitHeight(Constants.GLOVE_FITHEIGHT);
        _gloveGrab.setVisible(false);
        
        
        _glove.setPreserveRatio(true);
        _glove.setSmooth(true);
        _glove.setCache(true);
        glovePane.getChildren().addAll(_glove,_gloveGrab);
        
        //register the pane that contains glove with EventHandler, which move the glove according to keyboard arrow pressed.
        glovePane.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
        	public void handle(KeyEvent keyEvent){
        		switch (keyEvent.getCode()) {
        			case UP:
        				_glove.setY(_glove.getY()-20);
        				_gloveGrab.setY(_gloveGrab.getY()-20);
        				break;
        			case DOWN:
        				_glove.setY(_glove.getY()+20);
        				_gloveGrab.setY(_gloveGrab.getY()+20);
        				break;
        			case LEFT:
        				_glove.setX(_glove.getX()-20);
        				_gloveGrab.setX(_gloveGrab.getX()-20);
        				break;
        			case RIGHT:
        				_glove.setX(_glove.getX()+20);
        				_gloveGrab.setX(_gloveGrab.getX()+20);
        			default:
        				break;
        		}
        		keyEvent.consume();
        	}
        });
        
        

	}
	
	public void changeGlovevisibility() {
    	if (_glove.isVisible()) {
    		_glove.setVisible(false);
    	}else {
    		_glove.setVisible(true);
    	}
    }
	
	public void changeGloveGrabVisibility() {
		if (_gloveGrab.isVisible()) {
			_gloveGrab.setVisible(false);
		}else {
			_gloveGrab.setVisible(true);
		}
	}
	
	public boolean getGloveVisibility() {
		return _glove.isVisible();
	}
	
	public double getX() {
		return _glove.getX();
	}
	
	public double getY() {
		return _glove.getY();
	}

}
