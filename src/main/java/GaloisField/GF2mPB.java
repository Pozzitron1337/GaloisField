package GaloisField;

import java.math.BigInteger;
import java.lang.*;

/*Implementation of Galois Field with polynomial basis
 *
 * */
public class GF2mPB extends GF2m {
    public static BigInteger ZERO=new BigInteger("0");
    public static BigInteger ONE=new BigInteger("1");

    private BigInteger generator;
    private int generatorOrder;

    public GF2mPB(BigInteger element,BigInteger generator) {
        this.element = element;
        this.generator=generator;
        this.generatorOrder=generator.bitLength();
    }

    public GF2mPB(String bin,String generator, int radix){
        this.element=new BigInteger(bin,radix);
        this.generator=new BigInteger(generator,radix);
        this.generatorOrder=this.generator.bitLength();
    }

    public GF2mPB add(GF2mPB galoisFieldElement) {
        return new GF2mPB(element.xor(galoisFieldElement.element),generator);
    }

    public GF2mPB mul(GF2mPB galoisFieldElement) {
        BigInteger temp=new BigInteger("0");
        for (int i = 0; i< galoisFieldElement.element.bitLength(); i++){
            if(galoisFieldElement.element.testBit(i)){
                temp=temp.xor(element.shiftLeft(i));
            }
        }
        while (temp.bitLength()>=generatorOrder){
            temp=temp.xor(this.generator.shiftLeft(temp.bitLength()-generatorOrder));
        }
        return new GF2mPB(temp,generator);
    }

    public GF2mPB square(){
        return this.mul(this);
    }

    public GF2mPB trace() {
        GF2mPB sum=new GF2mPB(GF2mPB.ZERO,generator);
        GF2mPB temp1=new GF2mPB(element,generator);
        GF2mPB temp2=(GF2mPB) temp1.square();
        for(int i=3;i<generatorOrder;i++){
            sum=(GF2mPB) temp1.add(temp2);
            temp1=sum;
            temp2=(GF2mPB) temp2.square();
        }
        sum=(GF2mPB)temp1.add(temp2);
        return sum;
    }

    public GF2mPB pow(int exp){
        GF2mPB power =new GF2mPB(GF2mPB.ONE,generator);
        int binLen=Integer.toBinaryString(exp).length();
        for(int i=binLen-1;i>=0;i--){
            power=(GF2mPB) power.square();
            if(((exp>>i)&1)==1){
                power=(GF2mPB)power.mul(this);
            }
            //exp=(exp >> 1);
        }
        return power;
    }

    public GF2mPB pow(BigInteger exp){
        GF2mPB power=new GF2mPB(GF2mPB.ONE,generator);
        int len=exp.bitLength();
        for(int i=len-1;i>=0;i--){
            power=(GF2mPB) power.square();
            if(exp.testBit(i)){
                power=(GF2mPB)power.mul(this);
            }
        }
        return power;
    }

    public GF2mPB inv() {
        return this.pow(((BigInteger.ONE).shiftLeft(generatorOrder-1))
                                         .subtract(BigInteger.valueOf(2)));/*(1<<generatorOrder)-2*/
    }

    public String toString() {
        return element.toString(2);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        GF2mPB fieldElement=(GF2mPB) obj;
        if(this.element.compareTo(fieldElement.element)==0){
            return true;
        }else {
            return false;
        }
    }
}
