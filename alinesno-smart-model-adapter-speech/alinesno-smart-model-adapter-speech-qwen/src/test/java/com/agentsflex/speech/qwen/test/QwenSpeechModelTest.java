package com.agentsflex.speech.qwen.test;

import com.agentsflex.core.speech.RecognizeSpeechRequest;
import com.agentsflex.core.speech.RecognizeSpeechResponse;
import com.agentsflex.core.speech.SpeechResponse;
import com.agentsflex.core.speech.SynthesizeSpeechRequest;
import com.agentsflex.speech.qwen.QwenSpeechModel;
import com.agentsflex.speech.qwen.QwenSpeechModelConfig;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class QwenSpeechModelTest {

    private QwenSpeechModel speechModel;
    private QwenSpeechModelConfig config;
    private static final Logger log = LoggerFactory.getLogger(QwenSpeechModelTest.class);

    // 使用系统临时目录作为输出目录
    private static final String OUTPUT_DIR = System.getProperty("java.io.tmpdir");

    @BeforeEach
    void setUp() {
        // 检查环境变量是否存在
        String apiKey = System.getenv("ALINESNO_QIWEN_API_KEY");
        Assumptions.assumeTrue(apiKey != null && !apiKey.trim().isEmpty(),
            "ALINESNO_QIWEN_API_KEY 环境变量未设置，跳过测试");

        config = new QwenSpeechModelConfig();
        config.setApiKey(apiKey);
        speechModel = new QwenSpeechModel(config);

        // 确保输出目录存在
        File outputDir = new File(OUTPUT_DIR);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
    }

    @Test
    void testGenSpeech() {
        // 准备测试数据
        SynthesizeSpeechRequest request = new SynthesizeSpeechRequest();
        request.setText("一间有着精致窗户的花店，漂亮的木质门，摆放着花朵");
        request.setVoice("longxiaochun");

        // 执行测试
        SpeechResponse response = speechModel.synthesize(request);

        // 验证结果
        assertNotNull(response, "语音合成响应不应为空");
        assertNotNull(response.getSpeechMp3(), "音频数据不应为空");

        // 保存音频文件
        try {
            // 使用系统临时目录保存文件
            String fileName = "test_" + UUID.randomUUID() + ".mp3";
            Path outputPath = Paths.get(OUTPUT_DIR, fileName);
            File outputFile = outputPath.toFile();

            // 写入文件
            response.getSpeechMp3().writeToFile(outputFile);

            // 验证文件
            assertTrue(outputFile.exists(), "音频文件应该被创建");
            assertTrue(outputFile.length() > 0, "音频文件不应为空");

            // 打印完整的文件路径
            log.info("音频文件已保存到：{}", outputFile.getAbsolutePath());
            log.info("文件大小：{} 字节", outputFile.length());

        } catch (Exception e) {
            log.error("保存音频文件失败: ", e);
            fail("保存音频文件失败: " + e.getMessage());
        }
    }

    @Test
    void testRecognize() {
        // 设置识别模型
        config.setModel("paraformer-v1");

        // 准备测试数据
        RecognizeSpeechRequest request = new RecognizeSpeechRequest();
        request.setAudioList(List.of("http://data.linesno.com/demo_2.wav"));

        // 执行测试
        List<RecognizeSpeechResponse> responses = speechModel.recognize(request);

        // 验证结果
        assertNotNull(responses, "识别响应列表不应为空");
        assertFalse(responses.isEmpty(), "识别响应列表不应为空");

        // 验证第一个响应
        RecognizeSpeechResponse firstResponse = responses.get(0);
        assertNotNull(firstResponse.getTranscripts(), "转写结果不应为空");
        assertFalse(firstResponse.getTranscripts().isEmpty(), "转写结果不应为空");
        assertNotNull(firstResponse.getTranscripts().get(0).getText(), "转写文本不应为空");

        // 打印识别结果
        log.info("语音识别结果：{}", firstResponse.getTranscripts().get(0).getText());
    }
}
