package Cartoon;

import javafx.animation.FadeTransition;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class Ball{

  private ImageView _ball;
  private Pane _ballPane;
  public static double ACCELERATION_VAR = 0;
  public static double ACCELERATION = 0.01;

  public Ball(Machine machine){
	  _ballPane = new Pane();
	  Image image = new Image(this.getClass().getResourceAsStream("baseball.png"));
	  _ball = new ImageView();
      _ball.setImage(image);
      _ball.setX(machine.getPositionX());
      _ball.setY(machine.getPositionY());
      _ball.setFitWidth(Constants.BALL_FITWIDTH);
      _ball.setFitHeight(Constants.BALL_FITHEIGHT);
      _ball.setPreserveRatio(true);
      _ball.setSmooth(true);
      _ball.setCache(true);
      
      _ballPane.getChildren().add(_ball);
  }

  public void move(){
	  //randomly generate movement 
	  _ball.setX(_ball.getX()+5*Math.random());
	  _ball.setY(_ball.getY()+5*Math.random()+Ball.ACCELERATION_VAR);
	  //the ball is accelerating
	  Ball.ACCELERATION_VAR += Ball.ACCELERATION;
  }
  
  public double getAccelerationVar() {
	  return Ball.ACCELERATION_VAR;
  }
  
  
  public void addBallToPane(Pane pane) {
	  pane.getChildren().add(_ballPane);
  }
  
  public double getBallPostionX() {
	  return _ball.getX();
  }
  
  public double getBallPositionY() {
	  return _ball.getY();
  }
  
  //method that makes ball disappear
  public void ballFadeOut() {
	  FadeTransition ft = new FadeTransition(Duration.millis(3), _ball);
	  ft.setFromValue(1.0);
	  ft.setToValue(0);
	  ft.setCycleCount(1);
	  ft.setAutoReverse(true);
	  ft.play();
  }
  
  public void setBlur() {
	  BoxBlur boxBlur = new BoxBlur();
      boxBlur.setWidth(7);
      boxBlur.setHeight(3);
      boxBlur.setIterations(3);
      _ball.setEffect(boxBlur);
  }

  
  
  /* accelerate move(n)
	n ++    
	public void increaseSpeed() {
	 time.setRate(animation.getRate()+0.1);*/
}

