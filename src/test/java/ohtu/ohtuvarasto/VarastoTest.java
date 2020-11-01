package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tyhjastaVarastostaEiVoiOttaa() {
        double saatuMaara = varasto.otaVarastosta(2);
        
        // varaston pitäisi olla tyhjä, joten jos sielä yritetään ottaa, ei saada mitään 
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void tyhjanVarastonSaldoEiMuutuOtettaessa() {
        varasto.otaVarastosta(2);
        
        // varaston pitäisi olla tyhjä, joten jos sielä yritetään ottaa, se on edelleen tyhjä 
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonTilaEiYlity() {
        varasto.lisaaVarastoon(12);
        
        // varaston tilavuus on 10, joten jos sinne yritetään lisätä enemmän kuin 10, saldoa pitäisi olla 10
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void taydenVarastonTilaOnNolla() {
        varasto.lisaaVarastoon(12);
        
        // varasto on täynnä, tilan pitäsi olla nolla
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonTilavuusEiVoiOllaNegatiivinen() {
        Varasto varasto2 = new Varasto(-1);
        
        // yritetään tehdä varastolle negatiivinen tilavuus, tilavuudeksi pitäisi tulla nolla
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitetullaVarastollaOikeaTilavuus() {
        Varasto varasto2 = new Varasto(10, 2);
        
        // uuden varaston tilavuus pitäisi olla 10
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitetullaVarastollaOikeaSaldo() {
        Varasto varasto2 = new Varasto(10, 2);
        
        // uuden varaston saldo pitäisi olla 2
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitetunVarastonTilavuusEiVoiOllaNegatiivinen() {
        Varasto varasto2 = new Varasto(-1, 0);
        
        // yritetään tehdä varastolle negatiivinen tilavuus, tilavuudeksi pitäisi tulla nolla
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitetunVarastonSaldoEiVoiOllaNegatiivinen() {
        Varasto varasto2 = new Varasto(10, -1);
        
        // yritetään tehdä varastolle negatiivinen alkusaldo, saldoksi pitäisi tulla nolla
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoEiVoiYlittääTilavuutta() {
        Varasto varasto2 = new Varasto(2, 10);
        
        // varaston tilavuus 2, joten saldo ei voi ylittää kahta
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringPalauttaaOikein() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
    
    @Test
    public void eiVoiLisataNegatiivista() {
        varasto.lisaaVarastoon(-1);
        
        //yritetään lisätä negatiivinen määrä varastoon, joten saldon ei pitäisi muuttua
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaNegatiivista() {
        double saatuMaara = varasto.otaVarastosta(-1);
        
        //yritetään ottaa negatiivinen määrä varastosta, saadun määrän pitäisi olla nolla
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

}