public class Mixer extends ModuleAbstract{
    public Mixer(String name, int NbInputPorts){
        super(name,NbInputPorts,1);
    }

    public void exec(){
        double sum = 0.0;
        for(int i = 0; i < this.getNbInputPorts(); i++){
            sum += getInputPortValue(i);
        }
        setAndSendOutputPortValue(0,sum);
    }
}
