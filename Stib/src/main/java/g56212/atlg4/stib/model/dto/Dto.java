package g56212.atlg4.stib.model.dto;


import java.util.Objects;

public class Dto<K> {
    protected K key;
    public Dto(K key){
        if(key==null)
            throw new IllegalArgumentException("no key");
        this.key=key;
    }
    public K geyKey(){
        return this.key;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.key);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dto<?> other = (Dto<?>) obj;
        return Objects.equals(this.key, other.key);
    }


}

