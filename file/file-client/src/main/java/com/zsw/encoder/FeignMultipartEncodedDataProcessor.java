package com.zsw.encoder;

/**
 * Processor that supports multiple file uploads
 *
 * @author ZhangShaowei on 2017/9/27 11:35
 */

//public class FeignMultipartEncodedDataProcessor extends SpringMultipartEncodedDataProcessor {
//
//    /** */
//    private ObjectFactory<HttpMessageConverters> converters;
//
//    /**
//     * @param converters HttpMessageConverters
//     */
//    FeignMultipartEncodedDataProcessor(final ObjectFactory<HttpMessageConverters> converters) {
//        this.converters = converters;
//    }
//
//    /**
//     * 文件上传
//     *
//     * @param data     data
//     * @param template RequestTemplate
//     */
//    public void process(final Object data, final RequestTemplate template) {
//        if (data instanceof MultipartFile) { //单文件
//            this.process((MultipartFile) data, template);
//        } else { //多文件
//            this.process((MultipartFile[]) data, template);
//        }
//    }
//
//    /**
//     * 单文件上传
//     *
//     * @param file     MultipartFile
//     * @param template RequestTemplate
//     */
//    private void process(final MultipartFile file, final RequestTemplate template) {
//        super.process(Collections.singletonMap(file.getName(), file), template);
//    }
//
//
//    /**
//     * 多文件上传
//     *
//     * @param files    MultipartFile[]
//     * @param template RequestTemplate
//     */
//    private void process(final MultipartFile[] files, final RequestTemplate template) {
//        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
//        encodeMultipartFiles(map, files[0].getName(), files);
//        encodeRequest(map, template);
//    }
//
//
//    /**
//     * Wraps a single {@link MultipartFile} into a {@link HttpEntity} and sets the
//     * {@code Content-type} header to {@code application/octet-stream}
//     *
//     * @param file MultipartFile
//     * @return HttpEntity<?>
//     */
//    private HttpEntity<?> encodeMultipartFile(final MultipartFile file) {
//        HttpHeaders filePartHeaders = new HttpHeaders();
//        filePartHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        try {
//            Resource multipartFileResource = new MultipartFileResource(
//                    file.getOriginalFilename(), file.getSize(), file.getInputStream());
//            return new HttpEntity<>(multipartFileResource, filePartHeaders);
//        } catch (IOException ex) {
//            throw new EncodeException("Cannot encode request.", ex);
//        }
//    }
//
//
//    /**
//     * Fills the request map with {@link HttpEntity}s containing the given {@link MultipartFile}s.
//     * Sets the {@code Content-type} header to {@code application/octet-stream} for each file.
//     *
//     * @param map   current request map.
//     * @param name  the name of the array field in the multipart form.
//     * @param files MultipartFile[]
//     */
//    private void encodeMultipartFiles(
//            final MultiValueMap<String, Object> map,
//            final String name,
//            final MultipartFile[] files) {
//        try {
//            for (MultipartFile file : files) {
//                HttpHeaders filePartHeaders = new HttpHeaders();
//                filePartHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//                filePartHeaders.setContentDispositionFormData(file.getName(), file.getOriginalFilename());
//                Resource multipartFileResource = new MultipartFileResource(
//                        file.getOriginalFilename(), file.getSize(), file.getInputStream());
//                map.add(name, new HttpEntity<>(multipartFileResource, filePartHeaders));
//            }
//        } catch (IOException ex) {
//            throw new EncodeException("Cannot encode request.", ex);
//        }
//    }
//
//
//    /**
//     * Calls the conversion chain actually used by
//     * {@link org.springframework.web.client.RestTemplate}, filling the body of the request
//     * template.
//     *
//     * @param value    data
//     * @param template RequestTemplate
//     */
//    @SuppressWarnings("unchecked")
//    private void encodeRequest(
//            final Object value, final RequestTemplate template) {
//        HttpHeaders requestHeaders = this.getHttpHeaders(template.headers());
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        HttpOutputMessage dummyRequest = new HttpOutputMessageImpl(outputStream, requestHeaders);
//        try {
//            Class<?> requestType = value.getClass();
//            MediaType requestContentType = requestHeaders.getContentType();
//            for (HttpMessageConverter<?> messageConverter : converters.getObject()) {
//                if (messageConverter.canWrite(requestType, requestContentType)) {
//                    ((HttpMessageConverter<Object>) messageConverter).write(value, requestContentType, dummyRequest);
//                    break;
//                }
//            }
//        } catch (IOException ex) {
//            throw new EncodeException("Cannot encode request.", ex);
//        }
//        HttpHeaders headers = dummyRequest.getHeaders();
//        if (headers != null) {
//            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
//                template.header(entry.getKey(), entry.getValue());
//            }
//        }
//
//        // we should use a template output stream... this will cause issues if files are too big,
//        // since the whole request will be in memory.
//        template.body(outputStream.toByteArray(), Util.UTF_8);
//    }
//
//
//    /**
//     * @param headers Map
//     * @return HttpHeaders
//     */
//    private HttpHeaders getHttpHeaders(final Map<String, Collection<String>> headers) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        for (Map.Entry<String, Collection<String>> entry : headers.entrySet()) {
//            httpHeaders.put(entry.getKey(), new ArrayList<>(entry.getValue()));
//        }
//        return httpHeaders;
//    }
//
//    /**
//     * Minimal implementation of {@link org.springframework.http.HttpOutputMessage}. It's needed to
//     * provide the request body output stream to
//     * {@link org.springframework.http.converter.HttpMessageConverter}s
//     */
//    private static class HttpOutputMessageImpl implements HttpOutputMessage {
//
//        /** */
//        private final ByteArrayOutputStream body;
//        /** */
//        private final HttpHeaders headers;
//
//        /**
//         * @param body    ByteArrayOutputStream
//         * @param headers HttpHeaders
//         */
//        HttpOutputMessageImpl(final ByteArrayOutputStream body, final HttpHeaders headers) {
//            this.body = body;
//            this.headers = headers;
//        }
//
//        @Override
//        public ByteArrayOutputStream getBody() throws IOException {
//            return body;
//        }
//
//        @Override
//        public HttpHeaders getHeaders() {
//            return headers;
//        }
//
//    }
//
//    /**
//     *
//     */
//    static class MultipartFileResource extends InputStreamResource {
//
//        /** */
//        private final String filename;
//        /** */
//        private final long size;
//
//        /**
//         * @param filename    filename
//         * @param size        size
//         * @param inputStream InputStream
//         */
//        MultipartFileResource(final String filename, final long size, final InputStream inputStream) {
//            super(inputStream);
//            this.size = size;
//            this.filename = filename;
//        }
//
//        @Override
//        public String getFilename() {
//            return this.filename;
//        }
//
//        @Override
//        public InputStream getInputStream() throws IOException, IllegalStateException {
//            return super.getInputStream(); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public long contentLength() throws IOException {
//            return size;
//        }
//
//    }


//}
