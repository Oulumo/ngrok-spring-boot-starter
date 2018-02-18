package com.oulumo.ngrok.client;

import com.oulumo.ngrok.client.dto.Tunnel;
import com.oulumo.ngrok.client.dto.TunnelCreationRequest;
import com.oulumo.ngrok.client.dto.TunnelListResponse;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author Zsolt Homorodi.
 */
public interface INgrokClientApi {
    @GET("/api/tunnels")
    Call<TunnelListResponse> listTunnels();

    @POST("/api/tunnels")
    Call<Tunnel> createTunnel(@Body TunnelCreationRequest request);

    @DELETE("/api/tunnels/{tunnelName}")
    Call<Void> deleteTunnel(@Path("tunnelName") String tunnelName);
}
