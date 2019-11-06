import java.math.BigInteger;
import java.util.Random;

public class Main {
    public static void TestingGFPB(){
        int m=509;
        String generaror509bit="";
        for(int i=m;i>=0;i--){
            if(i==509||i==23||i==3||i==2||i==0){
                generaror509bit+="1";
            }
            else {
                generaror509bit+="0";
            }
        }
        BigInteger e=new BigInteger(generaror509bit,2);
        GF2mPB a=new GF2mPB("1010101001111100",generaror509bit,2);
        System.out.println("a: "+a);
        GF2mPB b=new GF2mPB("1111111100000001001",generaror509bit,2);
        System.out.println("b: "+b);
        GF2mPB c=new GF2mPB("11110111",generaror509bit,2);
        System.out.println("c: "+c);
        GF2mPB expected1=a.add(b).mul(c);
        System.out.println("(a+b)*c = "+expected1);
        GF2mPB expected2=b.mul(c).add(c.mul(a));
        System.out.println("b*c+c*a = "+expected2);
        GF2mPB d=new GF2mPB("1010101011101",generaror509bit,2);
        System.out.println();
        System.out.println("d :"+d);
        GF2m exponent=d.pow(BigInteger.ONE.shiftLeft(m).subtract(BigInteger.ONE)); //d^(2^m-1)
        System.out.println("d^(2^509-1) = "+exponent);


    }

    public static void TestingGFNB(){
       int m=509/2;
        GF2mNB a=new GF2mNB("10101010111110011001010",m,2);
        System.out.println("a: "+a);
        GF2mNB b=new GF2mNB("1111111100000001001",m,2);
        System.out.println("b: "+b);
        GF2mNB c=new GF2mNB("11110111",m,2);
        System.out.println("c: "+c);
        GF2mNB expected1=a.add(b).mul(c);
        System.out.println("(a+b)*c = "+expected1);
        GF2mNB expected2=b.mul(c).add(c.mul(a));
        System.out.println("b*c+c*a = "+expected2);
        expected1.printBinaryValue();
        expected2.printBinaryValue();
        GF2mNB d=new GF2mNB("1010101011101",m,2);
        System.out.println("d: "+d);
        GF2mNB exponent=d.pow(BigInteger.ONE.shiftLeft(m).subtract(BigInteger.ONE)); //d^(2^m-1)
        System.out.println("d^(2^509-1) = "+exponent);

    }

    public static String randomBin(int length){
        Random r=new Random();
        String bin="";
        for (int i=0;i<length;i++){
            bin+=(r.nextInt()&1);
        }
        return bin;
    }

    public static void ProfillingNB(){
        int m=509;
        GF2mNB a;
        GF2mNB b;
        int iterations=100;
        System.out.println("Iterations: "+iterations);
        long[] timers=new long[iterations];
        for(int i=0;i<iterations;i++){
            long beg=0;
            long end=0;
            a=new GF2mNB(randomBin(m),m,2);
            b=new GF2mNB(randomBin(m),m,2);
            beg=System.nanoTime();
            a.add(b);
            end=System.nanoTime();
            timers[i]=(end-beg);
        }
        long sum=0;
        for (long l:timers){
            sum+=l;
        }
        System.out.println("Add time: "+sum);
        System.out.println("Avg add: "+sum/iterations);


        timers=new long[iterations];
        for(int i=0;i<iterations;i++){
            long beg=0;
            long end=0;
            a=new GF2mNB(randomBin(m),m,2);
            b=new GF2mNB(randomBin(m),m,2);
            beg=System.nanoTime();
            a.mul(b);
            end=System.nanoTime();
            timers[i]=(end-beg);
        }
        sum=0;
        for (long l:timers){
            sum+=l;
        }
        System.out.println("Mul time: "+sum);
        System.out.println("Avg add: "+sum/iterations);


        timers=new long[iterations];
        for(int i=0;i<iterations;i++){
            long beg=0;
            long end=0;
            a=new GF2mNB(randomBin(m),m,2);
            beg=System.nanoTime();
            a.trace();
            end=System.nanoTime();
            timers[i]=(end-beg);
        }
        sum=0;
        for (long l:timers){
            sum+=l;
        }
        System.out.println("trace time: "+sum);
        System.out.println("Avg trace: "+sum/iterations);


        timers=new long[iterations];
        for(int i=0;i<iterations;i++){
            long beg=0;
            long end=0;
            a=new GF2mNB(randomBin(m),m,2);
            beg=System.nanoTime();
            a.square();
            end=System.nanoTime();
            timers[i]=(end-beg);
        }
        sum=0;
        for (long l:timers){
            sum+=l;
        }
        System.out.println("square time: "+sum);
        System.out.println("Avg square: "+sum/iterations);


        timers=new long[iterations];
        for(int i=0;i<iterations;i++){
            long beg=0;
            long end=0;
            a=new GF2mNB(randomBin(m),m,2);
            beg=System.nanoTime();
            a.inv();
            end=System.nanoTime();
            timers[i]=(end-beg);
        }
        sum=0;
        for (long l:timers){
            sum+=l;
        }
        System.out.println("Inv time: "+sum);
        System.out.println("Avg Inv: "+sum/iterations);

    }

