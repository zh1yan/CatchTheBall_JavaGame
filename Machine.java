package Cartoon;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.layout.Pane;



public class Machine {
	
	private Circle _ballPitcher;
	private Rectangle _rec;
	private Rectangle _upRec;
	private Rectangle _downRec;
	private Line _centerHolder;
	private Line _leftHolder;
	private Line _rightHolder;
	
	public Machine(Pane machinePane) {
		_ballPitcher = new Circle(Constants.BALLPITCHER_POSITIONX,Constants.BALLPITCHER_POSITIONY,Constants.BALL_RADIUS,Constants.ballPitcherColor);
		_ballPitcher.setStroke(Color.BLACK);
	    _ballPitcher.setStrokeWidth(Constants.BALLPITCHER_STROKE);
		
	    _rec = new Rectangle(Constants.REC_POSITIONX, Constants.REC_POSITIONY, Constants.REC_WIDTH, Constants.REC_HEIGHT);
		_rec.setFill(Constants.MACHINE_COLOR);
		_upRec = new Rectangle(Constants.UPREC_POSITIONX,Constants.UPREC_POSITIONY,Constants.UPREC_WIDTH,Constants.UPREC_HEIGHT);
		_downRec = new Rectangle(Constants.DOWNREC_POSITIONX,Constants.DOWNREC_POSITIONY,Constants.DOWNREC_WIDTH,Constants.DOWNREC_HEIGHT);
		_upRec.setFill(Constants.MACHINE_COLOR);
		_downRec.setFill(Constants.MACHINE_COLOR);
	    
	    _leftHolder = new Line(395.0, 150.0,360.0, 240.0);
	    _leftHolder.setStroke(Constants.HOLDER_COLOR);
	    _leftHolder.setStrokeWidth(Constants.HOLDER_STROKE-1);
	    
	    _rightHolder = new Line(395.0, 150.0,430.0, 240.0);
	    _rightHolder.setStroke(Constants.HOLDER_COLOR);
	    _rightHolder.setStrokeWidth(Constants.HOLDER_STROKE-1);
	    
	    _centerHolder = new Line(395,150,395,240);
	    _centerHolder.setStroke(Constants.HOLDER_COLOR);
	    _centerHolder.setStrokeWidth(Constants.HOLDER_STROKE);
		
		machinePane.getChildren().addAll(_leftHolder,_rightHolder,_centerHolder,_rec,_upRec,_downRec,_ballPitcher);
	
	}
	
	public double getPositionX() {
		return _ballPitcher.getCenterX();
	}
	
	public double getPositionY() {
		return _ballPitcher.getCenterY();
	}
	
	public void move(double n) {
		_rec.setX(_rec.getX()+n);
		_upRec.setX(_upRec.getX()+n);
		_downRec.setX(_downRec.getX()+n);
		_ballPitcher.setCenterX(_ballPitcher.getCenterX()+n);
		
		_centerHolder.setStartX(_centerHolder.getStartX()+n);
		_centerHolder.setEndX(_centerHolder.getEndX()+n);
		_leftHolder.setStartX(_leftHolder.getStartX()+n);
		_leftHolder.setEndX(_leftHolder.getEndX()+n);
		_rightHolder.setStartX(_rightHolder.getStartX()+n);
		_rightHolder.setEndX(_rightHolder.getEndX()+n);
		
	}


}
