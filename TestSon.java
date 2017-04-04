public class TestSon{
  public static void main(String[] arg){
    GenSineBasic s = new GenSineBasic("gen", 442, 1.0);
    AudioDataReceiver output1 = new AudioDataReceiver("output1");
    AudioDataReceiver output2 = new AudioDataReceiver("output2");
    if(output1 == null) throw new IllegalArgumentException("nnnnnnnnnnnnn");
    ModuleAbstract.connect(s.getOutputCommunicationPort(0),output1.getInputCommunicationPort(0));
    ModuleAbstract.connect(output1.getOutputCommunicationPort(0),output2.getInputCommunicationPort(0));
    for(int i = 0 ; i<5*44100 ; i++){
      s.exec();
      output1.exec();
      output2.exec();
     // o.displayAudioDataWaveform();
      /**o.saveAudioDataToWavFile("test1.wav");*/
     // o.playAudioData();
    } 
    output1.displayAudioDataWaveform();
    output1.playAudioData();
    output2.displayAudioDataWaveform();
    output2.playAudioData();
  }
}
