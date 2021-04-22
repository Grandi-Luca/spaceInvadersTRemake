package menu.factories;

import java.awt.Color;

import javax.swing.JSlider;

import menu.Board;
import util.Constants;

/**
 *A simple slider factory to avoid repetitions.
 */
public class SliderFactory {

	private JSlider slider = new JSlider(JSlider.HORIZONTAL, Constants.minSliderValue, Constants.maxSliderValue, (int) Constants.VOLUME_LEVEL_START);
	
	/**
	 * A method that create the slider.
	 * @param board
	 * @return the slider with the listener.
	 */
	public JSlider create(Board board){
		
		this.slider.setBackground(Color.black);
		this.slider.setMajorTickSpacing(Constants.SPACING);
		this.slider.setForeground(Color.white);
		this.slider.setPaintLabels(true);
		this.slider.setPaintTicks(true);
		this.slider.addChangeListener(e->{
				board.getAudio().setVolume((float)slider.getValue()/Constants.maxSliderValue);
		});
		
		return this.slider;
	}
}
