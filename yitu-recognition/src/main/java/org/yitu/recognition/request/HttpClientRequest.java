package org.yitu.recognition.request;

import org.apache.log4j.Logger;
import org.yitu.recognition.util.HttpRequestUtil;
import org.yitu.recognition.util.YituConfig;
import org.yitu.recognition.vo.FaceFeatureRequest;
import org.yitu.recognition.vo.FaceFeatureResponse;
import org.yitu.recognition.vo.FaceQueryRequest;
import org.yitu.recognition.vo.FaceQueryResponse;

import com.alibaba.fastjson.JSON;

/**
 * 私有|公有 云请求
 * 
 * @author yujinshui
 * @createTime 2016年7月1日 下午4:33:58
 */
public class HttpClientRequest implements HttpClientUtil {
	private static final Logger logger = Logger.getLogger(HttpClientRequest.class);
	private YituConfig config;

	public HttpClientRequest(YituConfig config) {
		this.config = config;
	}

	/**
	 * 
	 * @see org.yitu.recognition.request.HttpClientUtil#execute(org.yitu.recognition.vo.FaceFeatureRequest)
	 */
	@Override
	public FaceFeatureResponse execute(FaceFeatureRequest face) {
		return this.execute(JSON.toJSONString(face), config, config.getLOCAL_URL());
	}

	/**
	 * 
	 * @see org.yitu.recognition.request.HttpClientUtil#compareExecute(org.yitu.recognition.vo.FaceQueryRequest,
	 *      org.yitu.recognition.util.YituConfig)
	 */
	@Override
	public FaceQueryResponse compareExecute(FaceQueryRequest face) {
		FaceQueryResponse response = null;
		try {
			String res = HttpRequestUtil.httpPostWithJSON(JSON.toJSONString(face), config, config.getYITU_PAIR_URL());
			response = JSON.parseObject(res, FaceQueryResponse.class);
		} catch (Exception e) {
			logger.error("特征对比失败 - Get FaceQueryResponse is wrong.", e);
		}
		return response;
	}

	private FaceFeatureResponse execute(String json, YituConfig config, String url) {
		FaceFeatureResponse response = null;
		try {
			String res = HttpRequestUtil.httpPostWithJSON(json, config, url);
			response = JSON.parseObject(res, FaceFeatureResponse.class);
		} catch (Exception e) {
			logger.error("特征抽取失败 - Get FaceFeatureResponse is wrong.", e);
		}
		return response;
	}

}