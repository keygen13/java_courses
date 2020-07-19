package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("109.252.79.148");
        System.out.println(ipLocation);
        assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");
    }
}
