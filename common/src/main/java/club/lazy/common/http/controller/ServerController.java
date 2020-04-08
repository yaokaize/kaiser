package club.lazy.common.http.controller;

import club.lazy.common.contant.ErrorCode;
import club.lazy.common.http.dao.JsonRpcRequest;
import club.lazy.common.http.dao.JsonRpcResponse;
import club.lazy.common.http.exception.CustomException;
import club.lazy.common.http.service.RpcHttpService;
import club.lazy.common.util.json.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
public class ServerController {

    private RpcHttpService rpcHttpService;

    @Autowired
    public ServerController(RpcHttpService rpcHttpService) {
        this.rpcHttpService = rpcHttpService;
    }

    /**
     * all http request entry
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/api/rpc", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postRpc(@RequestBody JsonRpcRequest request) {
        String json;
        try{
            json = JsonUtils.objectToJsonString(rpcHttpService.rpc(request));
        } catch (CustomException e) {
            json = JsonUtils.objectToJsonString(new JsonRpcResponse<>(ErrorCode.RUNTIME_ERROR, e.getMessage()));
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
