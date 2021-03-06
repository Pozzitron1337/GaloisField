import GaloisField.GF2mPB;
import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigInteger;
public class TestGF2mPB {
    BigInteger a =new BigInteger("14AA68C75CB0706A8F6E5E6B0F2C498E6CDFA5B29E0D99E17234A40801483BDC58B7DB48ABF0F76292527459EDA5F9BEC665C42810CA336CE7E3B9C342A9A8E0",16);
    BigInteger b=new BigInteger("181D45DA1C1B7302308D894E9FEA5716CFAF5E3167B64D873B54AB677308D58DE2C0A49266BC7C2CA5C17D29872271C99F4A214E16636C255D231E51108087ED",16);
    BigInteger n=new BigInteger("0EA4BF4DDA9F5DE3DC265565F504BB5DEA344ADC4E6D8F8FA6CA970FA7CCFFDEA4BE0E8D74E1698551CB9AE979A1355134A78260ED59BBAE0EB227F93280F76E",16);
    BigInteger generator=new BigInteger("2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000080000d",16);
    @Test
    public void TestAdd(){
        GF2mPB A=new GF2mPB(a,generator);
        GF2mPB B=new GF2mPB(b,generator);
        BigInteger eAdd=new BigInteger("0CB72D1D40AB0368BFE3D72590C61E98A370FB83F9BBD46649600F6F7240EE51BA777FDACD4C8B4E379309706A878877592FE56606A95F49BAC0A79252292F0D",16);
        GF2mPB expected=new GF2mPB(eAdd,generator);
        assertEquals(expected.toString(),A.add(B).toString());
    }
    @Test
    public void TestMul(){
        GF2mPB A=new GF2mPB(a,generator);
        GF2mPB B=new GF2mPB(b,generator);
        BigInteger eMul=new BigInteger("05251E6BF371C4FC04F1CBD7F36D33DDB049EFC51C66098B593C65D77D390B6F4B76D42C1F0FA0D0D7CF7C60C81C4D44C38A7132BD336AD5DFC227BA21A63F81",16);
        GF2mPB expected=new GF2mPB(eMul,generator);
        assertEquals(expected.toString(),A.mul(B).toString());
    }
    @Test
    public void TestSquare(){
        GF2mPB A=new GF2mPB(a,generator);
        BigInteger eSquare=new BigInteger("0F8AFFF23F1CD61AB08A9D560C137BEF0C0E7C73F4367E980959FD969F6219363998A456E7E69DFB312549E67324242B5CA4DA9FE3C14E44107FDA0193AECE7F",16);
        GF2mPB expected=new GF2mPB(eSquare,generator);
        assertEquals(expected.toString(),A.square().toString());
    }
    @Test
    public void TestInv(){
        GF2mPB A=new GF2mPB(a,generator);
        BigInteger eInv=new BigInteger("1219FF65EA8C1B141F6ADA08DD3089BC121CC2E53553FF13741D25C1D6F98FAFE722DB69FF67504041574B7E6A6F85E88024DFF0336F897F0EF0BCA7DE067C15",16);
        GF2mPB expected=new GF2mPB(eInv,generator);
        assertEquals(expected.toString(),A.inv().toString());
    }
    @Test
    public void TestPow(){
        GF2mPB A=new GF2mPB(a,generator);
        BigInteger ePow=new BigInteger("0DCAFC026F56B4455A13A9815EA02292CC6AE9D88C8E3E9BF764E34D65356465348C2523B514BED945F19330635B17064F0105A8E665F029CBA30C2C585A7652",16);
        GF2mPB expected=new GF2mPB(ePow,generator);
        assertEquals(expected.toString(),A.pow(n).toString());
    }
}
