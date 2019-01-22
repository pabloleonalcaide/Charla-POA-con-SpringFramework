package persistence.entities;

import javax.persistence.Embeddable;

@Embeddable
public class EmbeddableEntity {

    private int number;
    
    private String value;
   
    public EmbeddableEntity() {
    }

    public EmbeddableEntity(int number, String value) {
        this.number = number;
        this.value = value;
    }

    @Override
    public String toString() {
        return "EmbeddableEntity [number=" + number + ", value=" + value + "]";
    }

    public int getNumber() {
        return number;
    }

    public String getValue() {
        return value;
    }    
    
}
