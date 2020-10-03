package GaloisField;

import java.math.BigInteger;

/*Implementation of Galois Field with optimal normal basis
*
* */
public class GF2mNB extends GF2m{
    public static BigInteger ZERO;
    public static BigInteger ONE;
    private int m;
    private int L[][];

    private void initialize_constants(){
        this.ZERO=BigInteger.ZERO;
        this.ONE=BigInteger.ONE.shiftLeft(m).subtract(BigInteger.ONE);
    }

    private void initialize_L(){
        int iterations=2;
        for (int i=1;i<=iterations;i++){
            int p=i*m+1;
            if(BigInteger.valueOf(p).isProbablePrime(1000)){
                int ord=1;
                BigInteger t=BigInteger.ONE.shiftLeft(1);
                while (t.mod(BigInteger.valueOf(p)).compareTo(BigInteger.ONE)!=0){
                    t=t.shiftLeft(1);
                    ord++;
                }
                if((BigInteger.valueOf(i)
                    .multiply(BigInteger.valueOf(m))
                    .divide(BigInteger.valueOf(ord)))
                    .gcd(BigInteger.valueOf(m))
                    .compareTo(BigInteger.ONE)==0) {
                    switch (i){
                        case 1:{
                            initialize_L_by_type1();
                            break;
                        }
                        case 2:{
                            initialize_L_by_type2();
                            break;
                        }
                    }
                    break;
                }else {
                    continue;
                }
            }
            if(i==iterations){
                throw new ArithmeticException("Not first and second type of m.Parametr m should be satisfied to this critea: \n" +
                        "1)T*m+1 should be prime. (Parameter T is type of gauss normal basis.Parameter T takes values {1,2}).\n"+
                        "2)gcd(T*m/k,m)=1, where parameter k=ord(2) by modulus (T*m+1) ");
            }
        }
    }

    private void initialize_L_by_type1() {
        /*
        there should be request to database
        instead of request there are true calculation
        */

        BigInteger one=BigInteger.ONE;
        BigInteger p = BigInteger.valueOf(m)
                                 .add(one);
        for(int i=0;i<m;i++){//line
            for(int j=i;j<m;j++){//colomn
                if(one.shiftLeft(i).add(one.shiftLeft(j)).mod(p).compareTo(BigInteger.ZERO)==0||
                   one.shiftLeft(i).add(one.shiftLeft(j)).mod(p).compareTo(one)==0){
                    L[i][j]=1;
                }
                else {
                    L[i][j]=0;
                }
            }
        }
        for (int j=0;j<(m-1);j++){
            for (int i=j+1;i<m;i++){
                L[i][j]=L[j][i];
            }
        }
    }

    private void initialize_L_by_type2(){
        /*
        there should be request to database
        instead of request there are true calculation
        */
        BigInteger one=BigInteger.ONE;
        BigInteger p=one.shiftLeft(1)
                        .multiply(BigInteger.valueOf(m))
                        .add(one);
        for(int i=0;i<m;i++){//line
            for(int j=i;j<m;j++){//colomn
                if(one.shiftLeft(i).add(one.shiftLeft(j)).mod(p).compareTo(one)==0 ||
                   one.shiftLeft(i).subtract(one.shiftLeft(j)).mod(p).compareTo(one)==0 ||
                   one.shiftLeft(i).negate().subtract(one.shiftLeft(j)).mod(p).compareTo(one)==0 ||
                   one.shiftLeft(i).negate().subtract(one.shiftLeft(j).negate()).mod(p).compareTo(one)==0
                ){
                    L[i][j]=1;
                }
                else {
                    L[i][j]=0;
                }
            }
        }
        for (int j=0;j<(m-1);j++){
            for (int i=j+1;i<m;i++){
                L[i][j]=L[j][i];
            }
        }
    }

    private GF2mNB(BigInteger element,int m,int[][] L){
        this.element=element;
        this.m=m;
        this.L=L;
        initialize_constants();
    }

    public GF2mNB(int element,int m){
        this.element=BigInteger.valueOf(element);
        this.m=m;
        this.L=new int[m][m];
        initialize_L();
        initialize_constants();
    }

