package cmmcloud.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.StringUtils;

@Slf4j
public class ReadableRequestWrapper extends HttpServletRequestWrapper {

    private final Charset encoding;
    private byte[] rawData;

    public ReadableRequestWrapper(HttpServletRequest request) throws IOException{
        super(request);

        String characterEncoding = request.getCharacterEncoding();

        if (StringUtils.isEmpty(characterEncoding.trim())) {
            characterEncoding = StandardCharsets.UTF_8.name();
        }
        this.encoding = Charset.forName(characterEncoding);

        try {
            InputStream inputStream = super.getInputStream();
            this.rawData = IOUtils.toByteArray(inputStream);

            String reqBodyStr = new String(rawData);
            if (reqBodyStr == null || reqBodyStr.isEmpty()) return;

            JSONParser parser = new JSONParser();
            Object reqObj = parser.parse(reqBodyStr);
            JSONObject reqJsonObj = (JSONObject) reqObj;

            // SQL Injection 처리
            JSONObject sendObj = new JSONObject();
            reqJsonObj.forEach((key, value) -> {

                if (value instanceof String) {
                    String valueStr = value.toString();
                    Object valueObj = valueStr
                        .replaceAll("\\p{Space}", "")
                        .replaceAll("\\*", "")
                        .replaceAll("%", "")
                        .replaceAll(";", "")
//                        .replaceAll("-", "")
                        .replaceAll("\\+", "");
//                        .replaceAll(",", "");

                    sendObj.put(key, valueObj);
                }
                else {
                    sendObj.put(key, value);
                }

            });

            this.rawData = sendObj.toString().getBytes();

        } catch (IOException e) {
            throw e;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
    }

    @Override
    public ServletRequest getRequest() {
        return super.getRequest();
    }
}
