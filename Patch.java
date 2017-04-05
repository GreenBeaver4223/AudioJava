import java.util.LinkedList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class Patch{
  private String nom;
  private LinkedList<ModuleAbstract> listeModules;
  private LinkedList<Connexion> listeConnexions;
  private Map<String, ModuleAbstract> dicoNomModule;

  public Patch(String nom){
    this.nom = nom;
    listeModules = new LinkedList<ModuleAbstract>();
    listeConnexions = new LinkedList<Connexion>();
    dicoNomModule = new HashMap<String, ModuleAbstract>();
  }

  public void addModule(ModuleAbstract m){
    listeModules.add(m);
    dicoNomModule.put(m.getName(), m);
  }

  public ModuleAbstract trouverModule(String nom){
		Iterator<ModuleAbstract> it = listeModules.iterator();
		while(it.hasNext()){
		    ModuleAbstract a = it.next();
        if(a.getName() == nom){
          return a;
        }
		}
		throw new IllegalArgumentException("Pas de Module avec ce nom");
	}

  public void connect(String nameOfOutputModule, int idOutputPort, String nameOfInputModule, int idInputPort){
    ModuleAbstract a;
    ModuleAbstract b;
    /**try{
			a = trouverModule(nameOfOutputModule);
      b = trouverModule(nameOfInputModule);
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Un des deux modules a connecter n'existe pas");
		}*/
    if(dicoNomModule.containsKey(nameOfOutputModule) && dicoNomModule.containsKey(nameOfOutputModule)){
      a = dicoNomModule.get(nameOfOutputModule);
      b = dicoNomModule.get(nameOfInputModule);
    }
    else{
      throw new IllegalArgumentException("Un des deux modules a connecter n'existe pas");
    }
    Connexion c = ModuleAbstract.connect(a, idOutputPort, b, idInputPort);
    listeConnexions.add(c);
  }

  public void exec(){
    Iterator<ModuleAbstract> it = listeModules.iterator();
    while(it.hasNext()){
		    ModuleAbstract a = it.next();
        a.exec();
    }
  }

  public void exec(int nbStep){
    for(int i = 0 ; i<nbStep ; i++){
      Iterator<ModuleAbstract> it = listeModules.iterator();
      while(it.hasNext()){
  		    ModuleAbstract a = it.next();
          a.exec();
      }
    }
  }

  public void reset(){
    Iterator<ModuleAbstract> it = listeModules.iterator();
		while(it.hasNext()){
		    ModuleAbstract a = it.next();
        a.reset();
		}
  }

  public LinkedList<CommunicationPort> getUnconnectedInputPorts(){
    CommunicationPort list = new LinkedList<CommunicationPort>();
    Iterator<ModuleAbstract> it = listeModules.iterator();
		while(it.hasNext()){
		    ModuleAbstract a = it.next();
        list.addAll(a.getUnconnectedInputPorts)
		}
    return list;
  }

  public LinkedList<CommunicationPort> getUnconnectedOutputPorts(){
    CommunicationPort list = new LinkedList<CommunicationPort>();
    Iterator<ModuleAbstract> it = listeModules.iterator();
		while(it.hasNext()){
		    ModuleAbstract a = it.next();
        list.addAll(a.getUnconnectedOutputPorts)
		}
    return list;
  }
}
