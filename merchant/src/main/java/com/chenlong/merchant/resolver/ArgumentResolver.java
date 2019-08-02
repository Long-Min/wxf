package com.chenlong.merchant.resolver;

import com.chenlong.common.constant.Constants;
import com.chenlong.entity.dto.Admin;
import com.chenlong.entity.dto.MerchantUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;


//自动注入SpringMVC参数的实现方法,需要WebConfig配置
public class ArgumentResolver implements WebArgumentResolver {
    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest nativeWebRequest) throws Exception {
        Class<?> parameterType = methodParameter.getParameterType();
        if(parameterType != null){

            if(parameterType.equals(MerchantUser.class)){
                HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
                return request.getAttribute(Constants.CURRENT_ADMIN);
            }
        }

        return UNRESOLVED;
    }
}
