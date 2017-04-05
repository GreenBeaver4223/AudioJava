import java.io.IOException;
import phelmaaudio.*;

public class TestSon{
  public static void main(String[] arg){
    Patch p = new Patch("test");
    GenSineBasic s1 = new GenSineBasic("gen1", 442, 1.0); 
    GenSineBasic s2 = new GenSineBasic("gen2", 742, 1.0);
    GenSineBasic s3 = new GenSineBasic("gen3", 542, 1.0);
    Multiplier m = new Multiplier("mixer",3);
    AudioDataReceiver output1 = new AudioDataReceiver("output1"); 
    AudioDataReceiver output3 = new AudioDataReceiver("output3");
    p.addModule(s1);
    p.addModule(s2);
    p.addModule(s3);
    p.addModule(output1);
   // p.addModule(output2);
   // p.addModule(output3);
    p.addModule(m);
    p.connect("gen1",0,"mixer",0);
    p.connect("gen2",0,"mixer",1);
    p.connect("gen3",0,"mixer",2);
    p.connect("mixer",0,"output1",0);
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
   /* try{
      output1.saveAudioDataToWavFile("test1.wav");
    } catch(IOException e){}
    catch(WavFileException e){}
    //output2.displayAudioDataWaveform();
    //output2.playAudioData();*/
  }
}
