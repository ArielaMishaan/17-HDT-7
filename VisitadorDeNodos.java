import java.util.ArrayList;

public class VisitadorDeNodos<K, V> implements ITraversal<K,V> {

    private ArrayList <Association> miListado = new ArrayList<Association>();

    @Override
    public void visit(TreeNode<K, V> actualNode) {
        // TODO Auto-generated method stub
        Association asociacionActual = (Association) actualNode.getValue();
        miListado.add(asociacionActual);
    }

    public ArrayList<Association> getMiListado() {
        return this.miListado;
    }

    public void setMiListado(ArrayList<Association> miListado) {
        this.miListado = miListado;
    }
    
}
