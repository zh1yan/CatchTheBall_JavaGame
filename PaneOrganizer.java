package Cartoon;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.text.Font;


public class PaneOrganizer{

  private BorderPane _root;
  private Machine _machine;
  private Timeline _timeline1;
  private Timeline _timeline2;
  private Timeline _timeline3;
  private Pane _glovePane;
  private Glove _glove;
  private double _ballPositionX;
  private double _ballPositionY;
  private int _point = 0;
  private Label _label1;
  private boolean _isCatched = false;
  private Ball _ball = null;
  private VBox _vBox;
  private Label _label3;


  public PaneOrganizer(){
	  
	//add glove, machine, buttons, labels, slider to the pane
	  
    _root = new BorderPane();
    Pane machinePane = new Pane();
    //machinePane.setFocusTraversable(true);
    //machinePane.requestFocus();
    _machine = new Machine(machinePane);
    _root.setCenter(machinePane);

    _glovePane = new Pane();
    _glove = new Glove(_glovePane);
    _root.getChildren().add(_glovePane);
    _glovePane.setFocusTraversable(true);
    _glovePane.requestFocus();
    
    _timeline1 = new Timeline();
    _timeline2 = new Timeline();
    
    this.setupTimeline();
    this.setUpGame();

    
    BackgroundImage image = new BackgroundImage(new Image(this.getClass().getResourceAsStream("background.png")), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    Background background = new Background(image);
    _root.setBackground(background);
    
    _vBox = new VBox();
	_root.setRight(_vBox);
	_root.setMargin(_vBox, new Insets(12,12,12,30));
	_root.setAlignment(_vBox, Pos.TOP_RIGHT);
    this.setUpLabel(); 
    this.setUpSlider();
    this.setUpButton();
    _vBox.setFocusTraversable(false);
	
  }

  
  public Pane getRoot(){
    return _root;
  }
  
  private void setUpButton() {
	  HBox buttonPane = new HBox();
	  _root.setTop(buttonPane);
	  Button b1 = new Button("Start");
	  Button b2 = new Button("Stop");
	  Button b3 = new Button("Quit");
	  buttonPane.getChildren().addAll(b1,b2,b3);
	  buttonPane.setSpacing(30);
	  
	  //register the buttons with EventHandler that controls the start/stop of the timeline.
	  b1.setOnAction(new ClickHandler(true));
	  b2.setOnAction(new ClickHandler(false));
	  b3.setOnAction(new QuitHandler());
	  
	  buttonPane.setFocusTraversable(false);
  }
  
  public void setUpLabel() {
	  _label1 = new Label("Your score is  "+Integer.toString(_point));
	  _vBox.getChildren().add(_label1);
	  _label1.setFont(new Font(15));
	  
	  Label label2 = new Label("Arrows control the glove;  \nPress space to catch ball; \nSlider changes speed of ball. ");
	  _root.setMargin(label2, new Insets(12,12,12,12));
	  label2.setFont(new Font(15));
	  _root.setBottom(label2);
	  
	  _label3 = new Label("Current speed: " + String.format("%.2f", Ball.ACCELERATION_VAR));
	  _vBox.getChildren().add(_label3);
  }
  
  public void setUpSlider() {
	  Slider slider = new Slider(0, 0.1, 0.05);
	  slider.valueProperty().addListener(new SliderListener());
	  _vBox.getChildren().add(slider);
	  _vBox.setSpacing(20);
  }
  
  private class SliderListener implements ChangeListener<Number> {

	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		// set the ratio of acceleration
		Ball.ACCELERATION = newValue.doubleValue();
		_glovePane.setFocusTraversable(true);
		_glovePane.requestFocus();
	}
	  
  }
  
  public void setupTimeline() {
	  
	  KeyFrame machineFrame = new KeyFrame(Duration.millis(5),new MachineHandler());
	  KeyFrame ballFrame = new KeyFrame(Duration.seconds(2), new BallGenerateHandler());

	  _timeline1.getKeyFrames().add(machineFrame);
	  //add ballframe to the second timeline so machine and ball can move at the same time
	  _timeline2.getKeyFrames().add(ballFrame);
	  _timeline1.setCycleCount(Animation.INDEFINITE);
	  _timeline2.setCycleCount(Animation.INDEFINITE);
	  
	  KeyFrame ballMovingFrame = new KeyFrame(Duration.millis(15), new BallMovingHandler());
	  _timeline3 = new Timeline();
	  _timeline3.getKeyFrames().add(ballMovingFrame);
	  _timeline3.setCycleCount(Animation.INDEFINITE);

  }
  
 
  private class ClickHandler implements EventHandler<ActionEvent>{
	  private boolean _isStart;
	  
	  public ClickHandler (boolean isStart) {
		  _isStart = isStart;
	  }
	  public void handle(ActionEvent event) {
		  if (_isStart) {
			  _timeline1.play();
			  _timeline2.play();
		  }else {
			  _timeline1.stop();
		  }
		  // give focus back to _glovePane after clicking on the button
		  _glovePane.setFocusTraversable(true);
		  _glovePane.requestFocus();
	  }
  }
  
  private class MachineHandler implements EventHandler<ActionEvent>{
	  
	  private double _dis;
	  
	  public MachineHandler () {
		  _dis = 1;
		  
	  }
	
	  public void handle(ActionEvent e) {
		  if ((_machine.getPositionX() >= Constants.APP_WIDTH) || (_machine.getPositionX() <=0)) {
			  _dis *= -1;
		  }
			 
		  _machine.move(_dis);
	  }
  }
  
  public class BallGenerateHandler implements EventHandler<ActionEvent>{
	  
	  public BallGenerateHandler() {
	
	  }
	  
	  public void handle(ActionEvent e) {
		  _ball = new Ball(_machine);
		  //Pane ballPane = new Pane();
		  _ball.addBallToPane(_glovePane);
		  //_root.getChildren().add(ballPane);
		  _timeline3.play();
	  }
	  
  }
  
  public class BallMovingHandler implements EventHandler<ActionEvent>{
	  
	  public BallMovingHandler() {
		  
	  }
	// give the movement to the ball after it is instantiated 
	  public void handle(ActionEvent e) {
		  _ball.move();
		  _label3.setText("Current speed: " + String.format("%.2f", Ball.ACCELERATION_VAR));
		  //set blur effect on ball with the speed up
		  if (Ball.ACCELERATION_VAR>6) {
			  _ball.setBlur();
		  }
		  _ballPositionX = _ball.getBallPostionX();
		  _ballPositionY = _ball.getBallPositionY();
		  if (_isCatched == true){
			 _ball.ballFadeOut();
		  }
		  e.consume();
	  }  
  }
  
  public void setUpGame() {
	  _glovePane.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
      	public void handle(KeyEvent k){
    		if (k.getCode() == KeyCode.SPACE) {
    			_glove.changeGlovevisibility();
    			_glove.changeGloveGrabVisibility();
    			//detect whether ball within glove when grab
    			if (!_glove.getGloveVisibility() && (Math.abs(_ballPositionX-_glove.getX())<140) && (Math.abs(_ballPositionY-_glove.getY())<100)) {
    				_point += 10;
    				_label1.setText("Your score is "+Integer.toString(_point));
    				_isCatched = true;
    			}else {
    				_isCatched = false;
    			}
    			
    		}
    		k.consume();
      	}
	  });
  }
  
  private class QuitHandler implements EventHandler<ActionEvent>{

	public void handle(ActionEvent event) {
		Platform.exit();
	}
	  
  }
  
}