    public GF2mNB(BigInteger element,int m){
        this.element=element;
        this.m=m;
        this.L=new int[m][m];
        initialize_L();
        initialize_constants();
    }

    public GF2mNB(String bin,int m,int radix){
        this.element=new BigInteger(bin,radix);
        this.m=m;
        this.L=new int[m][m];
        initialize_L();
        initialize_constants();
    }

    public GF2mNB add(GF2mNB galoisFieldElement) {
        return new GF2mNB(element.xor(galoisFieldElement.element),m,L);
    }

    public GF2mNB mul(GF2mNB galoisFieldElement) {
        GF2mNB u=new GF2mNB(this.element,m);
        GF2mNB v=new GF2mNB(galoisFieldElement.element,m);
        BigInteger z=BigInteger.ZERO;
        String binResult="";
        int[] t = new int[m];
        int c = 0;
        for (int k=0;k<m;k++){

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    if (u.element.testBit(m - j - 1) && L[i][j] == 1) {
                        c = (c + 1) & 1;
                    }
                }
                t[i] = c;
                c = 0;
            }
            for (int i = 0; i < m; i++) {
                if (v.element.testBit(m-i-1) && t[i] == 1) {
                    c = (c + 1) & 1;
                }
            }
            if(c==1){
                z = z.setBit(m-k-1);
            }
            c=0;
            u=(GF2mNB) u.cyclycShiftLeft(1);
            v=(GF2mNB) v.cyclycShiftLeft(1);
        }
        return new GF2mNB(z,m,L);
    }

    public GF2mNB cyclycShiftRigth(int shift) {
        if(element.bitLength()>m){
            return this;
        }
        int rest=shift%m;
        return new GF2mNB(ONE.and(element.shiftRight(rest)
                             .xor(element.shiftLeft(m-rest))),m,L);
    }

    public GF2mNB cyclycShiftLeft(int shift) {
        return cyclycShiftRigth(m-shift);
    }

    public GF2mNB square() {
        return cyclycShiftRigth(1);
    }

    public GF2mNB trace() {
        BigInteger iteration=BigInteger.ZERO;
        BigInteger x=element;
        while (x.compareTo(BigInteger.ZERO)!=0){
            x=(x.subtract(BigInteger.ONE)).and(x);
            iteration=iteration.and(BigInteger.ONE);
        }
        return new GF2mNB(iteration.and(BigInteger.ONE),m,L);
    }

    public GF2mNB pow(int exp) {
        GF2mNB power =new GF2mNB(ONE,m,L);
        int binLen=Integer.toBinaryString(exp).length();
        for(int i=binLen-1;i>=0;i--){
            power=(GF2mNB) power.square();
            if(((exp>>i)&1)==1){
                power=(GF2mNB)power.mul(this);
            }
        }
        return power;

    }

    public GF2mNB pow(BigInteger exp) {
        GF2mNB power=new GF2mNB( ONE,m,L);
        int len=exp.bitLength();
        for(int i=len-1;i>=0;i--){
            power=(GF2mNB) power.square();
            if(exp.testBit(i)){
                power=(GF2mNB)power.mul(this);
            }
        }
        return power;
    }

    public GF2mNB inv() {
        GF2mNB b=new GF2mNB(element,m,L);
        BigInteger h=BigInteger.valueOf(m)
                               .subtract(BigInteger.ONE);
        int k=1;
        int t=h.bitLength()-2;
        for(int i=t;i>=0;i--){
            b=(GF2mNB) b.pow(BigInteger.valueOf(2).pow(k))
                        .mul(b);
            k=k<<1;
            if(h.testBit(i)){
                b=(GF2mNB) b.square()
                            .mul(this);
                k++;
            }
        }
        return b.square();
    }

    public GF2mNB inv2(){
        return this.pow(BigInteger.ONE.shiftLeft(m).subtract(BigInteger.valueOf(2)));
    }

    @Override
    public String toString() {
        return element.toString(2);
    }

    public void printLref(){
        System.out.println(L);
    }

    public void printL(){
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){//colomn
                System.out.print(L[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        GF2mNB fieldElement=(GF2mNB) obj;
        if(this.element.compareTo(fieldElement.element)==0){
            return true;
        }else {
            return false;
        }
    }
}
