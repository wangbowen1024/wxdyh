package com.wxgzh.domain.response;

import com.wxgzh.domain.innerclass.Voice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ResponseVoice class
 * 响应语音消息
 *
 * @author BowenWang
 * @date 2019/08/03
 */
@XmlRootElement(name = "xml")
public class ResponseVoice extends BaseResponseMessage {
    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    @XmlElement(name = "Voice")
    private Voice voice;

    public ResponseVoice() {
        setMsgType("voice");
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
