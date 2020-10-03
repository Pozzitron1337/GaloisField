import GaloisField.GF2mNB;
import org.junit.Test;
import java.math.BigInteger;
import static org.junit.Assert.assertEquals;
public class TestGF2mNB {
    int m=509;
    BigInteger a=new BigInteger("0BA217D4E2FF4ECA3FC3797C0E0B9C1DA0A520E683954E977F7391B826961C328773E6C4B0546C98DBE5785FCA7F87D75D9BE22C9A485273EDCA0B30E1294A56",16);
    BigInteger b=new BigInteger("19B2FD68BBD6C9B297B19216BFBBA93402703E127D5D0D16BB9481E5D5F3E7CACEB45C987F9DA0613D3446A8054BD816F94BC09BAB4F08A3F6A8812F1593ADFF",16);
    BigInteger n=new BigInteger("02E9006812C54317E3FDC2A9D34F55D3B37E2FBB45466B1A852D588A471847575E4DB1BDE875116D6EB52FFFBF1D7A0FD02E835F45A2F9C30680A320953718EF",16);
    @Test
    public void TestAdd(){
        GF2mNB A=new GF2mNB(a,m);
        GF2mNB B=new GF2mNB(b,m);
        BigInteger eAdd=new BigInteger("1210EABC59298778A872EB6AB1B03529A2D51EF4FEC84381C4E7105DF365FBF849C7BA5CCFC9CCF9E6D13EF7CF345FC1A4D022B731075AD01B628A1FF4BAE7A9",16);
        GF2mNB expected=new GF2mNB(eAdd,m);
        assertEquals(expected.toString(),A.add(B).toString());
    }
    @Test
    public void TestMul(){
        GF2mNB A=new GF2mNB(a,m);
        GF2mNB B=new GF2mNB(b,m);
        BigInteger eMul=new BigInteger("1D011CC65E5184362A0B433766346185A6E109EE8FD3B8DBD5F304BC29F44BA131B60E863F233FE27AD582BC885147F3EF1CA8876D19D493E105D0B7C8EA6DE2",16);
        GF2mNB expected=new GF2mNB(eMul,m);
        assertEquals(expected.toString(),A.mul(B).toString());
    }
    @Test
    public void TestSquare(){
        GF2mNB A=new GF2mNB(a,m);
        BigInteger eSquare=new BigInteger("05D10BEA717FA7651FE1BCBE0705CE0ED052907341CAA74BBFB9C8DC134B0E1943B9F362582A364C6DF2BC2FE53FC3EBAECDF1164D242939F6E505987094A52B",16);
        GF2mNB expected=new GF2mNB(eSquare,m);
        assertEquals(expected.toString(),A.square().toString());
    }
    @Test
    public void TestInv(){
        GF2mNB A=new GF2mNB(a,m);
        BigInteger eInv=new BigInteger("0229B41752FCC7049EF51E22A908DA8EF6B822329BBCE80F0F778FA29B3C4253BE4E2BF13AD1D18CC738950E285C7CA66A5DA4A8360C1804A7C5B0574E433E53",16);
        GF2mNB expected=new GF2mNB(eInv,m);
        assertEquals(expected.toString(),A.inv().toString());
    }
    @Test
    public void TestPow(){
        GF2mNB A=new GF2mNB(a,m);
        BigInteger ePow=new BigInteger("1D52FC567BC28A7A4768E68A16DF8122C9C6E8E44060B009FF07AEEB89874F530CCA349F63DC97A7DEB3CE8997F32123100718E80F7A360D4A2A838C20A8AD8B",16);
        GF2mNB expected=new GF2mNB(ePow,m);
        assertEquals(expected.toString(),A.pow(n).toString());
    }
}
