package utiles;

import java.util.List;

public class FirstElement {

    public <T> T getFirstElement(List<T> listElements){
        T element = null;
        if(listElements.size() > 0){
            element = listElements.get(0);
        }

        return element;
    }

}
