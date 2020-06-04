package com.upanand.microservice.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.*;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


/**
 * This is the API gateways which decide wheather the incoming call to be 
 * processed or not based on our business logic.
 * @author upanand
 *
 */

@Component
public class ZuulLoggingFilter extends ZuulFilter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		return true; //Now we turned on the filter for every request , ideally we have to write our business logic for 
		              // the allowance of call
		}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = 
				RequestContext.getCurrentContext().getRequest(); // this will give the current http request.
		logger.info("request -> {} , request uri -> {} ", request , request.getRequestURI());
		
		return null;
		
	}

	@Override
	public String filterType() {
		// this is either pre , post or error , it will check based on this type , for now lets filter before call
		return "pre";
	}

	@Override
	public int filterOrder() {
	
		return 1; 
	}

}
