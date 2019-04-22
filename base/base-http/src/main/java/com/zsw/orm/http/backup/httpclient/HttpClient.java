package com.zsw.orm.http.backup.httpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 待实现
 *
 * @author ZhangShaowei
 */
@Deprecated
public abstract class HttpClient {
    private static Logger logger = LoggerFactory.getLogger(HttpClient.class);

//    private static LayeredConnectionSocketFactory sslsf = null;
//    private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = null;
//    private static RequestConfig requestConfig = null;

    static {
//        try {
//            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
//        } catch (NoSuchAlgorithmException e) {
//            logger.error("<线程{}>ssl初始化异常:{}", Thread.currentThread().getId(), e.getMessage());
//        }
//        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();
//        poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//        poolingHttpClientConnectionManager.setMaxTotal(1000);
//        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(500);
//        requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(3000)
//                .setConnectionRequestTimeout(500).build();
    }

//    public static String doGet(String url, Map<String, String> params)
//            throws ClientProtocolException, IOException, URISyntaxException {
//        URIBuilder uriBuilder = new URIBuilder(url);
//        for (String key : params.keySet()) {
//            uriBuilder.addParameter(key, params.get(key));
//        }
//        return doGet(uriBuilder.build().toString());
//    }
//
//    public static String doGet(String url) throws ClientProtocolException, IOException {
//        HttpGet httpGet = new HttpGet(url);
//        CloseableHttpResponse response = null;
//        try {
//            CloseableHttpClient httpClient = HttpClients.custom()
//                    .setConnectionManager(poolingHttpClientConnectionManager).setDefaultRequestConfig(requestConfig)
//                    .build();
//            response = httpClient.execute(httpGet);
//            if (response.getStatusLine().getStatusCode() == 200) {
//                return EntityUtils.toString(response.getEntity(), "UTF-8");
//            }
//        } finally {
//            if (response != null) {
//                response.close();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 发送 post请求
//     *
//     * @param httpUrl 地址
//     * @throws IOException
//     * @throws ClientProtocolException
//     */
//    public static String sendHttpPost(String httpUrl) throws ClientProtocolException, IOException {
//        HttpPost httpPost = new HttpPost(httpUrl);
//        return sendHttpPost(httpPost, Consts.UTF_8.name());
//    }
//
//    /**
//     * 发送 post请求form
//     *
//     * @param httpUrl 地址
//     * @param params  参数(格式:key1=value1&key2=value2)
//     * @throws IOException
//     * @throws ClientProtocolException
//     */
//    public static String sendHttpPostForm(String httpUrl, String params) throws ClientProtocolException, IOException {
//        HttpPost httpPost = new HttpPost(httpUrl);
//        StringEntity stringEntity = new StringEntity(params, ContentType.APPLICATION_FORM_URLENCODED);
//        httpPost.setEntity(stringEntity);
//        return sendHttpPost(httpPost, Consts.UTF_8.name());
//    }
//
//    /**
//     * 发送 post请求form
//     *
//     * @param httpUrl 地址
//     * @param maps    参数
//     * @throws IOException
//     * @throws ClientProtocolException
//     */
//    public static String sendHttpPost(String httpUrl, Map<String, String> maps)
//            throws ClientProtocolException, IOException {
//        HttpPost httpPost = new HttpPost(httpUrl);
//        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//        for (String key : maps.keySet()) {
//            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
//        }
//        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8));
//        return sendHttpPost(httpPost, Consts.UTF_8.name());
//    }
//
//    /**
//     * 发送 post请求xml
//     *
//     * @param httpUrl        地址
//     * @param requestContent 请求内容
//     * @param encoding       返回内容编码
//     * @return
//     * @throws ClientProtocolException
//     * @throws IOException
//     */
//    public static String sendHttpPostXml(String httpUrl, String requestContent, String encoding)
//            throws ClientProtocolException, IOException {
//        HttpPost httpPost = new HttpPost(httpUrl);
//        StringEntity stringEntity = new StringEntity(requestContent, ContentType.TEXT_XML);
//        httpPost.setEntity(stringEntity);
//        return sendHttpPost(httpPost, encoding);
//    }
//
//    /**
//     * 发送 post请求json
//     *
//     * @param httpUrl        地址
//     * @param requestContent 请求内容
//     * @param encoding       返回内容编码
//     * @return
//     * @throws ClientProtocolException
//     * @throws IOException
//     */
//    public static String sendHttpPostJson(String httpUrl, String requestContent, String encoding)
//            throws ClientProtocolException, IOException {
//        HttpPost httpPost = new HttpPost(httpUrl);
//        StringEntity stringEntity = new StringEntity(requestContent, ContentType.APPLICATION_JSON);
//        httpPost.setEntity(stringEntity);
//        return sendHttpPost(httpPost, encoding);
//    }
//
//    /**
//     * 发送post请求
//     *
//     * @param httpPost
//     * @return
//     * @throws IOException
//     * @throws ClientProtocolException
//     */
//    private static String sendHttpPost(HttpPost httpPost, String encoding) throws ClientProtocolException, IOException {
//        CloseableHttpResponse response = null;
//        String responseContent = null;
//        try {
//            // 连接池创建httpClient实例
//            CloseableHttpClient httpClient = HttpClients.custom()
//                    .setConnectionManager(poolingHttpClientConnectionManager).setDefaultRequestConfig(requestConfig)
//                    .build();
//            // 执行请求
//            response = httpClient.execute(httpPost);
//            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) { // 200
//                throw new RuntimeException(
//                        "请求[" + httpPost.getURI() + "]地址失败,状态代码:" + response.getStatusLine().getStatusCode());
//            }
//            responseContent = EntityUtils.toString(response.getEntity(), encoding);
//        } finally {
//            // 关闭连接,释放资源
//            if (response != null) {
//                response.close();
//            }
//        }
//        return responseContent;
//    }
}