    public static void ProfillingPB(){
        int m=509;
        String generaror509bit="";
        for(int i=m;i>=0;i--){
            if(i==509||i==23||i==3||i==2||i==0){
                generaror509bit+="1";
            }
            else {
                generaror509bit+="0";
            }
        }
        GF2mPB a;
        GF2mPB b;
        int iterations=100;
        System.out.println("Iterations: "+iterations);
        long[] timers=new long[iterations];
        for(int i=0;i<iterations;i++){
            long beg=0;
            long end=0;
            a=new GF2mPB(randomBin(m),generaror509bit,2);
            b=new GF2mPB(randomBin(m),generaror509bit,2);
            beg=System.nanoTime();
            a.add(b);
            end=System.nanoTime();
            timers[i]=(end-beg);
        }
        long sum=0;
        for (long l:timers){
            sum+=l;
        }
        System.out.println("Add time: "+sum);
        System.out.println("Avg add: "+sum/iterations);


        timers=new long[iterations];
        for(int i=0;i<iterations;i++){
            long beg=0;
            long end=0;
            a=new GF2mPB(randomBin(m),generaror509bit,2);
            b=new GF2mPB(randomBin(m),generaror509bit,2);
            beg=System.nanoTime();
            a.mul(b);
            end=System.nanoTime();
            timers[i]=(end-beg);
        }
        sum=0;
        for (long l:timers){
            sum+=l;
        }
        System.out.println("Mul time: "+sum);
        System.out.println("Avg add: "+sum/iterations);


        timers=new long[iterations];
        for(int i=0;i<iterations;i++){
            long beg=0;
            long end=0;
            a=new GF2mPB(randomBin(m),generaror509bit,2);
            beg=System.nanoTime();
            a.trace();
            end=System.nanoTime();
            timers[i]=(end-beg);
        }
        sum=0;
        for (long l:timers){
            sum+=l;
        }
        System.out.println("trace time: "+sum);
        System.out.println("Avg trace: "+sum/iterations);


        timers=new long[iterations];
        for(int i=0;i<iterations;i++){
            long beg=0;
            long end=0;
            a=new GF2mPB(randomBin(m),generaror509bit,2);
            beg=System.nanoTime();
            a.square();
            end=System.nanoTime();
            timers[i]=(end-beg);
        }
        sum=0;
        for (long l:timers){
            sum+=l;
        }
        System.out.println("square time: "+sum);
        System.out.println("Avg square: "+sum/iterations);


        timers=new long[iterations];
        for(int i=0;i<iterations;i++){
            long beg=0;
            long end=0;
            a=new GF2mPB(randomBin(m),generaror509bit,2);
            beg=System.nanoTime();
            a.inv();
            end=System.nanoTime();
            timers[i]=(end-beg);
        }
        sum=0;
        for (long l:timers){
            sum+=l;
        }
        System.out.println("Inv time: "+sum);
        System.out.println("Avg Inv: "+sum/iterations);

    }

    public static void main(String[] args) {
        TestingGFNB();
        TestingGFPB();
        ProfillingPB();
        ProfillingNB();
    }

}
