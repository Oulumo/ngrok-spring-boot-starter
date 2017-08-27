package com.oulumo.ngrok.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oulumo.ngrok.retrofit.dto.Tunnel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class NgrokClientTest {
    public static final String HTTPS_TEST_TUNNEL = "NgrokClientTestHttpsTest";
    public static final String HTTP_TEST_TUNNEL = "NgrokClientTestHttpTest";

    private static NgrokClient ngrokClient;

    @BeforeClass
    public static void setup() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ngrokClient = new NgrokClient(objectMapper);
    }

    @AfterClass
    public static void tearDown() throws Exception {
    }

    @Test
    public void createHttpsTunnelFor() throws Exception {
        Tunnel tunnel = ngrokClient.createHttpsTunnelFor(HTTPS_TEST_TUNNEL, 9876);
        assertThat(tunnel, is(notNullValue()));

        Thread.sleep(2000);
        List<Tunnel> tunnels = ngrokClient.listTunnels();
        assertThat(tunnels, is(notNullValue()));
        final boolean[] found = {false};
        tunnels.forEach(tunnel1 -> {
            if (tunnel1.getName().equals(HTTPS_TEST_TUNNEL)) {
                found[0] = true;
            }
        });
        assertThat(found[0], is(true));

        boolean deleteSuccess = ngrokClient.deleteTunnel(HTTPS_TEST_TUNNEL);
        assertThat(deleteSuccess, is(true));
    }

    @Test
    public void createHttpTunnelFor() throws Exception {
        Tunnel tunnel = ngrokClient.createHttpTunnelFor(HTTP_TEST_TUNNEL, 9875);
        assertThat(tunnel, is(notNullValue()));

        Thread.sleep(2000);
        List<Tunnel> tunnels = ngrokClient.listTunnels();
        assertThat(tunnels, is(notNullValue()));
        final boolean[] found = {false};
        tunnels.forEach(tunnel1 -> {
            if (tunnel1.getName().equals(HTTP_TEST_TUNNEL)) {
                found[0] = true;
            }
        });
        assertThat(found[0], is(true));

        boolean deleteSuccess = ngrokClient.deleteTunnel(HTTP_TEST_TUNNEL);
        assertThat(deleteSuccess, is(true));
    }
}