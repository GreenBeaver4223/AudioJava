import java.io.IOException;
import phelmaaudio.*;

public class TestSon{
  public static void main(String[] arg){
    Patch p = new Patch("test");
    //Constant f = new Constant("freq",0);
    //Constant a = new Constant("amp",5.0);
    GenSineBasic s1 = new GenSineBasic("gen1", 139, 1.0); 
    //Bruit c = new Bruit("square");
    GenSineBasic s2 = new GenSineBasic("gen2", 165, 0.9);
    GenSineBasic s3 = new GenSineBasic("gen3", 196, 0.7);
    GenSineBasic s4 = new GenSineBasic("gen4", 233, 0.4);
    EnvelopADSR en = new EnvelopADSR("env",1,2,1);
    Mixer m = new Mixer("mixer",4);
    Mixer mix = new Mixer("mix",2);
    AudioDataReceiver output1 = new AudioDataReceiver("output1"); 
    //AudioDataReceiver output3 = new AudioDataReceiver("output3");
    p.addModule(s1);
    p.addModule(s2);
    p.addModule(s3);
    p.addModule(s4);
    p.addModule(en);
   // p.addModule(f);
   // p.addModule(a);
   // p.addModule(s2);
   // p.addModule(s3);
   // p.addModule(c);
    p.addModule(output1);
   // p.addModule(output2);
   // p.addModule(output3);
    p.addModule(m);
    p.addModule(mix);
    p.connect("gen1",0,"mixer",0);
    p.connect("gen2",0,"mixer",1);
    p.connect("gen3",0,"mixer",2);
    p.connect("gen4",0,"mixer",3);
   // p.connect("amp",0,"square",1);
   // p.connect("gen1",0,"square",0);
   // p.connect("gen3",0,"square",1);
   // p.connect("gen3",0,"mult",0);
   // p.connect("square",0,"mult",1);
   // p.connect("square",0,"mixer",2);
    p.connect("mixer",0,"mix",0);
    p.connect("env",0,"mix",1);
    p.connect("mix",0,"output1",0);
   // AudioDataReceiver output2 = new AudioDataReceiver("output2");
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
