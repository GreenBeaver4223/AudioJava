import phelmaaudio.*;

public class AudioDataReceiver extends ModuleAbstract{
  private AudioData a;

  public AudioDataReceiver(){
    super("AudioDataReceiver", 1, 1);
  }

  @Override
  public void exec(){
    double e = getInputPortValue(0);
		a.addSample(e);
    setAndSendOutputPortValue(0, e);
	}

  void saveAudioDataToWavFile(String audioFileName){
    a.saveAudioDataToWavFileNormalized(audioFileName);
  }

  void displayAudioDataWaveform(){
    a.display();
  }

  void playAudioData(){
    a.play();
  }

}
