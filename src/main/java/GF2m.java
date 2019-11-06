import java.math.BigInteger;

public abstract class GF2m {
    protected BigInteger element;

    public void printValue() {
        System.out.println(element);
    };

    public void printBinaryValue(){
        System.out.println(element.toString(2));
    }

}
