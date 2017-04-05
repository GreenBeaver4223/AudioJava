public class Multiplier extends ModuleAbstract{
    public Multiplier(String name, int NbInputPorts){
        super(name,NbInputPorts,1);
    }

    public void exec(){
        double mult = 1.0;
        for(int i = 0; i < this.getNbInputPorts(); i++){
            mult *= getInputPortValue(i);
        }
        setAndSendOutputPortValue(0,mult);
    }
}
