package com.agentflex.ocr.aliyun;

import com.agentsflex.core.ocr.OcrModel;
import com.agentsflex.core.ocr.OcrRequest;
import com.agentsflex.core.ocr.OcrResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * AipOcrModel
 *
 * @author luoxiaodong
 */
@Slf4j
public class AliyunOcrModel implements OcrModel {

    private final AliyunOcrModelConfig config ;

    public AliyunOcrModel(AliyunOcrModelConfig config) {
        this.config = config ;
    }

    /**
     * <b>description</b> :
     * <p>使用凭据初始化账号Client</p>
     * @return Client
     *
     * @throws Exception
     */
    @SneakyThrows
    public com.aliyun.ocr_api20210707.Client createClient(String accessKeyId , String accessKeySecret , String endPoint) {
        com.aliyun.credentials.Client credential = new com.aliyun.credentials.Client();
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
            .setCredential(credential);

        config.setAccessKeyId(accessKeyId) ;
        config.setAccessKeySecret(accessKeySecret) ;

        // Endpoint 请参考 https://api.aliyun.com/product/ocr-api
        config.endpoint = endPoint ;
        return new com.aliyun.ocr_api20210707.Client(config);
    }


    @SneakyThrows
    @Override
    public OcrResponse recognize(OcrRequest ocrRequest) {

        String accessKeyId = this.config.getApiKey() ;
        String accessKeySecret = this.config.getSecretKey() ;
        String endPoint = this.config.getEndpoint() ;

        File file = ocrRequest.getImage() ;

        String data = "" ;
        long startTime = System.currentTimeMillis() ;

        com.aliyun.ocr_api20210707.Client client = createClient(accessKeyId , accessKeySecret , endPoint);
        // 需要安装额外的依赖库，直接点击下载完整工程即可看到所有依赖。
        java.io.InputStream bodyStream = com.aliyun.darabonba.stream.Client.readFromFilePath(file.getAbsolutePath());
        com.aliyun.ocr_api20210707.models.RecognizeAllTextRequest recognizeAllTextRequest = new com.aliyun.ocr_api20210707.models.RecognizeAllTextRequest()
            .setBody(bodyStream)
            .setType("Advanced");

        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            com.aliyun.ocr_api20210707.models.RecognizeAllTextResponse resp = client.recognizeAllTextWithOptions(recognizeAllTextRequest, runtime);

            data = resp.getBody().getData().getContent() ;

            log.debug("Aliyun Ocr识别结果:" + data) ;

        } catch (Exception _error) {
            log.error("" , _error);
        }

        long usageTime = System.currentTimeMillis() - startTime ;
        return new OcrResponse(data , usageTime) ;
    }
}
