import java.util.LinkedList;
import java.util.Iterator;

public class Patch{
  private String nom;
  private LinkedList<ModuleAbstract> listeModules;
  private LinkedList<Connexion> listeConnexions;

  public Patch(String nom){
    this.nom = nom;
    listeModules = new LinkedList<ModuleAbstract>();
    listeConnexions = new LinkedList<Connexion>();
  }

  public void addModule(ModuleAbstract m){
    listeModules.add(m);
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
    try{
			a = trouverModule(nameOfOutputModule);
      b = trouverModule(nameOfInputModule);
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Un des deux modules a connecter n'existe pas");
		}
    Connexion c = ModuleAbstract.connect(a, idOutputPort, b, idInputPort);
    listeConnexions.add(c);
  }

  public void exec(){

  }
}
