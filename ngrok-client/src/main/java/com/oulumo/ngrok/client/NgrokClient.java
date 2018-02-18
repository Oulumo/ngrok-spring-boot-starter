package com.oulumo.ngrok.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oulumo.ngrok.client.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zsolt Homorodi.
 */
public class NgrokClient {
    private static final Logger log = LoggerFactory.getLogger(NgrokClient.class);

    private INgrokClientApi clientApi;

    public NgrokClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:4040")
                .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
                .build();

        clientApi = retrofit.create(INgrokClientApi.class);
    }

    public Tunnel createHttpsTunnelFor(String name, int port) {
        return doCreateHTTPTunnel(name, port, true);
    }

    public Tunnel createHttpTunnelFor(String name, int port) {
        return doCreateHTTPTunnel(name, port, false);
    }

    private Tunnel doCreateHTTPTunnel(String name, int port, boolean httpsMode) {
        TunnelCreationRequest request = new TunnelCreationRequest();
        request.setAddress(Integer.toString(port));
        request.setName(name);
        request.setProtocol(TunnelProtocol.Http);
        request.setTlsType(httpsMode ? TunnelCreationTlsType.HttpsOnly : TunnelCreationTlsType.HttpOnly);
        request.setRequestInspectionEnabled(false);

        try {
            Call<Tunnel> createCall = clientApi.createTunnel(request);
            Response<Tunnel> createResponse = createCall.execute();

            if (createResponse.isSuccessful()) {
                return createResponse.body();
            }
            else {
                log.error("Tunnel creation failed: {}", (createResponse.errorBody() != null) ?
                                                        createResponse.errorBody().string() : "No response.");

                return null;
            }
        }
        catch (Exception e) {
            log.error("Error creating tunnel", e);

            return null;
        }
    }

    public List<Tunnel> listTunnels() {
        try {
            Call<TunnelListResponse> listCall = clientApi.listTunnels();
            Response<TunnelListResponse> listResponse = listCall.execute();

            if (listResponse.isSuccessful()) {
                TunnelListResponse tunnelListResponse = listResponse.body();
                if (tunnelListResponse != null) {
                    return tunnelListResponse.getTunnels();
                }
                else {
                    return new ArrayList<>();
                }
            }
            else {
                log.error("Tunnel listing failed: {}", (listResponse.errorBody() != null) ?
                                                        listResponse.errorBody().string() : "No response.");

                return new ArrayList<>();
            }
        }
        catch (Exception e) {
            log.error("Error listing tunnels", e);

            return new ArrayList<>();
        }
    }

    public boolean deleteTunnel(String name) {
        try {
            Call<Void> deleteCall = clientApi.deleteTunnel(name);
            Response<Void> deleteResponse = deleteCall.execute();

            if (!deleteResponse.isSuccessful()) {
                log.error("Tunnel deletion failed: {}", (deleteResponse.errorBody() != null) ?
                                                        deleteResponse.errorBody().string() : "No response.");
            }

            return deleteResponse.isSuccessful();
        }
        catch (Exception e) {
            log.error("Error deleting tunnel", e);

            return false;
        }
    }
}
