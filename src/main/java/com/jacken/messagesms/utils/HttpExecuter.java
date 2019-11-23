package com.jacken.messagesms.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author honglongsun
 *
 */
public class HttpExecuter {
	private static Log logger = LogFactory.getLog(HttpExecuter.class);

	private static String charset = "UTF-8";

	private Map<String, String> requestParams = new HashMap<String, String>();
	private List<Header> requestHeaders = new ArrayList<Header>();
	private String stringRequestBody;
	private byte[] byteRequestBody;
	private Map<String, String> encodedFormBody;

	private String url;
	private HttpRequestBase method;

	private HttpClientContext context;

	public HttpExecuter(String url) {
		this.url = url;
	}

	public HttpExecuter(String url, Map<String, String> requestParams) {
		this(url);
		this.requestParams.putAll(requestParams);
	}

	public HttpExecuter(String url, String requestBody) {
		this(url);
		this.stringRequestBody = requestBody;
	}

	public HttpExecuter(String url, Map<String, String> paramsMap, String requestBody) {
		this(url);
		this.stringRequestBody = requestBody;
		this.requestParams.putAll(requestParams);
	}

	public HttpExecuter() {
	}

	public String doGet() {
		this.method = new HttpGet(url);
		return executeMethod();
	}

	public String doDelete() {
		this.method = new HttpDelete(url);
		return executeMethod();
	}

	public String doPost() {
		this.method = new HttpPost(url);
		setMethodBody((HttpPost) method);
		return executeMethod();

	}

	public String doPut() {
		this.method = new HttpPut(url);
		setMethodBody((HttpPut) method);
		return executeMethod();

	}

	private void setMethodBody(HttpEntityEnclosingRequestBase method) {
		if (stringRequestBody != null) {
			method.setEntity(new StringEntity(stringRequestBody, charset));
		} else if (byteRequestBody != null && byteRequestBody.length > 0) {
			method.setEntity(new ByteArrayEntity(byteRequestBody));
		} else if (encodedFormBody != null && encodedFormBody.size() > 0) {
			try {
				List<NameValuePair> paramList = new ArrayList<>();
				for (Map.Entry<String, String> entry : encodedFormBody.entrySet()) {
					paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				method.setEntity(new UrlEncodedFormEntity(paramList, charset));
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	private void setUrlParams() {
		if (requestParams != null && requestParams.size() > 0) {
			List<NameValuePair> paramList = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> param : requestParams.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
				paramList.add(pair);
			}
			if (paramList == null || paramList.size() <= 0)
				return;
			try {
				URI uri = new URIBuilder(url).setParameters(paramList).build();
				this.url = uri.toString();
				this.method.setURI(uri);
			} catch (URISyntaxException e) {
				String msg = "Encoding request params error,requestParams:" + requestParams + " ,charset:" + charset;
				logger.error(msg, e);
				throw new HttpExecuteException(5000, msg);
			}
		}
	}

	private void setMethodHeader() {
		if (requestHeaders.size() > 0) {
			for (int i = 0; i < requestHeaders.size(); i++) {
				this.method.addHeader(requestHeaders.get(i));
			}
		}
		method.addHeader("Connection", "close");
	}

	public HttpClientContext createBasicAuthContext(HttpHost host, String username, String password) {
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		Credentials defaultCreds = new UsernamePasswordCredentials(username, password);
		credsProvider.setCredentials(new AuthScope(host.getHostName(), host.getPort()), defaultCreds);
		AuthCache authCache = new BasicAuthCache();
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(host, basicAuth);
		context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		context.setAuthCache(authCache);
		return context;
	}

	private String executeMethod() {
		CloseableHttpClient client = HttpClients.createDefault();
		// setAuth();
		setUrlParams();
		setMethodHeader();
		requestInfo();
		try {
			CloseableHttpResponse response = client.execute(method, context);
			return getResponseText(response);
		} catch (IOException e) {
			String msg = "http request error:" + e.getMessage();
			logger.error(msg, e);
			throw new HttpExecuteException(5000, msg);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				String msg = "Cannot close httpclient:" + e.getMessage();
				logger.error(msg, e);
			}
		}
	}

	private String getResponseText(CloseableHttpResponse response) {
		int code = response.getStatusLine().getStatusCode();
		HttpEntity entity = response.getEntity();
		try {
			String responseText = EntityUtils.toString(entity);
			if (code < 300) {
				return responseText;
			} else {
				String msg = "Http request error:http code:" + code + ",url:" + method.getURI() + " ,response:"
						+ responseText;
				logger.error(msg);
				throw new HttpExecuteException(5000, msg);
			}
		} catch (ParseException | IOException e) {
			String msg = "Get response error:" + e.getMessage();
			logger.error(msg, e);
			throw new HttpExecuteException(5000, msg);
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				String msg = "Cannot close response:" + e.getMessage();
				logger.error(msg, e);
			}
		}

	}

	private void requestInfo() {
		if (logger.isDebugEnabled()) {
			String methodString = "Method:" + method.getMethod() + "\n";
			Header[] headers = method.getAllHeaders();
			String hearderString = "Hearder:";
			for (int i = 0; i < headers.length; i++) {
				String name = headers[i].getName();
				String value = headers[i].getName();
				hearderString += name + ":" + value;
				if (i != headers.length - 1) {
					hearderString += ";";
				} else {
					hearderString += "\n";
				}
			}
			String urlString = "Url:" + method.getURI().toString() + "\n";
			String body = "Body:";
			if (stringRequestBody != null) {
				body += stringRequestBody;
			} else if (byteRequestBody != null) {
				body += "byte length:" + byteRequestBody.length;
			}
			body += "\n";
			String format = "------------------------------------------------------------\n";
			String info = format + methodString + hearderString + urlString + body + format;
			logger.debug("request info \n" + info);
		}
	}

	public void addJsonRequestHeader() {
		addRequestHeader("Content-Type", "application/json");
	}

	public void addTextRequestHeader() {
		addRequestHeader("Content-Type", "text/plain");
	}

	public void addRequestHeader(String name, String value) {
		BasicHeader header = new BasicHeader(name, value);
		requestHeaders.add(header);
	}

	public void addRequestParam(String name, String value) {
		this.requestParams.put(name, value);
	}

	public void addRequestParams(Map<String, String> paramsMap) {
		this.requestParams.putAll(paramsMap);
	}

	public Map<String, String> getRequestParamsMap() {
		return requestParams;
	}

	public void setRequestBody(String stringRequestBody) {
		this.stringRequestBody = stringRequestBody;
	}

	public void setRequestBody(byte[] byteRequestBody) {
		this.byteRequestBody = byteRequestBody;
	}

	public void setRequestBody(Map<String, String> encodedFormBody) {
		this.encodedFormBody = encodedFormBody;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTokenToParam(String token) {
		this.addRequestParam("UPYOO_TOKEN", token);
	}

}
