public class TestSon{
  public static void main(String[] arg){
    Patch p = new Patch("test");
    GenSineBasic s = new GenSineBasic("gen", 442, 1.0);
    AudioDataReceiver output1 = new AudioDataReceiver("output1");
    p.addModule(s);
    p.addModule(output1);
    p.connect("gen",0,"output1",0);
    AudioDataReceiver output2 = new AudioDataReceiver("output2");
    //if(output1 == null) throw new IllegalArgumentException("nnnnnnnnnnnnn");
    //Connexion w = ModuleAbstract.connect(s.getOutputCommunicationPort(0),output1.getInputCommunicationPort(0));
    //Connexion x = ModuleAbstract.connect(output1.getOutputCommunicationPort(0),output2.getInputCommunicationPort(0));
    /**for(int i = 0 ; i<5*44100 ; i++){
      s.exec();
      output1.exec();
      output2.exec();
     // o.displayAudioDataWaveform();
      /**o.saveAudioDataToWavFile("test1.wav");*/
     // o.playAudioData();
   //}
    p.exec(3*44100);
    output1.displayAudioDataWaveform();
    output1.playAudioData();
    //output1.saveAudioDataToWavFile("test1.wav");
    //output2.displayAudioDataWaveform();
    //output2.playAudioData();
  }
}
