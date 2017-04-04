import phelmaaudio.*;

public class AudioDataReceiver extends ModuleAbstract{
  private AudioData a;

  public AudioDataReceiver(String name){
    super(name, 1, 1);
    this.a = new AudioData();
  }

  @Override
  public void exec(){
    double e = getInputPortValue(0);
		a.addSample(e);
        setOutputPortValue(0, e);
	}

  /**void saveAudioDataToWavFile(String audioFileName){
    a.saveAudioDataToWavFileNormalized(audioFileName);
  }*/

  void displayAudioDataWaveform(){
    a.display();
  }

  void playAudioData(){
    a.play();
  }

}
