public class TestSon{
  public static void main(String[] arg){
    GenSineBasic s = new GenSineBasic("gen", 442, 1.0);
    AudioDataReceiver o = new AudioDataReceiver("output");
    ModuleAbstract.connect(s.getOutputCommunicationPort(0),o.getInputCommunicationPort(0));
    for(int i = 0 ; i<5*44100 ; i++){
      s.exec();
      o.exec();
      o.displayAudioDataWaveform();
      /**o.saveAudioDataToWavFile("test1.wav");*/
      o.playAudioData();
    }
  }
}
