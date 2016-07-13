package org.faceID.recognition.send;

import org.faceID.recognition.vo.DetectRequest;
import org.faceID.recognition.vo.DetectResponse;

public interface FaceHttpClient {
	/**
	 * 人脸检测接口
	 * 
	 * @param request
	 * @return
	 * @Author yujinshui
	 * @createTime 2016年7月12日 下午5:37:15
	 */
	DetectResponse execute(DetectRequest request);

}
