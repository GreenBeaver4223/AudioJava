public class Spliter extends ModuleAbstract{
    public Spliter(String name, int NbOutputPorts){
        super(name,1,NbOutputPorts);
    }

    public void exec(){
        for(int i = 0; i < this.getNbOutputPorts(); i++){
            setAndSendOutputPortValue(i,getInputPortValue(i));
        }       
    }
}
