import phelmaaudio.*;
public class AudioDataProvider extends ModuleAbstract{
	private AudioData audioData;
	private int nbStep = 0;
	public AudioDataProvider(String name,AudioData audioData){
		super(name,0,1);
		this.audioData = audioData;
	}
	public void exec(){
		if(audioData != null){
			double e = audioData.getSample(nbStep);
			setAndSendOutputPortValue(0,e);
			this.nbStep++;
		}else{
			throw new IllegalArgumentException("null pointer");
		}
	}
	public void reset(){
		this.nbStep = 0;
	}
}
